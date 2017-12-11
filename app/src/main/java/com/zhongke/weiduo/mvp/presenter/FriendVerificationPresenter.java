package com.zhongke.weiduo.mvp.presenter;

import android.text.TextUtils;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.im.IMConstance;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.FriendVerificationContract;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * Created by hyx on 2017/11/15.
 */

public class FriendVerificationPresenter extends BasePresenter {

    private FriendVerificationContract contract;

    public FriendVerificationPresenter(FriendVerificationContract checkMessageContract) {
        this.contract = checkMessageContract;
    }


    public void applyFriend(String friendId,String applyMsg,String friendNickName) {
        Map<String,String> map = new HashMap<>();

        map.put("friendId",friendId);
        map.put("applayMsg",applyMsg);
        if (!TextUtils.isEmpty(friendNickName)){
            map.put("friendNickName",friendNickName);
        }
        Subscription subscription = retrofitClient.applyFriendRequest(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                contract.applyFriendRequestSuccess();
                LogUtil.e("success----");
                List<String> toUserList = new ArrayList<String>();
                toUserList.add(friendId);
                IMClient.sendExtMessage(IMConstance.ADD_FRIEND_REQUEST, toUserList, "");

                // 更改状态
                RecentChatListManager.getInstance().changeAddFriendChatState(friendId, ChatListBean.NEW_ADD_FRIEND_STATE_ALREADY_SEND);
            }

            @Override
            public void failure(CommonException e) {
                contract.applyFriendRequestFailed(e);
                LogUtil.e("applyFriend failure---" + "e.code--" + e.getCode() + e.toString());
            }
        });
        compositeSubscription.add(subscription);
    }

}
