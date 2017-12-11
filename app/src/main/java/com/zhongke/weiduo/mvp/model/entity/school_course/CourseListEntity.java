package com.zhongke.weiduo.mvp.model.entity.school_course;

/**
 * Created by hyx on 2017/11/7.
 * 获得课程列表
 */

public class CourseListEntity {
    private String token;

    private String pageIndex;

    private String pageSize;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

}
