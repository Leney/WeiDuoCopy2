package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.BannerInfo;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.ClassifyInfo;
import com.zhongke.weiduo.mvp.model.entity.FindClassifyBean;
import com.zhongke.weiduo.mvp.model.entity.FindRecommendBean;

import java.util.List;

/**
 * Created by ${llj} on 2017/6/27.
 */

public interface FindFragmentContract extends BaseView{
    /**
     * 获取Banner数据成功
     */
    void getBannerBitmapSuccess(CarouselBitmapBean bean);

    void getBannerBitmapFailed(CommonException e);

    //获取活动类别
    void getActivityTypeSuccess(FindClassifyBean bean);

    void getActivityTypeFailed(CommonException e);

    //获取推荐列表成功
    void getRecommendSuccess(FindRecommendBean bean);

    void getRecommendFailed(CommonException e);

    //点赞成功
    void clickLikeSuccess();

    void clickLikeFailed();
}
