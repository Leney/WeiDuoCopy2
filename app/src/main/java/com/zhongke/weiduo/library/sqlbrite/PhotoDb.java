package com.zhongke.weiduo.library.sqlbrite;

import android.content.ContentValues;
import android.database.Cursor;

import com.zhongke.weiduo.mvp.model.entity.PhotoOrVideo;

import rx.functions.Func1;

/**
 * Created by Karma on 2017/8/16.
 * 类描述：图片数据库
 */

public class PhotoDb {
    public PhotoDb() {
    }

    public static abstract class PhotoTable {
        // 表名
        public static final String TABLE_NAME = "weiduo_session_photo";

        // 表字段
        public static final String ID = "_id";
        public static final String URL = "url";


        // 建表语句
        public static final String CREATE =
                "CREATE TABLE "
                        + TABLE_NAME
                        + " ("
                        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + URL + " TEXT)";


        // 对象转字段,放入表中
        public static ContentValues toContentValues(PhotoOrVideo photoOrVideo) {
            ContentValues values = new ContentValues();
            values.put(URL,photoOrVideo.getPhotoUrl());
            return values;
        }

        // 响应式的查询,根据表中的row生成一个对象
        public static Func1<Cursor, PhotoOrVideo> PHOTO_VIDEO = cursor -> {
            PhotoOrVideo photoOrVideo = new PhotoOrVideo();

            String url = cursor.getString(cursor.getColumnIndexOrThrow(URL));
            photoOrVideo.setPhotoUrl(url);
            return photoOrVideo;
        };

    }
}
