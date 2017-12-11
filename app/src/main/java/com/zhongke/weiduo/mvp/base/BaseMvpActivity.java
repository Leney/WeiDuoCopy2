package com.zhongke.weiduo.mvp.base;

import android.os.Bundle;

/**
 * Created by llj on 2017/6/15.
 */

public abstract class BaseMvpActivity<V,P extends BasePresenter<V>> extends BaseActivity {

    private static final String TAG = "BaseMvpActivity";
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//创建Presenter
        mPresenter.attachView((V)this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.releaseResource();
        mPresenter.detachView();

    }

    protected abstract P createPresenter();
}
