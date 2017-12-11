package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.RecommendSchemeBean;
import com.zhongke.weiduo.mvp.model.entity.TargetDetailBean2;

/**
 *
 * Created by llj on 2017/6/20.
 */

public interface TargetDetailContract extends BaseView {
    /**
     * 获取目标详情数据成功
     * @param detailBean 目标详情
     */
    void getDetailSuccess(TargetDetailBean2 detailBean);
    /** 获取目标详情数据失败*/
    void getDetailFailed(CommonException e);

    /**
     * 获取推荐规划列表成功
     * @param recommendSchemeBean
     */
    void getRecommendListSuccess(RecommendSchemeBean recommendSchemeBean);

    /**
     * 获取推荐规划列表失败
     * @param e
     */
    void getRecommendListFailed(CommonException e);

    /**
     * 添加目标成功
     */
    void addTargetSuccess();

    /**
     * 添加目标失败
     * @param e
     */
    void addTargetFailed(CommonException e);
}
