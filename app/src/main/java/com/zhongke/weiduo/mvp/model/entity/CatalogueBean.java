package com.zhongke.weiduo.mvp.model.entity;

/**
 * 目录对象
 * Created by llj on 2017/9/14.
 */

public class CatalogueBean {
    /**
     * 章节名称
     */
    private String indexName;
    /**
     * 目录名称
     */
    private String name;
    /**
     * 分钟数
     */
    private int time;
    /**
     * 视频地址
     */
    private String videoUrl;
    /**
     * 视频封面地址
     */
    private String thumpUrl;
    /**
     * 是否正在播放此目录下的视频
     */
    private boolean isSelect;

    public String getIndexName() {
        return indexName;
    }

    public CatalogueBean setIndexName(String indexName) {
        this.indexName = indexName;
        return this;
    }

    public String getName() {
        return name;
    }

    public CatalogueBean setName(String name) {
        this.name = name;
        return this;
    }

    public int getTime() {
        return time;
    }

    public CatalogueBean setTime(int time) {
        this.time = time;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public CatalogueBean setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getThumpUrl() {
        return thumpUrl;
    }

    public CatalogueBean setThumpUrl(String thumpUrl) {
        this.thumpUrl = thumpUrl;
        return this;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public CatalogueBean setSelect(boolean select) {
        isSelect = select;
        return this;
    }
}
