package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;

import java.util.List;

/**
 * Created by hyx on 2017/10/10.
 */

public class GroupLabelAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> data;

    public GroupLabelAdapter(Context context, List<String> data) {
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
            convertView = View.inflate(mContext, R.layout.item_group_introduce_label, null);
            holder.labelTxt = (TextView) convertView.findViewById(R.id.tv_label);

            convertView.setTag(holder);
        } else {
            holder = (LabelHolder) convertView.getTag();
        }
        holder.labelTxt.setText(data.get(position));
        return convertView;
    }


    class LabelHolder {
        private LabelHolder() {

        }

        TextView labelTxt;
    }
}
