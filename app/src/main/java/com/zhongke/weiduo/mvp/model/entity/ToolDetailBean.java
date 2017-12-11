package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 活动用具清单Bean
 * Created by llj on 2017/6/28.
 */

public class ToolDetailBean {

    /**
     * isManager : 0
     * prinId : 100231
     * prinName : 爸爸
     * toolList : [{"id":1,"name":"口语教材","isOk":0},{"id":2,"name":"耳麦","isOk":1},{"id":3,"name":"门票","isOk":0}]
     * roleList : [{"id":120011,"name":"爸爸"},{"id":120012,"name":"妈妈"}]
     */

    private int isManager;
    private int prinId;
    private String prinName;
    private List<ToolListBean> toolList;
    private List<RoleListBean> roleList;

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public int getPrinId() {
        return prinId;
    }

    public void setPrinId(int prinId) {
        this.prinId = prinId;
    }

    public String getPrinName() {
        return prinName;
    }

    public void setPrinName(String prinName) {
        this.prinName = prinName;
    }

    public List<ToolListBean> getToolList() {
        return toolList;
    }

    public void setToolList(List<ToolListBean> toolList) {
        this.toolList = toolList;
    }

    public List<RoleListBean> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleListBean> roleList) {
        this.roleList = roleList;
    }

    public static class ToolListBean {
        /**
         * id : 1
         * name : 口语教材
         * isOk : 0
         */

        private int id;
        private String name;
        private int isOk;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIsOk() {
            return isOk;
        }

        public void setIsOk(int isOk) {
            this.isOk = isOk;
        }
    }

    public static class RoleListBean {
        /**
         * id : 120011
         * name : 爸爸
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
