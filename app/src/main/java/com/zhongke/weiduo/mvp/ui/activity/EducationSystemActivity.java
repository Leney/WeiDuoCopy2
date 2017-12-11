package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.EducationSystemContract;
import com.zhongke.weiduo.mvp.model.entity.EducationSystemList;
import com.zhongke.weiduo.mvp.presenter.EducationSystemPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.EducationSystemAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.SystemItemDecoration;
import com.zhongke.weiduo.mvp.ui.widget.layout.SearchLayout;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.LoadMoreRecyclerView;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ${tanlei} on 2017/9/16.
 * 教育体系界面
 */

public class EducationSystemActivity extends BaseMvpActivity implements EducationSystemContract
        , SwipeRefreshLayout.OnRefreshListener
        , LoadMoreRecyclerView.LoadMoreListener
        , SearchLayout.SearchResponseListener {
    private LoadMoreRecyclerView recyclerView;
    private EducationSystemAdapter adapter;
    private EducationSystemPresenter presenter;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    private final String TAG = EducationSystemActivity.class.getSimpleName();

    @Override
    public String getSearchContent() {
        return searchLayout.getCurrentContent();
    }

    @Override
    protected BasePresenter createPresenter() {
        presenter = new EducationSystemPresenter(this, mDataManager);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_education_system);
        showCenterView();
        init();
    }

    private SearchLayout searchLayout;

    private void init() {
        setTitleName("教育体系");
        super.baseTitle.setRightImgRes(R.drawable.search_large);
        searchLayout = super.baseTitle.addSearchView();
        searchLayout.setResponseListener(this);
        searchLayout.hideSelf();
        super.baseTitle.setRightImgClickListener(view -> searchLayout.startAnimator());

        recyclerView = (LoadMoreRecyclerView) findViewById(R.id.rv_system);
        recyclerView.setLoadmoreListener(this);
        recyclerView.addItemDecoration(new SystemItemDecoration(this, SystemItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<EducationSystemList.RecordsBean> list = new ArrayList<>();
        adapter = new EducationSystemAdapter(this,R.layout.item_education_system, list);
        recyclerView.setAdapter(adapter);
        adapter.addHeader(view->{
            this.adapter.hideHeader();
            this.back();
            this.presenter.changeMode(EducationSystemPresenter.DATA_MODE_ALL);
            this.presenter.refresh();
        });
        adapter.setOnItemClickListener((adapter, view, position) -> {
            EducationSystemList.RecordsBean recordsBean = list.get(position);
            SystemDetailsActivity.startActivity(this, recordsBean.getId(), recordsBean.getBName(), recordsBean.getBInfo());
        });

        this.swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) findViewById(R.id.education_system_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        swipeRefreshLayout.setScrollUpChild(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        swipeRefreshLayout.setLoadingIndicator(true);
        //以上代码不响应onRefresh()，需要手动响应onReFresh()。
        this.onRefresh();
    }
    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, EducationSystemActivity.class));
    }
    /**
     * 释放状态
     */
    private void releaseState() {
        swipeRefreshLayout.setLoadingIndicator(false);
        recyclerView.setCanLoadMore(true);
    }
    @Override
    public void getActiveListSuccess(int mode, List<EducationSystemList.RecordsBean> list) {
        switch (mode) {
            case EducationSystemContract.MODE_LOADE_MORE:
                if (list.size() > 0) {
                    this.adapter.addData(list);
                }
                break;
            case EducationSystemContract.MODE_REFRESH:
                if (list.size() > 0) {
                    this.adapter.changeData(list);
                } else {
                    showNoDataView();
                }
                break;
            default:
                break;
        }
        releaseState();
    }
    @Override
    public void getActiveListFailed(int code, CommonException msg) {
        releaseState();
        UIUtils.showToast(msg.getErrorMsg());
        if (code == EducationSystemContract.MODE_REFRESH) {
            this.showErrorView();
        }
    }
    @Override
    public void onRefresh() {
        this.presenter.refresh();
    }
    @Override
    public void onLoadMore() {
        this.presenter.loadMore();
    }
    @Override
    public void searchContent(String content) {
        Log.i(TAG, "搜索的内容 " + content);
        this.presenter.changeMode(EducationSystemPresenter.DATA_MODE_SEARCH);
        this.presenter.refresh();
        adapter.showHeader();
    }
    @Override
    public void back() {
        searchLayout.backAnimator();
    }
}
