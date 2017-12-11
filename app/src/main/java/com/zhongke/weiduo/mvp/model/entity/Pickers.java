package com.zhongke.weiduo.mvp.model.entity;

import java.io.Serializable;

/**
 * 滚动选择器的实体类
 * Created by llj on 2017/6/22.
 */

public class Pickers implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int id;
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Pickers setName(String name) {
        this.name = name;
        return this;
    }

    public Pickers setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public Pickers(String showContent, int showId) {
        super();
        this.name = showContent;
        this.id = showId;
    }

    public Pickers() {
        super();
    }
}
