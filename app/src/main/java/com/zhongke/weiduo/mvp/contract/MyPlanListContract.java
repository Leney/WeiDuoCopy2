package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.MyPlanBean;

/**
 * Created by ${tanlei} on 2017/9/27.
 */

public interface MyPlanListContract extends BaseView {
    /**
     * 获取我的计划列表成功
     *
     * @param bean
     */
    void getMyPlanListSuccess(MyPlanBean bean);

    /**
     * 获取我的目标列表失败
     *
     * @param e
     */
    void getMyPlanListFailed(CommonException e);

    /**
     * 删除我的计划成功
     */
    void deleteMyPlanSuccess();

    /**
     * 删除我的计划失败
     *
     * @param e
     */
    void deleteMyPlanFailed(CommonException e);
}
