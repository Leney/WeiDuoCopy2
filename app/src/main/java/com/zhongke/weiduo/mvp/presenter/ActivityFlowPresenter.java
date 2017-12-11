package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActivityFlowContract;

/**
 * Created by hyx on 2017/12/7.
 */

public class ActivityFlowPresenter extends BasePresenter {

    private Context context;
    private ActivityFlowContract contract;
    public ActivityFlowPresenter(Context context,ActivityFlowContract contract) {
        this.context = context;
        this.contract = contract;
    }

    public void getActionFlowView () {

    }
}
