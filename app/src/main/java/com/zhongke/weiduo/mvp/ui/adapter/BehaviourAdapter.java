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
 * Created by hyx on 2017/12/1.
 */

public class BehaviourAdapter extends BaseAdapter {
    private List<DetailChildbean.Behaviour> list;
    private Context context;
    public BehaviourAdapter(Context context,List<DetailChildbean.Behaviour> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        if (list!=null) {
            return list.size();
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
        if (convertView == null) {
            convertView = View.inflate(context,R.layout.item_activity_detail_behaviour,null);

        }
        TextView name = (TextView) convertView.findViewById(R.id.behaviour_behaviour_name);
        name.setText(list.get(position).behaviourName);
        return convertView;
    }
}
