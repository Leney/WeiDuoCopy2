package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.model.entity.ActivityBehaviorBean;

/**
 * Created by ${xingen} on 2017/6/27.
 *
 */

public interface ActivityCommentContract {
    /**
     * 设置奖励列表
     */
    void setRewards();

    void loadDataSuccess(ActivityBehaviorBean bean);

    void loadDataFailed(CommonException e);

    //评论成功
    void CommentActivitySuccess();

    void CommentActivityFailed(CommonException e);

}
