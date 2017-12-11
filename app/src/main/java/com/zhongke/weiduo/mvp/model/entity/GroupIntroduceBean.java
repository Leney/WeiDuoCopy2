package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by hyx on 2017/10/31.
 */

public class GroupIntroduceBean  {

    private String groupPictureUrl;
    private String label;
    private String location;
    private String memberAvatarUrl;
    private int totalNumber;
    private String groupIntroduceText;

    public String getGroupPictureUrl() {
        return groupPictureUrl;
    }

    public void setGroupPictureUrl(String groupPictureUrl) {
        this.groupPictureUrl = groupPictureUrl;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMemberAvatarUrl() {
        return memberAvatarUrl;
    }

    public void setMemberAvatarUrl(String memberAvatarUrl) {
        this.memberAvatarUrl = memberAvatarUrl;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getGroupIntroduceText() {
        return groupIntroduceText;
    }

    public void setGroupIntroduceText(String groupIntroduceText) {
        this.groupIntroduceText = groupIntroduceText;
    }
}
