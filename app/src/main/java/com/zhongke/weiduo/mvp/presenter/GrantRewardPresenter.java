package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.GrantRewardContract;
import com.zhongke.weiduo.mvp.model.entity.ActivityAwardBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by hyx on 2017/10/25.
 */

public class GrantRewardPresenter extends BasePresenter {

    private Context context;
    private GrantRewardContract contract;
    public GrantRewardPresenter(Context context, GrantRewardContract contract) {
        this.context = context;
        this.contract = contract;
    }

    public void getAwardList(String actionId,String flowId) {

        Map<String,String> map = new HashMap<>();
        Subscription subscription = retrofitClient.getAwardList(map, new ResponseResultListener<ActivityAwardBean>() {
            @Override
            public void success(ActivityAwardBean activityAwardBean) {
                LogUtil.e("success---");
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure---"+e.getErrorMsg());
            }
        });
    }


    //发放活动奖励
    public void grantActivityReward (String userId,String awards,String counts) {

        Map<String,String> map = new HashMap<>();
        Subscription subscription = retrofitClient.grantActivityAward(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                LogUtil.e("success---");
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure---"+e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }
}
