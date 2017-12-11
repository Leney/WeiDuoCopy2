package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.TeachContentBean;
import com.zhongke.weiduo.mvp.ui.widget.CustomDialog;
import com.zhongke.weiduo.util.NullStringUtils;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/20.
 */

public class TeachContentAdapter extends BaseExpandableListAdapter {
    private List<TeachContentBean> teachContentBeanList;
    private Context context;

    public TeachContentAdapter(List<TeachContentBean> teachContentBeanList, Context context) {
        this.teachContentBeanList = teachContentBeanList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return teachContentBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return teachContentBeanList.get(groupPosition).getPlayModeBeans().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return teachContentBeanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return teachContentBeanList.get(groupPosition).getPlayModeBeans().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.teach_content_parent_item, null);
        }
        convertView.setTag(R.layout.teach_content_parent_item, groupPosition);
        convertView.setTag(R.layout.teach_content_chlid_item, -1);
        TextView text = (TextView) convertView.findViewById(R.id.tv1);
        ImageView ivDelete = (ImageView) convertView.findViewById(R.id.iv_delete_item);
        text.setText(teachContentBeanList.get(groupPosition).getTeachContentName());
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog customDialog = new CustomDialog(context);
                customDialog.setMessage("是否删除此课程");
                customDialog.setTitle("警告");
                customDialog.show();
                customDialog.setLeftBtnOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customDialog.dismiss();
                        //TODO 删除动作需要等接口
                    }
                });
                customDialog.setRightBtnOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teachContentBeanList.remove(groupPosition);
                        notifyDataSetChanged();
                        customDialog.dismiss();
                    }
                });
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.teach_content_chlid_item, null);
        }
        convertView.setTag(R.layout.teach_content_parent_item, groupPosition);
        convertView.setTag(R.layout.teach_content_chlid_item, childPosition);
        TextView text = (TextView) convertView.findViewById(R.id.tv2);
        TextView tvAngle = (TextView) convertView.findViewById(R.id.tv_angle);
        TextView tvAngleValue = (TextView) convertView.findViewById(R.id.tv_angle_value);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tv_date);
        TextView tvDateValue = (TextView) convertView.findViewById(R.id.tv_date_value);
        ImageView ivDelete = (ImageView) convertView.findViewById(R.id.iv_delete2);
        text.setText(teachContentBeanList.get(groupPosition).getPlayModeBeans().get(childPosition).getPlayPlace());
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog customDialog = new CustomDialog(context);
                customDialog.setMessage("是否删除此播放模式");
                customDialog.setTitle("警告");
                customDialog.show();
                customDialog.setLeftBtnOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO 删除动作需要等接口
                        customDialog.dismiss();
                    }
                });
                customDialog.setRightBtnOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        teachContentBeanList.get(groupPosition).getPlayModeBeans().remove(childPosition);
                        notifyDataSetChanged();
                        customDialog.dismiss();
                    }
                });
            }
        });
        if (!NullStringUtils.isNullStringUtils(teachContentBeanList.get(groupPosition).getPlayModeBeans().get(childPosition).getPlayAngleValue())) {
            tvAngleValue.setText(teachContentBeanList.get(groupPosition).getPlayModeBeans().get(childPosition).getPlayAngleValue());
        } else {
            tvAngleValue.setVisibility(View.GONE);
            tvAngle.setVisibility(View.GONE);
        }
        if (!NullStringUtils.isNullStringUtils(teachContentBeanList.get(groupPosition).getPlayModeBeans().get(childPosition).getPlayAngleDate())) {
            tvDateValue.setText(teachContentBeanList.get(groupPosition).getPlayModeBeans().get(childPosition).getPlayAngleDate());
        } else {
            tvDate.setVisibility(View.GONE);
            tvDateValue.setVisibility(View.GONE);
        }
        return convertView;
    }

    public void myNotifyDataSetChanged(List<TeachContentBean> teachContentBeanList) {
        this.teachContentBeanList = teachContentBeanList;
        notifyDataSetChanged();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
