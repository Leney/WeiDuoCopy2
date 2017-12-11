package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.ui.activity.ActivityCreateGroupChat;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/8/11.
 */

public class ActivityCreateGroupAdapter extends BaseAdapter {
    private Context context;
    private List<ContactsListBean> friendsBeans;
    private ActivityCreateGroupChat activity;
    //统计选中了多少个成员
    private int checkNum;

    public ActivityCreateGroupAdapter(Context context, List<ContactsListBean> friendsBeans, ActivityCreateGroupChat activity) {
        this.context = context;
        this.friendsBeans = friendsBeans;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return friendsBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return friendsBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_chat, null);
            viewHolder.ivCheck = (ImageView) convertView.findViewById(R.id.iv_check);
            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv_photo);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ContactsListBean friendsBeen = (ContactsListBean) getItem(position);
        viewHolder.tvName.setText(friendsBeen.getNickName());
        //使用glide加载头像
        Glide.with(context).load(friendsBeen.getHeadUrl()).into(viewHolder.ivPhoto);
        if (friendsBeen.isCheck()) {
            viewHolder.ivCheck.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.check));
        } else {
            viewHolder.ivCheck.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.uncheck));
        }
        viewHolder.ivCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (friendsBeen.isCheck()) {
                    friendsBeen.setCheck(false);
                    checkNum--;
                } else {
                    friendsBeen.setCheck(true);
                    checkNum++;
                }
                notifyDataSetChanged();
                activity.setNumber(checkNum);
            }
        });
        return convertView;
    }

    public void myNotifyDataSetChanged(List<ContactsListBean> friendsBeans) {
        this.friendsBeans = friendsBeans;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView ivCheck, ivPhoto;
        TextView tvName;
    }
}
