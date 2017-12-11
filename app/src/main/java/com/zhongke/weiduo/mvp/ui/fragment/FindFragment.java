package com.zhongke.weiduo.mvp.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.FindFragmentContract;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.FindClassifyBean;
import com.zhongke.weiduo.mvp.model.entity.FindRecommendBean;
import com.zhongke.weiduo.mvp.presenter.FindFragmentPresenter;
import com.zhongke.weiduo.mvp.ui.activity.FindClassifyActivity;
import com.zhongke.weiduo.mvp.ui.adapter.FindActAdapter;
import com.zhongke.weiduo.mvp.ui.widget.BaseTitleView;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.LoadMoreRecyclerView;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;
import com.zhongke.weiduo.mvp.ui.widget.view.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * 发现Fragment
 * Created by llj on 2017/9/4.
 */

public class FindFragment extends BaseMvpFragment implements FindFragmentContract, FindActAdapter.LikeClickListener, SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.LoadMoreListener {
    public  static final String TAG = FindFragment.class.getSimpleName();

    private FindFragmentPresenter mPresenter;
    /**
     * 标题
     */
    private BaseTitleView mTitleView;
    private LoadMoreRecyclerView mRecyclerView;
    private FindActAdapter mAdapter;
    private View headerView;
    private Banner headerBanner;
    /** 类别数据集合*/
    private List<FindClassifyBean.RecordsBean> findClassifyList;
    /** 推荐活动数据集合*/
    private List<FindRecommendBean.RecordsBean> recommendList;
    private int clickLikePosition;
    private ScrollChildSwipeRefreshLayout refreshLayout;
    private int pageIndex = 1;
    private static final int PAGE_SIZE= 16;

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new FindFragmentPresenter(this, mDataManager);
        return mPresenter;
    }

    @Override
    protected void initView(View baseView) {
        super.initView(baseView);
        setCenterLay(R.layout.fragment_find);
        mTitleView = (BaseTitleView) baseView.findViewById(R.id.find_fragment_title);
        mTitleView.setTitleName(getResources().getString(R.string.tab4));
        //mTitleView.setRightImgRes(R.drawable.search_large);
        mTitleView.setRightVisible(false);

        mTitleView.setLeftImgVisible(false);
        findClassifyList= new ArrayList<>();
        recommendList = new ArrayList<>();

        mRecyclerView = (LoadMoreRecyclerView) baseView.findViewById(R.id.find_fragment_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        // 设置自定义的分割线
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity(),1));
        refreshLayout = (ScrollChildSwipeRefreshLayout) baseView.findViewById(R.id.fragment_find_refresh_layout);
        refreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        refreshLayout.setScrollUpChild(mRecyclerView);
        refreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        refreshLayout.setLoadingIndicator(true);
        mRecyclerView.setLoadmoreListener(this);


        headerView = View.inflate(getActivity(), R.layout.find_fragment_header, null);
        headerBanner = (Banner) headerView.findViewById(R.id.fragment_find_banner);
//        headerHorScrollView = (HorizontalScrollView) headerView.findViewById(R.id.fragment_find_horizontal_scrollview);
        mAdapter = new FindActAdapter(getActivity());

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setLikeClickListener(this);
        mAdapter.setHeaderView(headerView);

        mPresenter.getBannerBitmap();
        mPresenter.getActivityType();
        this.onRefresh();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (headerBanner != null) {
            headerBanner.stopAutoPlay();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (headerBanner != null) {
                headerBanner.stopAutoPlay();
            }
        } else {
            if (headerBanner != null) {
                headerBanner.startAutoPlay();
            }
        }
    }

    private void setUpBanner() {
        headerBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //Glide 加载图片简单用法
                Glide.with(context).load(path).into(imageView);
            }
        });
        headerBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        headerBanner.setDelayTime(2000);
        headerBanner.start();
    }

    //获取banner数据成功
    @Override
    public void getBannerBitmapSuccess(CarouselBitmapBean bean) {
        if (!bean.getRecords().isEmpty()) {
            List<String> list = new ArrayList<>();
            for (CarouselBitmapBean.RecordsBean recordsBean : bean.getRecords()) {
                list.add(recordsBean.getImageUrl());
            }
            headerBanner.setImages(list);
            setUpBanner();
//            headerBanner.setOnBannerListener(new OnBannerListener() {
//                @Override
//                public void OnBannerClick(int position) {
//                    // banner点击事件
//                    //LogUtil.i(TAG,"banner描述-------->>>"+bean.getRecords().get(position).getBannerName());
//                }
//            });
        } else {
            showDefaultBanner();
        }
    }

    @Override
    public void getBannerBitmapFailed(CommonException e) {
        showDefaultBanner();
    }

    //设置默认的banner
    private void showDefaultBanner() {
        String url1 = "http://img.taopic.com/uploads/allimg/100924/169-1009241401420.jpg";
        String url2 = "http://pic42.nipic.com/20140609/18702850_151330225000_2.jpg";
        String url3 = "http://pic102.nipic.com/file/20160628/23300679_201052027000_2.jpg";
        List<String> bannerUrl = new ArrayList<>();
        bannerUrl.add(url1);
        bannerUrl.add(url2);
        bannerUrl.add(url3);

        headerBanner.setImages(bannerUrl);
        setUpBanner();
    }
    //获取活动类别成功
    @Override
    public void getActivityTypeSuccess(FindClassifyBean bean) {
        findClassifyList.clear();
        findClassifyList.addAll(bean.getRecords());
        // 水平控件设置内容
        LinearLayout horItemLay = (LinearLayout) headerView.findViewById(R.id.fragment_find_hor_item_lay);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);
        String iconUrl = "";
        int k = 0;
        for (int i = 0; i < findClassifyList.size(); i++) {
            FindClassifyBean.RecordsBean item = findClassifyList.get(i);
            View view = View.inflate(getActivity(),R.layout.find_header_hor_adapter,null);
            ImageView icon = (ImageView) view.findViewById(R.id.find_header_hor_item_icon);
            TextView name = (TextView) view.findViewById(R.id.find_header_hor_item_name);

            if (k == 0) {
                iconUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504534905884&di=3c84895c585f19f280158a5b5b0358ee&imgtype=0&src=http%3A%2F%2Fimg10.3lian.com%2Fshow2015%2F2%2F16%2F83.jpg";
            } else if (k == 1) {
                iconUrl = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1996274561,3454009669&fm=27&gp=0.jpg";
            } else if (k == 2) {
                iconUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504534850868&di=4cdd7d07470306c58b89df86b02ded56&imgtype=0&src=http%3A%2F%2Fimage.tupian114.com%2F20140522%2F09592009.png.thumb.jpg";
            } else if (k == 3) {
                iconUrl= "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3590298943,1108087221&fm=27&gp=0.jpg";
            }
            k++;
            if (k > 3) {
                k = 0;
            }
            PhotoLoaderUtil.displayRoundImage(getActivity(), icon, iconUrl, ContextCompat.getDrawable(getActivity(), R.drawable.default_img));
            name.setText(item.getKindName());
            view.setTag(item);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FindClassifyBean.RecordsBean recordsBean = (FindClassifyBean.RecordsBean) v.getTag();
                    FindClassifyActivity.startActivity(getActivity(),recordsBean.getId()+"",recordsBean.getKindName());
                    //UIUtils.showToast("点击了item");
                }
            });
            horItemLay.addView(view,params);
        }
    }

    @Override
    public void getActivityTypeFailed(CommonException e) {

    }

    //获取推荐列表成功
    @Override
    public void getRecommendSuccess(FindRecommendBean bean) {
        if (bean.getPageIndex() <= 1) {
            if (bean.getRecords().isEmpty()) {
                //请求第一页没有数据
                showNoDataView();
            } else {
                showCenterView();
                releaseState();
            }
        }
        if (pageIndex < bean.getPageTotal()) {
            pageIndex++;
            mRecyclerView.setCanLoadMore(true);
        } else {
            mRecyclerView.setCanLoadMore(false);
        }
        recommendList.addAll(bean.getRecords());
        mAdapter.addDatas(recommendList);
    }

    @Override
    public void getRecommendFailed(CommonException e) {
        if (pageIndex <= 1) {
            showErrorView();
        }
        releaseState();
    }

    //点赞成功
    @Override
    public void clickLikeSuccess() {
        mAdapter.notifyItemChanged(clickLikePosition,"");
    }

    @Override
    public void clickLikeFailed() {

    }

    @Override
    public void likeClick(int position,boolean hasHead) {
        if (hasHead) {
            clickLikePosition = position + 1;
        } else {
            clickLikePosition = position;
        }
        int actionId = recommendList.get(position).getId();
        mPresenter.clickLike(actionId+"");
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        reloadList();
    }

    private void reloadList() {
        showLoadingView();
        pageIndex = 1;
        recommendList.clear();
        mPresenter.getRecommend(pageIndex,PAGE_SIZE);
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        recommendList.clear();
        mPresenter.getRecommend(pageIndex,PAGE_SIZE);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getRecommend(pageIndex,PAGE_SIZE);
    }
    //释放状态
    private void releaseState() {
        refreshLayout.setLoadingIndicator(false);
        mRecyclerView.setCanLoadMore(true);
    }
}
