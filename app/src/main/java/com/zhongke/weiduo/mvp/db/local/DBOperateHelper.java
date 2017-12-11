package com.zhongke.weiduo.mvp.db.local;

import android.content.ContentValues;

import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.sqlbrite.SQLBriteProvider;
import com.zhongke.weiduo.mvp.model.entity.LoginSuccessBean;


/**
 * 数据库操作类
 * Created by llj on 2017/7/19.
 */

public class DBOperateHelper {
    /**
     * 将登录成功的数据插入到数据库中
     *
     * @param loginSuccessBean
     */
    public static void insertLoginSuccessUserInfo(LoginSuccessBean loginSuccessBean) {
        ContentValues values = TransformHelper.toContentValuesByLoginSuccessBean(loginSuccessBean);
        if (values != null) {
            SQLBriteProvider.getInstance(UIUtils.getContext()).getBriteDatabase().insert(Data.TABLE_USER_INFO, values);
        }
    }

    /**
     * 更新登录成功的UserInfo数据
     *
     * @param loginSuccessBean
     */
    public static void updateLoginSuccessUserInfo(LoginSuccessBean loginSuccessBean) {
        ContentValues values = TransformHelper.toContentValuesByLoginSuccessBean(loginSuccessBean);
        if (values != null) {
            SQLBriteProvider.getInstance(UIUtils.getContext()).getBriteDatabase().update(Data.TABLE_USER_INFO, values, null);
        }
    }

//    public static LoginSuccessBean queryCurLoginSuccessInfo() {
//        QueryObservable userObservable = SQLBriteProvider.getInstance(UIUtils.getContext()).getBriteDatabase().createQuery(Data.TABLE_USER_INFO, "SELECT *FROM " + Data.TABLE_USER_INFO);
//        Subscriber<SqlBrite.Query> querySubscriber = new Subscriber<SqlBrite.Query>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(SqlBrite.Query query) {
//                Cursor cursor = query.run();
//                LoginSuccessBean loginSuccessBean = TransformHelper.getLoginSuccessBean(cursor);
//            }
//        };
//
//        userObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<SqlBrite.Query>() {
//            @Override
//            public void call(SqlBrite.Query query) {
//                Cursor cursor = query.run();
//            }
//        });
//
//        userObservable.subscribe(querySubscriber);
//
//        Observable<String> myObservable = Observable.just("1","2","3");
//
//
//        userObservable.subscribe(new Action1<SqlBrite.Query>() {
//            @Override
//            public void call(SqlBrite.Query query) {
//                Cursor cursor = query.run();
//                LoginSuccessBean loginSuccessBean = TransformHelper.getLoginSuccessBean(cursor);
//            }
//        });
//    }
}
