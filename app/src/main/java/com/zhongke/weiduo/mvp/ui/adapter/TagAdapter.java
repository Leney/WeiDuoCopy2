package com.zhongke.weiduo.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自适应流式标签Adapter
 * Created by llj on 2017/6/26.
 */

public class TagAdapter extends BaseAdapter {
    private final List<String> mDataList;

    public TagAdapter() {
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
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
            convertView = View.inflate(parent.getContext(), R.layout.adapter_tag_item, null);
            holder.tv = (TextView) convertView.findViewById(R.id.tag_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String bean = (String) getItem(position);
        holder.tv.setText(bean);
        return convertView;
    }

    public void onlyAddAll(List<String> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<String> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    static class ViewHolder {
        TextView tv;
    }
}
