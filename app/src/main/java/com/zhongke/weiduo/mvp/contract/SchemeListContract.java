package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.SchemeBean2;

/**
 * Created by ${tanlei} on 2017/9/18.
 */

public interface SchemeListContract extends BaseView {
    void getListSuccess(SchemeBean2 bean);
    void getListFailed(CommonException e);
}
