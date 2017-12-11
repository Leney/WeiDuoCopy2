package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.NewExpertCourseBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/5.
 */

public class ExpertGvAdapter extends BaseAdapter {
    private List<NewExpertCourseBean.ExpertCourseBean> expertCourseBeanList;
    private Context context;

    public ExpertGvAdapter(List<NewExpertCourseBean.ExpertCourseBean> expertCourseBeanList, Context context) {
        this.expertCourseBeanList = expertCourseBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return expertCourseBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return expertCourseBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_or_class, null);
            holder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv1);
            holder.tvType = (TextView) convertView.findViewById(R.id.tv2);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewExpertCourseBean.ExpertCourseBean expertCourseBean = expertCourseBeanList.get(position);
        holder.tvName.setText(expertCourseBean.getCourseName());
//        holder.tvPrice.setText(expertCourseBean.getCoverUrl());
//        if (expertCourseBean.getCourseType() == 0) {
//            holder.tvType.setText("视频课");
//        } else {
//            holder.tvType.setText("直播课");
//        }
        Glide.with(context).load(expertCourseBean.getCoverUrl()).into(holder.ivPhoto);
        return convertView;
    }

    static class ViewHolder {
        ImageView ivPhoto;
        TextView tvName, tvPrice, tvType;
    }
}
