package com.zhongke.weiduo.mvp.ui.widget.expandablelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

/**
 * Created by ${xingen} on 2017/7/1.
 */

public class CommonExpandableListView  extends ExpandableListView implements ExpandableListView.OnGroupClickListener {
    public CommonExpandableListView(Context context) {
        super(context);
        setStyle();
    }
    public CommonExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setStyle();
    }
    /**
     * 根据需求，设置一些特征。
     */
    private void setStyle() {
        //Group中的图标，设置为空
        this.setGroupIndicator(null);
        //设置Group点击，不收回childe。
        this.setOnGroupClickListener(this);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;//返回true,拦截点击展开与收缩事件。
    }

    /**
     * 设置展开全部child
     */
    public void unfoldedAllChildView(){
       ExpandableListAdapter expandableListView= getExpandableListAdapter();
        for (int i=0;i<expandableListView.getGroupCount();++i){
             this.expandGroup(i);
        }
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
