package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.AssessmentBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/26.
 */

public interface AssessmentContart extends BaseView {
    /**
     * 显示ProgressDialog
     */
    void showProgressDialog();

    /**
     * 影藏ProgressDialog
     */
    void hideProgressDialog();

    /**
     * 显示数据
     */
    void showData(List<AssessmentBean> assessmentBeans);

    /**
     * 删除数据
     */
    void deleteData(List<AssessmentBean> assessmentBeans);
}
