package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.ActivityBehaviorBean;

import java.util.List;

/**
 * Created by hyx on 2017/12/2.
 */

public class BehaviorAdapter extends BaseAdapter {

    private Context context;
    private List<ActivityBehaviorBean.BehaviorBean> list;
    public BehaviorAdapter(Context context,List<ActivityBehaviorBean.BehaviorBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        if (list!= null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_activity_comment,null);
            vh.behaviorName = (TextView) convertView.findViewById(R.id.item_comment_behavior_name);
            vh.ratingBar = (RatingBar) convertView.findViewById(R.id.item_comment_rating_bar);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ActivityBehaviorBean.BehaviorBean bean = list.get(position);
        vh.behaviorName.setText(bean.getBehaviorName()+" : ");
        return convertView;
    }

    private class ViewHolder {
        TextView behaviorName;
        RatingBar ratingBar;
    }
}
