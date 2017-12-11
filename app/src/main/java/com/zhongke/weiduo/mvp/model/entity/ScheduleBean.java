package com.zhongke.weiduo.mvp.model.entity;

import com.zhongke.weiduo.mvp.model.entity.eventschedule.EventScheduleResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karma on 2017/9/18.
 * 日程 bean
 */

public class ScheduleBean {

    private String month;

    private String dayNumber;

    private String dayOfWeek;

    private List<EventScheduleResult.RecordsBean> scheduleChildList = new ArrayList<>();

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<EventScheduleResult.RecordsBean> getScheduleChildList() {
        return scheduleChildList;
    }

    public void setScheduleChildList(List<EventScheduleResult.RecordsBean> scheduleChildList) {
        this.scheduleChildList = scheduleChildList;
    }

    @Override
    public String toString() {
        return "ScheduleBean{" +
                "month='" + month + '\'' +
                ", dayNumber='" + dayNumber + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", scheduleChildList=" + scheduleChildList +
                '}';
    }
}
