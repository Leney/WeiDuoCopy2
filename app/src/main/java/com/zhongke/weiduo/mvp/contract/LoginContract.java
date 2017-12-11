package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;

/**
 * 登录
 * Created by llj on 2017/6/20.
 */

public interface LoginContract extends BaseView {
    /** 登录成功*/
    void loginSuccess(LoginResult loginResult);
    /** 登录失败*/
    void loginFailed();
}
