package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActivityCommentContract;
import com.zhongke.weiduo.mvp.model.entity.ActivityBehaviorBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${xingen} on 2017/6/27.
 *
 */

public class ActivityCommentPresenter extends BasePresenter {

    private ActivityCommentContract view;
    public ActivityCommentPresenter(ActivityCommentContract view){
        this.view=view;
    }

    public void loadData(String actionId,String flowId){

        Map<String,String> map =new HashMap<>();
        map.put("actionId",actionId);
        map.put("flowId",flowId);
        Subscription subscription = retrofitClient.getActivityBehavior(map, new ResponseResultListener<ActivityBehaviorBean>() {
            @Override
            public void success(ActivityBehaviorBean bean) {
                LogUtil.e("bean--"+bean.getBehavior().toString());
                view.loadDataSuccess(bean);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure---"+e.getErrorMsg());
                view.loadDataFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

    public void CommentActivityBehavior(String userId,String actionId,String flowId,String behaviorIds,String fNodeIds,
                                        String nValues,String runTimes,String doObjects,String evaluate) {

        Map<String,String> map = new HashMap<>();
        map.put("userId",userId);
        map.put("actionId",actionId);
        map.put("flowId",flowId);
        map.put("behaviorIds",behaviorIds);
        map.put("fNodeIds",fNodeIds);
        map.put("nValues",nValues);
        map.put("runTimes",runTimes);
        map.put("doObjects",doObjects);
        map.put("evaluate",evaluate);
        Subscription subscription = retrofitClient.CommentActivityBehavior(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                LogUtil.e("success");
                view.CommentActivitySuccess();
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("评论失败 failure---"+e.getErrorMsg());
                view.CommentActivityFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

}
