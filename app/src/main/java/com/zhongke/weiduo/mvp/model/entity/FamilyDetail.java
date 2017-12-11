package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 家庭资料详情实体类
 * Created by llj on 2017/6/23.
 */

public class FamilyDetail {

    /**
     * id : 21202
     * name : 家庭名称
     * icon : http://www.isfsdf.jpg
     * coverUrl : http://www.isfsdf.jpg
     * address : 深圳市南山区
     * longitude : 1202.212
     * latitude : 15515.326
     * isFriend : 0
     * labList : ["爱旅游","爱交流","爱教育"]
     * userList : [{"id":1215,"name":"用户名称","icon":"http://www.sdfsdfsdf.jpt"},{"id":1215,"name":"用户名称","icon":"http://www.sdfsdfsdf.jpt"},{"id":1215,"name":"用户名称","icon":"http://www.sdfsdfsdf.jpt"}]
     */

    private int id;
    private String name;
    private String icon;
    private String coverUrl;
    private String address;
    private double longitude;
    private double latitude;
    private int isFriend;
    private List<String> labList;
    private List<UserListBean> userList;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    public List<String> getLabList() {
        return labList;
    }

    public void setLabList(List<String> labList) {
        this.labList = labList;
    }

    public List<UserListBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListBean> userList) {
        this.userList = userList;
    }

    public static class UserListBean {
        /**
         * id : 1215
         * name : 用户名称
         * icon : http://www.sdfsdfsdf.jpt
         */

        private int id;
        private String name;
        private String icon;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
