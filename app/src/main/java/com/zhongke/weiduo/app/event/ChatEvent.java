package com.zhongke.weiduo.app.event;

/**
 * Created by Karma on 2017/7/5.
 * 类描述：聊天event
 */

public class ChatEvent {
    private String msgId;
    private long msgTime;

    public ChatEvent(String msgId, long msgTime) {
        this.msgId = msgId;
        this.msgTime = msgTime;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }

    @Override
    public String toString() {
        return "ChatEvent{" +
                "msgId='" + msgId + '\'' +
                ", msgTime=" + msgTime +
                '}';
    }
}
