package com.zhongke.weiduo.mvp.ui.widget.tree;


import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.activity.PlanDetailActivity;
import com.zhongke.weiduo.mvp.ui.activity.SchemeDetailActivity;
import com.zhongke.weiduo.mvp.ui.activity.SystemDetailsActivity;
import com.zhongke.weiduo.mvp.ui.activity.TargetDetailActivity;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.TreeNode;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.base.BaseNodeViewBinder;


/**
 * 子级树状层级视图(第一级Adapter)
 * Created by llj on 2017/9/22.
 */

public class OtherLevelNodeViewBinder extends BaseNodeViewBinder {

    private TextView name;
    private View pointImg;
    private View leftTopLine, leftBottomLine;
    private Context mContext;
    private int leftMargin;
    private View expandLine;
    private LinearLayout leftLineLay;
    // 分割线的宽度
    private int splineWidth;
    /**
     * 距离左边的横向线条对象
     */
    private View spaceLine;

    /**
     * 默认是否展开
     */
    private boolean isDefaultExpand;

    public OtherLevelNodeViewBinder(View itemView, boolean isDefaultExpand) {
        super(itemView);
        this.isDefaultExpand = isDefaultExpand;
        mContext = itemView.getContext();
        name = (TextView) itemView.findViewById(R.id.other_level_node_name);
        pointImg = itemView.findViewById(R.id.other_level_node_left_point);
        leftTopLine = itemView.findViewById(R.id.other_level_node_left_top_line);
        leftBottomLine = itemView.findViewById(R.id.other_level_node_left_bottom_line);
        expandLine = itemView.findViewById(R.id.other_level_node_expand_line);
        leftLineLay = (LinearLayout) itemView.findViewById(R.id.other_level_node_left_line_lay);
        spaceLine = itemView.findViewById(R.id.other_level_node_left_line);
    }

    @Override
    public int getLayoutId() {
        return R.layout.other_level_node_lay;
    }

    @Override
    public void bindView(TreeNode treeNode) {
        name.setText(treeNode.getValue().toString());

        if (treeNode.getIconRes() != -1) {
            pointImg.setBackgroundResource(treeNode.getIconRes());
        } else {
            pointImg.setBackgroundResource(R.drawable.circle_gray_stroke_shape);
        }

        int imgWidth = ((ConstraintLayout.LayoutParams) pointImg.getLayoutParams()).width;
        int spaceLineWidth = ((ConstraintLayout.LayoutParams) spaceLine.getLayoutParams()).width;

        treeNode.setFirstWidth(treeNode.getParent().getFirstWidth());

        if (leftMargin == 0) {
            if (treeNode.getLevel() == 1) {
//                leftMargin = imgWidth / 2;
                leftMargin = treeNode.getFirstWidth() / 2;
            } else {
                int count = treeNode.getLevel() - 1;
//                leftMargin = count * (spaceLineWidth + imgWidth / 2) + imgWidth / 2 + count;
                leftMargin = count * (spaceLineWidth + imgWidth / 2) + treeNode.getFirstWidth() / 2 + count;
            }
        }

        if (splineWidth == 0) {
            splineWidth = ((ConstraintLayout.LayoutParams) leftTopLine.getLayoutParams()).width;
        }

        ((ConstraintLayout.LayoutParams) leftTopLine.getLayoutParams()).leftMargin = leftMargin;
        ((ConstraintLayout.LayoutParams) leftBottomLine.getLayoutParams()).leftMargin = leftMargin;

        // 父级分割竖线
        leftLineLay.removeAllViews();


        if (treeNode.getLevel() >= 2) {
            // 从第二级目录开始
            // 需要添加的父级分割线个数
            int count = treeNode.getLevel() - 1;
            for (int i = 0; i < count; i++) {
                LinearLayout.LayoutParams params;
                if (i == 0) {
                    params = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
//                    params.leftMargin = imgWidth / 2;
                    params.leftMargin = treeNode.getFirstWidth() / 2;
                } else {
                    params = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
//                    params.leftMargin = ((ConstraintLayout.LayoutParams) pointImg.getLayoutParams()).width;
                    params.leftMargin = spaceLineWidth + imgWidth / 2;
                }
                View line = new View(mContext);
                line.setLayoutParams(params);
                line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tree_node_color));
                leftLineLay.addView(line);

                TreeNode curParentTreeNode = getParentTreeNode(treeNode, i);
                if (curParentTreeNode != null) {
                    // 设置线是否显示
                    line.setVisibility(curParentTreeNode.isLastChild() ? View.INVISIBLE : View.VISIBLE);
                }
            }
        }

        // 设置是否是最后一级的左边下面的指示线是否显示(是同级的最后一条不显示)
        leftBottomLine.setVisibility(treeNode.isLastChild() ? View.INVISIBLE : View.VISIBLE);

        // 设置是否显示展开子item的线
        if (treeNode.getChildren().isEmpty()) {
            // 如果没有子item了就直接不显示子item展开的线了
            expandLine.setVisibility(View.INVISIBLE);
        } else {
            expandLine.setVisibility(isDefaultExpand ? View.VISIBLE : View.INVISIBLE);
            expandLine.setVisibility(treeNode.isExpanded() ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public void onItemClick(TreeNode treeNode) {
        if (treeNode.getChildren().isEmpty()) {
            // 没有子item了
            expandLine.setVisibility(View.INVISIBLE);
            switch (treeNode.getType()) {
                case TreeNode.TYPE_SYSTEM:
                    // 体系
                    SystemDetailsActivity.startActivity(mContext, treeNode.getResId(), String.valueOf(treeNode.getValue())," Text");
                    break;
                case TreeNode.TYPE_SCHEME:
                    // 规划
                    SchemeDetailActivity.startActivity(mContext, treeNode.getResId(), String.valueOf(treeNode.getValue()));
                    break;
                case TreeNode.TYPE_TARGET:
                    // 目标
                    TargetDetailActivity.startActivity(mContext, treeNode.getResId(), String.valueOf(treeNode.getValue()));
                    break;
                case TreeNode.TYPE_PLAN:
                    // 计划
                    PlanDetailActivity.startActivity(mContext, treeNode.getResId(), String.valueOf(treeNode.getValue()));
                    break;
                case TreeNode.TYPE_ACTIVE:
                    // 活动
                    break;
                default:
                    break;
            }
        } else {
            // 还有子item
            expandLine.setVisibility(treeNode.isExpanded() ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /**
     * 查找指定层级的父级对象
     *
     * @param treeNode
     * @param curLine
     * @return
     */
    private TreeNode getParentTreeNode(TreeNode treeNode, int curLine) {
        if (treeNode == null) return null;
        // 需要画的父级线的条数
        int lineCount = treeNode.getLevel() - 1;
        // 需要向上找的父级层级数
        int tierCount = lineCount - curLine;

        int time = 0;

        TreeNode parentTreeNode = null;
        for (int i = 0; i < tierCount; i++) {
            if (parentTreeNode == null) {
                parentTreeNode = treeNode.getParent();
            } else {
                parentTreeNode = parentTreeNode.getParent();
            }
            time++;
            if (time == tierCount) {
                // 找到了需要判断的父级对象
                return parentTreeNode;
            }
        }
        return null;
    }

}
