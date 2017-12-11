package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.BuilderMap;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.EducationSystemContract;
import com.zhongke.weiduo.mvp.contract.MovableListContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.EducationSystemList;

import java.util.Map;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/9/18.
 */

public class EducationSystemPresenter extends BasePresenter {
    private EducationSystemContract educationSystemContract;
    private DataManager manager;
    private final int PAGESIZE = 8;
    private int currentPage = 1;
    private boolean isTaskGonging = false;
    /**
     * 搜索数据
     */
    public static final int DATA_MODE_SEARCH=110;
    /**
     * 全部数据
     */
    public static final int DATA_MODE_ALL=120;
    private int currentMode;

    public EducationSystemPresenter(EducationSystemContract educationSystemContract, DataManager manager) {
        this.educationSystemContract = educationSystemContract;
        this.manager = manager;
        this.currentMode=DATA_MODE_ALL;
    }

    /**
     * 改变Mode
     * @param mode
     */
    public void changeMode(int mode){
        if (mode==currentMode){
            return;
        }
        currentMode=mode;
    }
    /**
     * 创建参数
     * @return
     */
    private Map<String,Object> getParams(){
        Map<String,Object> params;
        if (currentMode==DATA_MODE_ALL){
            params=BuilderMap. buildMapEducationSystemDefault(currentPage, PAGESIZE);
        }else{
            params= BuilderMap. buildMapEducationSystemSearch(educationSystemContract.getSearchContent(), currentPage, PAGESIZE);
        }
        return params;
    }

    private void executeTask(final int mode, Map<String, Object> params) {
        isTaskGonging = true;
        Subscription subscription = retrofitClient.getEducationSystemAList(params, new ResponseResultListener<EducationSystemList>() {
            @Override
            public void success(EducationSystemList educationSystemList) {
                isTaskGonging = false;
                educationSystemContract.getActiveListSuccess(mode, educationSystemList.getRecords());
            }
            @Override
            public void failure(CommonException e) {
                isTaskGonging = false;
                educationSystemContract.getActiveListFailed(mode, e);
            }
        });
        this.compositeSubscription.add(subscription);
    }
    public void loadMore() {
        if (isTaskGonging) {
            return;
        }
        ++currentPage;
        executeTask(MovableListContract.MODE_LOADE_MORE,getParams());
    }
    public void refresh() {
        if (isTaskGonging) {
            return;
        }
        currentPage = 1;
        executeTask(MovableListContract.MODE_REFRESH, getParams());
    }

}
