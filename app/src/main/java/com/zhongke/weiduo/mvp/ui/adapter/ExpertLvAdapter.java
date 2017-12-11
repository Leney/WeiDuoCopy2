package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.StudentEvaluationBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/5.
 */

public class ExpertLvAdapter extends BaseAdapter {
    private List<StudentEvaluationBean> studentEvaluationBeanList;
    private Context context;

    public ExpertLvAdapter(List<StudentEvaluationBean> studentEvaluationBeanList, Context context) {
        this.studentEvaluationBeanList = studentEvaluationBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return studentEvaluationBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentEvaluationBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_or_comment, null);
            holder = new ViewHolder();
            holder.ivPhoto = (ImageView) convertView.findViewById(R.id.comment_imag);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvInfo = (TextView) convertView.findViewById(R.id.tv);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
            holder.rb = (RatingBar) convertView.findViewById(R.id.rb);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        StudentEvaluationBean studentEvaluationBean = studentEvaluationBeanList.get(position);
        holder.tvName.setText(studentEvaluationBean.getStudentName());
        holder.tvInfo.setText(studentEvaluationBean.getEvaluationText());
        holder.rb.setRating(studentEvaluationBean.getEvaluationLevel());
        Glide.with(context).load(studentEvaluationBean.getStudentPhoto()).into(holder.ivPhoto);
        return convertView;
    }

    static class ViewHolder {
        private ImageView ivPhoto;
        private TextView tvName, tvInfo, tvDate;
        private RatingBar rb;
    }
}
