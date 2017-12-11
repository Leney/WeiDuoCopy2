package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AddMemberContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;

/**
 * 添加亲友
 * Created by llj on 2017/6/21.
 */

public class AddMemberPresenter extends BasePresenter {
    private static final String TAG = "AddMemberPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private AddMemberContract mContract;

    public AddMemberPresenter(Context context, DataManager dataManager, AddMemberContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 添加亲友界面
     */
    public void addMembers(){
        // 伪代码
        mContract.addMemberSuccess();
    }
}
