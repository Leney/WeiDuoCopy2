package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.StringUtils;

/**
 * Created by ${xingen} on 2017/6/22.
 *
 *
 */
public abstract class BaseDialog extends Dialog {
    protected View rootView;
    protected Context context;
    protected View.OnClickListener onClickListener;
    public BaseDialog(@NonNull Context context) {
        this(context,null);
    }
    public BaseDialog(@NonNull Context context, int style,View.OnClickListener onClickListener) {
        super(context, style);
        this.onClickListener=onClickListener;
        this.context=context;
        setStyle();
        rootView=getRootView();
        initView(rootView);

        this.setContentView(rootView);
    }
    public BaseDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        this(context, R.style.DialogTheme,onClickListener);
    }
    /***
     *  设置dialog特征
     */
    private void setStyle() {
        //设置点击弹窗外不取消
        this.setCanceledOnTouchOutside(false);
        //默认设置弹窗位于手机屏幕中心
         this.setDialogGravity(Gravity.CENTER);
    }
    /**
     *  设置弹窗的位置
     * @param gravity
     */
    protected void setDialogGravity(int gravity){
        WindowManager.LayoutParams layoutParams= this.getWindow().getAttributes();
        layoutParams.gravity= gravity;
    }
    /**
     *  设置弹窗的位置
     * @param
     */
    protected void setDialogMargin(float horizontalMargin,float verticalMargin){
        WindowManager.LayoutParams layoutParams= this.getWindow().getAttributes();
        layoutParams.horizontalMargin=horizontalMargin;
        layoutParams.horizontalWeight=1;
        layoutParams.verticalMargin=verticalMargin;
        layoutParams.verticalWeight=1;
    }
    /**
     *  设置弹窗按back键盘不可取消，例如，强制下线，强制更新。
     */
    protected  void setBackNotCancle(){
        this.setCancelable(true);
    }
    /**
     *   设置弹窗的比例
     * @param proportion
     */
    protected void setDialogWidth(float proportion){
        WindowManager.LayoutParams layoutParams= this.getWindow().getAttributes();
        layoutParams.width= (int) (StringUtils.getPhoneMetrics(context).widthPixels*proportion);
    }
    /**
     * 设置输入法软键盘，例如：含有EditText的布局，需要弹出软键盘。
     */
    public void setSoftInputMode(int inputMode){
        WindowManager.LayoutParams layoutParams= this.getWindow().getAttributes();
        layoutParams.softInputMode=inputMode;
    }
    /**
     *   设置弹窗的的dpi
     * @param withdpi
     */
    protected void setDialogWidth(int  withdpi){
        WindowManager.LayoutParams layoutParams= this.getWindow().getAttributes();
        layoutParams.width= DisplayUtils.dip2px(context,withdpi);
    }
    /**
     * 获取弹窗中的根布局
     * @return
     */
    protected  abstract View   getRootView();

    /**
     * 初始化控件
     * @param rootView
     */
    protected  abstract  void initView(View rootView);
}
