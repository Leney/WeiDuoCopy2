package com.zhongke.weiduo.mvp.ui.adapter;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.CommentBean;
import com.zhongke.weiduo.util.DisplayUtils;

import java.util.List;

/**
 *
 * Created by llj on 2017/9/18.
 */

public class SchemeCommentAdapter extends BaseAdapter {
    private List<CommentBean> list;
    private LinearLayout.LayoutParams params;

    public SchemeCommentAdapter(List<CommentBean> list) {
        this.list = list;
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
        HolderView holderView;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_scheme_comment_item, null);
            holderView = new HolderView();
            holderView.init(convertView);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        CommentBean bean = (CommentBean) getItem(position);
        PhotoLoaderUtil.display(parent.getContext(), holderView.userIcon, bean.getUserIcon(), null);
        holderView.userName.setText(bean.getUserName());
        holderView.time.setText(bean.getTime());
        holderView.detail.setText(bean.getDetail());
        holderView.userLevelLay.removeAllViews();
        for (int i = 0; i < bean.getUserLevel(); i++) {
            if (params == null) {
                params = new LinearLayout.LayoutParams(DisplayUtils.dip2px(parent.getContext(), 14), DisplayUtils.dip2px(parent.getContext(), 14));
                params.rightMargin = DisplayUtils.dip2px(parent.getContext(),5);
                params.gravity = Gravity.BOTTOM;
            }
            ImageView levelIcon = new ImageView(parent.getContext());
            levelIcon.setImageResource(R.mipmap.user_level);
            holderView.userLevelLay.addView(levelIcon, params);
        }
        return convertView;
    }

    private class HolderView {
        ImageView userIcon;
        TextView userName;
        LinearLayout userLevelLay;
        TextView time;
        TextView detail;

        public void init(View baseView) {
            userIcon = (ImageView) baseView.findViewById(R.id.scheme_comment_adapter_icon);
            userName = (TextView) baseView.findViewById(R.id.scheme_comment_adapter_name);
            userLevelLay = (LinearLayout) baseView.findViewById(R.id.scheme_comment_adapter_level_lay);
            time = (TextView) baseView.findViewById(R.id.scheme_comment_adapter_time);
            detail = (TextView) baseView.findViewById(R.id.scheme_comment_adapter_detail);
        }
    }

}
