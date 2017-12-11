package com.zhongke.weiduo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.RecommendSchemeBean;
import com.zhongke.weiduo.mvp.model.entity.TargetDetailBean2;
import com.zhongke.weiduo.mvp.ui.activity.SchemeDetailActivity;
import com.zhongke.weiduo.mvp.ui.adapter.TargetDetailAdapter;
import com.zhongke.weiduo.mvp.ui.widget.WapGridView;

/**
 * 规划详情描述Fragment
 * Created by llj on 2017/9/16.
 */

public class TargetDetailFragment extends Fragment implements AdapterView.OnItemClickListener {
    public TargetDetailBean2 detailBean;
    //    public List<SchemeDetailBean> recommendList;
    public RecommendSchemeBean mRecommendSchemeBean;
    private TextView detail;
    private WapGridView gridView;
    private TargetDetailAdapter mAdapter;

    public static TargetDetailFragment newInstance(TargetDetailBean2 bean, RecommendSchemeBean recommendSchemeBean) {
        TargetDetailFragment instance = new TargetDetailFragment();
        instance.detailBean = bean;
        instance.mRecommendSchemeBean = recommendSchemeBean;
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_target_detail, container, false);
        detail = (TextView) view.findViewById(R.id.target_detail_detail_info);
        detail.setText(detailBean.getTarget().getIntroduce());
        gridView = (WapGridView) view.findViewById(R.id.target_detail_recommend_grid_view);
        gridView.setOnItemClickListener(this);
        mAdapter = new TargetDetailAdapter(mRecommendSchemeBean.getB_plan());
        gridView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RecommendSchemeBean.BPlanBean bean = mRecommendSchemeBean.getB_plan().get(position);
        SchemeDetailActivity.startActivity(getActivity(), bean.getId(), bean.getBName());
    }
}
