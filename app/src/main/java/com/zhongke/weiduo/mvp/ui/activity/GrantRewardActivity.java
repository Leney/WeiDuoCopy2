package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.GrantRewardContract;
import com.zhongke.weiduo.mvp.presenter.GrantRewardPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hyx on 2017/10/25.
 * 发放奖励页面
 */

public class GrantRewardActivity extends BaseMvpActivity implements GrantRewardContract,View.OnClickListener {

    @Bind(R.id.grant_reward_add)
    ImageView grant_reward_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_grant_reward);
        baseTitle.setVisibility(View.VISIBLE);
        baseTitle.setRightVisible(true);
        baseTitle.setRightText("发布");
        baseTitle.setTitleName("发放奖品");
        showCenterView();
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        grant_reward_add.setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        GrantRewardPresenter presenter = new GrantRewardPresenter(this,this);
        return presenter;
    }

    public static void startActivity (Context context) {
        Intent intent = new Intent(context,GrantRewardActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grant_reward_add:

                AddPrizesActivity.startActivity(GrantRewardActivity.this);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void getAwardListSuccess() {

    }

    @Override
    public void getAwardListFailed() {

    }

    @Override
    public void grantActivityRewardSuccess() {

    }

    @Override
    public void grantActivityRewardFailed() {

    }


}
