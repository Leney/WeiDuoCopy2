package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.presenter.ActivityAwardPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.AwardPrizeListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hyx on 2017/10/26.
 * 活动奖品界面
 */

public class ActivityAwardActivity extends BaseMvpActivity {

    private Context context;

    @Bind(R.id.activity_award_prize_lv)
    ListView prize_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseTitle.setRightVisible(true);
        baseTitle.setRightText("");
        baseTitle.setRightImgRes(R.drawable.activity_award_right_share);

        setCenterLay(R.layout.activity_award_comment);
        showCenterView();
        context = this;
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        AwardPrizeListAdapter prizeAdapter = new AwardPrizeListAdapter(context);
        prize_lv.setAdapter(prizeAdapter);
    }

    @Override
    protected BasePresenter createPresenter() {
        ActivityAwardPresenter presenter = new ActivityAwardPresenter(context);
        return presenter;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context,ActivityAwardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
