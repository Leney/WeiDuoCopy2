package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.SearchFamilyAndGroupResultBean;

/**
 * Created by llj on 2017/11/16.
 */

public interface SearchFamilyAndGroupResultContract extends BaseView {
    /**
     * 搜索家庭或群组成功
     *
     * @param searchName 搜索关键字
     * @param bean       返回搜索结果的实体
     */
    void searchFamilyAndGroupListSuccess(String searchName, SearchFamilyAndGroupResultBean bean);

    /**
     * 搜索家庭或好友失败
     *
     * @param searchName 搜索的关键字
     * @param e
     */
    void searchFamilyAndGroupListFailed(String searchName, CommonException e);
}
