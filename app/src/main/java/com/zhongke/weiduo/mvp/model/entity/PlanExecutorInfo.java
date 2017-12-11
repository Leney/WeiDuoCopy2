package com.zhongke.weiduo.mvp.model.entity;

/**
 * 计划执行项实体
 * Created by llj on 2017/9/27.
 */

public class PlanExecutorInfo {
    private int id;
    private String name;
    /** 执行时间*/
    private int day;

    public int getId() {
        return id;
    }

    public PlanExecutorInfo setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlanExecutorInfo setName(String name) {
        this.name = name;
        return this;
    }

    public int getDay() {
        return day;
    }

    public PlanExecutorInfo setDay(int day) {
        this.day = day;
        return this;
    }
}
