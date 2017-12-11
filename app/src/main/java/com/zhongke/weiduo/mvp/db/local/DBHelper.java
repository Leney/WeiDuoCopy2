package com.zhongke.weiduo.mvp.db.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhongke.weiduo.library.sqlbrite.PhotoDb;


/**
 * Created by ${xingen} on 2017/7/7.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance;

    private DBHelper(Context context) {
        super(context, Data.SQLITE_NAME, null, Data.SQLITE_VERSON);
    }

    public static DBHelper getInstance(Context context) {
//        if (instance == null) {
//            synchronized (DBHelper.class) {
//                if (instance == null) {
//                    instance = new DBHelper(context);
//                }
//            }
//        }
        return new DBHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建用户信息表
        sqLiteDatabase.execSQL(SqlHelper.CREATE_TABLE_USER_INFO);
        // 创建用户所属家庭表
        sqLiteDatabase.execSQL(SqlHelper.CREATE_TABLE_USER_FAMILY_INFO);

        sqLiteDatabase.execSQL(SessionDb.SessionTable.CREATE);

        sqLiteDatabase.execSQL(PhotoDb.PhotoTable.CREATE);

        // 创建最近聊天列表的数据库表
        sqLiteDatabase.execSQL(SqlHelper.CREATE_TABLE_RECENT_CHAT_LIST);
        // 创建联系人信息表
        sqLiteDatabase.execSQL(SqlHelper.CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }


}
