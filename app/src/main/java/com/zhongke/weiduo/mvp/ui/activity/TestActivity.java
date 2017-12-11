package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.TestBusContract;
import com.zhongke.weiduo.mvp.model.entity.TestBus;
import com.zhongke.weiduo.mvp.presenter.TestBusPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by llj on 2017/6/7.
 */

public class TestActivity extends BaseMvpActivity implements TestBusContract<List<TestBus>> {
    private static final String TAG = "TestActivity";
    @Bind(R.id.et_city)
    EditText mEtCity;
    @Bind(R.id.et_bus)
    EditText mEtBus;
    @Bind(R.id.tv_result)
    TextView mTvResult;
    @Bind(R.id.bt_check)
    Button mBtCheck;

    private TestBusPresenter testBusPresneter;
    private static final String APPKEY = "524454baff052e8f892797a420d4c808";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName("测试界面");
        baseTitle.setTitleBackground(R.color.main_color);
        baseTitle.setTitleNameColor(R.color.white);
        setCenterLay(R.layout.activity_test);
        showCenterView();
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected BasePresenter createPresenter() {
        testBusPresneter = new TestBusPresenter(mDataManager, this, this);
        return testBusPresneter;
    }

    public void initView() {
        final String city = mEtCity.getText().toString();
        final String bus = mEtBus.getText().toString();
        final String ditype = " ";
        mBtCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "点击事件!!!!");
//                testBusPresneter.getBus(ditype, city, bus, APPKEY);
                startActivity(new Intent(TestActivity.this, SessionActivity.class));
            }
        });

    }

    @Override
    public void success(List<TestBus> result) {
        mTvResult.setText(result.toString());
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,  SessionActivity.class));
    }
}
