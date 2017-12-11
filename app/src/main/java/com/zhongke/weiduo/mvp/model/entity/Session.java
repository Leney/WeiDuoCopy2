package com.zhongke.weiduo.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Karma on 2017/7/11.
 * 类描述：聊天
 */

public class Session implements Parcelable {
    /**
     * 文本类型
     */
    public static final int TEXT = 1;
    /**
     * 语音类型
     */
    public static final int VOICE = 2;
    /**
     * 图片类型
     */
    public static final int IMAGE = 3;
    /**
     * 视频类型
     */
    public static final int VIDEO = 4;
    /**
     * 表情类型
     */
    public static final int STICKER = 5;

    private static Session mInstance;
    private MemberInfo mMemberInfo = new MemberInfo();
    private String msgId;
    private int chatType;
    private long sendTime;
    private long receiveTime;
    private int sendOrReceive;
    private int duraction;
    private String content;
    private int sendStatus;
    private String localUrl;
    private String extra;
    private int index;
    private List<String> toUserList;
    //必需传递
    private String groupId = "";

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getToUserList() {
        return toUserList;
    }

    public void setToUserList(List<String> toUserList) {
        this.toUserList = toUserList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public static void setmInstance(Session mInstance) {
        Session.mInstance = mInstance;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public MemberInfo getMemberInfo() {
        return mMemberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        mMemberInfo = memberInfo;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public int getSendOrReceive() {
        return sendOrReceive;
    }

    public void setSendOrReceive(int sendOrReceive) {
        this.sendOrReceive = sendOrReceive;
    }

    public int getDuraction() {
        return duraction;
    }

    public void setDuraction(int duraction) {
        this.duraction = duraction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Creator<Session> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mMemberInfo, flags);
        dest.writeString(this.msgId);
        dest.writeInt(this.chatType);
        dest.writeLong(this.sendTime);
        dest.writeLong(this.receiveTime);
        dest.writeInt(this.sendOrReceive);
        dest.writeString(this.content);
        dest.writeInt(this.duraction);
    }

    public Session() {
    }

    public synchronized static Session getmInstance() {
        if (mInstance == null) {
            mInstance = new Session();
        }
        return mInstance;
    }

    protected Session(Parcel in) {
        this.mMemberInfo = in.readParcelable(MemberInfo.class.getClassLoader());
        this.msgId = in.readString();
        this.chatType = in.readInt();
        this.sendTime = in.readLong();
        this.receiveTime = in.readLong();
        this.sendOrReceive = in.readInt();
        this.content = in.readString();
        this.duraction = in.readInt();
    }

    public static final Creator<Session> CREATOR = new Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel source) {
            return new Session(source);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    @Override
    public String toString() {
        return "Session{" +
                "mMemberInfo=" + mMemberInfo +
                ", msgId='" + msgId + '\'' +
                ", chatType=" + chatType +
                ", sendTime=" + sendTime +
                ", receiveTime=" + receiveTime +
                ", sendOrReceive=" + sendOrReceive +
                ", duraction=" + duraction +
                ", content='" + content + '\'' +
                ", sendStatus=" + sendStatus +
                ", localUrl=" + localUrl +
                ", extra='" + extra + '\'' +
                '}';
    }
}
