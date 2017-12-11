package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchFriendContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;

/**
 * Created by ${tanlei} on 2017/10/10.
 */

public class SearchFriendPresenter extends BasePresenter {
    private SearchFriendContract searchFriendContract;
    private DataManager manager;

    public SearchFriendPresenter(SearchFriendContract searchFriendContract, DataManager manager) {
        this.searchFriendContract = searchFriendContract;
        this.manager = manager;
    }
}
