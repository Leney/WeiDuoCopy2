package com.zhongke.weiduo.mvp.presenter;

import android.util.Log;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchAddFamilyContract;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyTag;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyFamilyBean;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by dgg1 on 2017/11/16.
 */

public class SearchAddFamilyPresenter extends BasePresenter {
    private SearchAddFamilyContract contract;

    public SearchAddFamilyPresenter(SearchAddFamilyContract contract) {
        this.contract = contract;
    }

    /**
     * 获取推荐标签
     */
    public void getFamilyTag(int pageIndex) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("pageIndex", pageIndex + "", "pageSize", "4");
        Subscription subscription = retrofitClient.getFamilyTag(hashMap, new ResponseResultListener<NewFamilyTag>() {
            @Override
            public void success(NewFamilyTag familyTag) {
                contract.showFamilyTag(familyTag);
                Log.e("lee", "成功");
            }

            @Override
            public void failure(CommonException e) {
                Log.e("lee", "失败" + e.getErrorMsg() + e.toString());
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 获取附近的家庭列表
     */
    public void getNearbyFamily() {
        HashMap hashMap = RequestParameterUtils.setRequestPar("token", "3qs8258d1510796301474");
        Subscription subscription = retrofitClient.getNearbyFamily(hashMap, new ResponseResultListener<NewNearbyFamilyBean>() {
            @Override
            public void success(NewNearbyFamilyBean newNearbyFamilyBean) {
                contract.showNearbyFamilyList(newNearbyFamilyBean);
            }

            @Override
            public void failure(CommonException e) {

            }
        });
        compositeSubscription.add(subscription);
    }
}
