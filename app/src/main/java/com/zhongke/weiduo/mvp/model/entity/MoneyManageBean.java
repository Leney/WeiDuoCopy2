package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 资金管理Bean
 * Created by llj on 2017/6/27.
 */

public class MoneyManageBean {

    /**
     * isManager : 0
     * prinId : 100231
     * prinName : 爸爸
     * money : 200
     * unit : 次
     * roleList : [{"id":120011,"name":"爸爸"},{"id":120012,"name":"妈妈"}]
     */

    private int isManager;
    private int prinId;
    private String prinName;
    private int money;
    private String unit;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<RoleListBean> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleListBean> roleList) {
        this.roleList = roleList;
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
