package com.zhongke.weiduo.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.PlanExecutorInfo;

import java.util.List;

/**
 * 计划执行项的adapter
 * Created by llj on 2017/9/27.
 */

public class PlanExecuteAdapter extends BaseExpandableListAdapter {
    private List<PlanExecutorInfo> groupList;
    private List<List<String>> childList;

    public PlanExecuteAdapter(List<PlanExecutorInfo> groupList, List<List<String>> childList) {
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_plan_execute_group_item, null);
            viewHolder = new GroupViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }

        PlanExecutorInfo info = (PlanExecutorInfo) getGroup(groupPosition);
        viewHolder.name.setText(info.getName());
        viewHolder.time.setText(String.format(parent.getResources().getString(R.string.format_execute_item), info.getDay() + ""));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_plan_execute_child_item, null);
            viewHolder = new ChildViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(childList.get(groupPosition).get(childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class GroupViewHolder {
        TextView name;
        TextView time;

        public void init(View rootView) {
            name = (TextView) rootView.findViewById(R.id.plan_execute_group_name);
            time = (TextView) rootView.findViewById(R.id.plan_execute_group_time);
        }
    }

    private class ChildViewHolder {
        TextView name;

        public void init(View rootView) {
            name = (TextView) rootView.findViewById(R.id.plan_execute_child_name);
        }
    }
}
