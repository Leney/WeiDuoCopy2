package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.FamilyListContract;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.model.entity.FriendAndFamilyBean;
import com.zhongke.weiduo.mvp.presenter.FamilyListPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.FamilyListExpandableAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/30.
 * 家庭表界面
 */

public class FamilyListActivity extends BaseMvpActivity implements FamilyListContract, ContactsManager.OnContactsChangedListener {

    private ExpandableListView lv;
    private FamilyListPresenter presenter;
    private FamilyListExpandableAdapter adapter;

    private List<ContactsListBean> myFamilyList;
    private List<ContactsListBean> friendFamilyList;

    @Override
    protected BasePresenter createPresenter() {
        presenter = new FamilyListPresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_family_list);
        setTitleName(getResources().getString(R.string.my_family));
        showCenterView();
        initView();

        // 获取我的家庭列表和友好家庭列表
        presenter.getAllFamilyList();
    }

    private void initView() {
        // 设置监听联系人数据发生改变
        ContactsManager.getInstance().registerContactsChangedListener(this);

        List<FriendAndFamilyBean> list = new ArrayList();
        myFamilyList = new ArrayList<>();
        friendFamilyList = new ArrayList<>();

        list.add(new FriendAndFamilyBean("我的家庭", myFamilyList));
        list.add(new FriendAndFamilyBean("好友家庭", friendFamilyList));

        lv = (ExpandableListView) findViewById(R.id.lv);
        adapter = new FamilyListExpandableAdapter(list, this);

        lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0) {
                    // 我的家庭
                    ContactsListBean bean = myFamilyList.get(childPosition);
                    SessionActivity2.startActivity(FamilyListActivity.this, SessionActivity2.SESSION_TYPE_FAMILY, bean.getId()+"", "我的家庭");
                } else if (groupPosition == 1) {
                    // 友好家庭
                    ContactsListBean bean = friendFamilyList.get(childPosition);
                    FamilyDetailActivity.startActivity(FamilyListActivity.this, bean.id, true, false);
                }
                return false;
            }
        });


        //把向下的箭头去掉
        lv.setGroupIndicator(null);
        //不让点击展开
        lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }


    @Override
    public void onContactsChanged(int type, int id) {
        if (type == ContactsListBean.TYPE_MY_FAMILY) {
            presenter.getMyFamilyList();
        } else if (type == ContactsListBean.TYPE_FRIEND_FAMILY) {
            presenter.getFriendFamilyList();
        }
    }

    @Override
    public void getAllFamilyListSuccess(List<ContactsListBean> myFamilyList, List<ContactsListBean> friendFamilyList) {
        this.myFamilyList.addAll(myFamilyList);
        this.friendFamilyList.addAll(friendFamilyList);
        lv.setAdapter(adapter);
        int count = lv.getCount();
        for (int i = 0; i < count; i++) {
            lv.expandGroup(i);
        }
    }

    @Override
    public void getMyFamilyListSuccess(List<ContactsListBean> myFamilyList) {
        this.myFamilyList.clear();
        this.myFamilyList.addAll(myFamilyList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getFriendFamilyListSuccess(List<ContactsListBean> myFamilyList) {
        this.friendFamilyList.clear();
        this.friendFamilyList.addAll(myFamilyList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ContactsManager.getInstance().unregisterContactsChangedListener(this);
    }

    public static void startActivity(Context context/*, int tag, String stepName*/) {
        Intent intent = new Intent(context, FamilyListActivity.class);
//        intent.putExtra("tag", tag);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }
}
