package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ExpertContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.NewExpertBean;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/9/4.
 */

public class ExpertPresenter extends BasePresenter {
    public static final String TAG = "ExpertPresenter";
    private ExpertContract expertContract;
    private DataManager dataManager;
    private int index;
    public static final int REFRESH_STATUS = 0;
    public static final int LOAD_STATUS = 1;

    public ExpertPresenter(ExpertContract expertContract, DataManager dataManager) {
        this.expertContract = expertContract;
        this.dataManager = dataManager;
    }

    /**
     * 请求获取专家列表
     */
    public void showExpertList(int status) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("token", "jrimhwd41509508199084","pageIndex", index + "");
        Subscription subscription = retrofitClient.getExperList(hashMap, new ResponseResultListener<NewExpertBean>() {
            @Override
            public void success(NewExpertBean newExpertBean) {
                expertContract.showExpertList(newExpertBean.getRecords(),status);
            }

            @Override
            public void failure(CommonException e) {
                expertContract.requestFailure();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 请求专家界面的轮播图
     */
    public void requestCarousel() {
        HashMap hashMap = RequestParameterUtils.setRequestPar("token", "jrimhwd41509508199084", "bannerType", "3");
        Subscription subscription = retrofitClient.getBannerBitmap(hashMap, new ResponseResultListener<CarouselBitmapBean>() {
            @Override
            public void success(CarouselBitmapBean carouselBitmapBean) {
                expertContract.showCarousel(carouselBitmapBean);
            }

            @Override
            public void failure(CommonException e) {
//                expertContract.showDefaultCarousel();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 下拉刷新的调用
     */
    public void refreshList() {
        index = 1;
        showExpertList(REFRESH_STATUS);
    }

    /**
     * 上啦加载的调用
     */
    public void loadList() {
        index++;
        showExpertList(LOAD_STATUS);
    }
}
