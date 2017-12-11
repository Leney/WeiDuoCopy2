package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MoneyPrepareContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.MoneyManageBean;

import java.util.ArrayList;

/**
 * 资金筹备
 * Created by llj on 2017/6/27.
 */

public class MoneyPreparePresenter extends BasePresenter {
    private static final String TAG = "MoneyPreparePresenter";
    private Context mContext;
    private DataManager mDataManager;
    private MoneyPrepareContract mContract;

    public MoneyPreparePresenter(Context context, DataManager dataManager, MoneyPrepareContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }


    /**
     * 获取资金筹备信息
     */
    public void getMoneyManageInfo(){

        MoneyManageBean moneyManageBean = new MoneyManageBean();
        moneyManageBean.setIsManager(0);
        moneyManageBean.setMoney(185);
        moneyManageBean.setPrinId(10025);
        moneyManageBean.setPrinName("妈妈");
        moneyManageBean.setUnit("次");


        moneyManageBean.setRoleList(new ArrayList<MoneyManageBean.RoleListBean>());
        MoneyManageBean.RoleListBean roleListBean = new MoneyManageBean.RoleListBean();
        roleListBean.setId(10025);
        roleListBean.setName("妈妈");
        MoneyManageBean.RoleListBean roleListBean1 = new MoneyManageBean.RoleListBean();
        roleListBean1.setId(10026);
        roleListBean1.setName("爷爷");
        MoneyManageBean.RoleListBean roleListBean2 = new MoneyManageBean.RoleListBean();
        roleListBean2.setId(10027);
        roleListBean2.setName("奶奶");
        moneyManageBean.getRoleList().add(roleListBean);
        moneyManageBean.getRoleList().add(roleListBean1);
        moneyManageBean.getRoleList().add(roleListBean2);

        mContract.getMoneyManageInfoSuccess(moneyManageBean);
    }

    /**
     * 修改活动资金管理责任人
     *
     * userName可不传  现在传是为了好测试
     */
    public void modifyDutyCharge(int actId,int userId,String userName){
        // TODO 网络设置责任人

        MoneyManageBean.RoleListBean roleListBean = new MoneyManageBean.RoleListBean();
        roleListBean.setId(userId);
        roleListBean.setName(userName);
        mContract.modifyDutyChargeSuccess(roleListBean);
    }
}
