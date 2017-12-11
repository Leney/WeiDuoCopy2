package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.ActivityListBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/26.
 */

public interface MovableListContract extends BaseView{
    int MODE_REFRESH=1;
    int MODE_LOADE_MORE=2;
    void getActiveListSuccess(int mode,List<ActivityListBean.RecordsBean> list);
    void getActiveListFailed(int code,CommonException  msg);
}
