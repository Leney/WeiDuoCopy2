package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SettingContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/9/26.
 */

public class SettingPresenter extends BasePresenter {
    private SettingContract settingContract;
    private DataManager manager;

    public SettingPresenter(SettingContract settingContract, DataManager manager) {
        this.settingContract = settingContract;
        this.manager = manager;
    }

    //更改密码
    public void changePassword(String oldPwd,String newPwd,String sureNewPwd) {
        Map<String ,String> map = new HashMap<>();
        map.put("oldPwd",oldPwd);
        map.put("newPwd",newPwd);
        map.put("sureNewPwd",sureNewPwd);

        Subscription subscription = retrofitClient.changePassword(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                LogUtil.e("success---");
                settingContract.changePasswordSuccess();
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure---"+e.getErrorMsg());
                settingContract.changePasswordFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }
}
