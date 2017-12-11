package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SchoolFragmentContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseListResult;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/6/27.
 */

public class SchoolPresenter extends BasePresenter {
    private SchoolFragmentContract schoolFragmentContract;
    private DataManager dataManager;
    private int index;
    public static final int REFRESH_STATUS = 0;
    public static final int LOAD_STATUS = 1;

    public SchoolPresenter(SchoolFragmentContract schoolFragmentContract, DataManager dataManager) {
        this.schoolFragmentContract = schoolFragmentContract;
        this.dataManager = dataManager;
    }

    /**
     * 获取课程列表
     */
    public void getCourseList(int status) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("pageIndex", index + "");
        Subscription subscription = retrofitClient.getCourseList(hashMap, new ResponseResultListener<CourseListResult>() {
            @Override
            public void success(CourseListResult bean) {
                schoolFragmentContract.showCourseSuccess(bean,status);
            }

            @Override
            public void failure(CommonException e) {
                schoolFragmentContract.requestError();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 获取轮播图
     */
    public void getBannerBitmap() {
        HashMap hashMap = RequestParameterUtils.setRequestPar("bannerType", "2");
        Subscription subscription = retrofitClient.getBannerBitmap(hashMap, new ResponseResultListener<CarouselBitmapBean>() {
            @Override
            public void success(CarouselBitmapBean carouselBitmapBean) {
                schoolFragmentContract.showBanner(carouselBitmapBean);
            }

            @Override
            public void failure(CommonException e) {
                schoolFragmentContract.showDefaultCarousel();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 下拉刷新的调用
     */
    public void refreshList() {
        index = 1;
        getCourseList(REFRESH_STATUS);
    }

    /**
     * 上啦加载的调用
     */
    public void loadList() {
        index++;
        getCourseList(LOAD_STATUS);
    }
}
