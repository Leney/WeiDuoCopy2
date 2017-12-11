package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActivateContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;

import java.util.Map;

/**
 * 登录
 * Created by llj on 2017/6/20.
 */

public class ActivatePresenter extends BasePresenter {
    private static final String TAG = "ActivatePresenter";
    private Context mContext;
    private DataManager mDataManager;
    private ActivateContract mContract;

    public ActivatePresenter(Context context, DataManager dataManager, ActivateContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 获取相关数据
     */
    public void getInfo(){
        mContract.getInfoSuccess();
    }

    /**
     * 激活设备
     */
    public void activateDevice(Map<String,Object> params){
        retrofitClient.activateDevice(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                mContract.activateSuccess();
            }
            @Override
            public void failure(CommonException e) {
                mContract.activateFailed(e.getCode(),e.getErrorMsg());
            }
        });

    }
}
