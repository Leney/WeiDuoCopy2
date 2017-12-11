package com.zhongke.weiduo.mvp.model.entity.school_course;

/**
 * Created by hyx on 2017/11/8.
 * 课程详情  目录
 */

public class CataLogBean {

    /**
     * id : 35
     * name : 测试课程
     * fullName : 测试课程
     * level : 0
     * pId : null
     * code : 35
     * courseId : 79
     * url : null
     * resJson : {}
     * resId : null
     * type : 0
     * freeState : 1
     */

    private int id;
    private String name;
    private String fullName;
    private int level;
    private Object pId;
    private String code;
    private int courseId;
    private Object url;
    private String resJson;
    private Object resId;
    private int type;
    private int freeState;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    private boolean select;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Object getPId() {
        return pId;
    }

    public void setPId(Object pId) {
        this.pId = pId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public String getResJson() {
        return resJson;
    }

    public void setResJson(String resJson) {
        this.resJson = resJson;
    }

    public Object getResId() {
        return resId;
    }

    public void setResId(Object resId) {
        this.resId = resId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFreeState() {
        return freeState;
    }

    public void setFreeState(int freeState) {
        this.freeState = freeState;
    }
}
