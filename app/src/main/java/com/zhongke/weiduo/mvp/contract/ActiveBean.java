package com.zhongke.weiduo.mvp.contract;

/**
 * 活动实体类
 * Created by llj on 2017/9/20.
 */

public class ActiveBean {
    private int id;
    /** 所属计划的id*/
    private int belongId;
    private String name;
    private String icon;
    /** 活动地址*/
    private String location;
    /** 是否是线上活动*/
    private boolean isOnLine;
    /** 参与人数*/
    private int partInNum;
    /** 活动开始时间*/
    private String time;
    /** 价格*/
    private float price;
    /** 简单描述*/
    private String SimpleDes;
    /** 使用人数*/
    private int useCount;

    public int getId() {
        return id;
    }

    public ActiveBean setId(int id) {
        this.id = id;
        return this;
    }

    public int getBelongId() {
        return belongId;
    }

    public ActiveBean setBelongId(int belongId) {
        this.belongId = belongId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ActiveBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public ActiveBean setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ActiveBean setLocation(String location) {
        this.location = location;
        return this;
    }

    public boolean isOnLine() {
        return isOnLine;
    }

    public ActiveBean setOnLine(boolean onLine) {
        isOnLine = onLine;
        return this;
    }

    public int getPartInNum() {
        return partInNum;
    }

    public ActiveBean setPartInNum(int partInNum) {
        this.partInNum = partInNum;
        return this;
    }

    public String getTime() {
        return time;
    }

    public ActiveBean setTime(String time) {
        this.time = time;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public ActiveBean setPrice(float price) {
        this.price = price;
        return this;
    }

    public String getSimpleDes() {
        return SimpleDes;
    }

    public ActiveBean setSimpleDes(String simpleDes) {
        SimpleDes = simpleDes;
        return this;
    }

    public int getUseCount() {
        return useCount;
    }

    public ActiveBean setUseCount(int useCount) {
        this.useCount = useCount;
        return this;
    }
}
