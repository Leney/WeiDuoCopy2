package com.zhongke.weiduo.library.rxjava;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;
import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.library.sqlbrite.SQLBriteProvider;
import com.zhongke.weiduo.mvp.db.local.Data;
import com.zhongke.weiduo.mvp.db.local.SqlHelper;
import com.zhongke.weiduo.mvp.db.local.TransformHelper;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;

import rx.Observable;

/**
 * Created by ${xingen} on 2017/11/6.
 * <p>
 * 一个工具类，构建各种Observable对象
 */

public class ObservableUtils {
    /**
     * 构建保存信息的Observable
     *
     * @param loginResult
     * @return
     */
    public static Observable<Boolean> createSavePersonMSG(LoginResult loginResult) {
        return Observable.create(subscriber -> {
            try {
                BriteDatabase briteDatabase = SQLBriteProvider.getInstance(ZkApplication.getInstance()).getBriteDatabase();
                briteDatabase.delete(Data.TABLE_USER_INFO, null, new String[]{});
                briteDatabase.insert(Data.TABLE_USER_INFO, TransformHelper.toContentValues(loginResult));
                subscriber.onNext(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 构建保存注册的信息
     *
     * @param account
     * @return
     */
    public static Observable<Boolean> createSaveRegisterMSG(String account) {
        return Observable.create(subscriber -> {
            try {
                BriteDatabase briteDatabase = SQLBriteProvider.getInstance(ZkApplication.getInstance()).getBriteDatabase();
                briteDatabase.insert(Data.TABLE_USER_INFO, TransformHelper.toContentValues(account));
                subscriber.onNext(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * token过期，清空数据
     *
     * @return
     */
    public static Observable<Boolean> createDeletePersonMSG() {
        return Observable.create(subscriber -> {
            try {
                BriteDatabase briteDatabase = SQLBriteProvider.getInstance(ZkApplication.getInstance()).getBriteDatabase();
                briteDatabase.delete(Data.TABLE_USER_INFO, null, new String[]{});
                subscriber.onNext(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 查询数据库，获取Token
     *
     * @return
     */
    public static Observable<String> createQueryToken() {
        return Observable.create(subscriber -> {
            String token;
            Cursor cursor = null;
            try {
                cursor = SQLBriteProvider.getInstance(ZkApplication.getInstance()).getBriteDatabase().query(SqlHelper.createQueryTokenSQL(), new String[]{});
                if (cursor != null && cursor.moveToFirst()) {
                    token = cursor.getString(cursor.getColumnIndex(Data.COLUMNS_USER_TOKEN));
                } else {
                    token = "";
                }
                subscriber.onNext(token);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        });
    }

    /**
     * 查询数据库，长时间订阅
     *
     * @return
     */
    public static Observable<LoginResult> createKeepQueryAccountMsg() {
        return SQLBriteProvider.getInstance(ZkApplication.getInstance()).getBriteDatabase()
                .createQuery(Data.TABLE_USER_INFO, SqlHelper.createQueryAccountSQL(), new String[]{})
                .flatMap(query -> {
                    LoginResult loginResult = new LoginResult();
                    try {
                        Cursor cursor = query.run();
                        if (cursor != null && cursor.moveToFirst()) {
                            TransformHelper.toContentLogResultBean(cursor, loginResult);
                        }
                        cursor.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Observable.just(loginResult);
                });

    }

    public static Observable<LoginResult> createQueryAccountMsg() {
        return Observable.create(subscriber -> {
            LoginResult loginResult = new LoginResult();
            try {
                Cursor cursor = SQLBriteProvider.getInstance(ZkApplication.getInstance()).getBriteDatabase().query(SqlHelper.createQueryAccountSQL(), new String[]{});
                if (cursor != null && cursor.moveToFirst()) {
                    TransformHelper.toContentLogResultBean(cursor, loginResult);
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            subscriber.onNext(loginResult);
        });

    }
}
