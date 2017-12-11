package com.zhongke.weiduo.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by llj on 2017/6/16.
 * MVP Fragment基类
 */

public abstract class BaseMvpFragment<V,P extends BasePresenter<V>> extends BaseFragment {
    private static final String TAG = "MVPBaseFragment";
    protected P mPresenter;
    protected CompositeSubscription compositeSubscription;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//创建Presenter
        mPresenter.attachView((V)this);
        this.compositeSubscription=new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        this.compositeSubscription.clear();
    }


    protected abstract P createPresenter();
}
