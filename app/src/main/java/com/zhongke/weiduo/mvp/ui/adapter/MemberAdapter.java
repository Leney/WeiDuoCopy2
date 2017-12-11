package com.zhongke.weiduo.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 自适应流式标签Adapter
 * Created by llj on 2017/6/26.
 */

public class MemberAdapter extends BaseAdapter {
    private final List<String> mDataList;
    private OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public MemberAdapter() {
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.adapter_member_item, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.member_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String item = (String) getItem(position);
        PhotoLoaderUtil.display(parent.getContext(), holder.iv, item, R.drawable.ic_default_user);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener != null) {
                    onItemListener.onItem(position);
                }
            }
        });
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

    public interface OnItemListener {
        void onItem(int position);
    }

    static class ViewHolder {
        ImageView iv;
    }
}
