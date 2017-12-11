package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.TaskListBean;
import com.zhongke.weiduo.mvp.ui.widget.view.DotLineView;
import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.TextViewUtils;
import com.zhongke.weiduo.util.TimeUtils;
import com.zhongke.weiduo.util.ViewIdUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by ${xingen} on 2017/6/20.
 * 孩子的任务
 */

public class MicroRudderTaskListAdapter extends BaseAdapter {
    private Context context;
    private List<TaskListBean.ChildTaskBean> list;
    public MicroRudderTaskListAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.microrudder_tasklist_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageLayout = (RelativeLayout) convertView.findViewById(R.id.tasklist_item_imagelist);
            viewHolder.able_tv = (TextView) convertView.findViewById(R.id.tasklist_item_able_tv);
            viewHolder.name_tv = (TextView) convertView.findViewById(R.id.tasklist_item_name_tv);
            viewHolder.title_tv = (TextView) convertView.findViewById(R.id.tasklist_item_title_tv);
            viewHolder.address_tv = (TextView) convertView.findViewById(R.id.tasklist_item_address_tv);
            viewHolder.tip_tv = (TextView) convertView.findViewById(R.id.tasklist_item_tip_tv);
            viewHolder.pass_tv = (TextView) convertView.findViewById(R.id.tasklist_item_pass_tv);
            viewHolder.project_tv = (TextView) convertView.findViewById(R.id.tasklist_item_project_tv);
            viewHolder.dotLineView = (DotLineView) convertView.findViewById(R.id.tasklist_item_dotlineview);
            viewHolder.bg_layout = (LinearLayout) convertView.findViewById(R.id.tasklist_item_bg_layout);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setDataShow(viewHolder, position);
        viewHolder.bg_layout.setOnClickListener(view -> {
            if (onItemClickChangeListener != null){
                onItemClickChangeListener.itemClick(position);
            }
        });
        return convertView;
    }
    /**
     * 数据加载
     *
     * @param viewHolder
     */
    private void setDataShow(ViewHolder viewHolder, int position) {
        TaskListBean.ChildTaskBean actionBean = list.get(position);
        //设置字体颜色
        int greenColor = ColorUtils.stringToColor("#1cbf61");
        viewHolder.able_tv.setTextColor(greenColor);
        viewHolder.project_tv.setTextColor(greenColor);

        //设置TextView四周的图片
        TextViewUtils.setCompoundDrawables(viewHolder.able_tv, R.drawable.microrudder_able1, 0, 0, 0);
        TextViewUtils.setCompoundDrawables(viewHolder.project_tv, R.drawable.microrudder_project1, 0, 0, 0);
        TextViewUtils.setCompoundDrawables(viewHolder.name_tv, R.drawable.microrudder_name1, 0, 0, 0);
        TextViewUtils.setCompoundDrawables(viewHolder.address_tv, R.drawable.microrudder_address1, 0, 0, 0);
        TextViewUtils.setCompoundDrawables(viewHolder.pass_tv, R.drawable.microrudder_pass1, 0, 0, 0);

        int imageViewWith = DisplayUtils.dip2px(context, 40);
        int imageViewHeight = imageViewWith;
        int[] imageViewIds = new int[4];
        //添加圆形图片列表：
        viewHolder.imageLayout.removeAllViews();
        for (int i = 0; i < 4; ++i) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageViewWith, imageViewHeight);
            imageViewIds[i] = ViewIdUtils.getViewId();
            imageView.setId(imageViewIds[i]);
            if (i == 0) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            } else {
                layoutParams.addRule(RelativeLayout.LEFT_OF, imageViewIds[i - 1]);
                layoutParams.rightMargin = -DisplayUtils.dip2px(context, 10);
            }
            imageView.setLayoutParams(layoutParams);
            PhotoLoaderUtil.displayRoundImage(context, imageView, R.drawable.ic_default_user_circle);

            viewHolder.imageLayout.addView(imageView);
        }
        //动态计算显示图片布局的长度。
        ((RelativeLayout.LayoutParams) viewHolder.imageLayout.getLayoutParams()).width = DisplayUtils.dip2px(context, (120 + (imageViewIds.length - 1) * 90) / 3);
        //设置图片对应人数
        int[] colors = {ColorUtils.stringToColor("#999999"), ColorUtils.stringToColor("#ff3f3f"), ColorUtils.stringToColor("#999999")};
        viewHolder.address_tv.setText(actionBean.getAddress());
        String[] texts = {"共有", String.valueOf(actionBean.getPlanCount()), "人助威中"};
        SpannableStringBuilder spannableStringBuilder = TextViewUtils.setColorPan(colors, texts);
        viewHolder.tip_tv.setText(spannableStringBuilder);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(actionBean.getActionName());
        stringBuilder.append("[");
        stringBuilder.append("约");
        stringBuilder.append(TimeUtils.reduceTime(actionBean.getBeginTime(), actionBean.getEndTime()));
        stringBuilder.append("]");
        viewHolder.title_tv.setText(stringBuilder);
        //开始时间小于当前时间
        if (TimeUtils.compareTime(actionBean.getBeginTime(), TimeUtils.getTodayTime(TimeUtils.TIME_FORMAT))) {
            //结束时间小于当前时间
            if (TimeUtils.compareTime(actionBean.getEndTime(), TimeUtils.getTodayTime(TimeUtils.TIME_FORMAT))) {
                viewHolder.ivIcon.setVisibility(View.GONE);
                viewHolder.dotLineView.setData(false, getSpecifiedTime(actionBean.getEndTime()));
            } else {
                viewHolder.ivIcon.setVisibility(View.VISIBLE);
                viewHolder.dotLineView.setData(true, getSpecifiedTime(actionBean.getEndTime()), false);
            }
        } else {
            viewHolder.ivIcon.setVisibility(View.GONE);
            viewHolder.dotLineView.setData(true, getSpecifiedTime(actionBean.getEndTime()), false);
        }
    }

    /**
     * 指定时，分字段
     *
     * @param time
     * @return
     */
    private String getSpecifiedTime(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.TIME_FORMAT);
        String s = null;
        try {
            Date date = simpleDateFormat.parse(time);
            simpleDateFormat.applyPattern("HH:mm");
            s = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    private static class ViewHolder {
        public RelativeLayout imageLayout;
        public LinearLayout bg_layout;
        public TextView able_tv, name_tv, address_tv, tip_tv, project_tv, pass_tv, title_tv;
        public DotLineView dotLineView;
        public ImageView ivIcon;
    }
    /**
     * 改变数据源
     * @param actionBeanList
     */
    public void changData(List<TaskListBean.ChildTaskBean> actionBeanList) {
        this.list.clear();
        this.list.addAll(actionBeanList);
        this.notifyDataSetChanged();
    }
    public List<TaskListBean.ChildTaskBean> getTaskList() {
        return list;
    }
    private OnItemClickChangeListener onItemClickChangeListener;
    public void setOnItemClickChangeListener(OnItemClickChangeListener onItemClickChangeListener) {
        this.onItemClickChangeListener = onItemClickChangeListener;
    }
    /**
     * Item的点击事件
     */
    public interface OnItemClickChangeListener {
        void itemClick(int position);
    }
}
