package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AddAssessmentContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;

/**
 * Created by ${tanlei} on 2017/6/22.
 */

public class AddAssessmentPresenter extends BasePresenter {
    private static final String TAG = "AddAssessmentPresenter";
    private DataManager mDataManager;
    private AddAssessmentContract mContract;

    public AddAssessmentPresenter(DataManager dataManager, AddAssessmentContract contract) {
        this.mDataManager = dataManager;
        this.mContract = contract;
    }
    public void AddAssessmentData(){
        mContract.getAddAssessment();
        //这里需要封装成一个对象，传递给后台。
    }
}
