package com.zhongke.weiduo.mvp.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SearchFriendResultContract;
import com.zhongke.weiduo.mvp.model.entity.SearchFriendResultBean;
import com.zhongke.weiduo.mvp.presenter.SearchFriendResultPresenter;
import com.zhongke.weiduo.mvp.ui.activity.PersonalDetailsActivity;
import com.zhongke.weiduo.mvp.ui.adapter.SearchFriendResultAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.SystemItemDecoration;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.LoadMoreRecyclerView;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果Fragment
 * Created by llj on 2017/11/16.
 */

public class SearchFriendResultFragment extends BaseMvpFragment implements SearchFriendResultContract, SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.LoadMoreListener,SearchFriendResultAdapter.OnResultItemClickListener {

    private SearchFriendResultPresenter mPresenter;

    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreRecyclerView resultRecyclerView;
    private SearchFriendResultAdapter mAdapter;


    private int pageIndex = 1;
    private final int PAGE_SIZE = 16;


    /**
     * 当前搜索字符
     */
    private String searchName = "";
    /**
     * 上一次搜素的关键字
     */
    private String lastSearchName = "";
    private List<SearchFriendResultBean.UserListBean> resultList;

    @Override
    protected BasePresenter createPresenter() {
        return mPresenter = new SearchFriendResultPresenter(this);
    }

    @Override
    protected void initView(View baseView) {
        super.initView(baseView);
        setCenterLay(R.layout.fragment_search_result);

        resultList = new ArrayList<>();

        resultRecyclerView = (LoadMoreRecyclerView) baseView.findViewById(R.id.search_result_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        resultRecyclerView.setLayoutManager(layoutManager);
        resultRecyclerView.addItemDecoration(new SystemItemDecoration(getActivity(), SystemItemDecoration.VERTICAL_LIST));

        mAdapter = new SearchFriendResultAdapter(getActivity(), resultList);
        mAdapter.setOnResultItemClickListener(this);
        // 设置搜索结果的列表Adapter
        resultRecyclerView.setAdapter(mAdapter);


        swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) baseView.findViewById(R.id.search_result_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        swipeRefreshLayout.setScrollUpChild(resultRecyclerView);
        swipeRefreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        swipeRefreshLayout.setLoadingIndicator(true);
        resultRecyclerView.setLoadmoreListener(this);
        //以上代码不响应onRefresh()，需要手动响应onReFresh()。
        this.onRefresh();
    }

    @Override
    public void searchFriendListSuccess(String searchName, SearchFriendResultBean bean) {
        if (this.lastSearchName.equals(searchName)) {
            if (pageIndex == 1) {
                // 如果是第一页
                resultList.clear();
            }
        } else {
            resultList.clear();
        }

        if (pageIndex == 1) {
            if (bean.getUserList().isEmpty()) {
                showNoDataView();
            }else {
                showCenterView();
            }
            releaseState();
        }

        if (bean.getUserList().size() < PAGE_SIZE) {
            // 没有下一页了
            resultRecyclerView.setCanLoadMore(false);
        }
        pageIndex++;
        resultList.addAll(bean.getUserList());
        mAdapter.notifyDataSetChanged();
        lastSearchName = searchName;
    }

    @Override
    public void searchFriendListFailed(String searchName, CommonException e) {
        if (this.lastSearchName.equals(searchName)) {
            if (pageIndex == 1) {
                showErrorView();
            }
        } else {
            showErrorView();
        }
        lastSearchName = searchName;
    }

    @Override
    public void onRefresh() {
        // 下拉刷新
        pageIndex = 1;
        resultRecyclerView.setCanLoadMore(true);
        mPresenter.searchFriendList(searchName, pageIndex, PAGE_SIZE);
    }

    @Override
    public void onLoadMore() {
        // 加载更多
        mPresenter.searchFriendList(searchName, pageIndex, PAGE_SIZE);
    }

    /**
     * 开始一个新的搜索
     *
     * @param name
     */
    public void startSearch(String name) {
        this.searchName = name;
        this.onRefresh();
    }

    /**
     * 释放状态
     */
    private void releaseState() {
        swipeRefreshLayout.setLoadingIndicator(false);
        resultRecyclerView.setCanLoadMore(true);
    }

    @Override
    public void onResultItemClick(SearchFriendResultBean.UserListBean bean) {
        // item条目点击事件,跳转到个人资料详情界面
        PersonalDetailsActivity.startActivity(getActivity(),bean.getId()+"",bean.getUserName(),false,false);
    }
}
