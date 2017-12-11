package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 推荐规划bean
 * Created by llj on 2017/11/10.
 */

public class RecommendSchemeBean {
    private List<BPlanBean> b_plan;

    public List<BPlanBean> getB_plan() {
        return b_plan;
    }

    public void setB_plan(List<BPlanBean> b_plan) {
        this.b_plan = b_plan;
    }

    public static class BPlanBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/hQJdFD5Sb2dyjs2mw2x2TksAy1510217471038.jpg
         * bName : 继续减肥
         * userCount : 0
         * id : 136
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
