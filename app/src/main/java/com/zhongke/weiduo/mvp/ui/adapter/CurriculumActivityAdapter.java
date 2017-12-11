package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.mvp.model.entity.CurriculumActivityBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseActionResult;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/28.
 */

public class CurriculumActivityAdapter extends BaseAdapter {
    private List<CourseActionResult.RecordsBean> courActionRecords;
    private Context context;
    private MyOnClickListener listener;

    public CurriculumActivityAdapter(List<CourseActionResult.RecordsBean> courActionRecords, Context context, MyOnClickListener listener) {
        this.courActionRecords = courActionRecords;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
//        LogUtil.e("size---"+courActionRecords.size());
        if (courActionRecords != null && courActionRecords.size()>0) {
            LogUtil.e("size---"+courActionRecords.size());

            return courActionRecords.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return courActionRecords.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.curriculum_activity_item, null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.activity_name);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.activity_address);
            viewHolder.tvCollection = (TextView) convertView.findViewById(R.id.tv_collection);
            viewHolder.tvShare = (TextView) convertView.findViewById(R.id.tv_share);
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.ivCheck = (ImageView) convertView.findViewById(R.id.iv_check);
            viewHolder.ivShare = (ImageView) convertView.findViewById(R.id.iv_share);
            viewHolder.ivCollection = (ImageView) convertView.findViewById(R.id.iv_collection);
            viewHolder.ivPicture = (ImageView) convertView.findViewById(R.id.activity_picture);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CourseActionResult.RecordsBean cb = courActionRecords.get(position);
        viewHolder.tvName.setText(cb.getTitle());
        viewHolder.tvAddress.setText(cb.getAddress());
        if (cb.isCheck()) {
            viewHolder.ivCheck.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.check));
        } else {
            viewHolder.ivCheck.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.uncheck));
        }
        if (cb.getCollectActId() == 1) {
            viewHolder.ivCollection.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.activity_uncollection));
            viewHolder.tvCollection.setText(context.getResources().getString(R.string.check_collection));
        } else {
            viewHolder.ivCollection.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.activity_collection));
            viewHolder.tvCollection.setText(context.getResources().getString(R.string.collection));
        }

        viewHolder.ivCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickListener(position, "xuanzhong");
                notifyDataSetChanged();
            }
        });
        viewHolder.ivCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickListener(position, "shoucang");
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public interface MyOnClickListener {
        void clickListener(int position, String str);
    }

    static class ViewHolder {
        TextView tvName, tvAddress, tvCollection, tvShare, tvNumber;
        ImageView ivPicture, ivCollection, ivShare, ivCheck;
    }
}
