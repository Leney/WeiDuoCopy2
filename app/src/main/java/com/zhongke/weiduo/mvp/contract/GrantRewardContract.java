package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;

/**
 * Created by hyx on 2017/10/25.
 */

public interface GrantRewardContract extends BaseView {
    //获取活动未发放的奖品列表
    void getAwardListSuccess();

    void getAwardListFailed();

    //发放奖励成功
    void grantActivityRewardSuccess();

    void grantActivityRewardFailed();
}
