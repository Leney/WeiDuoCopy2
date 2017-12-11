package com.zhongke.weiduo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Environment;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ${xingen} on 2017/6/22.
 */

public class BitmapUtils {

    /**
     * 绘画一个圆形的bitmap
     * 思路：
     * 1.创建一个以bitmap作为画布
     * 2.通过画笔画一个圆形
     * 3.通过交集方(SRC_IN)式画一个bitmap
     *
     * @param bitmap
     * @return
     */
    public static Bitmap drawnRoundBitmap(Bitmap bitmap){
        //设置正方形的边长：
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //正方形的边长
        int r=0;
        r=width>height?width:height;
        Bitmap roundbitmap=null;
        try{
            if(bitmap!=null){
                roundbitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
                //bitmap作为画布
                Canvas canvas=new Canvas(roundbitmap);
                //创建画笔
                Paint paint=new Paint();
                //抗锯齿
                paint.setAntiAlias(true);
                paint.setColor(Color.WHITE);

                RectF rectF=new RectF(0,0,r,r);
                // 通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时,且都等于r/2时，画出来的圆角矩形就是圆形
                canvas.drawRoundRect(rectF,r/2,r/2,paint);

                //设置两种图片相交模式：SRC_IN为取SRC图形相交的部分，多余的将被去掉
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN ));
                //绘制bitmap
                canvas.drawBitmap(bitmap,null,rectF,paint);

                //再画圆形边框
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(r/2,r/2,r/2,paint);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(bitmap!=null){
                bitmap.recycle();
            }

        }
        return roundbitmap;
    }

    /**
     * 获得存储bitmap的文件
     * getExternalFilesDir()提供的是私有的目录，在app卸载后会被删除
     *
     * @param context
     * @param
     * @return
     */
    public static File getBitmapDiskFile(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {

            cachePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!=null ? context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath():context.getFilesDir().getPath();
        } else {
            cachePath = context.getFilesDir().getPath();
        }
        return new File(cachePath + File.separator + getBitmapFileName());
    }

    /**
     * 获得存储bitmap的文件
     * getExternalFilesDir()提供的是私有的目录，在app卸载后会被删除
     *
     * @param context
     * @param
     * @return
     */
    public static File getDiskFile(Context context,String fileName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!=null ? context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath():context.getFilesDir().getPath();
        } else {
            cachePath = context.getFilesDir().getPath();
        }
        return new File(cachePath + File.separator +fileName);
    }

    public static final String bitmapFormat = ".png";

    /**
     * 生成bitmap的文件名:日期，md5加密
     *
     * @return
     */
    public static String getBitmapFileName() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            String currentDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            mDigest.update(currentDate.getBytes("utf-8"));
            byte[] b = mDigest.digest();
            for (int i = 0; i < b.length; ++i) {
                String hex = Integer.toHexString(0xFF & b[i]);
                if (hex.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(hex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName = stringBuilder.toString() + bitmapFormat;
        return fileName;
    }

}
