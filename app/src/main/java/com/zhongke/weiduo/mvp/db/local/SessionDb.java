package com.zhongke.weiduo.mvp.db.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.zhongke.weiduo.mvp.model.entity.Session;

import rx.functions.Func1;

/**
 * Created by Karma on 2017/7/11.
 * 类描述：
 */

public class SessionDb {
    public SessionDb() {
    }

    public static abstract class SessionTable {
        // 表名
        public static final String TABLE_NAME = "weiduo_session";

        // 表字段
        public static final String ID = "_id";
        public static final String MSG_ID = "msg_id";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PROFIT = "head";
        public static final String COLUMN_CHAT_TYPE = "chat_type";
        public static final String COLUMN_SEND_STATUS = "send_status";
        public static final String COLUMN_SEND_TIME = "send_time";
        public static final String COLUMN_RECEIVE_TIME = "receive_time";
        public static final String COLUMN_SESSION_CONTENT = "session_content";
        public static final String COLUMN_SESSION_SORR = "send_or_receive";
        public static final String COLUMN_SEESION_DURACTION = "duraction";

        // 建表语句
        public static final String CREATE =
                "CREATE TABLE "
                        + TABLE_NAME
                        + " ("
                        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + MSG_ID + " TEXT,"
                        + COLUMN_USER_ID + " INT,"
                        + COLUMN_NAME + " TEXT,"
                        + COLUMN_PROFIT + " TEXT,"
                        + COLUMN_CHAT_TYPE + " INT,"
                        + COLUMN_SEND_STATUS + " INT,"
                        + COLUMN_SEND_TIME + " TIME,"
                        + COLUMN_RECEIVE_TIME + " TIME,"
                        + COLUMN_SESSION_CONTENT + " TEXT,"
                        + COLUMN_SESSION_SORR + " INT,"
                        + COLUMN_SEESION_DURACTION + " INT)";


        // 对象转字段,放入表中
        public static ContentValues toContentValues(Session session) {
            ContentValues values = new ContentValues();
            values.put(MSG_ID, session.getMsgId());
            values.put(COLUMN_USER_ID, session.getMemberInfo().getId());
            values.put(COLUMN_NAME, session.getMemberInfo().getName());
            values.put(COLUMN_PROFIT, session.getMemberInfo().getIcon());
            values.put(COLUMN_CHAT_TYPE, session.getChatType());
            values.put(COLUMN_SEND_STATUS, session.getSendStatus());
            values.put(COLUMN_SEND_TIME, session.getSendTime() == 0L ? 0L : session.getSendTime());
            values.put(COLUMN_RECEIVE_TIME, session.getReceiveTime() == 0L ? 0L : session.getReceiveTime());
            values.put(COLUMN_SESSION_CONTENT, session.getContent());
            values.put(COLUMN_SESSION_SORR, session.getSendOrReceive());
            values.put(COLUMN_SEESION_DURACTION, session.getDuraction() == 0 ? 0 : session.getDuraction());
            return values;
        }

        // 响应式的查询,根据表中的row生成一个对象
        static Func1<Cursor, Session> SESSION_MAPPER = cursor -> {
            Session session = new Session();

            String msgId = cursor.getString(cursor.getColumnIndexOrThrow(MSG_ID));
            int userId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            String head = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROFIT));
            int chatType = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CHAT_TYPE));
            int sendStatus = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SEND_STATUS));
            long sendTime = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_SEND_TIME));
            long receiveTime = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_RECEIVE_TIME));
            String chatContent = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SESSION_CONTENT));
            int sendOrReceive = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SESSION_SORR));
            int duraction = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SEESION_DURACTION));

            session.setMsgId(msgId);
            session.getMemberInfo().setId(userId);
            session.getMemberInfo().setName(name);
            session.getMemberInfo().setIcon(head);
            session.setChatType(chatType);
            session.setSendStatus(sendStatus);
            session.setSendTime(sendTime);
            session.setReceiveTime(receiveTime);
            session.setContent(chatContent);
            session.setSendOrReceive(sendOrReceive);
            session.setDuraction(duraction);

            return session;
        };

    }
}
