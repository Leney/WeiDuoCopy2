package com.zhongke.weiduo.mvp.presenter;


import android.content.Context;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.im.IMConstance;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.IRecentMessageFgView;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * Created by llj on 2017/6/28.
 * 类描述：最近聊天界面P层
 */

public class RecentMessageFgPresenter extends BasePresenter<IRecentMessageFgView> {
    private static final String TAG = "RecentMessageFgPresenter";

    private Context mContext;

    public RecentMessageFgPresenter(Context context) {
        this.mContext = context;
    }

    /**
     * 获取最近聊天列表数据
     */
    public void getRecentChatList() {
        // 获取最近聊天列表成功
        getView().getRecentChatListSuccess(RecentChatListManager.getInstance().getBeanList());
        LogUtil.e("getBeanList--" + RecentChatListManager.getInstance().getBeanList());
       // LogUtil.e("getBeanList--"+RecentChatListManager.getInstance().getBeanList());
        RecentChatListManager.getInstance().getRequestAddFriendListFromNetwork();
    }

    /**
     * 同意或拒绝好友请求
     *
     * @param friendState 是否接受好友请求 3=拒绝，2=通过
     */
    public void handleFriendRequest(ChatListBean bean, String friendState) {
        Map<String, String> map = new HashMap<>();
        LogUtil.e("bean.id--" + bean.id);
        map.put("friendId", bean.id);
        map.put("friendState", friendState);

        Subscription subscription = retrofitClient.handleFriendRequest(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                ContactsListBean contactsListBean = new ContactsListBean();
                contactsListBean.type = ContactsListBean.TYPE_FRIEND_PERSON;
                contactsListBean.id = Integer.valueOf(bean.id);
                contactsListBean.nickName = bean.name;
                contactsListBean.headUrl = bean.icon;
                if ("2".equals(friendState)) {
                    bean.addState = ChatListBean.NEW_ADD_FRIEND_STATE_PASSED;
                    LogUtil.e("同意添加 success--");
                    ContactsManager.getInstance().insertContactList(contactsListBean);

                    // 发送同意添加好友的扩展消息
                    List<String> toUserList = new ArrayList<>();
                    toUserList.add(bean.id);
                    IMClient.sendExtMessage(IMConstance.AGREE_FRIEND_REQUEST, toUserList, "");
                    // 更改添加好友的信息状态
                    RecentChatListManager.getInstance().changeAddFriendChatState(bean.id, ChatListBean.NEW_ADD_FRIEND_STATE_PASSED);
                } else if ("3".equals(friendState)) {
                    bean.addState = ChatListBean.NEW_ADD_FRIEND_STATE_NO_PASSED;
                    LogUtil.e("拒绝添加 success--");

                    // 发送拒绝添加好友的扩展消息
                    List<String> toUserList = new ArrayList<>();
                    toUserList.add(bean.id);

                    StringBuilder builder = new StringBuilder();
                    builder.append(IMConstance.REFUSE_FRIEND_REQUEST)
                            .append(",")
                            .append(IMClient.USER_ID);

                    IMClient.sendExtMessage(builder.toString(), toUserList, "");
                    // 更改添加好友的信息状态
                    RecentChatListManager.getInstance().changeAddFriendChatState(bean.id, ChatListBean.NEW_ADD_FRIEND_STATE_NO_PASSED);
                }
                getView().handleRequestSuccess(bean, friendState);
//                // 删除最近联系人列表中的数据
//                RecentChatListManager.getInstance().deleteRecentChatListBean(bean.type,bean.id);
            }

            @Override
            public void failure(CommonException e) {
                if ("2".equals(friendState)) {
                    LogUtil.e("同意添加 failure--" + e.getErrorMsg());
                } else {
                    LogUtil.e("拒绝添加 failure--" + e.getErrorMsg());
                }
                getView().handleRequestFailed(friendState);
            }
        });
        compositeSubscription.add(subscription);
    }

}
