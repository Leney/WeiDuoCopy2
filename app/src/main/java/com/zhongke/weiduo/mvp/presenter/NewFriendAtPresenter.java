package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.INewFriendAtView;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by Karma on 2017/6/28.
 * 类描述：新朋友P层
 */

public class NewFriendAtPresenter extends BasePresenter<INewFriendAtView> {
    private static final String TAG = "NewFriendAtPresenter";
    private Context context;
    private INewFriendAtView contract;

    public NewFriendAtPresenter(Context context, INewFriendAtView contract) {
        this.context = context;
        this.contract = contract;
    }

    public void getNewFriendMsg() {
        contract.getNewFriendMsgListSuccess(RecentChatListManager.getInstance().getAddFriendList());
    }

    /**
     * 同意或拒绝好友请求
     * @param friendState 是否接受好友请求 3=拒绝，2=通过
     */
    public void handleFriendRequest(ChatListBean bean, String friendState) {
        Map<String,String> map = new HashMap<>();
        LogUtil.e("bean.id--"+bean.id);
        map.put("friendId",bean.id);
        map.put("friendState",friendState);

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
                } else if("3".equals(friendState))  {
                    bean.addState = ChatListBean.NEW_ADD_FRIEND_STATE_NO_PASSED;
                    LogUtil.e("拒绝添加 success--");
                }
                getView().handleRequestSuccess(bean,friendState);
            }

            @Override
            public void failure(CommonException e) {
                if("2".equals(friendState)) {
                    LogUtil.e("同意添加 failure--"+e.getErrorMsg());
                } else{
                    LogUtil.e("拒绝添加 failure--"+e.getErrorMsg());
                }
                getView().handleRequestFailed(friendState);
            }
        });
        compositeSubscription.add(subscription);
    }
}
