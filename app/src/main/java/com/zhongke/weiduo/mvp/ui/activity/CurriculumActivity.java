package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CurriculumActivityContract;
import com.zhongke.weiduo.mvp.model.entity.CurriculumBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseBannerResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseListResult;
import com.zhongke.weiduo.mvp.presenter.CurriculumPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.CurriculumRecyclerAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.RecyclerItemDecoration;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.LoadMoreRecyclerView;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;
import com.zhongke.weiduo.util.SizeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程列表界面
 * Created by ${tanlei} on 2017/6/23.
 */

public class CurriculumActivity extends BaseMvpActivity implements CurriculumActivityContract, CurriculumRecyclerAdapter.OnItemClickListeners,
        SwipeRefreshLayout.OnRefreshListener,LoadMoreRecyclerView.LoadMoreListener {
    private CurriculumPresenter curriculumPresenter;

    private Banner mBanner;
    private CurriculumRecyclerAdapter curriculumRecyclerAdapter;
    private View curriculumHeaderView;
    private List<CurriculumBean> curriculumBeenList;

    private List<CourseListResult.RecordsBean> courseList;
    private int pageIndex;
    private LoadMoreRecyclerView recyclerView2;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    public static final int REFRESH_STATUS = 0;
    public static final int LOAD_STATUS = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_curriculum_lay);

        pageIndex = 1;
        init();
        initBanner();

        curriculumPresenter.getCourseBanner();
        curriculumPresenter.getCourseTable(pageIndex,REFRESH_STATUS);
    }

    @Override
    protected BasePresenter createPresenter() {
        return curriculumPresenter = new CurriculumPresenter(this, mDataManager);
    }

    private void init() {
        findViewById(R.id.tv).setVisibility(View.GONE);
        curriculumHeaderView = LayoutInflater.from(this).inflate(R.layout.recycler_curriculum_header, null);
        setTitleName(getString(R.string.curriculum));

        swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) findViewById(R.id.curriculum_refresh_layout);

        recyclerView2 = (LoadMoreRecyclerView) findViewById(R.id.activity_curriculum_loadmore_recyclerview);
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
        //设置分割线
        recyclerView2.addItemDecoration(new RecyclerItemDecoration(SizeUtils.dp2px(this, 10f), SizeUtils.dp2px(this, 6), SizeUtils.dp2px(this, 18.7f)));
        mBanner = (Banner) curriculumHeaderView.findViewById(R.id.fragment_school_banner);
        //curriculumBeenList = new ArrayList<>();
        courseList  = new ArrayList<>();
        curriculumRecyclerAdapter = new CurriculumRecyclerAdapter(this, courseList);
        curriculumRecyclerAdapter.setOnItemClickListeners(this);
        recyclerView2.setAdapter(curriculumRecyclerAdapter);
        curriculumRecyclerAdapter.setmHeaderView(curriculumHeaderView);

        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        swipeRefreshLayout.setScrollUpChild(recyclerView2);
        swipeRefreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        swipeRefreshLayout.setLoadingIndicator(true);
        //以上代码不响应onRefresh()，需要手动响应onReFresh()。
        this.onRefresh();
    }
    /**
     * 初始化Banner
     */
    private void initBanner() {
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //Glide 加载图片简单用法
                Glide.with(context).load(path).into(imageView);
            }
        });
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setDelayTime(2000);
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

    @Override
    public void getBannerSuccess(List<CourseBannerResult.RecordsBean> recordsBeanList) {
        List<String> urlList = new ArrayList<>();
        if (!recordsBeanList.isEmpty()) {
            for(int i = 0,size = recordsBeanList.size();i < size;i++) {
                urlList.add(recordsBeanList.get(i).getImageUrl());
            }
        } else {
            urlList.add("http://img.hb.aicdn.com/10d71b7b48fa693ba3c8d54c77c30f23a1ef8f6137cde-5iv274_fw658");
            urlList.add("http://image.tupian114.com/20160804/10303227.jpg");
            urlList.add("http://pic23.nipic.com/20120915/7612599_191200494310_2.jpg");
        }

        mBanner.setImages(urlList);
        mBanner.start();
    }

    @Override
    public void getCourseTableSuccess(CourseListResult courseListResult,int refreshStatus) {
        releaseState();
        if (courseListResult.getRecords() != null) {
            showCenterView();
        } else {
            //为空而且是请求第一页数据时候
            if (courseListResult.getPageIndex() <= 1) {
                showNoDataView();
            }
        }
        pageIndex = courseListResult.getPageIndex();
        if (refreshStatus == REFRESH_STATUS) {
            courseList.clear();
            courseList.addAll(courseListResult.getRecords());
        } else {
            courseList.addAll(courseListResult.getRecords());
        }
        curriculumRecyclerAdapter.notifyDataSetChanged();
    }

    public void getCourseTableFailed(CommonException e) {
        if (pageIndex <= 1) {
            // 请求第一页数据失败
            showErrorView();
        } else {
            // 请求下一页数据失败
        }
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        curriculumPresenter.getCourseTable(1,REFRESH_STATUS);
    }

    @Override
    public void clickItem(RecyclerView Parent, View view, int position) {
        CourseActivity.startActivity(this, courseList.get(position).getId());
    }

    public static void startActivity(Context context/*, int stepId, String stepName*/) {
        Intent intent = new Intent(context, CurriculumActivity.class);
//        intent.putExtra("stepId", stepId);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }

    @Override
    public void onRefresh() {
        //下拉  刷新
        pageIndex = 1;
        curriculumPresenter.getCourseTable(pageIndex,REFRESH_STATUS);
    }

    @Override
    public void onLoadMore() {
        pageIndex++;
        curriculumPresenter.getCourseTable(pageIndex,LOAD_STATUS);
    }

    //重置状态
    private void releaseState() {
        swipeRefreshLayout.setLoadingIndicator(false);
        recyclerView2.setCanLoadMore(true);
    }

}
