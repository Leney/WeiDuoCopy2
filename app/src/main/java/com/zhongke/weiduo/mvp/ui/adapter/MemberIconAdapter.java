package com.zhongke.weiduo.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;

import java.util.List;

/**
 * 自适应流式标签Adapter
 * Created by llj on 2017/6/26.
 */

public class MemberIconAdapter extends BaseAdapter {
    private final List<String> urls;

    public MemberIconAdapter(List<String> list) {
        this.urls = list;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_member_item, null);
            holderView = new HolderView();
            holderView.icon = (ImageView) convertView.findViewById(R.id.member_icon);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        String item = (String) getItem(position);
        PhotoLoaderUtil.display(holderView.icon, item, parent.getResources().getDrawable(R.mipmap.ic_launcher));
        return convertView;
    }

    private class HolderView {
        ImageView icon;
    }
}
