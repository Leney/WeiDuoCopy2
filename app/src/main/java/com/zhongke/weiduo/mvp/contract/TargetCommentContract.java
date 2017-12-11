package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.CommentBean;

import java.util.List;

/**
 *
 * Created by llj on 2017/6/20.
 */

public interface TargetCommentContract extends BaseView {
    /** 获取相关数据成功*/
    void getCommentListSuccess(List<CommentBean> list);
    /** 获取相关数据失败*/
    void getCommentListFailed(int errorCode, String msg);
}
