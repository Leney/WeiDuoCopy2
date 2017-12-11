package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${xingen} on 2017/11/13.
 */

public class RegisterResultBean {

    /**
     * SysUser : {"userState":1,"roleId":1,"userPhone":"15625221422","phoneCode":"888888","registerType":1,"id":114,"userName":"15625221422","registerCode":"888888","orgId":1}
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
         * userState : 1
         * roleId : 1
         * userPhone : 15625221422
         * phoneCode : 888888
         * registerType : 1
         * id : 114
         * userName : 15625221422
         * registerCode : 888888
         * orgId : 1
         */

        private int userState;
        private int roleId;
        private String userPhone;
        private String phoneCode;
        private int registerType;
        private int id;
        private String userName;
        private String registerCode;
        private int orgId;

        public int getUserState() {
            return userState;
        }

        public void setUserState(int userState) {
            this.userState = userState;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getPhoneCode() {
            return phoneCode;
        }

        public void setPhoneCode(String phoneCode) {
            this.phoneCode = phoneCode;
        }

        public int getRegisterType() {
            return registerType;
        }

        public void setRegisterType(int registerType) {
            this.registerType = registerType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getRegisterCode() {
            return registerCode;
        }

        public void setRegisterCode(String registerCode) {
            this.registerCode = registerCode;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }
    }
}
