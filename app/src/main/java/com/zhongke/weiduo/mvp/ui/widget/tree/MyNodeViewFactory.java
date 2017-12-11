package com.zhongke.weiduo.mvp.ui.widget.tree;

import android.view.View;

import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.base.BaseNodeViewBinder;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.base.BaseNodeViewFactory;


/**
 * Created by llj on 2017/9/22.
 */

public class MyNodeViewFactory extends BaseNodeViewFactory {
    /** 有竖线的Adapter类型*/
    public static final int LINE_TYPE = 0;
    /** 没有竖线的Adapter类型*/
    public static final int NONE_LINE_TYPE = 1;
    /**
     * 默认是否展开
     */
    private boolean isDefaultExpand = false;
    /**
     * adapter视图类型
     */
    private int viewType;

    /**
     *
     * @param isDefaultExpand 默认是否展开
     * @param type 视图类型，是否有竖线，LINE_TYPE=0=有竖线类型的Adapter,NONE_LINE_TYPE=1=没有竖线类型的Adapter
     */
    public MyNodeViewFactory(boolean isDefaultExpand,int type) {
        this.isDefaultExpand = isDefaultExpand;
        this.viewType = type;
    }

    @Override
    public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
        if(viewType == LINE_TYPE){
            // 有竖线的视图
            if (level == 0) {
                return new FirstLevelNodeViewBinder(view, isDefaultExpand);
            } else {
                return new OtherLevelNodeViewBinder(view, isDefaultExpand);
            }
        }else if(viewType == NONE_LINE_TYPE){
            // 没有竖线的视图
            if(level == 0){
                return new FirstLevelNodeViewNoLineBinder(view,isDefaultExpand);
            }else {
                return new OtherLevelNodeViewNoLineBinder(view,isDefaultExpand);
            }
        }

        // 默认返回没有竖线的视图
        if(level == 0){
            return new FirstLevelNodeViewNoLineBinder(view,isDefaultExpand);
        }else {
            return new OtherLevelNodeViewNoLineBinder(view,isDefaultExpand);
        }
    }
}
