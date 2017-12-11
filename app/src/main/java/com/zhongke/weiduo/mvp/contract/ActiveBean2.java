package com.zhongke.weiduo.mvp.contract;

import java.util.List;

/**
 * Created by hyx on 2017/11/6.
 */

public class ActiveBean2 {


    private List<ActionBean> action;

    public List<ActionBean> getAction() {
        return action;
    }

    public void setAction(List<ActionBean> action) {
        this.action = action;
    }

    public static class ActionBean {
        /**
         * address : 深圳动物
         * planCount : 300
         * id : 63
         * beginTime : 2017-10-25 00:00:00
         * endTime : 2017-10-25 00:00:00
         * title : 去动物园
         */

        private String address;
        private int planCount;
        private int id;
        private String beginTime;
        private String endTime;
        private String title;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "ActionBean{" +
                    "address='" + address + '\'' +
                    ", planCount=" + planCount +
                    ", id=" + id +
                    ", beginTime='" + beginTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ActiveBean2{" +
                "action=" + action +
                '}';
    }
}
