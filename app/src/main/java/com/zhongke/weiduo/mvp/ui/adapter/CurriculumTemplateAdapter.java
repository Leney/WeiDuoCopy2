package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.CurriculumTemplateBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/7/4.
 */

public class CurriculumTemplateAdapter extends BaseAdapter {
    private List<CurriculumTemplateBean> curriculumTemplates;
    private Context context;

    public CurriculumTemplateAdapter(List<CurriculumTemplateBean> curriculumTemplates, Context context) {
        this.curriculumTemplates = curriculumTemplates;
        this.context = context;
    }

    @Override
    public int getCount() {
        return curriculumTemplates.size();
    }

    @Override
    public Object getItem(int position) {
        return curriculumTemplates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CurriculumTemplateBean curriculumTemplateBean = curriculumTemplates.get(position);
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_footer_layout, null);
            holder.ivPicture = (ImageView) convertView.findViewById(R.id.activity_picture);
            holder.tvName = (TextView) convertView.findViewById(R.id.activity_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(curriculumTemplateBean.getName());
        return convertView;
    }

    static class ViewHolder {
        ImageView ivPicture;
        TextView tvName;
    }
}
