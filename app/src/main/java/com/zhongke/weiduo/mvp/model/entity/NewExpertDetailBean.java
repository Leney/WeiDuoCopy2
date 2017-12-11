package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by dgg1 on 2017/11/8.
 * 专家详情介绍实体
 */

public class NewExpertDetailBean {

    /**
     * expert : {"teachTag":"美术","nickName":"狐妖小红娘","userId":90,"orgId":1,"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/RnstXT8XZ4rM5ftktdmyaB6QN1509085032882.jpg","id":87,"introduceVideo":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Pn6WSDXkEt62wZrpxACHJaMsA1508740849165.avi","info":"国内有名的漫画师，中国话，树苗高手等。曾连续7年夺得世界漫画，中国话，树苗的冠军。"}
     */

    private ExpertBean expert;

    public ExpertBean getExpert() {
        return expert;
    }

    public void setExpert(ExpertBean expert) {
        this.expert = expert;
    }

    public static class ExpertBean {
        /**
         * teachTag : 美术
         * nickName : 狐妖小红娘
         * userId : 90
         * orgId : 1
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/RnstXT8XZ4rM5ftktdmyaB6QN1509085032882.jpg
         * id : 87
         * introduceVideo : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Pn6WSDXkEt62wZrpxACHJaMsA1508740849165.avi
         * info : 国内有名的漫画师，中国话，树苗高手等。曾连续7年夺得世界漫画，中国话，树苗的冠军。
         */

        private String teachTag;
        private String nickName;
        private int userId;
        private int orgId;
        private String coverUrl;
        private int id;
        private int collectId;
        private String introduceVideo;
        private String info;

        public int getCollectId() {
            return collectId;
        }

        public void setCollectId(int collectId) {
            this.collectId = collectId;
        }

        public String getTeachTag() {
            return teachTag;
        }

        public void setTeachTag(String teachTag) {
            this.teachTag = teachTag;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntroduceVideo() {
            return introduceVideo;
        }

        public void setIntroduceVideo(String introduceVideo) {
            this.introduceVideo = introduceVideo;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
