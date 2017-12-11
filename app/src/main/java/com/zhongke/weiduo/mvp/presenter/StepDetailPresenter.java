package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.StepDetailContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.StepDetailBean;

import java.util.ArrayList;

/**
 * 步骤详情
 * Created by llj on 2017/6/26.
 */

public class StepDetailPresenter extends BasePresenter {
    private static final String TAG = "StepDetailPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private StepDetailContract mContract;

    public StepDetailPresenter(Context context,DataManager dataManager,StepDetailContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }


    /**
     * 获取步骤详情数据
     */
    public void getStepDetails(){


        StepDetailBean stepDetailBean = new StepDetailBean();
        stepDetailBean.setNotes("1.因名额有限，若决定参与，请准时到达。欢迎所有喜欢运动的小伙伴参加\n2.安全：不单独行动，不做危险动作");
        stepDetailBean.setStepContent("活动步骤：首先进行早餐活动，点名...");

        stepDetailBean.setTeachList(new ArrayList<StepDetailBean.TeachListBean>());
        StepDetailBean.TeachListBean teachListBean1 = new StepDetailBean.TeachListBean();
        teachListBean1.setName("投影播放");
        teachListBean1.setValue("基本语法讲解视屏");
        StepDetailBean.TeachListBean teachListBean2 = new StepDetailBean.TeachListBean();
        teachListBean2.setName("主屏播放");
        teachListBean2.setValue("老师讲解视屏");
        stepDetailBean.getTeachList().add(teachListBean1);
        stepDetailBean.getTeachList().add(teachListBean2);


        stepDetailBean.setTipList(new ArrayList<StepDetailBean.TipListBean>());
        StepDetailBean.TipListBean tipListBean1 = new StepDetailBean.TipListBean();
        tipListBean1.setName("视屏检测");
        StepDetailBean.TipListBean tipListBean2 = new StepDetailBean.TipListBean();
        tipListBean2.setName("当所有人员到位后提示老师教学");
        stepDetailBean.getTipList().add(tipListBean1);
        stepDetailBean.getTipList().add(tipListBean2);

        stepDetailBean.setRecordList(new ArrayList<StepDetailBean.RecordListBean>());
        StepDetailBean.RecordListBean recordListBean1 = new StepDetailBean.RecordListBean();
        recordListBean1.setName("侧拍摄像头设置");
        recordListBean1.setValue(1);
        StepDetailBean.RecordListBean recordListBean2 = new StepDetailBean.RecordListBean();
        recordListBean2.setName("高拍摄像头设置");
        recordListBean2.setValue(0);
        StepDetailBean.RecordListBean recordListBean3 = new StepDetailBean.RecordListBean();
        recordListBean3.setName("麦克风设置");
        recordListBean3.setValue(1);
        stepDetailBean.getRecordList().add(recordListBean1);
        stepDetailBean.getRecordList().add(recordListBean2);
        stepDetailBean.getRecordList().add(recordListBean3);

        stepDetailBean.setAssessList(new ArrayList<StepDetailBean.AssessListBean>());
        StepDetailBean.AssessListBean assessListBean1 = new StepDetailBean.AssessListBean();
        assessListBean1.setName("讲文明有礼貌");
        assessListBean1.setValue("五星制");
        StepDetailBean.AssessListBean assessListBean2 = new StepDetailBean.AssessListBean();
        assessListBean2.setName("专心听讲");
        assessListBean2.setValue("五星制");
        StepDetailBean.AssessListBean assessListBean3 = new StepDetailBean.AssessListBean();
        assessListBean3.setName("笔记完整度");
        assessListBean3.setValue("五星制");
        stepDetailBean.getAssessList().add(assessListBean1);
        stepDetailBean.getAssessList().add(assessListBean2);
        stepDetailBean.getAssessList().add(assessListBean3);

        stepDetailBean.setResultList(new ArrayList<StepDetailBean.ResultListBean>());
        StepDetailBean.ResultListBean resultListBean1 = new StepDetailBean.ResultListBean();
        resultListBean1.setName("综合评分3星以上");
        resultListBean1.setValue("奖励");
        StepDetailBean.ResultListBean resultListBean2 = new StepDetailBean.ResultListBean();
        resultListBean2.setName("综合评分3星以下");
        resultListBean2.setValue("请再试一次");
        stepDetailBean.getResultList().add(resultListBean1);
        stepDetailBean.getResultList().add(resultListBean2);


        mContract.getStepDetailSuccess(stepDetailBean);
    }
}
