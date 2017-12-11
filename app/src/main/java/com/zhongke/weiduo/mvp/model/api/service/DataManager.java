package com.zhongke.weiduo.mvp.model.api.service;

import android.content.Context;

import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.library.retrofit.RetrofitClient;
import com.zhongke.weiduo.library.sqlbrite.SQLBriteProvider;
import com.zhongke.weiduo.app.listener.HttpResultFunc;
import com.zhongke.weiduo.mvp.contract.ActiveBean;
import com.zhongke.weiduo.mvp.contract.ActiveBean2;
import com.zhongke.weiduo.mvp.model.entity.LoginModelBean;
import com.zhongke.weiduo.mvp.model.entity.MicroRudderBean;
import com.zhongke.weiduo.mvp.model.entity.TestBus;
import com.zhongke.weiduo.mvp.model.entity.UserInfoBean;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * Module数据入口：
 * 这里处理本地数据和网络数据。
 */

public class DataManager {
    private static final String TAG = "DataManager";

    private final Context appContext;
    /**
     * 本地数据Module
     */
    private final SQLBriteProvider sqlBriteProvider;

    /**
     * 网络数据Module
     */
    private final RetrofitClient retrofitClient;

   private DataManager() {
        this.appContext= ZkApplication.getInstance();
        this.sqlBriteProvider=SQLBriteProvider.getInstance(appContext);
        this.retrofitClient=RetrofitClient.getInstance();
    }

    private static DataManager instance;
    public static synchronized  DataManager getInstance(){
        if(instance == null){
            instance = new DataManager();
        }
        return instance;
    }






}
