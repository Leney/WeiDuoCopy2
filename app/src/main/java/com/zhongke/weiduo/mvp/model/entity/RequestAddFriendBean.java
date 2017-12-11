package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 请求添加好友bean
 * Created by llj on 2017/11/21.
 */

public class RequestAddFriendBean {

    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"friendId":90,"createTime":"2017-11-15 20:03:29","friendState":2,"nickName":"小可爱","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","friendNickName":"","applayMsg":"莫大","fullName":null,"id":40,"userNickName":"","userName":"小可爱","userId":91},{"friendId":90,"createTime":"2017-11-21 14:08:06","friendState":1,"nickName":"陈习训","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","friendNickName":"","applayMsg":"1","fullName":null,"id":54,"userNickName":"","userName":"cxx","userId":88},{"friendId":90,"createTime":"2017-11-21 14:36:48","friendState":1,"nickName":"苏苏","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","friendNickName":"","applayMsg":"ffg","fullName":null,"id":56,"userNickName":"","userName":"admin","userId":1},{"friendId":90,"createTime":"2017-11-21 14:39:20","friendState":2,"nickName":"陈习训","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","friendNickName":"陈习训","applayMsg":null,"fullName":null,"id":58,"userNickName":"小慧","userName":"cxxun","userId":92},{"friendId":90,"createTime":"2017-11-21 14:40:25","friendState":2,"nickName":"陈习训","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png","friendNickName":"训哥","applayMsg":null,"fullName":null,"id":60,"userNickName":"小慧","userName":"cxixun","userId":93}]
     * recordTotal : 5
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
         * friendId : 90
         * createTime : 2017-11-15 20:03:29
         * friendState : 2
         * nickName : 小可爱
         * headImageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png
         * friendNickName :
         * applayMsg : 莫大
         * fullName : null
         * id : 40
         * userNickName :
         * userName : 小可爱
         * userId : 91
         */

        private int friendId;
        private String createTime;
        private int friendState;
        private String nickName;
        private String headImageUrl;
        private String friendNickName;
        private String applayMsg;
        private Object fullName;
        private int id;
        private String userNickName;
        private String userName;
        private int userId;

        public int getFriendId() {
            return friendId;
        }

        public void setFriendId(int friendId) {
            this.friendId = friendId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getFriendState() {
            return friendState;
        }

        public void setFriendState(int friendState) {
            this.friendState = friendState;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadImageUrl() {
            return headImageUrl;
        }

        public void setHeadImageUrl(String headImageUrl) {
            this.headImageUrl = headImageUrl;
        }

        public String getFriendNickName() {
            return friendNickName;
        }

        public void setFriendNickName(String friendNickName) {
            this.friendNickName = friendNickName;
        }

        public String getApplayMsg() {
            return applayMsg;
        }

        public void setApplayMsg(String applayMsg) {
            this.applayMsg = applayMsg;
        }

        public Object getFullName() {
            return fullName;
        }

        public void setFullName(Object fullName) {
            this.fullName = fullName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "friendId=" + friendId +
                    ", createTime='" + createTime + '\'' +
                    ", friendState=" + friendState +
                    ", nickName='" + nickName + '\'' +
                    ", headImageUrl='" + headImageUrl + '\'' +
                    ", friendNickName='" + friendNickName + '\'' +
                    ", applayMsg='" + applayMsg + '\'' +
                    ", fullName=" + fullName +
                    ", id=" + id +
                    ", userNickName='" + userNickName + '\'' +
                    ", userName='" + userName + '\'' +
                    ", userId=" + userId +
                    '}';
        }
    }
}
