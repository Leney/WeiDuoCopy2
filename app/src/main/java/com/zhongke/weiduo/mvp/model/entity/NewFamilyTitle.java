package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/13.
 */

public class NewFamilyTitle {

    private List<SysDictBean> SysDict;

    public List<SysDictBean> getSysDict() {
        return SysDict;
    }

    public void setSysDict(List<SysDictBean> SysDict) {
        this.SysDict = SysDict;
    }

    public static class SysDictBean {
        /**
         * typeInfo : 家庭称呼
         * enName : baba
         * dOrder : 1
         * id : 85
         * title : 爸爸
         * type : fTitleName
         * value : 1
         */

        private String typeInfo;
        private String enName;
        private int dOrder;
        private int id;
        private String title;
        private String type;
        private int value;

        public String getTypeInfo() {
            return typeInfo;
        }

        public void setTypeInfo(String typeInfo) {
            this.typeInfo = typeInfo;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public int getDOrder() {
            return dOrder;
        }

        public void setDOrder(int dOrder) {
            this.dOrder = dOrder;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
