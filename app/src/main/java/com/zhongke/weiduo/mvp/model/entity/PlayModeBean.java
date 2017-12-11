package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by ${tanlei} on 2017/6/21.
 */

/**
 * 播放模式对象
 */
public class PlayModeBean {
    /**
     * 播放的角度值
     */
    private String playAngleValue;
    /**
     * 播放的时间值
     */
    private String playAngleDate;
    /**
     * 播放的方式，是主屏播放，还是投影播放
     */
    private String playPlace;

    public String getPlayAngleValue() {
        return playAngleValue;
    }

    public void setPlayAngleValue(String playAngleValue) {
        this.playAngleValue = playAngleValue;
    }

    public String getPlayAngleDate() {
        return playAngleDate;
    }

    public void setPlayAngleDate(String playAngleDate) {
        this.playAngleDate = playAngleDate;
    }

    public String getPlayPlace() {
        return playPlace;
    }

    public void setPlayPlace(String playPlace) {
        this.playPlace = playPlace;
    }
}
