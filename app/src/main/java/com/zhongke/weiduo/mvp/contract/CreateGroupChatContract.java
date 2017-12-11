package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.NewGroupBean;

/**
 * Created by ${tanlei} on 2017/8/11.
 */

public interface CreateGroupChatContract extends BaseView {
    /**
     * 创建群聊成功
     */
    void createGroupSuccess(NewGroupBean newGroupBean);
}
