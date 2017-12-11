package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchFriendResultContract;
import com.zhongke.weiduo.mvp.model.entity.SearchFriendResultBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by llj on 2017/11/16.
 */

public class SearchFriendResultPresenter extends BasePresenter {
    private SearchFriendResultContract mContract;

    public SearchFriendResultPresenter(SearchFriendResultContract contract) {
        mContract = contract;
    }

    /**
     * 搜索好友、家庭、群组
     *
     * @param name
     */
    public void searchFriendList(String name, int pageIndex, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("searchName", name);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        // 搜索好友
        retrofitClient.searchAddFriendList(params, new ResponseResultListener<SearchFriendResultBean>() {
            @Override
            public void success(SearchFriendResultBean bean) {
                mContract.searchFriendListSuccess(name, bean);
            }

            @Override
            public void failure(CommonException e) {
                mContract.searchFriendListFailed(name, e);
            }
        });
    }
}
