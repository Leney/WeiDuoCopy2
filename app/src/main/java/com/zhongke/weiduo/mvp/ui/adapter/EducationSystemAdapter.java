package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.EducationSystemList;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/16.
 */

public class EducationSystemAdapter extends BaseQuickAdapter<EducationSystemList.RecordsBean, BaseViewHolder> {

    private List<EducationSystemList.RecordsBean> list;
    private Context context;


    public EducationSystemAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<EducationSystemList.RecordsBean> data) {
        super(layoutResId, data);
        this.list = data;
        this.context = context;
    }

    public List<EducationSystemList.RecordsBean> getList() {
        return list;
    }

    @Override
    protected void convert(BaseViewHolder helper, EducationSystemList.RecordsBean item) {
        ((TextView) helper.getView(R.id.tv_system_name)).setText(item.getBName());
        ((TextView) helper.getView(R.id.tv_system_text)).setText(item.getBInfo());
        Glide.with(helper.getConvertView().getContext()).load(item.getCoverUrl()).into(((ImageView) (helper.getView(R.id.iv_system_photo))));
    }

    /**
     * 改变数据
     *
     * @param beanList
     */
    public void changeData(List<EducationSystemList.RecordsBean> beanList) {
        this.list.clear();
        this.list.addAll(beanList);
        this.notifyDataSetChanged();
    }

    /**
     * 新增数据
     *
     * @param beanList
     */
    public void addData(List<EducationSystemList.RecordsBean> beanList) {
//        int size = list.size();
        this.list.addAll(beanList);
        this.notifyDataSetChanged();
//        this.notifyItemInserted(size);
    }

    private View headerView;

    public void addHeader(View.OnClickListener onClickListener) {
        headerView = LayoutInflater.from(context).inflate(R.layout.item_education_system_header, null, false);
        headerView.setOnClickListener(onClickListener);
        hideHeader();
        this.addHeaderView(headerView);
    }

    public void hideHeader() {
        if (headerView != null) {
            headerView.setVisibility(View.GONE);
        }

    }

    public void showHeader() {
        if (headerView != null) {
            headerView.setVisibility(View.VISIBLE);
        }
    }

}
