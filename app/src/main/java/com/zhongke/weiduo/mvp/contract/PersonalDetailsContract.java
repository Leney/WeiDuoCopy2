package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.FriendInfoBean;

/**
 * Created by ${tanlei} on 2017/8/11.
 */

public interface PersonalDetailsContract extends BaseView{

    //获取好友资料成功
    void getFriendInFoSuccess(FriendInfoBean bean);

    void getFriendInFoFailed(CommonException e);

    //删除好友成功
    void removeFriendSuccess();

    void removeFriendFailed();
}
