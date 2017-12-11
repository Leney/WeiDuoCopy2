package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;

/**
 * 注册
 * Created by llj on 2017/6/17.
 */

public interface RegisterContract extends BaseView {
    /**
     * 注册成功
     */
    void registerSuccess();

    /**
     * 注册失败
     */
    void registerFailed(int errorCode,String msg);
}
