package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActMembersContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.MemberInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动当前参与人员列表
 * Created by llj on 2017/6/27.
 */

public class ActMembersPresenter extends BasePresenter {
    private static final String TAG = "ActMembersPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private ActMembersContract mContract;

    public ActMembersPresenter(Context context, DataManager dataManager, ActMembersContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 获取当前参与人员列表数据
     */
    public void getCurMembers() {

        List<MemberInfo> memberInfos = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setId(i);
            memberInfo.setName("第" + i + "个成员");
            memberInfo.setIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498552696322&di=b69cce64ff2bb1f1d61e51d5661b2763&imgtype=0&src=http%3A%2F%2Fwww.yisongjixie.com%2Fimg%2F360418.jpg");
            memberInfos.add(memberInfo);
        }

        mContract.getMembersSuccess(memberInfos);
    }
}
