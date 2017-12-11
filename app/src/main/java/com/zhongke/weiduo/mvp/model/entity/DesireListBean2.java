package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by ${xingen} on 2017/9/21.
 * 孩子愿望bean
 */

public class DesireListBean2 {
    private List<ChildWishBean> childWish;

    public List<ChildWishBean> getChildWish() {
        return childWish;
    }

    public void setChildWish(List<ChildWishBean> childWish) {
        this.childWish = childWish;
    }

    public static class ChildWishBean {
        /**
         * coverUrl : null
         * awardId : null
         * audioUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/4GZ2cRWCifd4JH7sAsXiayRYc1508119007477.mp3
         * createTime : 2017-11-18 15:53:23
         * wishInfo : 奖杯
         * wishName : 花环
         * wishType : 1
         * id : 7
         * userId : 131
         * wishState : 1
         */

        private String coverUrl;
        private Object awardId;
        private String audioUrl;
        private String createTime;
        private String wishInfo;
        private String wishName;
        private int wishType;
        private int id;
        private int userId;
        private int wishState;

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public Object getAwardId() {
            return awardId;
        }

        public void setAwardId(Object awardId) {
            this.awardId = awardId;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getWishInfo() {
            return wishInfo;
        }

        public void setWishInfo(String wishInfo) {
            this.wishInfo = wishInfo;
        }

        public String getWishName() {
            return wishName;
        }

        public void setWishName(String wishName) {
            this.wishName = wishName;
        }

        public int getWishType() {
            return wishType;
        }

        public void setWishType(int wishType) {
            this.wishType = wishType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWishState() {
            return wishState;
        }

        public void setWishState(int wishState) {
            this.wishState = wishState;
        }

        @Override
        public String toString() {
            return "ChildWishBean{" +
                    "coverUrl=" + coverUrl +
                    ", awardId=" + awardId +
                    ", audioUrl='" + audioUrl + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", wishInfo='" + wishInfo + '\'' +
                    ", wishName='" + wishName + '\'' +
                    ", wishType=" + wishType +
                    ", id=" + id +
                    ", userId=" + userId +
                    ", wishState=" + wishState +
                    '}';
        }
    }
}
