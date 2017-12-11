package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.WeiDuoHomeBean;

import java.util.List;

/**
 * Created by ${llj} on 2017/6/27.
 */

public interface GrowUpFragmentContract extends BaseView {
    /**
     * 获取成长首页数据成功
     *
     * @param systemList 体系列表
     * @param schemeList 规划列表
     * @param planList   计划列表
     * @param activeList 活动列表
     */
    void getDataSuccess(List<WeiDuoHomeBean.StructureBean> systemList, List<WeiDuoHomeBean.BPlanBean> schemeList, List<WeiDuoHomeBean.SPlanBean> planList, List<WeiDuoHomeBean.ActionBean> activeList);

    /**
     * 获取成长首页数据失败
     *
     * @param errorCode
     * @param msg
     */
    void getDataFailed(int errorCode, String msg);

    /**
     * 显示轮播图
     *
     * @param carouselBitmapBean
     */
    void showBanner(CarouselBitmapBean carouselBitmapBean);

    /**
     * 显示默认轮播图
     */
    void showDefaultCarousel();
//    /**
//     * 	获得愿望、目标、规划、计划、活动日程统计
//     * @param bPlanCount
//     * @param sPlanCount
//     * @param wishCount
//     * @param scheduleCount
//     * @param targetCount
//     */
//    void updatedUserAcount(int bPlanCount,int sPlanCount,int wishCount,int scheduleCount,int targetCount);
}
