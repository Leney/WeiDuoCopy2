package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.NewGroupMemberBean;

/**
 * Created by ${tanlei} on 2017/8/16.
 */

public interface GroupDataContract extends BaseView {
    /**
     * 从群组/家庭移除成员成功
     */
    void dropOutGroupSuccess();

    /**
     * 显示群组或者家庭成员列表
     *
     * @param newGroupMemberBean
     */
    void showGroupMemberList(NewGroupMemberBean newGroupMemberBean);

    /**
     * 退出群组成功
     */
    void quitGroupSuccess();

    /**
     * 退出群组失败
     */
    void quitGroupFailure(String str);
}
