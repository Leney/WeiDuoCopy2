package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MyTargetListContract;
import com.zhongke.weiduo.mvp.model.entity.MyTargetBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/9/27.
 */

public class MyTargetListPresenter extends BasePresenter {
    private MyTargetListContract mContract;

    public MyTargetListPresenter(MyTargetListContract contract) {
        this.mContract = contract;
    }

    /**
     * 获取我的目标列表
     *
     * @param pageIndex
     * @param pageSize
     */
    public void getMyTargetList(int pageIndex, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);

        Subscription subscription = retrofitClient.getMyTargetList(params, new ResponseResultListener<MyTargetBean>() {
            @Override
            public void success(MyTargetBean myTargetBean) {
                mContract.getMyTargetListSuccess(myTargetBean);
            }

            @Override
            public void failure(CommonException e) {
                mContract.getMyTargetListFailed(e);
            }
        });
        compositeSubscription.add(subscription);

    }

    /**
     * 删除一条我的目标
     *
     * @param targetId
     */
    public void deleteMyTarget(int targetId) {
        Map<String, Object> params = new HashMap<>();
        params.put("targetId", targetId);

        Subscription subscription = retrofitClient.deleteMyTarget(params, new ResponseResultListener() {
            @Override
            public void success(Object object) {
                mContract.deleteMyTargetSuccess();
            }

            @Override
            public void failure(CommonException e) {
                mContract.deleteMyTargetFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

}
