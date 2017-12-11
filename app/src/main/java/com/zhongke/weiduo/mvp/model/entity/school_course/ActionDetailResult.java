package com.zhongke.weiduo.mvp.model.entity.school_course;

/**
 * Created by hyx on 2017/11/7.
 * 获取活动详情的结果
 */

public class ActionDetailResult {

    /**
     * b_plan : {"actionKindId":95,"flowJson":"{\"title\":\"去动物园\",\"nodes\":{\"1508832947835\":{\"name\":\"开始\",\"left\":84,\"top\":188,\"type\":\"start round mix\",\"width\":28,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1508832951324\":{\"name\":\"关键行为\",\"left\":613,\"top\":276,\"type\":\"behavior\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1508832952596\":{\"name\":\"视频\",\"left\":607,\"top\":184,\"type\":\"video\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1508833021703\":{\"name\":\"发送通知消息\",\"left\":200,\"top\":185,\"type\":\"sendMessage\",\"width\":104,\"height\":28,\"color\":\"#B6F700\",\"alt\":true},\"1508833024198\":{\"name\":\"活动评价\",\"left\":622,\"top\":360,\"type\":\"evaluate\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1508833025668\":{\"name\":\"花环\",\"left\":775,\"top\":358,\"type\":\"award\",\"width\":104,\"height\":28,\"color\":\"#B6F700\",\"alt\":true},\"1508833030517\":{\"name\":\"活动道具\",\"left\":416,\"top\":153,\"type\":\"tools\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1508833035409\":{\"name\":\"活动道具\",\"left\":416,\"top\":221,\"type\":\"tools\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1508833036598\":{\"name\":\"活动道具\",\"left\":420,\"top\":291,\"type\":\"tools\",\"width\":104,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true},\"1508833039046\":{\"name\":\"人民币\",\"left\":412,\"top\":84,\"type\":\"tools\",\"width\":104,\"height\":28,\"color\":\"#B6F700\",\"alt\":true},\"1508833144662\":{\"name\":\"结束\",\"left\":815,\"top\":274,\"type\":\"end round\",\"width\":28,\"height\":28,\"color\":\"#C0CCDA\",\"alt\":true}},\"lines\":{\"1508833129162\":{\"type\":\"sl\",\"from\":\"1508832947835\",\"to\":\"1508833021703\",\"name\":\"\",\"dash\":false},\"1508833130212\":{\"type\":\"sl\",\"from\":\"1508833021703\",\"to\":\"1508833039046\",\"name\":\"\",\"dash\":false},\"1508833131172\":{\"type\":\"sl\",\"from\":\"1508833021703\",\"to\":\"1508833030517\",\"name\":\"\",\"dash\":false},\"1508833132061\":{\"type\":\"sl\",\"from\":\"1508833021703\",\"to\":\"1508833035409\",\"name\":\"\",\"dash\":false},\"1508833134111\":{\"type\":\"sl\",\"from\":\"1508833021703\",\"to\":\"1508833036598\",\"name\":\"\",\"dash\":false},\"1508833135550\":{\"type\":\"sl\",\"from\":\"1508833039046\",\"to\":\"1508832952596\",\"name\":\"\",\"dash\":false},\"1508833136244\":{\"type\":\"sl\",\"from\":\"1508833030517\",\"to\":\"1508832952596\",\"name\":\"\",\"dash\":false},\"1508833138561\":{\"type\":\"sl\",\"from\":\"1508833036598\",\"to\":\"1508832952596\",\"name\":\"\",\"dash\":false},\"1508833139734\":{\"type\":\"sl\",\"from\":\"1508833035409\",\"to\":\"1508832952596\",\"name\":\"\",\"dash\":false},\"1508833140579\":{\"type\":\"sl\",\"from\":\"1508832952596\",\"to\":\"1508832951324\",\"name\":\"\",\"dash\":false},\"1508833141497\":{\"type\":\"sl\",\"from\":\"1508832951324\",\"to\":\"1508833024198\",\"name\":\"\",\"dash\":false},\"1508833142429\":{\"type\":\"sl\",\"from\":\"1508833024198\",\"to\":\"1508833025668\",\"name\":\"\",\"dash\":false},\"1508833146955\":{\"type\":\"sl\",\"from\":\"1508833025668\",\"to\":\"1508833144662\",\"name\":\"\",\"dash\":false}},\"areas\":{},\"initNum\":26}","actionState":1,"latitude":22.542648,"title":"春游活动","orgId":1,"actionImport":2,"videoUrl":null,"aInfo":"春游活动","actionKindName":"旅游","sceneId":null,"id":64,"beginTime":"2017-10-26 00:00:00","flowId":21,"longitude":113.980425,"cost":0,"planCount":34,"address":"深圳世界之窗","attendanceCount":0,"sceneName":null,"coverUrl":"","actionType":1,"organizer":1,"signupCount":0,"actionSafety":3,"endTime":"2017-10-26 00:00:00"}
     */

    private BPlanBean b_plan;

    public BPlanBean getB_plan() {
        return b_plan;
    }

    public void setB_plan(BPlanBean b_plan) {
        this.b_plan = b_plan;
    }

    public static class BPlanBean {
        /**
         * actionKindId : 95
         * flowJson : {"title":"去动物园","nodes":{"1508832947835":{"name":"开始","left":84,"top":188,"type":"start round mix","width":28,"height":28,"color":"#C0CCDA","alt":true},"1508832951324":{"name":"关键行为","left":613,"top":276,"type":"behavior","width":104,"height":28,"color":"#C0CCDA","alt":true},"1508832952596":{"name":"视频","left":607,"top":184,"type":"video","width":104,"height":28,"color":"#C0CCDA","alt":true},"1508833021703":{"name":"发送通知消息","left":200,"top":185,"type":"sendMessage","width":104,"height":28,"color":"#B6F700","alt":true},"1508833024198":{"name":"活动评价","left":622,"top":360,"type":"evaluate","width":104,"height":28,"color":"#C0CCDA","alt":true},"1508833025668":{"name":"花环","left":775,"top":358,"type":"award","width":104,"height":28,"color":"#B6F700","alt":true},"1508833030517":{"name":"活动道具","left":416,"top":153,"type":"tools","width":104,"height":28,"color":"#C0CCDA","alt":true},"1508833035409":{"name":"活动道具","left":416,"top":221,"type":"tools","width":104,"height":28,"color":"#C0CCDA","alt":true},"1508833036598":{"name":"活动道具","left":420,"top":291,"type":"tools","width":104,"height":28,"color":"#C0CCDA","alt":true},"1508833039046":{"name":"人民币","left":412,"top":84,"type":"tools","width":104,"height":28,"color":"#B6F700","alt":true},"1508833144662":{"name":"结束","left":815,"top":274,"type":"end round","width":28,"height":28,"color":"#C0CCDA","alt":true}},"lines":{"1508833129162":{"type":"sl","from":"1508832947835","to":"1508833021703","name":"","dash":false},"1508833130212":{"type":"sl","from":"1508833021703","to":"1508833039046","name":"","dash":false},"1508833131172":{"type":"sl","from":"1508833021703","to":"1508833030517","name":"","dash":false},"1508833132061":{"type":"sl","from":"1508833021703","to":"1508833035409","name":"","dash":false},"1508833134111":{"type":"sl","from":"1508833021703","to":"1508833036598","name":"","dash":false},"1508833135550":{"type":"sl","from":"1508833039046","to":"1508832952596","name":"","dash":false},"1508833136244":{"type":"sl","from":"1508833030517","to":"1508832952596","name":"","dash":false},"1508833138561":{"type":"sl","from":"1508833036598","to":"1508832952596","name":"","dash":false},"1508833139734":{"type":"sl","from":"1508833035409","to":"1508832952596","name":"","dash":false},"1508833140579":{"type":"sl","from":"1508832952596","to":"1508832951324","name":"","dash":false},"1508833141497":{"type":"sl","from":"1508832951324","to":"1508833024198","name":"","dash":false},"1508833142429":{"type":"sl","from":"1508833024198","to":"1508833025668","name":"","dash":false},"1508833146955":{"type":"sl","from":"1508833025668","to":"1508833144662","name":"","dash":false}},"areas":{},"initNum":26}
         * actionState : 1
         * latitude : 22.542648
         * title : 春游活动
         * orgId : 1
         * actionImport : 2
         * videoUrl : null
         * aInfo : 春游活动
         * actionKindName : 旅游
         * sceneId : null
         * id : 64
         * beginTime : 2017-10-26 00:00:00
         * flowId : 21
         * longitude : 113.980425
         * cost : 0
         * planCount : 34
         * address : 深圳世界之窗
         * attendanceCount : 0
         * sceneName : null
         * coverUrl :
         * actionType : 1
         * organizer : 1
         * signupCount : 0
         * actionSafety : 3
         * endTime : 2017-10-26 00:00:00
         */

        private int actionKindId;
        private String flowJson;
        private int actionState;
        private double latitude;
        private String title;
        private int orgId;
        private int actionImport;
        private Object videoUrl;
        private String aInfo;
        private String actionKindName;
        private Object sceneId;
        private int id;
        private String beginTime;
        private int flowId;
        private double longitude;
        private int cost;
        private int planCount;
        private String address;
        private int attendanceCount;
        private Object sceneName;
        private String coverUrl;
        private int actionType;
        private int organizer;
        private int signupCount;
        private int actionSafety;
        private String endTime;

        public int getActionKindId() {
            return actionKindId;
        }

        public void setActionKindId(int actionKindId) {
            this.actionKindId = actionKindId;
        }

        public String getFlowJson() {
            return flowJson;
        }

        public void setFlowJson(String flowJson) {
            this.flowJson = flowJson;
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

        public Object getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(Object videoUrl) {
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
    }
}
