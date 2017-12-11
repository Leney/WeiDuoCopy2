package com.zhongke.weiduo.mvp.presenter;

import android.util.Log;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.model.entity.NewOrganizationCourseBean;
import com.zhongke.weiduo.mvp.model.entity.NewOrganizationDetailBean;
import com.zhongke.weiduo.mvp.ui.activity.OrganizationDataActivity;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by Karma on 2017/9/4.
 */

public class OrganizationDataAtPresent extends BasePresenter {

    private OrganizationDataActivity activity;

    public OrganizationDataAtPresent(BaseMvpActivity activity) {
        this.activity = (OrganizationDataActivity) activity;
    }

    /**
     * 请求机构数据
     */
    public void showOrganizationDetail(int orgId) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("orgId", orgId + "");
        Subscription subscription = retrofitClient.getMechanismDetail(hashMap, new ResponseResultListener<NewOrganizationDetailBean>() {
            @Override
            public void success(NewOrganizationDetailBean newOrganizationDetailBean) {
                Log.e("lee", newOrganizationDetailBean.toString());
                activity.setData(newOrganizationDetailBean);
            }

            @Override
            public void failure(CommonException e) {
                Log.e("lee", e.getErrorMsg() + e.toString());
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 请求机构课程列表
     */
    public void showOrganizationCourse(int orgId) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("orgId", orgId + "");
        Subscription subscription = retrofitClient.getOrganizationCourse(hashMap, new ResponseResultListener<NewOrganizationCourseBean>() {
            @Override
            public void success(NewOrganizationCourseBean newOrganizationCourseBean) {
                activity.showCourse(newOrganizationCourseBean.getRecords());
            }

            @Override
            public void failure(CommonException e) {
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 请求收藏机构接口
     */
    public void collectionOrganization(int collectObjectId) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("collectType", "3", "collectObjectId", collectObjectId + "");
        Subscription subscription = retrofitClient.Collection(hashMap, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                activity.setCollection();
            }

            @Override
            public void failure(CommonException e) {
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 请求取消收藏机构接口
     */
    public void cancelCollectionOrganization(int collectObjectId) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("collectType", "3", "collectObjectId", collectObjectId + "");
        Subscription subscription = retrofitClient.cancelCollection(hashMap, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                activity.setCollection();
            }

            @Override
            public void failure(CommonException e) {

            }
        });
        compositeSubscription.add(subscription);
    }
}
