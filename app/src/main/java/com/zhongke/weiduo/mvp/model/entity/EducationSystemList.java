package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by ${xingen} on 2017/11/9.
 */

public class EducationSystemList {

    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/pkty4haDWAWXnHWtyYbc3P4ay1508142114054.jpg","bName":"养成良好的行为习惯","bInfo":"养成良好的行为习惯微体系","id":111},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/6j4GPYDmQycACZ38WeJtfy7R41509016942102.jpg","bName":"尊师重教","bInfo":"尊师重教微体系","id":112}]
     * recordTotal : 2
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
