package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.MemberInfo;

import java.util.List;

/**
 * 活动当前参与人员列表
 * Created by llj on 2017/6/27.
 */

public interface ActMembersContract extends BaseView {
    /** 获取活动当前已参与人员列表成功*/
    void getMembersSuccess(List<MemberInfo> memberInfoList);
    /** 获取活动当前参与人员列表失败*/
    void getMembersFailed(int errorCode,String msg);
}
