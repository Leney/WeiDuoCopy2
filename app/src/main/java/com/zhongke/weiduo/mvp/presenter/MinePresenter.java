package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MineContract;

/**
 * Created by Karma on 2017/9/25.
 */

public class MinePresenter extends BasePresenter {

    private Context context;
    private MineContract contract;
    public MinePresenter(Context context, MineContract contract) {
        this.context = context;
        this.contract = contract;
    }

}
