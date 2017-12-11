package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/30.
 */

public interface GroupListContract extends BaseView {
    //    void showMyFamily(NewMyFamily newMyFamily);
    /** 获取群组列表成功*/
    void getGroupListSuccess(List<ContactsListBean> list);
}
