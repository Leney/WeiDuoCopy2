package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.BuilderMap;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.library.rxjava.ObservableUtils;
import com.zhongke.weiduo.library.rxjava.SubscribeUtils;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.PersonalContract;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;
import com.zhongke.weiduo.mvp.model.entity.NewPersonalData;
import com.zhongke.weiduo.mvp.model.requestEntity.ModifyUserBean;

import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by hyx on 2017/9/25.
 */

public class PersonalPresenter extends BasePresenter {

    private Context context;
    private PersonalContract contract;
    private LoginResult loginResult;

    public PersonalPresenter(Context context, PersonalContract contract) {
        this.context = context;
        this.contract = contract;
    }

    /**
     * 获取个人信息
     */
    public void getPresenter() {
        Subscription subscription = retrofitClient.getPersonalData(new ResponseResultListener<NewPersonalData>() {
            @Override
            public void success(NewPersonalData newPersonalData) {
                contract.showPersonal(newPersonalData);
            }

            @Override
            public void failure(CommonException e) {
                contract.showFailure();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 修改个人信息
     */
    public void modifyInfo(ModifyUserBean bean) {
        Map<String, Object> hashMap = BuilderMap.buildMapPersona(bean);
        Subscription subscription = retrofitClient.modifyUserInfo(hashMap, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                contract.showSaveSuccess();
            }

            @Override
            public void failure(CommonException e) {
                contract.showSaveFailure(e);
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 修改头像  nickName
     */
    public void modifyInfo(ModifyUserBean bean,int state) {
        Map<String, Object> hashMap = BuilderMap.buildMapPersona(bean);
        Subscription subscription = retrofitClient.modifyUserInfo(hashMap, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                contract.showSaveSuccess(state);
                LogUtil.e("success---"+state);
            }

            @Override
            public void failure(CommonException e) {
                contract.showSaveFailure(e);
                LogUtil.e("failure---"+e.getErrorMsg()+"state=="+state);
            }
        });
        compositeSubscription.add(subscription);
    }

    public void changeLocalData (String data,int state) {
        SubscribeUtils.toSubscribe(ObservableUtils.createQueryAccountMsg(),new Subscriber<LoginResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LoginResult loginResult) {
                PersonalPresenter.this.loginResult = loginResult;
                if (state == 1) {
                    loginResult.getSysUser().setHeadImageUrl(data);
                } else if (state == 2) {
                    loginResult.getSysUser().setNickName(data);
                }

                ObservableUtils.createSavePersonMSG(loginResult)
                        .compose(SubscribeUtils.createTransformer()).subscribe(new Action1() {
                    @Override
                    public void call(Object b) {
                        LogUtil.e("更改成功");
                    }
                }, new Action1() {
                    @Override
                    public void call(Object error) {
                        LogUtil.e("更改失败");
                    }
                });
            }
        });
    }
}
