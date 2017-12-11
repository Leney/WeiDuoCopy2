package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by dgg1 on 2017/11/15.
 * 创建群组的群组实体类
 */

public class NewGroupBean {

    /**
     * group : {"createUserId":90,"groupType":2,"gName":"testAdmin、admin的群聊","createTime":"2017-11-15","gInfo":"testAdmin、admin的群聊","id":89,"orgId":1}
     */

    private GroupBean group;

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public static class GroupBean {
        /**
         * createUserId : 90
         * groupType : 2
         * gName : testAdmin、admin的群聊
         * createTime : 2017-11-15
         * gInfo : testAdmin、admin的群聊
         * id : 89
         * orgId : 1
         */

        private int createUserId;
        private int groupType;
        private String gName;
        private String createTime;
        private String gInfo;
        private int id;
        private int orgId;

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public int getGroupType() {
            return groupType;
        }

        public void setGroupType(int groupType) {
            this.groupType = groupType;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getGInfo() {
            return gInfo;
        }

        public void setGInfo(String gInfo) {
            this.gInfo = gInfo;
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
    }
}
