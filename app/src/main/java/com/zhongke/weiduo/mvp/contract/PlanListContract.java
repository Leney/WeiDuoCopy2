package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.PlanBean2;

/**
 * Created by ${tanlei} on 2017/9/18.
 */

public interface PlanListContract extends BaseView {
    void getListSuccess(PlanBean2 list);
    void getListFailed(CommonException e);
}
