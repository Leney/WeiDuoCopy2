package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseListResult;

/**
 * Created by ${tanlei} on 2017/6/27.
 */

public interface SchoolFragmentContract extends BaseView {
    /**
     * 显示推荐课程列表
     *
     * @param bean 从接口获取到的课程列表对象
     */
    void showCourseSuccess(CourseListResult bean, int status);

    /**
     * 显示轮播图
     *
     * @param carouselBitmapBean 从接口获取到的轮播图对象
     */
    void showBanner(CarouselBitmapBean carouselBitmapBean);

    /**
     * 请求数据失败时
     */
    void requestError();

    /**
     * 显示默认轮播图
     */
    void showDefaultCarousel();
}
