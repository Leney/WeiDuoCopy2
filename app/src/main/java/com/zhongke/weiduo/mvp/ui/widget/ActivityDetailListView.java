package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by ${xingen} on 2017/6/22.
 *
 * 一个指定带有footView的ListView
 */

public class ActivityDetailListView extends ListViewForScrollview{
    private Context context;
    public ActivityDetailListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        setStyle();
        initDivide();
    }

    private void setStyle() {
        this.setBackgroundColor(ColorUtils.stringToColor("#ffffff"));
        //隐藏ScrollBar
        this.setScrollbarFadingEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.setVerticalScrollBarEnabled(false);
        this.setSelector(new ColorDrawable(ColorUtils.stringToColor("#ffffff")));
    }

    /**
     * 绘制Divider
     */
    private void initDivide() {
        ColorDrawable colorDrawable=new ColorDrawable(ColorUtils.stringToColor("#f0f0f0"));
        this.setDivider(colorDrawable);
        this.setDividerHeight(DisplayUtils.dip2px(context,1));
    }

    /**
     * 隐藏divider
     */
    public void setHideDrivider(){
        this.setDivider(null);
        this.setDividerHeight(0);
    }

    private TextView evaluation_tv,participate_tv;


    /**
     * 添加底部View,添加点击相应
     */
    private void initFootView(OnClickListener onClickListener) {
        View footView=View.inflate(context, R.layout.activityprocess_listview_footview,null);
        this.evaluation_tv=(TextView) footView.findViewById(R.id.listview_footview_evaluation_tv);
        this.participate_tv=(TextView) footView.findViewById(R.id.listview_footview_participate_tv);
        this.evaluation_tv.setOnClickListener(onClickListener);
        this.participate_tv.setOnClickListener(onClickListener);
        this.addFooterView(footView);
    }
    /**
     *  尾部view的点击监听
     * @param onClickListener
     */
    public void setOnclick(OnClickListener onClickListener){
        this.evaluation_tv.setOnClickListener(onClickListener);
        this.participate_tv.setOnClickListener(onClickListener);
    }

    /***
     * 添加默认的footView
     */
    public void addDefaultFootView(){
       addDefaultFootView(null);
    }
    /***
     * 添加默认的footView,添加响应监听器
     */
    public void addDefaultFootView(OnClickListener onClickListener){
        initFootView(onClickListener);
    }
    /**
     *  添加指定的FootView
     * @param footView
     */
    public void addIndexFootView(View footView){
        this.addFooterView(footView);
    }
}
