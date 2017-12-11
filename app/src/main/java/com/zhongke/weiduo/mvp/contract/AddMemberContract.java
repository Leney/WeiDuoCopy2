package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;

/**
 * 添加亲友
 * Created by llj on 2017/6/21.
 */

public interface AddMemberContract extends BaseView {
    /** 添加亲友成功*/
    void addMemberSuccess();
    /** 添加亲友失败*/
    void addMemberFailed(int errorCode,String msg);
}
