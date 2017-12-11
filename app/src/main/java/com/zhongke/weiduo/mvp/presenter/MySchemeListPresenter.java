package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MySchemeListContract;
import com.zhongke.weiduo.mvp.model.entity.MySchemeBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/9/27.
 */

public class MySchemeListPresenter extends BasePresenter {
    private MySchemeListContract mContract;

    public MySchemeListPresenter(MySchemeListContract contract) {
        this.mContract = contract;
    }

    /**
     * 获取我的规划列表
     *
     * @param pageIndex
     * @param pageSize
     */
    public void getMySchemeList(int pageIndex, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);

        Subscription subscription = retrofitClient.getMySchemeList(params, new ResponseResultListener<MySchemeBean>() {
            @Override
            public void success(MySchemeBean bean) {
                mContract.getMySchemeListSuccess(bean);
            }

            @Override
            public void failure(CommonException e) {
                mContract.getMySchemeListFailed(e);
            }
        });
        compositeSubscription.add(subscription);

    }

    /**
     * 删除一条我的规划
     *
     * @param planId
     */
    public void deleteMyScheme(int planId) {
        Map<String, Object> params = new HashMap<>();
        params.put("planId", planId);

        Subscription subscription = retrofitClient.deleteMyScheme(params, new ResponseResultListener() {
            @Override
            public void success(Object object) {
                mContract.deleteMySchemeSuccess();
            }

            @Override
            public void failure(CommonException e) {
                mContract.deleteMySchemeFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

}
