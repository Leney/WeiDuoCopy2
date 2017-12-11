package com.zhongke.weiduo.mvp.ui.widget.tree;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.TreeNode;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.base.BaseNodeViewBinder;
import com.zhongke.weiduo.util.DisplayUtils;


/**
 * 子级树状层级视图(第一级Adapter)
 * Created by llj on 2017/9/22.
 */

public class OtherLevelNodeViewNoLineBinder extends BaseNodeViewBinder {

    private TextView name;
    private Context mContext;

    /**
     * 默认是否展开
     */
    private boolean isDefaultExpand;

    public OtherLevelNodeViewNoLineBinder(View itemView, boolean isDefaultExpand) {
        super(itemView);
        this.isDefaultExpand = isDefaultExpand;
        mContext = itemView.getContext();
        name = (TextView) itemView.findViewById(R.id.other_level_node_name);
    }

    @Override
    public int getLayoutId() {
        return R.layout.other_level_node_no_line_lay;
    }

    @Override
    public void bindView(TreeNode treeNode) {
        name.setText(treeNode.getValue().toString());
    }

    @Override
    public void onItemClick(TreeNode treeNode) {
    }

}
