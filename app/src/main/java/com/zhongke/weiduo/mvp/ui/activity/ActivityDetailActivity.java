package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import com.zhongke.weiduo.mvp.contract.ActivityDetailContract;
import com.zhongke.weiduo.mvp.model.entity.ActivityDetailBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityFlowBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityListDetailBean;
import com.zhongke.weiduo.mvp.presenter.ActivityDetailPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.ActivityDetailExpandableAdapter;
import com.zhongke.weiduo.mvp.ui.widget.layout.TransparentBGTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${xingen} on 2017/9/25.
 * <p>
 * 活动详情
 */

public class ActivityDetailActivity extends BaseMvpActivity implements ActivityDetailContract, View.OnClickListener {
    private ActivityDetailPresenter activityDetailPresenter;
    private int id;
    private String name;
    /**
     * 立即推荐按钮
     */
    private TextView recommedNowBtn;

    private NormalGSYVideoPlayer webPlayer;
    private OrientationUtils orientationUtils;
    private boolean isPlay, isPause, isSamll;

    private ExpandableListView expandableListView;
    private RelativeLayout abillity_promote;
    private TransparentBGTextView habit_tv, able_tv, project_tv, hearth_tv;
    private ImageView bg_tv;
    //    private ImageView play_icon;
    private TextView accompany_tv;
    private ActivityListDetailBean.ActionBean actionBean;
    private ActivityDetailBean activityDetailBean;
    private ActivityDetailExpandableAdapter expandableAdapter;
    private TextView unBind;

    private View headerView;
    /**
     * 活动流程
     */
    private RelativeLayout activityFlowLay;

    private List<String> groupList;
    private List<List<ActivityFlowBean.FlowDataBean>> childList;
    private boolean hasFlow;

    @Override
    protected BasePresenter createPresenter() {
        this.activityDetailPresenter = new ActivityDetailPresenter(this);
        return this.activityDetailPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_activity_detail2);
        receiverIntent();
        initView();
        this.activityDetailPresenter.loadData();
        this.activityDetailPresenter.getDeviceCount();

    }

    private void receiverIntent() {
        id = getIntent().getIntExtra("id", -1);
        if (id == -1) {
            finish();
            return;
        }
        name = getIntent().getStringExtra("name");
    }

    private TextView safe_tv, remind_tv, activity_content_tv;

    private void initView() {
        setTitleName(TextUtils.isEmpty(name) ? getResources().getString(R.string.active_detail) : name);
        groupList = new ArrayList<>();
        childList = new ArrayList<>();

        headerView = View.inflate(this, R.layout.activity_detail_header_lay, null);
        this.webPlayer = (NormalGSYVideoPlayer) headerView.findViewById(R.id.activity_detail_NormalGSYVideoPlayer);
        this.abillity_promote = (RelativeLayout) headerView.findViewById(R.id.ability_promote);
        this.bg_tv = (ImageView) headerView.findViewById(R.id.event_details_bg_tv);
        this.habit_tv = (TransparentBGTextView) headerView.findViewById(R.id.event_details_habit_tv);
        this.able_tv = (TransparentBGTextView) headerView.findViewById(R.id.event_details_able_tv);
        this.project_tv = (TransparentBGTextView) headerView.findViewById(R.id.event_details_project_tv);
        this.hearth_tv = (TransparentBGTextView) headerView.findViewById(R.id.event_details_hearth_tv);
        this.activity_content_tv = (TextView) headerView.findViewById(R.id.activity_detail_content_tv);
        this.remind_tv = (TextView) headerView.findViewById(R.id.activity_detail_remaining_tv);
        this.safe_tv = (TextView) headerView.findViewById(R.id.activity_detail_safe_tv);
        this.activityFlowLay = (RelativeLayout) headerView.findViewById(R.id.activity_detail_header_flow_lay);
        this.activityFlowLay.setOnClickListener(this);


        this.recommedNowBtn = (TextView) findViewById(R.id.activity_detail_choose_tv);
        this.accompany_tv = (TextView) findViewById(R.id.activity_detail_accompany_tv);
        this.recommedNowBtn.setOnClickListener(this);
        unBind = (TextView) findViewById(R.id.activity_detail_unbind_tv);
        unBind.setOnClickListener(this);

//        play_icon = (ImageView) findViewById(R.id.detail_video_play_icon);
//        play_icon.setOnClickListener(this);
        TextView query = (TextView) findViewById(R.id.activity_detail_query_tv);
        query.setOnClickListener(this);
        //setTransparentBGTextView();

//        this.nestedScrollView = (NestedScrollView) findViewById(R.id.activity_detail_nested_scroll_view);

        expandableListView = (ExpandableListView) findViewById(R.id.activity_detail_expandablelistview);
        expandableListView.addHeaderView(headerView);
        activityDetailBean = new ActivityDetailBean();
        activityDetailBean.dataList = new ArrayList<>();
//        expandableAdapter = new ActivityDetailExpandableAdapter(this, activityDetailBean.dataList);
        expandableAdapter = new ActivityDetailExpandableAdapter(this, groupList,childList);
        expandableListView.setAdapter(expandableAdapter);
        //expandableAdapter = new ActivityDetailExpandableAdapter(this,ActivityDetailBean.createInstance().dataList);
//        expandableAdapter = new ActivityDetailExpandableAdapter(this,activityDetailBean.dataList);
//
//        expandableListView.setAdapter(expandableAdapter);

//        this.nestedScrollView.smoothScrollTo(0, 0);
        this.showCenterView();

    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        this.activityDetailPresenter.loadData();
    }

    private void setTransparentBGTextView() {
        TransparentBGTextView.Message message1 = new TransparentBGTextView.Message();
        message1.content = "习惯";
        message1.percentage = "30%";

        TransparentBGTextView.Message message2 = new TransparentBGTextView.Message();
        message2.content = "能力";
        message2.percentage = "49%";

        TransparentBGTextView.Message message3 = new TransparentBGTextView.Message();
        message3.content = "安全指数";
        message3.percentage = "90%";

        TransparentBGTextView.Message message4 = new TransparentBGTextView.Message();
        message4.content = "身体机能";
        message4.percentage = "30%";
        habit_tv.addData(message1);
        able_tv.addData(message2);
        project_tv.addData(message3);
        hearth_tv.addData(message4);

        Glide.with(this).load(R.drawable.event_detail_bg1).asBitmap().fitCenter().into(bg_tv);
    }

    @Override
    public String getActionId() {
        return String.valueOf(id);
    }

    private void initPlayer(String url, String url2) {
        webPlayer.setUp(url == null ? "http://baobab.wdjcdn.com/14564977406580.mp4" : url, false, null, "活动视频");
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        PhotoLoaderUtil.display(ActivityDetailActivity.this, imageView, url2, null);
        webPlayer.setThumbImageView(imageView);

        webPlayer.getTitleTextView().setVisibility(View.GONE);
        webPlayer.getTitleTextView().setText("活动视频");
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

        webPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LogUtil.e(TAG, "执行横屏操作");
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                webPlayer.startWindowFullscreen(ActivityDetailActivity.this, true, true);
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
                    webPlayer.startWindowFullscreen(ActivityDetailActivity.this, true, true);
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
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }

    @Override
    public void onBackPressed() {
        if (NormalGSYVideoPlayer.backFromWindowFull(ActivityDetailActivity.this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_detail_choose_tv:
                // 立即推荐
                DesireListActivity.startActivity(ActivityDetailActivity.this, actionBean.getId(), actionBean.getTitle(), actionBean.getBeginTime(), actionBean.getEndTime());
                break;
//            case R.id.detail_video_play_icon:
//                abillity_promote.setVisibility(View.GONE);
//                play_icon.setVisibility(View.GONE);
//                if (webPlayer != null) {
//                    webPlayer.startPlayLogic();
//                }
//                break;
            case R.id.activity_detail_query_tv:
                SessionActivity2.startActivity(this, 1, id + "", name);
                break;
            case R.id.activity_detail_unbind_tv:
                UIUtils.showToast("请先绑定hapilo设备");
                break;
            case R.id.activity_detail_header_flow_lay:
                // 活动流程点击事件
                LogUtil.e("click header_lay");
                ActivityFlowActivity.openActivity(ActivityDetailActivity.this,id);
                break;
            default:

                break;
        }
    }

    @Override
    public void showData(ActivityListDetailBean detailBean) {
        actionBean = detailBean.getAction();
        initPlayer(actionBean.getVideoUrl(), actionBean.getCoverUrl());
        this.safe_tv.setText(String.valueOf(actionBean.getActionSafety()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("剩余:");
        this.remind_tv.setText(String.valueOf(actionBean.getPlanCount() - actionBean.getOrganizer()));
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("[");
        stringBuilder1.append(actionBean.getBeginTime());
        stringBuilder1.append("-");
        stringBuilder1.append(actionBean.getEndTime());
        stringBuilder1.append("] ");
        stringBuilder1.append(actionBean.getAInfo());
        this.activity_content_tv.setText(stringBuilder1);
        //获取流程
        this.activityDetailPresenter.getActivityFlowList(id + "");
    }

    @Override
    public void showError(CommonException exception) {
        UIUtils.showToast(exception.getErrorMsg());
        this.showErrorView();
    }

    @Override
    public void getActivityFlowListSuccess(ActivityFlowBean bean) {
        LogUtil.e("bean--" + bean.getFlowData().toString());
        showDetailItem(bean);
        expandableAdapter.notifyDataSetChanged();
    }

    //
    private void showDetailItem(ActivityFlowBean bean) {
        List<ActivityFlowBean.FlowDataBean> activityFlowList = bean.getFlowData();

        // 组活动资料
        groupList.add("活动资料");
        List<ActivityFlowBean.FlowDataBean> detailList = new ArrayList<>();
        // 分开组活动资料item
        ActivityFlowBean.FlowDataBean infoBean = new ActivityFlowBean.FlowDataBean();
        infoBean.setFNodeType("actDetail");
        infoBean.setFNodeName("活动时间：" + actionBean.getBeginTime());

        ActivityFlowBean.FlowDataBean infoBean2 = new ActivityFlowBean.FlowDataBean();
        infoBean2.setFNodeType("actDetail");
        infoBean2.setFNodeName("活动地点：" + actionBean.getAddress());

        ActivityFlowBean.FlowDataBean infoBean3 = new ActivityFlowBean.FlowDataBean();
        infoBean3.setFNodeType("actDetail");
        infoBean3.setFNodeName("活动人数：" + actionBean.getPlanCount()+"人");

        detailList.add(infoBean);
        detailList.add(infoBean2);
        detailList.add(infoBean3);
        childList.add(detailList);


        Map<String, List<ActivityFlowBean.FlowDataBean>> tempMap = new HashMap<>();
        for (ActivityFlowBean.FlowDataBean flowBeans : activityFlowList) {

            String noteName = flowBeans.getFNodeName().trim();
            if ("活动流程".equals(noteName)) {
                hasFlow = true;
            }
            if (!groupList.contains(noteName)) {
                // 不包含此名称，则加入
                groupList.add(noteName);
                List<ActivityFlowBean.FlowDataBean> list = new ArrayList<>();
                list.add(flowBeans);
                tempMap.put(noteName, list);
            } else {
                tempMap.get(noteName).add(flowBeans);
            }
        }

        // 组织子条目数据
        for (Map.Entry<String, List<ActivityFlowBean.FlowDataBean>> entry : tempMap.entrySet()) {
            childList.add(entry.getValue());
        }
        activityFlowLay.setVisibility(View.VISIBLE);
        expandableAdapter.notifyDataSetChanged();

//        LogUtil.e("behaviourList size" + behaviourList.size() + "rewardList size" + rewardList.size());
//        LogUtil.e("propsList size" + propsList.size() + "commentList size" + commentList.size());
//        //组织数据
//        //关键行为
//        if (behaviourList.size() > 0) {
//            ActivityDetailBean.DetailBean detailBean1 = new ActivityDetailBean.DetailBean();
//            detailBean1.childList = new ArrayList<>();
//            detailBean1.name = "关键行为";
//            DetailChildbean.Behaviour behaviour = new DetailChildbean.Behaviour();
//            detailBean1.childList.add(behaviour);
//            activityDetailBean.dataList.add(detailBean1);
//            //expandableAdapter.setBehaviourList(behaviourList);
//        }
//        //活动资料
//        ActivityDetailBean.DetailBean detailBean2 = new ActivityDetailBean.DetailBean();
//        detailBean2.childList = new ArrayList<>();
//        detailBean2.name = "活动资料";
//        DetailChildbean.Data data = new DetailChildbean.Data();
////        if (actionBean != null) {}
//        data.activityTime = actionBean.getBeginTime();
//        data.activityAddress = actionBean.getAddress();
//        data.activityCount = actionBean.getPlanCount();
//        detailBean2.childList.add(data);
//        //expandableAdapter.setDataBean(data);
//        activityDetailBean.dataList.add(detailBean2);
//
//        //活动流程
//        //活动奖励
//        if (rewardList.size() > 0) {
//            ActivityDetailBean.DetailBean detailBean3 = new ActivityDetailBean.DetailBean();
//            detailBean3.childList = new ArrayList<>();
//            detailBean3.name = "活动奖励";
//            detailBean3.childList.addAll(rewardList);
//            activityDetailBean.dataList.add(detailBean3);
//            //expandableAdapter.setRewardList(rewardList);
//        }
//        //活动道具
//        if (propsList.size() > 0) {
//            ActivityDetailBean.DetailBean detailBean4 = new ActivityDetailBean.DetailBean();
//            detailBean4.childList = new ArrayList<>();
//            detailBean4.name = "活动道具";
//            detailBean4.childList.addAll(propsList);
//            activityDetailBean.dataList.add(detailBean4);
//            //expandableAdapter.setPropsList(propsList);
//        }
//        //人员邀请
//        //活动评论
//        if (commentList.size() > 0) {
//            ActivityDetailBean.DetailBean detailBean5 = new ActivityDetailBean.DetailBean();
//            detailBean5.childList = new ArrayList<>();
//            detailBean5.name = "活动评论";
//            detailBean5.childList.addAll(commentList);
//            activityDetailBean.dataList.add(detailBean5);
//            //expandableAdapter.setCommentList(commentList);
//        }
//
//
//        LogUtil.e("behaviourList " + behaviourList.toString());
//        expandableAdapter.setBehaviourList(behaviourList);
//        expandableAdapter.setDataBean(data);
//        expandableAdapter.setRewardList(rewardList);
//        LogUtil.e("rewardList " + rewardList.toString());
//        expandableAdapter.setPropsList(propsList);
//        LogUtil.e("propsList " + propsList.toString());
//        expandableAdapter.setCommentList(commentList);

//        expandableAdapter.notifyDataSetChanged();
//        expandableListView.setAdapter(expandableAdapter);
    }

    @Override
    public void getActivityFlowListFailed(CommonException e) {
        UIUtils.showToast(e.getErrorMsg());
    }

    @Override
    public void getDeviceCountSuccess() {
        recommedNowBtn.setVisibility(View.VISIBLE);
        unBind.setVisibility(View.GONE);

    }

    @Override
    public void getDeviceCountFailed(CommonException e) {
        UIUtils.showToast(e.getErrorMsg());
    }

    public static void startActivity(Context context, int id, String name) {
        Intent intent = new Intent(context, ActivityDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }
}
