package com.zhongke.weiduo.mvp.presenter;

import android.util.Log;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ExpertDetailsContract;
import com.zhongke.weiduo.mvp.model.entity.NewExpertCourseBean;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.NewExpertDetailBean;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/9/6.
 */

public class ExpertDetailsPresenter extends BasePresenter {
    private ExpertDetailsContract expertDetailsContract;
    private DataManager dataManager;

    public ExpertDetailsPresenter(ExpertDetailsContract expertDetailsContract, DataManager dataManager) {
        this.expertDetailsContract = expertDetailsContract;
        this.dataManager = dataManager;
    }

    /**
     * 获取专家详情
     */
    public void getExpertDetail(int userId) {
        HashMap map = RequestParameterUtils.setRequestPar("userId", userId + "");
        Subscription subscription = retrofitClient.getExperDetail(map, new ResponseResultListener<NewExpertDetailBean>() {
            @Override
            public void success(NewExpertDetailBean newExpertDetailBean) {
                expertDetailsContract.getExpertDetail(newExpertDetailBean);
            }

            @Override
            public void failure(CommonException e) {
                expertDetailsContract.requestFailure();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 收藏专家
     */
    public void requestCollectionExpert(int collectObjectId) {
        HashMap map = RequestParameterUtils.setRequestPar("collectType", "1", "collectObjectId", collectObjectId + "");
        Subscription subscription = retrofitClient.Collection(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                expertDetailsContract.collectionExpert();
            }

            @Override
            public void failure(CommonException e) {

            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 取消收藏专家
     */
    public void cancelCollectionExpert(int collectObjectId) {
        HashMap map = RequestParameterUtils.setRequestPar("collectType", "1", "collectObjectId", String.valueOf(collectObjectId) );
        Subscription subscription = retrofitClient.cancelCollection(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                expertDetailsContract.cancelCollectionExpert();
            }

            @Override
            public void failure(CommonException e) {
                Log.e("lee","取消收藏失败");
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 获取专家的课程
     */
    public void getExpertCourse(int userId) {
        HashMap map = RequestParameterUtils.setRequestPar("userId", userId+"");
        Subscription subscription = retrofitClient.getExpertCourse(map, new ResponseResultListener<NewExpertCourseBean>() {
            @Override
            public void success(NewExpertCourseBean newExpertCourseBean) {
                expertDetailsContract.showExpertCourse(newExpertCourseBean);
            }

            @Override
            public void failure(CommonException e) {

            }
        });
        compositeSubscription.add(subscription);
    }
}
