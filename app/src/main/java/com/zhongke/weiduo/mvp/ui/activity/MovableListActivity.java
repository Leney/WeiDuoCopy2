package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MovableListContract;
import com.zhongke.weiduo.mvp.model.entity.ActivityListBean;
import com.zhongke.weiduo.mvp.presenter.MovableListPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.MovableListAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.SystemItemDecoration;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.LoadMoreRecyclerView;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.RecyclerViewItemClickListener;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/26.
 * 活动列表界面
 */

public class MovableListActivity extends BaseMvpActivity implements RecyclerViewItemClickListener
        , MovableListContract
        , SwipeRefreshLayout.OnRefreshListener
        , LoadMoreRecyclerView.LoadMoreListener {
    private MovableListPresenter presenter;
    private MovableListAdapter adapter;
    private LoadMoreRecyclerView recyclerView;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected BasePresenter createPresenter() {
        presenter = new MovableListPresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_movable_list);
        showCenterView();
        setTitleName("活动");
        initView();
    }

    private void initView() {
        recyclerView = (LoadMoreRecyclerView) findViewById(R.id.movable_recycler_view);
        recyclerView.setLoadmoreListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovableListAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SystemItemDecoration(this,LinearLayoutManager.VERTICAL));

        swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) findViewById(R.id.movable_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        swipeRefreshLayout.setScrollUpChild(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        swipeRefreshLayout. setLoadingIndicator(true);
        //以上代码不响应onRefresh()，需要手动响应onReFresh()。
        this.onRefresh();
    }

    @Override
    public void getActiveListSuccess(int mode,List<ActivityListBean.RecordsBean> list) {
         switch (mode){
             case MovableListContract.MODE_LOADE_MORE:
                 if (list.size()>0){
                     this.adapter.addData(list);
                 }
                 break;
             case MovableListContract.MODE_REFRESH:
                 if (list.size()>0){
                     this.adapter.changeData(list);
                 }else{
                     showNoDataView();
                 }
                 break;
             default:
                 break;
         }
        releaseState();
    }

    @Override
    public void getActiveListFailed(int code,CommonException msg) {
        UIUtils.showToast(msg.getErrorMsg());
        releaseState();
        if (code==MovableListContract.MODE_REFRESH){
            this.showErrorView();
        }
    }

    /**
     * 释放状态
     */
    private void releaseState(){
        swipeRefreshLayout.setLoadingIndicator(false);
        recyclerView.setCanLoadMore(true);
    }
    public static void startActivity(Context context/*, int stepId, String stepName*/) {
        Intent intent = new Intent(context, MovableListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view, int position) {
        ActivityListBean.RecordsBean recordsBean = adapter.getList().get(position);
        ActivityDetailActivity.startActivity(this, recordsBean.getId(), recordsBean.getTitle());
    }

    @Override
    public void onRefresh() {
        this.presenter.refresh();
    }
    @Override
    public void onLoadMore() {
        this.presenter.loadMore();
    }


}
