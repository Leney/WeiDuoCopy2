package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyOrGroupDetail;

/**
 * 家庭资料界面
 * Created by llj on 2017/6/23.
 */

public interface FamilyDetailContract extends BaseView {
    /**
     * 获取家庭详情数据成功
     */
    void getDetailSuccess(NewFamilyOrGroupDetail newFamilyOrGroupDetail);

    /**
     * 获取家庭详情数据失败
     */
    void getDetailFailed();

    /**
     * 添加为好友家庭成功
     */
    void addFriendFamilySuccess();

}
