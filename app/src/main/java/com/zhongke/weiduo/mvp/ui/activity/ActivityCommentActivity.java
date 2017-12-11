package com.zhongke.weiduo.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActivityCommentContract;
import com.zhongke.weiduo.mvp.model.entity.ActivityBehaviorBean;
import com.zhongke.weiduo.mvp.presenter.ActivityCommentPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.ActivityPrizeAdapter;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.widget.FactorEvaluationDialog;


/**
 * Created by ${xingen} on 2017/6/27.
 * <p>
 * 活动评论界面
 */

public class ActivityCommentActivity extends BaseMvpActivity implements ActivityCommentContract ,View.OnClickListener{
    private ActivityCommentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCenterLay(R.layout.activity_activitycomment);
        showCenterView();
        initView();
        //this.presenter.loadData();
        showDialog();
    }
    private void showDialog(){
        FactorEvaluationDialog dialog=new FactorEvaluationDialog(this,this);
        dialog.show();
    }

    private LinearLayout linearLayout;

    private ScrollView scrollView;
    private void initView() {

        scrollView=(ScrollView) this.findViewById(R.id.activitycomment_scrollview);
        linearLayout = (LinearLayout) this.findViewById(R.id.activitycomment_reward_layout);
    }

    @Override
    protected BasePresenter createPresenter() {
        this.presenter = new ActivityCommentPresenter(this);
        return presenter;
    }

    @Override
    public void setRewards() {
        View childView=View.inflate(this,R.layout.rewardview_layout,null);
        ( (GridView)childView.findViewById(R.id.rewardview_layout_gridview)).setAdapter(new ActivityPrizeAdapter(this));
        ((TextView)childView.findViewById(R.id.rewardview_layout_title)).setText("五星评价");
        linearLayout.addView(childView);
        View childView1=View.inflate(this,R.layout.rewardview_layout,null);
        ( (GridView)childView1.findViewById(R.id.rewardview_layout_gridview)).setAdapter(new ActivityPrizeAdapter(this));
        ((TextView)childView1.findViewById(R.id.rewardview_layout_title)).setText("四星评价");
        linearLayout.addView(childView1);
        scrollView.scrollTo(0,0);


    }

    @Override
    public void loadDataSuccess(ActivityBehaviorBean bean) {

    }

    @Override
    public void loadDataFailed(CommonException e) {

    }

    @Override
    public void CommentActivitySuccess() {

    }

    @Override
    public void CommentActivityFailed(CommonException e) {

    }

    @Override
    public void onClick(View v) {

    }
}
