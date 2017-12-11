package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${xingen} on 2017/12/5.
 */

public class DeviceMsgBean {

    /**
     * resCode : 2
     * user : {"birthday":"","nickName":"婷婷","headImageUrl":"","roleId":2,"userPass":"E10ADC3949BA59ABBE56E057F20F883E","userPhone":"A1234b","sex":1,"fullName":"","majorList":"","deviceCode":"A1234b","registerType":2,"userName":"婷婷","orgId":1,"familyAddress":"","tagList":"","userState":1,"createTime":"2017-11-07 09:43:32","school":"","phoneCode":"","id":120,"registerCode":"A1234b","info":"婷婷"}
     */

    private int resCode;
    private UserBean user;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * birthday :
         * nickName : 婷婷
         * headImageUrl :
         * roleId : 2
         * userPass : E10ADC3949BA59ABBE56E057F20F883E
         * userPhone : A1234b
         * sex : 1
         * fullName :
         * majorList :
         * deviceCode : A1234b
         * registerType : 2
         * userName : 婷婷
         * orgId : 1
         * familyAddress :
         * tagList :
         * userState : 1
         * createTime : 2017-11-07 09:43:32
         * school :
         * phoneCode :
         * id : 120
         * registerCode : A1234b
         * info : 婷婷
         */

        private String birthday;
        private String nickName;
        private String headImageUrl;
        private int roleId;
        private String userPass;
        private String userPhone;
        private int sex;
        private String fullName;
        private String majorList;
        private String deviceCode;
        private int registerType;
        private String userName;
        private int orgId;
        private String familyAddress;
        private String tagList;
        private int userState;
        private String createTime;
        private String school;
        private String phoneCode;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
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

        public int getRegisterType() {
            return registerType;
        }

        public void setRegisterType(int registerType) {
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

        public String getFamilyAddress() {
            return familyAddress;
        }

        public void setFamilyAddress(String familyAddress) {
            this.familyAddress = familyAddress;
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

        public String getPhoneCode() {
            return phoneCode;
        }

        public void setPhoneCode(String phoneCode) {
            this.phoneCode = phoneCode;
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
    }
}
