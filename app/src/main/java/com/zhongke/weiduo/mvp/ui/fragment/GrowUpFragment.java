package com.zhongke.weiduo.mvp.ui.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.zhongke.weiduo.mvp.base.BaseMvpFragment;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.GrowUpFragmentContract;
import com.zhongke.weiduo.mvp.control.UserInfoManager;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.WeiDuoHomeBean;
import com.zhongke.weiduo.mvp.presenter.GrowUpFragmentPresenter;
import com.zhongke.weiduo.mvp.ui.activity.ActivityDetailActivity;
import com.zhongke.weiduo.mvp.ui.activity.DesireListActivity;
import com.zhongke.weiduo.mvp.ui.activity.EducationSystemActivity;
import com.zhongke.weiduo.mvp.ui.activity.MovableListActivity;
import com.zhongke.weiduo.mvp.ui.activity.MyPlanListActivity;
import com.zhongke.weiduo.mvp.ui.activity.MySchemeListActivity;
import com.zhongke.weiduo.mvp.ui.activity.MyTargetListActivity;
import com.zhongke.weiduo.mvp.ui.activity.PlanDetailActivity;
import com.zhongke.weiduo.mvp.ui.activity.PlanListActivity;
import com.zhongke.weiduo.mvp.ui.activity.ScheduleActivity;
import com.zhongke.weiduo.mvp.ui.activity.SchemeDetailActivity;
import com.zhongke.weiduo.mvp.ui.activity.SchemeListActivity;
import com.zhongke.weiduo.mvp.ui.activity.SystemDetailsActivity;
import com.zhongke.weiduo.mvp.ui.widget.BaseTitleView;
import com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout.ScrollChildSwipeRefreshLayout;
import com.zhongke.weiduo.mvp.ui.widget.view.MyScrollView;
import com.zhongke.weiduo.mvp.ui.widget.view.PointView;
import com.zhongke.weiduo.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;



/**
 * 微舵主页(成长系统主页)Fragment
 * Created by llj on 2017/9/20.
 */

public class GrowUpFragment extends BaseMvpFragment implements GrowUpFragmentContract, View.OnClickListener, UserInfoManager.OnUserInfoChangeListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = GrowUpFragment.class.getSimpleName();
    private GrowUpFragmentPresenter mPresenter;

    private BaseTitleView mTitleView;

    private Banner mBanner;

    private PointView myTarget, myScheme, myPlan, myWish, myActive;
    private ImageView systemMoreBtn, schemeMoreBtn, planMoreBtn, activeMoreBtn;
    private LinearLayout systemItemLay, schemeItemLay, planItemLay, activeItemLay;


    private List<WeiDuoHomeBean.StructureBean> systemList;
    private List<WeiDuoHomeBean.BPlanBean> schemeList;
    private List<WeiDuoHomeBean.SPlanBean> planList;
    private List<WeiDuoHomeBean.ActionBean> activeList;

//    /** 导航按钮*/
//    private TextView navigationAct,navigationSystem,navigationScheme,navigationPlan;
    /**
     * 推荐标题
     */
    private TextView actTitle, systemTitle, schemeTitle, planTitle;

    /**
     * 位置
     */
    private int actLocation, systemLocation, schemeLocation, planLocation;

    private MyScrollView mScrollView;
    private int bannerLocation;

    @Override
    protected BasePresenter createPresenter() {
        return mPresenter = new GrowUpFragmentPresenter(this, mDataManager);
    }

//    private RadioButton[] controll_radioButtons = new RadioButton[5];

    //    @Override
//    public void updatedUserAcount(int bPlanCount, int sPlanCount, int wishCount, int scheduleCount, int targetCount) {
//        this.myScheme.setNum(bPlanCount);
//        this.myPlan.setNum(sPlanCount);
//        this.myWish.setNum(wishCount);
//        this.myActive.setNum(scheduleCount);
//        this.myTarget.setNum(targetCount);
//    }
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void initView(View baseView) {
        super.initView(baseView);
        setCenterLay(R.layout.fragment_grow_up_main);
        this.swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) baseView.findViewById(R.id.grow_up_fragment_swipe_refresh_layout);
        this.swipeRefreshLayout.setRefreshColor();
        this.swipeRefreshLayout.setOnRefreshListener(this);
        this.swipeRefreshLayout.setLoadingIndicator(true);
        this.onRefresh();

        systemList = new ArrayList<>();
        schemeList = new ArrayList<>();
        planList = new ArrayList<>();
        activeList = new ArrayList<>();
        UserInfoManager.getInstance().registerOnUserInfoChangeListener(this);

        mTitleView = (BaseTitleView) baseView.findViewById(R.id.grow_up_fragment_title);
        mBanner = (Banner) baseView.findViewById(R.id.grow_up_fragment_banner);
        myTarget = (PointView) baseView.findViewById(R.id.grow_up_fragment_my_target);
        myTarget.setNum(0);
        myTarget.setOnClickListener(this);
        myScheme = (PointView) baseView.findViewById(R.id.grow_up_fragment_my_scheme);
        myScheme.setOnClickListener(this);
        myScheme.setNum(0);
        myPlan = (PointView) baseView.findViewById(R.id.grow_up_fragment_my_plan);
        myPlan.setOnClickListener(this);
        myPlan.setNum(0);
        myWish = (PointView) baseView.findViewById(R.id.grow_up_fragment_my_wish);
        myWish.setOnClickListener(this);
        myWish.setNum(0);
        myActive = (PointView) baseView.findViewById(R.id.grow_up_fragment_my_active);
        myActive.setOnClickListener(this);
        myActive.setNum(0);
        systemItemLay = (LinearLayout) baseView.findViewById(R.id.grow_up_fragment_system_lay);
        schemeItemLay = (LinearLayout) baseView.findViewById(R.id.grow_up_fragment_scheme_lay);
        planItemLay = (LinearLayout) baseView.findViewById(R.id.grow_up_fragment_plan_lay);
        activeItemLay = (LinearLayout) baseView.findViewById(R.id.grow_up_fragment_active_lay);


//        controll_radioButtons[0] = (RadioButton) baseView.findViewById(R.id.grow_up_fragment_top_navigation);
//        controll_radioButtons[1] = (RadioButton) baseView.findViewById(R.id.grow_up_fragment_act_navigation);
//        controll_radioButtons[2] = (RadioButton) baseView.findViewById(R.id.grow_up_fragment_system_navigation);
//        controll_radioButtons[3] = (RadioButton) baseView.findViewById(R.id.grow_up_fragment_scheme_navigation);
//        controll_radioButtons[4] = (RadioButton) baseView.findViewById(R.id.grow_up_fragment_plan_navigation);
//        for (int i = 0; i < controll_radioButtons.length; ++i) {
//            controll_radioButtons[i].setOnClickListener(this);
//        }
        actTitle = (TextView) baseView.findViewById(R.id.grow_up_fragment_active_title);
        actTitle.setOnClickListener(this);
        systemTitle = (TextView) baseView.findViewById(R.id.grow_up_fragment_system_title);
        systemTitle.setOnClickListener(this);
        schemeTitle = (TextView) baseView.findViewById(R.id.grow_up_fragment_scheme_title);
        schemeTitle.setOnClickListener(this);
        planTitle = (TextView) baseView.findViewById(R.id.grow_up_fragment_plan_title);
        planTitle.setOnClickListener(this);
        mScrollView = (MyScrollView) baseView.findViewById(R.id.grow_up_fragment_scroll_view);
//        mScrollView.setOnTouchListener((v, event) -> {
//            if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                scrollResult();
//            }
//            return false;
//        });
        mTitleView.setLeftImgVisible(false);
        mTitleView.setTitleName(getResources().getString(R.string.app_name));

        showCenterView();


        this.swipeRefreshLayout.setScrollUpChild(mScrollView);
    }

    @Override
    public void getDataSuccess(List<WeiDuoHomeBean.StructureBean> systemList, List<WeiDuoHomeBean.BPlanBean> schemeList, List<WeiDuoHomeBean.SPlanBean> planList, List<WeiDuoHomeBean.ActionBean> activeList) {

        setUserInfo();

        this.systemList = systemList;
        this.schemeList = schemeList;
        this.planList = planList;
        this.activeList = activeList;

        addSystemItems();
        addSchemeItems();
        addPlanItems();
        addActiveItems();
        this.swipeRefreshLayout.setLoadingIndicator(false);
    }


    @Override
    public void getDataFailed(int errorCode, String msg) {

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

    @Override
    public void showDefaultCarousel() {
        //轮播默认的广告图片
        List<Integer> bannerRes = new ArrayList<>();
        bannerRes.add(R.drawable.grow_up_banner_image_1);
        bannerRes.add(R.drawable.grow_up_banner_image_2);
        bannerRes.add(R.drawable.grow_up_banner_image_3_modify);
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
    public void onDestroy() {
        super.onDestroy();
        UserInfoManager.getInstance().unregisterOnUserInfoChangeListener(this);
    }

    /**
     * 设置用户相关信息
     */
    private void setUserInfo() {
        this.myTarget.setNum(UserInfoManager.getInstance().getTargetNum());
        this.myWish.setNum(UserInfoManager.getInstance().getWishNum());
        this.myScheme.setNum(UserInfoManager.getInstance().getSchemeNum());
        this.myPlan.setNum(UserInfoManager.getInstance().getPlanNum());
        this.myActive.setNum(UserInfoManager.getInstance().getActiveNum());
    }


    /**
     * 添加体系item
     */
    private void addSystemItems() {
        double length = systemList.size();
        // 得到行数
        double lineNum = Math.ceil(length / 2);
        // 行布局数组
        LinearLayout[] lineLayouts = new LinearLayout[(int) lineNum];
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lineParams.bottomMargin = DisplayUtils.dip2px(getActivity(), 8);
        systemItemLay.removeAllViews();
        for (int i = 0; i < lineLayouts.length; i++) {
            lineLayouts[i] = new LinearLayout(getActivity());
            lineLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
            systemItemLay.addView(lineLayouts[i], lineParams);
        }

        int curLine = -1;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        params.leftMargin = DisplayUtils.dip2px(getActivity(), 4);
        params.rightMargin = params.leftMargin;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                curLine++;
            }
            WeiDuoHomeBean.StructureBean systemBean = systemList.get(i);
            View itemView = View.inflate(getActivity(), R.layout.grow_up_main_system_item_lay, null);
            ImageView icon = (ImageView) itemView.findViewById(R.id.grow_up_main_system_item_icon);
            TextView name = (TextView) itemView.findViewById(R.id.grow_up_main_system_item_name);
            PhotoLoaderUtil.display(getActivity(), icon, systemBean.getCoverUrl(), null);
            name.setText(systemBean.getBName());
            itemView.setTag(systemBean);
            itemView.setOnClickListener(v -> {
                // 跳转到体系详情界面
                WeiDuoHomeBean.StructureBean bean = (WeiDuoHomeBean.StructureBean) v.getTag();
                SystemDetailsActivity.startActivity(getActivity(), bean.getId(), bean.getBName(), "text");
            });

            lineLayouts[curLine].addView(itemView, params);

            if (i % 2 == 0 && i >= length - 1) {
                // 最后一排是单个item,添加一个空白View占位置
                lineLayouts[curLine].addView(new View(getActivity()), params);
            }
        }
    }


    /**
     * 添加规划item
     */
    private void addSchemeItems() {
        double length = schemeList.size();
        // 得到行数
        double lineNum = Math.ceil(length / 2);
        // 行布局数组
        LinearLayout[] lineLayouts = new LinearLayout[(int) lineNum];
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lineParams.bottomMargin = DisplayUtils.dip2px(getActivity(), 8);
        schemeItemLay.removeAllViews();
        for (int i = 0; i < lineLayouts.length; i++) {
            lineLayouts[i] = new LinearLayout(getActivity());
            lineLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
            schemeItemLay.addView(lineLayouts[i], lineParams);
        }

        int curLine = -1;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        params.leftMargin = DisplayUtils.dip2px(getActivity(), 4);
        params.rightMargin = params.leftMargin;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                curLine++;
            }
            WeiDuoHomeBean.BPlanBean schemeBean = schemeList.get(i);
            View itemView = View.inflate(getActivity(), R.layout.grow_up_main_scheme_item_lay, null);
            ImageView icon = (ImageView) itemView.findViewById(R.id.grow_up_main_scheme_item_icon);
            TextView name = (TextView) itemView.findViewById(R.id.grow_up_main_scheme_item_name);
            TextView useCount = (TextView) itemView.findViewById(R.id.grow_up_main_scheme_item_use_count);
            PhotoLoaderUtil.display(getActivity(), icon, schemeBean.getCoverUrl(), null);
            name.setText(schemeBean.getBName());
            useCount.setText(String.format(getResources().getString(R.string.format_use_count), "10"));
            itemView.setTag(schemeBean);
            itemView.setOnClickListener(v -> {
                // 跳转到规划详情界面
                WeiDuoHomeBean.BPlanBean bean = (WeiDuoHomeBean.BPlanBean) v.getTag();
                SchemeDetailActivity.startActivity(getActivity(), bean.getId(), bean.getBName());

            });
            lineLayouts[curLine].addView(itemView, params);

            if (i % 2 == 0 && i >= length - 1) {
                // 最后一排是单个item,添加一个空白View占位置
                lineLayouts[curLine].addView(new View(getActivity()), params);
            }
        }
    }

    /**
     * 添加计划item
     */
    private void addPlanItems() {
        double length = planList.size();
        // 得到行数
        double lineNum = Math.ceil(length / 3);
        // 行布局数组
        LinearLayout[] lineLayouts = new LinearLayout[(int) lineNum];
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lineParams.bottomMargin = DisplayUtils.dip2px(getActivity(), 8);
        planItemLay.removeAllViews();
        for (int i = 0; i < lineLayouts.length; i++) {
            lineLayouts[i] = new LinearLayout(getActivity());
            lineLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
            planItemLay.addView(lineLayouts[i], lineParams);
        }

        int curLine = -1;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        params.leftMargin = DisplayUtils.dip2px(getActivity(), 4);
        params.rightMargin = params.leftMargin;
        for (int i = 0; i < length; i++) {
            if (i % 3 == 0) {
                curLine++;
            }
            WeiDuoHomeBean.SPlanBean planBean = planList.get(i);
            View itemView = View.inflate(getActivity(), R.layout.grow_up_main_plan_item_lay, null);
            ImageView icon = (ImageView) itemView.findViewById(R.id.grow_up_main_plan_item_icon);
            PhotoLoaderUtil.display(getActivity(), icon, planBean.getCoverUrl(), null);
            itemView.setTag(planBean);
            itemView.setOnClickListener(v -> {
                // 跳转到计划详情界面
                WeiDuoHomeBean.SPlanBean bean = (WeiDuoHomeBean.SPlanBean) v.getTag();
                PlanDetailActivity.startActivity(getActivity(), bean.getId(), bean.getBName());
            });

            lineLayouts[curLine].addView(itemView, params);

            if (i >= length - 1) {
                if (i % 3 == 0) {
                    // 需要填补两个空白视图
                    lineLayouts[curLine].addView(new View(getActivity()), params);
                    lineLayouts[curLine].addView(new View(getActivity()), params);
                } else if (i % 3 == 1) {
                    // 需要填补一个空白视图
                    lineLayouts[curLine].addView(new View(getActivity()), params);
                }
            }
        }
    }

    /**
     * 添加活动item
     */
    private void addActiveItems() {
        double length = activeList.size();


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        activeItemLay.removeAllViews();
        for (int i = 0; i < length; i++) {
            WeiDuoHomeBean.ActionBean activeBean = activeList.get(i);
            View itemView = View.inflate(getActivity(), R.layout.grow_up_main_active_item_lay, null);
            ImageView icon = (ImageView) itemView.findViewById(R.id.grow_up_main_active_item_icon);
            TextView name = (TextView) itemView.findViewById(R.id.grow_up_main_active_item_name);
            TextView location = (TextView) itemView.findViewById(R.id.grow_up_main_active_item_location);
            TextView userCount = (TextView) itemView.findViewById(R.id.grow_up_main_active_user_item_count);
            TextView time = (TextView) itemView.findViewById(R.id.grow_up_main_active_item_time);

            PhotoLoaderUtil.display(getActivity(), icon, activeBean.getCoverUrl(), null);
            name.setText(activeBean.getTitle());
            location.setText(false ? getResources().getString(R.string.hapilo_location) : activeBean.getAddress());
            userCount.setText(String.format(getResources().getString(R.string.format_user_count), activeBean.getPlanCount() + ""));
            time.setText(activeBean.getBeginTime());
            itemView.setTag(activeBean);
            itemView.setOnClickListener(v -> {
                //TODO 跳转到活动详情界面
                WeiDuoHomeBean.ActionBean bean = (WeiDuoHomeBean.ActionBean) v.getTag();
                ActivityDetailActivity.startActivity(getActivity(), bean.getId(), bean.getTitle());
            });

            activeItemLay.addView(itemView, params);

        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grow_up_fragment_my_target:
                // 我的目标
                MyTargetListActivity.startActivity(getActivity());
                break;
            case R.id.grow_up_fragment_my_scheme:
                // 我的规划
                MySchemeListActivity.startActivity(getActivity());
                break;
            case R.id.grow_up_fragment_my_plan:
                // 我的计划
                MyPlanListActivity.startActivity(getActivity());
                break;
            case R.id.grow_up_fragment_my_wish:
                // 愿望
                DesireListActivity.startActivity(getActivity());
                break;
            case R.id.grow_up_fragment_my_active:
                // 日程活动
                ScheduleActivity.startActivity(getActivity());
                break;
            // 体系更多
            case R.id.grow_up_fragment_system_title:
                EducationSystemActivity.startActivity(getActivity());
                break;
            // 规划更多
            case R.id.grow_up_fragment_scheme_title:
                SchemeListActivity.startActivity(getActivity());
                break;
            // 计划更多
            case R.id.grow_up_fragment_plan_title:
                PlanListActivity.startActivity(getActivity());
                break;
            // 活动更多
            case R.id.grow_up_fragment_active_title:
                MovableListActivity.startActivity(getActivity());
                break;
//            //顶部
//            case R.id.grow_up_fragment_top_navigation:
//                if (currentSelectPosition == 0) {
//                    return;
//                }
//                currentSelectPosition = 0;
//                Log.i(TAG, " onCheckedChanged 顶部  ");
//                mScrollView.smoothScrollTo(0, bannerLocation);
//                break;
//            //活动
//            case R.id.grow_up_fragment_act_navigation:
//
//                if (currentSelectPosition == 1) {
//                    return;
//                }
//                currentSelectPosition = 1;
//                Log.i(TAG, " onCheckedChanged 活动  ");
//                mScrollView.smoothScrollTo(0, actLocation);
//
//                break;
//            //体系
//            case R.id.grow_up_fragment_system_navigation:
//
//                if (currentSelectPosition == 2) {
//                    return;
//                }
//                currentSelectPosition = 2;
//                Log.i(TAG, " onCheckedChanged 体系  ");
//                mScrollView.smoothScrollTo(0, systemLocation);
//
//                break;
//            //规划
//            case R.id.grow_up_fragment_scheme_navigation:
//
//                if (currentSelectPosition == 3) {
//                    return;
//                }
//                currentSelectPosition = 3;
//                Log.i(TAG, " onCheckedChanged 规划  ");
//                mScrollView.smoothScrollTo(0, schemeLocation);
//
//                break;
//            //计划
//            case R.id.grow_up_fragment_plan_navigation:
//
//                if (currentSelectPosition == 4) {
//                    return;
//                }
//                currentSelectPosition = 4;
//                Log.i(TAG, " onCheckedChanged 计划  ");
//                mScrollView.smoothScrollTo(0, planLocation);
//
//                break;

            default:

                break;
        }
    }

    private int currentSelectPosition = -1;

    private void calculationPosition() {
        bannerLocation = mBanner.getTop();

        actLocation = actTitle.getTop();
        systemLocation = systemTitle.getTop();
        schemeLocation = schemeTitle.getTop();
        planLocation = planTitle.getTop();
    }

//    private void setCheck() {
//        for (int i = 0; i < controll_radioButtons.length; ++i) {
//            RadioButton radioButton = controll_radioButtons[i];
//            radioButton.setChecked((currentSelectPosition == i) ? true : false);
//        }
//    }

//    /**
//     * 滚动结果，判断是否选中什么。
//     */
//    private void scrollResult() {
//        calculationPosition();
//        int scroll = mScrollView.getScrollY();
//        //ScrollView实际展现值
//        int scroll_y = scroll + mScrollView.getHeight();
//        if (scroll_y >= bannerLocation && scroll_y < actLocation) {
//            if (currentSelectPosition == 0) {
//                return;
//            }
//            Log.i(TAG, " scrollResult 顶部  ");
//            currentSelectPosition = 0;
//            setCheck();
//        } else if (scroll_y >= actLocation && scroll_y < systemLocation) {
//            if (currentSelectPosition == 1) {
//                return;
//            }
//            Log.i(TAG, " scrollResult 活动  ");
//            currentSelectPosition = 1;
//            setCheck();
//        } else if (scroll_y >= systemLocation & scroll_y < schemeLocation) {
//            if (currentSelectPosition == 2) {
//                return;
//            }
//            Log.i(TAG, " scrollResult 体系 ");
//            currentSelectPosition = 2;
//            setCheck();
//        } else if (scroll_y >= schemeLocation && scroll_y < planLocation) {
//            if (currentSelectPosition == 3) {
//                return;
//            }
//            Log.i(TAG, " scrollResult 规划 ");
//            currentSelectPosition = 3;
//            setCheck();
//        } else if (scroll_y >= planLocation) {
//            if (currentSelectPosition == 4) {
//                return;
//            }
//            Log.i(TAG, " scrollResult 计划 ");
//            currentSelectPosition = 4;
//            setCheck();
//        } else {
//            if (currentSelectPosition == -1) {
//                return;
//            }
//            currentSelectPosition = -1;
//            setCheck();
//        }
//    }


    @Override
    public void onUserInfoChange() {
        // 用户信息发生改变的监听
        setUserInfo();
    }

    @Override
    public void onRefresh() {
        mPresenter.getData();
        mPresenter.getBannerBitmap();
    }
}
