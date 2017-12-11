package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by hyx on 2017/11/30.
 * 活动流程
 */

public class ActivityFlowBean {

    private List<FlowDataBean> flowData;

    public List<FlowDataBean> getFlowData() {
        return flowData;
    }

    public void setFlowData(List<FlowDataBean> flowData) {
        this.flowData = flowData;
    }

    public static class FlowDataBean {
        /**
         * awardId : null
         * resUrl : null
         * fNodeId : 1510137990676
         * doObject : 1
         * behaviorId : null
         * awardName : null
         * fNodeType : answer
         * resId : 379
         * resName : 2017年全国卷
         * msgContent : null
         * behaviorName : null
         * createTime : 2017-11-08 19:59:31
         * judgeType : 1
         * actionId : 109
         * id : 380
         * runTime : 25
         * toolsName : null
         * flowId : 49
         * toolsId : null
         * fNodeName : 活动抢答
         */

        private Object awardId;
        private Object resUrl;
        private String fNodeId;
        private int doObject;
        private Object behaviorId;
        private String awardName;
        private String fNodeType;
        private int resId;
        private String resName;
        private Object msgContent;
        private String behaviorName;
        private String createTime;
        private int judgeType;
        private int actionId;
        private int id;
        private int runTime;
        private String toolsName;
        private int flowId;
        private Object toolsId;
        private String fNodeName;

        public Object getAwardId() {
            return awardId;
        }

        public void setAwardId(Object awardId) {
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

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public String getResName() {
            return resName;
        }

        public void setResName(String resName) {
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

        @Override
        public String toString() {
            return "FlowDataBean{" +
                    "awardId=" + awardId +
                    ", resUrl=" + resUrl +
                    ", fNodeId='" + fNodeId + '\'' +
                    ", doObject=" + doObject +
                    ", behaviorId=" + behaviorId +
                    ", awardName='" + awardName + '\'' +
                    ", fNodeType='" + fNodeType + '\'' +
                    ", resId=" + resId +
                    ", resName='" + resName + '\'' +
                    ", msgContent=" + msgContent +
                    ", behaviorName='" + behaviorName + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", judgeType=" + judgeType +
                    ", actionId=" + actionId +
                    ", id=" + id +
                    ", runTime=" + runTime +
                    ", toolsName='" + toolsName + '\'' +
                    ", flowId=" + flowId +
                    ", toolsId=" + toolsId +
                    ", fNodeName='" + fNodeName + '\'' +
                    '}';
        }
    }
}
