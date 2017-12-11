package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ExpertContract;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.NewExpertBean;
import com.zhongke.weiduo.mvp.presenter.ExpertPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.ExpertAdapter;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.LoadMoreRecyclerView;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/4.
 * 专家界面
 */

public class ExpertActivity extends BaseMvpActivity implements ExpertContract, ExpertAdapter.OnItemClickListeners, SwipeRefreshLayout.OnRefreshListener
        , LoadMoreRecyclerView.LoadMoreListener {
    /**
     * 专家界面的Presenter
     */
    private ExpertPresenter expertPresenter;
    /**
     * 显示专家数据的RecyclerView
     */
    private LoadMoreRecyclerView rvExpert;
    /**
     * RecyclerView添加的头部布局
     */
    private View headerView;
    /**
     * 广告轮播
     */
    private Banner mBanner;
    /**
     * 适配器
     */
    private ExpertAdapter expertAdapter;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    private List<NewExpertBean.RecordsBean> list;

    @Override
    protected BasePresenter createPresenter() {
        expertPresenter = new ExpertPresenter(this, mDataManager);
        return expertPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_expert);
        showLoadingView();
        initView();
    }

    private void initView() {
        setTitleName(getResources().getString(R.string.expert));
        headerView = LayoutInflater.from(this).inflate(R.layout.header_expert, rvExpert, false);
        rvExpert = (LoadMoreRecyclerView) findViewById(R.id.rv_expert);
        swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) findViewById(R.id.movable_refresh_layout);
        mBanner = (Banner) headerView.findViewById(R.id.expert_banner);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvExpert.setLayoutManager(layoutManager);

        swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) findViewById(R.id.movable_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        swipeRefreshLayout.setScrollUpChild(rvExpert);
        swipeRefreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        swipeRefreshLayout.setLoadingIndicator(true);
        //以上代码不响应onRefresh()，需要手动响应onReFresh()。
        this.onRefresh();
        //网络请求专家界面轮播图
        expertPresenter.requestCarousel();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ExpertActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void clickItem(RecyclerView Parent, View view, int position) {
        ExpertDetailsActivity.startActivity(this, list.get(position).getUserId());
    }

    @Override
    public void showExpertList(List<NewExpertBean.RecordsBean> list, int status) {
        releaseState();
        if (null != list) {
            showCenterView();
            this.list = list;
            if (null == expertAdapter) {
                expertAdapter = new ExpertAdapter(list, this);
                rvExpert.setAdapter(expertAdapter);
                expertAdapter.setHeaderView(headerView);
                expertAdapter.setOnItemClickListeners(this);
            } else {
                expertAdapter.myNotifyDataSetChanged(list, status);
            }
        } else {
            showNoDataView();
        }
    }

    /**
     * 释放状态
     */
    private void releaseState() {
        swipeRefreshLayout.setLoadingIndicator(false);
        rvExpert.setCanLoadMore(true);
    }

    @Override
    public void requestFailure() {
        showErrorView();
    }

    @Override
    public void showCarousel(CarouselBitmapBean carouselBitmapBean) {
        List<String> bannerUrls = new ArrayList<>();
        if (carouselBitmapBean != null) {
            //请求到了轮播图片
            List<CarouselBitmapBean.RecordsBean> records = carouselBitmapBean.getRecords();
            for (CarouselBitmapBean.RecordsBean recordsBean : records) {
                bannerUrls.add(recordsBean.getImageUrl());
            }
        } else {
            //请求到的轮播图片给空时，给默认图
            bannerUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504591182936&di=ccfc2d952f96cf28edf71f4015eefd91&imgtype=0&src=http%3A%2F%2Fdown.tutu001.com%2Fd%2Ffile%2F20120219%2Fa1a4c3c39a21533d83e2e0e027_560.jpg");
            bannerUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504591182936&di=995c887b9f929b025ee902f00c83d9b5&imgtype=0&src=http%3A%2F%2Fpic63.nipic.com%2Ffile%2F20150402%2F10078947_230007214608_2.jpg");
        }
        mBanner.setImages(bannerUrls);
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setDelayTime(2000);
        mBanner.start();
    }

    @Override
    public void onRefresh() {
        expertPresenter.refreshList();
        expertPresenter.requestCarousel();
    }

    @Override
    public void onLoadMore() {
        expertPresenter.loadList();
    }
}
