package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${tanlei} on 2017/9/26.
 * 活动实体类
 */

public class MovableBean {
    private int id;
    private String location;
    private String name;
    private String photoUrl;
    private String title;
    private int number;
    private String time;

    public MovableBean() {
    }

    public MovableBean(int id, String location, String name, String photoUrl, String title, int number, String time) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.photoUrl = photoUrl;
        this.title = title;
        this.number = number;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
