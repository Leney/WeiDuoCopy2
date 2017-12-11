package com.zhongke.weiduo.app.event;

import com.zhongke.weiduo.mvp.model.entity.Session;

/**
 * Created by Karma on 2017/7/24.
 * 类描述：
 */

public class ChatResponEvent {
    private Session mSession;

    public ChatResponEvent(Session session) {
        mSession = session;
    }

    public Session getSession() {
        return mSession;
    }

    public void setSession(Session session) {
        mSession = session;
    }
}
