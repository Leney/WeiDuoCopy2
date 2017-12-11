package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.RecommendPlanBean;
import com.zhongke.weiduo.mvp.model.entity.SchemeDetailBean2;

/**
 * Created by llj on 2017/6/20.
 */

public interface SchemeDetailContract extends BaseView {
    /**
     * 获取规划详情数据成功
     *
     * @param detailBean    规划详情
     */
    void getDetailSuccess(SchemeDetailBean2 detailBean);

    /**
     * 获取规划详情数据失败
     */
    void getDetailFailed(CommonException e);

    /**
     * 获取推荐计划列表成功
     * @param recommendPlanBean
     */
    void getRecommendListSuccess(RecommendPlanBean recommendPlanBean);

    /**
     * 获取推荐计划列表失败
     * @param e
     */
    void getRecommendListFailed(CommonException e);

    /**
     * 添加规划成功
     */
    void addSchemeSuccess();

    /**
     * 添加规划失败
     * @param e
     */
    void addSchemeFailed(CommonException e);
}
