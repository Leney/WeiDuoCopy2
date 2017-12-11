package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;

import java.util.List;

/**
 * Created by Karma on 2017/6/28.
 * 类描述：最近聊天接口
 */

public interface IRecentMessageFgView extends BaseView {
    /**
     * 获取最近聊天列表数据成功
     */
    void getRecentChatListSuccess(List<ChatListBean> list);

    void handleRequestSuccess(ChatListBean bean, String friendState);

    void handleRequestFailed(String friendState);
}
