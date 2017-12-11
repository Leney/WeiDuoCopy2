package com.zhongke.weiduo.mvp.control;


import android.content.Context;
import android.text.TextUtils;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.library.retrofit.RetrofitClient;
import com.zhongke.weiduo.mvp.db.local.ChatDBManager;
import com.zhongke.weiduo.mvp.db.local.ContactsDBManager;
import com.zhongke.weiduo.mvp.db.local.RecentDBManager;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.model.entity.OfflineMessage;
import com.zhongke.weiduo.mvp.model.entity.RequestAddFriendBean;
import com.zhongke.weiduo.mvp.model.entity.Session;
import com.zhongke.weiduo.util.TimeUtils;
import com.zk.Json;
import com.zk.NetFace;
import com.zk.ZkNetEvent;
import com.zk.ZkRetCode;
import com.zk.android.sqlite.ZkLocalMessage;
import com.zk.net.message.AckS2CMessage;
import com.zk.net.message.ChatResponseMessage;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 最近聊天信息列表管理类
 * Created by llj on 2017/8/17.
 */

public class RecentChatListManager {

    private static final String TAG = "RecentChatListManager";

//    /**
//     * 未读消息总数的TAG
//     */
//    private static final String TOTAL_MSG_NUM = "total_msg_num";

    private static RecentChatListManager instance;

//    /**
//     * 当前选择的position
//     */
//    private int curSelectPosition = -1;

    /**
     * 总共未读消息的数量
     */
    private int unreadMsgNum = 0;

    /**
     * 当前正在聊天的对象id
     */
    private String curChatSessionId = "";

    /**
     * 当前正在聊天的对象的类型
     */
    private int curChatSessionType = -1;

    /**
     * 未读消息总数量发生改变的监听对象
     */
    private List<onTotalMsgNumChangedListener> unreadMsgNumListeners;

    /**
     * 最近人列表有变化的监听对象
     */
    private List<onRecentChatListChangedListener> listChangedListeners;

//    /**
//     * 接收到了新的消息的监听对象
//     */
//    private onNewMessageReceiveListener newMessageReceiveListener;

    // Rxjava 线程管理类
    private CompositeSubscription compositeSubscription;

    private RecentChatListManager() {

    }

    /**
     * 当前保存的最近聊天列表集合
     */
    private List<ChatListBean> beanList = new ArrayList<>();

    public static RecentChatListManager getInstance() {

        if (instance == null) {
            synchronized (RecentChatListManager.class) {
                if (instance == null) {
                    instance = new RecentChatListManager();
                }
            }
        }
        return instance;
    }


    public void init(Context context) {
        // 初始化最近聊天列表数据库管理类
        this.unreadMsgNumListeners = new ArrayList<>();
        this.listChangedListeners = new ArrayList<>();
        this.compositeSubscription = new CompositeSubscription();
        RecentDBManager.getInstance().init(context);
        // 得到上一次退出时的聊天列表集合
        getLastRecentChatList();
    }


    /**
     * 获取缓存文件中的最近聊天列表数据
     */
    private void getLastRecentChatList() {
        // 查询数据库中的最近联系人列表消息
        Subscription subscription = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                beanList.clear();
                beanList.addAll(RecentDBManager.getInstance().queryAll());
                LogUtil.i("llj", "聊天记录条数beanList.size()-------->>>" + beanList.size());

                // 统计未读消息数量
                int size = beanList.size();
                for (int i = 0; i < size; i++) {
                    // 读取未读消息数量
                    ChatListBean chatListBean = beanList.get(i);
                    if (chatListBean.type == ChatListBean.CHAT_TYPE_ADD_FRIEND) {
                        if (chatListBean.addState == ChatListBean.NEW_ADD_FRIEND_STATE_UN_DO) {
                            unreadMsgNum++;
                        }
                        continue;
                    }
                    unreadMsgNum += chatListBean.unreadMessageNum;
                }
                subscriber.onNext(beanList);
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        callBackUnreadMsgNumChangeListener();
                    }
                });

        compositeSubscription.add(subscription);

        // 从网络获取好友请求列表
        getRequestAddFriendListFromNetwork();
    }

    /**
     * 从服务器获取好友添加列表
     */
    public void getRequestAddFriendListFromNetwork() {
        // 获取好友请求列表消息
        RetrofitClient.getInstance().getRequestAddFriendList(new ResponseResultListener<RequestAddFriendBean>() {
            @Override
            public void success(RequestAddFriendBean bean) {
                LogUtil.e("friend bean---" + bean.getRecords());
                doDataFromNetWork(bean);
            }

            @Override
            public void failure(CommonException e) {

            }
        });
    }


    // 处理从网络返回的数据
    private void doDataFromNetWork(RequestAddFriendBean bean) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                if (bean != null && bean.getRecords().isEmpty()) {
                    return;
                }
                int length = bean.getRecords().size();
                List<ChatListBean> list = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    list.add(rebuildChatListBeanByNewAddMessage(bean.getRecords().get(i)));
                }
                List<ChatListBean> queryList = RecentDBManager.getInstance().insertList(list, true);
                LogUtil.e("queryList---" + queryList);
                // LogUtil.e("list---"+list);
                if (queryList == null || queryList.isEmpty()) return;

                // 重新读取数据库总的数据
                beanList.clear();
                beanList.addAll(queryList);

                // 统计未读消息数量
                unreadMsgNum = 0;
                int size = beanList.size();
                for (int i = 0; i < size; i++) {
                    // 读取未读消息数量
                    ChatListBean chatListBean = beanList.get(i);
                    if (chatListBean.type == ChatListBean.CHAT_TYPE_ADD_FRIEND) {
                        if (chatListBean.addState == ChatListBean.NEW_ADD_FRIEND_STATE_UN_DO) {
                            unreadMsgNum++;
                        }
                        continue;
                    }
                    unreadMsgNum += chatListBean.unreadMessageNum;
                }
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        callBackUnreadMsgNumChangeListener();
                        callBackChatListChangeListener(-1);
                    }
                });
    }


    /**
     * 通过新的好友请求消息组合成最近聊天信息对象
     */
    private ChatListBean rebuildChatListBeanByNewAddMessage(RequestAddFriendBean.RecordsBean bean) {
        ChatListBean chatListBean = new ChatListBean();
        chatListBean.type = ChatListBean.CHAT_TYPE_ADD_FRIEND;
        chatListBean.addState = bean.getFriendState();
        chatListBean.icon = bean.getHeadImageUrl();
        String nickName = bean.getNickName();
        String userName = bean.getUserName();
        chatListBean.name = !TextUtils.isEmpty(nickName) ? nickName : !TextUtils.isEmpty(userName) ? userName : "";
        chatListBean.id = bean.getUserId() + "";
        chatListBean.newestMessageTime = TimeUtils.getLongTime(bean.getCreateTime());
        chatListBean.newestUpdateTime = chatListBean.newestMessageTime;
        // 是被动接收到的消息类型
        chatListBean.newestMessageIsReceiveType = true;
        chatListBean.newestMsg = bean.getApplayMsg();
        return chatListBean;
    }

    /**
     * 获取最新的一条总的消息聊天对象信息
     */
    public void getTotalNewestChatListBean(Action1<ChatListBean> action1) {
        Subscription subscription = Observable.create(new Observable.OnSubscribe<ChatListBean>() {
            @Override
            public void call(Subscriber<? super ChatListBean> subscriber) {
                ChatListBean chatListBean = RecentDBManager.getInstance().queryTotalNewest();
                subscriber.onNext(chatListBean);
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
        compositeSubscription.add(subscription);
    }

    public synchronized List<ChatListBean> getBeanList() {
        return beanList;
    }

    /**
     * 获取添加好友的消息列表
     *
     * @return
     */
    public List<ChatListBean> getAddFriendList() {
        List<ChatListBean> list = new ArrayList<>();
        int length = beanList.size();
        for (int i = 0; i < length; i++) {
            ChatListBean bean = beanList.get(i);
            if (bean.type == ChatListBean.CHAT_TYPE_ADD_FRIEND) {
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * 查询是否有新添加的好友的消息
     *
     * @return
     */
    public boolean isHaveNewFriendAdd() {
        int length = beanList.size();
        for (int i = 0; i < length; i++) {
            ChatListBean bean = beanList.get(i);
            if (bean.type == ChatListBean.CHAT_TYPE_ADD_FRIEND && bean.addState == ChatListBean.NEW_ADD_FRIEND_STATE_UN_DO) {
                // 有未处理的添加好友信息
                return true;
            }
        }
        return false;
    }

    /**
     * 通过position获取ChatListBean对象
     *
     * @param position
     * @return
     */
    public ChatListBean getChatListBean(int position) {
        if (position >= beanList.size()) return null;
        return beanList.get(position);
    }


    public int getUnreadMsgNum() {
        LogUtil.i(TAG, "未读消息数量----->>>" + unreadMsgNum);
        return unreadMsgNum;
    }

    /**
     * 回调所有的未读消息数量的监听
     */
    private void callBackUnreadMsgNumChangeListener() {
        int length = this.unreadMsgNumListeners.size();
        for (int i = 0; i < length; i++) {
            this.unreadMsgNumListeners.get(i).onChanged(unreadMsgNum);
        }
    }

    /**
     * 回调所有的列表变化的监听
     *
     * @param position
     */
    private void callBackChatListChangeListener(int position) {
        int length = this.listChangedListeners.size();
        for (int i = 0; i < length; i++) {
            this.listChangedListeners.get(i).onChange(position);
        }
    }

    /**
     * 减去未读消息数量(减去已读消息数量)
     *
     * @param num
     */
    public void minusUnreadMsgNum(int num) {
        LogUtil.i(TAG, "当前未读消息数------>>>" + this.unreadMsgNum + "---需要减去的数量----->>>" + num);
        this.unreadMsgNum = this.unreadMsgNum - num;
        if (this.unreadMsgNum < 0) this.unreadMsgNum = 0;
        LogUtil.i(TAG, "减去之后的未读消息数------>>>" + this.unreadMsgNum);
    }

    /**
     * 注册消息数量改变的监听
     *
     * @param listener
     */
    public void registerOnMsgNumChangedListener(onTotalMsgNumChangedListener listener) {
        if (listener == null) return;
        this.unreadMsgNumListeners.add(listener);
    }

    /**
     * 取消监听总的未读消息数量发生改变的监听
     *
     * @param listener
     */
    public void unregisterMsgNumChangeListener(onTotalMsgNumChangedListener listener) {
        this.unreadMsgNumListeners.remove(listener);
    }

    /**
     * 注册最近联系人列表发生改变的监听
     *
     * @param listener
     */
    public void registerOnRecentListChangedListener(onRecentChatListChangedListener listener) {
        if (listener == null) return;
        this.listChangedListeners.add(listener);
    }

    /**
     * 取消监听最近联系人列表发生改变的监听
     *
     * @param listener
     */
    public void unregisterOnRecentListChangedListener(onRecentChatListChangedListener listener) {
        if (listChangedListeners != null) {
            this.listChangedListeners.remove(listener);
        }
    }

//    /**
//     * 设置新消息接收的监听
//     *
//     * @param listener
//     */
//    public void setOnNewMessageReceiveListener(onNewMessageReceiveListener listener) {
//        this.newMessageReceiveListener = listener;
//    }

//    /**
//     * 用户主动发起聊天，生成一个聊天记录
//     *
//     * @param type 聊天对象的类型：私聊，群聊
//     */
//    public void initiativeChat(int type, String id) {
//        Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                // 设置当前正在聊天对象的信息
//                chatting(type, id);
//                if (beanList.isEmpty()) {
//                    // 最近聊天记录中没有记录
//                    LogUtil.i(TAG, "最近聊天记录为空");
//                    ChatListBean listBean = createNewChatListBean(type, id);
//                    // 插入一条新的数据到数据库
//                    boolean isSuccess = RecentDBManager.getInstance().insert(listBean);
//                    if (!isSuccess) return;
//                    beanList.add(listBean);
//                    subscriber.onNext(-1);
//                    return;
//                }
//
//                int position = matchingPosition(type, id);
//                if (position >= 0) {
//                    LogUtil.i(TAG, "最近聊天列表中有此聊天对象,移除旧的数据，将当前聊天对象添加到第一条");
//                    // 设置为已读消息
//                    readChatListBeanMessage(position);
//                    subscriber.onNext(-1);
//
//                } else {
//                    LogUtil.i(TAG, "最近聊天列表中没有此聊天对象");
//                    ChatListBean listBean = createNewChatListBean(type, id);
//                    boolean isSuccess = RecentDBManager.getInstance().insert(listBean);
//                    if (!isSuccess) return;
//                    beanList.add(0, listBean);
//                    subscriber.onNext(-1);
//                }
//            }
//        }).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer position) {
//                        callBackChatListChangeListener(position);
//                        callBackUnreadMsgNumChangeListener();
//                    }
//                });
//
//        compositeSubscription.add(subscription);
//    }


    /**
     * 设置已读消息
     *
     * @param type
     * @param id
     */
    public void readMessage(int type, String id) {
        // 设置当前正在聊天对象的信息
        Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                chatting(type, id);
                int position = matchingPosition(type, id);
                if (position >= 0) {
                    LogUtil.i(TAG, "最近聊天列表中有此聊天对象");
                    // 设置为已读消息
                    readChatListBeanMessage(position);
                    subscriber.onNext(-1);
                }
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer position) {
                        callBackChatListChangeListener(position);
                        callBackUnreadMsgNumChangeListener();
                    }
                });
        compositeSubscription.add(subscription);
    }


    /**
     * 总线中被动接收到消息
     */
    public void receiveMessage(ZkLocalMessage localMessage) {
        LogUtil.i(TAG, "被动接收到消息了！");

        Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(doReceiveMessage(localMessage));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer position) {
                        callBackChatListChangeListener(position);
                        // 回调未读消息发生改变
                        callBackUnreadMsgNumChangeListener();
                    }
                });
        compositeSubscription.add(subscription);
    }


    /**
     * 接收到离线消息(多条消息处理)
     *
     * @param messageList
     */
    public void receiveOfflineMessage(List<OfflineMessage.MsgListBean> messageList) {
        if (messageList == null || messageList.isEmpty()) return;
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                int length = messageList.size();
                int position = -1;
                for (int i = 0; i < length; i++) {
                    OfflineMessage.MsgListBean bean = messageList.get(i);
                    // 组响应的消息实体
                    ChatResponseMessage responseMessage = new ChatResponseMessage();
                    responseMessage.setSendUserId(bean.getSendUserId() + "");
                    responseMessage.setMsgId(bean.getMsgId());
                    responseMessage.setChatContent(bean.getChatContent());
                    responseMessage.setDeviceType(bean.getDeviceType());
                    responseMessage.setGroupId(bean.getGroupId() + "");
                    responseMessage.setTime(TimeUtils.getLongTime(bean.getTime()));

                    if (ChatDBManager.getInstance().saveChatResponseMessage(responseMessage)) {
                        // 查询出本地已经保存好的消息对象
                        ZkLocalMessage localMessage = ChatDBManager.getInstance().queryByMsgId(responseMessage.getMsgId());
                        if (localMessage != null) {
                            position = doReceiveMessage(localMessage);
                        }
                    }
                }
                subscriber.onNext(position);
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer position) {
                        callBackChatListChangeListener(position);
                        // 回调未读消息发生改变
                        callBackUnreadMsgNumChangeListener();
                    }
                });
    }

    /**
     * 处理接收到消息(在异步线程调用)
     *
     * @param localMessage
     */
    private int doReceiveMessage(ZkLocalMessage localMessage) {
        int type;
        String id;
        LogUtil.i(TAG, "localMessage.getSendUserId()------->>>" + localMessage.getSendUserId() + "\nlocalMessage.getGroupId()------>>>" + localMessage.getGroupId());
        if (!StringUtils.isEmpty(localMessage.getGroupId())) {
            LogUtil.i(TAG, "是群聊类型");
            type = ChatListBean.CHAT_TYPE_MORE;
            id = localMessage.getGroupId();
        } else if (!localMessage.getSendUserId().isEmpty()) {
            LogUtil.i(TAG, "是单聊类型");
            type = ChatListBean.CHAT_TYPE_SINGLE;
            id = localMessage.getSendUserId();
        } else {
            LogUtil.i(TAG, "是其它消息类型，系统消息！");
            type = ChatListBean.CHAT_TYPE_SYSTEM;
            id = "-1";
        }

        boolean isChatting = false;
        if (!StringUtils.isEmpty(curChatSessionId) || curChatSessionType != -1) {
            if (curChatSessionType == type && StringUtils.equals(curChatSessionId, id)) {
                // 是当前正在聊天对象的消息
                isChatting = true;
            }
        }

        int position = matchingPosition(type, id);
        if (isChatting) {
            // 是正在聊天对象的消息
            LogUtil.i(TAG, "是当前正在聊天对象的消息！！");
            receiveChattingMessage(localMessage);
//            subscriber.onNext(position);
            return position;
        } else {
            // 不是正在聊天对象的消息
            LogUtil.i(TAG, "不是当前正在聊天对象的消息！！");
            if (position >= 0) {
                LogUtil.i(TAG, "最近聊天列表中有此聊天对象");
                // 重新排序最近聊天列表顺序
                receiveUnChattingMessage(localMessage, position);
            } else {
                LogUtil.i(TAG, "最近聊天列表中没有此聊天对象, type----->>>" + type);
                createNewChatListBean(localMessage, type, id);
            }
//            subscriber.onNext(-1);
            return -1;
        }
    }

    /**
     * 接收到主动发送出去消息的消息
     *
     * @param event
     */
    public void receiveSendMessage(ZkNetEvent event) {
        LogUtil.i(TAG, "主动发送消息了");
        Subscription subscription = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                int position = disposeSendMessage(event);
                if (position == -1) return;
                subscriber.onNext(position);
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer position) {
                        callBackChatListChangeListener(position);
                    }
                });
        compositeSubscription.add(subscription);
    }

    /**
     * 处理接收到的发送消息
     *
     * @param event
     */
    private int disposeSendMessage(ZkNetEvent event) {
        ZkLocalMessage message = null;
        // 消息是否发送成功
        boolean isSuccess = false;
        if (event.retCode == ZkRetCode.FAILED) {
            LogUtil.e(TAG, "主动发送消息失败!!");
            message = event.localMessage;
            isSuccess = false;
//            Session msession = new Session();
//            msession.setMsgId(localMessage.getMsgId());
//            msession.setSendTime(localMessage.getTime());
        } else if (event.retCode == ZkRetCode.SECCUSS) {
            LogUtil.i(TAG, "主动发送消息成功!!");
            AckS2CMessage successMessage = (AckS2CMessage) event.eventData;
            message = NetFace.getDefault().queryByMsgId(successMessage.getMsgId());
            isSuccess = true;
//            Session session = new Session();
//            session.setMsgId(chatResponseMessage.getMsgId());
//            session.setSendTime(chatResponseMessage.getTime());
        }

        if (message == null) return -1;
        // 聊天对象类型
        int type = -1;
        // 聊天对象的id
        String id = "";
        Session session = Json.toBean(message.chatContent, Session.class);
        if (session == null) return -1;

        if (StringUtils.isEmpty(session.getGroupId())) {
            // 单聊
            type = ChatListBean.CHAT_TYPE_SINGLE;
            List<String> list = session.getToUserList();
            if (list.isEmpty()) return -1;
            id = list.get(0);
        } else {
            // 群聊
            type = ChatListBean.CHAT_TYPE_MORE;
            id = session.getGroupId();
        }
        int position = matchingPosition(type, id);

        if (position < 0) {
            LogUtil.i("llj", "列表中没有此聊天对象!");
            // 列表中没有此聊天对象
            ChatListBean listBean = createNewChatListBean(type, id);
            // 插入一条新的数据到数据库
            RecentDBManager.getInstance().insert(listBean);
            beanList.add(0, listBean);
            position = 0;
        } else {
            LogUtil.i("llj", "列表中已经有此聊天对象!");
            ChatListBean listBean = beanList.get(position);
            listBean.newestMsg = getNewestMessage(session);
            if (!isSuccess) listBean.newestMsg = listBean.newestMsg + "(失败)";
            listBean.newestMsgType = session.getChatType();
            listBean.newestMessageTime = message.getTime();
            listBean.newestMessageIsReceiveType = false;
            // 插入(不需要判断是插入一条新数据还是更新数据，在插入数据库的操作中已经做了处理)
            RecentDBManager.getInstance().insert(listBean);
        }
//        if (position < 0) return -1;

//        RecentDBManager.getInstance().insert(listBean);
        return position;
    }

    /**
     * 匹配列表中的聊天对象
     *
     * @param type
     * @param id
     * @return
     */
    public ChatListBean matching(int type, String id) {
        int length = beanList.size();
        for (int i = 0; i < length; i++) {
            ChatListBean chatListBean = beanList.get(i);
            if (chatListBean.type == type && StringUtils.equals(chatListBean.id, id)) {
                return chatListBean;
            }
        }
        return null;
    }

    /**
     * 匹配列表中的聊天对象
     *
     * @param type
     * @param id
     * @return
     */
    public int matchingPosition(int type, String id) {
        int length = beanList.size();
        for (int i = 0; i < length; i++) {
            ChatListBean chatListBean = beanList.get(i);
            if (chatListBean.type == type && StringUtils.equals(chatListBean.id, id)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 设置正在聊天
     *
     * @param type 聊天对象类型
     * @param id   聊天对象id
     */
    public void chatting(int type, String id) {
        this.curChatSessionType = type;
        this.curChatSessionId = id;
    }


    /**
     * 设置退出正在聊天
     */
    public void exitChatting() {
        this.curChatSessionType = -1;
        this.curChatSessionId = "";
        this.compositeSubscription.clear();
    }

    /**
     * calm
     * 重新排序最近聊天记录列表(之前已存在此聊天对象，不是正在聊天对象的消息)
     *
     * @param message  接收到的消息对象
     * @param position
     */
    private void receiveUnChattingMessage(ZkLocalMessage message, int position) {
        if (message == null) return;
        ChatListBean listBean = beanList.get(position);
        if (listBean == null) return;
        // 消息对象的未读消息加1
        listBean.unreadMessageNum++;
        Session session = Json.toBean(message.chatContent, Session.class);
        if (session == null) return;
        listBean.newestMsg = getNewestMessage(session);
        listBean.newestMsgType = session.getChatType();
        listBean.newestMessageTime = message.getTime();
        listBean.newestMessageIsReceiveType = true;
        LogUtil.i("接收到消息时间-------->>>" + listBean.newestMessageTime);

        // 更新数据库中最近聊天列表中此聊天对象的信息
        boolean isSuccess = RecentDBManager.getInstance().update(listBean);
        if (!isSuccess) return;

        // 总的未读消数量加1
        unreadMsgNum++;
        if (position == 0) return;
        // 移除原来的位置并添加到列表的第一条数据
        beanList.add(0, beanList.remove(position));
    }

    /**
     * 接收到正在聊天对象的消息
     *
     * @param message
     */
    private void receiveChattingMessage(ZkLocalMessage message) {
        if (message == null) return;
        if (beanList.isEmpty()) return;
        ChatListBean listBean = beanList.get(0);
        if (listBean == null) return;
        listBean.unreadMessageNum = 0;
        Session session = Json.toBean(message.chatContent, Session.class);
        listBean.newestMsg = getNewestMessage(session);
        listBean.newestMsgType = session.getChatType();
        listBean.newestMessageTime = message.getTime();
        listBean.newestMessageIsReceiveType = true;
        // 更新数据库中最近聊天列表中此聊天对象的信息
        RecentDBManager.getInstance().update(listBean);
    }

    /**
     * 设置最近聊天列表中的聊天对象消息为已读
     *
     * @param position
     */
    private void readChatListBeanMessage(int position) {
        ChatListBean listBean = beanList.get(position);
        if (listBean == null) return;
        // 总的未读消息数量减去此聊天对象的未读消息数
        minusUnreadMsgNum(listBean.unreadMessageNum);
        // 设置此对象的未读消息数为0
        listBean.unreadMessageNum = 0;
        listBean.newestUpdateTime = System.currentTimeMillis();
        // 更新数据库中聊天对象数据
        RecentDBManager.getInstance().update(listBean);
//        if (position == 0) return;
//        // 移除原来的位置并添加到列表的第一条数据
//        beanList.add(0, beanList.remove(position));
    }


    /**
     * <p>
     * 创建一个主动发起聊天的ChatListBean对象
     *
     * @param type
     * @param id
     */
    private ChatListBean createNewChatListBean(int type, String id) {
        if (type == ChatListBean.CHAT_TYPE_SINGLE) {
            // 是单个好友消息

            ContactsListBean bean = ContactsDBManager.getInstance().queryContactBeanByType(ContactsListBean.TYPE_FRIEND_PERSON, Integer.valueOf(id));
            if (bean != null) {
                // 联系人列表中有此联系人(已经是好友)
                LogUtil.i("llj", "主动发起聊天，创建新的最近聊天的对象，本地联系人列表中中有此联系人！");
                return doContactsListBean(bean);
            } else {
                // 本地联系人列表中没有此联系人
                LogUtil.i("llj", "主动发起，创建新的最近聊天的对象，但本地联系人列表中中没有此联系人！");
            }
        } else {
            ContactsListBean bean = ContactsDBManager.getInstance().queryGroupContactsBeanByIdFromAll(Integer.valueOf(id));
            if (bean != null) {
                LogUtil.i("llj", "接收到群组类型聊天消息，创建新的最近聊天的对象，本地联系人列表中中有此联系人！");
                return doContactsListBean(bean);
            } else {
                LogUtil.i("llj", "接收到群组类型聊天消息，创建新的最近聊天的对象，但本地联系人列表中中没有此联系人！");
            }
        }
        return null;
    }


    /**
     * 创建一个被动接收到消息时新的ChatListBean对象并添加到最近聊天对象列表中去
     */
    private void createNewChatListBean(ZkLocalMessage message, int type, String id) {
        if (type == ChatListBean.CHAT_TYPE_SINGLE) {
            LogUtil.i("llj", "接收到聊天消息，是单个好友聊天消息！");
            ContactsListBean bean = ContactsDBManager.getInstance().queryContactBeanByType(ContactsListBean.TYPE_FRIEND_PERSON, Integer.valueOf(id));
            if (bean != null) {
                // 联系人列表中有此联系人(已经是好友)
                LogUtil.i("llj", "接收到个人类型聊天消息，创建新的最近聊天的对象，本地联系人列表中中有此联系人！");
                doContactsListBean(message, bean);
            } else {
                // 本地联系人列表中没有此联系人
                LogUtil.i("llj", "接收到个人类型聊天消息，创建新的最近聊天的对象，但本地联系人列表中中没有此联系人！");
            }

//            // 是单个好友消息
//            ContactsManager.getInstance().queryContactsByTypeAndId(ContactsListBean.TYPE_FRIEND_PERSON, Integer.valueOf(id), new Action1<ContactsListBean>() {
//                @Override
//                public void call(ContactsListBean bean) {
//                    if (bean != null) {
//                        // 联系人列表中有此联系人(已经是好友)
//                        LogUtil.i("llj", "接收到个人类型聊天消息，创建新的最近聊天的对象，本地联系人列表中中有此联系人！");
//                        doContactsListBean(message, bean);
//                    } else {
//                        // 本地联系人列表中没有此联系人
//                        LogUtil.i("llj", "接收到个人类型聊天消息，创建新的最近聊天的对象，但本地联系人列表中中没有此联系人！");
//                    }
//
//                }
//            });
        } else {
            LogUtil.i("llj", "接收到聊天消息，不是单个好友聊天消息(是群聊消息)");

            ContactsListBean bean = ContactsDBManager.getInstance().queryGroupContactsBeanByIdFromAll(Integer.valueOf(id));
            if (bean != null) {
                LogUtil.i("llj", "接收到群组类型聊天消息，创建新的最近聊天的对象，本地联系人列表中中有此联系人！");
                doContactsListBean(message, bean);
            } else {
                LogUtil.i("llj", "接收到群组类型聊天消息，创建新的最近聊天的对象，但本地联系人列表中中没有此联系人！");
            }

//            ContactsManager.getInstance().queryGroupContactsByIdFromAll(Integer.valueOf(id), new Action1<ContactsListBean>() {
//                @Override
//                public void call(ContactsListBean bean) {
//                    if (bean != null) {
//                        LogUtil.i("llj", "接收到群组类型聊天消息，创建新的最近聊天的对象，本地联系人列表中中有此联系人！");
//                        doContactsListBean(message, bean);
//                    } else {
//                        LogUtil.i("llj", "接收到群组类型聊天消息，创建新的最近聊天的对象，但本地联系人列表中中没有此联系人！");
//                    }
//                }
//            });
        }


//        ChatListBean chatListBean = new ChatListBean();
//        chatListBean.unreadMessageNum = 1;
//        chatListBean.type = type;
//        chatListBean.id = id;
//        chatListBean.icon = "http://img.jsqq.net/uploads/allimg/150111/1_150111080328_19.jpg";
//        if (chatListBean.type == ChatListBean.CHAT_TYPE_SINGLE) {
//            chatListBean.name = "好友(" + id + ")";
//        } else if (chatListBean.type == ChatListBean.CHAT_TYPE_MORE) {
//            chatListBean.name = "群组(" + id + ")";
//        } else if (chatListBean.type == ChatListBean.CHAT_TYPE_SYSTEM) {
//            chatListBean.name = "系统消息";
//        }
//
//        Session session = Json.toBean(message.chatContent, Session.class);
//        if (session == null) return;
//        chatListBean.newestMsg = getNewestMessage(session);
//        chatListBean.newestMessageTime = message.getTime();
//        chatListBean.newestMsgType = session.getChatType();
//        chatListBean.newestUpdateTime = System.currentTimeMillis();
//        chatListBean.newestMessageIsReceiveType = true;
//        // 插入一条新的数据到数据库
//        boolean isSuccess = RecentDBManager.getInstance().insert(chatListBean);
//        // 插入一条总消息中的最新聊天对象数据
//        RecentDBManager.getInstance().insert(chatListBean);
//        if (!isSuccess) return;
//        // 总的未读消息数量加1
//        unreadMsgNum++;
//        // 添加到最近聊天对象列表第一位
//        beanList.add(0, chatListBean);
    }


    /**
     * 处理得到的联系人信息
     */
    private void doContactsListBean(ZkLocalMessage message, ContactsListBean bean) {
        ChatListBean chatListBean = new ChatListBean();
        chatListBean.unreadMessageNum = 1;
        if (bean.type == ContactsListBean.TYPE_FRIEND_PERSON) {
            chatListBean.type = ChatListBean.CHAT_TYPE_SINGLE;
        } else {
            chatListBean.type = ChatListBean.CHAT_TYPE_MORE;
        }
        chatListBean.id = bean.id + "";
        chatListBean.icon = bean.headUrl;
        chatListBean.name = bean.nickName;
        Session session = Json.toBean(message.chatContent, Session.class);
        if (session == null) return;
        chatListBean.newestMsg = getNewestMessage(session);
        chatListBean.newestMessageTime = message.getTime();
        chatListBean.newestMsgType = session.getChatType();
        chatListBean.newestUpdateTime = System.currentTimeMillis();
        chatListBean.newestMessageIsReceiveType = true;
        // 插入一条新的数据到数据库
        boolean isSuccess = RecentDBManager.getInstance().insert(chatListBean);
        // 插入一条总消息中的最新聊天对象数据
        RecentDBManager.getInstance().insert(chatListBean);
        if (!isSuccess) return;
        // 总的未读消息数量加1
        unreadMsgNum++;
        // 添加到最近聊天对象列表第一位
        beanList.add(0, chatListBean);
    }

    private ChatListBean doContactsListBean(ContactsListBean bean) {
        ChatListBean chatListBean = new ChatListBean();
        if (bean.type == ContactsListBean.TYPE_FRIEND_PERSON) {
            chatListBean.type = ChatListBean.CHAT_TYPE_SINGLE;
        } else {
            chatListBean.type = ChatListBean.CHAT_TYPE_MORE;
        }
        chatListBean.id = bean.id + "";
        chatListBean.icon = bean.headUrl;
        chatListBean.name = bean.nickName;

        chatListBean.newestMessageTime = 0;
        chatListBean.unreadMessageNum = 0;
        chatListBean.newestUpdateTime = System.currentTimeMillis();
        return chatListBean;
    }


    /**
     * 等到最新显示的消息文本
     *
     * @param session
     * @return
     */
    private String getNewestMessage(Session session) {

        LogUtil.i(TAG, "消息类型---sessionType---->>" + session.getChatType());
        switch (session.getChatType()) {
            case Session.TEXT:
                // 文本类型
                return session.getContent();
            case Session.VOICE:
                // 语音类型
                return "[语音]";
            case Session.IMAGE:
                // 图片类型
                return "[图片]";
            case Session.VIDEO:
                // 视频类型
                return "[视频]";
            case Session.STICKER:
                // 表情类型
                String content = session.getContent();
                if (content.endsWith(".png") || content.endsWith(".gif")) {
                    // 表情贴图
                    return "[表情图片]";
                }
                return content;
            default:
                return "新消息";
        }
    }

//    /**
//     * 删除某个联系人(对应的聊天记录也删除)
//     */
//    public void removeContact(int position) {
//        if (position < 0) return;
//        ChatListBean listBean = beanList.get(position);
//        if (listBean == null) return;
//        LogUtil.i(TAG, "删除聊天列表中的记录---->>" + listBean.name);
//        minusUnreadMsgNum(listBean.unreadMessageNum);
//        beanList.remove(position);
//        callBackChatListChangeListener(-1);
//    }


    /**
     * 删除一条数据
     *
     * @param type
     * @param id
     */
    public void deleteRecentChatListBean(int type, String id) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                RecentDBManager.getInstance().delete(type, id);
                // 再查询出所有的最近列表
                beanList = RecentDBManager.getInstance().queryAll();
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                callBackChatListChangeListener(-1);
            }
        });
    }

    /**
     * 改变添加好友信息的状态
     *
     * @param id
     * @param addState
     */
    public void changeAddFriendChatState(String id, int addState) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                ChatListBean chatListBean = RecentDBManager.getInstance().query(ChatListBean.CHAT_TYPE_ADD_FRIEND, id);
                if (chatListBean == null) {
                    return;
                }
                chatListBean.addState = addState;
                // 更新到数据库
                RecentDBManager.getInstance().update(chatListBean);

                // 再查询出所有最近聊天列表数据集合
                beanList = RecentDBManager.getInstance().queryAll();
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                callBackChatListChangeListener(-1);
            }
        });
    }


    /**
     * 清除所有记录
     */
    public void clear() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                RecentDBManager.getInstance().deleteAll();
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    /**
     * 总的消息数量发生改变的监听
     */
    public interface onTotalMsgNumChangedListener {
        void onChanged(int msgNum);
    }

    /**
     * 最近联系人列表数据发生改变的监听
     */
    public interface onRecentChatListChangedListener {
        //        /**
//         * @param isFirstChange 此次改变是否只是第一条数据未读消息数发生了改变
//         *                      true=Adapter局部刷新第一条数据就好了，false=Adapter全部刷新
//         */
//        void onChange(boolean isFirstChange);

        /**
         * @param position 是那条数据发生了变化 -1=列表顺序发生了改变，>=0表示只是对应的position的数据发生了改变，只需要刷新对应的item
         */
        void onChange(int position);
    }

//    /**
//     * 接收到新的消息的监听
//     */
//    public interface onNewMessageReceiveListener {
//        void onReceive(ChatListBean listBean);
//    }

}
