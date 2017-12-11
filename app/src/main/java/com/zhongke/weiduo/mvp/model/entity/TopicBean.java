package com.zhongke.weiduo.mvp.model.entity;

/**
 * 题目对象
 * Created by llj on 2017/8/28.
 */

public class TopicBean {
    /** 题目内容*/
    private String title;
    /** 选项数组*/
    private String[] options;
    /** 正确答案的position*/
    private int rightPosition;

    public String getTitle() {
        return title;
    }

    public TopicBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String[] getOptions() {
        return options;
    }

    public TopicBean setOptions(String[] options) {
        this.options = options;
        return this;
    }

    public int getRightPosition() {
        return rightPosition;
    }

    public TopicBean setRightPosition(int rightPosition) {
        this.rightPosition = rightPosition;
        return this;
    }
}
