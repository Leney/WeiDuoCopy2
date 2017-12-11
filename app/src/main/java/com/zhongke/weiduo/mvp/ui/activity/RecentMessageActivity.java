package com.zhongke.weiduo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.contract.IRecentMessageFgView;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.presenter.RecentMessageFgPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.RecentChatListAdapter;
import com.zhongke.weiduo.mvp.ui.widget.view.SimpleDividerItemDecoration;

import java.util.List;

/**
 * Created by Karma on 2017/6/28.
 * 类描述：最近聊天界面
 */

public class RecentMessageActivity extends BaseMvpActivity<IRecentMessageFgView, RecentMessageFgPresenter> implements IRecentMessageFgView,
        RecentChatListManager.onRecentChatListChangedListener, RecentChatListAdapter.AgreeListener, RecentChatListAdapter.RejectListener,
        RecentChatListAdapter.IonSlidingViewClickListener {
    private static final String TAG = "RecentMessageActivity";

    private RecyclerView mRecyclerView;

    private RecentChatListAdapter mAdapter;

    private List<ChatListBean> recentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName(getResources().getString(R.string.message));
//        Tools.setStatusColor(this, ContextCompat.getColor(this, R.color.title_bg));
        setCenterLay(R.layout.activity_recent_message);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_recent_message_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // 设置自定义的分割线
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this, 1));
        mAdapter = new RecentChatListAdapter(this);
        mAdapter.setAgreeListener(this);
        mAdapter.setRejectListener(this);
        mRecyclerView.setAdapter(mAdapter);
        showCenterView();
        mPresenter.getRecentChatList();

        // 注册监听列表数据发生改变的监听
        RecentChatListManager.getInstance().registerOnRecentListChangedListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        LogUtil.i(TAG, " onDestroy");
//        RecentChatListManager.getInstance().saveRecentChatList();
        // 注销监听最近联系人列表发生改变的监听
        RecentChatListManager.getInstance().unregisterOnRecentListChangedListener(this);
    }

    @Override
    protected RecentMessageFgPresenter createPresenter() {
        return new RecentMessageFgPresenter(this);
    }

    @Override
    public void getRecentChatListSuccess(List<ChatListBean> list) {
        recentList = list;
        mAdapter.addDatas(recentList);

    }

    @Override
    public void agreeAdd(ChatListBean chatListBean) {
        mPresenter.handleFriendRequest(chatListBean, "2");
    }

    @Override
    public void rejectAdd(ChatListBean bean) {
        mPresenter.handleFriendRequest(bean, "3");
    }

    //同意或拒绝友好请求成功
    @Override
    public void handleRequestSuccess(ChatListBean bean, String friendState) {

        int position = recentList.indexOf(bean);
        mAdapter.notifyItemChanged(position, "");
    }

    @Override
    public void handleRequestFailed(String friendState) {

    }

    @Override
    public void onChange(int position) {
//        recentList.clear();
//        recentList.addAll(RecentChatListManager.getInstance().getBeanList());
//        mAdapter.addDatas(recentList);

        // 最近聊天列表数据发生改变
        LogUtil.i("llj", "最近聊天列表界面发生改变！！！！position------->>>" + position);
        if (position >= 0) {
            // 更新具体的item
            mAdapter.notifyItemChanged(position);
        } else {
            // 更新所有
            mAdapter.notifyDataSetChanged();
//            // 重新去获取最近列表
//            mPresenter.getRecentChatList();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        LogUtil.i("llj", "onItemClick---->>>" + position);
//        ChatListBean bean = recentList.get(position);
//        RecentChatListManager.getInstance().deleteRecentChatListBean(bean.type, bean.id);
    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        Log.i("llj", "nDeleteBtnCilck!!!");
        ChatListBean bean = recentList.get(position);
        RecentChatListManager.getInstance().deleteRecentChatListBean(bean.type, bean.id);
    }
}
