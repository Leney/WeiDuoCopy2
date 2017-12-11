package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.ScheduleBean;
import com.zhongke.weiduo.mvp.model.entity.eventschedule.EventScheduleResult;
import com.zhongke.weiduo.mvp.ui.activity.AppendScheduleActivity;

import java.util.List;

/**
 * Created by Karma on 2017/9/19.
 */

public class ScheduleAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private Context context;
    private List<ScheduleBean> scheduleList;

    private boolean isToday;
    private boolean isTomorrow;

    public ScheduleAdapter(Context context, List<ScheduleBean> scheduleDateList) {
        this.context = context;
        this.scheduleList = scheduleDateList;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    public boolean isTomorrow() {
        return isTomorrow;
    }

    public void setTomorrow(boolean tomorrow) {
        isTomorrow = tomorrow;
    }

    @Override
    public int getGroupCount() {
        if (scheduleList !=null&& scheduleList.size()>0) {
            return scheduleList.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (scheduleList.get(groupPosition).getScheduleChildList()!=null
                && scheduleList.get(groupPosition).getScheduleChildList().size()>0) {
            return scheduleList.get(groupPosition).getScheduleChildList().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return scheduleList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return scheduleList.get(groupPosition).getScheduleChildList().get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder=null;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = View.inflate(context, R.layout.item_schedule_group,null);
            groupHolder.month = (TextView) convertView.findViewById(R.id.schedule_month);
            groupHolder.day = (TextView) convertView.findViewById(R.id.schedule_day);
            groupHolder.weekDay = (TextView) convertView.findViewById(R.id.schedule_week_day);
            groupHolder.tip = (TextView) convertView.findViewById(R.id.schedule_tips_text);

            convertView.setTag(groupHolder);
        } else{
            groupHolder = (GroupHolder) convertView.getTag();
        }

        if (scheduleList != null) {
            groupHolder.month.setText(scheduleList.get(groupPosition).getMonth()+"月");
            groupHolder.day.setText(scheduleList.get(groupPosition).getDayNumber()+"日");
            groupHolder.weekDay.setText(scheduleList.get(groupPosition).getDayOfWeek());
        }

        if(isToday()) {
            groupHolder.tip.setVisibility(View.VISIBLE);

        } else if (isTomorrow()) {
            groupHolder.tip.setVisibility(View.VISIBLE);
            groupHolder.tip.setText("明天");
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = View.inflate(context, R.layout.item_schedule_child,null);
            childHolder.behavior = (TextView) convertView.findViewById(R.id.tv_behavior);
            childHolder.time = (TextView) convertView.findViewById(R.id.tv_time);

            childHolder.startDateTime = (TextView) convertView.findViewById(R.id.tv_start_date_time);
            childHolder.endDateTime = (TextView) convertView.findViewById(R.id.tv_end_date_time);
            convertView.setTag(childHolder);
        } else{
            childHolder = (ChildHolder) convertView.getTag();
        }

        EventScheduleResult.RecordsBean recordsBean = scheduleList.get(groupPosition).getScheduleChildList().get(childPosition);
        childHolder.behavior.setText(recordsBean.getActionName());
        String begin_time = recordsBean.getBeginTime();
        String[] arr = begin_time.split(" ");
//        LogUtil.e("arr[1]"+ arr[1]);
        String beginTime = arr[1].substring(0,5);
//        LogUtil.e("beginTime--"+ beginTime);
        childHolder.time.setText(beginTime);
        childHolder.startDateTime.setText(begin_time);
        childHolder.endDateTime.setText(recordsBean.getEndTime());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;
        }
    }

    private static class GroupHolder {
        TextView month,day,weekDay,tip;
    }

    private static class ChildHolder {
        TextView behavior,time,startDateTime,endDateTime;
    }
}
