package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by Karma on 2017/8/16.
 * 类描述：视频和图片轮播实体类
 */

public class PhotoOrVideo extends Entity {
    private String photoUrl;
    private static PhotoOrVideo mInstance;

    public static PhotoOrVideo getInstance() {
        if (mInstance == null) {
            synchronized (PhotoOrVideo.class) {//保证异步处理安全操作
                if (mInstance == null) {
                    mInstance = new PhotoOrVideo();
                }
            }
        }
        return mInstance;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
