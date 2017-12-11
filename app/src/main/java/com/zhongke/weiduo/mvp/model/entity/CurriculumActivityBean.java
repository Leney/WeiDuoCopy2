package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${tanlei} on 2017/6/28.
 */

public class CurriculumActivityBean {
    private int type;
    private int id;
    private String name;
    private String address;
    private String picture;
    private boolean check;
    private boolean collection;

    public CurriculumActivityBean() {
    }

    public CurriculumActivityBean(int type, int id, String name, String address, String picture, boolean check, boolean collection) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.address = address;
        this.picture = picture;
        this.check = check;
        this.collection = collection;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }
}
