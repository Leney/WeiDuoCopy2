package com.zhongke.weiduo.mvp.base;

import android.content.Context;

import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.library.retrofit.RetrofitClient;
import com.zhongke.weiduo.library.sqlbrite.SQLBriteProvider;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by llj on 2017/6/15.
 * 通过弱引用和Activity以及Fragment的生命周期预防内存泄露的问题
 */

public abstract class BasePresenter<V> {
    protected Reference<V> mViewRef;//View 接口类型的弱引用
    protected RetrofitClient retrofitClient;
    protected SQLBriteProvider sqlBriteProvider;
    protected Context appContext;

//    public String getToken() {
//        if (TextUtils.isEmpty(token)){
//            queryPersonMsg();
//        }
//        return token;
//    }

//    protected  String token;
    protected  CompositeSubscription compositeSubscription=null;

    public BasePresenter(){
        initModeConfig();
    }
    /**
     * 初始化Model配置
     */
    void initModeConfig(){
        compositeSubscription=new CompositeSubscription();
        this.appContext=ZkApplication.getInstance();
        this.sqlBriteProvider=SQLBriteProvider.getInstance(this.appContext);
        this.retrofitClient=RetrofitClient.getInstance();
//        queryPersonMsg();
    }


    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
    protected Context getAppContext(){
      return   ZkApplication.getInstance();
    }
//    protected  void queryPersonMsg(){
//                 this.token=ZkApplication.getInstance().getToken();
//    }

    /**
     * 释放资源
     */
    public  void releaseResource(){
        this.compositeSubscription.clear();
    }



}
