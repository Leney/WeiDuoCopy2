package com.zhongke.weiduo.mvp.model.entity.school_course;

/**
 * Created by hyx on 2017/11/7.
 *  加入课程
 */

public class JoinCourseEntity {

    private String token;

    private String courseId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
