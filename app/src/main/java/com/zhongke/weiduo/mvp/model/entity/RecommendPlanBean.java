package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 规划详情中，推荐的计划列表
 * Created by llj on 2017/11/8.
 */

public class RecommendPlanBean {

    private List<SPlanBean> s_plan;

    public List<SPlanBean> getS_plan() {
        return s_plan;
    }

    public void setS_plan(List<SPlanBean> s_plan) {
        this.s_plan = s_plan;
    }

    public static class SPlanBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cTbasa5Yh5PTzsW1506678551337.jpg
         * bName : 微计划
         * userCount : 3
         * id : 95
         */

        private String coverUrl;
        private String bName;
        private int userCount;
        private int id;

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

        public int getUserCount() {
            return userCount;
        }

        public void setUserCount(int userCount) {
            this.userCount = userCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
