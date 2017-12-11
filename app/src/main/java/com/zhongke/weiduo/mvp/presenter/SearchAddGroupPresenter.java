package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchAddGroupContract;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyGroup;

import rx.Subscription;

/**
 * Created by dgg1 on 2017/11/16.
 */

public class SearchAddGroupPresenter extends BasePresenter {
    private SearchAddGroupContract contract;

    public SearchAddGroupPresenter(SearchAddGroupContract contract) {
        this.contract = contract;
    }

    /**
     * 获取附近的群组列表
     */
    public void getNearbyGroup() {
        Subscription subscription = retrofitClient.getNearbyGroup(new ResponseResultListener<NewNearbyGroup>() {
            @Override
            public void success(NewNearbyGroup newNearbyGroup) {
                contract.showNearbyGroup(newNearbyGroup);
            }

            @Override
            public void failure(CommonException e) {
                contract.showError();
            }
        });
        compositeSubscription.add(subscription);
    }
}
