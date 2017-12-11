package com.zhongke.weiduo.mvp.model.entity;

/**
 * 联系人列表实体类(转换之后的实体)
 * Created by llj on 2017/11/15.
 */

public class ContactsListBean {
    /**
     * 好友类型
     */
    public static final int TYPE_FRIEND_PERSON = 0;
    /**
     * 我的家庭类型
     */
    public static final int TYPE_MY_FAMILY = 1;
    /**
     * 友好家庭类型
     */
    public static final int TYPE_FRIEND_FAMILY = 2;
    /**
     * 群组类型
     */
    public static final int TYPE_GROUP = 3;
    /**
     * 设备类型
     */
    public static final int TYPE_DEVICE = 4;
    /**
     * 类型，0=好友，1=我的家庭，2=友好家庭，3=群组，4=设备
     */
    public int type;
    /**
     * 联系人id
     */
    public int id;
    /**
     * 创建时间
     */
    public String createTime;
    /**
     * 头像地址
     */
    public String headUrl;
    /**
     * 昵称
     */
    public String nickName;
    /**
     * 封面图片，我的家庭、友好家庭和群组所需字段
     */
    public String coverUrl;
    /**
     * 创建者id(管理员id)，我的家庭、友好家庭、群组所需字段
     */
    public int createUserId;
    /**
     * 描述信息,
     */
    public String info;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 创建群聊时是否被选中标识
     */
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
