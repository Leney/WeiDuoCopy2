package com.zhongke.weiduo.mvp.ui.adapter;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.SchemeDetailBean2;

import java.util.List;

/**
 * 规划详情阶段行为列表Adapter
 * Created by llj on 2017/11/8.
 */

public class SchemeExpandAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;
    private List<List<SchemeDetailBean2.BehaviorItemBean>> childList;

    public SchemeExpandAdapter(List<String> groupList, List<List<SchemeDetailBean2.BehaviorItemBean>> childList) {
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
            convertView = View.inflate(parent.getContext(), R.layout.adpater_scheme_detail_child_group, null);
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
            convertView = View.inflate(parent.getContext(), R.layout.adapter_scheme_detail_child_child, null);
            viewHolder = new ChildViewHolder();
            viewHolder.init(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
//        SchemeActionBean actionBean = (SchemeActionBean) getChild(groupPosition, childPosition);
        SchemeDetailBean2.BehaviorItemBean actionBean = childList.get(groupPosition).get(childPosition);
        viewHolder.name.setText(actionBean.getBehaviorName());
        viewHolder.targetName.setText("[" + actionBean.getTargetName() + "]");
        viewHolder.executeName.setText(actionBean.getDoObject() == 1 ? "孩子" : "父母");
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
            name = (TextView) view.findViewById(R.id.scheme_detail_child_name);
        }
    }

    private class ChildViewHolder {
        ConstraintLayout itemLay;
        TextView name, executeName, targetName;

        public void init(View view) {
            itemLay = (ConstraintLayout) view.findViewById(R.id.scheme_detail_child_child_lay);
            itemLay.setOnClickListener(childItemOnClickListener);
            name = (TextView) view.findViewById(R.id.scheme_detail_child_child_name);
            // 执行人
            executeName = (TextView) view.findViewById(R.id.scheme_detail_child_child_execute_name);
            // 目标名称
            targetName = (TextView) view.findViewById(R.id.scheme_detail_child_child_target);
        }
    }


    /**
     * 子item点击事件
     */
    private View.OnClickListener childItemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SchemeDetailBean2.BehaviorItemBean bean = (SchemeDetailBean2.BehaviorItemBean) v.getTag(R.id.scheme_detail_child_child_name);
            // TODO 跳转到行为详情界面
            Toast.makeText(v.getContext(), "跳转到" + bean.getBehaviorName() + "详情", Toast.LENGTH_SHORT).show();
        }
    };
}
