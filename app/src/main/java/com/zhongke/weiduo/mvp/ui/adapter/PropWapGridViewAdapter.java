package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.DetailChildbean;

import java.util.List;

/**
 * Created by hyx on 2017/10/24.
 */

public class PropWapGridViewAdapter extends BaseAdapter {

    private static final String TAG = "PropWapGridViewAdapter";
    private Context context;
    private List<DetailChildbean.Props> propList;
    public PropWapGridViewAdapter(Context context, List<DetailChildbean.Props> list) {
        this.context = context;
        this.propList = list;
    }
    @Override
    public int getCount() {
        if (propList!=null) {

            return propList.size();
        }
        return 0;
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
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(context, R.layout.item_prop_gridview,null);
            holder.textView = (TextView) convertView.findViewById(R.id.item_prop_text);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.textView.setText(propList.get(position).propsName);
        //LogUtil.e(TAG,"text--"+ propList.get(position).propsName);
        return convertView;
    }

    private class Holder {
        TextView textView;
        public Holder() {

        }

    }
}
