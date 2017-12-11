package com.zhongke.weiduo.mvp.model.entity;

/**
 * Banner对象
 * Created by llj on 2017/9/5.
 */
public class BannerInfo {
    public int id;
    /** 类型(后续定义)*/
    public int type;
    /** 展示图片地址*/
    public String icon;
    /** 对应的值(比如是跳转到网页，则就是跳转链接)*/
    public String value;
    /** 描述*/
    public String describle;
}
