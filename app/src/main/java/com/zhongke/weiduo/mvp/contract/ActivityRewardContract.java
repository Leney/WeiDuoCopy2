package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.model.entity.ActivityRewardBean;

import java.util.List;

/**
 * Created by ${xingen} on 2017/7/1.
 */

public interface ActivityRewardContract {

    void loadRewardList(List<ActivityRewardBean.AwardListBean> awardList);
    void loadScoreList(List<ActivityRewardBean.TargetListBean> targetList);
}
