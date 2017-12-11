package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by hyx on 2017/9/26.
 */

public class HomeAddressDialog extends Dialog{

    private Context mContext;

    public HomeAddressDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public HomeAddressDialog(Context context,int themeResID) {
        super(context,themeResID);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext,R.layout.dialog_home_address,null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = DisplayUtils.dip2px(mContext,225);
        lp.width = DisplayUtils.dip2px(mContext,280);
        win.setAttributes(lp);

        LinearLayout cancel = (LinearLayout) view.findViewById(R.id.address_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

}
