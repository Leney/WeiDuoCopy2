package com.zhongke.weiduo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.adapter.ActivityResultAdapter;
import com.zhongke.weiduo.mvp.ui.widget.ActivityDetailListView;

/**
 * Created by ${xingen} on 2017/6/23.
 * 活动结果
 */

public class ActivityResultFragment  extends Fragment {
    private View rootView;
    public static final String TAG=ActivityResultFragment.class.getSimpleName();
    public static ActivityResultFragment newInstance(){
        return new ActivityResultFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=View.inflate(getContext(), R.layout.fragment_activityprocess,null);
        initView();
        return rootView;
    }
    private ActivityDetailListView listView;
    private void initView() {
        this.listView=(ActivityDetailListView) rootView.findViewById(R.id.activityprocess_listview);
        this.listView.setAdapter(new ActivityResultAdapter(getActivity()));
        ( (EventDetailsFragment)getParentFragment()).scrollTop();
    }


}
