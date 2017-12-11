package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AddTeachContentContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;

/**
 * Created by ${tanlei} on 2017/6/26.
 */

public class AddTeachContentPresenter extends BasePresenter {
    private static final String TAG = "AddTeachContentPresenter";
    private AddTeachContentContract addTeachContentContract;
    private DataManager dataManager;

    public AddTeachContentPresenter(AddTeachContentContract addTeachContentContract, DataManager dataManager) {
        this.addTeachContentContract = addTeachContentContract;
        this.dataManager = dataManager;
    }

    /**
     * 添加教学内容成功
     */
    public void AddTeachContentSuccess() {
        addTeachContentContract.getAddPlayEquipment();
        addTeachContentContract.getAddPlayDate();
        addTeachContentContract.getAddPlayAngle();
        //需要new成一个对象
    }

    /**
     * 添加失败
     */
    public  void AddTeachContentFail(){

    }
}
