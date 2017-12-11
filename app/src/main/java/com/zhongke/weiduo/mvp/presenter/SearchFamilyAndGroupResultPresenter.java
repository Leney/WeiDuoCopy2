package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchFamilyAndGroupResultContract;
import com.zhongke.weiduo.mvp.model.entity.SearchFamilyAndGroupResultBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by llj on 2017/11/16.
 */

public class SearchFamilyAndGroupResultPresenter extends BasePresenter {
    private SearchFamilyAndGroupResultContract mContract;

    public SearchFamilyAndGroupResultPresenter(SearchFamilyAndGroupResultContract contract) {
        mContract = contract;
    }

    /**
     * 搜索好友、家庭、群组
     *
     * @param groupType 搜索类型，1=家庭，2=群组
     * @param name
     */
    public void searchFamilyAndGroupList(int groupType, String name, int pageIndex, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("searchName", name);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        params.put("groupType", groupType);
        // 搜索家庭或者群组
        retrofitClient.searchAddFamilyAndGroupList(params, new ResponseResultListener<SearchFamilyAndGroupResultBean>() {
            @Override
            public void success(SearchFamilyAndGroupResultBean bean) {
                mContract.searchFamilyAndGroupListSuccess(name, bean);
            }

            @Override
            public void failure(CommonException e) {
                mContract.searchFamilyAndGroupListFailed(name, e);
            }
        });
    }
}
