package com.zhongke.weiduo.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.RegisterContract;
import com.zhongke.weiduo.mvp.presenter.RegisterPresenter;
import com.zhongke.weiduo.mvp.ui.widget.CountDownTextView;
import com.zhongke.weiduo.mvp.ui.widget.CustomDialog;
import com.zhongke.weiduo.util.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by llj on 2017/6/17.
 * 注册界面
 */
public class RegisterActivity extends BaseMvpActivity implements RegisterContract, View.OnClickListener {

    private RegisterPresenter mPresenter;

/*    @Bind(R.id.register_device_code_input)
    EditText deviceCodeInput;*/
    @Bind(R.id.register_phone_input)
    EditText phoneInput;
    @Bind(R.id.register_msg_code_input)
    EditText msgCodeInput;
    @Bind(R.id.register_pwd_input)
    EditText newPwdInput;
    @Bind(R.id.register_count_down_text)
    CountDownTextView countDownView;
    @Bind(R.id.register_register_btn)
    TextView registerBtn;
/*    @Bind(R.id.register_scan_qdcode_img)
    ImageView scanQdBtn;*/
    @Bind(R.id.register_des)
    TextView registerDes;

    private CustomDialog registerDialog;
    /**
     * 默认注册方式是邀请码，1
     */
    private int registerType=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName(getResources().getString(R.string.register));
        setCenterLay(R.layout.activity_register);
        showCenterView();
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (countDownView != null) {
            countDownView.stop();
        }
    }

    private void initView() {
        registerBtn.setOnClickListener(this);
        countDownView.setOnClickListener(this);


        String des = getResources().getString(R.string.register_des);
        SpannableString spannableString = new SpannableString(des);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(getResources().getColor(R.color.main_color));  //设置下划线颜色
                ds.setUnderlineText(true);  // 显示下划线
            }

            @Override
            public void onClick(View view) {     // TextView点击事件
                Log.i("llj", " 点击事件！！！");
            }
        }, 18, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        registerDes.setText(spannableString);
        registerDes.setMovementMethod(LinkMovementMethod.getInstance());
//        registerDes.setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new RegisterPresenter(RegisterActivity.this, mDataManager, this);
        return mPresenter;
    }

    @Override
    public void registerSuccess() {
        if (registerDialog!=null&&registerDialog.isShowing()){
            registerDialog.dismiss();
        }
        // 注册成功跳转到登陆界面x
        Toast.makeText(this, getResources().getString(R.string.register_success), Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void registerFailed(int errorCode, String msg) {
        if (     registerDialog!=null&&     registerDialog.isShowing()){
            registerDialog.dismiss();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100011) {
            if (resultCode== Activity.RESULT_OK){
                registerType=2;
                // 扫描二维码返回
                String result = data.getStringExtra("result");
                if (!TextUtils.isEmpty(result)) {
                    Toast.makeText(ZkApplication.getContext(), result, Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_register_btn:

                // 手机号码
                String phone = phoneInput.getText().toString().trim();
                if (!StringUtils.isPhone(phone)) {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_tips_2), Toast.LENGTH_SHORT).show();
                    return;
                }

                // 验证码
                String msgCode = msgCodeInput.getText().toString().trim();
                if (StringUtils.isEmpty(msgCode)) {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_tips_3), Toast.LENGTH_SHORT).show();
                    return;
                }
                // 密码
                String pwd = newPwdInput.getText().toString().trim();
                if (!StringUtils.isPwd(pwd)) {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_tips_4), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (registerDialog == null) {
                    registerDialog = new CustomDialog(RegisterActivity.this);
                    registerDialog.setMessage(getResources().getString(R.string.registering));
                    registerDialog.setTitleVisible(false);
                    registerDialog.setBtnLayVisible(false);
                    registerDialog.setWidthAndHeight(CustomDialog.DEFAULT_WIDTH, 120);
                }
                registerDialog.show();
                // TODO 请求网络注册
                mPresenter.register(phone, pwd,  msgCode);
                break;
            case R.id.register_count_down_text:
                // 手机号码
                String phoneStr = phoneInput.getText().toString().trim();
                if (!StringUtils.isPhone(phoneStr)) {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_tips_2), Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.getPhoneCode(phoneStr);
                // 倒计时
                countDownView.start(60);
                break;
            case R.id.register_des:
                Log.i("llj", "底部点击事件");
                break;
     /*       case R.id.register_scan_qdcode_img:
                // 二维码按钮
                MipcaActivityCapture.openActivity(this);
                break;*/
            default:
                break;
        }
    }

    public static void startActivity(Activity context) {
        context.startActivityForResult(new Intent(context, RegisterActivity.class),110);
    }
}
