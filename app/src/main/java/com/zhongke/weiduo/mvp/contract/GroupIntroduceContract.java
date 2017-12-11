package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyOrGroupDetail;

/**
 * Created by hyx on 2017/10/10.
 */

public interface GroupIntroduceContract extends BaseView {
    /**
     * 加入群组成功
     */
    void joinGroupSuccess();

    /**
     * 显示群组信息
     */
    void showGroupDetail(NewFamilyOrGroupDetail newFamilyOrGroupDetail);

    /**
     * 显示错误视图
     */
    void showErrorViews();
}
