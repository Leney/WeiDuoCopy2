package com.zhongke.weiduo.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ${tanlei} on 2017/8/10.
 */

public class LoginModelBean implements Serializable{
    @SerializedName("loginTime")
    private String loginTime;
    @SerializedName("IP")
    private String IP;
    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("token")
    private String token;

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginModelBean{" +
                "loginTime='" + loginTime + '\'' +
                ", IP='" + IP + '\'' +
                ", id=" + id +
                ", userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}