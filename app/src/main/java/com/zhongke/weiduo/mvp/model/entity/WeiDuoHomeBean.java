package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by ${xingen} on 2017/11/9.
 */

public class WeiDuoHomeBean {
    private List<ActionBean> action;
    private List<SPlanBean> s_plan;
    private List<BPlanBean> b_plan;
    private List<StructureBean> structure;

    public List<ActionBean> getAction() {
        return action;
    }

    public void setAction(List<ActionBean> action) {
        this.action = action;
    }

    public List<SPlanBean> getS_plan() {
        return s_plan;
    }

    public void setS_plan(List<SPlanBean> s_plan) {
        this.s_plan = s_plan;
    }

    public List<BPlanBean> getB_plan() {
        return b_plan;
    }

    public void setB_plan(List<BPlanBean> b_plan) {
        this.b_plan = b_plan;
    }

    public List<StructureBean> getStructure() {
        return structure;
    }

    public void setStructure(List<StructureBean> structure) {
        this.structure = structure;
    }

    public static class ActionBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/i43Ze2atpGAkKR3NddQacS22m1511772778597.jpg
         * address : 深圳
         * planCount : 300
         * aInfo : 活动描述
         * id : 109
         * beginTime : 2017-11-28 16:29:52
         * endTime : 2017-11-29 16:31:30
         * title : 植物
         */

        private String coverUrl;
        private String address;
        private int planCount;
        private String aInfo;
        private int id;
        private String beginTime;
        private String endTime;
        private String title;

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

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

        public String getAInfo() {
            return aInfo;
        }

        public void setAInfo(String aInfo) {
            this.aInfo = aInfo;
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
    }

    public static class SPlanBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cTbasa5Yh5PTzsW1506678551337.jpg
         * bName : 微计划
         * bInfo : 微计划
         * id : 95
         */

        private String coverUrl;
        private String bName;
        private String bInfo;
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

        public String getBInfo() {
            return bInfo;
        }

        public void setBInfo(String bInfo) {
            this.bInfo = bInfo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class BPlanBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/NxphdkFNN24Nz4SfeCbX3KmmA1510217728510.jpg
         * bName : 美食策划
         * bInfo : 吃美食
         * id : 81
         */

        private String coverUrl;
        private String bName;
        private String bInfo;
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

        public String getBInfo() {
            return bInfo;
        }

        public void setBInfo(String bInfo) {
            this.bInfo = bInfo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class StructureBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/pkty4haDWAWXnHWtyYbc3P4ay1508142114054.jpg
         * bName : 养成良好的行为习惯
         * bInfo : 养成良好的行为习惯微体系
         * id : 111
         */

        private String coverUrl;
        private String bName;
        private String bInfo;
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

        public String getBInfo() {
            return bInfo;
        }

        public void setBInfo(String bInfo) {
            this.bInfo = bInfo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
