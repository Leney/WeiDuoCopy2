package com.zhongke.weiduo.mvp.db.local;

/**
 * 数据库操作sql语句类
 * Created by llj on 2017/7/19.
 */

public class SqlHelper {
    /**
     * 创建用户信息表的sql语句
     */
    public static final String CREATE_TABLE_USER_INFO = "create table if not exists "
            + Data.TABLE_USER_INFO
            + " ("
            + Data._ID
            + " integer primary key autoincrement, "
            + Data.COLUMNS_BIRTHEDAY
            + " text, "
            + Data.COLUMNS_NICK_NAME
            + " text, "
            + Data.COLUMNS_HeadImageUrl
            + " text, "
            + Data.COLUMNS_USER_PHONE
            + " integer, "
            + Data.COLUMNS_SEX
            + " integer, "
            + Data.COLUMNS_MAJORLIST
            + " text,"
            + Data.COLUMNS_DEVICE_CODE
            + " text,"
            + Data.COLUMNS_registerType
            + " integer,"
            + Data.COLUMNS_userName
            + " text, "
            + Data.COLUMNS_orgId
            + " text,"
            + Data.COLUMNS_registerCode
            + " text,"
            + Data.COLUMNS_tagList
            + " text,"
            + Data.COLUMNS_school
            + " text,"
            + Data.COLUMNS_phoneCode
            + " text,"
            + Data.COLUMNS_id
            + " integer,"
            + Data.COLUMNS_info
            + " text,"
            + Data.COLUMNS_USER_TOKEN
            + " text);";

    /**
     * 创建用户所拥有的家庭信息表的sql语句
     */
    public static final String CREATE_TABLE_USER_FAMILY_INFO = "create table if not exists "
            + Data.TABLE_USER_FAMILY_INFO
            + " ("
            + Data._ID
            + " integer primary key autoincrement, "
            + Data.COLUMNS_FAMILY_ID
            + " integer, "
            + Data.COLUMNS_FAMILY_NAME
            + " text, "
            + Data.COLUMNS_ROLE_NAME
            + " text, "
            + Data.COLUMNS_DEVICE_CODE
            + " text, "
            + Data.COLUMNS_CHILD_ID
            + " integer, "
            + Data.COLUMNS_CHILD_NAME
            + " text,"
            + Data.COLUMNS_MANAGER_ID
            + " integer);";

    /**
     * 创建最近聊天列表的数据库表sql语句
     */
    public static final String CREATE_TABLE_RECENT_CHAT_LIST = "create table if not exists "
            + Data.TABLE_RECENT_CHAT_LIST
            + " ("
            + Data._ID
            + " integer primary key autoincrement, "
            + Data.USER_ID
            + " text, "
            + Data.USER_NAME
            + " text, "
            + Data.USER_ICON
            + " text, "
            + Data.USER_TYPE
            + " integer, "
            + Data.NEWEST_MESSAGE_TYPE
            + " integer, "
            + Data.ADD_FRIEND_STATE
            + " integer, "
            + Data.NEWEST_MESSAGE_CONTENT
            + " text, "
            + Data.NEWEST_MESSAGE_TIME
            + " long,"
            + Data.NEWEST_INSERT_OR_UPDATE_TIME
            + " long,"
//            + Data.IS_TOTAL_NEWEST_MESSAGE
//            + " bit,"
            + Data.NEWEST_MESSAGE_IS_RECEVIE_TYPE
            + " bit,"
            + Data.UNREAD_MESSAGE_NUM
            + " integer);";

    /**
     * 创建最联系人列表的数据库表sql语句
     */
    public static final String CREATE_TABLE_CONTACTS = "create table if not exists "
            + Data.TABLE_CONTACTS
            + " ("
            + Data._ID
            + " integer primary key autoincrement, "
            + Data.COLUMNS_CONTACTS_TYPE
            + " integer, "
            + Data.COLUMNS_CONTACTS_ID
            + " integer, "
            + Data.COLUMNS_CONTACTS_CREATE_TIME
            + " text, "
            + Data.COLUMNS_CONTACTS_HEAD_URL
            + " text, "
            + Data.COLUMNS_CONTACTS_COVER_URL
            + " text, "
            + Data.COLUMNS_CONTACTS_CREATE_USER_ID
            + " integer, "
            + Data.COLUMNS_CONTACTS_INFO
            + " text, "
            + Data.COLUMNS_CONTACTS_NICK_NAME
            + " text);";


    public static String createQueryTokenSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" select ");
        stringBuilder.append(Data.COLUMNS_USER_TOKEN);
        stringBuilder.append(" from ");
        stringBuilder.append(Data.TABLE_USER_INFO);
        return stringBuilder.toString();
    }

    public static String createQueryAccountSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" select * ");
        stringBuilder.append(" from ");
        stringBuilder.append(Data.TABLE_USER_INFO);
        return stringBuilder.toString();
    }
}
