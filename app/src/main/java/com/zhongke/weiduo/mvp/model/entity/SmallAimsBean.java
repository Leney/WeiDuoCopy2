package com.zhongke.weiduo.mvp.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/19.
 * 微目标实体类
 */

public class SmallAimsBean implements Serializable{
    /**
     * 微目标名称
     */
    private String smallAnimsName;
    /**
     * 微目标ID（唯一标识）
     */
    private int smallAnimsId;

    public SmallAimsBean() {
    }

    public SmallAimsBean(String smallAnimsName, int smallAnimsId) {
        this.smallAnimsName = smallAnimsName;
        this.smallAnimsId = smallAnimsId;
    }

    public String getSmallAnimsName() {
        return smallAnimsName;
    }

    public void setSmallAnimsName(String smallAnimsName) {
        this.smallAnimsName = smallAnimsName;
    }

    public int getSmallAnimsId() {
        return smallAnimsId;
    }

    public void setSmallAnimsId(int smallAnimsId) {
        this.smallAnimsId = smallAnimsId;
    }

    @Override
    public String toString() {
        return "SmallAimsBean{" +
                "smallAnimsName='" + smallAnimsName + '\'' +
                ", smallAnimsId=" + smallAnimsId +
                '}';
    }

    public static List<SmallAimsBean> getData() {
        List<SmallAimsBean> list = new ArrayList<>();
        list.add(new SmallAimsBean("品德", 1));
        list.add(new SmallAimsBean("坚定的理想信念", 2));
        list.add(new SmallAimsBean("弘扬传统美德", 3));
        list.add(new SmallAimsBean("培养文明行为", 4));
        list.add(new SmallAimsBean("传承深圳精神", 5));
        return list;
    }
}
