package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.model.entity.NewNearbyUser;
import com.zhongke.weiduo.mvp.model.entity.NewRecommendUser;

/**
 * Created by llj on 2017/11/16.
 */

public interface SearchAddFriendContract {
    /**
     * 显示附近的用户
     */
    void showNearbyUserList(NewNearbyUser newNearbyUser);

    /**
     * 显示错误视图
     */
    void showErrorViews();

    /**
     * 显示推荐的好友
     */
    void showRecommendUser(NewRecommendUser newRecommendUser);
}
