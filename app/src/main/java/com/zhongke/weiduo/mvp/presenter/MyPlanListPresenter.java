package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MyPlanListContract;
import com.zhongke.weiduo.mvp.model.entity.MyPlanBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/9/27.
 */

public class MyPlanListPresenter extends BasePresenter {
    private MyPlanListContract mContract;

    public MyPlanListPresenter(MyPlanListContract contract) {
        this.mContract = contract;
    }

    /**
     * 获取我的计划列表
     *
     * @param pageIndex
     * @param pageSize
     */
    public void getMyPlanList(int pageIndex, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);

        Subscription subscription = retrofitClient.getMyPlanList(params, new ResponseResultListener<MyPlanBean>() {
            @Override
            public void success(MyPlanBean bean) {
                mContract.getMyPlanListSuccess(bean);
            }

            @Override
            public void failure(CommonException e) {
                mContract.getMyPlanListFailed(e);
            }
        });
        compositeSubscription.add(subscription);

    }

    /**
     * 删除一条我的计划
     *
     * @param planId
     */
    public void deleteMyPlan(int planId) {
        Map<String, Object> params = new HashMap<>();
        params.put("planId", planId);

        Subscription subscription = retrofitClient.deleteMyPlan(params, new ResponseResultListener() {
            @Override
            public void success(Object object) {
                mContract.deleteMyPlanSuccess();
            }

            @Override
            public void failure(CommonException e) {
                mContract.deleteMyPlanFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

}
