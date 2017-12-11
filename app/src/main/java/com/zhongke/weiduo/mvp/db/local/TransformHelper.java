package com.zhongke.weiduo.mvp.db.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;
import com.zhongke.weiduo.mvp.model.entity.LoginSuccessBean;

import static com.zhongke.weiduo.mvp.db.local.Data.COLUMNS_BIRTHEDAY;
import static com.zhongke.weiduo.mvp.db.local.Data.COLUMNS_MAJORLIST;
import static com.zhongke.weiduo.mvp.db.local.Data.COLUMNS_USER_PHONE;
import static com.zhongke.weiduo.mvp.db.local.Data.COLUMNS_info;
import static com.zhongke.weiduo.mvp.db.local.Data.COLUMNS_userName;

/**
 * 实体类转换ContentValues
 * Created by llj on 2017/7/12.
 */

public class TransformHelper {

    public static ContentValues toContentValues(String phone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNS_USER_PHONE, phone);
        return contentValues;
    }

    public static ContentValues toContentValues(LoginResult loginResult) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNS_BIRTHEDAY, loginResult.getSysUser().getBirthday());
        contentValues.put(Data.COLUMNS_NICK_NAME, loginResult.getSysUser().getNickName());
        contentValues.put(Data.COLUMNS_HeadImageUrl, loginResult.getSysUser().getHeadImageUrl());
        contentValues.put(COLUMNS_USER_PHONE, loginResult.getSysUser().getUserPhone());
        contentValues.put(Data.COLUMNS_SEX, loginResult.getSysUser().getSex());
        contentValues.put(COLUMNS_MAJORLIST, loginResult.getSysUser().getMajorList());
        contentValues.put(Data.COLUMNS_DEVICE_CODE, loginResult.getSysUser().getDeviceCode());
        contentValues.put(Data.COLUMNS_registerType, loginResult.getSysUser().getRegisterType());
        contentValues.put(COLUMNS_userName, loginResult.getSysUser().getUserName());
        contentValues.put(Data.COLUMNS_orgId, loginResult.getSysUser().getOrgId());
        contentValues.put(Data.COLUMNS_tagList, loginResult.getSysUser().getTagList());
        contentValues.put(Data.COLUMNS_school, loginResult.getSysUser().getSchool());
        contentValues.put(Data.COLUMNS_phoneCode, loginResult.getSysUser().getPhoneCode());
        contentValues.put(Data.COLUMNS_id, loginResult.getSysUser().getId());
        contentValues.put(Data.COLUMNS_registerCode, loginResult.getSysUser().getRegisterCode());
        contentValues.put(COLUMNS_info, loginResult.getSysUser().getInfo());
        contentValues.put(Data.COLUMNS_USER_TOKEN, loginResult.getToken());
        return contentValues;
    }

    public static LoginResult toContentLogResultBean(Cursor cursor, LoginResult loginResult) {
        LoginResult.SysUserBean sysUserBean = new LoginResult.SysUserBean();
        sysUserBean.setBirthday(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_BIRTHEDAY)));
        sysUserBean.setNickName(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_NICK_NAME)));
        sysUserBean.setHeadImageUrl(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_HeadImageUrl)));
        sysUserBean.setUserPhone(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_USER_PHONE)));
        sysUserBean.setSex(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_SEX)));
        sysUserBean.setMajorList(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_MAJORLIST)));
        sysUserBean.setDeviceCode(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_DEVICE_CODE)));
        sysUserBean.setRegisterType(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_registerType)));
        sysUserBean.setUserName(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_userName)));
        sysUserBean.setOrgId(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_orgId)));
        sysUserBean.setTagList(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_tagList)));
        sysUserBean.setSchool(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_school)));
        sysUserBean.setPhoneCode(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_phoneCode)));
        sysUserBean.setId(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_id)));
        sysUserBean.setRegisterCode(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_registerCode)));
        sysUserBean.setInfo(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_info)));
        loginResult.setSysUser(sysUserBean);
        loginResult.setToken(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_USER_TOKEN)));
        return loginResult;
    }


    /**
     * 将LoginSuccessBean转换为ContentValues对象
     *
     * @param bean
     * @return
     */
    public static ContentValues toContentValuesByLoginSuccessBean(LoginSuccessBean bean) {
        if (bean == null) {
            return null;
        }
        ContentValues values = new ContentValues();
        values.put(Data.COLUMNS_UPLOAD_VIDEO_URL, bean.getUploadVideoUrl());
        values.put(Data.COLUMNS_UPLOAD_PIC_URL, bean.getUploadPicUrl());
        values.put(Data.COLUMNS_HeadImageUrl, bean.getIcon());
        values.put(Data.COLUMNS_NICK_NAME, bean.getNickName());
        values.put(Data.COLUMNS_USER_ID, bean.getUserId());
        values.put(COLUMNS_USER_PHONE, bean.getPhone());
        values.put(Data.COLUMNS_USER_TOKEN, bean.getUserToken());
        values.put(Data.COLUMNS_HAVE_FAMILY, bean.getHaveFamily());
        values.put(Data.COLUMNS_COINS, bean.getCoins());
        values.put(Data.COLUMNS_INTEGRAL, bean.getIntegral());

        if (bean.getFamilyList() != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < bean.getFamilyList().size(); i++) {
                builder.append(bean.getFamilyList().get(i).getId());
            }
            values.put(Data.COLUMNS_FAMILY_IDS, builder.toString());
        }
        return values;
    }


    /**
     * 将ContentValues对象转换为LoginSuccessBean对象
     *
     * @return
     */
    public static LoginSuccessBean getLoginSuccessBean(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        LoginSuccessBean loginSuccessBean = new LoginSuccessBean();
        loginSuccessBean.setUploadVideoUrl(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_UPLOAD_VIDEO_URL)));
        loginSuccessBean.setUploadPicUrl(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_UPLOAD_PIC_URL)));
        loginSuccessBean.setIcon(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_HeadImageUrl)));
        loginSuccessBean.setNickName(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_NICK_NAME)));
        loginSuccessBean.setUserId(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_USER_ID)));
        loginSuccessBean.setPhone(cursor.getString(cursor.getColumnIndex(COLUMNS_USER_PHONE)));
        loginSuccessBean.setUserToken(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_USER_TOKEN)));
        loginSuccessBean.setHaveFamily(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_HAVE_FAMILY)));
        loginSuccessBean.setCoins(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_COINS)));
        loginSuccessBean.setIntegral(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_INTEGRAL)));
//        loginSuccessBean.setFamilyList();
        return loginSuccessBean;
    }


    /**
     * 通过游标获取单个FamilyListBean对象
     *
     * @param cursor
     * @return
     */
    public static LoginSuccessBean.FamilyListBean getFamilyListBean(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        LoginSuccessBean.FamilyListBean familyListBean = new LoginSuccessBean.FamilyListBean();
        familyListBean.setName(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_FAMILY_NAME)));
        familyListBean.setId(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_FAMILY_ID)));
        familyListBean.setRoleName(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_ROLE_NAME)));
        familyListBean.setDeviceCode(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_DEVICE_CODE)));
        familyListBean.setChildName(cursor.getString(cursor.getColumnIndex(Data.COLUMNS_CHILD_NAME)));
        familyListBean.setChildId(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_CHILD_ID)));
        familyListBean.setManagerId(cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_MANAGER_ID)));
        return familyListBean;
    }


    /**
     * 通过Cursor对象获取ChatListBean对象
     *
     * @param cursor
     * @return
     */
    public static ChatListBean getChatListBean(Cursor cursor) {
        if (cursor == null) return null;
        ChatListBean chatListBean = new ChatListBean();
        chatListBean.id = cursor.getString(cursor.getColumnIndex(Data.USER_ID));
        chatListBean.name = cursor.getString(cursor.getColumnIndex(Data.USER_NAME));
        chatListBean.icon = cursor.getString(cursor.getColumnIndex(Data.USER_ICON));
        chatListBean.type = cursor.getInt(cursor.getColumnIndex(Data.USER_TYPE));
        chatListBean.unreadMessageNum = cursor.getInt(cursor.getColumnIndex(Data.UNREAD_MESSAGE_NUM));
        chatListBean.newestMsg = cursor.getString(cursor.getColumnIndex(Data.NEWEST_MESSAGE_CONTENT));
        chatListBean.newestMsgType = cursor.getInt(cursor.getColumnIndex(Data.NEWEST_MESSAGE_TYPE));
        chatListBean.newestMessageTime = cursor.getLong(cursor.getColumnIndex(Data.NEWEST_MESSAGE_TIME));
        chatListBean.newestUpdateTime = cursor.getLong(cursor.getColumnIndex(Data.NEWEST_INSERT_OR_UPDATE_TIME));
        chatListBean.newestMessageIsReceiveType = cursor.getInt(cursor.getColumnIndex(Data.NEWEST_MESSAGE_IS_RECEVIE_TYPE)) == 0;
        chatListBean.addState = cursor.getInt(cursor.getColumnIndex(Data.ADD_FRIEND_STATE));
        return chatListBean;
    }

    /**
     * 把ChatListBean对象转换成ContentValues对象
     *
     * @param listBean
     * @return
     */
    public static ContentValues getChatListBeanContentValues(ChatListBean listBean) {
        if (listBean == null) return null;
        ContentValues values = new ContentValues();
        values.put(Data.USER_ID, listBean.id);
        values.put(Data.USER_NAME, listBean.name);
        values.put(Data.USER_ICON, listBean.icon);
        values.put(Data.USER_TYPE, listBean.type);
        values.put(Data.UNREAD_MESSAGE_NUM, listBean.unreadMessageNum);
        values.put(Data.NEWEST_MESSAGE_CONTENT, listBean.newestMsg);
        values.put(Data.NEWEST_MESSAGE_TYPE, listBean.newestMsgType);
        values.put(Data.NEWEST_MESSAGE_TIME, listBean.newestMessageTime);
        values.put(Data.NEWEST_INSERT_OR_UPDATE_TIME, listBean.newestUpdateTime);
        values.put(Data.NEWEST_MESSAGE_IS_RECEVIE_TYPE, listBean.newestMessageIsReceiveType ? 0 : 1);
        values.put(Data.ADD_FRIEND_STATE, listBean.addState);
//        // 总的消息接收，最新消息的标识
//        values.put(Data.IS_TOTAL_NEWEST_MESSAGE, isTotalUpdate ? 1 : 0);
        return values;
    }


    /**
     * 把ContactsListBean对象转换成ContentValues对象
     *
     * @param listBean
     * @return
     */
    public static ContentValues getContactsListBeanContentValues(ContactsListBean listBean) {
        if (listBean == null) return null;
        ContentValues values = new ContentValues();
        values.put(Data.COLUMNS_CONTACTS_ID, listBean.id);
        values.put(Data.COLUMNS_CONTACTS_TYPE, listBean.type);
        values.put(Data.COLUMNS_CONTACTS_NICK_NAME, listBean.nickName);
        values.put(Data.COLUMNS_CONTACTS_INFO, listBean.info);
        values.put(Data.COLUMNS_CONTACTS_HEAD_URL, listBean.headUrl);
        values.put(Data.COLUMNS_CONTACTS_CREATE_TIME, listBean.createTime);
        values.put(Data.COLUMNS_CONTACTS_COVER_URL, listBean.coverUrl);
        values.put(Data.COLUMNS_CONTACTS_CREATE_USER_ID, listBean.createUserId);
        return values;
    }

    /**
     * 通过Cursor对象获取ContactsListBean对象
     *
     * @param cursor
     * @return
     */
    public static ContactsListBean getContactsListBean(Cursor cursor) {
        if (cursor == null) return null;
        ContactsListBean contactsListBean = new ContactsListBean();
        contactsListBean.id = cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_CONTACTS_ID));
        contactsListBean.type = cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_CONTACTS_TYPE));
        contactsListBean.nickName = cursor.getString(cursor.getColumnIndex(Data.COLUMNS_CONTACTS_NICK_NAME));
        contactsListBean.info = cursor.getString(cursor.getColumnIndex(Data.COLUMNS_CONTACTS_INFO));
        contactsListBean.headUrl = cursor.getString(cursor.getColumnIndex(Data.COLUMNS_CONTACTS_HEAD_URL));
        contactsListBean.createTime = cursor.getString(cursor.getColumnIndex(Data.COLUMNS_CONTACTS_CREATE_TIME));
        contactsListBean.coverUrl = cursor.getString(cursor.getColumnIndex(Data.COLUMNS_CONTACTS_COVER_URL));
        contactsListBean.createUserId = cursor.getInt(cursor.getColumnIndex(Data.COLUMNS_CONTACTS_CREATE_USER_ID));
        return contactsListBean;
    }

}
