package com.zhongke.weiduo.mvp.contract;

import android.graphics.Bitmap;

/**
 * Created by ${xingen} on 2017/12/5.
 */

public interface  DeviceCodeContract {
    void showCodeBitmap(Bitmap bitmap);
    String getDeviceCode();
}
