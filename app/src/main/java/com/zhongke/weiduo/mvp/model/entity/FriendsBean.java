package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${tanlei} on 2017/8/11.
 */

public class FriendsBean {
    private String photo;
    private String name;
    private boolean check;

    public FriendsBean() {
    }

    public FriendsBean(String photo, String name, boolean check) {
        this.photo = photo;
        this.name = name;
        this.check = check;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
