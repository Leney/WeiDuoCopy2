package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by llj on 2017/11/7.
 */

public class SchemeBean2 {
    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/NxphdkFNN24Nz4SfeCbX3KmmA1510217728510.jpg","bName":"美食策划","userCount":3,"bInfo":"吃美食","id":81,"thumbUpCount":5},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/sZewSpEhBzAQimS65NGFeBxWS1510217705506.jpg","bName":"美食节","userCount":1,"bInfo":"吃美食","id":82,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/WHaAPjJ6aPspHPwxXmwNNYj2r1510217668866.jpg","bName":"品味美食","userCount":0,"bInfo":"美食","id":94,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/ikjjbBSiWdjJEk3KCXKp3GctR1510217639146.jpg","bName":"美食","userCount":0,"bInfo":"美食","id":134,"thumbUpCount":1},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/hQJdFD5Sb2dyjs2mw2x2TksAy1510217471038.jpg","bName":"继续减肥","userCount":0,"bInfo":"减肥","id":136,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/khJpkkHBFnFFnCmjeciiT7kTh1510217427315.jpg","bName":"减肥计划","userCount":0,"bInfo":"坚持减肥，锻炼身体","id":140,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/QxTm2Ft6zGYT56awHTiR6kfP61510217366304.jpg","bName":"美食计划","userCount":0,"bInfo":"吃美食","id":146,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cwW7pBZErHRWtGQTPr6inMAbF1510213772957.gif","bName":"减肥","userCount":1,"bInfo":"规划减肥","id":155,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/KyynNGmS4hnnEdSBzmjSrzaR61510217357503.jpg","bName":"美食之旅","userCount":0,"bInfo":"美食之旅","id":159,"thumbUpCount":0},{"coverUrl":"","bName":"我规划","userCount":1,"bInfo":"做规划","id":161,"thumbUpCount":0},{"coverUrl":"","bName":"我规划","userCount":1,"bInfo":"做规划","id":162,"thumbUpCount":0}]
     * recordTotal : 11
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
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/NxphdkFNN24Nz4SfeCbX3KmmA1510217728510.jpg
         * bName : 美食策划
         * userCount : 3
         * bInfo : 吃美食
         * id : 81
         * thumbUpCount : 5
         */

        private String coverUrl;
        private String bName;
        private int userCount;
        private String bInfo;
        private int id;
        private int thumbUpCount;
        /**
         * 是否点赞标识
         */
        private boolean isLike = false;

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

        public int getThumbUpCount() {
            return thumbUpCount;
        }

        public void setThumbUpCount(int thumbUpCount) {
            this.thumbUpCount = thumbUpCount;
        }

        public boolean isLike() {
            return isLike;
        }

        public void setLike(boolean like) {
            isLike = like;
        }
    }
}
