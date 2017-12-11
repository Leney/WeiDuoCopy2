package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.FindRecommendBean;

import java.util.List;

/**
 * 激活设备
 * Created by llj on 2017/6/20.
 */

public interface FindClassifyContract extends BaseView {
    /** 获取相关数据成功*/
    void getActListSuccess(FindRecommendBean bean);
    /** 获取相关数据失败*/
    void getActListFailed(CommonException e);

    void clickLikeSuccess();

    void clickLikeFailed();
}
