package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.SmallAimsBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/19.
 */

public class SystemDetailsAdapter extends BaseAdapter {
    private List<SmallAimsBean> list;
    private Context context;

    public SystemDetailsAdapter(List<SmallAimsBean> list, Context context) {
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
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_system_details, null);
            holder.tvName = (TextView) convertView.findViewById(R.id.small_aims_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(list.get(position).getSmallAnimsName());
        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
    }
}
