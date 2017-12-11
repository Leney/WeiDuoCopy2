package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.GroupListContract;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by ${tanlei} on 2017/9/30.
 */

public class GroupListPresenter extends BasePresenter {
    private GroupListContract mContract;

    public GroupListPresenter(GroupListContract contract) {
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
     * 获取数据库中群组列表
     */
    public void getGroupList() {
        ContactsManager.getInstance().queryContactListByType(ContactsListBean.TYPE_GROUP, new Action1<List<ContactsListBean>>() {
            @Override
            public void call(List<ContactsListBean> listBeen) {
                mContract.getGroupListSuccess(listBeen);
            }
        });
    }
}
