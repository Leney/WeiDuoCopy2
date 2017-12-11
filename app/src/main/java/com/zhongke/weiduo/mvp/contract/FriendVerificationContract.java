package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;

/**
 * Created by hyx on 2017/11/15.
 */

public interface FriendVerificationContract extends BaseView {

    //添加好友请求成功
    void applyFriendRequestSuccess();

    void applyFriendRequestFailed(CommonException e);
}
