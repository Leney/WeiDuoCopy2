package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/16.
 */

public class NewFamilyList {

    private List<FamilyListBean> familyList;

    public List<FamilyListBean> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<FamilyListBean> familyList) {
        this.familyList = familyList;
    }

    public static class FamilyListBean {
        /**
         * dynamicCode : ng9jh7
         * gName : 画画家庭
         * createUserId : 90
         * groupType : 1
         * address : 深圳
         * latitude : 22.568749
         * hasBrother : 2
         * communicationStyle : 4
         * orgId : 1
         * gIconUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Bs8GdwApzQ3J55ZdTTYYFwGYc1508729631619.gif
         * tagList : 1
         * gRule : null
         * familyStructure : 2
         * gCoverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/npi2PXK43r2KX4n8Cbf8zaDZW1508729639264.bmp
         * createTime : 2017-10-23 11:34:37
         * id : 64
         * gInfo : 和谐
         * longitude : 114.059378
         */

        private String dynamicCode;
        private String gName;
        private int createUserId;
        private int groupType;
        private String address;
        private double latitude;
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
        private double longitude;

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

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
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

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
