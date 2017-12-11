package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by dgg1 on 2017/11/13.
 */

public class NewPersonalData {

    /**
     * SysUser : {"headImageUrl":"","nickName":"丁小慧","roleId":7,"sex":1,"userPass":"","userPhone":"","deviceCode":"","userName":"testAdmin","orgId":1,"createTime":"2017-10-14","school":"","userState":1,"id":90,"registerCode":"","info":"测试"}
     */

    private SysUserBean SysUser;

    public SysUserBean getSysUser() {
        return SysUser;
    }

    public void setSysUser(SysUserBean SysUser) {
        this.SysUser = SysUser;
    }

    public static class SysUserBean {
        /**
         * headImageUrl :
         * nickName : 丁小慧
         * roleId : 7
         * sex : 1
         * userPass :
         * userPhone :
         * deviceCode :
         * userName : testAdmin
         * orgId : 1
         * createTime : 2017-10-14
         * school :
         * userState : 1
         * id : 90
         * registerCode :
         * info : 测试
         */
        private String birthday;
        private String headImageUrl;
        private String nickName;
        private int roleId;
        private int sex;
        private String userPass;
        private String userPhone;
        private String deviceCode;
        private String userName;
        private int orgId;
        private String createTime;
        private String school;
        private int userState;
        private int id;
        private String registerCode;
        private String info;
        private String fullName;
        private String familyAddress;

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getFamilyAddress() {
            return familyAddress;
        }

        public void setFamilyAddress(String familyAddress) {
            this.familyAddress = familyAddress;
        }

        public String getHeadImageUrl() {
            return headImageUrl;
        }

        public void setHeadImageUrl(String headImageUrl) {
            this.headImageUrl = headImageUrl;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
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

        public int getUserState() {
            return userState;
        }

        public void setUserState(int userState) {
            this.userState = userState;
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}
