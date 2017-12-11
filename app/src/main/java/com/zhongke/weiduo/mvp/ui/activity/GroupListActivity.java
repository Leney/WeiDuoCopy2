package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.GroupListContract;
import com.zhongke.weiduo.mvp.control.ContactsManager;
import com.zhongke.weiduo.mvp.model.entity.ContactsListBean;
import com.zhongke.weiduo.mvp.presenter.GroupListPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.GroupListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/30.
 * 群列表界面
 */

public class GroupListActivity extends BaseMvpActivity implements GroupListContract,ContactsManager.OnContactsInitFinishListener {

    private ListView lv;
    private GroupListPresenter presenter;
    private GroupListAdapter adapter;

    private List<ContactsListBean> groupList;

    @Override
    protected BasePresenter createPresenter() {
        presenter = new GroupListPresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_group_list);
        setTitleName("群组");
        showCenterView();
        initView();

        presenter.getGroupList();

        ContactsManager.getInstance().registerContactsInitFinishedListener(this);
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        groupList = new ArrayList<>();
        adapter = new GroupListAdapter(groupList, this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactsListBean bean = groupList.get(position);
                SessionActivity2.startActivity(GroupListActivity.this, SessionActivity2.SESSION_TYPE_GROUP, bean.id + "", bean.nickName);
            }
        });
    }

    @Override
    public void getGroupListSuccess(List<ContactsListBean> list) {
        groupList.clear();
        groupList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, GroupListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onContactsInitFinishListener() {
        // 联系人信息发生改变
        presenter.getGroupList();
    }
}
