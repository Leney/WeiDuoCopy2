package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/17.
 * 获取家庭获取群组成员实体
 */

public class NewGroupMemberBean {

    private List<MemberListBean> memberList;

    public List<MemberListBean> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberListBean> memberList) {
        this.memberList = memberList;
    }

    public static class MemberListBean {
        /**
         * gTitleName : 1
         * noSpeech : 1
         * createTime : 2017-10-23 11:35:18
         * nickName : 小可
         * headImageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/PictureFile/8cc405d996aa4a8b8196b0899ddbb14e/1510733658085.jpg
         * groupId : 68
         * fTitleName : 2
         * warning : null
         * id : 63
         * userName : testAdmin
         * userId : 90
         */

        private int gTitleName;
        private int noSpeech;
        private String createTime;
        private String nickName;
        private String headImageUrl;
        private int groupId;
        private int fTitleName;
        private Object warning;
        private int id;
        private String userName;
        private int userId;

        public int getGTitleName() {
            return gTitleName;
        }

        public void setGTitleName(int gTitleName) {
            this.gTitleName = gTitleName;
        }

        public int getNoSpeech() {
            return noSpeech;
        }

        public void setNoSpeech(int noSpeech) {
            this.noSpeech = noSpeech;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getFTitleName() {
            return fTitleName;
        }

        public void setFTitleName(int fTitleName) {
            this.fTitleName = fTitleName;
        }

        public Object getWarning() {
            return warning;
        }

        public void setWarning(Object warning) {
            this.warning = warning;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
