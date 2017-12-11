package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by Karma on 2017/7/4.
 * 类描述：视频实体类
 */

public class FileMessage extends MessageContent{
    private String videoUrl;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
