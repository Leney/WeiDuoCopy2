package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.MonitorEquipmentBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/20.
 */

public class MonitorEquipmentAdapter extends BaseAdapter {
    private List<MonitorEquipmentBean> monitorEquipmentBean;
    private Context context;

    public MonitorEquipmentAdapter(List<MonitorEquipmentBean> monitorEquipmentBean, Context context) {
        this.monitorEquipmentBean = monitorEquipmentBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return monitorEquipmentBean.size();
    }

    @Override
    public Object getItem(int position) {
        return monitorEquipmentBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        MonitorEquipmentBean monitorEquipmentBean = (MonitorEquipmentBean) getItem(position);
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.monitor_item_equipment, null);
            viewHolder.tvEquipmentName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.date_date);
            viewHolder.tvDateValue = (TextView) convertView.findViewById(R.id.date_value);
            viewHolder.tvAngle = (TextView) convertView.findViewById(R.id.angle_angle);
            viewHolder.tvAngleValue = (TextView) convertView.findViewById(R.id.angle_value);
            viewHolder.flOff = (FrameLayout) convertView.findViewById(R.id.fl_off);
            viewHolder.flOn = (FrameLayout) convertView.findViewById(R.id.fl_on);
            viewHolder.rlClick = (RelativeLayout) convertView.findViewById(R.id.rl_click);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if ("麦克风".equals(monitorEquipmentBean.getMonitorEquipmentName())) {
            viewHolder.tvEquipmentName.setText(monitorEquipmentBean.getMonitorEquipmentName());
            viewHolder.tvDate.setVisibility(View.INVISIBLE);
            viewHolder.tvDateValue.setVisibility(View.INVISIBLE);
            viewHolder.tvAngle.setVisibility(View.INVISIBLE);
            viewHolder.tvAngleValue.setText("请选择时间");
        } else {
            viewHolder.tvEquipmentName.setText(monitorEquipmentBean.getMonitorEquipmentName());
            viewHolder.tvDateValue.setText(monitorEquipmentBean.getMonitorEquipmentDate());
            viewHolder.tvAngleValue.setText(monitorEquipmentBean.getMonitorEquipmentAngle());
        }
        if (monitorEquipmentBean.isCheck()) {
            viewHolder.flOn.setVisibility(View.VISIBLE);
            viewHolder.flOff.setVisibility(View.GONE);
            viewHolder.tvDate.setTextColor(context.getResources().getColor(R.color.positionAddressColor));
            viewHolder.tvDateValue.setTextColor(context.getResources().getColor(R.color.positionAddressColor));
            viewHolder.tvAngle.setTextColor(context.getResources().getColor(R.color.positionAddressColor));
            viewHolder.tvAngleValue.setTextColor(context.getResources().getColor(R.color.positionAddressColor));
        } else {
            viewHolder.flOff.setVisibility(View.VISIBLE);
            viewHolder.flOn.setVisibility(View.GONE);
            viewHolder.tvDate.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.tvDateValue.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.tvAngle.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.tvAngleValue.setTextColor(context.getResources().getColor(R.color.black));
        }
        viewHolder.rlClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monitorEquipmentBean.isCheck()) {
                    monitorEquipmentBean.setCheck(false);
                    notifyDataSetChanged();
                } else {
                    monitorEquipmentBean.setCheck(true);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tvEquipmentName, tvDate, tvDateValue, tvAngle, tvAngleValue;
        FrameLayout flOn, flOff;
        RelativeLayout rlClick;
    }
}
