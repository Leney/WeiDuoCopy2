package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;

import com.zhongke.weiduo.util.ColorUtils;

/**
 * Created by ${xingen} on 2017/6/19.
 */

public class ListViewForScrollview  extends ListView{

    public ListViewForScrollview(Context context) {
        super(context);
        setStyle();
    }
    public ListViewForScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        setStyle();
    }
    private void setStyle(){
        this.setSelector(new ColorDrawable(ColorUtils.stringToColor("#00000000")));
    }
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
}
