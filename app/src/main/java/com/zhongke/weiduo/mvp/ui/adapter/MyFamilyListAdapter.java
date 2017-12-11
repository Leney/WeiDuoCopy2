package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;

import java.util.List;

/**
 * Created by dgg1 on 2017/12/7.
 */

public class MyFamilyListAdapter extends BaseAdapter {
    private Context context;
    private List<ContactsListBean> list;

    public MyFamilyListAdapter(Context context, List<ContactsListBean> list) {
        this.context = context;
        this.list = list;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_family_list, null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position).getNickName());
        return convertView;
    }

    static class ViewHolder {
        TextView tv;
    }
}
