package com.zhongke.weiduo.library.sqlbrite;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.zhongke.weiduo.mvp.db.local.DBHelper;

import rx.schedulers.Schedulers;

/**
 * Created by ${xingen} on 2017/7/7.
 * SQLBrite的操作类
 */

public class SQLBriteProvider {

    private SqlBrite sqlBrite;
    private BriteDatabase briteDatabase;
    private static SQLBriteProvider instance;

    private SQLBriteProvider(Context context) {
        this.sqlBrite = providerSqlBrite();
        this.briteDatabase = createDatabase(this.sqlBrite, providerOpenHelper(context));
    }

    public static synchronized SQLBriteProvider getInstance(Context context) {
        if (instance == null) {
            instance = new SQLBriteProvider(context);
        }
        return instance;
    }

    /**
     * 创建SQLiteOpenHelper对象
     *
     * @param context
     * @return
     */
    private SQLiteOpenHelper providerOpenHelper(Context context) {
        return DBHelper.getInstance(context);
    }

    /**
     * 创建SqlBrite对象
     *
     * @return
     */
    private SqlBrite providerSqlBrite() {
        return new SqlBrite.Builder().build();
    }

    /**
     * 通过SQLBrite对象和SQLiteOpenHel对象
     *
     * @param sqlBrite
     * @param sqLiteOpenHelper
     * @return
     */
    public BriteDatabase createDatabase(SqlBrite sqlBrite, SQLiteOpenHelper sqLiteOpenHelper) {
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(sqLiteOpenHelper, Schedulers.io());
        return db;
    }

    /**
     * 获取到SQLBrite中数据库对象
     *
     * @return
     */
    public BriteDatabase getBriteDatabase() {
        return briteDatabase;
    }
}