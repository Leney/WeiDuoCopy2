package com.zhongke.weiduo.mvp.contract;

import android.app.Activity;

/**
 * Created by ${xingen} on 2017/12/5.
 */

public interface DeviceBindContract  {
      Activity getActivityContext();
    void  showSelectRole(String name,int values);
}
