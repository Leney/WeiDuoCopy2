package com.zhongke.weiduo.mvp.presenter;

import android.widget.Toast;

import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.library.rxjava.ObservableUtils;
import com.zhongke.weiduo.library.rxjava.SubscribeUtils;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.LoginContract;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;
import com.zhongke.weiduo.mvp.model.requestEntity.LoginEntity;

import rx.functions.Action1;

/**
 * 登录
 * Created by llj on 2017/6/20.
 */

public class LoginPresenter extends BasePresenter {
    private static final String TAG = "LoginPresenter";
    private LoginContract mContract;

    public LoginPresenter(LoginContract contract) {
        super();
        this.mContract = contract;
    }

    /**
     * 登录
     */
    public void login(String account, String pwd) {
        LoginEntity entity = LoginEntity.newInstance(account, pwd);
        retrofitClient.getLogin(entity, new ResponseResultListener<LoginResult>() {
            @Override
            public void success(LoginResult loginResult) {
                 exctureSaveTask(loginResult);
            }
            @Override
            public void failure(CommonException e) {
                // TODO: 2017/11/27
                //Toast.makeText(appContext, "手机号码或密码不正确请重新输入", Toast.LENGTH_SHORT).show();
                UIUtils.showToast(e.getErrorMsg());
            }
        });
    }

    /**
     *
     * @param loginResult
     */
    private void exctureSaveTask(LoginResult loginResult){
        ZkApplication.getInstance().setToken(loginResult.getToken());
        ObservableUtils.createSavePersonMSG(loginResult)
                .compose(SubscribeUtils.createTransformer()).subscribe(b -> {
                    Toast.makeText(appContext, "登入成功", Toast.LENGTH_SHORT).show();
                    mContract.loginSuccess(loginResult);
                }, error -> {
            Toast.makeText(appContext, "插入数据异常", Toast.LENGTH_SHORT).show();
            mContract.loginFailed();
        });
    }


}
