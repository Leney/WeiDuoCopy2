package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActivityRewardContract;
import com.zhongke.weiduo.mvp.model.entity.ActivityRewardBean;
import com.zhongke.weiduo.mvp.presenter.ActivityRewardPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.ActivityRewardAdapter;
import com.zhongke.weiduo.mvp.ui.widget.expandablelistview.CommonExpandableListView;
import com.zhongke.weiduo.util.StringUtils;

import java.util.List;

/**
 * Created by ${xingen} on 2017/6/27.
 * 奖励设置
 */

public class ActivityRewardActivity extends BaseMvpActivity implements View.OnClickListener, ActivityRewardContract {
    private ActivityRewardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_activityreward);
        this.showCenterView();
        initView();
        setTitleName(StringUtils.getIndexString(R.string.reward_setting));
        this.presenter.loadData();
    }

    private CommonExpandableListView  expandableListView;
    private ScrollView scrollView;

    private void initView() {
        expandableListView = (CommonExpandableListView) this.findViewById(R.id.activity_reward_expandableListView);
        this.scrollView=(ScrollView) this.findViewById(R.id.activity_reward_scrollview);
    }

    @Override
    protected BasePresenter createPresenter() {
        this.presenter = new ActivityRewardPresenter(this);
        return this.presenter;
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void loadRewardList(List<ActivityRewardBean.AwardListBean> awardList) {
        expandableListView.setAdapter(new ActivityRewardAdapter(this,awardList));
        expandableListView.unfoldedAllChildView();
        this.scrollView.smoothScrollTo(0,0);
    }

    @Override
    public void loadScoreList(List<ActivityRewardBean.TargetListBean> targetList) {

    }
    public static void openActivity(Context context){
        Intent intent=new Intent(context,ActivityRewardActivity.class);
        context.startActivity(intent);
    }
}
