package com.zhongke.weiduo.mvp.model.entity;

import java.io.Serializable;

/**
 * Created by ${tanlei} on 2017/9/5.
 * 学生评价实体类
 */

public class StudentEvaluationBean implements Serializable{
    /**
     * 学生名
     */
    private String studentName;
    /**
     * 学生照片
     */
    private String studentPhoto;
    /**
     * 评价星级
     */
    private int evaluationLevel;
    /**
     * 评价内容
     */
    private String evaluationText;
    /**
     * 评价时间
     */
    private long evaluationDate;
    /**
     * 点赞数
     */
    private int like;

    public StudentEvaluationBean() {
    }

    public StudentEvaluationBean(String studentName, String studentPhoto, int evaluationLevel, String evaluationText, long evaluationDate, int like) {
        this.studentName = studentName;
        this.studentPhoto = studentPhoto;
        this.evaluationLevel = evaluationLevel;
        this.evaluationText = evaluationText;
        this.evaluationDate = evaluationDate;
        this.like = like;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhoto() {
        return studentPhoto;
    }

    public void setStudentPhoto(String studentPhoto) {
        this.studentPhoto = studentPhoto;
    }

    public int getEvaluationLevel() {
        return evaluationLevel;
    }

    public void setEvaluationLevel(int evaluationLevel) {
        this.evaluationLevel = evaluationLevel;
    }

    public String getEvaluationText() {
        return evaluationText;
    }

    public void setEvaluationText(String evaluationText) {
        this.evaluationText = evaluationText;
    }

    public long getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(long evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "StudentEvaluationBean{" +
                "studentName='" + studentName + '\'' +
                ", studentPhoto='" + studentPhoto + '\'' +
                ", evaluationLevel=" + evaluationLevel +
                ", evaluationText='" + evaluationText + '\'' +
                ", evaluationDate=" + evaluationDate +
                ", like=" + like +
                '}';
    }
}
