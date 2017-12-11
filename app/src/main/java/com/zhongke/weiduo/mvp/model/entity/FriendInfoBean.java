package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by hyx on 2017/11/18.
 * 获取好友资料 bean
 */

public class FriendInfoBean {

    /**
     * user : {"familyAddress":"asdas","birthday":"1984-11-30","tagList":null,"school":null,"nickName":"苏苏","headImageUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/PictureFile/8cc405d996aa4a8b8196b0899ddbb14e/1510733658085.jpg","sex":2,"fullName":"丁吉慧","majorList":null,"id":90,"userName":"testAdmin","info":"测试"}
     */

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * familyAddress : asdas
         * birthday : 1984-11-30
         * tagList : null
         * school : null
         * nickName : 苏苏
         * headImageUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/PictureFile/8cc405d996aa4a8b8196b0899ddbb14e/1510733658085.jpg
         * sex : 2
         * fullName : 丁吉慧
         * majorList : null
         * id : 90
         * userName : testAdmin
         * info : 测试
         */

        private String familyAddress;
        private String birthday;
        private Object tagList;
        private Object school;
        private String nickName;
        private String headImageUrl;
        private int sex;
        private String fullName;
        private Object majorList;
        private int id;
        private String userName;
        private String info;

        public String getFamilyAddress() {
            return familyAddress;
        }

        public void setFamilyAddress(String familyAddress) {
            this.familyAddress = familyAddress;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public Object getTagList() {
            return tagList;
        }

        public void setTagList(Object tagList) {
            this.tagList = tagList;
        }

        public Object getSchool() {
            return school;
        }

        public void setSchool(Object school) {
            this.school = school;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Object getMajorList() {
            return majorList;
        }

        public void setMajorList(Object majorList) {
            this.majorList = majorList;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "familyAddress='" + familyAddress + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", tagList=" + tagList +
                    ", school=" + school +
                    ", nickName='" + nickName + '\'' +
                    ", headImageUrl='" + headImageUrl + '\'' +
                    ", sex=" + sex +
                    ", fullName='" + fullName + '\'' +
                    ", majorList=" + majorList +
                    ", id=" + id +
                    ", userName='" + userName + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }
}
