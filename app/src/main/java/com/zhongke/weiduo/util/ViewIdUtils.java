package com.zhongke.weiduo.util;

import android.os.Build;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ${xingen} on 2017/6/20.
 */

public class ViewIdUtils {
    /**
     * 随机分配一个新的Id
     *
     * @return
     */
    public static int getViewId() {
        int viewId;
        if (Build.VERSION.SDK_INT < 17) {
            //采用View中源码
            viewId = generateViewId();
        } else {
            //采用View中generateViewId()静态方法
            viewId = View.generateViewId();
        }
        return viewId;
    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Generate a value suitable for use in {@link #setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    private static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
