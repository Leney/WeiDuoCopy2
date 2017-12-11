package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.BuilderMap;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ResetPasswordContract;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by hyx on 2017/10/27.
 */

public class ResetPasswordPresenter extends BasePresenter {
    private ResetPasswordContract contract;

    public ResetPasswordPresenter(ResetPasswordContract contract) {
        this.contract = contract;
    }

    /**
     * 准备做提交新密码到网络
     */

    public void confirm2Network(String userPhone, String phoneCode, String userPass, String sureUserPass) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("userPhone", userPhone, "phoneCode", phoneCode, "userPass", userPass, "sureUserPass", sureUserPass);
        Subscription subscription = retrofitClient.resetPassword(hashMap, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                contract.modifySuccess();
            }

            @Override
            public void failure(CommonException e) {
                contract.modifyFailure(e);
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * @param phone
     */
    public void getPhoneCode(String phone) {
        Subscription subscription = retrofitClient.getPhoneCode(BuilderMap.buildMapPhoneCode(phone), new ResponseResultListener() {
            @Override
            public void success(Object o) {
                UIUtils.showToast("获取短信验证码成功");
            }

            @Override
            public void failure(CommonException e) {
                UIUtils.showToast("获取短信验证码失败");
            }
        });
        this.compositeSubscription.add(subscription);
    }
}
