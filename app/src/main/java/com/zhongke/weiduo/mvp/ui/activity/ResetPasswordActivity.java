package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ResetPasswordContract;
import com.zhongke.weiduo.mvp.presenter.ResetPasswordPresenter;
import com.zhongke.weiduo.mvp.ui.widget.CountDownTextView;
import com.zhongke.weiduo.util.StringUtils;

import butterknife.ButterKnife;

public class ResetPasswordActivity extends BaseMvpActivity implements View.OnClickListener, ResetPasswordContract {
    private EditText resetPhoneNumber;
    private EditText resetCode;
    private EditText newPassWord;
    private EditText confirmPassWord;
    private TextView submit;
    private CountDownTextView getCode;
    private ResetPasswordPresenter passwordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_reset_password);
        baseTitle.setTitleName("重置密码");
        resetPhoneNumber = (EditText) findViewById(R.id.reset_password_input_phonenumber);
        resetCode = (EditText) findViewById(R.id.reset_password_input_authentication_code);
        newPassWord = (EditText) findViewById(R.id.reset_password_input_password);
        confirmPassWord = (EditText) findViewById(R.id.reset_password_confirm_password);
        getCode = (CountDownTextView) findViewById(R.id.reset_password_get_authentication_code);
        submit = (TextView) findViewById(R.id.submit);
        submit.setOnClickListener(this);
        getCode.setOnClickListener(this);
        showCenterView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (getCode != null) {
            getCode.stop();
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        passwordPresenter = new ResetPasswordPresenter(this);
        return passwordPresenter;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //获取验证码
            case R.id.reset_password_get_authentication_code:
                // 手机号码
                String phoneStr = resetPhoneNumber.getText().toString().trim();
                if (!StringUtils.isPhone(phoneStr)) {
                    Toast.makeText(this, getResources().getString(R.string.register_tips_2), Toast.LENGTH_SHORT).show();
                    return;
                }
                // 倒计时
                getCode.start(60);
                passwordPresenter.getPhoneCode(phoneStr);
                break;
            //提交
            case R.id.submit:
                // 手机号码
                String phone = resetPhoneNumber.getText().toString().trim();
                if (!StringUtils.isPhone(phone)) {
                    Toast.makeText(this, getResources().getString(R.string.register_tips_2), Toast.LENGTH_SHORT).show();
                    return;
                }

                // 验证码
                String msgCode = resetCode.getText().toString().trim();
                if (StringUtils.isEmpty(msgCode)) {
                    Toast.makeText(this, getResources().getString(R.string.register_tips_3), Toast.LENGTH_SHORT).show();
                    return;
                }
                String newPass = newPassWord.getText().toString().trim();
                String confirmPass = confirmPassWord.getText().toString().trim();
                //判断新设置的密码是否为空
                if (StringUtils.isEmpty(newPass) || StringUtils.isEmpty(confirmPass)) {
                    Toast.makeText(this, "新密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断两次输入的密码是否一样
                if (!newPass.equals(confirmPass)) {
                    Toast.makeText(this, "两次输入的密码不一致，请从新输入。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPass.length() < 8 || newPass.length() > 12) {
                    Toast.makeText(this, "提示:(8-12位数字与字母组合)", Toast.LENGTH_SHORT).show();
                    return;
                }
                passwordPresenter.confirm2Network(phone, msgCode, newPass, confirmPass);
                break;
        }
    }

    @Override
    public void modifySuccess() {
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void modifyFailure(CommonException e) {
        Toast.makeText(this, e.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }
}
