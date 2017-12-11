package com.zhongke.weiduo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.activity.ActMembersActivity;
import com.zhongke.weiduo.mvp.ui.activity.ActToolsActivity;
import com.zhongke.weiduo.mvp.ui.activity.ActivityRewardActivity;
import com.zhongke.weiduo.mvp.ui.activity.MoneyPrepareActivity;
import com.zhongke.weiduo.mvp.ui.activity.StepDetailActivity;
import com.zhongke.weiduo.mvp.ui.adapter.ActivityProcessAdapter;
import com.zhongke.weiduo.mvp.ui.widget.ActivityDetailListView;

/**
 * Created by ${xingen} on 2017/6/21.
 * 活动流程
 */

public class ActivityProcessFragment extends Fragment implements View.OnClickListener {
    private ActivityDetailListView listView;
    public static final String TAG = ActivityProcessFragment.class.getSimpleName();

    public static ActivityProcessFragment newInstance() {
        return new ActivityProcessFragment();
    }

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_activityprocess, container, false);
        initView();
        return rootView;
    }

    private ActivityProcessAdapter adapter;

    private void initView() {
        this.listView = (ActivityDetailListView) rootView.findViewById(R.id.activityprocess_listview);
        this.listView.addDefaultFootView(getIndexParentFragment());
        this.adapter = new ActivityProcessAdapter(getContext(), this);
        this.listView.setAdapter(adapter);
        Log.i(TAG,"child Fragment scroll");
        ( (EventDetailsFragment)getParentFragment()).scrollTop();
    }

    public EventDetailsFragment getIndexParentFragment() {
        return getParentFragment() instanceof EventDetailsFragment ? ((EventDetailsFragment) getParentFragment()) : null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activityprocess_listview_item_person_tv://人员
                ActMembersActivity.startActivity(getActivity(),12,10023);
                break;
            case R.id.activityprocess_listview_item_funds_tv://资金
                MoneyPrepareActivity.startActivity(getActivity(),100023);
                break;
            case R.id.activityprocess_listview_item_use_tv://用具
                ActToolsActivity.startActivity(getActivity(),100023);
                break;
            case R.id.activityprocess_listview_item_step1_tv://步骤一
                StepDetailActivity.startActivity(getActivity(),10023,1001,"步骤一");
                break;
            case R.id.activityprocess_listview_item_reward_tv: //奖励设置
                ActivityRewardActivity.openActivity(getActivity());
                break;
        }
    }


}
