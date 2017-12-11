package com.zhongke.weiduo.mvp.db.local;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.im.IMClient;
import com.zk.ZkRetCode;
import com.zk.android.sqlite.ZkLocalMessage;
import com.zk.android.sqlite.ZkLocalMessageDBHelper;
import com.zk.net.message.ChatResponseMessage;
import com.zk.net.message.ZkMessageType;

import static com.zk.android.sqlite.ZkLocalMessageManager.StringToList;

/**
 * 聊天消息的数据库操作类(此类是为离线消息保存到数据服务的)
 * Created by llj on 2017/12/6.
 */

public class ChatDBManager {

    private ZkLocalMessageDBHelper helper = null;
    private SQLiteDatabase database;

    private ChatDBManager() {
        helper = new ZkLocalMessageDBHelper(ZkApplication.getContext(), IMClient.USER_ID + "_MsgDB.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    private static ChatDBManager instance = null;

    public static ChatDBManager getInstance() {
        if (instance == null) {
            synchronized (ChatDBManager.class) {
                if (instance == null) {
                    instance = new ChatDBManager();
                }
            }
        }
        return instance;
    }

    /**
     * 保存接收到的消息i
     */
    public boolean saveChatResponseMessage(ChatResponseMessage message) {
        boolean flag = false;
        database = helper.getWritableDatabase();

        //开启事务
        database.beginTransaction();
        try {
            if (database.isOpen()) {
                //接收到消息保存数据库
                database.execSQL("insert into tb_message(msgId,messageType,sendUserId,toUserList,chatContent,deviceType,groupId,time,retCode) values(?,?,?,?,?,?,?,?,?)",
                        new Object[]{message.getMsgId(), ZkMessageType.COMMAND_CHAT_RESP, message.getSendUserId(), IMClient.USER_ID + ",",
                                message.getChatContent(), message.getDeviceType(), message.getGroupId(), message.getTime(), ZkRetCode.RECV_MSG_SECCUSS});

                //标记事务成功，如果没执行这句话，则自动回滚
                database.setTransactionSuccessful();
            }
            flag = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            //关闭事务
            database.endTransaction();
            database.close();
        }
        return flag;
    }


    /**
     * 根据id查询一个ZkLocalMessage
     */
    public ZkLocalMessage queryByMsgId(String msgId) {
        ZkLocalMessage message = null;
        database = helper.getReadableDatabase();
        try {
            if (database.isOpen()) {
                Cursor cursor = database.rawQuery("select msgId,messageType,sendUserId,toUserList,chatContent,deviceType,groupId,time,retCode from tb_message where msgId = ?", new String[]{msgId});
                if (cursor != null && cursor.getCount() > 0) {
                    if (cursor.moveToFirst()) {
                        message = getMessage(cursor);
                    }
                }
                cursor.close();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
        return message;
    }

    @NonNull
    private ZkLocalMessage getMessage(Cursor cursor) {
        ZkLocalMessage message = new ZkLocalMessage();
        message.setMsgId(cursor.getString(0));
        message.setMessageType(cursor.getInt(1));
        message.setSendUserId(cursor.getString(2));
        message.setToUserList(StringToList(cursor.getString(3)));
        message.setChatContent(cursor.getString(4));
        message.setDeviceType(cursor.getString(5));
        message.setGroupId(cursor.getString(6));
        message.setTime(cursor.getLong(7));
        message.setRetCode(cursor.getInt(8));

        return message;
    }

}
