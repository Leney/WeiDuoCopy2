package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by hyx on 2017/11/21.
 * 发现 推荐列表
 */

public class FindRecommendBean {
    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/BfxxitjFykAE4cbKSEKMcep7x1509963257395.jpg","address":"深圳","aInfo":"活动描述","id":109,"beginTime":"2017-11-09 18:14:52","endTime":"2018-11-22 18:14:54","title":"献爱心，快乐行","thumbUpCount":2},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/BfxxitjFykAE4cbKSEKMcep7x1509963257395.jpg","address":"深圳","aInfo":"活动描述","id":111,"beginTime":"2017-11-08 18:14:52","endTime":"2017-11-30 18:14:54","title":"关爱老人，向世界献爱心","thumbUpCount":null},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/BfxxitjFykAE4cbKSEKMcep7x1509963257395.jpg","address":"深圳","aInfo":"活动描述","id":114,"beginTime":"2017-11-08 18:14:52","endTime":"2017-11-30 18:14:54","title":"红色爱心活动","thumbUpCount":null},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/MJ26HKhhAW2bXkp7nraNywwWk1510215585565.gif","address":"深圳","aInfo":"活动描述","id":117,"beginTime":"2017-11-09 18:14:52","endTime":"2017-11-30 18:14:54","title":"美食之旅","thumbUpCount":null},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/kwJyArP85SF8sTsCXPd5DrBrc1509971467902.jpg","address":"深圳世界之窗","aInfo":"成语接龙","id":119,"beginTime":"2017-11-09 20:31:18","endTime":"2019-02-22 20:31:20","title":"成语接龙","thumbUpCount":null},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/yZzcT2HnYy2fCEmYYpbJxnMGk1510022123050.gif","address":"深圳","aInfo":"唱歌","id":120,"beginTime":"2017-11-07 10:36:50","endTime":"2019-11-07 10:36:52","title":"唱歌比赛","thumbUpCount":null},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Sm44hr3fKtN44SnREBCNxnf4B1510033486291.jpg","address":"深圳","aInfo":"唱歌","id":121,"beginTime":"2017-11-07 10:36:50","endTime":"2020-11-27 10:36:52","title":"游泳比赛","thumbUpCount":null},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/yZzcT2HnYy2fCEmYYpbJxnMGk1510022123050.gif","address":"深圳","aInfo":"唱歌","id":124,"beginTime":"2017-11-07 10:36:50","endTime":"2019-11-07 10:36:52","title":"朗诵比赛","thumbUpCount":null},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/yZzcT2HnYy2fCEmYYpbJxnMGk1510022123050.gif","address":"深圳","aInfo":"唱歌","id":130,"beginTime":"2017-11-07 10:36:50","endTime":"2019-11-07 10:36:52","title":"演讲比赛","thumbUpCount":null},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/kYFQpFytfxraTsstN4KXa8tCt1511231052705.jpg","address":null,"aInfo":"美女","id":145,"beginTime":"2017-11-20 10:24:26","endTime":"2019-11-21 10:24:28","title":"美女直播","thumbUpCount":null}]
     * recordTotal : 10
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
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/BfxxitjFykAE4cbKSEKMcep7x1509963257395.jpg
         * address : 深圳
         * aInfo : 活动描述
         * id : 109
         * beginTime : 2017-11-09 18:14:52
         * endTime : 2018-11-22 18:14:54
         * title : 献爱心，快乐行
         * thumbUpCount : 2
         */

        private String coverUrl;
        private String address;
        private String aInfo;
        private int id;
        private String beginTime;
        private String endTime;
        private String title;
        private int thumbUpCount;
        private boolean isLike;

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

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "coverUrl='" + coverUrl + '\'' +
                    ", address='" + address + '\'' +
                    ", aInfo='" + aInfo + '\'' +
                    ", id=" + id +
                    ", beginTime='" + beginTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", title='" + title + '\'' +
                    ", thumbUpCount=" + thumbUpCount +
                    ", isLike=" + isLike +
                    '}';
        }
    }
}
