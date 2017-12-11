package com.zhongke.weiduo.mvp.db.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;

import java.util.ArrayList;
import java.util.List;

import static com.zhongke.weiduo.mvp.db.local.TransformHelper.getChatListBean;

/**
 * 最近联系人列表数据库管理类
 * Created by llj on 2017/10/18.
 */

public class RecentDBManager {

    private static final String TAG = RecentDBManager.class.getSimpleName();

    private static RecentDBManager instance;

    private DBHelper dbHelper;

    private RecentDBManager() {
    }

    public static RecentDBManager getInstance() {
        if (instance == null) {
            synchronized (RecentDBManager.class) {
                if (instance == null) {
                    instance = new RecentDBManager();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    /**
     * 查询出所有的最近聊天对象列表
     *
     * @return
     */
    public List<ChatListBean> queryAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<ChatListBean> list = new ArrayList<>();
        // 根据最后更新时间倒序查询出所有的记录
        Cursor cursor = db.query(Data.TABLE_RECENT_CHAT_LIST, null, null, null, null, null, Data.NEWEST_INSERT_OR_UPDATE_TIME + " DESC");
        while (cursor.moveToNext()) {
            list.add(getChatListBean(cursor));
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 查询具体一个聊天对象
     *
     * @param type
     * @param userId
     * @return
     */
    public ChatListBean query(int type, String userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                Data.TABLE_RECENT_CHAT_LIST
                , null
                , Data.USER_TYPE + "=? and " + Data.USER_ID + "=?"
                , new String[]{type + "", userId}
                , null, null, null);
        if (cursor == null) {
            db.close();
            return null;
        }
        ChatListBean listBean = null;
        if (cursor.moveToFirst()) {
            listBean = TransformHelper.getChatListBean(cursor);
        }
        cursor.close();
        db.close();
        return listBean;
    }

    /**
     * 查询具体一个聊天对象(总的消息最新的聊天对象信息)
     *
     * @return
     */
    public ChatListBean queryTotalNewest() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // 通过倒叙查询一条最新数据的更新时间
        Cursor cursor = db.query(
                Data.TABLE_RECENT_CHAT_LIST
                , null
                , null
                , null
                , null
                , null
                , Data.NEWEST_INSERT_OR_UPDATE_TIME + " DESC", "1");
        if (cursor == null) {
            db.close();
            return null;
        }
        ChatListBean listBean = null;
        if (cursor.moveToFirst()) {
            listBean = TransformHelper.getChatListBean(cursor);
        }
        cursor.close();
        db.close();
        return listBean;
    }


    /**
     * 插入一条新的数据
     *
     * @param listBean
     */
    public boolean insert(ChatListBean listBean) {
        if (listBean == null) return false;
        ChatListBean queryListBean = query(listBean.type, listBean.id);
        if (queryListBean != null) {
            LogUtil.i(TAG, "insert(),原来数据中已经有了此条数据，就直接更新");
            return update(listBean);
        }

        ContentValues values = TransformHelper.getChatListBeanContentValues(listBean);
        if (values == null) return false;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insert(Data.TABLE_RECENT_CHAT_LIST, null, values);
        db.close();
        return rowId != -1;
    }

    /**
     * 插入或者更新一个集合
     *
     * @param list
     * @param isQueryAll 是否需要在插入更新数据完成之后再查询出所有最近列表的数据
     */
    public List<ChatListBean> insertList(List<ChatListBean> list, boolean isQueryAll) {
        if (list == null || list.isEmpty()) return null;
        SQLiteDatabase writeDB = dbHelper.getWritableDatabase();
        List<ChatListBean> allList = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from ").append(Data.TABLE_RECENT_CHAT_LIST).append(" where ");

            int length = list.size();
            // 拼接字符串
            for (int i = 0; i < length; i++) {
                ChatListBean bean = list.get(i);
                sql.append(Data.USER_TYPE)
                        .append("=")
                        .append(bean.type)
                        .append(" and ")
                        .append(Data.USER_ID)
                        .append("=")
                        .append(bean.id);

                if (i != length - 1) {
                    // 不是最后一个
                    sql.append(" or ");
                }
            }

            writeDB.beginTransaction();
            Cursor cursor = writeDB.rawQuery(sql.toString(), null);
            // 删除查询到的数据
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    writeDB.delete(Data.TABLE_RECENT_CHAT_LIST, Data._ID + "=" + cursor.getInt(cursor.getColumnIndex(Data._ID)), null);
                } while (cursor.moveToNext());
                cursor.close();
            }

            // 插入新获取到的sdk
            for (int i = 0; i < length; i++) {
                ChatListBean bean = list.get(i);
                writeDB.insert(Data.TABLE_RECENT_CHAT_LIST, null, TransformHelper.getChatListBeanContentValues(bean));
            }

            // 需要查询出当前所有的最近聊天列表
            if (isQueryAll) {
                // 根据最后更新时间倒序查询出所有的记录
                Cursor queryCursor = writeDB.query(Data.TABLE_RECENT_CHAT_LIST, null, null, null, null, null, Data.NEWEST_INSERT_OR_UPDATE_TIME + " DESC");
                while (queryCursor.moveToNext()) {
                    allList.add(TransformHelper.getChatListBean(queryCursor));
                }
                queryCursor.close();
            }

            writeDB.setTransactionSuccessful();
            writeDB.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writeDB != null) {
                writeDB.close();
            }
        }
        return allList;
    }

    //删除一条ChatListBean数据
    public void deleteChatListBean(ChatListBean chatListBean) {
        if (chatListBean == null) {
            return;
        }
        SQLiteDatabase writeDB = dbHelper.getWritableDatabase();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from ").append(Data.TABLE_RECENT_CHAT_LIST).append(" where ");
            sql.append(Data.USER_TYPE)
                    .append("=")
                    .append(chatListBean.type)
                    .append(" and ")
                    .append(Data.USER_ID)
                    .append("=")
                    .append(chatListBean.id);

            writeDB.beginTransaction();
            Cursor cursor = writeDB.rawQuery(sql.toString(), null);
            // 删除查询到的数据
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    writeDB.delete(Data.TABLE_RECENT_CHAT_LIST, Data._ID + "=" + cursor.getInt(cursor.getColumnIndex(Data._ID)), null);
                } while (cursor.moveToNext());
                cursor.close();
            }

            writeDB.setTransactionSuccessful();
            writeDB.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writeDB != null) {
                writeDB.close();
            }
        }
    }

    //删除一条数据
    public void delete(int type, String id) {
        SQLiteDatabase writeDB = dbHelper.getWritableDatabase();
        try {
            writeDB.delete(Data.TABLE_RECENT_CHAT_LIST,Data.USER_TYPE+"=? and "+Data.USER_ID+"=?",new String[]{type+"",id});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writeDB != null) {
                writeDB.close();
            }
        }
    }

    /**
     * 删除所有的最近聊天记录
     */
    public void deleteAll(){
        SQLiteDatabase writeDB = dbHelper.getWritableDatabase();
        try {
            writeDB.delete(Data.TABLE_RECENT_CHAT_LIST,null,null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writeDB != null) {
                writeDB.close();
            }
        }
    }

    /**
     * 更新一条数据
     *
     * @param listBean
     * @return
     */
    public boolean update(ChatListBean listBean) {
        if (listBean == null) return false;
        ContentValues values = TransformHelper.getChatListBeanContentValues(listBean);
        if (values == null) return false;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.update(
                Data.TABLE_RECENT_CHAT_LIST, values
                , Data.USER_ID + "=? and " + Data.USER_TYPE + " =?"
                , new String[]{listBean.id, listBean.type + ""});
        db.close();

        if (count > 0) return true;

        // 沒有更新成功
        ChatListBean queryListBean = query(listBean.type, listBean.id);
        if (queryListBean == null) {
            // 数据库中根本就没有此条记录
            // 直接插入一条新的数据
            return insert(listBean);
        }
        return false;
    }

}
