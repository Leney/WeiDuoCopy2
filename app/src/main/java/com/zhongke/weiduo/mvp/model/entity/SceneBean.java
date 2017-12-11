package com.zhongke.weiduo.mvp.model.entity;

import com.zhongke.weiduo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/21.
 * 情景灯情景实体
 */

public class SceneBean {
    private int id;
    private String name;
    private String url;
    private int resId;

    public SceneBean() {
    }

    public SceneBean(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public SceneBean(int id, String name, int resId) {
        this.id = id;
        this.name = name;
        this.resId = resId;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResId() {
        return resId;
    }

    public SceneBean setResId(int resId) {
        this.resId = resId;
        return this;
    }

    @Override
    public String toString() {
        return "SceneBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public static List<SceneBean> getData() {
        List<SceneBean> list = new ArrayList<>();
        list.add(new SceneBean(1, "倒计时", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506001098518&di=4cdc1d00787143d37fd6fb9701cbd1bf&imgtype=0&src=http%3A%2F%2Fimg01.taopic.com%2F150323%2F240391-1503230T40522.jpg"));
        list.add(new SceneBean(2, "早上好", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506001128973&di=19e4166f5ae280b2e3b2cea2274c9679&imgtype=0&src=http%3A%2F%2Fy1.ifengimg.com%2F67cc586dde7ab2c2%2F2015%2F0305%2Frdn_54f801eae368a.jpg"));
        list.add(new SceneBean(3, "真棒", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506001151251&di=3e75c5df01672a8a40ffebfa1820fb3c&imgtype=0&src=http%3A%2F%2Fwww.cqzql.com%2Fuploads%2Fmain%2Fallimg%2F20151214%2F20151214152602.jpg"));
//        list.add(new SceneBean(4, "快乐", "http://img.61gequ.com/allimg/2011-5/20115209394699315.jpg"));
//        list.add(new SceneBean(5, "森林", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506001192166&di=da142443147f6461ea890f6d4a5fb898&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Dd1500abf48a7d933aba5ec30c522bb66%2Fc995d143ad4bd113ad13879950afa40f4afb05ba.jpg"));
//        list.add(new SceneBean(6, "海洋", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506001208698&di=95df4013612ca4b059de6d2f32881335&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160924%2F98d7b7dd99c74283a8ddbcf2996a0eb1_th.jpg"));
//        list.add(new SceneBean(7, "宇宙", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506001224758&di=76db28d6041e260a1dec0c6d533ae897&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F12%2F92%2F85A58PICrED_1024.jpg"));
        return list;
    }

    public static List<SceneBean> getData2() {
        List<SceneBean> list = new ArrayList<>();
        list.add(new SceneBean(1, "倒计时", R.drawable.count_down));
        list.add(new SceneBean(2, "早上好", R.drawable.morning));
        list.add(new SceneBean(3, "真棒", R.drawable.good_well));
        return list;
    }
}
