package com.zhongke.weiduo.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.TestFragmentContract;
import com.zhongke.weiduo.mvp.model.entity.TestBus;
import com.zhongke.weiduo.mvp.presenter.TestFragmentPresenter;
import com.zhongke.weiduo.mvp.ui.activity.TestActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by llj on 2017/6/16.
 */

public class TestFragment extends BaseMvpFragment implements TestFragmentContract<List<TestBus>> {

    private static final String TAG = "TestActivity";
    @Bind(R.id.et_city)
    EditText mEtCity;
    @Bind(R.id.et_bus)
    EditText mEtBus;
    @Bind(R.id.tv_result)
    TextView mTvResult;
    @Bind(R.id.bt_check)
    Button mBtCheck;

    private TestFragmentPresenter mTestFragmentPresenter;
    private static final String APPKEY = "524454baff052e8f892797a420d4c808";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View baseView) {
        super.initView(baseView);
        setCenterLay(R.layout.activity_test);
        showCenterView();
        ButterKnife.bind(this,baseView);
        mBtCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "点击事件!!!!");
                final String city = mEtCity.getText().toString();
                final String bus = mEtBus.getText().toString();
                final String ditype = " ";
                mTestFragmentPresenter.getBus(ditype, city, bus, APPKEY);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        mTestFragmentPresenter = new TestFragmentPresenter(mDataManager,getActivity(),this);
        return mTestFragmentPresenter;
    }


    @Override
    public void success(List<TestBus> result) {
        mTvResult.setText(result.toString());
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, TestActivity.class));
    }

}
