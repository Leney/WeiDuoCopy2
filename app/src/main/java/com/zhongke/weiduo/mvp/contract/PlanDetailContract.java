package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.PlanDetailBean2;
import com.zhongke.weiduo.mvp.model.entity.RecommendActiveBean;

/**
 * 激活设备
 * Created by llj on 2017/6/20.
 */

public interface PlanDetailContract extends BaseView {
    /**
     * 获取计划详情数据成功
     *
     * @param detailBean 计划详情
     */
    void getDetailSuccess(PlanDetailBean2 detailBean);

    /**
     * 获取计划详情数据失败
     */
    void getDetailFailed(CommonException e);

    /**
     * 获取推荐计划列表成功
     * @param recommendActiveBean
     */
    void getRecommendListSuccess(RecommendActiveBean recommendActiveBean);

    /**
     * 获取推荐计划列表失败
     * @param e
     */
    void getRecommendListFailed(CommonException e);

    /**
     * 添加计划成功
     */
    void addPlanSuccess();

    /**
     * 添加计划失败
     * @param e
     */
    void addPlanFailed(CommonException e);
}
