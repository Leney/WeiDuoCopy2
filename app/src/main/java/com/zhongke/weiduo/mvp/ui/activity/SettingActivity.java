package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SettingContract;
import com.zhongke.weiduo.mvp.presenter.SettingPresenter;
import com.zhongke.weiduo.mvp.ui.widget.dialog.CustomerServiceDialog;
import com.zhongke.weiduo.mvp.ui.widget.dialog.LoginOutDialog;
import com.zhongke.weiduo.mvp.ui.widget.dialog.ModifyPassDialog;

/**
 * Created by ${tanlei} on 2017/9/22.
 * 设置界面
 */

public class SettingActivity extends BaseMvpActivity implements View.OnClickListener, SettingContract, ModifyPassDialog.DetermineListener {
    private SettingPresenter presenter;
    private ModifyPassDialog modifyPassDialog;
    private ConstraintLayout modifyPasswordLayout;
    private ConstraintLayout consumerPhoneLayout;
    private CustomerServiceDialog dialog1;
    private ConstraintLayout aboutUsLayout;

    /**
     * 退出登录确认框
     */
    private LoginOutDialog loginOutDialog;

    @Override
    protected BasePresenter createPresenter() {
        presenter = new SettingPresenter(this, mDataManager);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_setting);
        setTitleName("设置");
        showCenterView();
        init();
    }

    private void init() {
        modifyPasswordLayout = (ConstraintLayout) findViewById(R.id.activity_setting_modify_password_constraintlayout);
        modifyPasswordLayout.setOnClickListener(this);
        consumerPhoneLayout = (ConstraintLayout) findViewById(R.id.activity_setting_consumer_phone_layout);
        consumerPhoneLayout.setOnClickListener(this);
        aboutUsLayout = (ConstraintLayout) findViewById(R.id.activity_setting_about_us_layout);
        aboutUsLayout.setOnClickListener(this);
        // 退出登录
        findViewById(R.id.setting_again_login_tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_setting_modify_password_constraintlayout:
                if (modifyPassDialog == null) {
                    modifyPassDialog = new ModifyPassDialog(this);
                    modifyPassDialog.setDetermineListener(SettingActivity.this);
                }
                modifyPassDialog.show();
                break;
            case R.id.activity_setting_consumer_phone_layout:
                if (dialog1 == null) {
                    dialog1 = new CustomerServiceDialog(this);
                }
                dialog1.show();
                break;
            case R.id.activity_setting_about_us_layout:
                RegardingWeActivity.startActivity(this);
                break;
            case R.id.setting_again_login_tv:
                // 退出登录
                if(loginOutDialog == null){
                    loginOutDialog = new LoginOutDialog(SettingActivity.this);
                }
                loginOutDialog.show();
                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }

    //点击确定回调
    @Override
    public void determine(String originalPas, String newPas, String repeatPas) {
        if ("".equals(originalPas)) {
            UIUtils.showToast("请输入原密码");
        } else if("".equals(newPas)) {
            UIUtils.showToast("请输入新密码");
        }else if("".equals(repeatPas)) {
            UIUtils.showToast("请输入确认密码");
        }else if (!newPas.equals(repeatPas)) {//新密码和再次输入密码不一样
            UIUtils.showToast("两次输入的密码不一致");
        } else if(!TextUtils.isEmpty(originalPas) && !TextUtils.isEmpty(newPas)&& originalPas.equals(newPas)) {//新密码与旧密码相同
            UIUtils.showToast("新密码不能与旧密码相同");
        } else if (!"".equals(originalPas ) && newPas.equals(repeatPas)) {
            presenter.changePassword(originalPas,newPas,repeatPas);
        }
    }

    @Override
    public void changePasswordSuccess() {
        UIUtils.showToast("修改密码成功");
        if (modifyPassDialog != null) {
            modifyPassDialog.dismiss();
        }
    }

    @Override
    public void changePasswordFailed(CommonException e) {
        UIUtils.showToast("修改密码失败,"+e.getErrorMsg());
    }
}
