package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyTag;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyFamilyBean;

/**
 * Created by dgg1 on 2017/11/16.
 */

public interface SearchAddFamilyContract extends BaseView {
    /**
     * 显示家庭的标签
     *
     * @param familyTag
     */
    void showFamilyTag(NewFamilyTag familyTag);

    /**
     * 显示附近的家庭列表
     *
     * @param newNearbyFamilyBean
     */
    void showNearbyFamilyList(NewNearbyFamilyBean newNearbyFamilyBean);
}
