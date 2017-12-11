package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MySchemeListContract;
import com.zhongke.weiduo.mvp.control.UserInfoManager;
import com.zhongke.weiduo.mvp.model.entity.MySchemeBean;
import com.zhongke.weiduo.mvp.presenter.MySchemeListPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.DividerDecoration;
import com.zhongke.weiduo.mvp.ui.adapter.MySchemeListAdapter;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.LoadMoreRecyclerView;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/27.
 * 我的规划列表
 */

public class MySchemeListActivity extends BaseMvpActivity implements MySchemeListContract
        , MySchemeListAdapter.IonSlidingViewClickListener
        , SwipeRefreshLayout.OnRefreshListener
        , LoadMoreRecyclerView.LoadMoreListener {

    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreRecyclerView recyclerView;
    private MySchemeListAdapter adapter;
    private MySchemeListPresenter presenter;

    /**
     * 我的目标列表
     */
    private List<MySchemeBean.RecordsBean> list;

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
        presenter = new MySchemeListPresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_aims_plan);
        initView();
    }

    private void initView() {
        recyclerView = (LoadMoreRecyclerView) findViewById(R.id.lv_aims_plan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        baseTitle.setRightImgRes(R.drawable.main_add);
        baseTitle.setRightImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AddAimsPlanningActivity.startActivityForResult(MySchemeListActivity.this, AddAimsPlanningActivity.MY_SCHEME);
                SchemeListActivity.startActivity(MySchemeListActivity.this);
            }
        });

        list = new ArrayList<>();

        adapter = new MySchemeListAdapter(list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerDecoration(this, DividerDecoration.VERTICAL_LIST, R.drawable.item_target_divider));

        setTitleName("规划");

        this.swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) findViewById(R.id.my_aims_list_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        swipeRefreshLayout.setScrollUpChild(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        swipeRefreshLayout.setLoadingIndicator(true);
        recyclerView.setLoadmoreListener(this);
        //以上代码不响应onRefresh()，需要手动响应onReFresh()。
        this.onRefresh();
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
//        adapter.removeData(position);
        presenter.deleteMyScheme(list.get(position).getId());
    }

    @Override
    public void getMySchemeListSuccess(MySchemeBean bean) {
        // 获取我的规划列表成功
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
        list.addAll(bean.getRecords());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getMySchemeListFailed(CommonException e) {
        // 获取我的规划列表失败
        if (pageIndex <= 1) {
            // 请求第一页数据失败
            showErrorView();
        } else {
            // 请求下一页数据失败
        }
        releaseState();
    }

    @Override
    public void deleteMySchemeSuccess() {
        // 删除规划成功
//        adapter.removeData(position);
        reloadList();
        // 数量减1
        UserInfoManager.getInstance().minusNum(1,UserInfoManager.TYPE_SCHEME);
    }

    @Override
    public void deleteMySchemeFailed(CommonException e) {
        // 删除规划失败
        Toast.makeText(this, "删除规划失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        reloadList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AddAimsPlanningActivity.ADD_SCHEME_RESULT_CODE) {
            reloadList();
        }
    }

    /**
     * 重新加载数据
     */
    private void reloadList() {
        showLoadingView();
        pageIndex = 1;
        list.clear();
        presenter.getMySchemeList(pageIndex, PAGE_SIZE);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MySchemeListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        list.clear();
        presenter.getMySchemeList(pageIndex, PAGE_SIZE);
    }

    @Override
    public void onLoadMore() {
        presenter.getMySchemeList(pageIndex, PAGE_SIZE);
    }

    /**
     * 释放状态
     */
    private void releaseState() {
        swipeRefreshLayout.setLoadingIndicator(false);
        recyclerView.setCanLoadMore(true);
    }
}
