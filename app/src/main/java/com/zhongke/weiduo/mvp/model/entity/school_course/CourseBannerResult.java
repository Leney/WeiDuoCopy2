package com.zhongke.weiduo.mvp.model.entity.school_course;

import java.util.List;

/**
 * Created by hyx on 2017/11/7.
 * 学堂banner结果
 */

public class CourseBannerResult {

    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"baState":1,"bannerType":4,"createTime":"2017-11-04 09:44:58","gOrder":1,"imageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/nWhbYCM5GwtMsrzQtws8jQAhd1509759866872.jpg","bannerName":"我是课程","id":5,"showBeginTime":"2017-11-04 00:00:00","showEndTime":"2017-11-29 00:00:00","url":"/"}]
     * recordTotal : 1
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
         * baState : 1
         * bannerType : 4
         * createTime : 2017-11-04 09:44:58
         * gOrder : 1
         * imageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/nWhbYCM5GwtMsrzQtws8jQAhd1509759866872.jpg
         * bannerName : 我是课程
         * id : 5
         * showBeginTime : 2017-11-04 00:00:00
         * showEndTime : 2017-11-29 00:00:00
         * url : /
         */

        //状态
        private int baState;
        //类型
        private int bannerType;
        private String createTime;
        //排序
        private int gOrder;
        private String imageUrl;
        private String bannerName;
        private int id;
        private String showBeginTime;
        private String showEndTime;
        private String url;

        public int getBaState() {
            return baState;
        }

        public void setBaState(int baState) {
            this.baState = baState;
        }

        public int getBannerType() {
            return bannerType;
        }

        public void setBannerType(int bannerType) {
            this.bannerType = bannerType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getGOrder() {
            return gOrder;
        }

        public void setGOrder(int gOrder) {
            this.gOrder = gOrder;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getBannerName() {
            return bannerName;
        }

        public void setBannerName(String bannerName) {
            this.bannerName = bannerName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShowBeginTime() {
            return showBeginTime;
        }

        public void setShowBeginTime(String showBeginTime) {
            this.showBeginTime = showBeginTime;
        }

        public String getShowEndTime() {
            return showEndTime;
        }

        public void setShowEndTime(String showEndTime) {
            this.showEndTime = showEndTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "baState=" + baState +
                    ", bannerType=" + bannerType +
                    ", createTime='" + createTime + '\'' +
                    ", gOrder=" + gOrder +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", bannerName='" + bannerName + '\'' +
                    ", id=" + id +
                    ", showBeginTime='" + showBeginTime + '\'' +
                    ", showEndTime='" + showEndTime + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CourseBannerResult{" +
                "pageTotal=" + pageTotal +
                ", pageIndex=" + pageIndex +
                ", recordTotal=" + recordTotal +
                ", records=" + records +
                '}';
    }
}
