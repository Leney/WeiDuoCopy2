package com.zhongke.weiduo.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by ${xingen} on 2017/11/8.
 * Activity的工具类
 */

public class ActivityUtils {

    private static ArrayList<Activity> list=new ArrayList<>();
    public static void addActivity(Activity activity){
        if(!list.contains(activity)){
            list.add(activity);
        }

    }

    /**
     * 移除
     * @param activity
     */
    public static void removeActivity(Activity activity){
        list.remove(activity);
    }

    /**
     * 销毁全部的Activity
     */
    public static void finishAllActivity(){
        for(Activity currentActivity: list){
            if(!currentActivity.isFinishing()){
                currentActivity.finish();
            }
        }
    }

    /**
     * 判断当前是否包含一个Activity
     * @param activity
     * @return
     */
    public static boolean isActivityDestoryed(Activity activity){
        if(list.contains(activity)){
            return false;
        }else{
            return true;
        }

    }

    /**
     * 根据Activity名字销毁
     * @param activityName
     */
    public static void finishIndexActivity(String activityName){
        for(Activity currentActivity: list){
            if((currentActivity.getPackageName()+"."+currentActivity.getLocalClassName()).equals(activityName)){
                currentActivity.finish();
                break;
            }
        }
    }
    public static int getListSize() {
        return list.size();
    }

    /**
     * 开启指定的Activity
     * @param context
     * @param mClass
     */
    public static void openActivity(Context context, Class<?> mClass){
        openActivity(context,null,mClass);
    }
    public static void openActivity(Context context, Bundle bundle, Class<?> mClass){
        Intent intent=new Intent(context,mClass);
        if (! (context instanceof Activity)){
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
