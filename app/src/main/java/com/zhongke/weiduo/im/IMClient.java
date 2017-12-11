package com.zhongke.weiduo.im;

import android.net.NetworkInfo;
import android.util.Log;

import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.StringUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.im.extra.ExtraMessage;
import com.zhongke.weiduo.im.extra.message.WishBindActivityNotice;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.library.retrofit.RetrofitClient;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;
import com.zhongke.weiduo.mvp.model.entity.MemberInfo;
import com.zhongke.weiduo.mvp.model.entity.OfflineMessage;
import com.zhongke.weiduo.mvp.model.entity.Session;
import com.zk.Json;
import com.zk.NetFace;
import com.zk.ZkDeviceType;
import com.zk.ZkNetEvent;
import com.zk.android.sqlite.ZkLocalMessage;
import com.zk.net.ZkMessagePacket;
import com.zk.net.message.ChatRequestMessage;
import com.zk.net.message.ExtCmdResponseMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Karma on 2017/7/3.
 * 类描述：IM
 */

public class IMClient {
    private static final String TAG = "IMClient";
    //    private static final ZkImClient imClient = new ZkImClient();
    private static volatile IMClient Instance = null;

    public static IMClient getInstance() {
        IMClient imClient = Instance;
        if (imClient == null) {
            synchronized (IMClient.class) {
                imClient = Instance;
                if (imClient == null) {
                    Instance = imClient = new IMClient();
                }
            }
        }
        return imClient;
    }

    /**
     * 用户本身的Id
     */
    public static String USER_ID = "10087";
//    /**
//     * 聊天对象的Id
//     */
//    public  static final String OBJECT_ID="10021";
//    /**
//     * 群组的id
//     */
//    public static final String GROUP_ID = "110";
    /**
     * 群组id集合
     */
    private List<String> groupIdList = new ArrayList<>();

    /**
     * im配置信息
     */
    private String devMac = "111111112";
    private String mHost = "121.42.36.103";
    private int mPort = 5566;

    /**
     * 发送消息的时候需要携带的自己的用户信息对象
     */
    private static MemberInfo mMemberInfo;

    public void initSdk(LoginResult.SysUserBean userBean, List<String> groupIdList) {
        this.groupIdList.addAll(groupIdList);
        this.USER_ID = userBean.getId() + "";
        NetFace.getDefault().getImClientConfig().devMac = devMac;
        NetFace.getDefault().getImClientConfig().devType = ZkDeviceType.Mobile;
        NetFace.getDefault().getImClientConfig().token = ZkApplication.getInstance().getToken();
        NetFace.getDefault().getImClientConfig().userId = USER_ID;
        NetFace.getDefault().getImClientConfig().mHost = mHost;
        NetFace.getDefault().getImClientConfig().mPort = mPort;

        //初始化
        NetFace.getDefault().Init(ZkApplication.getInstance());
//        onConnect(NetFace.getDefault().getImClientConfig().mPort, "121.42.36.103");
        onConnect(mPort, mHost);
        EventBus.getDefault().register(this);
        mMemberInfo = new MemberInfo();
        mMemberInfo.setId(userBean.getId());
        mMemberInfo.setName(userBean.getUserName());
        mMemberInfo.setIcon(userBean.getHeadImageUrl());
        LogUtil.i("llj", "初始化时，mMemberInfo.getIcon()------->>>" + mMemberInfo.getIcon());
    }

    public void unInitSdk() {
        NetFace.getDefault().unInit(UIUtils.getContext());
        EventBus.getDefault().unregister(this);
        this.groupIdList.clear();
    }

    public static void onConnect(int port, String host) {
        try {
            NetFace.getDefault().connect(port, host);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onReconnect() {
        try {
            NetFace.getDefault().reConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onDisconnect() {
        NetFace.getDefault().disConnect();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ZkNetEvent event) {
        Log.i("llj", "接收到消息-type----》》》" + event.netEventType);
        switch (event.netEventType) {
            case MSG_CHAT_REQ_EVENT://聊天消息发送
//                if (event.retCode == ZkRetCode.FAILED) {
//                    LogUtil.e(TAG, "-------发送失败--------");
//                    String msgId = event.eventData.toString();
//                    ZkLocalMessage localMessage = event.localMessage;
//                    ZkMessagePacket packet = (ZkMessagePacket) event.extData;//这里可以重传
//                } else if (event.retCode == ZkRetCode.SECCUSS) {
//
//                }

                // 是聊天消息
                RecentChatListManager.getInstance().receiveSendMessage(event);
                break;
            case MSG_CHAT_RES_EVNT://聊天消息接收
                Log.i("llj", "接收到消息！！！！");
                if (event.localMessage != null) {
                    // 用户接收到消息
                    RecentChatListManager.getInstance().receiveMessage(event.localMessage);
                } else {
                    LogUtil.e(TAG, "-------->服务器返回聊天信息：null");
                }
                break;
            case MSG_EXT_REQ_EVENT:
                // 扩展消息类型(发送消息)
                LogUtil.i(TAG, "------>>发送扩展消息");
                break;
            case MSG_EXT_RES_EVENT:
                // 扩展消息类型(接收消息)
                LogUtil.i(TAG, "------>>接收到扩展消息");
                doExtMessage(event);
                break;
            case CHANNEL_EXCEPTION://套接字通道异常
//                UIUtils.showToastSafely("套接字通道异常！");
                LogUtil.e(TAG, "-------->套接字通道异常！");
                IMClient.onReconnect();
                break;
            case CHANNEL_INTERRUPT://套接字通道被关闭中断
//                UIUtils.showToastSafely("套接字通道被关闭中断！");
                LogUtil.e(TAG, "-------->套接字通道被关闭中断！");
                break;
            case CHANNEL_CONNECT_SUCCESS://套接字通道连接成功
//                UIUtils.showToastSafely("套接字通道连接成功！");
                LogUtil.i(TAG, "-------->套接字通道连接成功！！");
                break;
            case CHANNEL_KILL_OFF://套接字通道被踢下线
                UIUtils.showToastSafely("套接字通道被踢下线！");
                break;
            case CHANEEL_AUTH_SECCUSS://通道鉴权成功
//                UIUtils.showToastSafely("通道鉴权成功！");
                LogUtil.i(TAG, "-------->通道鉴权成功！");
//                IMClient.joinGroup(GROUP_ID);
//                noNetTips.setVisibility(View.GONE);

//                int length = groupIdList.size();
//                LogUtil.i("llj", "加入群组，length------->>>>" + length);
//                for (int i = 0; i < length; i++) {
//                    // 加入群聊
//                    Log.i("llj", "加入群组---->>>" + groupIdList.get(i));
//                    IMClient.joinGroup(groupIdList.get(i));
//                }
                // 加入群组
                joinGroups(groupIdList);
                // 获取离线消息
                getOfflineMessage();

                break;
            case NETWORK_WIFI_STATE_CHANGE://WIFI状态变化
//                UIUtils.showToastSafely("wifi网络连接异常");
//                noNetTips.setVisibility(View.VISIBLE);
                break;
            case NETWORK_WIFT_CONNECT_ROUTER_CHANGE://WIFI连接路由状态变化
                if ((Boolean) event.eventData) {
                    // 网络正常、重新连接
                    IMClient.onReconnect();
                }
                IMClient.onReconnect();
                break;
            case NETWORK_INFO_CHANGE://网络信息变化包含移动和WIFI的变化
                if (event.eventData != null) {
                    NetworkInfo activeNetInfo = (NetworkInfo) event.eventData;
                    if (activeNetInfo.isAvailable() && activeNetInfo.isConnected()) {
                        // 重新连接
                        IMClient.onReconnect();
                        return;
                    }
                }
//                UIUtils.showToastSafely("网络连接异常");
//                noNetTips.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public static void sendMessage(String content, List<String> userList, String groupId) {
        NetFace.getDefault().sendChatMsgRequest(content, userList, groupId);
    }

    public static void joinGroup(String groupId) {
        NetFace.getDefault().sendJoinGroup(groupId);
    }

    /**
     * @param groupList
     */
    public static void joinGroups(List<String> groupList) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                int length = groupList.size();
                for (int i = 0; i < length; i++) {
                    NetFace.getDefault().sendJoinGroup(groupList.get(i));
                }
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 离开群组
     *
     * @param groupIdList
     */
    public static void leaveGroups(List<String> groupIdList) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                int length = groupIdList.size();
                for (int i = 0; i < length; i++) {
                    NetFace.getDefault().leaveJoinGroup(groupIdList.get(i));
                }
            }
        }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribe();
    }

    public static String sendMessageByMsgId(Session session, List<String> userList, String groupId) {
        String msgId = UUID.randomUUID().toString();
        ZkMessagePacket messagePacket = null;
        session.setMemberInfo(mMemberInfo);
        LogUtil.i("llj", "发送时的头像-----mMemberInfo.getIcon----->>>" + mMemberInfo.getIcon());
        LogUtil.i("llj", "发送时的头像---------->>>" + session.getMemberInfo().getIcon());
        try {
            String content = Json.toJson(session);
            messagePacket = NetFace.getDefault().getSendChatMsg(content, userList, groupId);
            ((ChatRequestMessage) messagePacket.getMessage()).setMsgId(msgId);
            NetFace.getDefault().sendChatMsgRequestAndSaveDB(messagePacket);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return msgId;
    }

    /**
     * 发送扩展协议的消息，不保存数据,即时通知
     *
     * @return
     */
    public static void sendExtMessage(String content, List<String> userList, String groupId) {
        try {
            ZkMessagePacket messagePacket = NetFace.getDefault().getSendExtMsg(content, userList, groupId);
            NetFace.getDefault().sendExtMsgRequest(messagePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送扩展协议的消息，不保存数据,即时通知
     *
     * @return
     */
    public static void sendExtMessage2(ExtraMessage extraMessage, List<String> userList, String groupId) {
        try {
            ZkMessagePacket messagePacket = NetFace.getDefault().getSendExtMsg(Json.toJson(extraMessage), userList, groupId);
            messagePacket.setMessage(extraMessage);
            NetFace.getDefault().sendExtMsgRequest(messagePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取离线消息
     */
    public void getOfflineMessage() {
        LogUtil.i("llj", "获取离线消息！！！！");
        RetrofitClient.getInstance().getOfflineMessageList(new ResponseResultListener<OfflineMessage>() {
            @Override
            public void success(OfflineMessage message) {
                if (message == null) return;
                // 处理得到的离线消息
                RecentChatListManager.getInstance().receiveOfflineMessage(message.getMsgList());
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("llj", "获取离线消息失败！！！！");
            }
        });
    }

    /**
     * 清除聊天记录
     */
    public void clear() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                NetFace.getDefault().deleteAll();
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io()).subscribe();

    }

    /**
     * 查询消息,根据接受者。
     * <p>
     * 这里会匹配与对方的聊天信息，返回自己发的，和对方发的信息。
     */
    public List<ZkLocalMessage> queryMessageByReceiver(String sendUserId, String recvUserId, int pageIndex, int pageSize) {
        return NetFace.getDefault().queryWithSendUserId(sendUserId, recvUserId, pageIndex, pageSize);
    }

    /**
     * 查询消息,根据接受者。
     * <p>
     * 这里会匹配与对方的聊天信息，返回自己发的，和对方发的信息。
     */
    public List<ZkLocalMessage> queryMessageByGroup(String groupId, int pageIndex, int pageSize) {
        return NetFace.getDefault().queryWithGroupId(groupId, pageIndex, pageSize);
    }

    private void doExtMessage(ZkNetEvent event) {
        if (event == null || event.extData == null) return;
        ExtCmdResponseMessage extCmdResponseMessage = (ExtCmdResponseMessage) event.extData;
        String chatContent = extCmdResponseMessage.chatContent;
        ExtraMessage extraMessage = null;
        try {
            extraMessage = Json.toBean(chatContent,ExtraMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(extraMessage == null){
            if (StringUtil.isEquals(chatContent, IMConstance.ADD_FRIEND_REQUEST)) {
                // 是添加好友类型(命令)
                LogUtil.i("llj", "接收到添加好友命令！！！");
                // 去重新获取请求添加好友信息
                RecentChatListManager.getInstance().getRequestAddFriendListFromNetwork();
            } else if (StringUtil.isEquals(chatContent, IMConstance.AGREE_FRIEND_REQUEST)) {
                // 同意添加好友请求
                // 重新从网络获取联系人信息
                ContactsManager.getInstance().getContactsListFromNetwork(false);
            } else if (StringUtil.isEquals(chatContent, IMConstance.CREATE_GROUP_REQUEST)) {
                // 被人拉入到一个新的群聊
                // 重新从网络获取联系人信息
                ContactsManager.getInstance().getContactsListFromNetwork(false);
            }
            if (chatContent.startsWith(IMConstance.DELETE_FRIEND_REQUEST)) {
                // 对方删除了自己
                String[] values = chatContent.split(",");
                // 重新从网络获取联系人信息
                ContactsManager.getInstance().getContactsListFromNetwork(false);
                // 删除最近聊天消息
                RecentChatListManager.getInstance().deleteRecentChatListBean(Integer.valueOf(values[1]), values[2]);
            } else if (chatContent.startsWith(IMConstance.REFUSE_FRIEND_REQUEST)) {
                // 对方拒绝了我的好友请求
                String[] values = chatContent.split(",");
                RecentChatListManager.getInstance().changeAddFriendChatState(values[1], ChatListBean.NEW_ADD_FRIEND_STATE_NO_PASSED);
            }
        }else {
            // 新定义出来的协议类型
            if(extraMessage.code == ExtraMessage.wish_bind_activiy){
                // 愿望绑定活动类型
                WishBindActivityNotice notice = (WishBindActivityNotice) extraMessage.message;
                switch (notice.code){
                    case WishBindActivityNotice.code_start_wishi:
                        // 孩子许了一个愿望
                        break;
                    case WishBindActivityNotice.code_accept_activity:
                        // 孩子接受活动
                        break;
                    case WishBindActivityNotice.code_activity_finish:
                        // 绑定的活动已经结束
                        break;
                }
            }
        }
    }
}
