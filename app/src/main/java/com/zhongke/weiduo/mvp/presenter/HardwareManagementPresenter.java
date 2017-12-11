package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.HardwareManagementContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;

/**
 * Created by ${tanlei} on 2017/9/20.
 */

public class HardwareManagementPresenter extends BasePresenter {
    private HardwareManagementContract hardwareManagementContract;
    private DataManager manager;

    public HardwareManagementPresenter(HardwareManagementContract hardwareManagementContract, DataManager manager) {
        this.hardwareManagementContract = hardwareManagementContract;
        this.manager = manager;
    }
}
