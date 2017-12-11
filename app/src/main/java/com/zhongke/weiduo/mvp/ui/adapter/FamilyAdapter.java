package com.zhongke.weiduo.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;

import java.util.List;

/**
 * Created by ${xingen} on 2017/6/23.
 */

public class FamilyAdapter extends BaseExpandableListAdapter {

    private List<String> groupList;
    private List<List<ContactsListBean>> childList;

    public FamilyAdapter(List<String> groupList, List<List<ContactsListBean>> childList) {
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
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.family_expandablelistview_item_group, null);
            groupViewHolder.textView = (TextView) convertView.findViewById(R.id.family_expandablelistview_group_tv);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.textView.setText(groupList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.family_expandablelistview_item_child, null);
            childViewHolder.icon_iv = (ImageView) convertView.findViewById(R.id.family_expandablelistview_child_iv);
//            childViewHolder.message_tv = (TextView) convertView.findViewById(R.id.family_expandablelistview_child_message_iv);
            childViewHolder.textView = (TextView) convertView.findViewById(R.id.family_expandablelistview_child_conent_tv);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
//        childViewHolder.message_tv.setVisibility(groupPosition == 0 ? View.VISIBLE : View.GONE);
        ContactsListBean contact = (ContactsListBean) getChild(groupPosition, childPosition);
        childViewHolder.textView.setText(contact.nickName);
        PhotoLoaderUtil.display(parent.getContext(), childViewHolder.icon_iv, contact.headUrl, R.mipmap.default_user_icon);
        return convertView;
    }

    public static class GroupViewHolder {
        public TextView textView;
    }

    public static class ChildViewHolder {
        public TextView textView;
        public ImageView icon_iv;
    }

}
