package com.zhongke.weiduo.mvp.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/20.
 */

/**
 * 教学内容中的课程对象
 */
public class TeachContentBean implements Serializable {
    /**
     * 课程名称
     */
    private String name;
    /**
     * 播放方式集合
     */
    private List<PlayModeBean> value;

    public String getTeachContentName() {
        return name;
    }

    public void setTeachContentName(String teachContentName) {
        this.name = teachContentName;
    }

    public List<PlayModeBean> getPlayModeBeans() {
        return value;
    }

    public void setPlayModeBeans(List<PlayModeBean> playModeBeans) {
        this.value = playModeBeans;
    }
}
