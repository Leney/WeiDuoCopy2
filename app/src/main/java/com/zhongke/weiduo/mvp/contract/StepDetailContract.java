package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.StepDetailBean;

/**
 * 步骤详情
 * Created by llj on 2017/6/26.
 */

public interface StepDetailContract extends BaseView {
    /** 获取步骤详情数据成功*/
    void getStepDetailSuccess(StepDetailBean detailBean);
    /** 获取步骤详情失败*/
    void getStepDetailFailed(int errorCode,String msg);
}
