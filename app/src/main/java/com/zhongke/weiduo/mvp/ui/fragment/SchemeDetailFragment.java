package com.zhongke.weiduo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.StringUtil;
import com.zhongke.weiduo.mvp.model.entity.RecommendPlanBean;
import com.zhongke.weiduo.mvp.model.entity.SchemeDetailBean2;
import com.zhongke.weiduo.mvp.ui.activity.PlanDetailActivity;
import com.zhongke.weiduo.mvp.ui.adapter.SchemeDetailAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.SchemeExpandAdapter;
import com.zhongke.weiduo.mvp.ui.widget.WapExpandableListView;
import com.zhongke.weiduo.mvp.ui.widget.WapGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 规划详情描述Fragment
 * Created by llj on 2017/9/16.
 */

public class SchemeDetailFragment extends Fragment implements AdapterView.OnItemClickListener {
    public SchemeDetailBean2 detailBean;
    //    public List<PlanDetailBean> recommendList;
    private RecommendPlanBean mRecommendPlanBean;

    //    private LinearLayout childInfoLay,parentInfoLay;
    private WapGridView gridView;
    private SchemeDetailAdapter mAdapter;
    private WapExpandableListView expandListView;
    private SchemeExpandAdapter mExpandAdapter;

    /**
     * 阶段名称列表
     */
    public List<String> groupList;

    /**
     * 阶段行为列表
     */
    public List<List<SchemeDetailBean2.BehaviorItemBean>> childList;

    public static SchemeDetailFragment newInstance(SchemeDetailBean2 bean, RecommendPlanBean recommendPlanBean) {
        SchemeDetailFragment instance = new SchemeDetailFragment();
        instance.detailBean = bean;
        instance.mRecommendPlanBean = recommendPlanBean;
        instance.groupList = new ArrayList<>();
        instance.childList = new ArrayList<>();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scheme_detail, container, false);
//        childInfoLay = (LinearLayout) view.findViewById(R.id.scheme_details_child_info_lay);
//        parentInfoLay = (LinearLayout) view.findViewById(R.id.scheme_details_parent_info_lay);

        // 重组行为列表数据
        reGroupData(detailBean.getBehaviorItem());

        expandListView = (WapExpandableListView) view.findViewById(R.id.scheme_detail_expand_list_view);
        mExpandAdapter = new SchemeExpandAdapter(groupList, childList);
        expandListView.setAdapter(mExpandAdapter);

        // 设置组不可点击
        expandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });


        // 默认全部展开
        int groupCount = expandListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandListView.expandGroup(i);
        }

        gridView = (WapGridView) view.findViewById(R.id.scheme_detail_recommend_grid_view);
        gridView.setOnItemClickListener(this);
        mAdapter = new SchemeDetailAdapter(mRecommendPlanBean.getS_plan());
        gridView.setAdapter(mAdapter);

//        for (Map.Entry entry : detailBean.getChildStageList().entrySet()) {
//            View stageView = View.inflate(getActivity(),R.layout.scheme_stage_item_layout,null);
//            TextView stageText = (TextView) stageView.findViewById(R.id.scheme_detail_stage_item_name);
//            stageText.setText((String) entry.getKey());
//            TreeView treeView = new TreeView(((TreeNode) entry.getValue()),getActivity(),new MyNodeViewFactory(true,MyNodeViewFactory.LINE_TYPE));
//            treeView.expandAll();
//            childInfoLay.addView(stageView);
//            childInfoLay.addView(treeView.getView());
//        }
//
//        for (Map.Entry entry : detailBean.getParentStageList().entrySet()) {
//            View stageView = View.inflate(getActivity(),R.layout.scheme_stage_item_layout,null);
//            TextView stageText = (TextView) stageView.findViewById(R.id.scheme_detail_stage_item_name);
//            stageText.setText((String) entry.getKey());
//            TreeView treeView = new TreeView(((TreeNode) entry.getValue()),getActivity(),new MyNodeViewFactory(true,MyNodeViewFactory.LINE_TYPE));
//            treeView.expandAll();
//            parentInfoLay.addView(stageView);
//            parentInfoLay.addView(treeView.getView());
//        }

        // 节点部分
//        TreeView treeView = new TreeView(detailBean.getRootTreeNode(), getActivity(), new MyNodeViewFactory());
//        View rootView = treeView.getView();
//        view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
//        detailLay.addView(rootView);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RecommendPlanBean.SPlanBean bean = mRecommendPlanBean.getS_plan().get(position);
        PlanDetailActivity.startActivity(getActivity(), bean.getId(), bean.getBName());
    }

    /**
     * 重组行为列表数据
     *
     * @param list
     */
    private void reGroupData(List<SchemeDetailBean2.BehaviorItemBean> list) {
        // 重组数据
        int length = list.size();
        for (int i = 0; i < length; i++) {
            SchemeDetailBean2.BehaviorItemBean bean = list.get(i);
            String name = bean.getStage().trim();
            if (name.isEmpty()) continue;
            int position = getGroupPosition(name);
            if (position <= -1) {
                // 新的阶段
                groupList.add(name);
                List<SchemeDetailBean2.BehaviorItemBean> stageList = new ArrayList<>();
                stageList.add(bean);
                childList.add(stageList);
            } else {
                // 已经有加入过此阶段，则将行为数据加入到之前的阶段列表中去
                childList.get(position).add(bean);
            }
        }
    }


    // 根据value获得在GroupList中的position
    private int getGroupPosition(String value) {
        int length = groupList.size();
        for (int i = 0; i < length; i++) {
            if (StringUtil.isEquals(groupList.get(i), value)) {
                // 阶段组中有此数据
                return i;
            }
        }
        // 等于-1,表示没有查到数据，是一个新的阶段组别
        return -1;
    }
}
