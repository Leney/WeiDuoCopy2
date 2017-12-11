package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.ToolDetailBean;

/**
 * 活动用具清单
 * Created by llj on 2017/6/28.
 */

public interface ActToolsContract extends BaseView {
    /**
     * 获取工具清单详情成功
     */
    void getToolListSuccess(ToolDetailBean toolDetailBean);

    /**
     * 获取工具清单详情失败
     * @param errorCode
     * @param msg
     */
    void getToolListFailed(int errorCode,String msg);

    /**
     * 更改责任人成功
     * @param roleListBean
     */
    void modifyDutyChargeSuccess(ToolDetailBean.RoleListBean roleListBean);

    /**
     * 更改责任人失败
     * @param errorCode
     * @param msg
     */
    void modifyDutyChargeFailed(int errorCode,String msg);
}
