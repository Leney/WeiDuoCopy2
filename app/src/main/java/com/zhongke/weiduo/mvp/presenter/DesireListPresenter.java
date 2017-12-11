package com.zhongke.weiduo.mvp.presenter;

import android.util.Log;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.DesireListContract;
import com.zhongke.weiduo.mvp.model.entity.DesireListBean2;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyFamilyBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${xingen} on 2017/9/21.
 */

public class DesireListPresenter extends BasePresenter {
    private DesireListContract desireListContract;
    public DesireListPresenter(DesireListContract desireListContract){
        this.desireListContract=desireListContract;
    }

    //获取孩子愿望
    public void getChildWish(int actionId) {
        Map<String,String> map = new HashMap<>();
        map.put("actionId",actionId+"");
        Subscription subscription = retrofitClient.getChildWish(map,new ResponseResultListener<DesireListBean2>() {
            @Override
            public void success(DesireListBean2 desireListBean2) {
                LogUtil.e("success----");
                desireListContract.getChildWishSuccess(desireListBean2);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure----"+e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }

    public void getChildWishNoActionId() {
        Subscription subscription = retrofitClient.getChildWishNoActionId(new ResponseResultListener<DesireListBean2>() {
            @Override
            public void success(DesireListBean2 desireListBean2) {
                LogUtil.e("success----");
                desireListContract.getChildWishSuccess(desireListBean2);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure----"+e.getErrorMsg());
                desireListContract.getChildWishFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }


    public void helpRealizeWish(String actionId,String userIds,String wishIds,String actionName,String beginTime,String endTime) {
        Map<String,String> map = new HashMap<>();
        map.put("actionId",actionId);
        map.put("userIds",userIds);
        map.put("wishIds",wishIds);
        map.put("actionName",actionName);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);

        Subscription subscription = retrofitClient.helpRealizeWish(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                LogUtil.e("success");
                desireListContract.helpRealizeWishSuccess();
            }

            @Override
            public void failure(CommonException e) {
                desireListContract.helpRealizeWishSuccess();
            }
        });
        compositeSubscription.add(subscription);
    }

    //直接推荐,没有选择愿望
    public void directlyRecommended(String actionId,String actionName,String beginTime,String endTime) {
        Map<String,String> map = new HashMap<>();
        map.put("actionId",actionId);
        map.put("actionName",actionName);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);

        Subscription subscription = retrofitClient.helpRealizeWish(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                LogUtil.e("success---");
                desireListContract.directlyRecommendSuccess();
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure---"+e.getErrorMsg());
                desireListContract.directlyRecommendFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }
}
