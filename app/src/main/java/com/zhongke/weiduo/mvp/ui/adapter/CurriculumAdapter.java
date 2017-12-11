package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.CurriculumBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/27.
 */

public class CurriculumAdapter extends BaseAdapter {
    private List<CurriculumBean> curriculumBeenList;
    private Context context;

    public CurriculumAdapter(List<CurriculumBean> curriculumBeenList, Context context) {
        this.curriculumBeenList = curriculumBeenList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return curriculumBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return curriculumBeenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.curriculum_item, null);
            viewHolder.tvNameCurriculum = (TextView) convertView.findViewById(R.id.tv_name_curriculum);
            viewHolder.tvStage = (TextView) convertView.findViewById(R.id.tv_stage);
            viewHolder.tvCurriculumDate = (TextView) convertView.findViewById(R.id.tv_curriculum_date);
            viewHolder.tvCurriculumBaoming = (TextView) convertView.findViewById(R.id.tv_curriculum_baoming);
            viewHolder.ivPictureCurriculum = (ImageView) convertView.findViewById(R.id.iv_picture_curriculum);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CurriculumBean curriculumBean = (CurriculumBean) getItem(position);
        viewHolder.tvNameCurriculum.setText(curriculumBean.getName());
        viewHolder.tvCurriculumBaoming.setText(curriculumBean.getSignUp());
        viewHolder.tvCurriculumDate.setText(curriculumBean.getDate());
        viewHolder.tvStage.setText(curriculumBean.getStage());
//        viewHolder.ivPictureCurriculum.setImageBitmap(curriculumBean.getBitmap());
        return convertView;
    }

    static class ViewHolder {
        TextView tvNameCurriculum, tvStage, tvCurriculumDate, tvCurriculumBaoming;
        ImageView ivPictureCurriculum;
    }
}
