package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;

/**
 * Created by ${tanlei} on 2017/6/22.
 */

public interface AddAssessmentContract extends BaseView {
    /**
     * 获取用户输入的考核要素
     *
     * @return
     */
    String getAddAssessment();

    /**
     * 显示ProgressDialog
     */
    void showProgressDialog();

    /**
     * 影藏ProgressDialog
     */
    void hideProgressDialog();
}
