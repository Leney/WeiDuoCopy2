package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.zhongke.weiduo.R;

/**
 * Created by hyx on 2017/10/26.
 */

public class AwardPrizeListAdapter extends BaseAdapter {

    private Context context;
    private String[] str = {"爸爸","妈妈"};

    public AwardPrizeListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return str.length;
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context,R.layout.item_activity_awrad_prize,null);
            holder.family_name = (TextView) convertView.findViewById(R.id.item_activity_award_name);
            holder.gridView = (GridView) convertView.findViewById(R.id.activity_award_prize_item_gridView);

            convertView.setTag(holder);
        } else {
           holder = (ViewHolder) convertView.getTag();
        }

        holder.family_name.setText(str[position]);
        holder.gridView.setAdapter(new ActivityPrizeAdapter2(context));
        return convertView;
    }

    private static class ViewHolder {
        private TextView family_name;
        private GridView gridView;

        public ViewHolder() {

        }
    }
}
