package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${tanlei} on 2017/7/4.
 * 课程活动模板实体Bean;
 */

public class CurriculumTemplateBean {
    private String name;
    private int picture;

    public CurriculumTemplateBean() {
    }

    public CurriculumTemplateBean(String name, int picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
