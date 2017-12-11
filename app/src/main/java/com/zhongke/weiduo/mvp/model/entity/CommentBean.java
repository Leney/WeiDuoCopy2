package com.zhongke.weiduo.mvp.model.entity;

/**
 * 评论对象
 * Created by llj on 2017/9/18.
 */

public class CommentBean {
    private int userId;
    private String userName;
    private String userIcon;
    private int userLevel;
    private String time;
    private String detail;

    public int getUserId() {
        return userId;
    }

    public CommentBean setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public CommentBean setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public CommentBean setUserIcon(String userIcon) {
        this.userIcon = userIcon;
        return this;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public CommentBean setUserLevel(int userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public String getTime() {
        return time;
    }

    public CommentBean setTime(String time) {
        this.time = time;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public CommentBean setDetail(String detail) {
        this.detail = detail;
        return this;
    }
}
