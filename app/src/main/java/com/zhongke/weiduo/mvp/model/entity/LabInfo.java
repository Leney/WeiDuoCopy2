package com.zhongke.weiduo.mvp.model.entity;

/**
 * 标签实体类
 * Created by llj on 2017/6/21.
 */

public class LabInfo {
    private String name;
    private boolean isCheck;

    public String getName() {
        return name;
    }

    public LabInfo setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public LabInfo setCheck(boolean check) {
        isCheck = check;
        return this;
    }
}
