package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SchemeDetailContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.RecommendPlanBean;
import com.zhongke.weiduo.mvp.model.entity.SchemeDetailBean2;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * 登录
 * Created by llj on 2017/6/20.
 */

public class SchemeDetailPresenter extends BasePresenter {
    private static final String TAG = "SchemeDetailPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private SchemeDetailContract mContract;

    public SchemeDetailPresenter(Context context, DataManager dataManager, SchemeDetailContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 获取规划详情数据
     */
    public void getDetailInfo(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", id);

        Subscription subscription = retrofitClient.getSchemeDetail(params, new ResponseResultListener<SchemeDetailBean2>() {
            @Override
            public void success(SchemeDetailBean2 schemeDetailBean2) {
                mContract.getDetailSuccess(schemeDetailBean2);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("llj", "errorCode---->>>" + e.getCode() + "---errorMsg------>>>" + e.getErrorMsg());
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

        Subscription subscription = retrofitClient.getSchemeDetailRecommendList(params, new ResponseResultListener<RecommendPlanBean>() {
            @Override
            public void success(RecommendPlanBean recommendPlanBean) {
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
     * 添加规划
     *
     * @param bookId
     */
    public void addScheme(int bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("bookType", 2); // 2=规划，3=计划


        Subscription subscription = retrofitClient.addSchemeOrPlan(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                mContract.addSchemeSuccess();
            }

            @Override
            public void failure(CommonException e) {
                mContract.addSchemeFailed(e);
            }
        });

        compositeSubscription.add(subscription);
    }


}
