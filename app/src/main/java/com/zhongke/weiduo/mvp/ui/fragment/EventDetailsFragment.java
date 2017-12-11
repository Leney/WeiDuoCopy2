package com.zhongke.weiduo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.TabItem;
import com.zhongke.weiduo.mvp.ui.activity.EventDetailsActivity;
import com.zhongke.weiduo.mvp.ui.widget.dialog.ActivityCommentDialog;
import com.zhongke.weiduo.mvp.ui.widget.layout.TabItemChangeLayout;
import com.zhongke.weiduo.util.StringUtils;
import com.zhongke.weiduo.util.ViewIdUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${xingen} on 2017/6/21.
 * 动态变化的活动详情界面
 */

public class EventDetailsFragment extends Fragment implements TabItemChangeLayout.TabChangeListener, View.OnClickListener {
    public static final String TAG = EventDetailsFragment.class.getSimpleName();
    private List<Fragment> fragments;
    private String NAME_1, NAME_2, NAME_3, NAME_4;
    private View rootView;
    private TabItemChangeLayout itemChangeLayout;
    private List<TabItem> list;
    public static EventDetailsFragment newInstance() {
        return newInstance(null);
    }
    public static EventDetailsFragment newInstance(Bundle bundle) {
        EventDetailsFragment fragment = new EventDetailsFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initConfig();
        rootView = inflater.inflate(R.layout.fragment_eventdetails, container, false);
        initView();
        addData();
        return rootView;
    }

    /**
     * 初始化配置
     */
    private void initConfig() {
        fragments = new ArrayList<>();
        list = new ArrayList<>();
        NAME_1 = StringUtils.getIndexString(R.string.invite_family);
        NAME_2 = StringUtils.getIndexString(R.string.activity_comments);
        NAME_3 = StringUtils.getIndexString(R.string.activity_result);
        NAME_4 = StringUtils.getIndexString(R.string.activity_progress);
    }
    private void addData() {
        Bundle bundle = getArguments();
        int state = bundle.getInt(EventDetailsActivity.STATE);
        int type = bundle.getInt(EventDetailsActivity.TYPE);
        switch (state) {
            case 0:  //未开始
                if (type == 1) {
                    list.add(createTab(NAME_4));
                    list.add(createTab(NAME_1));
                    list.add(createTab(NAME_2));
                    addOrShowFragment(ActivityProcessFragment.newInstance(), ActivityProcessFragment.TAG);
                } else {//纯文本
                    list.add(createTab(NAME_1));
                    list.add(createTab(NAME_2));
                    addOrShowFragment(InviteFamilyFragment.newInstance(), InviteFamilyFragment.TAG);
                }
                itemChangeLayout.addItemTab(list);
                break;
            case 1: //进行中
                list.add(createTab(NAME_4));
                list.add(createTab(NAME_1));
                list.add(createTab(NAME_2));
                itemChangeLayout.addItemTab(list);
                addOrShowFragment(ActivityProcessFragment.newInstance(), ActivityProcessFragment.TAG);
                break;
            case 2:

                break;
            case 6:
                list.add(createTab(NAME_4));
                list.add(createTab(NAME_3));
                list.add(createTab(NAME_2));
                itemChangeLayout.addItemTab(list, 1);
                addOrShowFragment(ActivityResultFragment.newInstance(), ActivityResultFragment.TAG);
                break;
        }

    }

    public TabItem createTab(String name) {
        TabItem tabItem = new TabItem();
        tabItem.setViewId(ViewIdUtils.getViewId());
        tabItem.setViewName(name);
        return tabItem;
    }

    private void initView() {
        itemChangeLayout = (TabItemChangeLayout) rootView.findViewById(R.id.event_details_tabitemchanglayout);
        itemChangeLayout.setChangeListener(this);
    }

    @Override
    public void changeTab(int currentTab) {
        for (int i = 0; i < list.size(); ++i) {
            if (currentTab == list.get(i).getViewId()) {
                remeberScrollTop();
                Fragment fragment = fragments.size() > i ? fragments.get(i) : null;
                if (NAME_1.equals(list.get(i).getViewName())) {//邀请家庭
                    addOrShowFragment(fragment == null ? InviteFamilyFragment.newInstance() : fragment, InviteFamilyFragment.TAG);
                } else if (NAME_2.equals(list.get(i).getViewName())) {//活动评论
                    addOrShowFragment(fragment == null ? ActivityEvaluationFragment.newInstance() : fragment, ActivityEvaluationFragment.TAG);
                } else if (NAME_3.equals(list.get(i).getViewName())) {//活动结果
                    addOrShowFragment(fragment == null ? ActivityResultFragment.newInstance() : fragment, ActivityResultFragment.TAG);
                } else if (NAME_4.equals(list.get(i).getViewName())) {//活动流程
                    addOrShowFragment(fragment == null ? ActivityProcessFragment.newInstance() : fragment, ActivityProcessFragment.TAG);
                }
                scrollTop();
            }
        }
    }

    public void scrollTop(){
        (  (EventDetailsActivity)getActivity() ).scrollTop();
    }
    public void remeberScrollTop(){
        (  (EventDetailsActivity)getActivity() ).remeberScrollTop();
    }

    /**
     * 添加或者显示fragment
     *
     * @param fragment
     * @param tag
     */
    public void addOrShowFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideChildrenFragment(transaction);
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            fragments.add(fragment);
            transaction.add(R.id.activitydetails_table_item_content_layout, fragment, tag);
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     *
     * @param transaction
     */
    public void hideChildrenFragment(FragmentTransaction transaction) {
        for (int i = 0; i < fragments.size(); ++i) {
            if (fragments.get(i) != null) {
                transaction.hide(fragments.get(i));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listview_footview_evaluation_tv://评论
                showActivityCommentDialog();
                break;
            case R.id.listview_footview_participate_tv://参加活动

                break;
            case R.id.activitycomment_commit_tv://弹窗上的评论按钮
                cancleActivityCommentDialog();
                break;
        }
    }

    private ActivityCommentDialog dialog;

    public void showActivityCommentDialog() {
        if (dialog == null) {
            dialog = new ActivityCommentDialog(getActivity(), this);
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public void cancleActivityCommentDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
