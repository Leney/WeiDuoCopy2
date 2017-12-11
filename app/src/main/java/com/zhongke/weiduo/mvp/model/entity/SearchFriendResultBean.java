package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 搜索好友列表Bean对象
 * Created by llj on 2017/11/16.
 */

public class SearchFriendResultBean {

    private List<UserListBean> userList;

    public List<UserListBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListBean> userList) {
        this.userList = userList;
    }

    public static class UserListBean {
        /**
         * headImageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/cDD4FwaRMnGi5PMnabcwBrbHf1510645575103.png
         * id : 1
         * userName : admin
         */

        private String headImageUrl;
        private int id;
        private String userName;
        private String nickName;
        private String fullName;

        public String getHeadImageUrl() {
            return headImageUrl;
        }

        public void setHeadImageUrl(String headImageUrl) {
            this.headImageUrl = headImageUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }
}
