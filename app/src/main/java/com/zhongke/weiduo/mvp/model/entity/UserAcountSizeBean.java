package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${xingen} on 2017/11/18.
 */

public class UserAcountSizeBean {

    /**
     * count : {"bPlanCount":6,"sPlanCount":3,"wishCount":0,"scheduleCount":36,"targetCount":2}
     */

    private CountBean count;

    public CountBean getCount() {
        return count;
    }

    public void setCount(CountBean count) {
        this.count = count;
    }

    public static class CountBean {
        /**
         * bPlanCount : 6
         * sPlanCount : 3
         * wishCount : 0
         * scheduleCount : 36
         * targetCount : 2
         */

        private int bPlanCount;
        private int sPlanCount;
        private int wishCount;
        private int scheduleCount;
        private int targetCount;

        public int getBPlanCount() {
            return bPlanCount;
        }

        public void setBPlanCount(int bPlanCount) {
            this.bPlanCount = bPlanCount;
        }

        public int getSPlanCount() {
            return sPlanCount;
        }

        public void setSPlanCount(int sPlanCount) {
            this.sPlanCount = sPlanCount;
        }

        public int getWishCount() {
            return wishCount;
        }

        public void setWishCount(int wishCount) {
            this.wishCount = wishCount;
        }

        public int getScheduleCount() {
            return scheduleCount;
        }

        public void setScheduleCount(int scheduleCount) {
            this.scheduleCount = scheduleCount;
        }

        public int getTargetCount() {
            return targetCount;
        }

        public void setTargetCount(int targetCount) {
            this.targetCount = targetCount;
        }
    }
}
