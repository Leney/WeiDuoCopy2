package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 步骤详情实体类
 * Created by llj on 2017/6/26.
 */

public class StepDetailBean {

    /**
     * notes : 注意事项 1.xxxxxxxx 2.xxxxxxxxxx
     * stepContent : 具体步骤1.xxxxx 2.xxxxxx
     * isManager : 0
     * teachList : [{"name":"投影播放","value":"基本语法讲解视频"},{"name":"主屏播放","value":"主讲老师视屏"}]
     * tipList : [{"name":"视频检测"},{"name":"当所有人员到位后提示老师开始教学"}]
     * recordList : [{"name":"侧拍摄像头设置","value":0},{"name":"高拍摄像头设置","value":1},{"name":"麦克风设置","value":1}]
     * assessList : [{"name":"讲文明有礼貌","value":"五星制"},{"name":"专心听讲","value":"评分制"}]
     * resultList : [{"name":"综合评分3星以上","value":"奖励"},{"name":"三星以下","value":"再做一次"}]
     */

    private String notes;
    private String stepContent;
    private int isManager;
    private List<TeachListBean> teachList;
    private List<TipListBean> tipList;
    private List<RecordListBean> recordList;
    private List<AssessListBean> assessList;
    private List<ResultListBean> resultList;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStepContent() {
        return stepContent;
    }

    public void setStepContent(String stepContent) {
        this.stepContent = stepContent;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public List<TeachListBean> getTeachList() {
        return teachList;
    }

    public void setTeachList(List<TeachListBean> teachList) {
        this.teachList = teachList;
    }

    public List<TipListBean> getTipList() {
        return tipList;
    }

    public void setTipList(List<TipListBean> tipList) {
        this.tipList = tipList;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public List<AssessListBean> getAssessList() {
        return assessList;
    }

    public void setAssessList(List<AssessListBean> assessList) {
        this.assessList = assessList;
    }

    public List<ResultListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultListBean> resultList) {
        this.resultList = resultList;
    }

    public static class TeachListBean {
        /**
         * name : 投影播放
         * value : 基本语法讲解视频
         */

        private String name;
        private String value;

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

    public static class TipListBean {
        /**
         * name : 视频检测
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class RecordListBean {
        /**
         * name : 侧拍摄像头设置
         * value : 0
         */

        private String name;
        private int value;

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

    public static class AssessListBean {
        /**
         * name : 讲文明有礼貌
         * value : 五星制
         */

        private String name;
        private String value;

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

    public static class ResultListBean {
        /**
         * name : 综合评分3星以上
         * value : 奖励
         */

        private String name;
        private String value;

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
