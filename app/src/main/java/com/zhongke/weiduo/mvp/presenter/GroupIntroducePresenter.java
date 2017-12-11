package com.zhongke.weiduo.mvp.presenter;

import android.util.Log;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.GroupIntroduceContract;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyOrGroupDetail;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by hyx on 2017/10/10.
 */

public class GroupIntroducePresenter extends BasePresenter {
    private GroupIntroduceContract contract;

    public GroupIntroducePresenter(GroupIntroduceContract contract) {
        this.contract = contract;
    }

    /**
     * 请求加入群组
     */
    public void requestJoinGroup(int familyOrGroupId) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("familyOrGroupId", familyOrGroupId + "", "groupType", "2");
        Subscription subscription = retrofitClient.joinGroup(hashMap, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                contract.joinGroupSuccess();
                Log.e("lee", "GroupIntroducePresenter");
            }

            @Override
            public void failure(CommonException e) {

            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 获取群组信息
     */
    public void getGroupDetail(int groupId) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("familyOrGroupId", groupId + "");
        Subscription subscription = retrofitClient.getFamilyOrGroupDetail(hashMap, new ResponseResultListener<NewFamilyOrGroupDetail>() {
            @Override
            public void success(NewFamilyOrGroupDetail newFamilyOrGroupDetail) {
                contract.showGroupDetail(newFamilyOrGroupDetail);
            }

            @Override
            public void failure(CommonException e) {
                contract.showErrorViews();
            }
        });
        compositeSubscription.add(subscription);
    }
}
