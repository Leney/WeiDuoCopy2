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
import com.zhongke.weiduo.mvp.model.entity.PlanDetailBean2;
import com.zhongke.weiduo.mvp.model.entity.RecommendActiveBean;
import com.zhongke.weiduo.mvp.ui.activity.ActivityDetailActivity;
import com.zhongke.weiduo.mvp.ui.adapter.PlanDetailAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.PlanExpandAdapter;
import com.zhongke.weiduo.mvp.ui.widget.WapExpandableListView;
import com.zhongke.weiduo.mvp.ui.widget.WapGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 计划详情描述Fragment
 * Created by llj on 2017/9/16.
 */

public class PlanDetailFragment extends Fragment implements AdapterView.OnItemClickListener {
    public PlanDetailBean2 detailBean;
    //    public List<ActiveBean> recommendList;
    private RecommendActiveBean mRecommendActiveBean;
    private WapGridView gridView;
    private PlanDetailAdapter mAdapter;
    private WapExpandableListView expandListView;
    private PlanExpandAdapter mExpandAdapter;

    /**
     * 阶段名称列表
     */
    public List<String> groupList;

    /**
     * 阶段行为列表
     */
    public List<List<PlanDetailBean2.BehaviorItemBean>> childList;

//    private LinearLayout childInfoLay,parentInfoLay;

//    private ExpandListViewForScrollview childListView;
//    private ExpandListViewForScrollview parentsListView;
//    private PlanExecuteAdapter childExecuteAdapter;
//    private PlanExecuteAdapter parentsExecuteAdapter;
//    public List<PlanExecutorInfo> childGroupList;
//    public List<List<String>> childChildList;
//    public List<PlanExecutorInfo> parentsGroupList;
//    public List<List<String>> parentsChildList;

    //    public static PlanDetailFragment newInstance(PlanDetailBean bean, List<ActiveBean> list, List<PlanExecutorInfo> childGroupList, List<List<String>> childChildList, List<PlanExecutorInfo> parentsGroupList, List<List<String>> parentsChildList) {
//        PlanDetailFragment instance = new PlanDetailFragment();
//        instance.detailBean = bean;
//        instance.recommendList = list;
//        instance.childGroupList = childGroupList;
//        instance.childChildList = childChildList;
//        instance.parentsGroupList = parentsGroupList;
//        instance.parentsChildList = parentsChildList;
//        return instance;
//    }
    public static PlanDetailFragment newInstance(PlanDetailBean2 bean, RecommendActiveBean recommendActiveBean) {
        PlanDetailFragment instance = new PlanDetailFragment();
        instance.detailBean = bean;
        instance.mRecommendActiveBean = recommendActiveBean;
        instance.groupList = new ArrayList<>();
        instance.childList = new ArrayList<>();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan_detail, container, false);

//        childInfoLay = (LinearLayout) view.findViewById(R.id.plan_details_child_info_lay);
//        parentInfoLay = (LinearLayout) view.findViewById(R.id.plan_details_parent_info_lay);

//        childListView = (ExpandListViewForScrollview) view.findViewById(R.id.plan_detail_child_info_list);
//        parentsListView = (ExpandListViewForScrollview) view.findViewById(R.id.plan_detail_parents_info_list);


        // 重组行为列表数据
        reGroupData(detailBean.getBehaviorItem());

        expandListView = (WapExpandableListView) view.findViewById(R.id.plan_detail_expand_list_view);
        mExpandAdapter = new PlanExpandAdapter(groupList, childList);
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

        gridView = (WapGridView) view.findViewById(R.id.plan_detail_recommend_grid_view);
        gridView.setOnItemClickListener(this);
        mAdapter = new PlanDetailAdapter(mRecommendActiveBean.getAction());
        gridView.setAdapter(mAdapter);

//        for (Map.Entry entry : detailBean.getChildStageList().entrySet()) {
//            View stageView = View.inflate(getActivity(),R.layout.scheme_stage_item_layout,null);
//            TextView stageText = (TextView) stageView.findViewById(R.id.scheme_detail_stage_item_name);
//            stageText.setText((String) entry.getKey());
//            TreeView treeView = new TreeView(((TreeNode) entry.getValue()),getActivity(),new MyNodeViewFactory(true,MyNodeViewFactory.NONE_LINE_TYPE));
//            treeView.expandAll();
//            childInfoLay.addView(stageView);
//            childInfoLay.addView(treeView.getView());
//        }
//
//        for (Map.Entry entry : detailBean.getParentStageList().entrySet()) {
//            View stageView = View.inflate(getActivity(),R.layout.scheme_stage_item_layout,null);
//            TextView stageText = (TextView) stageView.findViewById(R.id.scheme_detail_stage_item_name);
//            stageText.setText((String) entry.getKey());
//            TreeView treeView = new TreeView(((TreeNode) entry.getValue()),getActivity(),new MyNodeViewFactory(true,MyNodeViewFactory.NONE_LINE_TYPE));
//            treeView.expandAll();
//            parentInfoLay.addView(stageView);
//            parentInfoLay.addView(treeView.getView());
//        }

//        childExecuteAdapter = new PlanExecuteAdapter(childGroupList,childChildList);
//        parentsExecuteAdapter = new PlanExecuteAdapter(parentsGroupList,parentsChildList);

//        childListView.setAdapter(childExecuteAdapter);
//        parentsListView.setAdapter(parentsExecuteAdapter);
//
//        // 默认全部展开
//        for (int i = 0; i < childGroupList.size(); i++) {
//            childListView.expandGroup(i);
//        }
//
//        // 默认全部展开
//        for (int i = 0; i < parentsGroupList.size(); i++) {
//            parentsListView.expandGroup(i);
//        }


//        // 屏蔽组的点击事件
//        childListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                // TODO Auto-generated method stub
//                return true;
//            }
//        });
//
//        // 屏蔽组的点击事件
//        parentsListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                // TODO Auto-generated method stub
//                return true;
//            }
//        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RecommendActiveBean.ActionBean bean = mRecommendActiveBean.getAction().get(position);
        ActivityDetailActivity.startActivity(getActivity(), bean.getId(), bean.getTitle());
    }

    /**
     * 重组行为列表数据
     *
     * @param list
     */
    private void reGroupData(List<PlanDetailBean2.BehaviorItemBean> list) {
        // 重组数据
        int length = list.size();
        for (int i = 0; i < length; i++) {
            PlanDetailBean2.BehaviorItemBean bean = list.get(i);
            String name = bean.getStage().trim();
            if (name.isEmpty()) continue;
            int position = getGroupPosition(name);
            if (position <= -1) {
                // 新的阶段
                groupList.add(name);
                List<PlanDetailBean2.BehaviorItemBean> stageList = new ArrayList<>();
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
