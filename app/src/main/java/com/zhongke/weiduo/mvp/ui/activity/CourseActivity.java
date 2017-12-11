package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.lqr.audio.AudioPlayManager;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.listener.SampleListener;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CourseContract;
import com.zhongke.weiduo.mvp.model.entity.CatalogueBean;
import com.zhongke.weiduo.mvp.model.entity.CatalogueVideoBean;
import com.zhongke.weiduo.mvp.model.entity.TabItem;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;
import com.zhongke.weiduo.mvp.presenter.CoursePresenter;
import com.zhongke.weiduo.mvp.ui.fragment.CourseCatalogueFragment;
import com.zhongke.weiduo.mvp.ui.fragment.CourseDetailFragment;
import com.zhongke.weiduo.mvp.ui.fragment.CourseEvaluationFragment;
import com.zhongke.weiduo.mvp.ui.fragment.CourseMovementFragment;
import com.zhongke.weiduo.mvp.ui.widget.layout.TabItemChangeLayout;
import com.zhongke.weiduo.util.ViewIdUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/14.
 * 课程详情界面，里面装入详情，目录，评价，活动四个Fragment
 */

public class CourseActivity extends BaseMvpActivity implements CourseContract, ViewPager.OnPageChangeListener, CourseCatalogueFragment.OnChangeClassListener {
    private NormalGSYVideoPlayer webPlayer;
    /**
     * 视频封面图片
     */
    private ImageView thumbImg;
    private TabItemChangeLayout tabItemChangeLayout;
    private CoursePresenter coursePresenter;
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();

    private OrientationUtils orientationUtils;

    private boolean isPlay, isPause, isSmall;

    /**
     * 课程目录列表
     */
    private List<CatalogueBean> catalogueList;

    private List<CourseDetailResult.CatalogBean> catalogBeanList;

    /**
     * 当前选中的目录position
     */
    private int curSelectPosition = -1;

    /**
     * 课程id
     */
    private int id;

    @Override
    protected BasePresenter createPresenter() {
        return coursePresenter = new CoursePresenter(this, mDataManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName(getResources().getString(R.string.curriculum_details));
        this.id = getIntent().getIntExtra("stepId", -1);
        if (id == -1) {
            finish();
            return;
        }
        setCenterLay(R.layout.activity_course);
        catalogueList = new ArrayList<>();
        catalogBeanList = new ArrayList<>();
        init();
        showCenterView();

        coursePresenter.getCourseDetail(id+"");

    }

    private void init() {

        webPlayer = (NormalGSYVideoPlayer) findViewById(R.id.web_player);

        fragmentList.add((Fragment) CourseDetailFragment.newInstance(id));
        fragmentList.add((Fragment) CourseCatalogueFragment.newInstance(id,this));
        fragmentList.add((Fragment) new CourseEvaluationFragment());
        fragmentList.add((Fragment) CourseMovementFragment.newInstance(id));
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        tabItemChangeLayout = (TabItemChangeLayout) findViewById(R.id.tab_item_layout);
        List<TabItem> list = new ArrayList<>();
        TabItem ti1 = new TabItem();
        ti1.setViewId(ViewIdUtils.getViewId());
        ti1.setViewName("详情");
        TabItem ti2 = new TabItem();
        ti2.setViewId(ViewIdUtils.getViewId());
        ti2.setViewName("目录");
        TabItem ti3 = new TabItem();
        ti3.setViewId(ViewIdUtils.getViewId());
        ti3.setViewName("评价");
        TabItem ti4 = new TabItem();
        ti4.setViewId(ViewIdUtils.getViewId());
        ti4.setViewName("活动");
        list.add(ti1);
        list.add(ti2);
        list.add(ti3);
        list.add(ti4);
        tabItemChangeLayout.addItemTab(list);
        tabItemChangeLayout.setChangeListener(new TabItemChangeLayout.TabChangeListener() {
            @Override
            public void changeTab(int currentTab) {
                for (int i = 0; i < list.size(); i++) {
                    if (currentTab == list.get(i).getViewId()) {
                        viewPager.setCurrentItem(i);
                    }
                }
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabItemChangeLayout.setCurrentSelect(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause && !isSmall) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!webPlayer.isIfCurrentIsFullscreen()) {
                    webPlayer.startWindowFullscreen(CourseActivity.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (webPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
        AudioPlayManager.getInstance().stopPlay();
        // mPresenter.saveDraft();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void getCourseDetailSuccess(CourseDetailResult courseDetailResult) {

        LogUtil.e("getCourseDetailSuccess");
        this.catalogBeanList.addAll(courseDetailResult.getCatalog());
        CourseDetailResult.CourseBean courseBean = courseDetailResult.getCourse();

//        ((CourseCatalogueFragment) fragmentList.get(1)).setCatalugueList(catalogueList);
        if (this.catalogBeanList.isEmpty()) return;
        // 默认设置第一节课内容
        curSelectPosition = 0;

        // 获取视频地址等信息成功
        LogUtil.e("video url--"+courseBean.getIntroduceVideo()+"CourseName--"+courseBean.getCourseName());
        if (courseBean.getIntroduceVideo() != null && courseBean.getCourseName() !=null) {
            webPlayer.setUp(courseBean.getIntroduceVideo(), false, courseBean.getCourseName());
        }
        //增加封面
        thumbImg = new ImageView(this);
        thumbImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        thumbImg.setImageResource(R.drawable.wugeng);
        LogUtil.e("Cover url--"+ courseBean.getCoverUrl());
        PhotoLoaderUtil.display(this, thumbImg, courseBean.getCoverUrl(), null);
        webPlayer.setThumbImageView(thumbImg);

        //增加title
        webPlayer.getTitleTextView().setVisibility(View.GONE);
        webPlayer.getTitleTextView().setText(courseBean.getCourseName());
        webPlayer.getBackButton().setVisibility(View.GONE);
//        resolveNormalVideoUI();

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, webPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        webPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        webPlayer.setRotateViewAuto(false);
        webPlayer.setLockLand(false);
        webPlayer.setShowFullAnimation(false);
        webPlayer.setNeedLockFull(true);
        //detailPlayer.setOpenPreView(true);
        webPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LogUtil.e(TAG, "执行横屏操作");
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                webPlayer.startWindowFullscreen(CourseActivity.this, true, true);
            }
        });

        webPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                LogUtil.i("llj","退出全屏");
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        webPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });

    }

    @Override
    public void getCourseDetailFailed() {

    }

    @Override
    public void onBackPressed() {
        if(NormalGSYVideoPlayer.backFromWindowFull(CourseActivity.this)) return;
        super.onBackPressed();
    }

    @Override
    public void onChangeClass(int position) {
        // 通过目录Fragment切换了播放图片
        LogUtil.i("llj", "lastSelectPosition------>>>" + curSelectPosition + "\nposition---->>>" + position);
        if (curSelectPosition == position) return;
        // 设置上次选择的为不选择状态
        catalogBeanList.get(curSelectPosition).setSelect(false);

        CourseDetailResult.CatalogBean bean = catalogBeanList.get(position);
        coursePresenter.getVideoUrl(bean.getResId()+"",bean.getType()+"");

        curSelectPosition = position;
        bean.setSelect(true);
    }

    @Override
    public void getVideoUrlSuccess(CatalogueVideoBean bean) {
        if (!TextUtils.isEmpty(bean.getVideoUrl())) {
            webPlayer.setUp(bean.getVideoUrl(), false, /*!TextUtils.isEmpty(bean.getName())? bean.getName():*/"没有课程名称");
        }
        //PhotoLoaderUtil.display(this, thumbImg, bean.getThumpUrl(), null);
    }

    @Override
    public void getVideoUrlFailed(CommonException e) {
        UIUtils.showToast(e.getErrorMsg());
    }


    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    public static void startActivity(Context context, int stepId/*, String stepName*/) {
        Intent intent = new Intent(context, CourseActivity.class);
        intent.putExtra("stepId", stepId);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }
}
