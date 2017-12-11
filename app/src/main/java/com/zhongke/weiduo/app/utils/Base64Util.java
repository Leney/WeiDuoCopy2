package com.zhongke.weiduo.app.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by karma on 2017/07/06
 */

public class Base64Util {

    /**
     * bitmap转成base64
     *
     * @param bitmap bitmap
     * @return base64
     */
    public static  String bitmapToBase64(Bitmap bitmap) {
        // 将Bitmap转换成字符串
        String base64;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        base64 = Base64.encodeToString(bytes, Base64.DEFAULT);
        return base64;
    }

    /**
     * base64转成bitmap
     *
     * @param base64 base64
     * @return bitmap
     */
    public static Bitmap base64ToBitmap(String base64) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(base64, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}