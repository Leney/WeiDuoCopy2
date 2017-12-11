package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AssessmentContart;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.AssessmentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/26.
 */

public class AssessmentPresenter extends BasePresenter {
    private static final String TAG = "AssessmentPresenter";
    private AssessmentContart assessmentContart;
    private DataManager dataManager;

    public AssessmentPresenter(AssessmentContart assessmentContart, DataManager dataManager) {
        this.assessmentContart = assessmentContart;
        this.dataManager = dataManager;
    }

    public void showAssessmentData() {
        List<AssessmentBean> assessmentBeanList = new ArrayList<>();
        AssessmentBean ass1 = new AssessmentBean();
        ass1.setAssessmentName("评分方式");
        AssessmentBean ass2 = new AssessmentBean();
        ass2.setAssessmentName("智力检查");
        AssessmentBean ass3 = new AssessmentBean();
        ass3.setAssessmentName("综合考核");
        AssessmentBean ass5 = new AssessmentBean();
        ass5.setAssessmentName("体力考核");
        assessmentBeanList.add(ass1);
        assessmentBeanList.add(ass2);
        assessmentBeanList.add(ass3);
        assessmentBeanList.add(ass5);
        assessmentContart.showProgressDialog();
        assessmentContart.showData(assessmentBeanList);
    }
}
