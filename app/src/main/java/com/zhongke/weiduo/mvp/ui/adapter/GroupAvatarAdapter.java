package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;

import java.util.List;

/**
 * Created by hyx on 2017/10/10.
 */

public class GroupAvatarAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> data;

    public GroupAvatarAdapter(Context context, List<String> data) {
        mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LabelHolder holder;
        if (convertView == null) {
            holder = new LabelHolder();
            convertView = View.inflate(mContext, R.layout.item_group_introduce_avatar, null);
            holder.avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);

            convertView.setTag(holder);
        } else {
            holder = (LabelHolder) convertView.getTag();
        }
        Glide.with(mContext).load(data.get(position)).into(holder.avatar);
        return convertView;
    }


    class LabelHolder {
        private LabelHolder() {

        }

        ImageView avatar;
    }
}
