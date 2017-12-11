package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.BuilderMap;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActivityDetailContract;
import com.zhongke.weiduo.mvp.model.entity.ActivityFlowBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityListDetailBean;
import com.zhongke.weiduo.mvp.model.entity.IsBindDeviceBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${xingen} on 2017/9/25.
 */

public class ActivityDetailPresenter extends BasePresenter  {
    private ActivityDetailContract view;
    public ActivityDetailPresenter(ActivityDetailContract view) {
        this.view = view;

    }
    public  void loadData(){
        executeTask();
    }

    private  void executeTask(){
        Subscription subscription= this.retrofitClient.getActivityListDetail(BuilderMap.buildMap(view.getActionId()), new ResponseResultListener<ActivityListDetailBean>() {
            @Override
            public void success(ActivityListDetailBean activityListDetailBean) {
                      view.showData(activityListDetailBean);
            }

            @Override
            public void failure(CommonException e) {
              view.showError(e);
            }
        });
        this.compositeSubscription.add(subscription);
    }

    //获取活动流程数据列表
    public void getActivityFlowList(String actionId) {
        Map<String,String > map = new HashMap<>();
        map.put("actionId",actionId);

        Subscription subscription = retrofitClient.getActivityFlowList(map, new ResponseResultListener<ActivityFlowBean>() {
            @Override
            public void success(ActivityFlowBean bean) {
                LogUtil.e("success--");
                LogUtil.e("bean---"+bean.getFlowData());
                view.getActivityFlowListSuccess(bean);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure---"+e.getErrorMsg());
                view.getActivityFlowListFailed(e);
            }
        });
        this.compositeSubscription.add(subscription);
    }

    //检验是否绑定设备
    public void getDeviceCount() {
        Subscription subscription = retrofitClient.getDeviceCount(new ResponseResultListener<IsBindDeviceBean>() {
            @Override
            public void success(IsBindDeviceBean isBindDeviceBean) {
                view.getDeviceCountSuccess();
            }

            @Override
            public void failure(CommonException e) {
                view.getDeviceCountFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }
}
