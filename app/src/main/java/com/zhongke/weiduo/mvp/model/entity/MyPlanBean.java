package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 我的计划bean
 * Created by llj on 2017/11/13.
 */

public class MyPlanBean {
    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"coverUrl":"","bName":"美食节","createTime":"2017-11-30 16:37:11","id":196,"userId":90,"percent":null}]
     * recordTotal : 1
     */

    private int pageTotal;
    private int pageIndex;
    private int recordTotal;
    private List<RecordsBean> records;

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * coverUrl :
         * bName : 美食节
         * createTime : 2017-11-30 16:37:11
         * id : 196
         * userId : 90
         * percent : null
         */

        private String coverUrl;
        private String bName;
        private String createTime;
        private int id;
        private int userId;
        private double percent;

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getBName() {
            return bName;
        }

        public void setBName(String bName) {
            this.bName = bName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getPercent() {
            return percent;
        }

        public void setPercent(double percent) {
            this.percent = percent;
        }
    }
}
