package com.zhongke.weiduo.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程详情介绍bean
 * Created by llj on 2017/9/14.
 */

public class CourseDetailBean {
    /** 课程id*/
    private int id;
    /** 课程名称*/
    private String name;
    /** 好评率*/
    private String goodPercent;
    /** 报名人数*/
    private int signUpNum;
    /** 播放次数*/
    private int playNum;
    /** 课程信息介绍*/
    private String info;
    /** 介绍图片集合*/
    private List<String> infoImgs = new ArrayList<>();

    public int getId() {
        return id;
    }

    public CourseDetailBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseDetailBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getGoodPercent() {
        return goodPercent;
    }

    public CourseDetailBean setGoodPercent(String goodPercent) {
        this.goodPercent = goodPercent;
        return this;
    }

    public int getSignUpNum() {
        return signUpNum;
    }

    public CourseDetailBean setSignUpNum(int signUpNum) {
        this.signUpNum = signUpNum;
        return this;
    }

    public int getPlayNum() {
        return playNum;
    }

    public CourseDetailBean setPlayNum(int playNum) {
        this.playNum = playNum;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public CourseDetailBean setInfo(String info) {
        this.info = info;
        return this;
    }

    public List<String> getInfoImgs() {
        return infoImgs;
    }

    public CourseDetailBean setInfoImgs(List<String> infoImgs) {
        this.infoImgs = infoImgs;
        return this;
    }
}
