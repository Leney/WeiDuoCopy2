package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActToolsContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.ToolDetailBean;

import java.util.ArrayList;

/**
 * 活动清单
 * Created by llj on 2017/6/28.
 */

public class ActToolsPresenter extends BasePresenter {
    private static final String TAG = "ActivatePresenter";
    private Context mContext;
    private DataManager mDataManager;
    private ActToolsContract mContract;

    public ActToolsPresenter(Context context, DataManager dataManager, ActToolsContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 获取工具清单详情
     */
    public void getToolListDetail() {

        ToolDetailBean toolDetailBean = new ToolDetailBean();
        toolDetailBean.setIsManager(0);
        toolDetailBean.setPrinId(10021);
        toolDetailBean.setPrinName("妈妈");
        toolDetailBean.setToolList(new ArrayList<ToolDetailBean.ToolListBean>());
        ToolDetailBean.ToolListBean toolListBean = new ToolDetailBean.ToolListBean();
        toolListBean.setId(10001);
        toolListBean.setName("口语教材");
        toolListBean.setIsOk(0);
        ToolDetailBean.ToolListBean toolListBean1 = new ToolDetailBean.ToolListBean();
        toolListBean1.setId(10002);
        toolListBean1.setName("耳麦");
        toolListBean1.setIsOk(0);
        ToolDetailBean.ToolListBean toolListBean2 = new ToolDetailBean.ToolListBean();
        toolListBean2.setId(10003);
        toolListBean2.setName("门票");
        toolListBean2.setIsOk(1);
        toolDetailBean.getToolList().add(toolListBean);
        toolDetailBean.getToolList().add(toolListBean1);
        toolDetailBean.getToolList().add(toolListBean2);


        toolDetailBean.setRoleList(new ArrayList<ToolDetailBean.RoleListBean>());
        ToolDetailBean.RoleListBean roleListBean = new ToolDetailBean.RoleListBean();
        roleListBean.setId(100021);
        roleListBean.setName("妈妈");
        ToolDetailBean.RoleListBean roleListBean1 = new ToolDetailBean.RoleListBean();
        roleListBean1.setId(100022);
        roleListBean1.setName("爸爸");
        ToolDetailBean.RoleListBean roleListBean2 = new ToolDetailBean.RoleListBean();
        roleListBean2.setId(100023);
        roleListBean2.setName("爷爷");
        ToolDetailBean.RoleListBean roleListBean3 = new ToolDetailBean.RoleListBean();
        roleListBean3.setId(100024);
        roleListBean3.setName("奶奶");
        toolDetailBean.getRoleList().add(roleListBean);
        toolDetailBean.getRoleList().add(roleListBean1);
        toolDetailBean.getRoleList().add(roleListBean2);
        toolDetailBean.getRoleList().add(roleListBean3);


        mContract.getToolListSuccess(toolDetailBean);
    }


    /**
     * 更改责任人
     *
     * @param actId
     * @param userId
     * @param userName
     */
    public void modifyDutyCharge(int actId, int userId, String userName) {
        // TODO 请求网络更改设置责任人
        ToolDetailBean.RoleListBean roleListBean = new ToolDetailBean.RoleListBean();
        roleListBean.setId(userId);
        roleListBean.setName(userName);
        mContract.modifyDutyChargeSuccess(roleListBean);
    }
}
