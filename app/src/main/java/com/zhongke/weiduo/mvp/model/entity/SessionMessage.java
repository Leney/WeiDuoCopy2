package com.zhongke.weiduo.mvp.model.entity;

import android.net.Uri;

/**
 * Created by Karma on 2017/6/27.
 * 类描述：
 */

public class SessionMessage extends MessageContent {
    private static SessionMessage sSessionMessage;

    private String chatContent;
    private int chatType;
    private int Duration;
    private Uri mUri;
    private int messageDirection;
    private String msgId;
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public static SessionMessage getInstance() {
        return new SessionMessage();
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }


    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public int getMessageDirection() {
        return messageDirection;
    }

    public void setMessageDirection(int messageDirection) {
        this.messageDirection = messageDirection;
    }


}
