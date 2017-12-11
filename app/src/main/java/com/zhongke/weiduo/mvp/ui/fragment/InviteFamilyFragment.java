package com.zhongke.weiduo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.adapter.InviteFamilyAdapter;
import com.zhongke.weiduo.mvp.ui.widget.ActivityDetailListView;

/**
 * Created by ${xingen} on 2017/6/22.
 * 邀请家庭
 */

public class InviteFamilyFragment extends Fragment{
    public static final  String TAG=InviteFamilyFragment.class.getSimpleName();
    public static InviteFamilyFragment newInstance(){
        return  new InviteFamilyFragment();
    }
    private View rootView;
    private ActivityDetailListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView=inflater.inflate(R.layout.fragment_activityprocess,container,false);
        initView();
        return rootView;
    }

    private void initView() {
        this.listView=(ActivityDetailListView) rootView.findViewById(R.id.activityprocess_listview);
        this.listView.addDefaultFootView(getIndexParentFragment());
        this.listView.setAdapter(new InviteFamilyAdapter(getContext()));
        ( (EventDetailsFragment)getParentFragment()).scrollTop();
    }
    public EventDetailsFragment getIndexParentFragment(){
        return   getParentFragment() instanceof  EventDetailsFragment?((EventDetailsFragment)getParentFragment()):null;
    }

}
