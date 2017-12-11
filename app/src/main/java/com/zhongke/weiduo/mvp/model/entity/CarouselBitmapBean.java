package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/7.
 * 轮播图实体类
 */

public class CarouselBitmapBean {
    @Override
    public String toString() {
        return "CarouselBitmapBean{" +
                "pageTotal=" + pageTotal +
                ", pageIndex=" + pageIndex +
                ", recordTotal=" + recordTotal +
                ", records=" + records +
                '}';
    }

    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"baState":1,"bannerType":2,"createTime":"2017-11-03 14:03:36","gOrder":5,"imageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/kaEPmKHWpnPAGnArz8zEWxwrj1508575119920.jpg","bannerName":"我是大美女","id":3,"url":"/"}]
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
                    ", url='" + url + '\'' +
                    '}';
        }

        /**
         * baState : 1
         * bannerType : 2
         * createTime : 2017-11-03 14:03:36
         * gOrder : 5
         * imageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/kaEPmKHWpnPAGnArz8zEWxwrj1508575119920.jpg
         * bannerName : 我是大美女
         * id : 3
         * url : /
         */

        private int baState;
        private int bannerType;
        private String createTime;
        private int gOrder;
        private String imageUrl;
        private String bannerName;
        private int id;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
