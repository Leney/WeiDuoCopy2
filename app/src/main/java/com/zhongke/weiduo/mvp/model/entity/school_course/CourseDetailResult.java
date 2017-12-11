package com.zhongke.weiduo.mvp.model.entity.school_course;

import java.util.List;

/**
 * Created by hyx on 2017/11/7.
 * 获得课程详情
 */

public class CourseDetailResult {

    /**
     * catalog : [{"code":"35","level":0,"freeState":1,"fullName":"测试课程","pId":0,"type":0,"resId":null,"url":null,"createTime":"2017-09-22 15:31:28","name":"测试课程","id":35,"courseId":79,"resJson":"{}"},{"code":"35-36","level":1,"freeState":1,"fullName":"测试课程-第一节","pId":0,"type":0,"resId":null,"url":null,"createTime":"2017-11-17 14:21:57","name":"第一节","id":36,"courseId":79,"resJson":null},{"code":"35-36-39","level":2,"freeState":1,"fullName":"测试课程-第一章-视频","pId":36,"type":2,"resId":355,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=39","createTime":"2017-09-25 09:48:21","name":"视频","id":39,"courseId":79,"resJson":"{\"id\":\"355\",\"name\":\"视频\",\"tag\":\"视频\",\"resType\":\"2\",\"coverUrl\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/ktCatwfXmC.jpg\",\"url\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/MMG8FhStHZ.mp4\",\"androidUrl\":\"\",\"isoUrl\":\"\",\"createTime\":\"2017-03-24 12:35:29\",\"info\":\"视频\",\"extendInfo\":\"视频\",\"updateTime\":\"2017-03-24 12:35:29\",\"orgId\":\"1\",\"checkState\":\"2\",\"checkUserId\":\"1\",\"checkMsg\":\"审核通过\",\"checkTime\":\"2017-09-22 14:13:19\",\"uploadUserId\":\"1\",\"orgName\":\"众可\",\"BindTime\":\"2017-09-25 09:48:21\"}"},{"code":"35-68","level":1,"freeState":1,"fullName":"测试课程-01 早安听力","pId":35,"type":0,"resId":null,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=68","createTime":"2017-11-10 11:25:34","name":"01 早安听力","id":68,"courseId":79,"resJson":"{}"},{"code":"35-68-69","level":2,"freeState":1,"fullName":"测试课程-01 早安听力-02 写作-如何把你的想法变成英语作文","pId":68,"type":0,"resId":null,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=69","createTime":"2017-11-10 11:27:17","name":"02 写作-如何把你的想法变成英语作文","id":69,"courseId":79,"resJson":"{}"},{"code":"35-68-70","level":2,"freeState":1,"fullName":"测试课程-01 早安听力-03 阅读-英语阅读要这样","pId":68,"type":0,"resId":null,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=70","createTime":"2017-11-10 11:27:42","name":"03 阅读-英语阅读要这样","id":70,"courseId":79,"resJson":"{}"},{"code":"35-68-71","level":2,"freeState":1,"fullName":"测试课程-01 早安听力-04 口语-场景素材合并+专治疑难杂题","pId":68,"type":0,"resId":null,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=71","createTime":"2017-11-10 11:28:31","name":"04 口语-场景素材合并+专治疑难杂题","id":71,"courseId":79,"resJson":"{}"},{"code":"35-68-71-72","level":3,"freeState":1,"fullName":"测试课程-01 早安听力-04 口语-场景素材合并+专治疑难杂题-05 早安听力-早起听力训练","pId":71,"type":0,"resId":null,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=72","createTime":"2017-11-10 11:29:29","name":"05 早安听力-早起听力训练","id":72,"courseId":79,"resJson":"{}"},{"code":"35-68-71-73","level":3,"freeState":1,"fullName":"测试课程-01 早安听力-04 口语-场景素材合并+专治疑难杂题-06 阅读-判断题满分+高頩预测","pId":71,"type":0,"resId":null,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=73","createTime":"2017-11-10 11:30:23","name":"06 阅读-判断题满分+高頩预测","id":73,"courseId":79,"resJson":"{}"},{"code":"35-75","level":1,"freeState":1,"fullName":"测试课程-07 口语-实事中的英语","pId":35,"type":0,"resId":null,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=75","createTime":"2017-11-10 11:31:24","name":"07 口语-实事中的英语","id":75,"courseId":79,"resJson":"{}"},{"code":"35-76","level":1,"freeState":1,"fullName":"测试课程-08 早安听力-早起听力训练","pId":35,"type":0,"resId":null,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=76","createTime":"2017-11-10 11:31:56","name":"08 早安听力-早起听力训练","id":76,"courseId":79,"resJson":"{}"},{"code":"35-77","level":1,"freeState":1,"fullName":"测试课程-09 阅读-养成阅读好习惯","pId":35,"type":0,"resId":null,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=77","createTime":"2017-11-10 11:32:18","name":"09 阅读-养成阅读好习惯","id":77,"courseId":79,"resJson":"{}"},{"code":"35-78","level":1,"freeState":1,"fullName":"测试课程-10 留学讲座-院系专业分析","pId":35,"type":0,"resId":null,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=78","createTime":"2017-11-10 11:32:42","name":"10 留学讲座-院系专业分析","id":78,"courseId":79,"resJson":"{}"},{"code":"35-68-69-129","level":3,"freeState":1,"fullName":"测试课程-01 早安听力-02 写作-如何把你的想法变成英语作文-英语教学视频","pId":69,"type":2,"resId":356,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=129","createTime":"2017-11-10 13:51:00","name":"英语教学视频","id":129,"courseId":79,"resJson":"{\"id\":\"356\",\"name\":\"英语教学视频\",\"tag\":\"视频\",\"resType\":\"2\",\"coverUrl\":\"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/x6cy6AHX7mdFXyeZ3DM5KhdK21509097114773.jpg\",\"url\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/MMG8FhStHZ.mp4\",\"androidUrl\":\"\",\"isoUrl\":\"\",\"createTime\":\"2017-03-24 12:35:41\",\"info\":\"视频\",\"extendInfo\":\"视频\",\"updateTime\":\"2017-11-10 11:46:59\",\"orgId\":\"1\",\"checkState\":\"2\",\"checkUserId\":\"1\",\"checkMsg\":\"审核通过\",\"checkTime\":\"2017-09-22 14:13:19\",\"uploadUserId\":\"1\",\"orgName\":\"众可\",\"BindTime\":\"2017-11-10 13:51:01\"}"},{"code":"35-68-70-130","level":3,"freeState":1,"fullName":"测试课程-01 早安听力-03 阅读-英语阅读要这样-英语教学视频","pId":70,"type":2,"resId":356,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=130","createTime":"2017-11-10 13:51:06","name":"英语教学视频","id":130,"courseId":79,"resJson":"{\"id\":\"356\",\"name\":\"英语教学视频\",\"tag\":\"视频\",\"resType\":\"2\",\"coverUrl\":\"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/x6cy6AHX7mdFXyeZ3DM5KhdK21509097114773.jpg\",\"url\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/MMG8FhStHZ.mp4\",\"androidUrl\":\"\",\"isoUrl\":\"\",\"createTime\":\"2017-03-24 12:35:41\",\"info\":\"视频\",\"extendInfo\":\"视频\",\"updateTime\":\"2017-11-10 11:46:59\",\"orgId\":\"1\",\"checkState\":\"2\",\"checkUserId\":\"1\",\"checkMsg\":\"审核通过\",\"checkTime\":\"2017-09-22 14:13:19\",\"uploadUserId\":\"1\",\"orgName\":\"众可\",\"BindTime\":\"2017-11-10 13:51:07\"}"},{"code":"35-75-131","level":2,"freeState":1,"fullName":"测试课程-07 口语-实事中的英语-英语教学视频","pId":75,"type":2,"resId":356,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=131","createTime":"2017-11-10 13:51:14","name":"英语教学视频","id":131,"courseId":79,"resJson":"{\"id\":\"356\",\"name\":\"英语教学视频\",\"tag\":\"视频\",\"resType\":\"2\",\"coverUrl\":\"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/x6cy6AHX7mdFXyeZ3DM5KhdK21509097114773.jpg\",\"url\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/MMG8FhStHZ.mp4\",\"androidUrl\":\"\",\"isoUrl\":\"\",\"createTime\":\"2017-03-24 12:35:41\",\"info\":\"视频\",\"extendInfo\":\"视频\",\"updateTime\":\"2017-11-10 11:46:59\",\"orgId\":\"1\",\"checkState\":\"2\",\"checkUserId\":\"1\",\"checkMsg\":\"审核通过\",\"checkTime\":\"2017-09-22 14:13:19\",\"uploadUserId\":\"1\",\"orgName\":\"众可\",\"BindTime\":\"2017-11-10 13:51:15\"}"},{"code":"35-76-132","level":2,"freeState":1,"fullName":"测试课程-08 早安听力-早起听力训练-英语教学视频","pId":76,"type":2,"resId":356,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=132","createTime":"2017-11-10 13:51:20","name":"英语教学视频","id":132,"courseId":79,"resJson":"{\"id\":\"356\",\"name\":\"英语教学视频\",\"tag\":\"视频\",\"resType\":\"2\",\"coverUrl\":\"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/x6cy6AHX7mdFXyeZ3DM5KhdK21509097114773.jpg\",\"url\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/MMG8FhStHZ.mp4\",\"androidUrl\":\"\",\"isoUrl\":\"\",\"createTime\":\"2017-03-24 12:35:41\",\"info\":\"视频\",\"extendInfo\":\"视频\",\"updateTime\":\"2017-11-10 11:46:59\",\"orgId\":\"1\",\"checkState\":\"2\",\"checkUserId\":\"1\",\"checkMsg\":\"审核通过\",\"checkTime\":\"2017-09-22 14:13:19\",\"uploadUserId\":\"1\",\"orgName\":\"众可\",\"BindTime\":\"2017-11-10 13:51:21\"}"},{"code":"35-77-133","level":2,"freeState":1,"fullName":"测试课程-09 阅读-养成阅读好习惯-英语教学视频","pId":77,"type":2,"resId":356,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=133","createTime":"2017-11-10 13:51:26","name":"英语教学视频","id":133,"courseId":79,"resJson":"{\"id\":\"356\",\"name\":\"英语教学视频\",\"tag\":\"视频\",\"resType\":\"2\",\"coverUrl\":\"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/x6cy6AHX7mdFXyeZ3DM5KhdK21509097114773.jpg\",\"url\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/MMG8FhStHZ.mp4\",\"androidUrl\":\"\",\"isoUrl\":\"\",\"createTime\":\"2017-03-24 12:35:41\",\"info\":\"视频\",\"extendInfo\":\"视频\",\"updateTime\":\"2017-11-10 11:46:59\",\"orgId\":\"1\",\"checkState\":\"2\",\"checkUserId\":\"1\",\"checkMsg\":\"审核通过\",\"checkTime\":\"2017-09-22 14:13:19\",\"uploadUserId\":\"1\",\"orgName\":\"众可\",\"BindTime\":\"2017-11-10 13:51:27\"}"},{"code":"35-78-134","level":2,"freeState":1,"fullName":"测试课程-10 留学讲座-院系专业分析-英语教学视频","pId":78,"type":2,"resId":356,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=134","createTime":"2017-11-10 13:51:32","name":"英语教学视频","id":134,"courseId":79,"resJson":"{\"id\":\"356\",\"name\":\"英语教学视频\",\"tag\":\"视频\",\"resType\":\"2\",\"coverUrl\":\"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/x6cy6AHX7mdFXyeZ3DM5KhdK21509097114773.jpg\",\"url\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/MMG8FhStHZ.mp4\",\"androidUrl\":\"\",\"isoUrl\":\"\",\"createTime\":\"2017-03-24 12:35:41\",\"info\":\"视频\",\"extendInfo\":\"视频\",\"updateTime\":\"2017-11-10 11:46:59\",\"orgId\":\"1\",\"checkState\":\"2\",\"checkUserId\":\"1\",\"checkMsg\":\"审核通过\",\"checkTime\":\"2017-09-22 14:13:19\",\"uploadUserId\":\"1\",\"orgName\":\"众可\",\"BindTime\":\"2017-11-10 13:51:33\"}"},{"code":"35-68-135","level":2,"freeState":1,"fullName":"测试课程-01 早安听力-英语教学视频","pId":68,"type":2,"resId":356,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=135","createTime":"2017-11-10 13:52:30","name":"英语教学视频","id":135,"courseId":79,"resJson":"{\"id\":\"356\",\"name\":\"英语教学视频\",\"tag\":\"视频\",\"resType\":\"2\",\"coverUrl\":\"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/x6cy6AHX7mdFXyeZ3DM5KhdK21509097114773.jpg\",\"url\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/MMG8FhStHZ.mp4\",\"androidUrl\":\"\",\"isoUrl\":\"\",\"createTime\":\"2017-03-24 12:35:41\",\"info\":\"视频\",\"extendInfo\":\"视频\",\"updateTime\":\"2017-11-10 11:46:59\",\"orgId\":\"1\",\"checkState\":\"2\",\"checkUserId\":\"1\",\"checkMsg\":\"审核通过\",\"checkTime\":\"2017-09-22 14:13:19\",\"uploadUserId\":\"1\",\"orgName\":\"众可\",\"BindTime\":\"2017-11-10 13:52:31\"}"},{"code":"35-68-71-72-136","level":4,"freeState":1,"fullName":"测试课程-01 早安听力-04 口语-场景素材合并+专治疑难杂题-05 早安听力-早起听力训练-英语教学视频","pId":72,"type":2,"resId":356,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=136","createTime":"2017-11-10 13:52:45","name":"英语教学视频","id":136,"courseId":79,"resJson":"{\"id\":\"356\",\"name\":\"英语教学视频\",\"tag\":\"视频\",\"resType\":\"2\",\"coverUrl\":\"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/x6cy6AHX7mdFXyeZ3DM5KhdK21509097114773.jpg\",\"url\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/MMG8FhStHZ.mp4\",\"androidUrl\":\"\",\"isoUrl\":\"\",\"createTime\":\"2017-03-24 12:35:41\",\"info\":\"视频\",\"extendInfo\":\"视频\",\"updateTime\":\"2017-11-10 11:46:59\",\"orgId\":\"1\",\"checkState\":\"2\",\"checkUserId\":\"1\",\"checkMsg\":\"审核通过\",\"checkTime\":\"2017-09-22 14:13:19\",\"uploadUserId\":\"1\",\"orgName\":\"众可\",\"BindTime\":\"2017-11-10 13:52:46\"}"},{"code":"35-68-71-73-137","level":4,"freeState":1,"fullName":"测试课程-01 早安听力-04 口语-场景素材合并+专治疑难杂题-06 阅读-判断题满分+高頩预测-英语教学视频","pId":73,"type":2,"resId":356,"url":"http://www.yanfayi.cn:8086/preview/c_clg?d=137","createTime":"2017-11-10 13:52:50","name":"英语教学视频","id":137,"courseId":79,"resJson":"{\"id\":\"356\",\"name\":\"英语教学视频\",\"tag\":\"视频\",\"resType\":\"2\",\"coverUrl\":\"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/x6cy6AHX7mdFXyeZ3DM5KhdK21509097114773.jpg\",\"url\":\"https://sonta-file-system.oss-cn-shenzhen.aliyuncs.com/res_pic/MMG8FhStHZ.mp4\",\"androidUrl\":\"\",\"isoUrl\":\"\",\"createTime\":\"2017-03-24 12:35:41\",\"info\":\"视频\",\"extendInfo\":\"视频\",\"updateTime\":\"2017-11-10 11:46:59\",\"orgId\":\"1\",\"checkState\":\"2\",\"checkUserId\":\"1\",\"checkMsg\":\"审核通过\",\"checkTime\":\"2017-09-22 14:13:19\",\"uploadUserId\":\"1\",\"orgName\":\"众可\",\"BindTime\":\"2017-11-10 13:52:51\"}"}]
     * course : {"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jNG6x2nEjPTrHtD6ccTi7cCNF1510281104322.jpg","courseName":"新概念英语","teachTag":"新概念英语，英语，学好英语","createUserId":1,"addId":1,"introduceVideo":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/3ytBwxH3ee4JnZ41506065479105.mp4","courseKind":1,"thumbUpCount":null,"orgId":1,"info":"英语中级听说读写训练课程。欢迎关注梁海滨英语课堂微信公众号：\u201d英语一天一练\u201c学习更多免费的英语课程。"}
     */

    private CourseBean course;
    private List<CatalogBean> catalog;

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }

    public List<CatalogBean> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<CatalogBean> catalog) {
        this.catalog = catalog;
    }

    public static class CourseBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jNG6x2nEjPTrHtD6ccTi7cCNF1510281104322.jpg
         * courseName : 新概念英语
         * teachTag : 新概念英语，英语，学好英语
         * createUserId : 1
         * addId : 1
         * introduceVideo : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/3ytBwxH3ee4JnZ41506065479105.mp4
         * courseKind : 1
         * thumbUpCount : null
         * orgId : 1
         * info : 英语中级听说读写训练课程。欢迎关注梁海滨英语课堂微信公众号：”英语一天一练“学习更多免费的英语课程。
         */

        private String coverUrl;
        private String courseName;
        private String teachTag;
        private int createUserId;
        private int addId;
        private String introduceVideo;
        private int courseKind;
        private Object thumbUpCount;
        private int orgId;
        private String info;

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

        public String getTeachTag() {
            return teachTag;
        }

        public void setTeachTag(String teachTag) {
            this.teachTag = teachTag;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public int getAddId() {
            return addId;
        }

        public void setAddId(int addId) {
            this.addId = addId;
        }

        public String getIntroduceVideo() {
            return introduceVideo;
        }

        public void setIntroduceVideo(String introduceVideo) {
            this.introduceVideo = introduceVideo;
        }

        public int getCourseKind() {
            return courseKind;
        }

        public void setCourseKind(int courseKind) {
            this.courseKind = courseKind;
        }

        public Object getThumbUpCount() {
            return thumbUpCount;
        }

        public void setThumbUpCount(Object thumbUpCount) {
            this.thumbUpCount = thumbUpCount;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "CourseBean{" +
                    "coverUrl='" + coverUrl + '\'' +
                    ", courseName='" + courseName + '\'' +
                    ", teachTag='" + teachTag + '\'' +
                    ", createUserId=" + createUserId +
                    ", addId=" + addId +
                    ", introduceVideo='" + introduceVideo + '\'' +
                    ", courseKind=" + courseKind +
                    ", thumbUpCount=" + thumbUpCount +
                    ", orgId=" + orgId +
                    ", info='" + info + '\'' +
                    '}';
        }
    }

    public static class CatalogBean {
        /**
         * code : 35
         * level : 0
         * freeState : 1
         * fullName : 测试课程
         * pId : 0
         * type : 0
         * resId : null
         * url : null
         * createTime : 2017-09-22 15:31:28
         * name : 测试课程
         * id : 35
         * courseId : 79
         * resJson : {}
         */

        private String code;
        private int level;
        private int freeState;
        private String fullName;
        private int pId;
        private int type;
        private int resId;
        private String url;
        private String createTime;
        private String name;
        private int id;
        private int courseId;
        private String resJson;
        private boolean select;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getFreeState() {
            return freeState;
        }

        public void setFreeState(int freeState) {
            this.freeState = freeState;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getPId() {
            return pId;
        }

        public void setPId(int pId) {
            this.pId = pId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getResJson() {
            return resJson;
        }

        public void setResJson(String resJson) {
            this.resJson = resJson;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
    }

    @Override
    public String toString() {
        return "CourseDetailResult{" +
                "course=" + course +
                ", catalog=" + catalog +
                '}';
    }
}
