package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.AssessmentBean;
import com.zhongke.weiduo.mvp.ui.widget.CustomDialog;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/20.
 */

public class AssessmentAdapter extends BaseAdapter {
    private OnMyItemClickListeners onMyItemClickListeners;
    private List<AssessmentBean> assessmentBeanList;
    private Context context;
    public static final String SCORING_SYSTEM = "评分方式";

    public AssessmentAdapter(Context context, List<AssessmentBean> assessmentBeanList) {
        this.assessmentBeanList = assessmentBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return assessmentBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return assessmentBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        AssessmentBean assessmentBean = assessmentBeanList.get(position);
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.assessment_item, null);
            viewHolder.tvAssessmentName = (TextView) convertView.findViewById(R.id.tv_assessment_name);
            viewHolder.tvAssessmentRule = (TextView) convertView.findViewById(R.id.tv_assessment_value);
            viewHolder.ivDelete = (ImageView) convertView.findViewById(R.id.image_view2);
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rl);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvAssessmentName.setText(assessmentBean.getAssessmentName());
        //还需要处理，坐判断是否为评分方式。如果为评分方式的话显示相对布局，影藏delete。如果不是评分方式显示delete，影藏相对布局。还有点击事件的回调。
        if (assessmentBean.getAssessmentName().equals(SCORING_SYSTEM)) {
            viewHolder.ivDelete.setVisibility(View.GONE);
            viewHolder.relativeLayout.setVisibility(View.VISIBLE);
            viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMyItemClickListeners.clickItem(SCORING_SYSTEM, v);
                }
            });
        } else {
            viewHolder.relativeLayout.setVisibility(View.GONE);
            viewHolder.ivDelete.setVisibility(View.VISIBLE);
            viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMyItemClickListeners.clickItem("", v);
                    final CustomDialog customDialog = new CustomDialog(context);
                    customDialog.setMessage("是否删除此考核要素");
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
                            assessmentBeanList.remove(position);
                            notifyDataSetChanged();
                            customDialog.dismiss();
                        }
                    });
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tvAssessmentName, tvAssessmentRule;
        ImageView ivDelete;
        RelativeLayout relativeLayout;
    }

    public interface OnMyItemClickListeners {
        void clickItem(String str, View v);
    }

    public void setOnMyItemClickListeners(OnMyItemClickListeners onMyItemClickListeners) {
        this.onMyItemClickListeners = onMyItemClickListeners;
    }
}
