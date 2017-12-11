package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.FamilyClassifyListBean;
import com.zhongke.weiduo.mvp.model.entity.FamilyInfo;

import java.util.List;

/**
 * 添加家庭列表界面
 * Created by llj on 2017/6/22.
 */

public interface AddFamilyListContract extends BaseView {
    /** 获取家庭类别列表成功*/
    void getFamilyClassifySuccess(FamilyClassifyListBean classifyListBean);
    /** 获取家庭类别列表失败*/
    void getFamilyClassifyFailed(int errorCode,String msg);
    /** 获取附近家庭列表成功*/
    void getNearbyFamilyListSuccess(List<FamilyInfo> list);
    /** 获取附近家庭列表失败*/
    void getNearbyFamilyListFailed(int errorCode,String msg);
}
