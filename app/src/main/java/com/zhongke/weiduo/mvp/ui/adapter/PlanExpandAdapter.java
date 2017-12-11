package com.zhongke.weiduo.mvp.ui.adapter;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.PlanDetailBean2;

import java.util.List;

/**
 * 规划详情阶段行为列表Adapter
 * Created by llj on 2017/11/8.
 */

public class PlanExpandAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;
    private List<List<PlanDetailBean2.BehaviorItemBean>> childList;

    public PlanExpandAdapter(List<String> groupList, List<List<PlanDetailBean2.BehaviorItemBean>> childList) {
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adpater_plan_detail_child_group, null);
            viewHolder = new GroupViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(groupList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_plan_detail_child_child, null);
            viewHolder = new ChildViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
//        SchemeActionBean actionBean = (SchemeActionBean) getChild(groupPosition, childPosition);
        PlanDetailBean2.BehaviorItemBean actionBean = childList.get(groupPosition).get(childPosition);
        viewHolder.name.setText(actionBean.getBehaviorName());
        viewHolder.targetName.setText("[" + actionBean.getTargetName() + "]");
        viewHolder.executeName.setText(actionBean.getDoObject() == 1 ? "(孩子)" : "(父母)");
        viewHolder.executeTotalDay.setText(actionBean.getDoLongTime() + "天");
        viewHolder.executeFrequency.setText(actionBean.getDoCycle() + "天一次");
        viewHolder.executeFrequency.setVisibility(actionBean.getDoCycle() <= 0 ? View.GONE : View.VISIBLE);
        viewHolder.itemLay.setTag(R.id.scheme_detail_child_child_name, actionBean);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    private class GroupViewHolder {
        TextView name;

        public void init(View view) {
            name = (TextView) view.findViewById(R.id.plan_detail_child_name);
        }
    }

    private class ChildViewHolder {
        ConstraintLayout itemLay;
        TextView name, executeName, targetName;
        /**
         * 执行总天数、执行频率
         */
        TextView executeTotalDay, executeFrequency;

        public void init(View view) {
            itemLay = (ConstraintLayout) view.findViewById(R.id.plan_detail_child_child_lay);
            itemLay.setOnClickListener(childItemOnClickListener);
            name = (TextView) view.findViewById(R.id.plan_detail_child_child_name);
            // 执行人
            executeName = (TextView) view.findViewById(R.id.plan_detail_child_child_execute_name);
            // 目标名称
            targetName = (TextView) view.findViewById(R.id.plan_detail_child_child_target);
            executeTotalDay = (TextView) view.findViewById(R.id.plan_detail_child_child_execute_total_date);
            executeFrequency = (TextView) view.findViewById(R.id.plan_detail_child_child_execute_frequency);
        }
    }


    /**
     * 子item点击事件
     */
    private View.OnClickListener childItemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PlanDetailBean2.BehaviorItemBean bean = (PlanDetailBean2.BehaviorItemBean) v.getTag(R.id.scheme_detail_child_child_name);
            // TODO 跳转到行为详情界面
            Toast.makeText(v.getContext(), "跳转到" + bean.getBehaviorName() + "详情", Toast.LENGTH_SHORT).show();
        }
    };
}
