package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.model.entity.ActivityFlowBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityListDetailBean;

/**
 * Created by ${xingen} on 2017/9/25.
 */

public interface ActivityDetailContract {

   String getActionId();
   void showData(ActivityListDetailBean detailBean);
   void showError(CommonException exception);
   //获取活动流程成功
   void getActivityFlowListSuccess(ActivityFlowBean bean);

   void getActivityFlowListFailed(CommonException e);
   //检测用户是否绑定成功
   void getDeviceCountSuccess();
   //检测用户是否绑定失败
   void getDeviceCountFailed(CommonException  e);
}
