package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by ${xingen} on 2017/7/11.
 * 已经结束活动成果评价详情
 */

public class ActivityResultFinishBean {

    /**
     * id : 10001
     * managerUserId : 10021
     * managerUserName : 爸爸
     * type : 0
     * isManager : 0
     * isHaveComment : 0
     * judgeType : 0
     * commentDes : 具体评论信息
     * resultList : [{"type":0,"url":"www.sddfsfd.jpg"},{"type":1,"url":"www.sddfsfd.mp4"}]
     * awardList : [{"userId":1002,"userName":"妈妈","giftList":[{"type":0,"name":"金币","value":60,"imgUrl":""},{"type":2,"name":"泰迪熊公仔","value":1,"imgUrl":"www.sdfsfd.jpg"}]},{"userId":1001,"userName":"爸爸","giftList":[{"type":0,"name":"金币","value":60,"imgUrl":""},{"type":2,"name":"泰迪熊公仔","value":1,"imgUrl":"www.sdfsfd.jpg"}]}]
     */

    private int id;
    private int managerUserId;
    private String managerUserName;
    private int type;
    private int isManager;
    private int isHaveComment;
    private int judgeType;
    private String commentDes;
    private List<ResultListBean> resultList;
    private List<AwardListBean> awardList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(int managerUserId) {
        this.managerUserId = managerUserId;
    }

    public String getManagerUserName() {
        return managerUserName;
    }

    public void setManagerUserName(String managerUserName) {
        this.managerUserName = managerUserName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public int getIsHaveComment() {
        return isHaveComment;
    }

    public void setIsHaveComment(int isHaveComment) {
        this.isHaveComment = isHaveComment;
    }

    public int getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(int judgeType) {
        this.judgeType = judgeType;
    }

    public String getCommentDes() {
        return commentDes;
    }

    public void setCommentDes(String commentDes) {
        this.commentDes = commentDes;
    }

    public List<ResultListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultListBean> resultList) {
        this.resultList = resultList;
    }

    public List<AwardListBean> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<AwardListBean> awardList) {
        this.awardList = awardList;
    }

    public static class ResultListBean {
        /**
         * type : 0
         * url : www.sddfsfd.jpg
         */

        private int type;
        private String url;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class AwardListBean {
        /**
         * userId : 1002
         * userName : 妈妈
         * giftList : [{"type":0,"name":"金币","value":60,"imgUrl":""},{"type":2,"name":"泰迪熊公仔","value":1,"imgUrl":"www.sdfsfd.jpg"}]
         */

        private int userId;
        private String userName;
        private List<GiftListBean> giftList;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<GiftListBean> getGiftList() {
            return giftList;
        }

        public void setGiftList(List<GiftListBean> giftList) {
            this.giftList = giftList;
        }

        public static class GiftListBean {
            /**
             * type : 0
             * name : 金币
             * value : 60
             * imgUrl :
             */

            private int type;
            private String name;
            private int value;
            private String imgUrl;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}
