package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by dgg1 on 2017/11/8.
 */

public class NewOrganizationDetailBean {

    /**
     * org : {"orgType":2,"code":"50","level":0,"createTime":"2017-03-14 11:47:07","collectId":0,"name":"深圳大学","logo":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/s8tBNfrScDrww8GGPCb2Z585x1509417074891.png","fullName":"深圳大学","pId":0,"id":50,"url":"www.shenzhenxiaoxue.com","info":null}
     */

    private OrgBean org;

    public OrgBean getOrg() {
        return org;
    }

    public void setOrg(OrgBean org) {
        this.org = org;
    }

    public static class OrgBean {
        /**
         * orgType : 2
         * code : 50
         * level : 0
         * createTime : 2017-03-14 11:47:07
         * collectId : 0
         * name : 深圳大学
         * logo : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/s8tBNfrScDrww8GGPCb2Z585x1509417074891.png
         * fullName : 深圳大学
         * pId : 0
         * id : 50
         * url : www.shenzhenxiaoxue.com
         * info : null
         */

        private int orgType;
        private String code;
        private int level;
        private String createTime;
        private int collectId;
        private String name;
        private String logo;
        private String fullName;
        private int pId;
        private int id;
        private String url;
        private Object info;

        public int getOrgType() {
            return orgType;
        }

        public void setOrgType(int orgType) {
            this.orgType = orgType;
        }

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCollectId() {
            return collectId;
        }

        public void setCollectId(int collectId) {
            this.collectId = collectId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }
    }
}
