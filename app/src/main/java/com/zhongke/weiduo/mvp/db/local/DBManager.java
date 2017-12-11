package com.zhongke.weiduo.mvp.db.local;

import android.util.Log;

import com.zhongke.weiduo.app.common.AppConst;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.library.sqlbrite.PhotoDb;
import com.zhongke.weiduo.library.sqlbrite.SQLBriteProvider;
import com.zhongke.weiduo.mvp.model.entity.PhotoOrVideo;
import com.zhongke.weiduo.mvp.model.entity.Session;
import com.zhongke.weiduo.mvp.ui.activity.SessionActivity2;
import com.zk.Json;
import com.zk.android.sqlite.ZkLocalMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Karma on 2017/7/11.
 * 类描述：db管理类
 */

public class DBManager {
    private static final String TAG = "DBManager";
    private static DBManager mInstance;

    private DBManager() {
    }

    public static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {//保证异步处理安全操作
                    mInstance = new DBManager();
            }
        }
        return mInstance;
    }
    public Observable<List<PhotoOrVideo>> queryPhoto() {
        return SQLBriteProvider.getInstance(UIUtils.getContext()).getBriteDatabase()
                .createQuery(PhotoDb.PhotoTable.TABLE_NAME, "SELECT * FROM " + PhotoDb.PhotoTable.TABLE_NAME)
                .mapToList(PhotoDb.PhotoTable.PHOTO_VIDEO);
    }

    public long addPhoto(PhotoOrVideo photoOrVideo) {
        return SQLBriteProvider.getInstance(UIUtils.getContext()).getBriteDatabase()
                .insert(PhotoDb.PhotoTable.TABLE_NAME, PhotoDb.PhotoTable.toContentValues(photoOrVideo));
    }

    public Observable<List<Session>> querySession(String objectId, int objectType, int pageIndex, int pageSize) {
        Log.i(TAG,"进行查询动作的判断  querySession" +objectType );
        if (objectType==SessionActivity2.SESSION_TYPE_PRIVATE){//单聊
            return queryOnChatSession(IMClient.USER_ID,objectId,pageIndex,pageSize);
        }else if (objectType==SessionActivity2.SESSION_TYPE_GROUP){//群聊
            return queryGroupSession(objectId,pageIndex,pageSize);
        }else{
            return null;
        }
    }
    /**
     * 群聊
     * @param groupId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    private Observable<List<Session>> queryGroupSession(final String groupId, final int pageIndex, final int pageSize) {
        Log.i(TAG," 群聊信息查询"+groupId+" "+pageIndex+" "+pageSize);
        Observable<List<Session>>  observable=   Observable.create(new Observable.OnSubscribe<List<Session>>() {
            @Override
            public void call(Subscriber<? super List<Session>> subscriber) {

                List<ZkLocalMessage> queryResult = IMClient.getInstance().queryMessageByGroup(groupId, pageIndex, pageSize);
                List<Session> list=conversionObject(queryResult);
                Collections.reverse(list);
                Log.i(TAG," 群聊信息查询，创建被观察者"+list.size());
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        });
        return observable;
    }
    /**
     * 单聊
     * @param sendUserId
     * @param recvUserId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    private Observable<List<Session>> queryOnChatSession(final String sendUserId, final String recvUserId,final int pageIndex,final int pageSize) {
        Log.i(TAG," 单聊信息查询"+sendUserId+" "+recvUserId+" "+pageIndex+" "+pageSize);
        Observable<List<Session>>  observable=   Observable.create(new Observable.OnSubscribe<List<Session>>() {
            @Override
            public void call(Subscriber<? super List<Session>> subscriber) {

                List<ZkLocalMessage> queryResult = IMClient.getInstance().queryMessageByReceiver(sendUserId, recvUserId, pageIndex, pageSize);
                List<Session> list=conversionObject(queryResult);
                Collections.reverse(list);
                Log.i(TAG," 单聊信息查询，创建被观察者"+list.size());
                subscriber.onNext(list);
                subscriber.onCompleted();
            }});
        return  observable;
    }
    /**
     * 转换对象
     * @param queryResult
     * @return
     */
    private List<Session> conversionObject(List<ZkLocalMessage> queryResult) {
        List<Session> list = new ArrayList<>();
        for (ZkLocalMessage zkLocalMessage:queryResult){
           Session session= Json.toBean(zkLocalMessage.chatContent,Session.class);
            session.setSendStatus(zkLocalMessage.getRetCode());
            session.setSendOrReceive(zkLocalMessage.getSendUserId().equals(IMClient.USER_ID)? AppConst.SESSION_SEND:AppConst.SESSION_RECEIVE);
            list.add(session);
        }
        return list;
    }

    public Observable<List<Session>> querySessionByMsgId(String msgId) {
        return SQLBriteProvider.getInstance(UIUtils.getContext()).getBriteDatabase()
                .createQuery(SessionDb.SessionTable.TABLE_NAME, "SELECT * FROM " + SessionDb.SessionTable.TABLE_NAME
                                + " WHERE "
                                + SessionDb.SessionTable.MSG_ID
                                + " = ?"
                        , msgId)
                .mapToList(SessionDb.SessionTable.SESSION_MAPPER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public long addSession(Session session) {
        return SQLBriteProvider.getInstance(UIUtils.getContext()).getBriteDatabase()
                .insert(SessionDb.SessionTable.TABLE_NAME, SessionDb.SessionTable.toContentValues(session));
    }

    public int deleteSessionByMsgId(final String MsgId) {
        return SQLBriteProvider.getInstance(UIUtils.getContext()).getBriteDatabase()
                .delete(SessionDb.SessionTable.TABLE_NAME, SessionDb.SessionTable.ID + "=?", MsgId);
    }
}
