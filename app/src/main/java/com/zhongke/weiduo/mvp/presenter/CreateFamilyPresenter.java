package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CreateFamilyContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.FamilyInfo;

/**
 * 创建家庭
 * Created by llj on 2017/6/21.
 */
public class CreateFamilyPresenter extends BasePresenter {
    private static final String TAG = "CreateFamilyPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private CreateFamilyContract mContract;

    public CreateFamilyPresenter(Context context, DataManager dataManager, CreateFamilyContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 创建家庭方法
     */
    public void createFamily(){

        FamilyInfo familyInfo = new FamilyInfo();
        familyInfo.setName("李家");
        familyInfo.setId(154245);
        mContract.createSuccess(familyInfo);
    }
}
