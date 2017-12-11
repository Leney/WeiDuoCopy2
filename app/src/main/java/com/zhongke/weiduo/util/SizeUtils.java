package com.zhongke.weiduo.util;

import android.content.Context;

/**
 * Created by ${tanlei} on 2017/6/27.
 * 根据手机的密度值不同转化px和dp值
 */

public class SizeUtils {

    public static int dp2px(Context context, float dpValue) {
        //获取比例
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
