package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 家庭类别实体类
 * Created by llj on 2017/6/22.
 */

public class FamilyClassifyListBean {

    private List<ClassifyListBean> classifyList;

    public List<ClassifyListBean> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<ClassifyListBean> classifyList) {
        this.classifyList = classifyList;
    }

    public static class ClassifyListBean {
        /**
         * id : 122312
         * name : 技能类
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
