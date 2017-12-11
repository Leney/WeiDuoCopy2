package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.eventschedule.EventScheduleResult;

/**
 * Created by Karma on 2017/9/18.
 */

public interface ScheduleContract extends BaseView {

    //获取我的活动日程列表成功
    void getEventScheduleSuccess(EventScheduleResult resultBean,String requestTime);

    void getEventScheduleFailed(CommonException e);

}
