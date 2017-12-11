package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by llj on 2017/12/8.
 */

public class JoinMember {

    private List<LookUserListBean> lookUserList;
    private List<ActUserListBean> actUserList;

    public List<LookUserListBean> getLookUserList() {
        return lookUserList;
    }

    public void setLookUserList(List<LookUserListBean> lookUserList) {
        this.lookUserList = lookUserList;
    }

    public List<ActUserListBean> getActUserList() {
        return actUserList;
    }

    public void setActUserList(List<ActUserListBean> actUserList) {
        this.actUserList = actUserList;
    }

    public static class LookUserListBean {
        /**
         * nickName : 小明
         * headImageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/6k3m2XKYBDapFSfxZHi47Awp61511494315093.png
         * actionId : 109
         * fullName : null
         * actorState : 3
         * userName : B1231U
         * userId : 162
         */

        private String nickName;
        private String headImageUrl;
        private int actionId;
        private Object fullName;
        private int actorState;
        private String userName;
        private int userId;

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

        public int getActionId() {
            return actionId;
        }

        public void setActionId(int actionId) {
            this.actionId = actionId;
        }

        public Object getFullName() {
            return fullName;
        }

        public void setFullName(Object fullName) {
            this.fullName = fullName;
        }

        public int getActorState() {
            return actorState;
        }

        public void setActorState(int actorState) {
            this.actorState = actorState;
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
    }

    public static class ActUserListBean {
        /**
         * nickName : 嫣儿
         * headImageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/PictureFile/5384789c5da642c5a4346593a9d9add7/1512532000529.jpg
         * actionId : 109
         * fullName : 柳嫣儿
         * actorState : 5
         * userName : testAdmin
         * userId : 90
         */

        private String nickName;
        private String headImageUrl;
        private int actionId;
        private String fullName;
        private int actorState;
        private String userName;
        private int userId;

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

        public int getActionId() {
            return actionId;
        }

        public void setActionId(int actionId) {
            this.actionId = actionId;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getActorState() {
            return actorState;
        }

        public void setActorState(int actorState) {
            this.actorState = actorState;
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
    }
}
