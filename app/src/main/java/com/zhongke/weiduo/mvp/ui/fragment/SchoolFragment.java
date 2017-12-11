package com.zhongke.weiduo.mvp.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.SchoolFragmentContract;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseListResult;
import com.zhongke.weiduo.mvp.presenter.SchoolPresenter;
import com.zhongke.weiduo.mvp.ui.activity.CourseActivity;
import com.zhongke.weiduo.mvp.ui.activity.CurriculumActivity;
import com.zhongke.weiduo.mvp.ui.activity.ExpertActivity;
import com.zhongke.weiduo.mvp.ui.activity.OrganizationListActivity;
import com.zhongke.weiduo.mvp.ui.adapter.CurriculumRecyclerAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.RecyclerItemDecoration;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.LoadMoreRecyclerView;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;
import com.zhongke.weiduo.util.SizeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/23.
 */

public class SchoolFragment extends BaseMvpFragment implements View.OnClickListener, SchoolFragmentContract, CurriculumRecyclerAdapter.OnItemClickListeners, SwipeRefreshLayout.OnRefreshListener
        , LoadMoreRecyclerView.LoadMoreListener {
    public static final String TAG = SchoolFragment.class.getSimpleName();
    /**
     * presenter
     */
    private SchoolPresenter schoolPresenter;
    /**
     * 机构按钮
     */
    private ImageView ivMechanism;
    /**
     * 专家按钮
     */
    private ImageView ivExpert;
    /**
     * 课程按钮
     */
    private ImageView ivCurriculum;
    /**
     * 播放轮播图控件
     */
    private Banner mBanner;
    /**
     * 推荐课程列表的RecyclerView
     */
    private LoadMoreRecyclerView recyclerView;
    /**
     * 推荐课程的适配器
     */
    private CurriculumRecyclerAdapter curriculumRecyclerAdapter;
    /**
     * RecyclerView的头部控件
     */
    private View myHeaderView;

    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    private List<CourseListResult.RecordsBean> courseList;

    @Override
    protected void initView(View baseView) {
        super.initView(baseView);
        setCenterLay(R.layout.fragment_school);
        //刚进来界面时是loading状态
        showLoadingView();
        //初始化
        init(baseView);
        //请求banner数据
        schoolPresenter.getBannerBitmap();
        //设置监听器
        setListener();
    }

    @Override
    public void showCourseSuccess(CourseListResult bean, int status) {
        releaseState();
        //判断是否从服务器中获取到了推荐课程列表数据
        if (bean != null) {
            //如果获取到了数据的话，展示中间布局
            showCenterView();
            courseList = new ArrayList<>();
            if (null == curriculumRecyclerAdapter) {
                curriculumRecyclerAdapter = new CurriculumRecyclerAdapter(getActivity(), bean.getRecords());
                recyclerView.setAdapter(curriculumRecyclerAdapter);
                curriculumRecyclerAdapter.setmHeaderView(myHeaderView);
                curriculumRecyclerAdapter.setOnItemClickListeners(this);
            } else {
                curriculumRecyclerAdapter.myNotifyDataSetChanged(bean.getRecords(), status);
            }
            if (status == 0) {
                courseList.clear();
                courseList.addAll(bean.getRecords());
            } else {
                courseList.addAll(bean.getRecords());
            }
        } else {
            //如果数据为null的话，展示无数据布局
            showNoDataView();
        }
    }

    @Override
    public void showBanner(CarouselBitmapBean carouselBitmapBean) {
        //如果从服务器拿到了轮播图
        if (null != carouselBitmapBean) {
            List<CarouselBitmapBean.RecordsBean> records = carouselBitmapBean.getRecords();
            List<String> bannerRes = new ArrayList<>();
            for (CarouselBitmapBean.RecordsBean recordsBean : records) {
                bannerRes.add(recordsBean.getImageUrl());
            }
            mBanner.setImages(bannerRes);
        }
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //Glide 加载图片简单用法
                Glide.with(context).load(path).centerCrop().into(imageView);
            }
        });
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setDelayTime(2000);
        mBanner.start();
    }

    private void setListener() {
        ivCurriculum.setOnClickListener(this);
        ivMechanism.setOnClickListener(this);
        ivExpert.setOnClickListener(this);
    }

    private void init(View views) {
        myHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_header, recyclerView, false);
        ivCurriculum = (ImageView) myHeaderView.findViewById(R.id.iv_curriculum);
        ivMechanism = (ImageView) myHeaderView.findViewById(R.id.iv_mechanism);
        ivExpert = (ImageView) myHeaderView.findViewById(R.id.iv2);
        recyclerView = (LoadMoreRecyclerView) views.findViewById(R.id.recycler);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
        swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) views.findViewById(R.id.movable_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        swipeRefreshLayout.setScrollUpChild(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        swipeRefreshLayout.setLoadingIndicator(true);
        //以上代码不响应onRefresh()，需要手动响应onReFresh()。
        this.onRefresh();
        //设置分割线
        recyclerView.addItemDecoration(new RecyclerItemDecoration(SizeUtils.dp2px(getActivity(), 10f), SizeUtils.dp2px(getActivity(), 6), SizeUtils.dp2px(getActivity(), 18.7f)));
        mBanner = (Banner) myHeaderView.findViewById(R.id.fragment_school_banner);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //机构按钮
            case R.id.iv_mechanism:
                OrganizationListActivity.startActivity(getActivity());
                break;
            //课程按钮
            case R.id.iv_curriculum:
                CurriculumActivity.startActivity(getActivity());
                break;
            //专家按钮
            case R.id.iv2:
                ExpertActivity.startActivity(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        schoolPresenter = new SchoolPresenter(this, mDataManager);
        return schoolPresenter;
    }

    /**
     * 重试，再次获取数据
     */
    @Override
    protected void tryAgain() {
        super.tryAgain();
        //请求网络数据
        schoolPresenter.getBannerBitmap();
    }

    /**
     * 请求失败
     */
    @Override
    public void requestError() {
        showErrorView();
    }

    @Override
    public void showDefaultCarousel() {
        //轮播默认的广告图片
        List<Integer> bannerRes = new ArrayList<>();
        bannerRes.add(R.drawable.school_fragment_banner_1);
        bannerRes.add(R.drawable.school_fragment_banner_2);
        bannerRes.add(R.drawable.school_fragment_banner_3);
        mBanner.setImages(bannerRes);
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //Glide 加载图片简单用法
                Glide.with(context).load(path).centerCrop().into(imageView);
            }
        });
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setDelayTime(2000);
        mBanner.start();
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

    /**
     * 释放状态
     */
    private void releaseState() {
        swipeRefreshLayout.setLoadingIndicator(false);
        recyclerView.setCanLoadMore(true);
    }

    /**
     * 判断该Fragment是否被影藏
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            if (mBanner != null) {
                mBanner.stopAutoPlay();
            }
        } else {
            if (mBanner != null) {
                mBanner.startAutoPlay();
            }
        }
    }

    //recyclerView点击事件的监听
    @Override
    public void clickItem(RecyclerView Parent, View view, int position) {
        CourseActivity.startActivity(getActivity(), courseList.get(position).getId());
    }

    @Override
    public void onRefresh() {
        schoolPresenter.refreshList();
    }

    @Override
    public void onLoadMore() {
        schoolPresenter.loadList();
    }
}
