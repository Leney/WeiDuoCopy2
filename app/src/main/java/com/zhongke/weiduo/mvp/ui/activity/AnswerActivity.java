package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.im.extra.ExtraMessage;
import com.zhongke.weiduo.im.extra.message.ActivityStart;
import com.zhongke.weiduo.im.extra.message.JoinActivity;
import com.zhongke.weiduo.im.extra.message.QuestionResult;
import com.zhongke.weiduo.im.extra.message.QuestionScramble;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AnswerContract;
import com.zhongke.weiduo.mvp.model.entity.JoinMember;
import com.zhongke.weiduo.mvp.model.entity.Paper;
import com.zhongke.weiduo.mvp.presenter.AnswerPresenter;
import com.zhongke.weiduo.mvp.ui.widget.dialog.AnswerFailureDialog;
import com.zhongke.weiduo.mvp.ui.widget.dialog.AnswerRewardDialog;
import com.zhongke.weiduo.mvp.ui.widget.dialog.ReceiveAwardDialog;
import com.zhongke.weiduo.mvp.ui.widget.dialog.TimeOutDialog;
import com.zhongke.weiduo.mvp.ui.widget.view.CountClock;
import com.zhongke.weiduo.mvp.ui.widget.view.OptionsLayout;
import com.zhongke.weiduo.mvp.ui.widget.view.PartInLayout;
import com.zk.Json;
import com.zk.ZkNetEvent;
import com.zk.net.message.ExtCmdResponseMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 观看答题界面
 * Created by llj on 2017/8/31.
 */

public class AnswerActivity extends BaseMvpActivity implements AnswerContract {

    private static final String TAG = AnswerActivity.class.getSimpleName();

    private AnswerPresenter mPresenter;


    private TextView question;

    private CountClock countClock;

    private OptionsLayout optionsLay;

    private PartInLayout partInLayout;

    /**
     * 超时dialog
     */
    private TimeOutDialog timeOutDialog;
    /**
     * 奖励dialog
     */
    private AnswerRewardDialog rewardDialog;
    /**
     * 回答错误dialog
     */
    private AnswerFailureDialog failureDialog;

//    /**
//     * 整套题的内容列表
//     */
//    private List<TopicBean> topicList;
    /**
     * 试卷内容(题库)
     */
    private Paper mPaper;

    /**
     * 活动id
     */
    private int actionId;

    /**
     * 此抢答活动所生成的groupId
     */
    private String groupId;

    /**
     * 参与人员列表
     */
    private List<JoinMember.ActUserListBean> partInMemberList;


    /**
     * 下一题
     */
    private static final int NEXT_CODE = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == NEXT_CODE) {
                // 显示下一题
                Bundle bundle = msg.getData();
                int position = bundle.getInt("nextPosition", -1);
                if (position < 0) {
                    return;
                }
                if (rewardDialog != null) {
                    rewardDialog.dismiss();
                }
                if (failureDialog != null) {
                    failureDialog.dismiss();
                }
                if (timeOutDialog != null) {
                    timeOutDialog.dismiss();
                }
                // 切换题目
                changeNewTopic(position);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        actionId = getIntent().getIntExtra("actionId", -1);
        if (actionId < 0) {
            finish();
            return;
        }
        groupId = "Answer_" + actionId;

        baseTitle.setVisibility(View.GONE);
        setCenterLay(R.layout.activity_answer);


        question = (TextView) findViewById(R.id.answer_question);
        countClock = (CountClock) findViewById(R.id.answer_count_down_view);
        optionsLay = (OptionsLayout) findViewById(R.id.answer_options_lay);
        partInLayout = (PartInLayout) findViewById(R.id.part_in_list_lay);

        // 获取题库信息
        mPresenter.getTopicList(actionId);
        showCenterView();

        EventBus.getDefault().register(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return mPresenter = new AnswerPresenter(this);
    }

    @Override
    public void getTopicListSuccess(Paper paper) {
        mPaper = paper;
        if (mPaper.isStart()) {
            // 活动已经开始
            Log.i("llj", "活动已经开始");
        } else {
            // 活动还没有开始
            Log.i("llj", "活动还没有开始");
        }
        // 设置题目名称
        question.setText("请稍候...");
        countClock.setVisibility(View.INVISIBLE);
        // 获取当前参与人员的列表
        mPresenter.getMembersList(actionId);
    }

    @Override
    public void getTopicListFailed(CommonException e) {
        showErrorView();
    }

    @Override
    public void getJoinMemberSuccess(JoinMember member) {
        if (partInMemberList == null) {
            partInMemberList = new ArrayList<>();
        }
        partInMemberList.clear();
        partInMemberList.addAll(member.getActUserList());
        // 添加视图
        partInLayout.addViews(partInMemberList);
    }

    @Override
    public void getJoinMemberFailed(CommonException e) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ZkNetEvent event) {
        Log.i("llj", "接收到消息-type----》》》" + event.netEventType);
        switch (event.netEventType) {
            case MSG_EXT_REQ_EVENT:
                // 扩展消息类型(发送消息)
                LogUtil.i(TAG, "------>>发送扩展消息");
                break;
            case MSG_EXT_RES_EVENT:
                // 扩展消息类型(接收消息)
                LogUtil.i(TAG, "------>>接收到扩展消息");
                doExtMessage(event);
                break;
            case CHANNEL_EXCEPTION://套接字通道异常
                LogUtil.e(TAG, "-------->套接字通道异常！");
                IMClient.onReconnect();
                break;
            case CHANNEL_INTERRUPT://套接字通道被关闭中断
                LogUtil.e(TAG, "-------->套接字通道被关闭中断！");
                break;
            case CHANNEL_CONNECT_SUCCESS://套接字通道连接成功
                LogUtil.i(TAG, "-------->套接字通道连接成功！！");
                break;
            case CHANEEL_AUTH_SECCUSS://通道鉴权成功
                LogUtil.i(TAG, "-------->通道鉴权成功！");
                // 通道鉴权成功重新加入群聊
//                IMClient.joinGroup(GROUP_ID);
//                noNetTips.setVisibility(View.GONE);
//                // 加入群组
//                joinGroups(groupIdList);
//                // 获取离线消息
//                getOfflineMessage();

                break;
            case NETWORK_WIFI_STATE_CHANGE://WIFI状态变化
//                UIUtils.showToastSafely("wifi网络连接异常");
//                noNetTips.setVisibility(View.VISIBLE);
                break;
            case NETWORK_WIFT_CONNECT_ROUTER_CHANGE://WIFI连接路由状态变化
                IMClient.onReconnect();
                break;
            case NETWORK_INFO_CHANGE://网络信息变化包含移动和WIFI的变化
                if (event.eventData != null) {
                    NetworkInfo activeNetInfo = (NetworkInfo) event.eventData;
                    if (activeNetInfo.isAvailable() && activeNetInfo.isConnected()) {
                        // 重新连接
                        IMClient.onReconnect();
                        return;
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 处理即时通讯扩展协议消息
     *
     * @param event
     */
    private void doExtMessage(ZkNetEvent event) {
        if (event == null || event.extData == null) return;
        ExtCmdResponseMessage extCmdResponseMessage = (ExtCmdResponseMessage) event.extData;
        String chatContent = extCmdResponseMessage.chatContent;
        ExtraMessage extraMessage = null;
        try {
            extraMessage = Json.toBean(chatContent, ExtraMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (extraMessage != null) {
            // 新定义出来的协议类型
            switch (extraMessage.code) {
                case ExtraMessage.activity_start:
                    // 活动开始的消息
                    // 开始显示第一题
                    ActivityStart start = (ActivityStart) extraMessage.message;
                    if (start.activityId.equals(actionId + "")) {
                        // 显示第一题
                        changeNewTopic(0);
                    }
                    break;
                case ExtraMessage.qustion_scramble:
                    // 谁抢答到了的消息(抢答结果)
                    QuestionScramble scramble = (QuestionScramble) extraMessage.message;
                    raceResult(scramble);
                    break;
                case ExtraMessage.question_result:
                    // 答题结果
                    // TODO 显示相关答题结果展示界面，并且界面展示多久时间，判断是否还有下一题，处理活动结束相关逻辑
                    QuestionResult result = (QuestionResult) extraMessage.message;
                    answerResult(result);
                    break;
                case ExtraMessage.join_activity:
                    // 有人加入活动(观战人员或者参加答题人员)
                    // 重新去获取参加人员列表
                    JoinActivity join = (JoinActivity) extraMessage.message;
                    if (join.activityId == actionId) {
                        // 是此活动
                        mPresenter.getMembersList(actionId);
                    }
                    break;
            }
        }
    }


    /**
     * 切换答题题目
     *
     * @param position
     */
    private void changeNewTopic(int position) {
        Paper.QutionBean qutionBean = mPaper.getQution().get(position);
//        countClock.start(30);
        // 设置题目名称
        question.setText(qutionBean.getName());
        // 设置题目选项
        optionsLay.setOptions(qutionBean);
        // 设置没有答题人员
        partInLayout.setAnswerPosition(-1);
        // 倒计时控件暂时隐藏
        countClock.setVisibility(View.INVISIBLE);
        // TODO 是否需要显示正在抢答状态的标识
    }

    /**
     * 处理有人抢到答题权的消息
     *
     * @param scramble
     */
    private void raceResult(QuestionScramble scramble) {
        // 设置正在答题的人员
        partInLayout.setAnswerUserId(scramble.userId);
        // 设置倒计时时间
        countClock.start(scramble.countdownTime);
        countClock.setVisibility(View.VISIBLE);
    }

    /**
     * 处理答题结果的消息
     *
     * @param result
     */
    private void answerResult(QuestionResult result) {
        //显示相关答题结果展示界面，并且界面展示多久时间，判断是否还有下一题，处理活动结束相关逻辑
        // 拿到对方所答题目的对象
        Paper.QutionBean qutionBean = mPaper.getQution().get(result.currentQuestionId);
        // 当前所显示的题目对象
        Paper.QutionBean curQutionBean = optionsLay.getCurQutionBean();
        // 对比两个题目对象是否是同一题
        countClock.stop();
        if (qutionBean.getId() == curQutionBean.getId()) {
            // 是同一题目
            LogUtil.i("llj", "是同一题目!!!");
            if (result.answerResult < 0) {
                // 答题超时
                if (timeOutDialog == null) {
                    timeOutDialog = new TimeOutDialog(AnswerActivity.this);
//                    timeOutDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                        @Override
//                        public void onCancel(DialogInterface dialogInterface) {
//                            if (failureDialog == null) {
//                                failureDialog = new AnswerFailureDialog(AnswerActivity.this);
//                                failureDialog.setCanceledOnTouchOutside(true);
//                            }
//                            failureDialog.show();
//                        }
//                    });
                }
                timeOutDialog.show();
            } else {
                // 获得选项答案对象
                Paper.QutionBean.AnswerBean answerBean = curQutionBean.getAnswer().get(result.answerResult);
                if (answerBean.getIsRight() == 1) {
                    // 回答正确
                    if (rewardDialog == null) {
                        rewardDialog = new AnswerRewardDialog(AnswerActivity.this);
                        rewardDialog.setCanceledOnTouchOutside(true);
                        rewardDialog.show();
                    } else {
                        rewardDialog.reStartShow();
                    }
                } else {
                    // 回答错误
                    if (failureDialog == null) {
                        failureDialog = new AnswerFailureDialog(AnswerActivity.this);
                        failureDialog.setCanceledOnTouchOutside(true);
                        failureDialog.show();
                    } else {
                        failureDialog.reStartShow();
                    }
                }
            }
        } else {
            // 不是同一题目
            LogUtil.i("llj", "不是同一题目!!!");
        }

        if (result.coutdownTime <= 0) {
            // 没有下一题了，答题结束
            LogUtil.i("llj", "没有下一题了，答题结束!!!");
            if (rewardDialog != null) {
                rewardDialog.dismiss();
            }
            if (failureDialog != null) {
                failureDialog.dismiss();
            }
            if (timeOutDialog != null) {
                timeOutDialog.dismiss();
            }
            // 弹出排名dialog
            ReceiveAwardDialog receiveAwardDialog = new ReceiveAwardDialog(AnswerActivity.this);
            receiveAwardDialog.show();
        } else {
            // 还有下一题，延迟显示下一题视图
            LogUtil.i("llj", "还有下一题，延迟显示下一题视图,延迟时间----->>>" + result.coutdownTime + "秒");
            Message message = new Message();
            message.what = NEXT_CODE;
            Bundle bundle = new Bundle();
            bundle.putInt("nextPosition", result.nextQuestionId);
            message.setData(bundle);
            mHandler.sendMessageDelayed(message, result.coutdownTime * 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countClock != null) {
            countClock.stop();
        }
        EventBus.getDefault().unregister(this);
    }

    public static void startActivity(Context context, int actionId) {
        Intent intent = new Intent(context, AnswerActivity.class);
        intent.putExtra("actionId", actionId);
        context.startActivity(intent);
    }
}
