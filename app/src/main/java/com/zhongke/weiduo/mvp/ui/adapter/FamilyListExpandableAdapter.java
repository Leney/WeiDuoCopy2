package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.model.entity.FriendAndFamilyBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/10/10.
 * 家庭列表和好友家庭列表界面的适配器
 */

public class FamilyListExpandableAdapter extends BaseExpandableListAdapter {
    private List<FriendAndFamilyBean> list;
    private Context context;

    public FamilyListExpandableAdapter(List<FriendAndFamilyBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getGroupBeanList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getGroupBeanList().get(childPosition);
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
        if (null == convertView) {
            viewHolder = new GroupViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_family_group, null);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(list.get(groupPosition).getTypeName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_list, null);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_photo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        ContactsListBean bean = (ContactsListBean) getChild(groupPosition, childPosition);
//        viewHolder.tv.setText(list.get(groupPosition).getGroupBeanList().get(childPosition).getBName());
//        Glide.with(context).load(list.get(groupPosition).getGroupBeanList().get(childPosition).getPhotos().get(1)).into(viewHolder.iv);
        viewHolder.tv.setText(bean.nickName);
        PhotoLoaderUtil.display(parent.getContext(), viewHolder.iv, bean.headUrl, R.mipmap.default_user_icon);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        TextView tv;
    }

    static class ChildViewHolder {
        ImageView iv;
        TextView tv;
    }
}
