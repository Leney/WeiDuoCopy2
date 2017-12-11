package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.MyTargetBean;

/**
 * Created by ${tanlei} on 2017/9/27.
 */

public interface MyTargetListContract extends BaseView {
    /**
     * 获取我的目标列表成功
     *
     * @param myTargetBean
     */
    void getMyTargetListSuccess(MyTargetBean myTargetBean);

    /**
     * 获取我的目标列表失败
     *
     * @param e
     */
    void getMyTargetListFailed(CommonException e);

    /**
     * 删除我的目标成功
     */
    void deleteMyTargetSuccess();

    /**
     * 获取我的目标列表失败
     *
     * @param e
     */
    void deleteMyTargetFailed(CommonException e);
}
