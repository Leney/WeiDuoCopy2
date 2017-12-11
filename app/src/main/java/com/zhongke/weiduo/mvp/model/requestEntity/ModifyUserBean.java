package com.zhongke.weiduo.mvp.model.requestEntity;

/**
 * Created by dgg1 on 2017/11/15.
 * 修改用户信息的请求实体类
 */

public class ModifyUserBean {
    private String token;
    private String nickName;
    private String headImageUrl;
    private String userPhone;
    private String sex;
    private String birthday;
    private String familyAddress;
    private String fullName;

    public ModifyUserBean() {
    }

    public ModifyUserBean(String token, String nickName, String headImageUrl, String userPhone, String sex, String birthday, String familyAddress, String fullName) {
        this.token = token;
        this.nickName = nickName;
        this.headImageUrl = headImageUrl;
        this.userPhone = userPhone;
        this.sex = sex;
        this.birthday = birthday;
        this.familyAddress = familyAddress;
        this.fullName = fullName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
