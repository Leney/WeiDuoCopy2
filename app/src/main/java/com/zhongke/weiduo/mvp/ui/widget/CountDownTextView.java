package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import com.zhongke.weiduo.R;


/**
 * 倒计时控件
 * Created by llj
 */
public class CountDownTextView extends android.support.v7.widget.AppCompatTextView {
    private final int COUNT_DOWN_MSG = 1;
    /** 倒计时总次数 */
    private int totalCount = 60;
    /** 默认的显示文本*/
    private String defaultText;

    private Drawable defaultBg;

    /** 倒计时的时候控件的背景颜色*/
    private int countDownBgCorlor;

    private String retryStr;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == COUNT_DOWN_MSG){
                if(--totalCount < 0){
                    // 倒计时完成
                    // 设置按钮可点击
                    setEnabled(true);
                    // 还原默认的显示文本
                    setText(defaultText);
                    // 还原默认的背景
                    setBackground(defaultBg);
                }else {
                    // 倒计时未完成
                    setText(totalCount + retryStr);
                    sendEmptyMessageDelayed(COUNT_DOWN_MSG,1000);
                }
            }
        }
    };

    public CountDownTextView(Context context) {
        super(context);
        retryStr = context.getResources().getString(R.string.retry_later);
    }

    public CountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        retryStr = context.getResources().getString(R.string.retry_later);

    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        retryStr = context.getResources().getString(R.string.retry_later);
    }

//    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    // 开始倒计时
    public void start(int totalCount){
        this.totalCount = totalCount;
        this.defaultText = getText().toString();
        // 开始计时 设置为不可点击
        setEnabled(false);
        setText(totalCount + retryStr);
        defaultBg = getBackground();
        setBackgroundResource(R.color.edit_lay_bg);
        handler.sendEmptyMessageDelayed(COUNT_DOWN_MSG,1000);
    }

    public void stop(){
        totalCount = -1;
    }
}
