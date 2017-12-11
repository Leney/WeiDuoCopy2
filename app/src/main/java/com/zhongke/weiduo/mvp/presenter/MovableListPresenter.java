package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.BuilderMap;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MovableListContract;
import com.zhongke.weiduo.mvp.model.entity.ActivityListBean;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/9/26.
 */

public class MovableListPresenter extends BasePresenter {
    private MovableListContract movableListContract;

    private final int PAGESIZE = 8;
    private int currentPage = 1;
    private boolean isTaskGoging = false;

    public MovableListPresenter(MovableListContract movableListContract) {
        this.movableListContract = movableListContract;
    }
    public void executeTask(final int mode) {
        isTaskGoging=true;
        Subscription subscription = retrofitClient.getActivityList(BuilderMap.buildMap(currentPage, PAGESIZE), new ResponseResultListener<ActivityListBean>() {
            @Override
            public void success(ActivityListBean activityListBean) {
                isTaskGoging = false;
                movableListContract.getActiveListSuccess(mode,activityListBean.getRecords());
            }
            @Override
            public void failure(CommonException e) {
                isTaskGoging = false;
                movableListContract.getActiveListFailed(mode,e);
            }
        });
        this.compositeSubscription.add(subscription);
    }

    public void loadMore() {
        if (isTaskGoging) {
            return;
        }
        ++currentPage;
        executeTask(MovableListContract.MODE_LOADE_MORE);
    }

    public void refresh() {
        if (isTaskGoging) {
            return;
        }
        currentPage=1;
        executeTask(MovableListContract.MODE_REFRESH);
    }
}
