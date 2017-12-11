package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by ${xingen} on 2017/7/1.
 */

public class ActivityRewardBean {

    /**
     * resultCode : 0
     * msg :
     * type : 0
     * targetList : [{"id":10021,"name":"观察能力","describe":"观察能力描述xxxxxxxxxx"},{"id":10021,"name":"注意力","describe":"注意力描述xxxxxxxxxx"}]
     * awardList : [{"name":"五星奖励","list":[{"userId":1002,"userName":"爸爸","giftList":[{"type":0,"value":90,"unit":"金币","url":""},{"type":1,"value":90,"unit":"分","url":""},{"type":2,"value":0,"unit":"","url":"http:www.ddfdfd.jpg"}]},{"userId":1003,"userName":"妈妈","giftList":[{"type":0,"value":90,"unit":"金币","url":""},{"type":1,"value":90,"unit":"分","url":""},{"type":2,"value":0,"unit":"","url":"http:www.ddfdfd.jpg"}]}]},{"name":"三星奖励","list":[{"userId":1002,"userName":"爸爸","giftList":[{"type":0,"value":80,"unit":"金币","url":""},{"type":1,"value":80,"unit":"分","url":""},{"type":2,"value":0,"unit":"","url":"http:www.ddfdfd.jpg"}]},{"userId":1003,"userName":"妈妈","giftList":[{"type":0,"value":80,"unit":"金币","url":""},{"type":1,"value":80,"unit":"分","url":""},{"type":2,"value":0,"unit":"","url":"http:www.ddfdfd.jpg"}]}]}]
     */

    private int resultCode;
    private String msg;
    private int type;
    private List<TargetListBean> targetList;
    private List<AwardListBean> awardList;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<TargetListBean> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<TargetListBean> targetList) {
        this.targetList = targetList;
    }

    public List<AwardListBean> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<AwardListBean> awardList) {
        this.awardList = awardList;
    }

    public static class TargetListBean {
        /**
         * id : 10021
         * name : 观察能力
         * describe : 观察能力描述xxxxxxxxxx
         */

        private int id;
        private String name;
        private String describe;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }
    }

    public static class AwardListBean {
        /**
         * name : 五星奖励
         * list : [{"userId":1002,"userName":"爸爸","giftList":[{"type":0,"value":90,"unit":"金币","url":""},{"type":1,"value":90,"unit":"分","url":""},{"type":2,"value":0,"unit":"","url":"http:www.ddfdfd.jpg"}]},{"userId":1003,"userName":"妈妈","giftList":[{"type":0,"value":90,"unit":"金币","url":""},{"type":1,"value":90,"unit":"分","url":""},{"type":2,"value":0,"unit":"","url":"http:www.ddfdfd.jpg"}]}]
         */

        private String name;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * userId : 1002
             * userName : 爸爸
             * giftList : [{"type":0,"value":90,"unit":"金币","url":""},{"type":1,"value":90,"unit":"分","url":""},{"type":2,"value":0,"unit":"","url":"http:www.ddfdfd.jpg"}]
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
                 * value : 90
                 * unit : 金币
                 * url :
                 */

                private int type;
                private int value;
                private String unit;
                private String url;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
