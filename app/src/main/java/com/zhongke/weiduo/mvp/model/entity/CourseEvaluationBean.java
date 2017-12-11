package com.zhongke.weiduo.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/14.
 * 课程评价的实体
 */

public class CourseEvaluationBean {
    private String photo;
    private String name;
    private String evaluationText;
    private float score;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CourseEvaluationBean() {
    }

    public CourseEvaluationBean(String photo, String name, String evaluationText, float score) {
        this.photo = photo;
        this.name = name;
        this.evaluationText = evaluationText;
        this.score = score;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvaluationText() {
        return evaluationText;
    }

    public void setEvaluationText(String evaluationText) {
        this.evaluationText = evaluationText;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public static List<CourseEvaluationBean> getData() {
        List<CourseEvaluationBean> list = new ArrayList<>();
        list.add(new CourseEvaluationBean("http://m.qqju.com/pic/tx/tx6061.jpg", "青青", "这次活动非常的棒", 4.7f));
        list.add(new CourseEvaluationBean("http://g.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=55e948ce42a98226b8942321bab29539/c8177f3e6709c93dcee2c44c9e3df8dcd100547d.jpg", "屡屡", "这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒这次活动非常的棒", 5f));
        list.add(new CourseEvaluationBean("http://qq1234.org/uploads/allimg/141104/3_141104135129_11.jpg", "傻傻", "这次活动非常的棒", 4f));
        list.add(new CourseEvaluationBean("http://img5.duitang.com/uploads/item/201402/13/20140213092819_n5TBT.thumb.224_0.jpeg", "萌萌", "这次活动非常的棒", 2.8f));
        return list;
    }
}
