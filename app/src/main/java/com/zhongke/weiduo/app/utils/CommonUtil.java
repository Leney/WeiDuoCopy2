package com.zhongke.weiduo.app.utils;

import android.content.Context;
import android.os.Looper;
import android.telephony.TelephonyManager;

/**
 * Created by Karma on 2017/6/8.
 * 类描述：
 */

public class CommonUtil {
    public static boolean isOnMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
    public static String getIMEI(Context context) {
        try {
            if (context == null) {
                return "";
            }
            TelephonyManager telephonyManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String imei = telephonyManager.getDeviceId();

            if (imei != null && !imei.equals("")) {
                return imei;
            }
        } catch (Exception exception1) {
        }

        return "";
    }
}
