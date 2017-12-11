package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.GrowUpFragmentContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.WeiDuoHomeBean;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

import rx.Subscription;

/**
 * 发现presenter
 * Created by ${llj} on 2017/6/27.
 */

public class GrowUpFragmentPresenter extends BasePresenter {
    private GrowUpFragmentContract mContract;
    private DataManager dataManager;

    public GrowUpFragmentPresenter(GrowUpFragmentContract contract, DataManager dataManager) {
        this.mContract = contract;
        this.dataManager = dataManager;
    }

    /**
     * 获取成长首页数据
     */
    public void getData() {
        executeTask();
    }


//    /**
//     * 获取banner数据
//     *
//     * @return
//     */
//    private List<BannerInfo> getBinner() {
//
//        List<BannerInfo> bannerInfos = new ArrayList<>();
//        BannerInfo bannerInfo = new BannerInfo();
//        bannerInfo.id = 100;
//        bannerInfo.type = 0;
//        bannerInfo.icon = "http://pic2.ooopic.com/13/19/36/88b6OOOPIC4d.jpg";
//        bannerInfo.describle = "第一条banner";
//        bannerInfo.value = "http://www.dddf/sfsdf.html";
//
//        BannerInfo bannerInfo1 = new BannerInfo();
//        bannerInfo1.id = 101;
//        bannerInfo1.type = 1;
//        bannerInfo1.icon = "http://img0.imgtn.bdimg.com/it/u=2126911375,3166619993&fm=214&gp=0.jpg";
//        bannerInfo1.describle = "第二条banner";
//        bannerInfo1.value = "http://www.dddf/sfsdf.html";
//
//        BannerInfo bannerInfo2 = new BannerInfo();
//        bannerInfo2.id = 102;
//        bannerInfo2.type = 1;
//        bannerInfo2.icon = "http://pic.qiantucdn.com/58pic/19/70/32/44D58PICuQw_1024.jpg";
//        bannerInfo2.describle = "第三条banner";
//        bannerInfo2.value = "http://www.dddf/sfsdf.html";
//
//        bannerInfos.add(bannerInfo);
//        bannerInfos.add(bannerInfo1);
//        bannerInfos.add(bannerInfo2);
//        return bannerInfos;
//    }

    /**
     * 获取轮播图
     */
    public void getBannerBitmap() {
        HashMap hashMap = RequestParameterUtils.setRequestPar("bannerType", "1");
        Subscription subscription = retrofitClient.getBannerBitmap(hashMap, new ResponseResultListener<CarouselBitmapBean>() {
            @Override
            public void success(CarouselBitmapBean carouselBitmapBean) {
                mContract.showBanner(carouselBitmapBean);
            }

            @Override
            public void failure(CommonException e) {
                mContract.showDefaultCarousel();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 执行网络任务
     */
    private void executeTask() {
        Subscription subscription = retrofitClient.getWeiDuoHome(new ResponseResultListener<WeiDuoHomeBean>() {
            @Override
            public void success(WeiDuoHomeBean weiDuoHomeBean) {
                mContract.getDataSuccess(weiDuoHomeBean.getStructure(), weiDuoHomeBean.getB_plan(), weiDuoHomeBean.getS_plan(), weiDuoHomeBean.getAction());
            }

            @Override
            public void failure(CommonException e) {
                UIUtils.showToast(e.getErrorMsg());
                mContract.getDataFailed(e.getCode(), e.getErrorMsg());
            }
        });
        this.compositeSubscription.add(subscription);

//        Subscription subscription1 = retrofitClient.getUserAcountSize(new ResponseResultListener<UserAcountSizeBean>() {
//            @Override
//            public void success(UserAcountSizeBean userAcountSizeBean) {
//                UserAcountSizeBean.CountBean countBean = userAcountSizeBean.getCount();
//                if (countBean != null) {
//                    UserInfoManager.getInstance().getUserInfo().wishNum = countBean.getWishCount();
//                    UserInfoManager.getInstance().getUserInfo().targetNum = countBean.getTargetCount();
//                    UserInfoManager.getInstance().getUserInfo().schemeNum = countBean.getBPlanCount();
//                    UserInfoManager.getInstance().getUserInfo().planNum = countBean.getSPlanCount();
//                    UserInfoManager.getInstance().getUserInfo().ActiveNum = countBean.getScheduleCount();
//                    mContract.updatedUserAcount(countBean.getBPlanCount(), countBean.getSPlanCount(), countBean.getWishCount(), countBean.getScheduleCount(), countBean.getTargetCount());
//                }
//            }
//            @Override
//            public void failure(CommonException e) {
//                UIUtils.showToast(e.getErrorMsg());
//            }
//        });
//        this.compositeSubscription.add(subscription1);
    }
}
