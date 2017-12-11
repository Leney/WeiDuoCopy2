package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;

import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.ActivityRewardBean;

import java.util.List;

/**
 * Created by ${xingen} on 2017/7/1.
 */

public class ActivityRewardAdapter extends BaseExpandableListAdapter {
    private List<ActivityRewardBean.AwardListBean> awardList;
    private Context context;

    public ActivityRewardAdapter(Context context, List<ActivityRewardBean.AwardListBean> awardList) {
        this.context = context;
        this.awardList = awardList;
    }

    @Override
    public int getGroupCount() {
        return awardList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return awardList.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return awardList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return awardList.get(groupPosition).getList().get(childPosition);
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
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHodler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.activityresult_listview_item1, null);
            groupViewHodler = new GroupViewHolder();
            groupViewHodler.textView = (TextView) convertView.findViewById(R.id.activityresult_item_name_tv);
            convertView.setTag(groupViewHodler);
        } else {
            groupViewHodler = (GroupViewHolder) convertView.getTag();
        }
        groupViewHodler.textView.setText(awardList.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.activityprizeshow_item, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.gridView = (GridView) convertView.findViewById(R.id.activityprizeshow_item_gridView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.gridView.setAdapter(new ActivityPrizeAdapter(context));
        return convertView;
    }


    private static class GroupViewHolder {
        public TextView textView;
    }
    private static class ChildViewHolder {
        GridView gridView;
    }
}
