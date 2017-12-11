package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.RegardingWeContract;
import com.zhongke.weiduo.mvp.presenter.PersonalPresenter;
import com.zhongke.weiduo.mvp.presenter.RegardingWePresenter;

/**
 * Created by ${tanlei} on 2017/9/28.
 */

public class RegardingWeActivity extends BaseMvpActivity implements RegardingWeContract {
    private PersonalPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_regarding_we);
        showCenterView();
        setTitleName("关于我们");
    }

    @Override
    protected BasePresenter createPresenter() {
        RegardingWePresenter presenter = new RegardingWePresenter();
        return presenter;
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RegardingWeActivity.class));
    }

}
