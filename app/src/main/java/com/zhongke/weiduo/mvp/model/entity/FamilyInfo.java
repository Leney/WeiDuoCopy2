package com.zhongke.weiduo.mvp.model.entity;

/**
 * 家庭对象信息
 * Created by llj on 2017/6/21.
 */

public class FamilyInfo {

    /**
     * id : 122312
     * name : 小红的家庭
     * icon : http:www.sdfsfsfd.jpg
     * address : 深圳市宝安区西乡
     * latitude : 151152.2215
     * altitude : 1521.15152
     * distance : 0.12km
     */

    private int id;
    private String name;
    private String icon;
    private String address;
    private double latitude;
    private double altitude;
    private String distance;

    public FamilyInfo() {
    }

    public FamilyInfo(int id, String name, String icon, String address, double latitude, double altitude, String distance) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.address = address;
        this.latitude = latitude;
        this.altitude = altitude;
        this.distance = distance;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
