package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.SearchFriendResultBean;

/**
 * Created by llj on 2017/11/16.
 */

public interface SearchFriendResultContract extends BaseView {
    /**
     * 搜索好友成功
     *
     * @param searchName 搜索关键字
     * @param bean       返回搜索结果的实体
     */
    void searchFriendListSuccess(String searchName, SearchFriendResultBean bean);

    /**
     * 搜索好友失败
     *
     * @param searchName 搜索的关键字
     * @param e
     */
    void searchFriendListFailed(String searchName, CommonException e);
}
