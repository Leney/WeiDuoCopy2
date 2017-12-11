package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/14.
 * 新的专家课程实体
 */

public class NewExpertCourseBean {

    private List<ExpertCourseBean> expertCourse;

    public List<ExpertCourseBean> getExpertCourse() {
        return expertCourse;
    }

    public void setExpertCourse(List<ExpertCourseBean> expertCourse) {
        this.expertCourse = expertCourse;
    }

    public static class ExpertCourseBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jNG6x2nEjPTrHtD6ccTi7cCNF1510281104322.jpg
         * courseName : 新概念英语
         * id : 79
         * courseKind : 1
         */

        private String coverUrl;
        private String courseName;
        private int id;
        private int courseKind;

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCourseKind() {
            return courseKind;
        }

        public void setCourseKind(int courseKind) {
            this.courseKind = courseKind;
        }
    }
}
