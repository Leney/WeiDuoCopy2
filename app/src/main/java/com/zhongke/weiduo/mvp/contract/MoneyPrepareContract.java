package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.MoneyManageBean;

/**
 * 资金筹备
 * Created by llj on 2017/6/27.
 */

public interface MoneyPrepareContract extends BaseView {
    /** 获取资金管理信息成功*/
    void getMoneyManageInfoSuccess(MoneyManageBean moneyManageBean);
    /** 获取自己管理信息失败*/
    void getMoneyManageInfoFailed(int errorCode,String msg);
    /** 修改责任人成功*/
    void modifyDutyChargeSuccess(MoneyManageBean.RoleListBean roleListBean);
    /** 修改责任人失败*/
    void modifyDutyChargeFailed(int errorCode,String msg);
}
