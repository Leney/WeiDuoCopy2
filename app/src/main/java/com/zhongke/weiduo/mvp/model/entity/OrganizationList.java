package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by Karma on 2017/9/4.
 * 类描述：
 */

public class OrganizationList extends Entity {
    private String img;
    private String title;
    private String content;
    private int count;
    private int people;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "OrganizationList{" +
                "img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", count=" + count +
                ", people=" + people +
                '}';
    }
}
