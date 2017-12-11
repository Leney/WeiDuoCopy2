package com.zhongke.weiduo.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.SearchFriendResultBean;

import java.util.List;

/**
 * 搜索结果Adapter
 * Created by llj on 2017/6/23.
 */

public class SearchResultAdapter extends BaseAdapter {
    private List<SearchFriendResultBean.UserListBean> list;

    public SearchResultAdapter(List<SearchFriendResultBean.UserListBean> list) {
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
            view = View.inflate(viewGroup.getContext(), R.layout.adapter_search_result, null);
            holderView = new HolderView();
            holderView.icon = (ImageView) view.findViewById(R.id.search_result_adapter_icon);
            holderView.name = (TextView) view.findViewById(R.id.search_result_adapter_name);
            view.setTag(holderView);
        } else {
            holderView = (HolderView) view.getTag();
        }

        SearchFriendResultBean.UserListBean info = (SearchFriendResultBean.UserListBean) getItem(i);
        holderView.name.setText(info.getUserName());
        PhotoLoaderUtil.display(viewGroup.getContext(), holderView.icon, info.getHeadImageUrl(), R.drawable.ic_default_user);
        return view;
    }

    private class HolderView {
        ImageView icon;
        TextView name;
    }
}
