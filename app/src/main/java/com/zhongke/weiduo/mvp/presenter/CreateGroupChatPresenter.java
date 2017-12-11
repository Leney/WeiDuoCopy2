package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CreateGroupChatContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.NewGroupBean;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/8/11.
 */

public class CreateGroupChatPresenter extends BasePresenter {
    private CreateGroupChatContract createGroupChatContract;
    private DataManager dataManager;

    public CreateGroupChatPresenter(CreateGroupChatContract createGroupChatContract, DataManager dataManager) {
        this.createGroupChatContract = createGroupChatContract;
        this.dataManager = dataManager;
    }

    /**
     * 创建群组
     */
    public void createGroup(String userIds) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("userIds", userIds);
        Subscription subscription = retrofitClient.createGroup(hashMap, new ResponseResultListener<NewGroupBean>() {
            @Override
            public void success(NewGroupBean newGroupBean) {
                createGroupChatContract.createGroupSuccess(newGroupBean);
            }

            @Override
            public void failure(CommonException e) {

            }
        });
        compositeSubscription.add(subscription);
    }
}
