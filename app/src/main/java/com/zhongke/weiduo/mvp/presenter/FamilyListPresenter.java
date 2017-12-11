package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.FamilyListContract;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by ${tanlei} on 2017/9/30.
 */

public class FamilyListPresenter extends BasePresenter {
    private FamilyListContract mContract;

    public FamilyListPresenter(FamilyListContract contract) {
        this.mContract = contract;
    }

//    /**
//     * 获取我的家庭资料
//     */
//    public void getMyFamily() {
//        HashMap hashMap = RequestParameterUtils.setRequestPar("token", "mga7csnv1509791787822", "userId", "90");
//        retrofitClient.getMyFamily(hashMap, new ResponseResultListener<NewMyFamily>() {
//            @Override
//            public void success(NewMyFamily newMyFamily) {
//                Log.e("lee",newMyFamily.toString());
//                groupListContract.showMyFamily(newMyFamily);
//            }
//
//            @Override
//            public void failure(CommonException e) {
//
//            }
//        });
//    }

    /**
     * 获取我的家庭列表和友好家庭列表
     */
    public void getAllFamilyList() {
        ContactsManager.getInstance().queryContactListByType(ContactsListBean.TYPE_MY_FAMILY, new Action1<List<ContactsListBean>>() {
            @Override
            public void call(List<ContactsListBean> list) {
                getFriendFamilyList(list);
            }
        });
    }

    private void getFriendFamilyList(List<ContactsListBean> myFamilyList) {
        ContactsManager.getInstance().queryContactListByType(ContactsListBean.TYPE_FRIEND_FAMILY, new Action1<List<ContactsListBean>>() {
            @Override
            public void call(List<ContactsListBean> list) {
                mContract.getAllFamilyListSuccess(myFamilyList, list);
            }
        });
    }


    /**
     * 单独获取我的家庭列表
     */
    public void getMyFamilyList() {
        ContactsManager.getInstance().queryContactListByType(ContactsListBean.TYPE_MY_FAMILY, new Action1<List<ContactsListBean>>() {
            @Override
            public void call(List<ContactsListBean> list) {
                mContract.getMyFamilyListSuccess(list);
            }
        });
    }

    /**
     * 单独获取友好家庭列表
     */
    public void getFriendFamilyList() {
        ContactsManager.getInstance().queryContactListByType(ContactsListBean.TYPE_FRIEND_FAMILY, new Action1<List<ContactsListBean>>() {
            @Override
            public void call(List<ContactsListBean> list) {
                mContract.getFriendFamilyListSuccess(list);
            }
        });
    }
}
