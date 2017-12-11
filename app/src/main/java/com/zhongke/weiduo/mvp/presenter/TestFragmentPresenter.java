package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.zhongke.weiduo.app.listener.SubscriberOnNextListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.TestFragmentContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.TestBus;

import java.util.List;

/**
 * Created by Karma on 2017/6/15.
 * 类描述：
 */

public class TestFragmentPresenter extends BasePresenter {
    private static final String TAG = "TestFragmentPresenter";
    private Context context;
    private DataManager mDataManager;
    private TestFragmentContract<List<TestBus>> testFragmentContract;

    public TestFragmentPresenter(DataManager dataManager, Context context, TestFragmentContract<List<TestBus>> testFragmentContract) {
        this.context = context;
        this.testFragmentContract = testFragmentContract;
        this.mDataManager=dataManager;
    }

    public void getBus(String ditype, String city, String bus, String key) {
        if (mDataManager == null) return;

        SubscriberOnNextListener<List<TestBus>> onNext = new SubscriberOnNextListener<List<TestBus>>() {
            @Override
            public void onNext(List<TestBus> testBuses) {
                Log.e(TAG, "onNext: " + testBuses.toString());
                testFragmentContract.success(testBuses);
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "onError: " + t.getMessage());
            }
        };

     //   mDataManager.getBus(ditype, city, bus, key, new ProgressSubscriber<List<TestBus>>(onNext, context));
    }

}
