package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.PrizeAddContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;

/**
 * Created by ${tanlei} on 2017/6/26.
 */

public class PrizeAddPresenter extends BasePresenter{
    private static final String TAG = "PrizeAddPresenter";
    private PrizeAddContract prizeAddContract;
    private DataManager dataManager;

    public PrizeAddPresenter(PrizeAddContract prizeAddContract, DataManager dataManager) {
        this.prizeAddContract = prizeAddContract;
        this.dataManager = dataManager;
    }
}
