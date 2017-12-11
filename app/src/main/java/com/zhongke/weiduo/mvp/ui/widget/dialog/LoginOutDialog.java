package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.library.rxjava.ObservableUtils;
import com.zhongke.weiduo.library.rxjava.SubscribeUtils;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.control.UserInfoManager;
import com.zhongke.weiduo.mvp.ui.activity.LoginActivity;
import com.zhongke.weiduo.util.ActivityUtils;


/**
 * 退出登录的dialog
 * Created by llj on 2017/11/24.
 */

public class LoginOutDialog extends BaseDialog implements View.OnClickListener {
    public LoginOutDialog(@NonNull Context context) {
        super(context);
    }

    public LoginOutDialog(@NonNull Context context, int style, View.OnClickListener onClickListener) {
        super(context, style, onClickListener);
    }

    public LoginOutDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context, onClickListener);
    }

    @Override
    protected View getRootView() {
        return View.inflate(getContext(), R.layout.dialog_comfirm_login_out, null);
    }

    @Override
    protected void initView(View rootView) {
        rootView.findViewById(R.id.dialog_login_out_cancel_btn).setOnClickListener(this);
        rootView.findViewById(R.id.dialog_login_out_sure_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_login_out_cancel_btn:
                // 取消
                dismiss();
                break;
            case R.id.dialog_login_out_sure_btn:
                // 确定
                dismiss();
                loginOut();
                break;
        }
    }

    private void loginOut() {
        try {
            ZkApplication.getInstance().setToken("");
            ObservableUtils.createDeletePersonMSG().compose(SubscribeUtils.createTransformer()).subscribe();
            LoginActivity.startActivity(getContext());
            ActivityUtils.finishAllActivity();
            // 清除用户信息
            UserInfoManager.getInstance().clear();
            // 清除最近聊天记录
            RecentChatListManager.getInstance().clear();
            // 清除联系人信息
            ContactsManager.getInstance().clear();
            // 清除所有聊天记录
            IMClient.getInstance().clear();
            // 注销掉及时通讯信息
            IMClient.getInstance().unInitSdk();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
