package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by hyx on 2017/12/2.
 *
 */

public class ActivityBehaviorBean {
    private List<BehaviorBean> behavior;

    public List<BehaviorBean> getBehavior() {
        return behavior;
    }

    public void setBehavior(List<BehaviorBean> behavior) {
        this.behavior = behavior;
    }

    public static class BehaviorBean {
        /**
         * awardId : null
         * resUrl : null
         * fNodeId : 1510137837776
         * doObject : 1
         * behaviorId : 260
         * awardName : null
         * fNodeType : behavior
         * resId : null
         * resName : null
         * msgContent : null
         * behaviorName : 积极参加集体活动
         * createTime : 2017-11-29 15:53:15
         * judgeType : 1
         * actionId : 109
         * id : 392
         * runTime : 60
         * toolsName : null
         * flowId : 49
         * toolsId : null
         * fNodeName : 关键行为
         */

        private int awardId;
        private Object resUrl;
        private String fNodeId;
        private int doObject;
        private int behaviorId;
        private Object awardName;
        private String fNodeType;
        private Object resId;
        private Object resName;
        private Object msgContent;
        private String behaviorName;
        private String createTime;
        private int judgeType;
        private int actionId;
        private int id;
        private int runTime;
        private String toolsName;
        private int flowId;
        private int toolsId;
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

        public int getBehaviorId() {
            return behaviorId;
        }

        public void setBehaviorId(int behaviorId) {
            this.behaviorId = behaviorId;
        }

        public Object getAwardName() {
            return awardName;
        }

        public void setAwardName(Object awardName) {
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

        public String getBehaviorName() {
            return behaviorName;
        }

        public void setBehaviorName(String behaviorName) {
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

        public String getToolsName() {
            return toolsName;
        }

        public void setToolsName(String toolsName) {
            this.toolsName = toolsName;
        }

        public int getFlowId() {
            return flowId;
        }

        public void setFlowId(int flowId) {
            this.flowId = flowId;
        }

        public int getToolsId() {
            return toolsId;
        }

        public void setToolsId(int toolsId) {
            this.toolsId = toolsId;
        }

        public String getFNodeName() {
            return fNodeName;
        }

        public void setFNodeName(String fNodeName) {
            this.fNodeName = fNodeName;
        }

        @Override
        public String toString() {
            return "BehaviorBean{" +
                    "awardId=" + awardId +
                    ", resUrl=" + resUrl +
                    ", fNodeId='" + fNodeId + '\'' +
                    ", doObject=" + doObject +
                    ", behaviorId=" + behaviorId +
                    ", awardName=" + awardName +
                    ", fNodeType='" + fNodeType + '\'' +
                    ", resId=" + resId +
                    ", resName=" + resName +
                    ", msgContent=" + msgContent +
                    ", behaviorName='" + behaviorName + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", judgeType=" + judgeType +
                    ", actionId=" + actionId +
                    ", id=" + id +
                    ", runTime=" + runTime +
                    ", toolsName=" + toolsName +
                    ", flowId=" + flowId +
                    ", toolsId=" + toolsId +
                    ", fNodeName='" + fNodeName + '\'' +
                    '}';
        }
    }
}
