package com.zhongke.weiduo.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ${xingen} on 2017/7/11.
 *
 * 活动详情基本数据
 *
 */
public class ActivityDetailBaseDataBean {

    /**
     * id : 10001
     * managerUserId : 10021
     * managerUserName : 爸爸
     * state : 0
     * title : 活动名称
     * describe : 活动简短描述
     * imgUrl : http://www.sfsfsdfsf.jpg
     * import : 0
     * isModel : 0
     * coreTarget : 核心主要锻炼目标
     * judgeType : 0
     * targetList : [{"id":1001,"name":"注意力","value":49},{"id":1002,"name":"交际能力","value":22},{"id":1003,"name":"观察力","value":29}]
     */

    private int id;
    private int managerUserId;
    private String managerUserName;
    private int state;
    private String title;
    private String describe;
    private String imgUrl;
    @SerializedName("import")
    private int importX;
    private int isModel;
    private String coreTarget;
    private int judgeType;
    private List<TargetListBean> targetList;

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImportX() {
        return importX;
    }

    public void setImportX(int importX) {
        this.importX = importX;
    }

    public int getIsModel() {
        return isModel;
    }

    public void setIsModel(int isModel) {
        this.isModel = isModel;
    }

    public String getCoreTarget() {
        return coreTarget;
    }

    public void setCoreTarget(String coreTarget) {
        this.coreTarget = coreTarget;
    }

    public int getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(int judgeType) {
        this.judgeType = judgeType;
    }

    public List<TargetListBean> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<TargetListBean> targetList) {
        this.targetList = targetList;
    }

    public static class TargetListBean {
        /**
         * id : 1001
         * name : 注意力
         * value : 49
         */

        private int id;
        private String name;
        private int value;

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

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
