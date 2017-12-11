package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by hyx on 2017/10/31.
 */

public class GrantRewardBean {

    /**
     * 几星奖励
     */
    private int rewardLevel;
    private String rewardInfo;
    /**
     * 愿望
     */
    private String desireText;

    public int getRewardLevel() {
        return rewardLevel;
    }

    public void setRewardLevel(int rewardLevel) {
        this.rewardLevel = rewardLevel;
    }

    public String getRewardInfo() {
        return rewardInfo;
    }

    public void setRewardInfo(String rewardInfo) {
        this.rewardInfo = rewardInfo;
    }

    public String getDesireText() {
        return desireText;
    }

    public void setDesireText(String desireText) {
        this.desireText = desireText;
    }

}
