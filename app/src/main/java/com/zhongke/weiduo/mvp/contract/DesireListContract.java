package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.model.entity.DesireListBean2;

/**
 * Created by ${xingen} on 2017/9/21.
 */

public interface DesireListContract {

    //获取孩子愿望成功
    void getChildWishSuccess(DesireListBean2 Bean);

    void getChildWishFailed(CommonException e);
    //帮助孩子实现愿望
    void helpRealizeWishSuccess();

    void helpRealizeWishFailed(CommonException e);

    //直接推荐成功
    void directlyRecommendSuccess();

    void directlyRecommendFailed(CommonException e);
}
