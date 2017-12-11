package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.model.entity.NewPersonalData;

/**
 * Created by hyx on 2017/9/25.
 */

public interface PersonalContract {
    /**
     * 显示个人资料
     * @param newPersonalData
     */
    void showPersonal(NewPersonalData newPersonalData);

    /**
     * 显示失败视图
     */
    void showFailure();

    /**
     * 修改个人资料后保存成功的回调
     */
    void showSaveSuccess();

    void showSaveSuccess(int state);

    /**
     * 显示保存失败的toast
     */
    void showSaveFailure(CommonException e);
}
