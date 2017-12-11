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
 * 删除会话
 */

public class DeleteContactDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private String name;
    private TextView cancel, plan, tips;
    private ClickDeleteListener deleteListener;

    public DeleteContactDialog(@NonNull Context context) {
        super(context);
    }

    public DeleteContactDialog(Context context, int themeResID) {
        super(context, themeResID);
        this.mContext = context;
    }

    public DeleteContactDialog(Context context, int themeResID, String name) {
        super(context, themeResID);
        this.mContext = context;
        this.name = name;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.dialog_delete_contact, null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = DisplayUtils.dip2px(mContext, 160);
        lp.width = DisplayUtils.dip2px(mContext, 280);
        win.setAttributes(lp);

        cancel = (TextView) view.findViewById(R.id.dialog_delete_contact_cancel);
        plan = (TextView) view.findViewById(R.id.dialog_delete_contact_delete);
        tips = (TextView) view.findViewById(R.id.dialog_delete_contact_tips);

        tips.setText("将" + name + "删除,将同时删除与该联系人的聊天记录");
        cancel.setOnClickListener(this);
        plan.setOnClickListener(this);
    }

    public void setData(String text) {
        tips.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_delete_contact_cancel:
                dismiss();
                break;
            case R.id.dialog_delete_contact_delete:
                //确定
                if (deleteListener != null) {
                    deleteListener.clickDelete();
                }
                dismiss();
                break;

            default:
                break;
        }
    }

    public interface ClickDeleteListener {
        void clickDelete();
    }

    public void setClickDeleteListener(ClickDeleteListener listener) {
        this.deleteListener = listener;
    }
}
