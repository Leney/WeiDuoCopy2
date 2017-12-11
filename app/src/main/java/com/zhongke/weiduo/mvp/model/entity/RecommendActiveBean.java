package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 规划详情中，推荐的计划列表
 * Created by llj on 2017/11/8.
 */

public class RecommendActiveBean {

    private List<ActionBean> action;

    public List<ActionBean> getAction() {
        return action;
    }

    public void setAction(List<ActionBean> action) {
        this.action = action;
    }

    public static class ActionBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/zYtJsNhGyQz2zKs7wDDRkZE8Q1512024403023.jpg
         * joinCount : 2
         * id : 117
         * title : 美食之旅
         */

        private String coverUrl;
        private int joinCount;
        private int id;
        private String title;

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getJoinCount() {
            return joinCount;
        }

        public void setJoinCount(int joinCount) {
            this.joinCount = joinCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
