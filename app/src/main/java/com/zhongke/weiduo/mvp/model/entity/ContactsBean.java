package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 联系人对象(我的好友、我的群组、我的友好家庭、我的家庭)
 * 网络请求联系人接口返回的实体类
 * Created by llj on 2017/11/15.
 */

public class ContactsBean {
    private List<FriendFamilyListBean> friendFamilyList;
    private List<DeviceListBean> deviceList;
    private List<GroupListBean> groupList;
    private List<FriendListBean> friendList;
    private List<MyFamilyListBean> myFamilyList;

    public List<FriendFamilyListBean> getFriendFamilyList() {
        return friendFamilyList;
    }

    public void setFriendFamilyList(List<FriendFamilyListBean> friendFamilyList) {
        this.friendFamilyList = friendFamilyList;
    }

    public List<DeviceListBean> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<DeviceListBean> deviceList) {
        this.deviceList = deviceList;
    }

    public List<GroupListBean> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupListBean> groupList) {
        this.groupList = groupList;
    }

    public List<FriendListBean> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<FriendListBean> friendList) {
        this.friendList = friendList;
    }

    public List<MyFamilyListBean> getMyFamilyList() {
        return myFamilyList;
    }

    public void setMyFamilyList(List<MyFamilyListBean> myFamilyList) {
        this.myFamilyList = myFamilyList;
    }

    public static class FriendFamilyListBean {
        /**
         * gIconUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Bs8GdwApzQ3J55ZdTTYYFwGYc1508729631619.gif
         * tagList : 1,牛人,封疆
         * gName : 晴明家庭
         * createUserId : 90
         * gCoverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/npi2PXK43r2KX4n8Cbf8zaDZW1508729639264.bmp
         * createTime : 2017-11-21 15:00:24
         * friendGroupId : 68
         * groupId : 94
         * friendNickName : 好家庭
         * id : 15
         * gInfo : 和谐
         * groupNickName : 陈习训的家庭
         */

        private String gIconUrl;
        private String tagList;
        private String gName;
        private int createUserId;
        private String gCoverUrl;
        private String createTime;
        private int friendGroupId;
        private int groupId;
        private String friendNickName;
        private int id;
        private String gInfo;
        private String groupNickName;

        public String getGIconUrl() {
            return gIconUrl;
        }

        public void setGIconUrl(String gIconUrl) {
            this.gIconUrl = gIconUrl;
        }

        public String getTagList() {
            return tagList;
        }

        public void setTagList(String tagList) {
            this.tagList = tagList;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getGCoverUrl() {
            return gCoverUrl;
        }

        public void setGCoverUrl(String gCoverUrl) {
            this.gCoverUrl = gCoverUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getFriendGroupId() {
            return friendGroupId;
        }

        public void setFriendGroupId(int friendGroupId) {
            this.friendGroupId = friendGroupId;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getFriendNickName() {
            return friendNickName;
        }

        public void setFriendNickName(String friendNickName) {
            this.friendNickName = friendNickName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGInfo() {
            return gInfo;
        }

        public void setGInfo(String gInfo) {
            this.gInfo = gInfo;
        }

        public String getGroupNickName() {
            return groupNickName;
        }

        public void setGroupNickName(String groupNickName) {
            this.groupNickName = groupNickName;
        }
    }

    public static class DeviceListBean {
        /**
         * headImageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/6k3m2XKYBDapFSfxZHi47Awp61511494315093.png
         * nickName : 宝贝
         * sex : 1
         * fullName : null
         * id : 131
         * deviceCode : A6754123D
         * userName : A6754123D
         */

        private String headImageUrl;
        private String nickName;
        private int sex;
        private Object fullName;
        private int id;
        private String deviceCode;
        private String userName;

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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getFullName() {
            return fullName;
        }

        public void setFullName(Object fullName) {
            this.fullName = fullName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }

    public static class GroupListBean {
        /**
         * gName : zkxmse、zkqhoh的群聊
         * createUserId : 136
         * nickName : zkxmse
         * groupId : 6
         * userId : 136
         * gIconUrl : null
         * tagList : null
         * gTitleName : 1
         * noSpeech : 1
         * gCoverUrl : null
         * createTime : 2017-12-06 15:59:45
         * fTitleName : null
         * warning : null
         * id : 14
         * gInfo : zkxmse、zkqhoh的群聊
         */

        private String gName;
        private int createUserId;
        private String nickName;
        private int groupId;
        private int userId;
        private Object gIconUrl;
        private Object tagList;
        private int gTitleName;
        private int noSpeech;
        private Object gCoverUrl;
        private String createTime;
        private Object fTitleName;
        private Object warning;
        private int id;
        private String gInfo;

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getGIconUrl() {
            return gIconUrl;
        }

        public void setGIconUrl(Object gIconUrl) {
            this.gIconUrl = gIconUrl;
        }

        public Object getTagList() {
            return tagList;
        }

        public void setTagList(Object tagList) {
            this.tagList = tagList;
        }

        public int getGTitleName() {
            return gTitleName;
        }

        public void setGTitleName(int gTitleName) {
            this.gTitleName = gTitleName;
        }

        public int getNoSpeech() {
            return noSpeech;
        }

        public void setNoSpeech(int noSpeech) {
            this.noSpeech = noSpeech;
        }

        public Object getGCoverUrl() {
            return gCoverUrl;
        }

        public void setGCoverUrl(Object gCoverUrl) {
            this.gCoverUrl = gCoverUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getFTitleName() {
            return fTitleName;
        }

        public void setFTitleName(Object fTitleName) {
            this.fTitleName = fTitleName;
        }

        public Object getWarning() {
            return warning;
        }

        public void setWarning(Object warning) {
            this.warning = warning;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGInfo() {
            return gInfo;
        }

        public void setGInfo(String gInfo) {
            this.gInfo = gInfo;
        }
    }

    public static class FriendListBean {
        /**
         * friendId : 140
         * createTime : 2017-12-06 15:58:42
         * friendState : 2
         * headImageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/6k3m2XKYBDapFSfxZHi47Awp61511494315093.png
         * nickName :
         * friendNickName : 模拟器2
         * applayMsg : fff
         * fullName : null
         * id : 11
         * userNickName :
         * userName : zkqhoh
         * userId : 136
         */

        private int friendId;
        private String createTime;
        private int friendState;
        private String headImageUrl;
        private String nickName;
        private String friendNickName;
        private String applayMsg;
        private Object fullName;
        private int id;
        private String userNickName;
        private String userName;
        private int userId;

        public int getFriendId() {
            return friendId;
        }

        public void setFriendId(int friendId) {
            this.friendId = friendId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getFriendState() {
            return friendState;
        }

        public void setFriendState(int friendState) {
            this.friendState = friendState;
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

        public String getFriendNickName() {
            return friendNickName;
        }

        public void setFriendNickName(String friendNickName) {
            this.friendNickName = friendNickName;
        }

        public String getApplayMsg() {
            return applayMsg;
        }

        public void setApplayMsg(String applayMsg) {
            this.applayMsg = applayMsg;
        }

        public Object getFullName() {
            return fullName;
        }

        public void setFullName(Object fullName) {
            this.fullName = fullName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

    public static class MyFamilyListBean {
        /**
         * dynamicCode : 14aowm
         * gName : 好孩子的家庭
         * createUserId : 1
         * groupType : 1
         * address : null
         * latitude : null
         * hasBrother : null
         * communicationStyle : null
         * orgId : null
         * gIconUrl : null
         * tagList : null
         * gRule : null
         * familyStructure : null
         * gCoverUrl : null
         * createTime : 2017-12-04 19:56:19
         * id : 1
         * gInfo : 好孩子的家庭
         * longitude : null
         */

        private String dynamicCode;
        private String gName;
        private int createUserId;
        private int groupType;
        private Object address;
        private Object latitude;
        private Object hasBrother;
        private Object communicationStyle;
        private Object orgId;
        private Object gIconUrl;
        private Object tagList;
        private Object gRule;
        private Object familyStructure;
        private Object gCoverUrl;
        private String createTime;
        private int id;
        private String gInfo;
        private Object longitude;

        public String getDynamicCode() {
            return dynamicCode;
        }

        public void setDynamicCode(String dynamicCode) {
            this.dynamicCode = dynamicCode;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public int getGroupType() {
            return groupType;
        }

        public void setGroupType(int groupType) {
            this.groupType = groupType;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getHasBrother() {
            return hasBrother;
        }

        public void setHasBrother(Object hasBrother) {
            this.hasBrother = hasBrother;
        }

        public Object getCommunicationStyle() {
            return communicationStyle;
        }

        public void setCommunicationStyle(Object communicationStyle) {
            this.communicationStyle = communicationStyle;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public Object getGIconUrl() {
            return gIconUrl;
        }

        public void setGIconUrl(Object gIconUrl) {
            this.gIconUrl = gIconUrl;
        }

        public Object getTagList() {
            return tagList;
        }

        public void setTagList(Object tagList) {
            this.tagList = tagList;
        }

        public Object getGRule() {
            return gRule;
        }

        public void setGRule(Object gRule) {
            this.gRule = gRule;
        }

        public Object getFamilyStructure() {
            return familyStructure;
        }

        public void setFamilyStructure(Object familyStructure) {
            this.familyStructure = familyStructure;
        }

        public Object getGCoverUrl() {
            return gCoverUrl;
        }

        public void setGCoverUrl(Object gCoverUrl) {
            this.gCoverUrl = gCoverUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGInfo() {
            return gInfo;
        }

        public void setGInfo(String gInfo) {
            this.gInfo = gInfo;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }
    }
}
