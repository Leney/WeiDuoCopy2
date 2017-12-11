package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.NewMechanismBean;
import com.zhongke.weiduo.mvp.ui.activity.OrganizationListActivity;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by Karma on 2017/9/4.
 * 类描述：
 */

public class OrganizationListAtPresent extends BasePresenter {

    private OrganizationListActivity activity;

    public OrganizationListAtPresent(BaseMvpActivity activity) {
        this.activity = (OrganizationListActivity) activity;
    }

    /**
     * 请求获取机构列表
     */
    public void getMechanismList() {
        HashMap map = RequestParameterUtils.setRequestPar();
        Subscription subscription = retrofitClient.getMechanismList(map, new ResponseResultListener<NewMechanismBean>() {
            @Override
            public void success(NewMechanismBean newMechanismBean) {
                activity.showMechanismList(newMechanismBean.getRecords());
            }

            @Override
            public void failure(CommonException e) {
            }
        });
        compositeSubscription.add(subscription);
    }

    public void getOrganizationCarousel() {
        HashMap map = RequestParameterUtils.setRequestPar("bannerType", "5");
        Subscription subscription = retrofitClient.getBannerBitmap(map, new ResponseResultListener<CarouselBitmapBean>() {
            @Override
            public void success(CarouselBitmapBean carouselBitmapBean) {
                activity.showOrganizationCarousel(carouselBitmapBean);
            }

            @Override
            public void failure(CommonException e) {

            }
        });
        compositeSubscription.add(subscription);
    }
}
