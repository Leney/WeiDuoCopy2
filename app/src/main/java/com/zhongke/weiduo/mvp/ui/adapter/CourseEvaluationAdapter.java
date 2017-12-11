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
import com.zhongke.weiduo.mvp.model.entity.CourseEvaluationBean;
import com.zhongke.weiduo.mvp.ui.widget.view.StarBar;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/14.
 */

public class CourseEvaluationAdapter extends BaseAdapter {
    private List<CourseEvaluationBean> list;
    private Context context;

    public CourseEvaluationAdapter(List<CourseEvaluationBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_course_evaluation, parent, false);
            holder.tvName = (TextView) convertView.findViewById(R.id.name_evaluation);
            holder.tvText = (TextView) convertView.findViewById(R.id.text_evaluation);
            holder.ivPhoto = (ImageView) convertView.findViewById(R.id.image_evaluation);
            holder.sb = (StarBar) convertView.findViewById(R.id.sb_evaluation);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CourseEvaluationBean courseEvaluationBean = (CourseEvaluationBean) getItem(position);
        holder.tvName.setText(courseEvaluationBean.getName());
        holder.tvText.setText(courseEvaluationBean.getEvaluationText());
        holder.sb.setStarMark(courseEvaluationBean.getScore());
        Glide.with(context).load(courseEvaluationBean.getPhoto()).into(holder.ivPhoto);
        return convertView;
    }

    static class ViewHolder {
        TextView tvName, tvText;
        StarBar sb;
        ImageView ivPhoto;
    }
}
