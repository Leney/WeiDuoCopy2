package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by hyx on 2017/12/7.
 * 获取活动未发放的奖品列表bean
 */

public class ActivityAwardBean {

    private List<AwardBean> award;

    public List<AwardBean> getAward() {
        return award;
    }

    public void setAward(List<AwardBean> award) {
        this.award = award;
    }

    public static class AwardBean {
        /**
         * awardId : 6
         * resUrl : null
         * fNodeId : 1510138019893
         * doObject : 1
         * behaviorId : null
         * awardName : 玩具车
         * fNodeType : award
         * resId : null
         * resName : null
         * msgContent : null
         * behaviorName : null
         * createTime : 2017-11-16 10:01:13
         * judgeType : 1
         * actionId : 109
         * id : 390
         * runTime : 343
         * toolsName : null
         * flowId : 49
         * toolsId : null
         * fNodeName : 活动奖励
         */

        private int awardId;
        private Object resUrl;
        private String fNodeId;
        private int doObject;
        private Object behaviorId;
        private String awardName;
        private String fNodeType;
        private Object resId;
        private Object resName;
        private Object msgContent;
        private Object behaviorName;
        private String createTime;
        private int judgeType;
        private int actionId;
        private int id;
        private int runTime;
        private Object toolsName;
        private int flowId;
        private Object toolsId;
        private String fNodeName;

        public int getAwardId() {
            return awardId;
        }

        public void setAwardId(int awardId) {
            this.awardId = awardId;
        }

        public Object getResUrl() {
            return resUrl;
        }

        public void setResUrl(Object resUrl) {
            this.resUrl = resUrl;
        }

        public String getFNodeId() {
            return fNodeId;
        }

        public void setFNodeId(String fNodeId) {
            this.fNodeId = fNodeId;
        }

        public int getDoObject() {
            return doObject;
        }

        public void setDoObject(int doObject) {
            this.doObject = doObject;
        }

        public Object getBehaviorId() {
            return behaviorId;
        }

        public void setBehaviorId(Object behaviorId) {
            this.behaviorId = behaviorId;
        }

        public String getAwardName() {
            return awardName;
        }

        public void setAwardName(String awardName) {
            this.awardName = awardName;
        }

        public String getFNodeType() {
            return fNodeType;
        }

        public void setFNodeType(String fNodeType) {
            this.fNodeType = fNodeType;
        }

        public Object getResId() {
            return resId;
        }

        public void setResId(Object resId) {
            this.resId = resId;
        }

        public Object getResName() {
            return resName;
        }

        public void setResName(Object resName) {
            this.resName = resName;
        }

        public Object getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(Object msgContent) {
            this.msgContent = msgContent;
        }

        public Object getBehaviorName() {
            return behaviorName;
        }

        public void setBehaviorName(Object behaviorName) {
            this.behaviorName = behaviorName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getJudgeType() {
            return judgeType;
        }

        public void setJudgeType(int judgeType) {
            this.judgeType = judgeType;
        }

        public int getActionId() {
            return actionId;
        }

        public void setActionId(int actionId) {
            this.actionId = actionId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRunTime() {
            return runTime;
        }

        public void setRunTime(int runTime) {
            this.runTime = runTime;
        }

        public Object getToolsName() {
            return toolsName;
        }

        public void setToolsName(Object toolsName) {
            this.toolsName = toolsName;
        }

        public int getFlowId() {
            return flowId;
        }

        public void setFlowId(int flowId) {
            this.flowId = flowId;
        }

        public Object getToolsId() {
            return toolsId;
        }

        public void setToolsId(Object toolsId) {
            this.toolsId = toolsId;
        }

        public String getFNodeName() {
            return fNodeName;
        }

        public void setFNodeName(String fNodeName) {
            this.fNodeName = fNodeName;
        }
    }
}
