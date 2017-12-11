package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.NewGroupMemberBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/8/16.
 */

public class GroupDataAdapter extends BaseAdapter {
    private Context context;
    private List<NewGroupMemberBean.MemberListBean> friendsBeans;

    public GroupDataAdapter(Context context, List<NewGroupMemberBean.MemberListBean> friendsBeans) {
        this.context = context;
        this.friendsBeans = friendsBeans;
    }

    @Override
    public int getCount() {
        return friendsBeans.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return friendsBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_data, null);
            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv1);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position < friendsBeans.size()) {
            NewGroupMemberBean.MemberListBean friendsBean = (NewGroupMemberBean.MemberListBean) getItem(position);
            viewHolder.tvName.setText(friendsBean.getNickName());
            Glide.with(context).load(friendsBean.getHeadImageUrl()).into(viewHolder.ivPhoto);
        } else {
            viewHolder.ivPhoto.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.add_btn));
            viewHolder.tvName.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView ivPhoto;
        TextView tvName;
    }
}
