package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 规划详情
 * Created by llj on 2017/9/16.
 */

public class SchemeDetailBean2 {

    /**
     * behaviorItem : [{"resGrade":null,"targetName":"按时睡觉","itemType":1,"createUserId":1,"modelList":[],"targetId":87,"doOrder":4,"doObject":2,"doLongTime":null,"behaviorId":249,"orgId":1,"bookId":136,"standardList":[],"behaviorName":"按时睡觉","stage":"按时睡觉","milestone":"按时睡觉","createTime":"2017-10-31 15:17:50","doCycle":null,"id":57,"awardType":null}]
     * b_plan : {"publishState":1,"publishTime":null,"createUserId":90,"checkMsg":null,"bName":"爬山微计划","catalog":null,"checkUserId":null,"publishUserId":null,"orgId":1,"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/KNi8RxSQymK63ZsZD5FfsCP7S1508322568093.png","checkTime":null,"createTime":"2017-10-18 18:30:12","bInfo":"爬山","checkState":1,"abilityTag":"强身健体","id":136,"prospectusUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/DC3iTz8GSZ2CtbeCj87i66Eri1508322571585.pdf","introduceVideo":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Hid5MaHHtxQRdzn3CpJaMY3QB1508322575201.mp4","publishMsg":null,"thumbUpCount":0,"bookType":2}
     */

    private BPlanBean b_plan;
    private List<BehaviorItemBean> behaviorItem;

    public BPlanBean getB_plan() {
        return b_plan;
    }

    public void setB_plan(BPlanBean b_plan) {
        this.b_plan = b_plan;
    }

    public List<BehaviorItemBean> getBehaviorItem() {
        return behaviorItem;
    }

    public void setBehaviorItem(List<BehaviorItemBean> behaviorItem) {
        this.behaviorItem = behaviorItem;
    }

    public static class BPlanBean {
        /**
         * publishState : 1
         * publishTime : null
         * createUserId : 90
         * checkMsg : null
         * bName : 爬山微计划
         * catalog : null
         * checkUserId : null
         * publishUserId : null
         * orgId : 1
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/KNi8RxSQymK63ZsZD5FfsCP7S1508322568093.png
         * checkTime : null
         * createTime : 2017-10-18 18:30:12
         * bInfo : 爬山
         * checkState : 1
         * abilityTag : 强身健体
         * id : 136
         * prospectusUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/DC3iTz8GSZ2CtbeCj87i66Eri1508322571585.pdf
         * introduceVideo : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Hid5MaHHtxQRdzn3CpJaMY3QB1508322575201.mp4
         * publishMsg : null
         * thumbUpCount : 0
         * bookType : 2
         */

        private int publishState;
        private Object publishTime;
        private int createUserId;
        private Object checkMsg;
        private String bName;
        private Object catalog;
        private Object checkUserId;
        private Object publishUserId;
        private int orgId;
        private String coverUrl;
        private Object checkTime;
        private String createTime;
        private String bInfo;
        private int checkState;
        private String abilityTag;
        private int id;
        private String prospectusUrl;
        private String introduceVideo;
        private Object publishMsg;
        private int thumbUpCount;
        private int bookType;

        public int getPublishState() {
            return publishState;
        }

        public void setPublishState(int publishState) {
            this.publishState = publishState;
        }

        public Object getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(Object publishTime) {
            this.publishTime = publishTime;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public Object getCheckMsg() {
            return checkMsg;
        }

        public void setCheckMsg(Object checkMsg) {
            this.checkMsg = checkMsg;
        }

        public String getBName() {
            return bName;
        }

        public void setBName(String bName) {
            this.bName = bName;
        }

        public Object getCatalog() {
            return catalog;
        }

        public void setCatalog(Object catalog) {
            this.catalog = catalog;
        }

        public Object getCheckUserId() {
            return checkUserId;
        }

        public void setCheckUserId(Object checkUserId) {
            this.checkUserId = checkUserId;
        }

        public Object getPublishUserId() {
            return publishUserId;
        }

        public void setPublishUserId(Object publishUserId) {
            this.publishUserId = publishUserId;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public Object getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(Object checkTime) {
            this.checkTime = checkTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getBInfo() {
            return bInfo;
        }

        public void setBInfo(String bInfo) {
            this.bInfo = bInfo;
        }

        public int getCheckState() {
            return checkState;
        }

        public void setCheckState(int checkState) {
            this.checkState = checkState;
        }

        public String getAbilityTag() {
            return abilityTag;
        }

        public void setAbilityTag(String abilityTag) {
            this.abilityTag = abilityTag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProspectusUrl() {
            return prospectusUrl;
        }

        public void setProspectusUrl(String prospectusUrl) {
            this.prospectusUrl = prospectusUrl;
        }

        public String getIntroduceVideo() {
            return introduceVideo;
        }

        public void setIntroduceVideo(String introduceVideo) {
            this.introduceVideo = introduceVideo;
        }

        public Object getPublishMsg() {
            return publishMsg;
        }

        public void setPublishMsg(Object publishMsg) {
            this.publishMsg = publishMsg;
        }

        public int getThumbUpCount() {
            return thumbUpCount;
        }

        public void setThumbUpCount(int thumbUpCount) {
            this.thumbUpCount = thumbUpCount;
        }

        public int getBookType() {
            return bookType;
        }

        public void setBookType(int bookType) {
            this.bookType = bookType;
        }
    }

    public static class BehaviorItemBean {
        /**
         * resGrade : null
         * targetName : 按时睡觉
         * itemType : 1
         * createUserId : 1
         * modelList : []
         * targetId : 87
         * doOrder : 4
         * doObject : 2
         * doLongTime : null
         * behaviorId : 249
         * orgId : 1
         * bookId : 136
         * standardList : []
         * behaviorName : 按时睡觉
         * stage : 按时睡觉
         * milestone : 按时睡觉
         * createTime : 2017-10-31 15:17:50
         * doCycle : null
         * id : 57
         * awardType : null
         */

        private Object resGrade;
        private String targetName;
        private int itemType;
        private int createUserId;
        private int targetId;
        private int doOrder;
        private int doObject;
        private Object doLongTime;
        private int behaviorId;
        private int orgId;
        private int bookId;
        private String behaviorName;
        private String stage;
        private String milestone;
        private String createTime;
        private Object doCycle;
        private int id;
        private Object awardType;
        private List<?> modelList;
        private List<?> standardList;

        public Object getResGrade() {
            return resGrade;
        }

        public void setResGrade(Object resGrade) {
            this.resGrade = resGrade;
        }

        public String getTargetName() {
            return targetName;
        }

        public void setTargetName(String targetName) {
            this.targetName = targetName;
        }

        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public int getDoOrder() {
            return doOrder;
        }

        public void setDoOrder(int doOrder) {
            this.doOrder = doOrder;
        }

        public int getDoObject() {
            return doObject;
        }

        public void setDoObject(int doObject) {
            this.doObject = doObject;
        }

        public Object getDoLongTime() {
            return doLongTime;
        }

        public void setDoLongTime(Object doLongTime) {
            this.doLongTime = doLongTime;
        }

        public int getBehaviorId() {
            return behaviorId;
        }

        public void setBehaviorId(int behaviorId) {
            this.behaviorId = behaviorId;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public String getBehaviorName() {
            return behaviorName;
        }

        public void setBehaviorName(String behaviorName) {
            this.behaviorName = behaviorName;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getMilestone() {
            return milestone;
        }

        public void setMilestone(String milestone) {
            this.milestone = milestone;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getDoCycle() {
            return doCycle;
        }

        public void setDoCycle(Object doCycle) {
            this.doCycle = doCycle;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getAwardType() {
            return awardType;
        }

        public void setAwardType(Object awardType) {
            this.awardType = awardType;
        }

        public List<?> getModelList() {
            return modelList;
        }

        public void setModelList(List<?> modelList) {
            this.modelList = modelList;
        }

        public List<?> getStandardList() {
            return standardList;
        }

        public void setStandardList(List<?> standardList) {
            this.standardList = standardList;
        }
    }
}
