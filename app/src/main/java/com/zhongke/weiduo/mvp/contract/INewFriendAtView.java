package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;

import java.util.List;

/**
 * Created by Karma on 2017/6/28.
 * 类描述：
 */

public interface INewFriendAtView extends BaseView {

    void getNewFriendMsgListSuccess(List<ChatListBean> list);
    void getNewFriendMsgListFailed();

    //处理好友请求
    void handleRequestSuccess(ChatListBean bean,String friendState);

    void handleRequestFailed(String friendState);

}
