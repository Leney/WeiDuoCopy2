package com.zhongke.weiduo.mvp.model.entity.school_course;

/**
 * Created by hyx on 2017/11/7.
 * 收藏课程
 */

public class CollectionCourseEntity {

    private String token;

    //收藏类型（1.专家2.课程3.机构4.活动）
    private String collectType;

    private String collectObjectId;

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

    public String getCollectObjectId() {
        return collectObjectId;
    }

    public void setCollectObjectId(String collectObjectId) {
        this.collectObjectId = collectObjectId;
    }
}
