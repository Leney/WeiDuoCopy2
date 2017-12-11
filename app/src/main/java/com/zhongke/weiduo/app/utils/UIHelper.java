package com.zhongke.weiduo.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.zhongke.weiduo.R;


/**
 * Created by Karma on 2017/6/8.
 * 类描述：应用程序UI工具包：封装UI相关的一些操作
 */
public class UIHelper {

    public final static String TAG = "UIHelper";

    public final static int RESULT_OK = 0x00;
    public final static int REQUEST_CODE = 0x01;
    public final static int REQUEST_LOGIN_CODE = 0x02;
    public final static int REQUEST_REGISTER_CODE = 0x03;


    public static void ToastMessage(Context cont, String msg) {
        if (cont == null || msg == null) {
            return;
        }
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, int msg) {
        if (cont == null || msg <= 0) {
            return;
        }
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, String msg, int time) {
        if (cont == null || msg == null) {
            return;
        }
        Toast.makeText(cont, msg, time).show();
    }

    public static void showTel(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
        context.startActivity(intent);
    }

    public static void showMSN(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
        context.startActivity(intent);
    }


    public static void startActivity(Activity context, Class cls) {
        Intent intent = new Intent(context, cls);

        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in,
                R.anim.push_left_out);
    }

    public static void startActivity(Activity activity, Class cls, Bundle b) {
        Intent intent = new Intent(activity, cls);
        intent.putExtras(b);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_left_in,
                R.anim.push_left_out);

    }

    /**
     * 关闭 Activity
     *
     * @param activity
     */
    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }
}
