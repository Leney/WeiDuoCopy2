package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AppendScheduleContract;
import com.zhongke.weiduo.mvp.model.entity.ExecutePeopleBean;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by Karma on 2017/9/18.
 */

public class AppendSchedulePrensenter extends BasePresenter {

    private Context context;
    private AppendScheduleContract contract;
    public AppendSchedulePrensenter (Context context, AppendScheduleContract contract) {
        this.context = context;
        this.contract = contract;
    }

    //添加日程
    public void addSchedule(String beginTime,String endTime,String beginDate,String endDate,String cycle,String actionName,String userId) {
        HashMap<String,String> map = new HashMap<>();
//        map.put("token",token);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        map.put("cycle",cycle);
        map.put("actionName",actionName);
        map.put("userId",userId);

        retrofitClient.addSchedule(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                //添加成功
                contract.addScheduleSuccess();
                LogUtil.e("addSchedule success");
            }

            @Override
            public void failure(CommonException e) {
                contract.addScheduleFailed(e);
                LogUtil.e("failure"+e.toString());
            }
        });
    }

    //获取用户和用户孩子列表
    public void getUserAndChild() {
        Subscription subscription = retrofitClient.getUserAndChild(new ResponseResultListener<ExecutePeopleBean>() {
            @Override
            public void success(ExecutePeopleBean executePeopleBean) {
                LogUtil.e("success---");
                contract.getUserAndChildSuccess(executePeopleBean);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure---");
                contract.getUserAndChildFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }
}
