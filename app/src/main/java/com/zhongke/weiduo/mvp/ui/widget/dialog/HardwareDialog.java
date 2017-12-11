package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;

/**
 * Created by ${tanlei} on 2017/9/22.
 * 硬件管理授权密码弹出框
 */

public class HardwareDialog extends Dialog implements View.OnClickListener {
    private Activity activity;
    private TextView tvCancel, tvSure;
    private EditText etInput;
    private ImageView iv;
    /**
     * 是否记住密码
     */
    private boolean isCheck;

    public HardwareDialog(@NonNull Context context) {
        this(context, 0);
    }

    public HardwareDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.DialogTheme_no_title);
        View v = View.inflate(context, R.layout.hardware_dialog, null);
        this.activity = (Activity) context;
        this.setContentView(v);
        initView(v);
        setDialogWidth();
    }

    private void initView(View v) {
        tvCancel = (TextView) v.findViewById(R.id.bt_cancel);
        tvSure = (TextView) v.findViewById(R.id.bt_sure);
        tvCancel.setOnClickListener(this);
        tvSure.setOnClickListener(this);
        etInput = (EditText) v.findViewById(R.id.et_input);
        iv = (ImageView) v.findViewById(R.id.iv_check);
        iv.setTag(isCheck);
        iv.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.bt_cancel:
                dismiss();
                break;
            case R.id.iv_check:
                boolean check = (boolean) iv.getTag();
                check = !check;
                if (check) {
                    iv.setImageResource(R.drawable.hardware_check);
                } else {
                    iv.setImageResource(R.drawable.hardware_uncheck);
                }
                iv.setTag(check);
                break;
            case R.id.bt_sure:
                String s = etInput.getText().toString().trim();
                if (s.equals("")) {
                    //授权密码正确
                    dismiss();
                } else {
                    //授权密码错误
                    etInput.setText("");
                }
                break;
            default:
                break;
        }
    }
}
