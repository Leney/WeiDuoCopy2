package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.BuilderMap;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.library.rxjava.ObservableUtils;
import com.zhongke.weiduo.library.rxjava.SubscribeUtils;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.RegisterContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.RegisterResultBean;

import rx.Subscription;

/**
 * 注册
 * Created by llj on 2017/6/17.
 */

public class RegisterPresenter extends BasePresenter {
    private static final String TAG = "RegisterPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private RegisterContract mContract;

    public RegisterPresenter(Context context, DataManager dataManager, RegisterContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 注册
     *
     * @param phone
     * @param pwd

     * @param msgCode
     */
    public void register(final String phone, String pwd,String msgCode) {
        Subscription subscription = retrofitClient.getRegister(BuilderMap.buildMapRegistrer(phone, pwd,  msgCode), new ResponseResultListener<RegisterResultBean>() {
            @Override
            public void success(RegisterResultBean registerResultBean) {
                saveRegisterMsg(phone);
            }

            @Override
            public void failure(CommonException e) {
                mContract.registerFailed(e.getCode(), e.getErrorMsg());
            }
        });
        this.compositeSubscription.add(subscription);
    }

    private void saveRegisterMsg(String phone) {
        Subscription subscription = ObservableUtils.createSaveRegisterMSG(phone)
                .compose(SubscribeUtils.createTransformer())
                .subscribe(
                        result ->
                                mContract.registerSuccess(),
                        error -> {
                            UIUtils.showToast("保存数据失败");
                            mContract.registerSuccess();
                        });

        this.compositeSubscription.add(subscription);
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
