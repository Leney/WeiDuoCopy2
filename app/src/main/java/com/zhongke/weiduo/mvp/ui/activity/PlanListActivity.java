package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.PlanListContract;
import com.zhongke.weiduo.mvp.model.entity.PlanBean2;
import com.zhongke.weiduo.mvp.presenter.PlanListPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.PlanListAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.SystemItemDecoration;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.LoadMoreRecyclerView;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/18.
 * 计划列表界面
 */

public class PlanListActivity extends BaseMvpActivity implements PlanListContract
        , PlanListAdapter.OnItemClickListeners
        , PlanListAdapter.OnLikeListener
        , SwipeRefreshLayout.OnRefreshListener
        , LoadMoreRecyclerView.LoadMoreListener{
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreRecyclerView recyclerView;
    private PlanListAdapter adapter;
    private PlanListPresenter presenter;

    private List<PlanBean2.RecordsBean> planList;

    /**
     * 当前请求页数
     */
    private int pageIndex = 1;
    /**
     * 每页请求的数据条数
     */
    private static final int PAGE_SIZE = 16;

    @Override
    protected BasePresenter createPresenter() {
        presenter = new PlanListPresenter(this, mDataManager);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //由于和EducationSystemActivity界面一样只是显示一个列表，所以共用一个布局文件
        setCenterLay(R.layout.activity_plan_list);

        planList = new ArrayList<>();
        showCenterView();
        init();
        this.swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) findViewById(R.id.plan_list_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        swipeRefreshLayout.setScrollUpChild(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        swipeRefreshLayout.setLoadingIndicator(true);
        recyclerView.setLoadmoreListener(this);
        //以上代码不响应onRefresh()，需要手动响应onReFresh()。
        this.onRefresh();
    }

    private void init() {
        setTitleName(getResources().getString(R.string.plan_project));
        recyclerView = (LoadMoreRecyclerView) findViewById(R.id.rv_plan);
        adapter = new PlanListAdapter(planList, this);
        adapter.setLikeListener(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SystemItemDecoration(this, SystemItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListeners(this);
    }

    @Override
    public void getListSuccess(PlanBean2 bean) {
        if (bean.getPageIndex() <= 1) {
            // 请求第一页返回
            if (bean.getRecords().isEmpty()) {
                // 第一页没有数据
                showNoDataView();
            } else {
                // 第一页有数据
                showCenterView();
                releaseState();
            }
        }
        if (bean.getRecords().size() >= PAGE_SIZE) {
            // 还有下一页
            pageIndex++;
            recyclerView.setCanLoadMore(true);
        } else {
            // 没有下一页
            recyclerView.setCanLoadMore(false);
        }
        planList.addAll(bean.getRecords());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getListFailed(CommonException e) {
        if (pageIndex <= 1) {
            // 请求第一页数据失败
            showErrorView();
        } else {
            // 请求下一页数据失败
        }
        releaseState();
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        // 仅仅只是在重新加载第一页数据时调用此方法
        showLoadingView();
        this.onRefresh();
    }

    @Override
    public void onItemClick(View v, int position) {
        PlanBean2.RecordsBean bean = planList.get(position);
        PlanDetailActivity.startActivity(PlanListActivity.this, bean.getId(), bean.getBName());
    }

    @Override
    public void clickLike(PlanBean2.RecordsBean bean) {
        // 点赞
        presenter.clickLike(bean.getId());
    }

    /**
     * 跳转方法
     *
     * @param context
     */
    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, PlanListActivity.class));
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        planList.clear();
        presenter.getList(pageIndex, PAGE_SIZE);
    }

    @Override
    public void onLoadMore() {
        presenter.getList(pageIndex, PAGE_SIZE);
    }

    /**
     * 释放状态
     */
    private void releaseState() {
        swipeRefreshLayout.setLoadingIndicator(false);
        recyclerView.setCanLoadMore(true);
    }
}
