package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zhongke.weiduo.R;

/**
 * Created by ${tanlei} on 2017/9/26.
 * 客服电话的对话框
 */

public class CustomerServiceDialog extends Dialog implements View.OnClickListener {
    private Activity activity;
    private TextView tvNumber, tvCancel, tvCall;

    public CustomerServiceDialog(@NonNull Context context) {
        this(context, 0);
    }

    public CustomerServiceDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.DialogTheme_no_title);
        activity = (Activity) context;
        View rootView = View.inflate(context, R.layout.dialog_customer_service, null);
        setContentView(rootView);
        initView(rootView);
        setDialogWidth();
    }

    private void initView(View rootView) {
        tvNumber = (TextView) rootView.findViewById(R.id.tv_number);
        tvCancel = (TextView) rootView.findViewById(R.id.tv_cancel);
        tvCall = (TextView) rootView.findViewById(R.id.tv_call);
        tvCancel.setOnClickListener(this);
        tvCall.setOnClickListener(this);
    }

    /**
     * 设置屏幕宽度
     */
    private void setDialogWidth() {
        Window window = getWindow();
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.65); // 宽度设置为屏幕的0.65
        window.setAttributes(p);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel) {
            //点击取消按钮，对话框消失
            dismiss();
        } else if (v.getId() == R.id.tv_call) {
            //点击呼叫，获取电话号码，进行呼叫
            String s = tvNumber.getText().toString();
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + s));
            activity.startActivity(intent);
            dismiss();
        }
    }
}
