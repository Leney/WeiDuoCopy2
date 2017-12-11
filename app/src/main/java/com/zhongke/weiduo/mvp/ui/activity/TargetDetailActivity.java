package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.TargetDetailContract;
import com.zhongke.weiduo.mvp.control.UserInfoManager;
import com.zhongke.weiduo.mvp.model.entity.RecommendSchemeBean;
import com.zhongke.weiduo.mvp.model.entity.TargetDetailBean2;
import com.zhongke.weiduo.mvp.presenter.TargetDetailPresenter;
import com.zhongke.weiduo.mvp.ui.fragment.TargetCommentFragment;
import com.zhongke.weiduo.mvp.ui.fragment.TargetDetailFragment;
import com.zhongke.weiduo.mvp.ui.widget.ModuleMessageLayout;
import com.zhongke.weiduo.mvp.ui.widget.ModuleMessageView;
import com.zhongke.weiduo.mvp.ui.widget.view.MyScrollView;


/**
 * 目标详情界面
 * Created by llj on 2017/9/16.
 */

public class TargetDetailActivity extends BaseMvpActivity implements TargetDetailContract, View.OnClickListener, MyScrollView.OnScrollChangeListener {
    private final int DETAIL_TAB = 0;
    private final int COMMENT_TAB = 1;
    private TargetDetailPresenter mPresenter;
    private TargetDetailFragment detailFragment;
    private TargetCommentFragment commentFragment;

    private FragmentManager fragmentManager;

    private MyScrollView mScrollView;
    private LinearLayout scrollLay;
    private NormalGSYVideoPlayer webPlayer;
    private TextView name, price, simpleDes;
    private Button askTeacherBtn, addBtn;
    private ModuleMessageLayout manageLay;
    /**
     * 两个只是tab  中间的和顶部悬浮的
     */
    private LinearLayout centerTabLay, topTabLay;
    private LinearLayout detailLay, commentLay, topDetailLay, topCommentLay;
    private TextView detailText, commentText, topDetailText, topCommentText;
    private View detailPoint, commentPoint, topDetailPoint, topCommentPoint;

    private OrientationUtils orientationUtils;

    private boolean isPlay, isPause, isSamll;

    private TargetDetailBean2 mDetailBean;

    private int id;

    //    private List<SchemeDetailBean> recommendList;
    // 推荐的规划列表
    private RecommendSchemeBean mRecommendSchemeBean;

    private int statusBarHeight = 0;

    private int titleHeight = 0;

//    /**
//     * 跑马灯控件
//     */
//    private MarqueeView topMarqueeView;

    @Override
    protected BasePresenter createPresenter() {
        return mPresenter = new TargetDetailPresenter(TargetDetailActivity.this, mDataManager, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_target_detail);
        String name = getIntent().getStringExtra("name");
        setTitleName(TextUtils.isEmpty(name) ? getResources().getString(R.string.target_detail) : name);
        id = getIntent().getIntExtra("id", -1);
        if (id <= 0) {
            finish();
            return;
        }
//        recommendList = new ArrayList<>();
        initView();
        mPresenter.getDetailInfo(id);
    }


    private void initView() {
        fragmentManager = getSupportFragmentManager();

//        topMarqueeView = (MarqueeView) findViewById(R.id.scheme_detail_marqueeView);
//        topMarqueeView.startMarquee();
//        topMarqueeView.setOnClickListener(this);

        mScrollView = (MyScrollView) findViewById(R.id.scheme_detail_scroll_view);
        mScrollView.setScrollListener(this);
        scrollLay = (LinearLayout) findViewById(R.id.scheme_detail_lay);
        webPlayer = (NormalGSYVideoPlayer) findViewById(R.id.scheme_detail_player);
        name = (TextView) findViewById(R.id.scheme_detail_name);
        price = (TextView) findViewById(R.id.scheme_detail_price);
        simpleDes = (TextView) findViewById(R.id.scheme_detail_simple_des);
        askTeacherBtn = (Button) findViewById(R.id.scheme_detail_ask_teacher_btn);
        askTeacherBtn.setOnClickListener(this);
        addBtn = (Button) findViewById(R.id.scheme_detail_add_btn);
        addBtn.setOnClickListener(this);
        manageLay = (ModuleMessageLayout) findViewById(R.id.scheme_detail_manage_lay);


        centerTabLay = (LinearLayout) findViewById(R.id.scheme_detail_center_tab_lay);
        centerTabLay.setVisibility(View.VISIBLE);
        topTabLay = (LinearLayout) findViewById(R.id.scheme_detail_top_tab_lay);
        topTabLay.setVisibility(View.GONE);

        detailLay = (LinearLayout) findViewById(R.id.scheme_detail_detail_tab);
        detailLay.setOnClickListener(this);
        commentLay = (LinearLayout) findViewById(R.id.scheme_detail_comment_tab);
        commentLay.setOnClickListener(this);
        detailText = (TextView) findViewById(R.id.scheme_detail_detail_text);
        commentText = (TextView) findViewById(R.id.scheme_detail_comment_text);
        detailPoint = findViewById(R.id.scheme_detail_detail_point);
        commentPoint = findViewById(R.id.scheme_detail_comment_point);

        topDetailLay = (LinearLayout) findViewById(R.id.scheme_detail_top_detail_tab);
        topDetailLay.setOnClickListener(this);
        topCommentLay = (LinearLayout) findViewById(R.id.scheme_detail_top_comment_tab);
        topCommentLay.setOnClickListener(this);
        topDetailText = (TextView) findViewById(R.id.scheme_detail_top_detail_text);
        topCommentText = (TextView) findViewById(R.id.scheme_detail_top_comment_text);
        topDetailPoint = findViewById(R.id.scheme_detail_top_detail_point);
        topCommentPoint = findViewById(R.id.scheme_detail_top_comment_point);


//        changeTab(DETAIL_TAB);

        scrollLay.setFocusable(true);
        scrollLay.setFocusableInTouchMode(true);
        scrollLay.requestFocus();
    }

    private void changeTab(int index) {
        int curScrollX = mScrollView.getScrollX();
        int curScrollY = mScrollView.getScrollY();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (index == DETAIL_TAB) {
            detailText.setTextColor(ContextCompat.getColor(this, R.color.main_color));
            detailPoint.setBackgroundColor(ContextCompat.getColor(this, R.color.main_color));
            detailPoint.setVisibility(View.VISIBLE);
            commentText.setTextColor(ContextCompat.getColor(this, R.color.text_333333));
            commentPoint.setVisibility(View.INVISIBLE);

            topDetailText.setTextColor(ContextCompat.getColor(this, R.color.main_color));
            topDetailPoint.setBackgroundColor(ContextCompat.getColor(this, R.color.main_color));
            topDetailPoint.setVisibility(View.VISIBLE);
            topCommentText.setTextColor(ContextCompat.getColor(this, R.color.text_333333));
            topCommentPoint.setVisibility(View.INVISIBLE);

            if (commentFragment != null) {
                transaction.hide(commentFragment);
            }

            if (detailFragment == null) {
                detailFragment = TargetDetailFragment.newInstance(mDetailBean, mRecommendSchemeBean);
                transaction.add(R.id.scheme_detail_fragment_lay, detailFragment, "TargetDetailFragment");
            } else {
                transaction.show(detailFragment);
            }
            transaction.commitAllowingStateLoss();
        } else if (index == COMMENT_TAB) {
            commentText.setTextColor(ContextCompat.getColor(this, R.color.main_color));
            commentPoint.setBackgroundColor(ContextCompat.getColor(this, R.color.main_color));
            commentPoint.setVisibility(View.VISIBLE);
            detailText.setTextColor(ContextCompat.getColor(this, R.color.text_333333));
            detailPoint.setVisibility(View.INVISIBLE);

            topCommentText.setTextColor(ContextCompat.getColor(this, R.color.main_color));
            topCommentPoint.setBackgroundColor(ContextCompat.getColor(this, R.color.main_color));
            topCommentPoint.setVisibility(View.VISIBLE);
            topDetailText.setTextColor(ContextCompat.getColor(this, R.color.text_333333));
            topDetailPoint.setVisibility(View.INVISIBLE);

            transaction.hide(detailFragment);
            if (commentFragment == null) {
                commentFragment = TargetCommentFragment.newInstance(id);
//                commentFragment.setScrollView(mScrollView);
                transaction.add(R.id.scheme_detail_fragment_lay, commentFragment, "CommentFragment");
            } else {
                transaction.show(commentFragment);
            }
            transaction.commitAllowingStateLoss();
        }
        mScrollView.smoothScrollTo(curScrollX, curScrollY);

        scrollLay.setFocusable(true);
        scrollLay.setFocusableInTouchMode(true);
        scrollLay.requestFocus();
    }

    @Override
    public void getDetailSuccess(TargetDetailBean2 detailBean) {
        // 获取详情数据成功
        this.mDetailBean = detailBean;
        initPlayer(mDetailBean);
        name.setText(mDetailBean.getTarget().getName());
//        price.setText(mDetailBean.getPrice() <= 0f ? getResources().getString(R.string.free) : detailBean.getPrice() + "");
        simpleDes.setText(mDetailBean.getTarget().getInfo());

        mPresenter.getRecommendList(detailBean.getTarget().getId());
        showCenterView();
    }

    @Override
    public void getDetailFailed(CommonException e) {
        // 获取详情数据失败
        showErrorView();
    }

    @Override
    public void getRecommendListSuccess(RecommendSchemeBean recommendSchemeBean) {
        // 获取推荐计划列表成功
        this.mRecommendSchemeBean = recommendSchemeBean;
        changeTab(DETAIL_TAB);
    }

    @Override
    public void getRecommendListFailed(CommonException e) {
        // 获取推荐规划列表失败，(即使是失败了行为数据也有,所以正常显示)
        LogUtil.e("llj", "获取推荐规划列表失败 code---->>>" + e.getCode() + "---msg---->>" + e.getErrorMsg());
        changeTab(DETAIL_TAB);
    }

    @Override
    public void addTargetSuccess() {
        // 添加目标成功
        // TODO 看逻辑处理  是否需要在添加成功之后才播放添加的动画
        LogUtil.i("llj", "添加目标成功！！");

        UserInfoManager.getInstance().setTargetNum(UserInfoManager.getInstance().getTargetNum() + 1);
        manageLay.updateMessageWithAnimator(addBtn, ModuleMessageView.ModuleMessage.MODEL_TARGET, UserInfoManager.getInstance().getTargetNum());
    }

    @Override
    public void addTargetFailed(CommonException e) {
        // 添加目标失败
        Toast.makeText(this, e.getErrorMsg(), Toast.LENGTH_SHORT).show();
//        // 因为在请求网络之前已经加1，所以这里要减1
//        UserInfoManager.getInstance().setTargetNum(UserInfoManager.getInstance().getTargetNum() - 1);
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        // 重试
        mPresenter.getDetailInfo(id);
    }


    private void initPlayer(TargetDetailBean2 bean) {
        if(bean.getTarget().getVideoUrl() != null){
            webPlayer.setUp(bean.getTarget().getVideoUrl(), false, null, bean.getTarget().getName());
        }else {
            webPlayer.setUp("", false, null, bean.getTarget().getName());
        }
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        PhotoLoaderUtil.display(TargetDetailActivity.this, imageView, bean.getTarget().getImageUrl(), null);
        webPlayer.setThumbImageView(imageView);

        webPlayer.getTitleTextView().setVisibility(View.GONE);
        webPlayer.getTitleTextView().setText(bean.getTarget().getName());
        webPlayer.getBackButton().setVisibility(View.GONE);

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
                webPlayer.startWindowFullscreen(TargetDetailActivity.this, true, true);
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause && !isSamll) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!webPlayer.isIfCurrentIsFullscreen()) {
                    webPlayer.startWindowFullscreen(TargetDetailActivity.this, true, true);
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
    public void onBackPressed() {
        if (NormalGSYVideoPlayer.backFromWindowFull(TargetDetailActivity.this)) return;
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scheme_detail_ask_teacher_btn:
                // 咨询专家
                break;
            case R.id.scheme_detail_add_btn:
                // 添加
//                manageLay.updateMessageWithAnimator(v, ModuleMessageView.ModuleMessage.MODEL_TARGET,1);
                mPresenter.addTarget(id);
                break;
            case R.id.scheme_detail_detail_tab:
                // 中间详情tab点击
                changeTab(DETAIL_TAB);
                break;
            case R.id.scheme_detail_comment_tab:
                // 中间评论tab点击
                changeTab(COMMENT_TAB);
                break;
            case R.id.scheme_detail_top_detail_tab:
                // 顶部悬浮详情tab点击
                changeTab(DETAIL_TAB);
                break;
            case R.id.scheme_detail_top_comment_tab:
                // 顶部悬浮评论tab点击
                changeTab(COMMENT_TAB);
                break;
//            case R.id.scheme_detail_marqueeView:
//                // 顶部跑马灯
//                FullScreenPlayVideoActivity.startActivity(TargetDetailActivity.this, "http://baobab.wdjcdn.com/14564977406580.mp4", "");
//                topMarqueeView.setVisibility(View.GONE);
//                break;
        }
    }

    public static void startActivity(Context context, int id, String name) {
        Intent intent = new Intent(context, TargetDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        // ScrollView 滚动监听
        int[] location = new int[2];
        centerTabLay.getLocationOnScreen(location);
        int xPosition = location[0];
        int yPosition = location[1];
//        Log.d("ScrollViewActivity", "yPosition:" + yPosition);
        if (statusBarHeight <= 0) {
            statusBarHeight = getStatusBarHeight();
        }
        if (titleHeight <= 0) {
            titleHeight = baseTitle.getMeasuredHeight();
        }
//        int statusBarHeight = getStatusBarHeight();
//        Log.d("ScrollViewActivity", "statusBarHeight:" + statusBarHeight);
        if (yPosition <= (statusBarHeight + titleHeight)) {
            topTabLay.setVisibility(View.VISIBLE);
        } else {
            topTabLay.setVisibility(View.GONE);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
