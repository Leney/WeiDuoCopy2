package com.zhongke.weiduo.mvp.db.local;

import android.provider.BaseColumns;

/**
 * Created by ${xingen} on 2017/7/7.
 * 创建BaseColumn的实现类，存放数据库需要用到的常量配置
 */

public final class Data implements BaseColumns {
    /**
     * 数据库信息
     */
    public static final String SQLITE_NAME = "WeiDuo.db";
    public static final int SQLITE_VERSON = 1;


    /** 用户信息表*/

    public static final String TABLE_USER_INFO = "table_user_info";


    public static final String COLUMNS_USER_ID = "user_id";
    public static final String COLUMNS_USER_PHONE = "phone";

    public static final String COLUMNS_HAVE_FAMILY = "have_family";
    public static final String COLUMNS_COINS = "coins";
    public static final String COLUMNS_INTEGRAL = "integral";

    /**
     * 登陆用户信息表名称
     */
    public static final String COLUMNS_USER_TOKEN = "user_token";
    public static final String COLUMNS_BIRTHEDAY="birthday";
    public static final String COLUMNS_NICK_NAME = "nickName";
    public static final String COLUMNS_HeadImageUrl = "headImageUrl";
    public static final String COLUMNS_SEX="sex";
    public static final String COLUMNS_MAJORLIST="majorList";
    public static final String COLUMNS_DEVICE_CODE="deviceCode";
    public static final String COLUMNS_registerType="registerType";
    public static final String COLUMNS_userName="userName";
    public static final String COLUMNS_orgId="orgId";
    public static final String COLUMNS_tagList="tagList";
    public static final String COLUMNS_school="school";
    public static final String COLUMNS_phoneCode="phoneCode";
    public static final String COLUMNS_id="id";
    public static final String COLUMNS_registerCode="registerCode";
    public static final String COLUMNS_info="info";


    /**
     * 联系人列表表字段
     */
    /** 好友/我的家庭/友好家庭/群组id*/
    public static final String TABLE_CONTACTS = "table_contacts";
    /**
     * 类别,0=好友，1=我的家庭，2=友好家庭，3=群组
     */
    public static final String COLUMNS_CONTACTS_TYPE = "type";
    /** 好友/我的家庭/友好家庭/群组id*/
    public static final String COLUMNS_CONTACTS_ID = "contacts_id";
    /**
     * 好友/我的家庭/友好家庭/群组创建时间
     */
    public static final String COLUMNS_CONTACTS_CREATE_TIME = "create_time";
    /**
     * 好友/友好家庭/群组头像地址
     */
    public static final String COLUMNS_CONTACTS_HEAD_URL = "head_url";
    /**
     * 好友/友好家庭/群组昵称
     */
    public static final String COLUMNS_CONTACTS_NICK_NAME = "nick_name";
    /**
     * 友好家庭/群组,创建者id(管理员id)
     */
    public static final String COLUMNS_CONTACTS_CREATE_USER_ID = "create_user_id";
    /**
     * 友好家庭/群组封面图片
     */
    public static final String COLUMNS_CONTACTS_COVER_URL = "cover_url";
    /**
     * 友好家庭/群组描述信息
     */
    public static final String COLUMNS_CONTACTS_INFO = "info";









    /**
     * 上传视频地址
     */
    public static final String COLUMNS_UPLOAD_VIDEO_URL = "upload_video_url";
    /**
     * 上传图片地址
     */
    public static final String COLUMNS_UPLOAD_PIC_URL = "upload_pic_url";
    /**
     * 所加入的家庭id组合字符串，以"," 分割
     */
    public static final String COLUMNS_FAMILY_IDS = "family_ids";


    /**
     * 用户所拥有的的家庭信息表
     */
    public static final String TABLE_USER_FAMILY_INFO = "table_user_family_info";
    public static final String COLUMNS_FAMILY_ID = "family_id";
    /**
     * 家庭名称
     */
    public static final String COLUMNS_FAMILY_NAME = "family_name";
    /**
     * 用户在家庭中的角色名称
     */
    public static final String COLUMNS_ROLE_NAME = "role_name";
    /**
     * 家庭所对应的宝贝用户id
     */
    public static final String COLUMNS_CHILD_ID = "child_id";

    /**
     * 家庭所对应的宝贝名称
     */
    public static final String COLUMNS_CHILD_NAME = "child_name";
    /**
     * 家庭管理员的用户id
     */
    public static final String COLUMNS_MANAGER_ID = "manager_id";


    /**
     * 最近聊天列表信息表
     */
    public static final String TABLE_RECENT_CHAT_LIST = "table_recent_chat_list";
    /**
     * 消息id(这里有可能是个人用户id、群组id、家庭id、系统消息id，系统消息id目前统一值为：-1)
     */
    public static final String USER_ID = "user_id";
    /**
     * 用户名称
     */
    public static final String USER_NAME = "user_name";
    /**
     * 用户头像地址
     */
    public static final String USER_ICON = "user_icon";
    /**
     * 聊天对象的类型
     */
    public static final String USER_TYPE = "user_type";
    /**
     * 最新消息类型
     */
    public static final String NEWEST_MESSAGE_TYPE = "newest_message_type";
    /**
     * 最新消息内容
     */
    public static final String NEWEST_MESSAGE_CONTENT = "newest_message_content";
    /**
     * 最新消息的时间
     */
    public static final String NEWEST_MESSAGE_TIME = "newest_message_time";
    /**
     * 未读消息数量
     */
    public static final String UNREAD_MESSAGE_NUM = "unread_message_num";
    /**
     * 插入或者最后一次更新到数据库的时间
     */
    public static final String NEWEST_INSERT_OR_UPDATE_TIME = "newest_insert_or_update_time";
    /**
     * 最新消息的类型
     * 0=接收的消息类型
     * 1=发送的消息类型
     */
    public static final String NEWEST_MESSAGE_IS_RECEVIE_TYPE = "newest_message_is_receive_type";
    /**
     * 最新消息的类型
     * 0=接收的消息类型
     * 1=发送的消息类型
     */
    public static final String ADD_FRIEND_STATE = "add_friend_state";
//    /**
//     * 标识是否是需要显示在总的最新一条聊天对象数据
//     * (每次接收到一条消息会更新这个标识为true的数据)
//     * 0=最近任务列表中
//     * 1=总的消息最新的一条聊天对象数据
//     */
//    public static final String IS_TOTAL_NEWEST_MESSAGE = "is_total_newest_message";


}
