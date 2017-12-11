package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/30.
 */

public interface FamilyListContract extends BaseView {
    //    void showMyFamily(NewMyFamily newMyFamily);

    /**
     * 获取我的家庭列表和友好家庭列表成功
     *
     * @param myFamilyList
     * @param friendFamilyList
     */
    void getAllFamilyListSuccess(List<ContactsListBean> myFamilyList, List<ContactsListBean> friendFamilyList);

    /**
     * 获取我的家庭列表成功
     * @param myFamilyList
     */
    void getMyFamilyListSuccess(List<ContactsListBean> myFamilyList);

    /**
     * 获取友好家庭列表成功
     * @param myFamilyList
     */
    void getFriendFamilyListSuccess(List<ContactsListBean> myFamilyList);
}
