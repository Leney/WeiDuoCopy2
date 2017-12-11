package com.zhongke.weiduo.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.RecommendActiveBean;

import java.util.List;

/**
 * 活动GridView Adapter
 * Created by llj on 2017/9/16.
 */

public class PlanDetailAdapter extends BaseAdapter {
    private List<RecommendActiveBean.ActionBean> list;

    public PlanDetailAdapter(List<RecommendActiveBean.ActionBean> list) {
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
        HolderView holderView = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_scheme_detial_item, null);
            holderView = new HolderView();
            holderView.init(convertView);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }

        RecommendActiveBean.ActionBean bean = (RecommendActiveBean.ActionBean) getItem(position);
        PhotoLoaderUtil.display(parent.getContext(), holderView.icon, bean.getCoverUrl(), R.mipmap.default_image);
        holderView.name.setText(bean.getTitle());
//        holderView.price.setText(bean.getPrice() == 0f ? parent.getResources().getString(R.string.free) : String.format(parent.getResources().getString(R.string.format_price), bean.getPrice() + ""));
        holderView.userCount.setText(String.format(parent.getResources().getString(R.string.format_use_count), bean.getJoinCount() + ""));
        return convertView;
    }

    class HolderView {
        ImageView icon;
        TextView name, price, userCount;

        public void init(View baseView) {
            icon = (ImageView) baseView.findViewById(R.id.scheme_adapter_icon);
            name = (TextView) baseView.findViewById(R.id.scheme_adapter_name);
            price = (TextView) baseView.findViewById(R.id.scheme_adapter_price);
            userCount = (TextView) baseView.findViewById(R.id.scheme_adapter_use_count);
        }
    }
}
