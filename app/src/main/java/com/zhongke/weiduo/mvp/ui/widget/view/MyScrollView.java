package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 添加滚动监听的ScrollView
 * Created by llj on 2017/9/19.
 */

public class MyScrollView extends ScrollView {

    private OnScrollChangeListener mListener;


    public OnScrollChangeListener getListener() {
        return mListener;
    }

    public MyScrollView setScrollListener(OnScrollChangeListener listener) {
        mListener = listener;
        return this;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) mListener.onScrollChanged(l, t, oldl, oldt);
    }

    public interface OnScrollChangeListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
