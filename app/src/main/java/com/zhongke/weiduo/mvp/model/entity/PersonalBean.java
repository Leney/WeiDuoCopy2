package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by hyx on 2017/10/31.
 *
 * 个人资料
 */

public class PersonalBean {

    private String userAvatarUrl;
    private String nickName;
    private String phoneNumber;
    private String name;
    private int gender;
    private String bornDay;
    private String homeAddress;

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBornDay() {
        return bornDay;
    }

    public void setBornDay(String bornDay) {
        this.bornDay = bornDay;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "PersonalBean{" +
                "userAvatarUrl='" + userAvatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", bornDay='" + bornDay + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                '}';
    }
}
