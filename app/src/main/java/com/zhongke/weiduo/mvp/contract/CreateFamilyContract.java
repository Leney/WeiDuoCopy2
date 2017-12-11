package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.FamilyInfo;

/**
 * 创建家庭
 * Created by llj on 2017/6/21.
 */

public interface CreateFamilyContract extends BaseView {
    /** 创建家庭成功*/
    void createSuccess(FamilyInfo familyInfo);

    /** 创建家庭失败*/
    void createFailed(int errorCode,String msg);
}
