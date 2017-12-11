package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.activity.GrantRewardActivity;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by hyx on 2017/11/2.
 *
 * 询问是否去发放奖励界面
 */

public class GoGrandRewardDialog extends Dialog implements View.OnClickListener {

    private Context mContext;

    public GoGrandRewardDialog(@NonNull Context context) {
        super(context);
    }

    public GoGrandRewardDialog(Context context, int themeResID) {
        super(context,themeResID);
        this.mContext = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext,R.layout.dialog_go_to_grand_reward,null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = DisplayUtils.dip2px(mContext,160);
        lp.width = DisplayUtils.dip2px(mContext,280);
        win.setAttributes(lp);

        TextView cancel = (TextView) view.findViewById(R.id.dialog_go_to_grand_cancel);
        TextView go = (TextView) view.findViewById(R.id.dialog_go_to_grand_go);

        cancel.setOnClickListener(this);
        go.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_go_to_grand_cancel:
                dismiss();
                break;
            case R.id.dialog_go_to_grand_go:
                //去发放奖品
                GrantRewardActivity.startActivity(mContext);
                dismiss();
                break;

            default:
                break;
        }
    }
}
