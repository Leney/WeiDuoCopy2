package com.zhongke.weiduo.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.library.rxjava.ObservableUtils;
import com.zhongke.weiduo.library.rxjava.SubscribeUtils;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.LoginContract;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;
import com.zhongke.weiduo.mvp.presenter.LoginPresenter;
import com.zhongke.weiduo.mvp.ui.widget.CustomDialog;
import com.zhongke.weiduo.util.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;


/**
 * 登录界面
 * Created by llj on 2017/6/20.
 */

public class LoginActivity extends BaseMvpActivity implements LoginContract, View.OnClickListener {
    private LoginPresenter mPresenter;
    private final String tag = LoginActivity.class.getSimpleName();

    @Bind(R.id.login_phone_input)
    EditText phoneInput;
    @Bind(R.id.login_pwd_input)
    EditText pwdInput;
    @Bind(R.id.login_login_btn)
    TextView loginBtn;
    @Bind(R.id.login_register_btn)
    TextView registerBtn;
    @Bind(R.id.login_forget_pwd_btn)
    TextView forgetBtn;
    @Bind(R.id.login_qq_login)
    ImageView qqLoginBtn;
    @Bind(R.id.login_chat_login)
    ImageView chatLoginBtn;
    private SharedPreferences preferences;
    private CustomDialog loginDialog;
    private String userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName(getResources().getString(R.string.login));
        setCenterLay(R.layout.activity_login);
        showCenterView();
        ButterKnife.bind(this);
        initView();
        queryAccountMsg();
    }


    /**
     * 查询数据库中存储的数据
     */
    private void queryAccountMsg() {
        SubscribeUtils.toSubscribe(ObservableUtils.createQueryAccountMsg(), new Subscriber<LoginResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LoginResult loginResult) {
                if (loginResult.getSysUser() != null) {
                    Log.i(tag, " 查询到的账号" + loginResult.getSysUser().getUserPhone());
                    phoneInput.setText(loginResult.getSysUser().getUserPhone());
                }
            }
        });
    }

    private void initView() {
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        forgetBtn.setOnClickListener(this);
        qqLoginBtn.setOnClickListener(this);
        chatLoginBtn.setOnClickListener(this);
        preferences = getPreferences(MODE_PRIVATE);
        userPhone = preferences.getString("userPhone", "");
        if (!StringUtils.isEmpty(userPhone)) {
            phoneInput.setText(userPhone);
        }
    }

    @Override
    public void loginSuccess(LoginResult loginResult) {
        if (loginDialog != null && loginDialog.isShowing()) {
            loginDialog.dismiss();
        }
        // TODO 登录成功
        MainActivity.startActivity(LoginActivity.this);
        preferences.edit().putString("userPhone", phoneInput.getText().toString().trim()).commit();
        finish();
    }

    @Override
    public void loginFailed() {
        // UIUtils.showToast();
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new LoginPresenter(LoginActivity.this);
        return mPresenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login_btn:
                // 登录
                // 手机号码
                String phone = phoneInput.getText().toString().trim();
                // 密码
                String pwd = pwdInput.getText().toString().trim();
                if (phone.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.login(phone, pwd);
                break;
            case R.id.login_register_btn:
                // 新用户注册
                RegisterActivity.startActivity(LoginActivity.this);
                break;
            case R.id.login_forget_pwd_btn:
                // 忘记密码
                ResetPasswordActivity.startActivity(this);
                break;
            case R.id.login_qq_login:
                // QQ登录
                break;
            case R.id.login_chat_login:
                // 微信登录
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110) {
            if (resultCode == Activity.RESULT_OK) {
                queryAccountMsg();
            }
        }
    }
}
