package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 登陆成功Bean
 * Created by llj on 2017/6/28.
 */

public class LoginSuccessBean {

    /**
     * uploadVideoUrl : http://www.sfsfsdfadfsa.acn/video
     * uploadPicUrl : http://www.sfsfsdfadfsa.acn/pic
     * icon : http://www.didfdffdd.jpg
     * nickName : 滴答小王子
     * userId : 100010
     * phone : 13810001000
     * userToken : sdf55sd5f5sfee8482116
     * haveFamily : 0
     * coins : 100
     * integral : 100
     * familyList : [{"id":12125,"name":"家庭名称","roleName":"爸爸","deviceCode":"55asf55sa","childName":"小明","managerId":152121},{"id":12125,"name":"家庭名称","roleName":"堂叔","deviceCode":"5323a32","childName":"小花","managerId":1521251}]
     */

    private String uploadVideoUrl;
    private String uploadPicUrl;
    private String icon;
    private String nickName;
    private int userId;
    private String phone;
    private String userToken;
    private int haveFamily;
    private int coins;
    private int integral;
    private List<FamilyListBean> familyList;

    public String getUploadVideoUrl() {
        return uploadVideoUrl;
    }

    public void setUploadVideoUrl(String uploadVideoUrl) {
        this.uploadVideoUrl = uploadVideoUrl;
    }

    public String getUploadPicUrl() {
        return uploadPicUrl;
    }

    public void setUploadPicUrl(String uploadPicUrl) {
        this.uploadPicUrl = uploadPicUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public int getHaveFamily() {
        return haveFamily;
    }

    public void setHaveFamily(int haveFamily) {
        this.haveFamily = haveFamily;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public List<FamilyListBean> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<FamilyListBean> familyList) {
        this.familyList = familyList;
    }

    public static class FamilyListBean {
        /**
         * id : 12125
         * name : 家庭名称
         * roleName : 爸爸
         * deviceCode : 55asf55sa
         * childName : 小明
         * managerId : 152121
         */

        private int id;
        private String name;
        private String roleName;
        private String deviceCode;
        private String childName;
        private int childId;
        private int managerId;

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

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }

        public String getChildName() {
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }

        public int getManagerId() {
            return managerId;
        }

        public void setManagerId(int managerId) {
            this.managerId = managerId;
        }
        public int getChildId() {
            return childId;
        }

        public void setChildId(int childId) {
            this.childId = childId;
        }
    }
}
