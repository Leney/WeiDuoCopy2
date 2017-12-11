package com.zhongke.weiduo.mvp.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${xingen} on 2017/11/18.
 */

public class TaskListBean  {
    private List<MyTaskBean> myTask;
    private List<ChildTaskBean> childTask;

    public List<MyTaskBean> getMyTask() {
        return myTask;
    }

    public void setMyTask(List<MyTaskBean> myTask) {
        this.myTask = myTask;
    }

    public List<ChildTaskBean> getChildTask() {
        return childTask;
    }

    public void setChildTask(List<ChildTaskBean> childTask) {
        this.childTask = childTask;
    }

    public static class MyTaskBean implements Serializable {
        /**
         * address :
         * planCount : 1
         * evaluateScore : 0
         * doState : 1
         * latitude : 1.2222
         * userId : 1
         * createTime : 2017-11-14 14:25:58
         * actionId : 1
         * id : 62
         * beginTime : 2017-11-17 10:00:00
         * endTime : 2017-11-17 22:00:00
         * flowId:49
         * actionName : 喝酒
         * longitude : 1.2333
         */

        private String address;
        private int planCount;
        private int evaluateScore;
        private int doState;
        private double latitude;
        private int userId;
        private String createTime;
        private int actionId;
        private int id;
        private String beginTime;
        private String endTime;
        private int flowId;
        private String actionName;
        private double longitude;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPlanCount() {
            return planCount;
        }

        public void setPlanCount(int planCount) {
            this.planCount = planCount;
        }

        public int getEvaluateScore() {
            return evaluateScore;
        }

        public void setEvaluateScore(int evaluateScore) {
            this.evaluateScore = evaluateScore;
        }

        public int getDoState() {
            return doState;
        }

        public void setDoState(int doState) {
            this.doState = doState;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public int getFlowId() {
            return flowId;
        }

        public void setFlowId(int flowId) {
            this.flowId = flowId;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getActionName() {
            return actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "MyTaskBean{" +
                    "address='" + address + '\'' +
                    ", planCount=" + planCount +
                    ", evaluateScore=" + evaluateScore +
                    ", doState=" + doState +
                    ", latitude=" + latitude +
                    ", userId=" + userId +
                    ", createTime='" + createTime + '\'' +
                    ", actionId=" + actionId +
                    ", id=" + id +
                    ", beginTime='" + beginTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", flowId=" + flowId +
                    ", actionName='" + actionName + '\'' +
                    ", longitude=" + longitude +
                    '}';
        }
    }

    public static class ChildTaskBean implements Serializable{
        /**
         * address : 深圳
         * planCount : 4
         * evaluateScore : 0
         * doState : 1
         * latitude : 22.562342
         * userId : 163
         * actionType : 1
         * createTime : 2017-12-07 18:19:57
         * actionId : 109
         * id : 187
         * beginTime : 2017-12-07 16:29:52
         * endTime : 2017-12-10 16:31:30
         * evaluate : null
         * flowId : 49
         * actionName : 测试直播活动
         * longitude : 114.066852
         */

        private String address;
        private int planCount;
        private int evaluateScore;
        private int doState;
        private double latitude;
        private int userId;
        private int actionType;
        private String createTime;
        private int actionId;
        private int id;
        private String beginTime;
        private String endTime;
        private String evaluate;
        private int flowId;
        private String actionName;
        private double longitude;

        /**
         * address :
         * planCount : 1
         * evaluateScore : 0
         * doState : 1
         * latitude : 1.2333
         * userId : 1
         * createTime : 2017-11-14 14:25:58
         * actionId : 2
         * id : 62
         * beginTime : 2017-11-17 10:00:00
         * endTime : 2017-11-17 22:00:00
         * actionName : 喝酒
         * longitude : 1.2333
         */

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPlanCount() {
            return planCount;
        }

        public void setPlanCount(int planCount) {
            this.planCount = planCount;
        }

        public int getEvaluateScore() {
            return evaluateScore;
        }

        public void setEvaluateScore(int evaluateScore) {
            this.evaluateScore = evaluateScore;
        }

        public int getDoState() {
            return doState;
        }

        public void setDoState(int doState) {
            this.doState = doState;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getActionType() {
            return actionType;
        }

        public void setActionType(int actionType) {
            this.actionType = actionType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }

        public int getFlowId() {
            return flowId;
        }

        public void setFlowId(int flowId) {
            this.flowId = flowId;
        }

        public String getActionName() {
            return actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "ChildTaskBean{" +
                    "address='" + address + '\'' +
                    ", planCount=" + planCount +
                    ", evaluateScore=" + evaluateScore +
                    ", doState=" + doState +
                    ", latitude=" + latitude +
                    ", userId=" + userId +
                    ", actionType=" + actionType +
                    ", createTime='" + createTime + '\'' +
                    ", actionId=" + actionId +
                    ", id=" + id +
                    ", beginTime='" + beginTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", evaluate='" + evaluate + '\'' +
                    ", flowId=" + flowId +
                    ", actionName='" + actionName + '\'' +
                    ", longitude=" + longitude +
                    '}';
        }
    }
}
