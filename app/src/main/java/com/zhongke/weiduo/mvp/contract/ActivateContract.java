package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;

/**
 * 激活设备
 * Created by llj on 2017/6/20.
 */

public interface ActivateContract extends BaseView {
    /** 获取相关数据成功*/
    void getInfoSuccess();
    /** 获取相关数据失败*/
    void getInfoFailed(int errorCode,String msg);
    /** 激活设备成功*/
    void activateSuccess();
    /** 激活设备失败*/
    void activateFailed(int errorCode,String msg);
}
