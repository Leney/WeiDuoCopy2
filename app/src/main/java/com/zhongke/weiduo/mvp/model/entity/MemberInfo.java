package com.zhongke.weiduo.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户信息实体类
 * Created by llj on 2017/6/23.
 */

public class MemberInfo implements Parcelable {
    private int id;
    private String name;
    private String icon;

    public int getId() {
        return id;
    }

    public MemberInfo setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MemberInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public MemberInfo setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.icon);
    }

    public MemberInfo() {
    }

    protected MemberInfo(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.icon = in.readString();
    }

    public static final Creator<MemberInfo> CREATOR = new Creator<MemberInfo>() {
        @Override
        public MemberInfo createFromParcel(Parcel source) {
            return new MemberInfo(source);
        }

        @Override
        public MemberInfo[] newArray(int size) {
            return new MemberInfo[size];
        }
    };

    @Override
    public String toString() {
        return "MemberInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
