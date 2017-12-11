package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.MySchemeBean;

/**
 * Created by ${tanlei} on 2017/9/27.
 */

public interface MySchemeListContract extends BaseView {
    /**
     * 获取我的规划列表成功
     *
     * @param bean
     */
    void getMySchemeListSuccess(MySchemeBean bean);

    /**
     * 获取我的目标列表失败
     *
     * @param e
     */
    void getMySchemeListFailed(CommonException e);

    /**
     * 删除我的规划成功
     */
    void deleteMySchemeSuccess();

    /**
     * 删除我的规划失败
     *
     * @param e
     */
    void deleteMySchemeFailed(CommonException e);
}
