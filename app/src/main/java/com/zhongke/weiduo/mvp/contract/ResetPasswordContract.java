package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;

/**
 * Created by hyx on 2017/10/27.
 */

public interface ResetPasswordContract extends BaseView {
    /**
     * 修改成功
     */
    void modifySuccess();

    /**
     * 修改失败
     */
    void modifyFailure(CommonException e);
}
