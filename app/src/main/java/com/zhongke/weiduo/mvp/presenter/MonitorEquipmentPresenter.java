package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MonitorEquipmentContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.MonitorEquipmentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/26.
 */

public class MonitorEquipmentPresenter extends BasePresenter {
    private static final String TAG = "MonitorEquipmentPresenter";
    private MonitorEquipmentContract monitorEquipmentContract;
    private DataManager dataManager;

    public MonitorEquipmentPresenter(MonitorEquipmentContract monitorEquipmentContract, DataManager dataManager) {
        this.monitorEquipmentContract = monitorEquipmentContract;
        this.dataManager = dataManager;
    }

    public void showMonitorEquipment() {
        List<MonitorEquipmentBean> monitorEquipmentBeanList = new ArrayList<MonitorEquipmentBean>();
        monitorEquipmentBeanList.add(new MonitorEquipmentBean("麦克风","",null,false));
        monitorEquipmentBeanList.add(new MonitorEquipmentBean("摄影机","水平：10°，垂直80°","23：30：59",true));
        monitorEquipmentBeanList.add(new MonitorEquipmentBean("投影仪","水平：10°，垂直80°","23：30：59",true));
        monitorEquipmentBeanList.add(new MonitorEquipmentBean("照相机","水平：10°，垂直80°","23：30：59",false));
        monitorEquipmentBeanList.add(new MonitorEquipmentBean("摄像头","水平：10°，垂直80°","23：30：59",true));
        monitorEquipmentContract.showMonitorEquipmentData(monitorEquipmentBeanList);
    }
}
