package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by dgg1 on 2017/11/4.
 */

public class DeleteFamilyDialog extends Dialog implements View.OnClickListener {
    TextView tvSure, tvCancel;

    public DeleteFamilyDialog(@NonNull Context context) {
        this(context, 0);
    }

    public DeleteFamilyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_delete_family, null);
        setContentView(view);
        tvSure = (TextView) view.findViewById(R.id.dialog_delete_contact_delete);
        tvCancel = (TextView) view.findViewById(R.id.dialog_delete_contact_cancel);
        tvSure.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = DisplayUtils.dip2px(context, 160);
        lp.width = DisplayUtils.dip2px(context, 280);
        win.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dialog_delete_contact_delete) {
            dismiss();
        } else if (v.getId() == R.id.dialog_delete_contact_cancel) {
            dismiss();
        }
    }
}
