package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.zhongke.weiduo.R;


/**
 * 提醒评价的dialog
 */

public class RemindCommentDialog extends BaseDialog implements View.OnClickListener {
    public RemindCommentDialog(@NonNull Context context) {
        super(context);
    }

    public RemindCommentDialog(@NonNull Context context, int style, View.OnClickListener onClickListener) {
        super(context, style, onClickListener);
    }

    public RemindCommentDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context, onClickListener);
    }

    @Override
    protected View getRootView() {
        return View.inflate(getContext(), R.layout.dialog_remind_comment, null);
    }

    @Override
    protected void initView(View rootView) {
        rootView.findViewById(R.id.remind_comment_cancel).setOnClickListener(this);
        rootView.findViewById(R.id.remind_comment_sure).setOnClickListener(this);
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

                break;
        }
    }

}
