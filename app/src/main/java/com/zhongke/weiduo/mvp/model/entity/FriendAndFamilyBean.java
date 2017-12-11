package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/10/10.
 */

public class FriendAndFamilyBean {
    private String typeName;
    private List<ContactsListBean> groupBeanList;

    public FriendAndFamilyBean() {
    }

    public FriendAndFamilyBean(String typeName, List<ContactsListBean> groupBeanList) {
        this.typeName = typeName;
        this.groupBeanList = groupBeanList;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<ContactsListBean> getGroupBeanList() {
        return groupBeanList;
    }

    public void setGroupBeanList(List<ContactsListBean> groupBeanList) {
        this.groupBeanList = groupBeanList;
    }
}
