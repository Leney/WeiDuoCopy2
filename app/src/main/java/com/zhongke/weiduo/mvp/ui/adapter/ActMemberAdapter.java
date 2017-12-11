package com.zhongke.weiduo.mvp.ui.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.MemberInfo;

import java.util.List;

/**
 * 活动参与人员列表Adapter
 * Created by llj on 2017/6/26.
 */

public class ActMemberAdapter extends BaseAdapter {
    private final List<MemberInfo> urls;

    public ActMemberAdapter(List<MemberInfo> list) {
        this.urls = list;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_act_member_item, null);
            holderView = new HolderView();
            holderView.itemLay = (LinearLayout) convertView.findViewById(R.id.act_members_adapter_lay);
            holderView.icon = (ImageView) convertView.findViewById(R.id.act_members_adapter_icon);
            holderView.name = (TextView) convertView.findViewById(R.id.act_members_adapter_name);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        MemberInfo item = (MemberInfo) getItem(position);
        if (item.getId() != -1) {
            // 正常成员数据信息
            PhotoLoaderUtil.display(parent.getContext(), holderView.icon, item.getIcon(), R.drawable.ic_default_user);
            holderView.itemLay.setOnClickListener(null);
        } else {
            // 最后一个新增按钮
            holderView.icon.setImageResource(R.drawable.add_btn);
            holderView.itemLay.setOnClickListener(addMemberListener);
        }
        holderView.name.setText(item.getName());
        return convertView;
    }


    private class HolderView {
        LinearLayout itemLay;
        ImageView icon;
        TextView name;
    }

    private View.OnClickListener addMemberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 添加成员
            Log.i("llj", "添加成员按钮点击");
        }
    };
}
