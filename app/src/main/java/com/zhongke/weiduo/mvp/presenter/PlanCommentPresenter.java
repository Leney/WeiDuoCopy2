package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.PlanCommentContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.CommentBean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by llj on 2017/6/20.
 */

public class PlanCommentPresenter extends BasePresenter {
    private static final String TAG = "PlanCommentPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private PlanCommentContract mContract;

    public PlanCommentPresenter(Context context, DataManager dataManager, PlanCommentContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 获取相关数据
     */
    public void getCommentList(int id){

        List<CommentBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CommentBean bean = new CommentBean();
            bean.setUserId(i);
            bean.setUserIcon("http://v1.qzone.cc/avatar/201409/24/19/58/5422b1ff86ed0232.jpg!200x200.jpg");
            bean.setUserName("用户名称("+i+")");
            bean.setTime("2017-09-18 17:53");
            bean.setUserLevel(5);
            bean.setDetail("("+i+")具体评论描述详情,具体评论描述详情具体评论描述详情具体评论描述详情具体评论描述详情具体评论描述详情");
            list.add(bean);
        }
        mContract.getCommentListSuccess(list);
    }

}
