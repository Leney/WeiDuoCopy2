package com.zhongke.weiduo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.contract.INewFriendAtView;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.presenter.NewFriendAtPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.RecentChatListAdapter;
import com.zhongke.weiduo.mvp.ui.widget.view.SimpleDividerItemDecoration;
import com.zhongke.weiduo.mvp.ui.widget.view.SlidingButtonView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karma on 2017/6/28.
 * 类描述：
 */

public class NewFriendActivity extends BaseMvpActivity<INewFriendAtView, NewFriendAtPresenter> implements INewFriendAtView,RecentChatListManager.onRecentChatListChangedListener,
        RecentChatListAdapter.IonSlidingViewClickListener,RecentChatListAdapter.AgreeListener,RecentChatListAdapter.RejectListener {
    private RecyclerView mRecyclerView;

    private RecentChatListAdapter mAdapter;

    private List<ChatListBean> recentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_new_friend);

        recentList = new ArrayList<>();
        setTitleName(getResources().getString(R.string.new_friend_2));
//        Tools.setStatusColor(this, ContextCompat.getColor(this, R.color.title_bg));
        mRecyclerView = new RecyclerView(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // 设置自定义的分割线
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this, 1));
        setCenterLay(mRecyclerView);
        showCenterView();

        mPresenter.getNewFriendMsg();

        // 注册监听列表数据发生改变的监听
        RecentChatListManager.getInstance().registerOnRecentListChangedListener(this);
    }

    @Override
    protected NewFriendAtPresenter createPresenter() {
        return new NewFriendAtPresenter(NewFriendActivity.this, this);
    }

    @Override
    public void getNewFriendMsgListSuccess(List<ChatListBean> list) {
        mAdapter = new RecentChatListAdapter(this);
        mAdapter.setAgreeListener(this);
        mAdapter.setRejectListener(this);
        recentList.addAll(list);
        mAdapter.addDatas(recentList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getNewFriendMsgListFailed() {

    }

    @Override
    public void onChange(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销监听最近联系人列表发生改变的监听
        RecentChatListManager.getInstance().unregisterOnRecentListChangedListener(this);
    }


    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {

    }
    //点击同意的监听
    @Override
    public void agreeAdd(ChatListBean chatListBean) {
        mPresenter.handleFriendRequest(chatListBean,"2");
    }
    //点击拒绝的监听
    @Override
    public void rejectAdd(ChatListBean bean) {
        mPresenter.handleFriendRequest(bean,"3");
    }
    //处理好友请求成功后
    @Override
    public void handleRequestSuccess(ChatListBean bean, String friendState) {
            mAdapter.notifyDataSetChanged();
    }

    @Override
    public void handleRequestFailed(String friendState) {

    }
}
