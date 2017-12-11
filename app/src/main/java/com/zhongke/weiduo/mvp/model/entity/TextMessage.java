package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by Karma on 2017/7/1.
 * 类描述：文本消息
 */

public class TextMessage extends MessageContent {
    private String chatContent;
    private String chatType;

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }
}
