package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/30.
 */

public class GroupListAdapter extends BaseAdapter {
    private List<ContactsListBean> list;
    private Context context;

    public GroupListAdapter(List<ContactsListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_list, null);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_photo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ContactsListBean bean = (ContactsListBean) getItem(position);
        holder.tvName.setText(bean.nickName);
        PhotoLoaderUtil.display(parent.getContext(), holder.iv, bean.headUrl, R.mipmap.default_user_icon);

        return convertView;
    }

    static class ViewHolder {
        private TextView tvName;
        private ImageView iv;
    }
}
