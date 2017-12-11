package com.zhongke.weiduo.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.zhongke.weiduo.app.ZkApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * Created by Karma on 2017/6/16.
 */

public class Tools {

    /**
     * 设置状态栏颜色 * * @param activity 需要设置的activity * @param color 状态栏颜色值
     */
    public static void setStatusColor(Activity activity, int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

//        // 生成一个状态栏大小的矩形
//        View statusView = createStatusView(activity, color);
//        // 添加 statusView 到布局中
//        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
//        decorView.addView(statusView);
//        // 设置根布局的参数
//        ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
//        rootView.setFitsSystemWindows(true);
//        rootView.setClipToPadding(true);
    }

    /**
     * 生成一个和状态栏大小相同的矩形条 * * @param activity 需要设置的activity * @param color 状态栏颜色值 * @return 状态栏矩形条
     */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

    /**
     * 算出最多存在多少行数据
     *
     * @param a
     * @param b
     */
    public static int math(int a, int b) {
        int c;
        if (a % b == 0) {
            c = a / b;
            System.out.print("a/b=" + c);
        } else {
            c = a / b + 1;
            System.out.print("a/b=" + c);
        }
        return c;
    }


    /**
     * 改变文本其中某部分的字体颜色
     *
     * @param all
     * @param change
     * @param color  如：#545646
     * @return
     */
    public static SpannableStringBuilder setOneOfTextColor(String all, String change, String color) {
        SpannableStringBuilder style = new SpannableStringBuilder(all);
        int fstart = all.indexOf(change);
        int fend = fstart + change.length();
        style.setSpan(new ForegroundColorSpan(Color.parseColor(color)), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style;
    }


    /**
     * 将秒的数值转换成时间格式
     *
     * @param count 秒的数值
     * @return
     */
    public static String formatTime(int count) {
        if(count >= 3600) return "超过范围了";
        StringBuilder builder = new StringBuilder();
        if (count < 60) {
            if (count < 10) {
                builder.append("00:0").append(count);
            } else {
                builder.append("00:").append(count);
            }
        } else {
            // 分钟
            int minu = (int) Math.floor(count / 60);
            // 秒
            int second = count % 60;
            if (minu < 10) {
                builder.append("0").append(minu).append(":");
            } else {
                builder.append(minu).append(":");
            }

            if(second < 10){
                builder.append("0").append(second);
            }else {
                builder.append(second);
            }
        }
        return builder.toString();
    }

    /**
     * 随机获取指定范围内的值
     * @param min
     * @param max
     * @return
     */
    public static int getRandomValue(int min,int max){
        if(min >= max) return -1;
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }


    /**
     * 通过数据流保存数据到缓存文件
     *
     * @param fileName
     * @param object
     * @return
     */
    public static boolean saveCacheDataToFile(String fileName, Object object) {
        if (object == null) {
            return false;
        }
        try {
            // 需要一个文件输出流和对象输出流；文件输出流用于将字节输出到文件，对象输出流用于将对象输出为字节
            FileOutputStream fout = ZkApplication.getContext().openFileOutput(fileName + ".ser",
                    Activity.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(object);
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 从缓存文件里面获取缓存数据
     *
     * @param fileName
     * @return
     */
    public static Object getCacheDataFromFile(String fileName) {
        Object object = null;
        try {
            FileInputStream fin = ZkApplication.getContext().openFileInput(fileName + ".ser");
            ObjectInputStream in = new ObjectInputStream(fin);
            object = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 清除指定文件名的缓存
     *
     * @param fileName
     */
    public static void clearCacheFile(String fileName) {
        File file = new File(ZkApplication.getContext().getFilesDir(), fileName + ".ser");
        if (file.exists()) {
            file.delete();
        }
    }


    /**
     * 获取当前进程的名称
     * @param context
     * @return
     */
    String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

}
