package com.zhongke.weiduo.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyList;

import java.util.List;

/**
 * 家庭列表adapter
 * Created by llj on 2017/6/23.
 */

public class FamilyListByTagAdapter extends BaseAdapter {
    private List<NewFamilyList.FamilyListBean> list;

    public FamilyListByTagAdapter(List<NewFamilyList.FamilyListBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HolderView holderView;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.adapter_family_item, null);
            holderView = new HolderView();
            holderView.icon = (ImageView) view.findViewById(R.id.add_family_adapter_icon);
            holderView.name = (TextView) view.findViewById(R.id.add_family_adapter_name);
            holderView.distance = (TextView) view.findViewById(R.id.add_family_adapter_distance);
            view.setTag(holderView);
        } else {
            holderView = (HolderView) view.getTag();
        }

        NewFamilyList.FamilyListBean info = (NewFamilyList.FamilyListBean) getItem(i);
        holderView.name.setText(info.getGName());
        holderView.distance.setVisibility(View.GONE);
        PhotoLoaderUtil.display(viewGroup.getContext(), holderView.icon, info.getGCoverUrl(), R.drawable.ic_default_user);
        return view;
    }

    private class HolderView {
        ImageView icon;
        TextView name, distance;
    }

    public void myNotifyDataSetChanged(List<NewFamilyList.FamilyListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
