package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by hyx on 2017/11/30.
 * 执行人 bean
 */

public class ExecutePeopleBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * nickName :
         * fTitleName : 2
         * fullName : null
         * userName : 15625221424
         * userId : 126
         */

        private String nickName;
        private int fTitleName;
        private String fullName;
        private String userName;
        private int userId;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getFTitleName() {
            return fTitleName;
        }

        public void setFTitleName(int fTitleName) {
            this.fTitleName = fTitleName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
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
            return "ListBean{" +
                    "nickName='" + nickName + '\'' +
                    ", fTitleName=" + fTitleName +
                    ", fullName=" + fullName +
                    ", userName='" + userName + '\'' +
                    ", userId=" + userId +
                    '}';
        }
    }
}
