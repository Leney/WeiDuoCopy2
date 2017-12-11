package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchAddFriendContract;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyUser;
import com.zhongke.weiduo.mvp.model.entity.NewRecommendUser;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by llj on 2017/11/16.
 */

public class SearchAddFriendPresenter extends BasePresenter {
    private SearchAddFriendContract mContract;

    public SearchAddFriendPresenter(SearchAddFriendContract contract) {
        this.mContract = contract;
    }

    /**
     * 获取附近的用户
     */
    public void getNearbyUserList() {
        Subscription subscription = retrofitClient.getNearbyUser(new ResponseResultListener<NewNearbyUser>() {
            @Override
            public void success(NewNearbyUser newNearbyUser) {
                mContract.showNearbyUserList(newNearbyUser);
            }

            @Override
            public void failure(CommonException e) {
                mContract.showErrorViews();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 获取推荐的好友列表
     *
     * @param pageIndex
     */
    public void getRecommendUserList(int pageIndex) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("pageSize", "4","pageIndex", pageIndex + "");
        Subscription subscription = retrofitClient.getRecommendUser(hashMap, new ResponseResultListener<NewRecommendUser>() {
            @Override
            public void success(NewRecommendUser newRecommendUser) {
                mContract.showRecommendUser(newRecommendUser);
            }

            @Override
            public void failure(CommonException e) {

            }
        });
        compositeSubscription.add(subscription);
    }
}
