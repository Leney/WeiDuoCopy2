package com.zhongke.weiduo.mvp.model.entity;

import java.io.Serializable;

/**
 * Created by ${tanlei} on 2017/9/5.
 * 专家课程实体类
 */

public class ExpertCourseBean implements Serializable{
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 课程图片介绍
     */
    private String coursePhoto;
    /**
     * 课程价格
     */
    private String coursePrice;
    /**
     * 课程的类型：1为直播课，0为视频课
     */
    private int courseType;

    public ExpertCourseBean() {
    }

    public ExpertCourseBean(String courseName, String coursePhoto, String coursePrice, int courseType) {
        this.courseName = courseName;
        this.coursePhoto = coursePhoto;
        this.coursePrice = coursePrice;
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoursePhoto() {
        return coursePhoto;
    }

    public void setCoursePhoto(String coursePhoto) {
        this.coursePhoto = coursePhoto;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    @Override
    public String toString() {
        return "ExpertCourseBean{" +
                "courseName='" + courseName + '\'' +
                ", coursePhoto='" + coursePhoto + '\'' +
                ", coursePrice='" + coursePrice + '\'' +
                ", courseType=" + courseType +
                '}';
    }
}
