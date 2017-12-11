package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.zhongke.weiduo.R;


/**
 * 超时dialog
 * Created by llj on 2017/8/28.
 */

public class TimeOutDialog extends BaseDialog {

    public TimeOutDialog(@NonNull Context context) {
        super(context);
    }

    public TimeOutDialog(@NonNull Context context, int style, View.OnClickListener onClickListener) {
        super(context, style, onClickListener);
    }

    public TimeOutDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context, onClickListener);
    }

    @Override
    protected View getRootView() {
        ImageView imageView  = new ImageView(getContext());
        imageView.setImageResource(R.drawable.time_out);
        return imageView;
    }

    @Override
    protected void initView(View rootView) {
        setCanceledOnTouchOutside(true);
    }
}
