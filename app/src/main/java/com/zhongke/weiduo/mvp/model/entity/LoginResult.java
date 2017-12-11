package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${xingen} on 2017/11/6.
 */

public class LoginResult {

    /**
     * SysUser : {"birthday":0,"nickName":"丁小慧","headImageUrl":"","userPhone":"","sex":1,"majorList":"","deviceCode":"","registerType":0,"userName":"testAdmin","orgId":1,"tagList":"","school":"","phoneCode":"","id":90,"registerCode":"","info":"测试"}
     * token : d8vnmu0t1509532489561
     */

    private SysUserBean SysUser;
    private String token;

    public SysUserBean getSysUser() {
        return SysUser;
    }

    public void setSysUser(SysUserBean SysUser) {
        this.SysUser = SysUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class SysUserBean {
        /**
         * birthday : 0
         * nickName : 丁小慧
         * headImageUrl :
         * userPhone :
         * sex : 1
         * majorList :
         * deviceCode :
         * registerType : 0
         * userName : testAdmin
         * orgId : 1
         * tagList :
         * school :
         * phoneCode :
         * id : 90
         * registerCode :
         * info : 测试
         */

        private String birthday;
        private String nickName;
        private String headImageUrl;
        private String userPhone;
        private int sex;
        private String majorList;
        private String deviceCode;
        private int registerType;
        private String userName;
        private int orgId;
        private String tagList;
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

        public String getTagList() {
            return tagList;
        }

        public void setTagList(String tagList) {
            this.tagList = tagList;
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

        @Override
        public String toString() {
            return "SysUserBean{" +
                    "birthday='" + birthday + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", headImageUrl='" + headImageUrl + '\'' +
                    ", userPhone='" + userPhone + '\'' +
                    ", sex=" + sex +
                    ", majorList='" + majorList + '\'' +
                    ", deviceCode='" + deviceCode + '\'' +
                    ", registerType=" + registerType +
                    ", userName='" + userName + '\'' +
                    ", orgId=" + orgId +
                    ", tagList='" + tagList + '\'' +
                    ", school='" + school + '\'' +
                    ", phoneCode='" + phoneCode + '\'' +
                    ", id=" + id +
                    ", registerCode='" + registerCode + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }
}
