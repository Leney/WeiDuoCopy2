package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.TextViewUtils;

/**
 * Created by ${xingen} on 2017/6/21.
 */

public class ActivityProcessLayout extends LinearLayout {
    private Context context;
    private int blockColor, lineColor, whiteColor;
    private final int title_tv_size = 18, content_tv_size = 16;

    public ActivityProcessLayout(Context context) {
        super(context);
        this.context = context;
        this.blockColor = ColorUtils.stringToColor("#333333");
        this.lineColor = ColorUtils.stringToColor("#f2f2f2");
        this.whiteColor = ColorUtils.stringToColor("#ffffff");
        this.setOrientation(VERTICAL);
    }

    /**
     * 一级标题
     *
     * @return
     */
    private TextView createTitleTV(String s) {
        TextView textView = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setCompoundDrawablePadding(DisplayUtils.dip2px(context, 8.3f));
        int padding = DisplayUtils.dip2px(context, 9);
        textView.setPadding(padding, padding, padding, padding);
        textView.setLayoutParams(layoutParams);
        textView.getPaint().setFakeBoldText(true);
        textView.setTextColor(blockColor);
        textView.setTextSize(DisplayUtils.sp2px(context, title_tv_size));
        textView.setText(s);
        return textView;
    }

    /**
     * 创建线段
     * @return
     */
    private TextView createLine() {
        TextView textView = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, DisplayUtils.dip2px(context, 0.5f));
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundColor(lineColor);
        return textView;
    }
    /***
     * 创建时间布局
     */
    private RelativeLayout createDateLayout(){
        RelativeLayout layout=new RelativeLayout(context);
        LayoutParams layoutParams1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(layoutParams1);
        layout.setPadding(DisplayUtils.dip2px(context,10),DisplayUtils.dip2px(context,16.3f),DisplayUtils.dip2px(context,10),DisplayUtils.dip2px(context,16.3f));

        return layout;
    }

    /**
     * 带小圆圈的TextView
     * @return
     */
    private TextView createTV1(){
        TextView textView = new TextView(context);
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setCompoundDrawablePadding(DisplayUtils.dip2px(context,7.7f));
        TextViewUtils.setCompoundDrawables(textView, R.drawable.activityprocess_green_bg_shape,0,0,0);
        return  textView;
    }


}
