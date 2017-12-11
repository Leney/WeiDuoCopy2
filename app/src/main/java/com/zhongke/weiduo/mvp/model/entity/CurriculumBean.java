package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${tanlei} on 2017/6/27.
 */

public class CurriculumBean {
    private int id;
    private String name;
    private String stage;
    private String date;
    private String signUp;
    private String icon;

    public CurriculumBean() {
    }

    public CurriculumBean(int id,String name, String stage, String date, String signUp, String icon) {
        this.id = id;
        this.name = name;
        this.stage = stage;
        this.date = date;
        this.signUp = signUp;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public CurriculumBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSignUp() {
        return signUp;
    }

    public void setSignUp(String signUp) {
        this.signUp = signUp;
    }

    public String getIcon() {
        return icon;
    }

    public CurriculumBean setIcon(String icon) {
        this.icon = icon;
        return this;
    }
}
