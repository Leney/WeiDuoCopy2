package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 我的目标列表bean
 * Created by llj on 2017/11/10.
 */

public class MyTargetBean {
    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"targetId":130,"iId":null,"imageUrl":null,"name":"好好吃饭","id":130,"userId":90,"percent":null}]
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
         * targetId : 130
         * iId : null
         * imageUrl : null
         * name : 好好吃饭
         * id : 130
         * userId : 90
         * percent : null
         */
        private int targetId;
        private Object iId;
        private Object imageUrl;
        private String name;
        private int id;
        private int userId;
        private double percent;

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public Object getIId() {
            return iId;
        }

        public void setIId(Object iId) {
            this.iId = iId;
        }

        public Object getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(Object imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
