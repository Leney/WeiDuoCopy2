package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ScheduleContract;
import com.zhongke.weiduo.mvp.model.entity.eventschedule.EventScheduleResult;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by Karma on 2017/9/18.
 */

public class SchedulePresenter extends BasePresenter {
    private Context context;
    private ScheduleContract scheduleContract;

    public SchedulePresenter(Context context, ScheduleContract scheduleContract) {
        this.context = context;
        this.scheduleContract = scheduleContract;
    }

    public void getEventSchedule(String beginTime) {
        HashMap<String,String> hMap = new HashMap<>();
        hMap.put("beginTime",beginTime);

        Subscription subscription = retrofitClient.getEventSchedule(hMap, new ResponseResultListener<EventScheduleResult>() {
            @Override
            public void success(EventScheduleResult eventScheduleResult) {
                LogUtil.e("EventSchedule success--"+eventScheduleResult.getRecords());
                LogUtil.e("beginTime----"+beginTime);
                scheduleContract.getEventScheduleSuccess(eventScheduleResult,beginTime);
            }

            @Override
            public void failure(CommonException e) {
                scheduleContract.getEventScheduleFailed(e);
                LogUtil.e("EventSchedule failed--"+e.getCode()+e.toString());
            }
        });
        compositeSubscription.add(subscription);
    }

}
