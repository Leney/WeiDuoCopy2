package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by hyx on 2017/11/23.
 * 用户收藏列表bean
 */

public class UserCollectListBean {
    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"orgName":"众可","nickName":null,"headImageUrl":null,"title":null,"userId":null,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":1,"collectType":3,"courseName":null,"createTime":"2017-11-21 20:39:46","logo":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/74b7QXEYbrBbQjRdxdaXR4Nz51509417087517.png","actionId":null,"courseId":null},{"orgName":null,"nickName":"李老师","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/n5Y3yMWFrkf4yGa8cBH5kj2fP1509085045202.jpg","title":null,"userId":1,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":null,"collectType":1,"courseName":null,"createTime":"2017-11-21 09:41:58","logo":null,"actionId":null,"courseId":null},{"orgName":null,"nickName":"曹操","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/MJ3cTrRzrZ5yWCJfFc8iMiANn1508740835110.gif","title":null,"userId":97,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":null,"collectType":1,"courseName":null,"createTime":"2017-11-20 18:02:18","logo":null,"actionId":null,"courseId":null},{"orgName":null,"nickName":"33老师","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/kawxiReehX22iDMhFEKjiWjZb1510279879356.jpg","title":null,"userId":106,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":null,"collectType":1,"courseName":null,"createTime":"2017-11-20 18:01:57","logo":null,"actionId":null,"courseId":null},{"orgName":"小猫企业","nickName":null,"headImageUrl":null,"title":null,"userId":null,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":66,"collectType":3,"courseName":null,"createTime":"2017-11-20 16:58:39","logo":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/bex5pSc36hjJMP6wZiBprmC6B1509417540498.png","actionId":null,"courseId":null},{"orgName":null,"nickName":"英语一天一练 ","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/iD7ytRKGa8FWCmyCiDineQW8C1509623148812.jpg","title":null,"userId":2,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":null,"collectType":1,"courseName":null,"createTime":"2017-11-20 16:37:54","logo":null,"actionId":null,"courseId":null},{"orgName":null,"nickName":"英语专家","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/C2BExtFGCT4RpNWRKSCr7SQ441509085226793.png","title":null,"userId":107,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":null,"collectType":1,"courseName":null,"createTime":"2017-11-20 16:34:09","logo":null,"actionId":null,"courseId":null},{"orgName":null,"nickName":null,"headImageUrl":null,"title":null,"userId":null,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":null,"collectType":4,"courseName":null,"createTime":"2017-11-09 10:20:00","logo":null,"actionId":null,"courseId":null},{"orgName":null,"nickName":null,"headImageUrl":null,"title":null,"userId":null,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":null,"collectType":4,"courseName":null,"createTime":"2017-11-09 10:19:55","logo":null,"actionId":null,"courseId":null},{"orgName":null,"nickName":null,"headImageUrl":null,"title":null,"userId":null,"actionCoverUrl":null,"courseCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jNG6x2nEjPTrHtD6ccTi7cCNF1510281104322.jpg","orgId":null,"collectType":2,"courseName":"新概念英语","createTime":"2017-11-04 14:11:14","logo":null,"actionId":null,"courseId":79},{"orgName":null,"nickName":null,"headImageUrl":null,"title":null,"userId":null,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":null,"collectType":4,"courseName":null,"createTime":"2017-11-04 13:45:34","logo":null,"actionId":null,"courseId":null},{"orgName":null,"nickName":null,"headImageUrl":null,"title":null,"userId":null,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":null,"collectType":2,"courseName":null,"createTime":"2017-11-02 17:43:12","logo":null,"actionId":null,"courseId":null},{"orgName":null,"nickName":null,"headImageUrl":null,"title":null,"userId":null,"actionCoverUrl":null,"courseCoverUrl":null,"orgId":null,"collectType":2,"courseName":null,"createTime":"2017-11-01 16:55:07","logo":null,"actionId":null,"courseId":null}]
     * recordTotal : 13
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
         * orgName : 众可
         * nickName : null
         * headImageUrl : null
         * title : null
         * userId : null
         * actionCoverUrl : null
         * courseCoverUrl : null
         * orgId : 1
         * collectType : 3
         * courseName : null
         * createTime : 2017-11-21 20:39:46
         * logo : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/74b7QXEYbrBbQjRdxdaXR4Nz51509417087517.png
         * actionId : null
         * courseId : null
         */

        private String orgName;
        private String nickName;
        private String headImageUrl;
        private String title;
        private int userId;
        private String actionCoverUrl;
        private String courseCoverUrl;
        private int orgId;
        private int collectType;
        private String courseName;
        private String createTime;
        private String logo;
        private int actionId;
        private int courseId;

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadImageUrl() {
            return headImageUrl;
        }

        public void setHeadImageUrl(String headImageUrl) {
            this.headImageUrl = headImageUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getActionCoverUrl() {
            return actionCoverUrl;
        }

        public void setActionCoverUrl(String actionCoverUrl) {
            this.actionCoverUrl = actionCoverUrl;
        }

        public String getCourseCoverUrl() {
            return courseCoverUrl;
        }

        public void setCourseCoverUrl(String courseCoverUrl) {
            this.courseCoverUrl = courseCoverUrl;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getCollectType() {
            return collectType;
        }

        public void setCollectType(int collectType) {
            this.collectType = collectType;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getActionId() {
            return actionId;
        }

        public void setActionId(int actionId) {
            this.actionId = actionId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }
    }
}
