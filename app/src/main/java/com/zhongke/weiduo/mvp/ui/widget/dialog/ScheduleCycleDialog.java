package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.widget.wheelview.CustomDateView;

/**
 * Created by hyx on 2017/9/28.
 */

public class ScheduleCycleDialog extends Dialog {

    private Context mContext;
    private String chooseStr;
    public ScheduleCycleDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public ScheduleCycleDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.dialog_append_schedule_cycle,null);
        setContentView(view);

        Window win = getWindow();
        if (win != null) {
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.width = com.zhongke.weiduo.util.StringUtils.getPhoneMetrics(mContext).widthPixels;
            win.setAttributes(lp);
            win.setGravity(Gravity.BOTTOM);
        }

        TextView cancel = (TextView) view.findViewById(R.id.append_schedule_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        TextView sure = (TextView) view.findViewById(R.id.append_schedule_sure);
        sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    public String getChooseStr() {
        return chooseStr;
    }
}
