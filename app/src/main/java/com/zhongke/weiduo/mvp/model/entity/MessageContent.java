package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by Karma on 2017/7/1.
 * 类描述：消息总类
 */

public class MessageContent extends Entity {
    private MemberInfo userInfo;

    public MemberInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(MemberInfo userInfo) {
        this.userInfo = userInfo;
    }
}
