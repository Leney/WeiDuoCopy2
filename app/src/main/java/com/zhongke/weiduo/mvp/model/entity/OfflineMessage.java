package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by llj on 2017/12/5.
 */

public class OfflineMessage {

    private List<MsgListBean> msgList;

    public List<MsgListBean> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<MsgListBean> msgList) {
        this.msgList = msgList;
    }

    public static class MsgListBean {
        /**
         * sendUserId : 90
         * deviceType : mb
         * recvTime : null
         * toUserList : 126
         * groupId : 0
         * recvState : 101
         * msgId : f4deecb9-0cc7-4952-ad6c-1a22a99cf243
         * id : 773
         * time : 2017-12-05 11:48:58
         * chatContent : {"chatType":1,"content":"[憨笑]","duraction":0,"groupId":"","index":0,"memberInfo":{"icon":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/PictureFile/f109a458c5da488da7ab8d32d6476542/1512373344770.jpg","id":138,"name":"zkl650"},"receiveTime":0,"sendOrReceive":0,"sendStatus":5,"sendTime":0,"toUserList":["126"]}
         */

        private int sendUserId;
        private String deviceType;
        private Object recvTime;
        private String toUserList;
        private int groupId;
        private int recvState;
        private String msgId;
        private int id;
        private String time;
        private String chatContent;

        public int getSendUserId() {
            return sendUserId;
        }

        public void setSendUserId(int sendUserId) {
            this.sendUserId = sendUserId;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public Object getRecvTime() {
            return recvTime;
        }

        public void setRecvTime(Object recvTime) {
            this.recvTime = recvTime;
        }

        public String getToUserList() {
            return toUserList;
        }

        public void setToUserList(String toUserList) {
            this.toUserList = toUserList;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getRecvState() {
            return recvState;
        }

        public void setRecvState(int recvState) {
            this.recvState = recvState;
        }

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getChatContent() {
            return chatContent;
        }

        public void setChatContent(String chatContent) {
            this.chatContent = chatContent;
        }
    }
}
