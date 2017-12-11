package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by ${xingen} on 2017/7/11.
 * 活动流程详情
 */

public class ActivityFlowDetailBean {


    /**
     * id : 10001
     * managerUserId : 10021
     * managerUserName : 爸爸
     * type : 0
     * isManager : 0
     * state : 0
     * address : 活动地址
     * longitude : 10251.226656
     * latitude : 5515.36565
     * startTime : 521205151551
     * endTime : 521205993332
     * preUserCount : 20
     * curApplyUserCount : 18
     * money : 200
     * unit : 元
     * isUserOk : 1
     * isMoneyOk : 0
     * isToolsOk : 1
     * textPlanDiscribe : 当是纯文本活动类型时，显示的活动安排相关的长文本数据描述
     * toolList : [{"id":1001,"name":"工具名称1"},{"id":1002,"name":"工具名称2"}]
     * stepList : [{"id":1001,"name":"步骤一","value":"步骤所做内容名称"},{"id":1002,"name":"步骤二","value":"步骤所做内容名称"}]
     * cancelList : ["成员不齐","天气原因"]
     */

    private int id;
    private int managerUserId;
    private String managerUserName;
    private int type;
    private int isManager;
    private int state;
    private String address;
    private double longitude;
    private double latitude;
    private long startTime;
    private long endTime;
    private int preUserCount;
    private int curApplyUserCount;
    private int money;
    private String unit;
    private int isUserOk;
    private int isMoneyOk;
    private int isToolsOk;
    private String textPlanDiscribe;
    private List<ToolListBean> toolList;
    private List<StepListBean> stepList;
    private List<String> cancelList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(int managerUserId) {
        this.managerUserId = managerUserId;
    }

    public String getManagerUserName() {
        return managerUserName;
    }

    public void setManagerUserName(String managerUserName) {
        this.managerUserName = managerUserName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getPreUserCount() {
        return preUserCount;
    }

    public void setPreUserCount(int preUserCount) {
        this.preUserCount = preUserCount;
    }

    public int getCurApplyUserCount() {
        return curApplyUserCount;
    }

    public void setCurApplyUserCount(int curApplyUserCount) {
        this.curApplyUserCount = curApplyUserCount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getIsUserOk() {
        return isUserOk;
    }

    public void setIsUserOk(int isUserOk) {
        this.isUserOk = isUserOk;
    }

    public int getIsMoneyOk() {
        return isMoneyOk;
    }

    public void setIsMoneyOk(int isMoneyOk) {
        this.isMoneyOk = isMoneyOk;
    }

    public int getIsToolsOk() {
        return isToolsOk;
    }

    public void setIsToolsOk(int isToolsOk) {
        this.isToolsOk = isToolsOk;
    }

    public String getTextPlanDiscribe() {
        return textPlanDiscribe;
    }

    public void setTextPlanDiscribe(String textPlanDiscribe) {
        this.textPlanDiscribe = textPlanDiscribe;
    }

    public List<ToolListBean> getToolList() {
        return toolList;
    }

    public void setToolList(List<ToolListBean> toolList) {
        this.toolList = toolList;
    }

    public List<StepListBean> getStepList() {
        return stepList;
    }

    public void setStepList(List<StepListBean> stepList) {
        this.stepList = stepList;
    }

    public List<String> getCancelList() {
        return cancelList;
    }

    public void setCancelList(List<String> cancelList) {
        this.cancelList = cancelList;
    }

    public static class ToolListBean {
        /**
         * id : 1001
         * name : 工具名称1
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class StepListBean {
        /**
         * id : 1001
         * name : 步骤一
         * value : 步骤所做内容名称
         */

        private int id;
        private String name;
        private String value;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
