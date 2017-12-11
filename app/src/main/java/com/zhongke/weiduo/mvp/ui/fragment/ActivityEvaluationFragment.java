package com.zhongke.weiduo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.adapter.ActivityEvaluationAdapter;
import com.zhongke.weiduo.mvp.ui.widget.ActivityDetailListView;

/**
 * Created by ${xingen} on 2017/6/22.
 * 活动评论界面
 */

public class ActivityEvaluationFragment extends Fragment{
    public static final String TAG=ActivityEvaluationFragment.class.getSimpleName();
    public static ActivityEvaluationFragment newInstance(){
        return  new ActivityEvaluationFragment();
    }
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=View.inflate(getActivity(), R.layout.fragment_activityprocess,null);
        initView();
        return rootView;
    }
    private ActivityDetailListView listView;

    private void initView() {

        this.listView=(ActivityDetailListView) rootView.findViewById(R.id.activityprocess_listview);
        this.listView.setHideDrivider();
        this.listView.addDefaultFootView(getIndexParentFragment());
        this.listView.setAdapter(new ActivityEvaluationAdapter(getContext()));

    }

    @Override
    public void onResume() {
        ( (EventDetailsFragment)getParentFragment()).scrollTop();
        super.onResume();
    }

    @Override
    public void onPause() {
        ( (EventDetailsFragment)getParentFragment()).remeberScrollTop();
        super.onPause();
    }

    public EventDetailsFragment getIndexParentFragment(){
        return   getParentFragment() instanceof  EventDetailsFragment?((EventDetailsFragment)getParentFragment()):null;
    }


}
