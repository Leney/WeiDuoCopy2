package com.zhongke.weiduo.mvp.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/16.
 * 体系实体类
 */

public class SystemBean implements Serializable{
    /**
     * 体系的id,唯一标识
     */
    private int systemId;
    /**
     * 体系图片
     */
    private String systemPhoto;
    /**
     * 体系名称
     */
    private String systemName;
    /**
     * 体系介绍
     */
    private String systemText;

    public SystemBean() {
    }

    public SystemBean(int systemId, String systemPhoto, String systemName, String systemText) {
        this.systemId = systemId;
        this.systemPhoto = systemPhoto;
        this.systemName = systemName;
        this.systemText = systemText;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getSystemPhoto() {
        return systemPhoto;
    }

    public void setSystemPhoto(String systemPhoto) {
        this.systemPhoto = systemPhoto;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemText() {
        return systemText;
    }

    public void setSystemText(String systemText) {
        this.systemText = systemText;
    }

    @Override
    public String toString() {
        return "SystemBean{" +
                "systemId=" + systemId +
                ", systemPhoto='" + systemPhoto + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemText='" + systemText + '\'' +
                '}';
    }

    public static List<SystemBean> getData() {
        List<SystemBean> list = new ArrayList<>();
        list.add(new SystemBean(1,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2969072748,483381186&fm=27&gp=0.jpg","香港教育体系","深圳经济特区，在国家教育的基础上，结合自身诚实的特点，制定了培养适应城市现代化，国际化，信息化的人才。"));
        list.add(new SystemBean(2,"https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=116ce2080ae93901420f856c1a853f82/7a899e510fb30f242f12b62bc395d143ad4b0309.jpg","中国教育体系","深圳经济特区，在国家教育的基础上，结合自身诚实的特点，制定了培养适应城市现代化，国际化，信息化的人才。"));
        list.add(new SystemBean(3,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2175362478,3816406498&fm=27&gp=0.jpg","美国教育体系","深圳经济特区，在国家教育的基础上，结合自身诚实的特点，制定了培养适应城市现代化，国际化，信息化的人才。"));
        list.add(new SystemBean(4,"https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=62e58768db54564ef168ec6bd2b7f7e7/7e3e6709c93d70cf10761176fedcd100baa12b38.jpg","澳门教育体系","深圳经济特区，在国家教育的基础上，结合自身诚实的特点，制定了培养适应城市现代化，国际化，信息化的人才。"));
        return list;
    }
}
