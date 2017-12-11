package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by hyx on 2017/11/2.
 *
 * 管理规划提醒
 */

public class ManagementPlanDialog extends Dialog implements View.OnClickListener {

    private Context mContext;

    public ManagementPlanDialog(@NonNull Context context) {
        super(context);
    }

    public ManagementPlanDialog(Context context,int themeResID) {
        super(context,themeResID);
        this.mContext = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext,R.layout.dialog_management_plan,null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = DisplayUtils.dip2px(mContext,160);
        lp.width = DisplayUtils.dip2px(mContext,280);
        win.setAttributes(lp);

        TextView cancel = (TextView) view.findViewById(R.id.dialog_management_plan_cancel);
        TextView plan = (TextView) view.findViewById(R.id.dialog_management_plan_plan);

        cancel.setOnClickListener(this);
        plan.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_management_plan_cancel:
                dismiss();
                break;
            case R.id.dialog_management_plan_plan:
                //去规划
                dismiss();
                break;

            default:
                break;
        }
    }
}
