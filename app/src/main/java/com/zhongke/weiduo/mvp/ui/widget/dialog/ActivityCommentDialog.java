package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.zhongke.weiduo.R;

/**
 * Created by ${xingen} on 2017/6/22.
 *
 * 活动评论弹窗
 */

public class ActivityCommentDialog extends BaseDialog {
    private TextView commit_tv;

    public ActivityCommentDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context,onClickListener);
        //设置弹窗宽度填充手机宽度
        this.setDialogWidth(1.0f);
        //设置弹窗居于底部和水平中心
        this.setDialogGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
        //当弹窗具备焦点的是时候，一直显示输入法。
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
    @Override
    protected View getRootView() {
        return View.inflate(context, R.layout.dialog_activitycomment,null);
    }

    @Override
    protected void initView(View rootView) {
          this.commit_tv=(TextView)  rootView.findViewById(R.id.activitycomment_commit_tv);
          this.commit_tv.setOnClickListener(onClickListener);
    }

}
