package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 计划详情
 * Created by llj on 2017/9/16.
 */

public class PlanDetailBean2 {
    /**
     * s_plan : {"publishState":1,"publishTime":null,"createUserId":1,"checkMsg":null,"bName":"微计划","catalog":null,"checkUserId":null,"publishUserId":null,"orgId":1,"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cTbasa5Yh5PTzsW1506678551337.jpg","checkTime":null,"createTime":"2017-09-29 17:49:21","bInfo":"微计划","checkState":1,"abilityTag":"微计划","id":95,"prospectusUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/F2XjhyKQ3DSDx341506678553933.pdf","introduceVideo":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/pxmyEWzZXszGxMw1506678556796.mp4","publishMsg":null,"thumbUpCount":1,"bookType":3}
     * behaviorItem : [{"resGrade":3,"targetName":"不做有危险的游戏","itemType":2,"createUserId":90,"modelList":[],"targetId":112,"doOrder":1,"doObject":1,"doLongTime":1,"toolsList":[],"behaviorId":273,"actionKindList":[],"orgId":1,"bookId":95,"standardList":[],"behaviorName":"不做有危险的游戏","stage":"5","milestone":"1","createTime":"2017-11-09 15:23:30","doCycle":1,"sceneList":[],"id":48,"awardType":1},{"resGrade":2,"targetName":"按时上学，不迟到，不早退","itemType":2,"createUserId":90,"modelList":[],"targetId":113,"doOrder":1,"doObject":1,"doLongTime":30,"toolsList":[],"behaviorId":252,"actionKindList":[],"orgId":1,"bookId":95,"standardList":[],"behaviorName":"按时上学，不迟到，不早退","stage":"1","milestone":"1","createTime":"2017-11-09 11:31:59","doCycle":1,"sceneList":[],"id":49,"awardType":2},{"resGrade":3,"targetName":"放学后按时回家","itemType":2,"createUserId":90,"modelList":[],"targetId":114,"doOrder":1,"doObject":2,"doLongTime":365,"toolsList":[],"behaviorId":268,"actionKindList":[],"orgId":1,"bookId":95,"standardList":[],"behaviorName":"放学后按时回家","stage":"1","milestone":"1","createTime":"2017-11-09 11:32:14","doCycle":1,"sceneList":[],"id":50,"awardType":1},{"resGrade":2,"targetName":"按时睡午觉","itemType":2,"createUserId":90,"modelList":[{"itemId":38,"modelName":"宋就种瓜","modelId":40,"id":69},{"itemId":38,"modelName":"孙策粗心丧命","modelId":42,"id":70},{"itemId":38,"modelName":"柳璨燃叶照书","modelId":53,"id":71}],"targetId":58,"doOrder":2,"doObject":1,"doLongTime":2,"toolsList":[{"itemId":38,"id":42,"toolsName":"刀","toolsId":1},{"itemId":38,"id":43,"toolsName":"本子","toolsId":5},{"itemId":38,"id":44,"toolsName":"床","toolsId":2},{"itemId":38,"id":45,"toolsName":"书","toolsId":73},{"itemId":38,"id":46,"toolsName":"发动机上课","toolsId":78},{"itemId":38,"id":47,"toolsName":"盘子","toolsId":80},{"itemId":38,"id":48,"toolsName":"水杯","toolsId":82}],"behaviorId":187,"actionKindList":[{"itemId":38,"actionKindId":2,"actionKindName":"表演","id":81},{"itemId":38,"actionKindId":152,"actionKindName":"开展社会调查活动。","id":82},{"itemId":38,"actionKindId":151,"actionKindName":"开展文化辅导活动或者是法律宣传与咨询活动","id":83}],"orgId":1,"bookId":95,"standardList":[{"itemId":38,"standardName":"居安之时要思危","standardId":136,"id":66},{"itemId":38,"standardName":"以德报怨是君子","standardId":135,"id":67},{"itemId":38,"standardName":"己所不欲勿施于人","standardId":134,"id":68}],"behaviorName":"按时睡午觉","stage":"好好吃饭","milestone":"1","createTime":"2017-10-16 18:11:26","doCycle":1,"sceneList":[{"itemId":38,"sceneName":"生活","sceneId":2,"id":33}],"id":38,"awardType":2}]
     */

    private SPlanBean s_plan;
    private List<BehaviorItemBean> behaviorItem;

    public SPlanBean getS_plan() {
        return s_plan;
    }

    public void setS_plan(SPlanBean s_plan) {
        this.s_plan = s_plan;
    }

    public List<BehaviorItemBean> getBehaviorItem() {
        return behaviorItem;
    }

    public void setBehaviorItem(List<BehaviorItemBean> behaviorItem) {
        this.behaviorItem = behaviorItem;
    }

    public static class SPlanBean {
        /**
         * publishState : 1
         * publishTime : null
         * createUserId : 1
         * checkMsg : null
         * bName : 微计划
         * catalog : null
         * checkUserId : null
         * publishUserId : null
         * orgId : 1
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cTbasa5Yh5PTzsW1506678551337.jpg
         * checkTime : null
         * createTime : 2017-09-29 17:49:21
         * bInfo : 微计划
         * checkState : 1
         * abilityTag : 微计划
         * id : 95
         * prospectusUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/F2XjhyKQ3DSDx341506678553933.pdf
         * introduceVideo : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/pxmyEWzZXszGxMw1506678556796.mp4
         * publishMsg : null
         * thumbUpCount : 1
         * bookType : 3
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
         * resGrade : 3
         * targetName : 不做有危险的游戏
         * itemType : 2
         * createUserId : 90
         * modelList : []
         * targetId : 112
         * doOrder : 1
         * doObject : 1
         * doLongTime : 1
         * toolsList : []
         * behaviorId : 273
         * actionKindList : []
         * orgId : 1
         * bookId : 95
         * standardList : []
         * behaviorName : 不做有危险的游戏
         * stage : 5
         * milestone : 1
         * createTime : 2017-11-09 15:23:30
         * doCycle : 1
         * sceneList : []
         * id : 48
         * awardType : 1
         */

        private int resGrade;
        private String targetName;
        private int itemType;
        private int createUserId;
        private int targetId;
        private int doOrder;
        private int doObject;
        private int doLongTime;
        private int behaviorId;
        private int orgId;
        private int bookId;
        private String behaviorName;
        private String stage;
        private String milestone;
        private String createTime;
        private int doCycle;
        private int id;
        private int awardType;
        private List<?> modelList;
        private List<?> toolsList;
        private List<?> actionKindList;
        private List<?> standardList;
        private List<?> sceneList;

        public int getResGrade() {
            return resGrade;
        }

        public void setResGrade(int resGrade) {
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

        public int getDoLongTime() {
            return doLongTime;
        }

        public void setDoLongTime(int doLongTime) {
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

        public int getDoCycle() {
            return doCycle;
        }

        public void setDoCycle(int doCycle) {
            this.doCycle = doCycle;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAwardType() {
            return awardType;
        }

        public void setAwardType(int awardType) {
            this.awardType = awardType;
        }

        public List<?> getModelList() {
            return modelList;
        }

        public void setModelList(List<?> modelList) {
            this.modelList = modelList;
        }

        public List<?> getToolsList() {
            return toolsList;
        }

        public void setToolsList(List<?> toolsList) {
            this.toolsList = toolsList;
        }

        public List<?> getActionKindList() {
            return actionKindList;
        }

        public void setActionKindList(List<?> actionKindList) {
            this.actionKindList = actionKindList;
        }

        public List<?> getStandardList() {
            return standardList;
        }

        public void setStandardList(List<?> standardList) {
            this.standardList = standardList;
        }

        public List<?> getSceneList() {
            return sceneList;
        }

        public void setSceneList(List<?> sceneList) {
            this.sceneList = sceneList;
        }
    }
}
