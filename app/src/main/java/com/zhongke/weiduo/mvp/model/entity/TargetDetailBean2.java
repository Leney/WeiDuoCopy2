package com.zhongke.weiduo.mvp.model.entity;

/**
 * 目标详情
 * Created by llj on 2017/11/10.
 */

public class TargetDetailBean2 {

    /**
     * target : {"createUserId":90,"videoUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/caHrSEZzh73njZRHsbh6yG6ni1509153429902.mp4","createTime":"2017-11-16 11:42:40","introduce":"1.我要努力学习","imageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/hA7CiidMJYSkRYp4XisQspANc1509153425309.gif","name":"不挑食","addId":1,"id":86,"orgId":1,"info":"不挑食，多吃蔬菜"}
     */

    private TargetBean target;

    public TargetBean getTarget() {
        return target;
    }

    public void setTarget(TargetBean target) {
        this.target = target;
    }

    public static class TargetBean {
        /**
         * createUserId : 90
         * videoUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/caHrSEZzh73njZRHsbh6yG6ni1509153429902.mp4
         * createTime : 2017-11-16 11:42:40
         * introduce : 1.我要努力学习
         * imageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/hA7CiidMJYSkRYp4XisQspANc1509153425309.gif
         * name : 不挑食
         * addId : 1
         * id : 86
         * orgId : 1
         * info : 不挑食，多吃蔬菜
         */

        private int createUserId;
        private String videoUrl;
        private String createTime;
        private String introduce;
        private String imageUrl;
        private String name;
        private int addId;
        private int id;
        private int orgId;
        private String info;

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAddId() {
            return addId;
        }

        public void setAddId(int addId) {
            this.addId = addId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}
