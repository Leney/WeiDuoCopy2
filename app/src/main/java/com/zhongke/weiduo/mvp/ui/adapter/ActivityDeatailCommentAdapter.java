package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by hyx on 2017/10/24.
 * 活动详情的活动评论
 *
 *    SchemeCommentAdapter
 */

public class ActivityDeatailCommentAdapter extends BaseAdapter {

    private Context context;
    private LinearLayout.LayoutParams layoutParams;
    private boolean the_once = true;

    public ActivityDeatailCommentAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HolderView holderView;
        if (convertView == null) {
            holderView = new HolderView();
            convertView = View.inflate(context,R.layout.adapter_scheme_comment_item, null);

            holderView.userIcon = (ImageView) convertView.findViewById(R.id.scheme_comment_adapter_icon);
            holderView.userName = (TextView) convertView.findViewById(R.id.scheme_comment_adapter_name);
            holderView.userLevelLay = (LinearLayout) convertView.findViewById(R.id.scheme_comment_adapter_level_lay);
            holderView.time = (TextView) convertView.findViewById(R.id.scheme_comment_adapter_time);
            holderView.detail = (TextView) convertView.findViewById(R.id.scheme_comment_adapter_detail);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        holderView.userIcon.setImageResource(R.drawable.activity_detail_comment_head);

        for (int i = 0;i < 5;i++) {
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(DisplayUtils.dip2px(context,14),DisplayUtils.dip2px(context,14));
                layoutParams.rightMargin = DisplayUtils.dip2px(context,5);
                layoutParams.gravity = Gravity.BOTTOM;
            }
            ImageView levelIcon = new ImageView(context);
            levelIcon.setImageResource(R.mipmap.user_level);
            holderView.userLevelLay.addView(levelIcon,layoutParams);

            if (i == 4 && the_once) {
                holderView.userLevelLay.removeAllViews();
                the_once = false;
            }
        }
        return convertView;
    }

    private class HolderView {
        ImageView userIcon;
        TextView userName;
        LinearLayout userLevelLay;
        TextView time;
        TextView detail;

        public HolderView() {

        }
    }
}
