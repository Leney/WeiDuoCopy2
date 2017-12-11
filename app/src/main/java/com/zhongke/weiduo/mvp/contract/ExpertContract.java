package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.NewExpertBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/4.
 */

public interface ExpertContract extends BaseView {
    /**
     * 显示专家列表
     *
     * @param list
     */
    void showExpertList(List<NewExpertBean.RecordsBean> list,int status);

    /**
     * 获取数据失败
     */
    void requestFailure();

    /**
     * 显示专家界面轮播图
     *
     * @param carouselBitmapBean
     */
    void showCarousel(CarouselBitmapBean carouselBitmapBean);

}
