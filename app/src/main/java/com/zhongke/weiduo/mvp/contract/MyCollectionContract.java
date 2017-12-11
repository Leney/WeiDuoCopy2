package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.UserCollectItem;
import com.zhongke.weiduo.mvp.model.entity.UserCollectListBean;

import java.util.List;

/**
 * Created by Karma on 2017/9/19.
 */

public interface MyCollectionContract extends BaseView {

    //获取收藏列表成功
    void getUserCollectionSuccess(UserCollectListBean bean,List<UserCollectItem> collects);

    void getUserCollectionFailed(CommonException e);

}
