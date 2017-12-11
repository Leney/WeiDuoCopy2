package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.ExecutePeopleBean;

/**
 * Created by Karma on 2017/9/18.
 */

public interface AppendScheduleContract extends BaseView {

    //添加成功
    void addScheduleSuccess();

    void addScheduleFailed(CommonException e);

    void getUserAndChildSuccess(ExecutePeopleBean executePeopleBean);

    void getUserAndChildFailed(CommonException e);
}
