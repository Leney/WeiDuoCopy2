package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.zhongke.weiduo.R;

/**
 * Created by ${xingen} on 2017/11/8.
 */

public class HandelTokenExpireDialog  extends BaseDialog {
    public HandelTokenExpireDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context, R.style.DialogTheme_no_black,onClickListener);
        setDialogWidth(0.8f);
    }

    @Override

    protected View getRootView() {
        return View.inflate(context, R.layout.dialog_handle_token_expire,null);
    }

    @Override
    protected void initView(View rootView) {
          rootView.findViewById(R.id.dialog_handle_token_expire_sure_tv).setOnClickListener(onClickListener);
    }
}
