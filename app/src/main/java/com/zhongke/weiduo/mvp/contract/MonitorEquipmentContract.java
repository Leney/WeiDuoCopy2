package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.MonitorEquipmentBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/26.
 */

public interface MonitorEquipmentContract extends BaseView {
    /**
     * 显示监控记录的数据
    */
    void showMonitorEquipmentData(List<MonitorEquipmentBean> monitorEquipmentBeans);
    /**
     * 显示ProgressDialog
     */
    void showProgressDialog();
    /**
     * 影藏ProgressDialog
     */
    void hideProgressDialog();
}
