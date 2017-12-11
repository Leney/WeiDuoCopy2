package com.zhongke.weiduo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.activity.EventDetailsActivity;

/**
 * Created by ${xingen} on 2017/6/21.
 * 未完成活动页面
 */

public class NotFinishActivityFragment extends Fragment {
    private View rootView;
    public static final  String TAG=NotFinishActivityFragment.class.getSimpleName();
    public static NotFinishActivityFragment newIntance(){
        return  newIntance(null);
    }
    public static NotFinishActivityFragment newIntance(Bundle bundle){
        NotFinishActivityFragment finishActivityFragment=new NotFinishActivityFragment();
        if(bundle!=null){
            finishActivityFragment.setArguments(bundle);
        }
        return finishActivityFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_plaintexteventdetails,container,false);
        initView();
        return rootView;
    }

    private void initView() {
       Bundle bundle= getArguments();
       int state= bundle.getInt(EventDetailsActivity.STATE);
        switch (state){
            case 2://延迟
                   rootView.findViewById(R.id.event_details_line_tv1).setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.event_details_delay_time_layout).setVisibility(View.VISIBLE);
                break;
            case 4://未完成
                rootView.findViewById(R.id.event_details_line_tv1).setVisibility(View.GONE);
                rootView.findViewById(R.id.event_details_delay_time_layout).setVisibility(View.GONE);
                break;
        }
    }
}
