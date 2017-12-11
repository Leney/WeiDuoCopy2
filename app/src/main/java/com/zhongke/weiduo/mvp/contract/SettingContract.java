package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;

/**
 * Created by ${tanlei} on 2017/9/26.
 */

public interface SettingContract extends BaseView{

    //更改密码成功
    void changePasswordSuccess();

    void changePasswordFailed(CommonException e);
}
