package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.ExecutePeopleBean;

/**
 * Created by ${tanlei} on 2017/9/27.
 */

public interface AddAimsPlanningContract extends BaseView{
    /**
     * 添加自定义目标成功
     */
    void addSelfTargetSuccess();

    /**
     * 添加自定义目标失败
     * @param e
     */
    void addSelfTargetFailed(CommonException e);
    /**
     * 添加自定义规划成功
     */
    void addSelfSchemeSuccess();

    /**
     * 添加自定义规划失败
     * @param e
     */
    void addSelfSchemeFailed(CommonException e);
    /**
     * 添加自定义计划成功
     */
    void addSelfPlanSuccess();

    /**
     * 添加自定义计划失败
     * @param e
     */
    void addSelfPlanFailed(CommonException e);

    //获取用户和用户孩子列表
    void getUserAndChildSuccess(ExecutePeopleBean bean);

    void getUserAndChildFailed(CommonException e);
}
