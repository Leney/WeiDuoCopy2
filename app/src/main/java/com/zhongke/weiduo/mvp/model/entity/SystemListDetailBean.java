package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by ${xingen} on 2017/11/9.
 */

public class SystemListDetailBean {

    private List<AbilityBookBean> ability_book;
    private List<CatalogBean> catalog;

    public List<AbilityBookBean> getAbility_book() {
        return ability_book;
    }

    public void setAbility_book(List<AbilityBookBean> ability_book) {
        this.ability_book = ability_book;
    }

    public List<CatalogBean> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<CatalogBean> catalog) {
        this.catalog = catalog;
    }

    public static class AbilityBookBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/pkty4haDWAWXnHWtyYbc3P4ay1508142114054.jpg
         * createUserId : 90
         * bName : 养成良好的行为习惯
         * createTime : 2017-10-16 16:21:47
         * bInfo : 养成良好的行为习惯微体系
         * abilityTag : 养成良好的行为习惯
         * id : 111
         * prospectusUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/RG7nEdNrwjEAXHGS2Dwj4kZdh1508141976278.pdf
         * introduceVideo : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/4YGntxHyE7eJXnp4Cy5Y6ZfTm1508141981765.wmv
         * thumbUpCount : 0
         * orgId : 1
         * bookType : 4
         */

        private String coverUrl;
        private int createUserId;
        private String bName;
        private String createTime;
        private String bInfo;
        private String abilityTag;
        private int id;
        private String prospectusUrl;
        private String introduceVideo;
        private int thumbUpCount;
        private int orgId;
        private int bookType;

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

        public String getBInfo() {
            return bInfo;
        }

        public void setBInfo(String bInfo) {
            this.bInfo = bInfo;
        }

        public String getAbilityTag() {
            return abilityTag;
        }

        public void setAbilityTag(String abilityTag) {
            this.abilityTag = abilityTag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProspectusUrl() {
            return prospectusUrl;
        }

        public void setProspectusUrl(String prospectusUrl) {
            this.prospectusUrl = prospectusUrl;
        }

        public String getIntroduceVideo() {
            return introduceVideo;
        }

        public void setIntroduceVideo(String introduceVideo) {
            this.introduceVideo = introduceVideo;
        }

        public int getThumbUpCount() {
            return thumbUpCount;
        }

        public void setThumbUpCount(int thumbUpCount) {
            this.thumbUpCount = thumbUpCount;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getBookType() {
            return bookType;
        }

        public void setBookType(int bookType) {
            this.bookType = bookType;
        }
    }

    public static class CatalogBean {
        /**
         * code : 584
         * level : 0
         * freeState : 1
         * fullName : 养成良好的行为习惯
         * pId : 0
         * type : 0
         * resId : null
         * url : http://www.baidu.com?id=584
         * bookId : 111
         * createTime : 2017-10-16 16:21:47
         * name : 养成良好的行为习惯
         * id : 584
         * resJson : {}
         */

        private String code;
        private int level;
        private int freeState;
        private String fullName;
        private int pId;
        private int type;
        private int  resId;
        private String url;
        private int bookId;
        private String createTime;
        private String name;
        private int id;
        private String resJson;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getFreeState() {
            return freeState;
        }

        public void setFreeState(int freeState) {
            this.freeState = freeState;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getPId() {
            return pId;
        }

        public void setPId(int pId) {
            this.pId = pId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getResId() {
            return resId;
        }

        public void setResId(int  resId) {
            this.resId = resId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getResJson() {
            return resJson;
        }

        public void setResJson(String resJson) {
            this.resJson = resJson;
        }
    }
}
