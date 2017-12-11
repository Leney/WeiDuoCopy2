package com.zhongke.weiduo.mvp.model.entity.eventschedule;

import java.util.List;

/**
 * Created by hyx on 2017/11/7.
 */

public class EventScheduleResult {


    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"actionId":null,"id":10,"beginTime":"2017-10-31 17:00:00","endTime":"2017-10-31 21:00:00","userId":90,"actionName":"吃饭"},{"actionId":null,"id":11,"beginTime":"2017-10-31 17:00:00","endTime":"2017-10-31 21:00:00","userId":90,"actionName":"吃饭"},{"actionId":null,"id":12,"beginTime":"2017-10-31 17:00:00","endTime":"2017-10-31 21:00:00","userId":90,"actionName":"吃饭"},{"actionId":null,"id":13,"beginTime":"2017-10-31 17:00:00","endTime":"2017-10-31 21:00:00","userId":90,"actionName":"吃饭"},{"actionId":64,"id":14,"beginTime":"2017-10-26 00:00:00","endTime":"2017-10-26 00:00:00","userId":90,"actionName":"春游活动"}]
     * recordTotal : 5
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
         * actionId : null
         * id : 10
         * beginTime : 2017-10-31 17:00:00
         * endTime : 2017-10-31 21:00:00
         * userId : 90
         * actionName : 吃饭
         */

        private int actionId;
        private int id;
        private String beginTime;
        private String endTime;
        private int userId;
        private String actionName;

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getActionName() {
            return actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "actionId=" + actionId +
                    ", id=" + id +
                    ", beginTime='" + beginTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", userId=" + userId +
                    ", actionName='" + actionName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "EventScheduleResult{" +
                "pageTotal=" + pageTotal +
                ", pageIndex=" + pageIndex +
                ", recordTotal=" + recordTotal +
                ", records=" + records +
                '}';
    }
}
