package com.zhongke.weiduo.mvp.model.entity.school_course;

import java.util.List;

/**
 * Created by hyx on 2017/11/7.
 * 课程列表
 */

public class CourseListResult {

    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/s2hftCy7T33fWap1506065474392.png","courseName":"测试课程","id":79},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/iFPfWHxQ3cXfy8K1506395070502.jpg","courseName":"测试课程007","id":80},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/2DTsAryXB8SdNTf2db44HhZzw1508469255945.gif","courseName":"算数1","id":81},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/EntyDiw3dCCbQk3DfQYtxJcye1509083192408.jpg","courseName":"美术课","id":82},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/SpN2XtYbbMz6TRsfNB6RKjnd31509083488936.gif","courseName":"语文课","id":83},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/rECnSewJCrXkH8PYnrNtFCtEy1509084718257.jpg","courseName":"数学","id":84},{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/n4ZyQ2hKSewerN4hQfzDppNmQ1509084770786.jpg","courseName":"英语","id":85}]
     * recordTotal : 7
     */

    private int pageTotal;
    private int pageIndex;
    private int recordTotal;
    private List<RecordsBean> records;

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/s2hftCy7T33fWap1506065474392.png
         * courseName : 测试课程
         * id : 79
         * * courseKind : 1
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
