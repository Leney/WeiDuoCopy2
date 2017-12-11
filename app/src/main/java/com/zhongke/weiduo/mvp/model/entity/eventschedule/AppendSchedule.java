package com.zhongke.weiduo.mvp.model.entity.eventschedule;

/**
 * Created by hyx on 2017/11/7.
 * 添加我的日程
 */

public class AppendSchedule {

    private String token;

    private String actionName;

    private String beginTime;

    private String endTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


}
