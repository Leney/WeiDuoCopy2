package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.FamilyDetailContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyOrGroupDetail;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * 家庭详情
 * Created by llj on 2017/6/23.
 */

public class FamilyDetailPresenter extends BasePresenter {
    private static final String TAG = "FamilyDetailPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private FamilyDetailContract mContract;

    public FamilyDetailPresenter(Context context, DataManager dataManager, FamilyDetailContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 获取家庭详情数据
     */
    public void getFamilyDetail(int id) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("familyOrGroupId", id + "");
        retrofitClient.getFamilyOrGroupDetail(hashMap, new ResponseResultListener<NewFamilyOrGroupDetail>() {
            @Override
            public void success(NewFamilyOrGroupDetail newFamilyOrGroupDetail) {
                mContract.getDetailSuccess(newFamilyOrGroupDetail);
            }

            @Override
            public void failure(CommonException e) {
                mContract.getDetailFailed();
            }
        });
    }

    /**
     * 添加家庭为友好家庭
     */
    public void addFriendFamily(int familyId, String familyNickName, String myFamilyId, String myFamilyName) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("familyId", familyId + "", "familyNickName", familyNickName, "myFamilyId", myFamilyId, "myFamilyName", myFamilyName);
        Subscription subscription = retrofitClient.addFamilyFriend(hashMap, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                mContract.addFriendFamilySuccess();
            }

            @Override
            public void failure(CommonException e) {

            }
        });
        compositeSubscription.add(subscription);
    }
}
