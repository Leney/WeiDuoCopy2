package com.zhongke.weiduo.mvp.model.entity.school_course;

/**
 * Created by hyx on 2017/11/7.
 * 收藏活动
 */

public class CollectionActionEntity {

    private String token;
    //类型（1.专家2.课程3.机构4.活动）
    private String collectType;

    private int collectObjectId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    public int getCollectObjectId() {
        return collectObjectId;
    }

    public void setCollectObjectId(int collectObjectId) {
        this.collectObjectId = collectObjectId;
    }

}
