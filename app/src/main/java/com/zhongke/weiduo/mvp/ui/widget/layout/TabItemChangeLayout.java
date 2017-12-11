package com.zhongke.weiduo.mvp.ui.widget.layout;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.mvp.model.entity.TabItem;
import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.TextViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${xingen} on 2017/6/21.
 *
 *  一个tab切换的layout
 */

public class TabItemChangeLayout extends LinearLayout implements View.OnClickListener {
    private final  String TAG=TabItemChangeLayout .class.getSimpleName();
    private Context context;
    private List<TabItem> itemList;
    /**
     * Tab中item的颜色
     */
    private int greeColor, whiteColor, blockColor;
    /**
     * Tab中Item的默认字体大小，单位sp
     */
    private final int TEXT_SIZE = 16;
    private final float MARGIN_TOP = 16.7f;

    /**
     * 创建画笔，用于测量文字的长度
     */
    private Paint paint;
    /**
     * 选中的id
     */
    private int selectedPosition =0;
    /**
     * 上一次点击的id , 防止重复点击
     */
    private int before_id;

    private TabChangeListener changeListener;

    public TabItemChangeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initConfig();
        //设置水平方向
        this.setOrientation(HORIZONTAL);
        //设置背景
        this.setBackgroundColor(whiteColor);
    }
    /**
     * 初始化一些配置
     */
    private void initConfig() {
        this.greeColor = ColorUtils.stringToColor("#1cbf61");
        this.whiteColor = ColorUtils.stringToColor("#ffffff");
        this.blockColor = ColorUtils.stringToColor("#333333");
        this.itemList=new ArrayList<>();
        this.paint=new Paint();
    }
    /***
     *     添加item
     */
    private void addTabItem() {
        //移除先前存在的子item。
        this.removeAllViews();
        //计算线段的长度
       int lineWidth=(int) this.calculationLineWith();
        //添加item
        for (int i = 0; i < itemList.size(); ++i) {
            RelativeLayout layout = createLayout();
            layout.setId(itemList.get(i).getViewId());
            LinearLayout childrenLayout=createChildrenLayout();
            TextView name_tv=createTV();
            TextView line_tv=createLineTv(lineWidth);
            name_tv.setText(itemList.get(i).getViewName());
            if(i== selectedPosition){//根据id,设置显示选中的item。
                before_id=itemList.get(i).getViewId();
                name_tv.setTextColor(greeColor);
                line_tv.setBackgroundColor(greeColor);
            }else{
                name_tv.setTextColor(blockColor);
                line_tv.setBackgroundColor(whiteColor);
            }
            childrenLayout.addView(name_tv);
            childrenLayout.addView(line_tv);
            layout.addView(childrenLayout);
            this.addView(layout);
            layout.setOnClickListener(this);
        }
    }
    /**
     * 计算出Tab中item显示文字的最大长度，即底部指示线的长度。
     * @return
     */
    private  float calculationLineWith(){
        float maxLength=0;
        paint.reset();
        paint.setTextSize(DisplayUtils.sp2px(context,TEXT_SIZE));
        for (int i = 0; i < itemList.size(); ++i) {
            float newLength= TextViewUtils.calculationDigitalWith(paint,itemList.get(i).getViewName());
            if(newLength>maxLength){
                maxLength=newLength;
            }
        }
        return maxLength;
    }
    /**
     * 创建一个RelativeLayout,
     * 利用其子View居中特性，
     * 摆放TabItem的位置。
     * @return
     */
    private RelativeLayout createLayout() {
        RelativeLayout layout = new RelativeLayout(context);
        LinearLayout.LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        layoutParams.topMargin = DisplayUtils.dip2px(context, MARGIN_TOP);
        layout.setLayoutParams(layoutParams);
        return layout;
    }
    /**
     *  创建显示文本的TextView
     * @return
     */
    private TextView createTV() {
        TextView tv = new TextView(context);
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity= Gravity.CENTER_HORIZONTAL;
        tv.setLayoutParams(layoutParams);
        tv.setTextSize( TEXT_SIZE);
        return tv;
    }

    /**
     * 创建一个指示线的TextView
     * @param width
     * @return
     */
    private TextView createLineTv(int width){
        TextView tv = new TextView(context);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width, DisplayUtils.dip2px(context,2));
        layoutParams.topMargin=DisplayUtils.dip2px(context,14);
        tv.setLayoutParams(layoutParams);
        return tv;
    }
    /**
     * 创建一个LinearLayout，用于竖直排列文本和指示线
     * @return
     */
    private LinearLayout createChildrenLayout() {
        LinearLayout layout = new LinearLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        layout.setOrientation(VERTICAL);
        layout.setLayoutParams(layoutParams);
        return layout;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==before_id){//防止重复点击
            return;
        }
        /**
         * 筛选出对应的item，改变颜色，发出通知。
         */
        for (int i=0;i<itemList.size();++i){
            LinearLayout layout=(LinearLayout)   ( (RelativeLayout) this.findViewById(itemList.get(i).getViewId()) ).getChildAt(0);
            TextView name_tv=(TextView) layout.getChildAt(0);
            TextView line_tv=(TextView) layout.getChildAt(1);
            if(v.getId()==itemList.get(i).getViewId()){
                before_id=v.getId();
                name_tv.setTextColor(greeColor);
                line_tv.setBackgroundColor(greeColor);
                Log.i(TAG," 点击了 位置 :"+i+"监听器  "+changeListener);
                if(changeListener!=null){
                    changeListener.changeTab(v.getId());
                }
            }else{
                name_tv.setTextColor(blockColor);
                line_tv.setBackgroundColor(whiteColor);
            }
        }
    }
    public void setChangeListener(TabChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    /**
     * 设置选中的Tab
     * @param position
     */
    public void setCurrentSelect(int position){
       if (position>=itemList.size())return;
       int newId= itemList.get(position).getViewId();
        if (before_id==newId) return;
        /**
         * 筛选出对应的item，改变颜色，发出通知。
         */
        for (int i=0;i<itemList.size();++i){
            LinearLayout layout=(LinearLayout)   ( (RelativeLayout) this.findViewById(itemList.get(i).getViewId()) ).getChildAt(0);
            TextView name_tv=(TextView) layout.getChildAt(0);
            TextView line_tv=(TextView) layout.getChildAt(1);
            if(newId==itemList.get(i).getViewId()){
                before_id=newId;
                name_tv.setTextColor(greeColor);
                line_tv.setBackgroundColor(greeColor);
            }else{
                name_tv.setTextColor(blockColor);
                line_tv.setBackgroundColor(whiteColor);
            }
        }
    }
    /**
     *  对外公开，添加tab数据
     * @param list
     */
    public void addItemTab(List<TabItem> list){
        addItemTab(list,0);
    }
    /**
     *  添加指定的Item,和选中的位置
     * @param list
     * @param selectedPosition
     */
    public void addItemTab(List<TabItem> list,int selectedPosition){
        this.selectedPosition =selectedPosition;
        this.itemList.clear();
        this.itemList.addAll(list);
        this.addTabItem();
    }
    /**
     * 一个Tab改变的监听器
     */
    public interface TabChangeListener {
        void changeTab(int currentTab);
    }
}
