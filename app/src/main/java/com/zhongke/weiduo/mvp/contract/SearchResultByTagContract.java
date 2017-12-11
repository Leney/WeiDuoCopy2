package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyList;

/**
 * Created by dgg1 on 2017/11/17.
 */

public interface SearchResultByTagContract extends BaseView {
    /**
     * 显示通过标签获取家庭列表
     *
     * @param newFamilyList
     */
    void showFamilyListByTag(NewFamilyList newFamilyList);

    /**
     *  显示错误视图
     */
    void showError();
}
