package com.zhongke.weiduo.mvp.model.entity;

import com.zhongke.weiduo.app.common.AppConst;

import java.io.Serializable;

/**
 * 聊天列表bean
 * Created by llj on 2017/8/16.
 */

public class ChatListBean implements Serializable, Cloneable {

    /**
     * 单聊类型
     */
    public final static int CHAT_TYPE_SINGLE = AppConst.chat_one;
    /**
     * 多聊类型
     */
    public final static int CHAT_TYPE_MORE = AppConst.chat_group;
    /**
     * 系统消息类型
     */
    public final static int CHAT_TYPE_SYSTEM = AppConst.chat_system;
    /**
     * 添加好友消息类型
     */
    public final static int CHAT_TYPE_ADD_FRIEND = AppConst.chat_add_friend;


    /**
     * 添加好友时,已发送申请，但对方未处理
     */
    public final static int NEW_ADD_FRIEND_STATE_ALREADY_SEND = 1;
    /**
     * 添加好友时,已通过的状态
     */
    public final static int NEW_ADD_FRIEND_STATE_PASSED = 2;
    /**
     * 添加好友时,已拒绝的状态
     */
    public final static int NEW_ADD_FRIEND_STATE_NO_PASSED = 3;
    /**
     * 添加好友时,有好友申请，自己未处理
     */
    public final static int NEW_ADD_FRIEND_STATE_UN_DO = 4;

    // 后续再分具体是家庭类型还是群组类型
//    /** 系统消息类型*/
//    public static int CHAT_TYPE_SYSTEM = 3;

    /**
     * 类型 0=单聊，1=多聊，3=系统消息
     */
    public int type;
    /**
     * 消息id(这里有可能是个人用户id、群组id、家庭id、系统消息id，系统消息id目前统一值为：-1)
     */
    public String id;
    /**
     * 聊天对象的名称
     */
    public String name;
    /**
     * 聊天对象的头像路径
     */
    public String icon;
    /**
     * 最新消息的消息类型，0=文本，1=语音，2=表情，3=图片，4=视频
     */
    public int newestMsgType;
    /**
     * 最新消息的文本(表情、图片、视频等非文本消息用相应的文本代替,比如图片：[图片])
     */
    public String newestMsg;
    /**
     * 最新接收消息的时间
     */
    public long newestMessageTime;
    /**
     * 未读消息的数量数
     */
    public int unreadMessageNum = 0;
    /**
     * 最后更新数据的时间(第一次加入最近聊天列表的时间或者最后一次更新本身数据的时间)
     */
    public long newestUpdateTime;
    /**
     * 标识最新一条消息是接收的消息还是自己发送的消息
     * true = 接收的消息类型
     * false = 发送的消息类型
     */
    public boolean newestMessageIsReceiveType;

    /**
     * 如果是添加好友消息类型，处理状态。
     * 1=NEW_ADD_FRIEND_STATE_ALREADY_SEND=未处理，2=NEW_ADD_FRIEND_STATE_PASSED=通过，3=NEW_ADD_FRIEND_STATE_NO_PASSED=拒绝
     */
    public int addState;


//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

//    public ChatListBean cloneMyself() {
//        try {
//            return (ChatListBean) clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatListBean that = (ChatListBean) o;

        if (type != that.type) return false;
        if (newestMsgType != that.newestMsgType) return false;
        if (newestMessageTime != that.newestMessageTime) return false;
        if (unreadMessageNum != that.unreadMessageNum) return false;
        if (newestUpdateTime != that.newestUpdateTime) return false;
        if (newestMessageIsReceiveType != that.newestMessageIsReceiveType) return false;
        if (addState != that.addState) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        return newestMsg != null ? newestMsg.equals(that.newestMsg) : that.newestMsg == null;

    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + newestMsgType;
        result = 31 * result + (newestMsg != null ? newestMsg.hashCode() : 0);
        result = 31 * result + (int) (newestMessageTime ^ (newestMessageTime >>> 32));
        result = 31 * result + unreadMessageNum;
        result = 31 * result + (int) (newestUpdateTime ^ (newestUpdateTime >>> 32));
        result = 31 * result + (newestMessageIsReceiveType ? 1 : 0);
        result = 31 * result + addState;
        return result;
    }

    @Override
    public String toString() {
        return "ChatListBean{" +
                "type=" + type +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", newestMsgType=" + newestMsgType +
                ", newestMsg='" + newestMsg + '\'' +
                ", newestMessageTime=" + newestMessageTime +
                ", unreadMessageNum=" + unreadMessageNum +
                ", newestUpdateTime=" + newestUpdateTime +
                ", newestMessageIsReceiveType=" + newestMessageIsReceiveType +
                ", addState=" + addState +
                '}';
    }
}
