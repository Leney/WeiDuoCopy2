package com.zhongke.weiduo.mvp.ui.widget.tree;


import android.view.View;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.TreeNode;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.base.BaseNodeViewBinder;


/**
 * 一级树状层级视图(第一级Adapter)
 * Created by llj on 2017/9/22.
 */

public class FirstLevelNodeViewBinder extends BaseNodeViewBinder {

    private TextView name;
    private View expandLine;
    private View pointView;
    /**
     * 默认是否展开
     */
    private boolean isDefaultExpand = false;
    public FirstLevelNodeViewBinder(View itemView,boolean isDefaultExpand) {
        super(itemView);
        this.isDefaultExpand = isDefaultExpand;
        name = (TextView) itemView.findViewById(R.id.first_level_node_name);
        expandLine = itemView.findViewById(R.id.first_level_expand_line);
        pointView = itemView.findViewById(R.id.first_level_left_point);
    }

    @Override
    public int getLayoutId() {
        return R.layout.first_level_node_lay;
    }

    @Override
    public void bindView(TreeNode treeNode) {
        name.setText(treeNode.getValue().toString());
        // 默认不显示展开的线
        expandLine.setVisibility(isDefaultExpand ? View.VISIBLE : View.INVISIBLE);
        treeNode.setFirstWidth(pointView.getLayoutParams().width);
        if(treeNode.getIconRes() != -1){
            pointView.setBackgroundResource(treeNode.getIconRes());
        }else {
            pointView.setBackgroundResource(R.drawable.circle_gray_stroke_shape);
        }
    }

    @Override
    public void onItemClick(TreeNode treeNode) {
        if(treeNode.getChildren().isEmpty()){
            // 没有子item了
            expandLine.setVisibility(View.INVISIBLE);
        }else {
            // 还有子item
            expandLine.setVisibility(treeNode.isExpanded()?View.VISIBLE:View.INVISIBLE);
        }
    }

}
