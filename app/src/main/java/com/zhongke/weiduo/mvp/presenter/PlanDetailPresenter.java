package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.PlanDetailContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.PlanDetailBean2;
import com.zhongke.weiduo.mvp.model.entity.RecommendActiveBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * 计划详情
 * Created by llj on 2017/6/20.
 */

public class PlanDetailPresenter extends BasePresenter {
    private static final String TAG = "PlanDetailPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private PlanDetailContract mContract;

    public PlanDetailPresenter(Context context, DataManager dataManager, PlanDetailContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 获取相关数据
     */
    public void getDetailInfo(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", id);

        Subscription subscription = retrofitClient.getPlanDetail(params, new ResponseResultListener<PlanDetailBean2>() {
            @Override
            public void success(PlanDetailBean2 planDetailBean2) {
                mContract.getDetailSuccess(planDetailBean2);
            }

            @Override
            public void failure(CommonException e) {
                Log.e("llj", "errorCode---->>>" + e.getCode() + "---errorMsg------>>>" + e.getErrorMsg());
                mContract.getDetailFailed(e);
            }
        });

        compositeSubscription.add(subscription);

    }

    /**
     * 获取推荐计划列表
     *
     * @param bookId
     */
    public void getRecommendList(int bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);

        Subscription subscription = retrofitClient.getPlanDetailRecommendList(params, new ResponseResultListener<RecommendActiveBean>() {
            @Override
            public void success(RecommendActiveBean recommendPlanBean) {
                mContract.getRecommendListSuccess(recommendPlanBean);
            }

            @Override
            public void failure(CommonException e) {
                mContract.getRecommendListFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 添加计划
     *
     * @param bookId
     */
    public void addPlan(int bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("bookType", 3); // 2=规划，3=计划

        Subscription subscription = retrofitClient.addSchemeOrPlan(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                mContract.addPlanSuccess();
            }

            @Override
            public void failure(CommonException e) {
                mContract.addPlanFailed(e);
            }
        });

        compositeSubscription.add(subscription);
    }


}
