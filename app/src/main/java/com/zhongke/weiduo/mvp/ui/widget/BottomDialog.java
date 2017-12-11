package com.zhongke.weiduo.mvp.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zhongke.weiduo.R;

/**
 * 底部弹出dialog
 * Created by llj on 2017/6/23.
 */

public class BottomDialog extends Dialog implements View.OnClickListener {
    private Activity activity;

    public BottomDialog(Activity activity,View view) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
        setContentView(view);
        init();
    }

    public BottomDialog(Activity activity,int resId) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
        setContentView(resId);
        init();
    }


    private void init(){
        setViewLocation();
        setCanceledOnTouchOutside(true);//外部点击取消
    }


    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation(){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = height;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }

    @Override
    public void onClick(View view) {

    }
}
