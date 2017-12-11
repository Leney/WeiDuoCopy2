package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${xingen} on 2017/11/9.
 */

public class ActivityListDetailBean {
    /**
     * action : {"createUserId":90,"actionKindId":174,"actionState":1,"latitude":22.562342,"title":"植物手绘","resKind":2,"orgId":1,"actionImport":1,"videoUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/mB7D68KWPXwHdWwMs4Dh5w68Y1511231387495.mp4","aInfo":"活动描述","actionKindName":"木槿","sceneId":null,"id":109,"beginTime":"2017-12-06 16:29:52","flowId":49,"longitude":114.066852,"resGrade":2,"cost":0,"planCount":4,"address":"深圳","attendanceCount":0,"sceneName":null,"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/rZQ2WbjFAWdWaMdX8bia3D2wC1512010620908.jpg","actionType":1,"createTime":"2017-11-06 18:15:15","organizer":90,"signupCount":0,"actionSafety":1,"endTime":"2017-12-10 16:31:30","thumbUpCount":58}
     */
    private ActionBean action;

    public ActionBean getAction() {
        return action;
    }

    public void setAction(ActionBean action) {
        this.action = action;
    }

    public static class ActionBean {
        /**
         * createUserId : 90
         * actionKindId : 174
         * actionState : 1
         * latitude : 22.562342
         * title : 植物手绘
         * resKind : 2
         * orgId : 1
         * actionImport : 1
         * videoUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/mB7D68KWPXwHdWwMs4Dh5w68Y1511231387495.mp4
         * aInfo : 活动描述
         * actionKindName : 木槿
         * sceneId : null
         * id : 109
         * beginTime : 2017-12-06 16:29:52
         * flowId : 49
         * longitude : 114.066852
         * resGrade : 2
         * cost : 0
         * planCount : 4
         * address : 深圳
         * attendanceCount : 0
         * sceneName : null
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/rZQ2WbjFAWdWaMdX8bia3D2wC1512010620908.jpg
         * actionType : 1
         * createTime : 2017-11-06 18:15:15
         * organizer : 90
         * signupCount : 0
         * actionSafety : 1
         * endTime : 2017-12-10 16:31:30
         * thumbUpCount : 58
         */

        private int createUserId;
        private int actionKindId;
        private int actionState;
        private double latitude;
        private String title;
        private int resKind;
        private int orgId;
        private int actionImport;
        private String videoUrl;
        private String aInfo;
        private String actionKindName;
        private Object sceneId;
        private int id;
        private String beginTime;
        private int flowId;
        private double longitude;
        private int resGrade;
        private int cost;
        private int planCount;
        private String address;
        private int attendanceCount;
        private Object sceneName;
        private String coverUrl;
        private int actionType;
        private String createTime;
        private int organizer;
        private int signupCount;
        private int actionSafety;
        private String endTime;
        private int thumbUpCount;

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public int getActionKindId() {
            return actionKindId;
        }

        public void setActionKindId(int actionKindId) {
            this.actionKindId = actionKindId;
        }

        public int getActionState() {
            return actionState;
        }

        public void setActionState(int actionState) {
            this.actionState = actionState;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getResKind() {
            return resKind;
        }

        public void setResKind(int resKind) {
            this.resKind = resKind;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getActionImport() {
            return actionImport;
        }

        public void setActionImport(int actionImport) {
            this.actionImport = actionImport;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getAInfo() {
            return aInfo;
        }

        public void setAInfo(String aInfo) {
            this.aInfo = aInfo;
        }

        public String getActionKindName() {
            return actionKindName;
        }

        public void setActionKindName(String actionKindName) {
            this.actionKindName = actionKindName;
        }

        public Object getSceneId() {
            return sceneId;
        }

        public void setSceneId(Object sceneId) {
            this.sceneId = sceneId;
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

        public int getFlowId() {
            return flowId;
        }

        public void setFlowId(int flowId) {
            this.flowId = flowId;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public int getResGrade() {
            return resGrade;
        }

        public void setResGrade(int resGrade) {
            this.resGrade = resGrade;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getPlanCount() {
            return planCount;
        }

        public void setPlanCount(int planCount) {
            this.planCount = planCount;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAttendanceCount() {
            return attendanceCount;
        }

        public void setAttendanceCount(int attendanceCount) {
            this.attendanceCount = attendanceCount;
        }

        public Object getSceneName() {
            return sceneName;
        }

        public void setSceneName(Object sceneName) {
            this.sceneName = sceneName;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
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

        public int getOrganizer() {
            return organizer;
        }

        public void setOrganizer(int organizer) {
            this.organizer = organizer;
        }

        public int getSignupCount() {
            return signupCount;
        }

        public void setSignupCount(int signupCount) {
            this.signupCount = signupCount;
        }

        public int getActionSafety() {
            return actionSafety;
        }

        public void setActionSafety(int actionSafety) {
            this.actionSafety = actionSafety;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getThumbUpCount() {
            return thumbUpCount;
        }

        public void setThumbUpCount(int thumbUpCount) {
            this.thumbUpCount = thumbUpCount;
        }
    }
}
