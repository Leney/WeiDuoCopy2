package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AddAimsPlanningContract;
import com.zhongke.weiduo.mvp.model.entity.ExecutePeopleBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/9/27.
 */

public class AddAimsPlanningPresenter extends BasePresenter {
    private AddAimsPlanningContract mContract;

    public AddAimsPlanningPresenter(AddAimsPlanningContract contract) {
        this.mContract = contract;
    }

    /**
     * 添加自定义目标
     * @param name
     * @param infos
     */
    public void addSelfTarget(String name,String infos,int userId){
        Map<String, Object> params = new HashMap<>();
//        params.put("token", token);
        params.put("targetName", name);
        params.put("targetInfo", infos);
        params.put("userId",userId);

        retrofitClient.addSelfTarget(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                mContract.addSelfTargetSuccess();
            }

            @Override
            public void failure(CommonException e) {
                mContract.addSelfTargetFailed(e);
            }
        });
    }

    /**
     * 添加自定义规划
     * @param name
     * @param infos
     */
    public void addSelfScheme(String name,String infos,int userId){
        Map<String, Object> params = new HashMap<>();
//        params.put("token", token);
        params.put("name", name);
        params.put("info", infos);
        params.put("userId", userId);

        retrofitClient.addSelfScheme(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                mContract.addSelfSchemeSuccess();
            }

            @Override
            public void failure(CommonException e) {
                mContract.addSelfSchemeFailed(e);
            }
        });
    }

    /**
     * 添加自定义计划
     * @param name
     * @param infos
     */
    public void addSelfPlan(String name,String infos,int userId){
        Map<String, Object> params = new HashMap<>();
//        params.put("token", token);
        params.put("name", name);
        params.put("info", infos);
        params.put("userId", userId);

        retrofitClient.addSelfPlan(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                mContract.addSelfPlanSuccess();
            }

            @Override
            public void failure(CommonException e) {
                mContract.addSelfPlanFailed(e);
            }
        });
    }

    //获取用户和用户孩子列表
    public void getUserAndChild() {
        Subscription subscription = retrofitClient.getUserAndChild(new ResponseResultListener<ExecutePeopleBean>() {
            @Override
            public void success(ExecutePeopleBean executePeopleBean) {
                LogUtil.e("success---");
                mContract.getUserAndChildSuccess(executePeopleBean);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure---");
                mContract.getUserAndChildFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }
}
