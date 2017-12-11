package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.GroupDataContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.NewGroupMemberBean;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/8/16.
 */

public class GroupDataPresenter extends BasePresenter {
    private static final String TAG = "GroupDataPresenter";
    private DataManager mDataManager;
    private GroupDataContract groupDataContract;

    public GroupDataPresenter(DataManager mDataManager, GroupDataContract groupDataContract) {
        this.mDataManager = mDataManager;
        this.groupDataContract = groupDataContract;
    }

    /**
     * 移除某个成员群组
     */
    public void requestDropOutGroup() {
        HashMap hashMap = RequestParameterUtils.setRequestPar("familyOrGroupId", "64", "userIds", "1");
        Subscription subscription = retrofitClient.dropOutGroup(hashMap, new ResponseResultListener() {
            @Override
            public void success(Object o) {

            }

            @Override
            public void failure(CommonException e) {
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 获取群组或者家庭成员列表
     */
    public void getGroupMemberList(String groupId) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("groupId", groupId);
        Subscription subscription = retrofitClient.getGroupMemberList(hashMap, new ResponseResultListener<NewGroupMemberBean>() {
            @Override
            public void success(NewGroupMemberBean newGroupMemberBean) {
                groupDataContract.showGroupMemberList(newGroupMemberBean);
            }

            @Override
            public void failure(CommonException e) {
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 退出群组
     */
    public void requestQuitGroup(String familyOrGroupId) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("familyOrGroupId", familyOrGroupId);
        Subscription subscription = retrofitClient.quitGroup(hashMap, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                groupDataContract.quitGroupSuccess();
            }

            @Override
            public void failure(CommonException e) {
                groupDataContract.quitGroupFailure(e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }
}
