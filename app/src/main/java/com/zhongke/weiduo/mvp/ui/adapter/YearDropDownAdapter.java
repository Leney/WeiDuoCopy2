package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;

import java.util.List;

/**
 * Created by hyx on 2017/10/12.
 */

public class YearDropDownAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> yearList;


    public YearDropDownAdapter(Context context,List<String> list) {
        mContext = context;
        yearList = list;
    }
    @Override
    public int getCount() {
        if (yearList.size() > 0) {
            return yearList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        yearHolder holder = null;
        if (convertView == null) {
            holder = new yearHolder();
            convertView = View.inflate(mContext, R.layout.item_year_dropdown,null);
            holder.yearTxt = (TextView) convertView.findViewById(R.id.year_text);

            convertView.setTag(holder);
        } else {
            holder = (yearHolder) convertView.getTag();
        }
        holder.yearTxt.setText(yearList.get(position));

        return convertView;
    }


     private class yearHolder {
        TextView yearTxt;

        public yearHolder() {

        }
    }
}
