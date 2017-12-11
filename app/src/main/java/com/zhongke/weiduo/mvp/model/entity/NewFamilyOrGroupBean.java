package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/17.
 * 获取群组或家庭的资料√
 */

public class NewFamilyOrGroupBean {

    /**
     * familyGroup : {"dynamicCode":"6q3us4","gName":"gfdfsd","createUserId":1,"groupType":1,"address":"ds","latitude":null,"hasBrother":1,"communicationStyle":2,"orgId":1,"gIconUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jnWfYAMxE7TrWpxeQHFr3XaNN1508729362137.jpg","tagList":"fds","gRule":null,"familyStructure":1,"gCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Sef36t5EfDm6rCcNEzN3Ckpe61508729364445.png","createTime":"2017-10-23 11:29:34","id":58,"gInfo":"fdsafds","longitude":null}
     * memberList : [{"gTitleName":1,"noSpeech":1,"createTime":"2017-10-23 11:29:34","nickName":"颜永华","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","groupId":58,"fTitleName":1,"warning":null,"id":53,"userName":"admin","userId":1}]
     */

    private FamilyGroupBean familyGroup;
    private List<MemberListBean> memberList;

    public FamilyGroupBean getFamilyGroup() {
        return familyGroup;
    }

    public void setFamilyGroup(FamilyGroupBean familyGroup) {
        this.familyGroup = familyGroup;
    }

    public List<MemberListBean> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberListBean> memberList) {
        this.memberList = memberList;
    }

    public static class FamilyGroupBean {
        /**
         * dynamicCode : 6q3us4
         * gName : gfdfsd
         * createUserId : 1
         * groupType : 1
         * address : ds
         * latitude : null
         * hasBrother : 1
         * communicationStyle : 2
         * orgId : 1
         * gIconUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jnWfYAMxE7TrWpxeQHFr3XaNN1508729362137.jpg
         * tagList : fds
         * gRule : null
         * familyStructure : 1
         * gCoverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Sef36t5EfDm6rCcNEzN3Ckpe61508729364445.png
         * createTime : 2017-10-23 11:29:34
         * id : 58
         * gInfo : fdsafds
         * longitude : null
         */

        private String dynamicCode;
        private String gName;
        private int createUserId;
        private int groupType;
        private String address;
        private Object latitude;
        private int hasBrother;
        private int communicationStyle;
        private int orgId;
        private String gIconUrl;
        private String tagList;
        private Object gRule;
        private int familyStructure;
        private String gCoverUrl;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public int getHasBrother() {
            return hasBrother;
        }

        public void setHasBrother(int hasBrother) {
            this.hasBrother = hasBrother;
        }

        public int getCommunicationStyle() {
            return communicationStyle;
        }

        public void setCommunicationStyle(int communicationStyle) {
            this.communicationStyle = communicationStyle;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

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

        public Object getGRule() {
            return gRule;
        }

        public void setGRule(Object gRule) {
            this.gRule = gRule;
        }

        public int getFamilyStructure() {
            return familyStructure;
        }

        public void setFamilyStructure(int familyStructure) {
            this.familyStructure = familyStructure;
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

    public static class MemberListBean {
        /**
         * gTitleName : 1
         * noSpeech : 1
         * createTime : 2017-10-23 11:29:34
         * nickName : 颜永华
         * headImageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png
         * groupId : 58
         * fTitleName : 1
         * warning : null
         * id : 53
         * userName : admin
         * userId : 1
         */

        private int gTitleName;
        private int noSpeech;
        private String createTime;
        private String nickName;
        private String headImageUrl;
        private int groupId;
        private int fTitleName;
        private Object warning;
        private int id;
        private String userName;
        private int userId;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getFTitleName() {
            return fTitleName;
        }

        public void setFTitleName(int fTitleName) {
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
}
