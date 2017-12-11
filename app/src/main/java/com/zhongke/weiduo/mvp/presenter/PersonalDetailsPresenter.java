package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.PersonalDetailsContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.FriendInfoBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/8/11.
 */

public class PersonalDetailsPresenter extends BasePresenter{
    private final String TAG = getClass().getSimpleName();
    private PersonalDetailsContract personalDetailsContract;
    private DataManager dataManager;

    public PersonalDetailsPresenter(PersonalDetailsContract personalDetailsContract, DataManager dataManager) {
        this.personalDetailsContract = personalDetailsContract;
        this.dataManager = dataManager;
    }

    public void getFriendInfo(String userId) {
        Map<String,String> map = new HashMap<>();
        map.put("userId",userId);
        Subscription  subscription =retrofitClient.getFriendInfo(map, new ResponseResultListener<FriendInfoBean>() {
            @Override
            public void success(FriendInfoBean friendInfoBean) {
                LogUtil.e("success---");
                personalDetailsContract.getFriendInFoSuccess(friendInfoBean);
            }

            @Override
            public void failure(CommonException e) {
                personalDetailsContract.getFriendInFoFailed(e);
                LogUtil.e("failure---"+e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }

    //删除好友
    public void removeFriend(String friendId) {
        Map<String,String> map = new HashMap<>();
        map.put("friendId",friendId);

        Subscription subscription = retrofitClient.removeFriend(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                LogUtil.e("success---");
                personalDetailsContract.removeFriendSuccess();
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure---"+e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }
}
