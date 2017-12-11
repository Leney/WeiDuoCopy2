package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${tanlei} on 2017/8/14.
 */

/**
 * 用户信息
 */
public class UserInfoBean {
    private String birthday;
    private String nickName;
    private String headImageUrl;
    private int roleId;
    private String userPass;
    private String userPhone;
    private String sex;
    private String majorList;
    private String deviceCode;
    private String registerType;
    private String userName;
    private int orgId;
    private String tagList;
    private int userState;
    private String createTime;
    private String school;
    private int id;
    private String registerCode;
    private String info;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
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

    public String getMajorList() {
        return majorList;
    }

    public void setMajorList(String majorList) {
        this.majorList = majorList;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "birthday='" + birthday + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headImageUrl='" + headImageUrl + '\'' +
                ", roleId=" + roleId +
                ", userPass='" + userPass + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", sex='" + sex + '\'' +
                ", majorList='" + majorList + '\'' +
                ", deviceCode='" + deviceCode + '\'' +
                ", registerType='" + registerType + '\'' +
                ", userName='" + userName + '\'' +
                ", orgId=" + orgId +
                ", tagList='" + tagList + '\'' +
                ", userState=" + userState +
                ", createTime='" + createTime + '\'' +
                ", school='" + school + '\'' +
                ", id=" + id +
                ", registerCode='" + registerCode + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
