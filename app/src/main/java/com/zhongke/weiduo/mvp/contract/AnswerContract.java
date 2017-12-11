package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.JoinMember;
import com.zhongke.weiduo.mvp.model.entity.Paper;

/**
 * Created by llj on 2017/8/31.
 */

public interface AnswerContract extends BaseView {
    /** 获取题库数据成功*/
    void getTopicListSuccess(Paper paper);
    /** 获取相关数据失败*/
    void getTopicListFailed(CommonException e);
    /** 获取参加活动人员列表成功(包含观战人员)*/
    void getJoinMemberSuccess(JoinMember member);
    /** 获取参加活动人员列表失败*/
    void getJoinMemberFailed(CommonException e);
}
