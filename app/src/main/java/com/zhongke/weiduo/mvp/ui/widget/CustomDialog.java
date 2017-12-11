package com.zhongke.weiduo.mvp.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;

/**
 * Created by llj  on 2017/6/17.
 */

public class CustomDialog extends Dialog {

    /**
     * 默认宽度
     */
    public static final int DEFAULT_WIDTH = 280;

    /**
     * 默认高度
     */
    public static final int DEFAULT_HEIGHT = 120;

    private TextView title, msg, leftBtn, rightBtn;

    /**
     * 底部按钮部分
     */
    private LinearLayout bottomLay;

    /**
     * 中间视图部分
     */
    private FrameLayout centerLay;

    private Context context;

    public CustomDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
        init(context, DEFAULT_WIDTH,DEFAULT_HEIGHT,true);
    }

    public CustomDialog(Context context, int width,int height,boolean isDefault) {
        super(context, R.style.DialogTheme);
        init(context, width,height,isDefault);
    }

    public CustomDialog(@NonNull Context context,boolean isDefault) {
        super(context, R.style.DialogTheme);
        init(context, DEFAULT_WIDTH,DEFAULT_HEIGHT,isDefault);
    }




    private void init(Context context, int width,int height,boolean isDefault) {
        // 设置内容
        setContentView(R.layout.custom_dialog);
        this.context = context;
        // 设置窗口属性
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        // 设置宽度、高度、密度、对齐方式
        float density = getDensity(context);
        params.width = (int) (width * density);
        if(isDefault){
            params.height = (int) (height * density);
        }
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);


        title = (TextView) findViewById(R.id.dialog_title);
        msg = (TextView) findViewById(R.id.dialog_content);
        leftBtn = (TextView) findViewById(R.id.dialog_left_btn);
        rightBtn = (TextView) findViewById(R.id.dialog_right_btn);
        bottomLay = (LinearLayout) findViewById(R.id.dialog_button_lay);
        centerLay = (FrameLayout) findViewById(R.id.dialog_center_lay);
    }


    /**
     * 自定义中间视图
     * @param resId
     */
    public void setCenterLay(int resId) {
        centerLay.removeAllViews();
        View view = View.inflate(context, resId, null);
        centerLay.addView(view, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
    }

    /**
     * 自定义中间视图
     * @param view
     */
    public void setCenterLay(View view) {
        centerLay.removeAllViews();
        centerLay.addView(view, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
    }

    /**
     * 设置左边按钮点击事件
     *
     * @param listener
     */
    public void setLeftBtnOnClickListener(View.OnClickListener listener) {
        this.leftBtn.setOnClickListener(listener);
    }

    /**
     * 设置右边按钮的点击事件
     *
     * @param listner
     */
    public void setRightBtnOnClickListener(View.OnClickListener listner) {
        this.rightBtn.setOnClickListener(listner);
    }

    /**
     * 设置消息内容
     *
     * @param msg
     */
    public void setMessage(String msg) {
        this.msg.setText(msg);
    }

    /**
     * 设置标题内容
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title.setText(title);
    }

    /**
     * 设置左边按钮的名称
     *
     * @param name
     */
    public void setLeftBtnName(String name) {
        this.leftBtn.setText(name);
    }

    /**
     * 设置右边按钮的名称
     *
     * @param name
     */
    public void setRightBtnName(String name) {
        this.rightBtn.setText(name);
    }

    /**
     * 设置标题是否显示
     *
     * @param visible
     */
    public void setTitleVisible(boolean visible) {
        this.title.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    /**
     * 设置整个按钮区域是否显示
     *
     * @param visible
     */
    public void setBtnLayVisible(boolean visible) {
        this.bottomLay.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置左边按钮是否显示
     *
     * @param visible
     */
    public void setLeftBtnVisible(boolean visible) {
        this.leftBtn.setVisibility(visible ? View.VISIBLE : View.GONE);
        if (!visible) {
            findViewById(R.id.dialog_btn_split).setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边边按钮是否显示
     *
     * @param visible
     */
    public void setRightBtnVisible(boolean visible) {
        this.rightBtn.setVisibility(visible ? View.VISIBLE : View.GONE);
        if (!visible) {
            findViewById(R.id.dialog_btn_split).setVisibility(View.GONE);
        }
    }

    /**
     * 设置内容文本颜色
     *
     * @param resId
     */
    public void setMsgColor(int resId) {
        this.msg.setTextColor(ContextCompat.getColor(context, resId));
    }

    /**
     * 设置标题文本颜色
     *
     * @param resId
     */
    public void setTitleColor(int resId) {
        this.title.setTextColor(ContextCompat.getColor(context, resId));
    }

    /**
     * 设置左边按钮文本颜色
     *
     * @param resId
     */
    public void setLeftBtnColor(int resId) {
        this.leftBtn.setTextColor(ContextCompat.getColor(context, resId));
    }

    /**
     * 设置右边按钮文本颜色
     *
     * @param resId
     */
    public void setRightBtnColor(int resId) {
        this.rightBtn.setTextColor(ContextCompat.getColor(context, resId));
    }


    /**
     * 设置dialog的宽
     *
     * @param width
     */
    public void setWidth(int width) {
        // 设置窗口属性
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        // 设置宽度、高度、密度、对齐方式
        float density = getDensity(context);
        params.width = (int) (width * density);
//        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    /**
     * 设置dialog的高
     *
     * @param height
     */
    public void setHeight(int height) {
        // 设置窗口属性
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        // 设置宽度、高度、密度、对齐方式
        float density = getDensity(context);
//        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    /**
     * 设置dialog的宽和高
     *
     * @param width
     * @param height
     */
    public void setWidthAndHeight(int width, int height) {
        // 设置窗口属性
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        // 设置宽度、高度、密度、对齐方式
        float density = getDensity(context);
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }


    /**
     * 获取显示密度
     *
     * @param context
     * @return
     */
    private float getDensity(Context context) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        return dm.density;
    }

}
