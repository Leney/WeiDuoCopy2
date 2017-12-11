package com.zhongke.weiduo.mvp.db.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人DB管理类
 * Created by llj on 2017/11/15.
 */

public class ContactsDBManager {
    private static final String TAG = ContactsDBManager.class.getSimpleName();

    private static ContactsDBManager instance;

    private ContactsDBManager() {
    }

    public static ContactsDBManager getInstance() {
        if (instance == null) {
            synchronized (ContactsDBManager.class) {
                if (instance == null) {
                    instance = new ContactsDBManager();
                }
            }
        }
        return instance;
    }


    /**
     * 插入一条新的数据
     *
     * @param bean
     */
    public boolean insert(ContactsListBean bean) {
        if (bean == null) return false;
        SQLiteDatabase db = DBHelper.getInstance(ZkApplication.getContext()).getWritableDatabase();
        long rowId = 0;
        try {
            ContentValues values = TransformHelper.getContactsListBeanContentValues(bean);
            rowId = db.insert(Data.TABLE_CONTACTS, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return rowId != -1;
    }

    /**
     * 更新一条数据
     *
     * @param bean
     * @return
     */
    public boolean update(ContactsListBean bean) {
        if (bean == null) return false;
        SQLiteDatabase db = DBHelper.getInstance(ZkApplication.getContext()).getWritableDatabase();
        int count = 0;
        try {
            ContentValues values = TransformHelper.getContactsListBeanContentValues(bean);
            count = db.update(
                    Data.TABLE_CONTACTS, values
                    , Data.COLUMNS_CONTACTS_ID + "=? and " + Data.COLUMNS_CONTACTS_TYPE + " =?"
                    , new String[]{bean.id + "", bean.type + ""});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return count > 0;
    }

    /**
     * 批量插入数据
     *
     * @param list
     */
    public boolean insertAll(List<ContactsListBean> list) {
        if (list == null || list.isEmpty()) return false;
        SQLiteDatabase db = DBHelper.getInstance(ZkApplication.getContext()).getWritableDatabase();
        try {
            db.beginTransaction();
            int length = list.size();
            for (int i = 0; i < length; i++) {
                ContentValues values = TransformHelper.getContactsListBeanContentValues(list.get(i));
                db.insert(Data.TABLE_CONTACTS, null, values);
            }
            Log.i("llj", "插入所有数据到数据库成功！！！");
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return true;
    }

    /**
     * 删除所有数据
     */
    public void deleteAll() {
        SQLiteDatabase db = DBHelper.getInstance(ZkApplication.getContext()).getWritableDatabase();
        try {
            db.delete(Data.TABLE_CONTACTS, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * 删除一条联系人数据
     *
     * @param type 联系人类型
     * @param id   联系人id
     * @return
     */
    public boolean delete(int type, int id) {
        SQLiteDatabase db = DBHelper.getInstance(ZkApplication.getContext()).getWritableDatabase();
        int count = 0;
        try {
            count = db.delete(Data.TABLE_CONTACTS, Data.COLUMNS_CONTACTS_TYPE + "=? and " + Data.COLUMNS_CONTACTS_ID + "=?", new String[]{type + "", id + ""});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return count > 0;
    }

    /**
     * 根据类型查询出联系人列表
     *
     * @return
     */
    public List<ContactsListBean> queryContactListByType(int type) {
        SQLiteDatabase db = DBHelper.getInstance(ZkApplication.getContext()).getReadableDatabase();
        List<ContactsListBean> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(
                    Data.TABLE_CONTACTS
                    , null
                    , Data.COLUMNS_CONTACTS_TYPE + "=?"
                    , new String[]{type + ""}
                    , null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    list.add(TransformHelper.getContactsListBean(cursor));
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return list;
    }

    /**
     * 根据类型和联系人id查询出联系人
     *
     * @return
     */
    public ContactsListBean queryContactBeanByType(int type, int id) {
        SQLiteDatabase db = DBHelper.getInstance(ZkApplication.getContext()).getReadableDatabase();
        ContactsListBean bean = null;
        Cursor cursor = null;
        try {
            cursor = db.query(
                    Data.TABLE_CONTACTS
                    , null
                    , Data.COLUMNS_CONTACTS_TYPE + "=? and " + Data.COLUMNS_CONTACTS_ID + "=?"
                    , new String[]{type + "", id + ""}
                    , null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                bean = TransformHelper.getContactsListBean(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return bean;
    }


    /**
     * 通过id查找出对应的群组类型的联系人对象(我的家庭、友好家庭、群组)
     *
     * @return
     */
    public ContactsListBean queryGroupContactsBeanByIdFromAll(int id) {
        SQLiteDatabase db = DBHelper.getInstance(ZkApplication.getContext()).getReadableDatabase();
        ContactsListBean bean = null;
        Cursor cursor = null;

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select * from ")
                .append(Data.TABLE_CONTACTS)
                .append(" where (")
                .append(Data.COLUMNS_CONTACTS_TYPE)
                .append("=")
                .append(ContactsListBean.TYPE_MY_FAMILY)
                .append(" or ")
                .append(Data.COLUMNS_CONTACTS_TYPE)
                .append("=")
                .append(ContactsListBean.TYPE_FRIEND_FAMILY)
                .append(" or ")
                .append(Data.COLUMNS_CONTACTS_TYPE)
                .append("=")
                .append(ContactsListBean.TYPE_GROUP)
                .append(") and ")
                .append(Data.COLUMNS_CONTACTS_ID)
                .append("=")
                .append(id)
                .append(";");
        try {
            cursor = db.rawQuery(sqlBuilder.toString(), null);
            if (cursor != null && cursor.moveToFirst()) {
                bean = TransformHelper.getContactsListBean(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return bean;
    }
}
