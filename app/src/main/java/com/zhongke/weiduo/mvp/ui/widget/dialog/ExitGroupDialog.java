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
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by hyx on 2017/11/2.
 * <p>
 * 退出群组提醒
 */

public class ExitGroupDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private ExitGroupListener listener;

    public void setListener(ExitGroupListener listener) {
        this.listener = listener;
    }

    public ExitGroupDialog(@NonNull Context context) {
        super(context);
    }

    public ExitGroupDialog(Context context, int themeResID) {
        super(context, themeResID);
        this.mContext = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.dialog_exit_group, null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = DisplayUtils.dip2px(mContext, 160);
        lp.width = DisplayUtils.dip2px(mContext, 280);
        win.setAttributes(lp);

        TextView cancel = (TextView) view.findViewById(R.id.dialog_exit_group_cancel);
        TextView plan = (TextView) view.findViewById(R.id.dialog_exit_group_sure);

        cancel.setOnClickListener(this);
        plan.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_exit_group_cancel:
                dismiss();
                break;
            case R.id.dialog_exit_group_sure:
                //确定退出
                listener.exitGroup();
                break;

            default:
                break;
        }
    }

    public interface ExitGroupListener {
        void exitGroup();
    }
}
