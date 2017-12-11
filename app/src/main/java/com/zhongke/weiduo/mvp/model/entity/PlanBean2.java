package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/18.
 * 计划实体类
 */

public class PlanBean2 {
    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cTbasa5Yh5PTzsW1506678551337.jpg","bName":"微计划","userCount":2,"bInfo":"微计划","id":95,"thumbUpCount":12},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/pCr6tf382Q3r66dpeRw5FHhjG1507951658931.jpg","bName":"好好喝水0","userCount":0,"bInfo":"让孩子养成好好喝水的习惯","id":103,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/FciPGniADczKpem5sBCtGrKSp1508148332994.png","bName":"聊天","userCount":0,"bInfo":"好好吃饭","id":119,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/zsAc2BK4HAyfDAKixdrhbS2bK1509107580376.jpg","bName":"每天早上起来读单词","userCount":0,"bInfo":"可爱","id":128,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jQr4mCWrtTx5KTGPtZZbbSDWM1508207393192.gif","bName":"每周学习2次乐器","userCount":0,"bInfo":"可爱","id":131,"thumbUpCount":1},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/WQAAD6f6ik7XdTSNMdAebkCPW1509696577260.jpg","bName":"我的计划","userCount":0,"bInfo":"我要做计划","id":151,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Mznwbx6ZrKRB27dRXpKQSfNxP1509696633391.gif","bName":"我计划","userCount":0,"bInfo":"做计划","id":154,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/AhwDrTHpyGYiwf8DSA4BWDMJf1510214206834.gif","bName":"跑步","userCount":0,"bInfo":"跑步","id":157,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/j5CMfGB8i4Cr2a4kARh2Mzx3t1510215342538.gif","bName":"吃美食","userCount":0,"bInfo":"吃美食","id":160,"thumbUpCount":0}]
     * recordTotal : 9
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
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cTbasa5Yh5PTzsW1506678551337.jpg
         * bName : 微计划
         * userCount : 2
         * bInfo : 微计划
         * id : 95
         * thumbUpCount : 12
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

//    /**
//     * pageTotal : 1
//     * pageIndex : 1
//     * records : [{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cTbasa5Yh5PTzsW1506678551337.jpg","bName":"微计划","bInfo":"微计划","id":95,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/pCr6tf382Q3r66dpeRw5FHhjG1507951658931.jpg","bName":"好好喝水0","bInfo":"让孩子养成好好喝水的习惯","id":103,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/FciPGniADczKpem5sBCtGrKSp1508148332994.png","bName":"聊天","bInfo":"好好吃饭","id":119,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/zsAc2BK4HAyfDAKixdrhbS2bK1509107580376.jpg","bName":"每天早上起来读单词","bInfo":"可爱","id":128,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jQr4mCWrtTx5KTGPtZZbbSDWM1508207393192.gif","bName":"每周学习2次乐器","bInfo":"可爱","id":131,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/WQAAD6f6ik7XdTSNMdAebkCPW1509696577260.jpg","bName":"我的计划","bInfo":"我要做计划","id":151,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Mznwbx6ZrKRB27dRXpKQSfNxP1509696633391.gif","bName":"我计划","bInfo":"做计划","id":154,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/AhwDrTHpyGYiwf8DSA4BWDMJf1510214206834.gif","bName":"跑步","bInfo":"跑步","id":157,"thumbUpCount":0},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/j5CMfGB8i4Cr2a4kARh2Mzx3t1510215342538.gif","bName":"吃美食","bInfo":"吃美食","id":160,"thumbUpCount":0}]
//     * recordTotal : 9
//     */
//
//    private int pageTotal;
//    private int pageIndex;
//    private int recordTotal;
//    private List<RecordsBean> records;
//
//    public int getPageTotal() {
//        return pageTotal;
//    }
//
//    public void setPageTotal(int pageTotal) {
//        this.pageTotal = pageTotal;
//    }
//
//    public int getPageIndex() {
//        return pageIndex;
//    }
//
//    public void setPageIndex(int pageIndex) {
//        this.pageIndex = pageIndex;
//    }
//
//    public int getRecordTotal() {
//        return recordTotal;
//    }
//
//    public void setRecordTotal(int recordTotal) {
//        this.recordTotal = recordTotal;
//    }
//
//    public List<RecordsBean> getRecords() {
//        return records;
//    }
//
//    public void setRecords(List<RecordsBean> records) {
//        this.records = records;
//    }
//
//    public static class RecordsBean {
//        /**
//         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cTbasa5Yh5PTzsW1506678551337.jpg
//         * bName : 微计划
//         * bInfo : 微计划
//         * id : 95
//         * thumbUpCount : 0
//         */
//
//        private String coverUrl;
//        private String bName;
//        private String bInfo;
//        private int id;
//        private int thumbUpCount;
//        /**
//         * 是否点赞标识
//         */
//        private boolean isLike = false;
//
//        public String getCoverUrl() {
//            return coverUrl;
//        }
//
//        public void setCoverUrl(String coverUrl) {
//            this.coverUrl = coverUrl;
//        }
//
//        public String getBName() {
//            return bName;
//        }
//
//        public void setBName(String bName) {
//            this.bName = bName;
//        }
//
//        public String getBInfo() {
//            return bInfo;
//        }
//
//        public void setBInfo(String bInfo) {
//            this.bInfo = bInfo;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getThumbUpCount() {
//            return thumbUpCount;
//        }
//
//        public void setThumbUpCount(int thumbUpCount) {
//            this.thumbUpCount = thumbUpCount;
//        }
//
//        public boolean isLike() {
//            return isLike;
//        }
//
//        public void setLike(boolean like) {
//            isLike = like;
//        }
//    }
}
