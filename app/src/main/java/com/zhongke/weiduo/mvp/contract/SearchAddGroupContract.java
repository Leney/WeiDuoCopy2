package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyGroup;

/**
 * Created by dgg1 on 2017/11/16.
 */

public interface SearchAddGroupContract extends BaseView {
    /**
     * 显示附近的群组列表
     */
    void showNearbyGroup(NewNearbyGroup newNearbyGroup);

    /**
     * 显示失败视图
     */
    void showError();
}
