package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchResultByTagContract;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyList;
import com.zhongke.weiduo.util.RequestParameterUtils;

import java.util.HashMap;

/**
 * Created by dgg1 on 2017/11/17.
 */

public class SearchResultByTagPresenter extends BasePresenter {
    private SearchResultByTagContract contract;

    public SearchResultByTagPresenter(SearchResultByTagContract contract) {
        this.contract = contract;
    }

    /**
     * 通过标签名获取家庭列表数据
     *
     * @param tagName
     */
    public void getFamilyList(String tagName) {
        HashMap hashMap = RequestParameterUtils.setRequestPar("tagName", tagName);
        retrofitClient.getFamilyListToTag(hashMap, new ResponseResultListener<NewFamilyList>() {
            @Override
            public void success(NewFamilyList newFamilyList) {
                contract.showFamilyListByTag(newFamilyList);
            }

            @Override
            public void failure(CommonException e) {
                contract.showError();
            }
        });
    }
}
