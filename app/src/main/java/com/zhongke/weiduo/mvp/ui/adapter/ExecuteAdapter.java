package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.ExecutePeopleBean;

import java.util.List;

/**
 * Created by hyx on 2017/11/30.
 *
 */

public class ExecuteAdapter extends BaseAdapter {

    private Context context;
    private List<ExecutePeopleBean.ListBean>  peopleList;
    public ExecuteAdapter(Context context, List<ExecutePeopleBean.ListBean> list) {
        this.context = context;
        this.peopleList = list;
    }
    @Override
    public int getCount() {
        if (peopleList!= null&& peopleList.size() > 0) {
            return peopleList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return peopleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context,R.layout.item_select_execute_people,null);
            viewHolder.peopleName = (TextView) convertView.findViewById(R.id.select_execute_people);

            convertView.setTag(viewHolder);
        } else {
           viewHolder = (ViewHolder) convertView.getTag();
        }
        if (!TextUtils.isEmpty(peopleList.get(position).getNickName())) {
            viewHolder.peopleName.setText(peopleList.get(position).getNickName());
        } else {
            viewHolder.peopleName.setText(peopleList.get(position).getUserName());
        }
        return convertView;
    }

    private static class ViewHolder{
        TextView peopleName;
    }
}
