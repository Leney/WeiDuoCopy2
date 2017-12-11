package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 简单封装GridView设置不可点击
 * Created by llj on 2017/6/21.
 */

public class WapGridView extends GridView {
    public WapGridView(Context context) {
        super(context);


    }

    public WapGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WapGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WapGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        return false;
//    }


    /**
     * onMeasure():设置listview的高度，只是适应ScrollView
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

//    /**
//     * 设置上下不滚动
//     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if(ev.getAction() == MotionEvent.ACTION_MOVE){
//            return true;//true:禁止滚动
//        }
//
//        return super.dispatchTouchEvent(ev);
//    }
}
