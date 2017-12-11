package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/15.
 */

public class NewFamilyTag {

    private List<TagFamilyListBean> tagFamilyList;

    public List<TagFamilyListBean> getTagFamilyList() {
        return tagFamilyList;
    }

    public void setTagFamilyList(List<TagFamilyListBean> tagFamilyList) {
        this.tagFamilyList = tagFamilyList;
    }

    public static class TagFamilyListBean {
        /**
         * createUserId : 90
         * createTime : 2017-11-09 18:32:48
         * tagKind : 7
         * name : 标签名⑥
         * id : 74
         * info : null
         */

        private int createUserId;
        private String createTime;
        private int tagKind;
        private String name;
        private int id;
        private Object info;
        private String coverUrl;

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getTagKind() {
            return tagKind;
        }

        public void setTagKind(int tagKind) {
            this.tagKind = tagKind;
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

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }
    }
}
