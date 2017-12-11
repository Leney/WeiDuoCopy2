package com.zhongke.weiduo.mvp.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hitomi.tilibrary.transfer.Transferee;
import com.lqr.audio.AudioPlayManager;
import com.lqr.audio.AudioRecordManager;
import com.lqr.audio.IAudioRecordListener;
import com.lqr.emoji.EmotionKeyboard;
import com.lqr.emoji.EmotionLayout;
import com.lqr.emoji.IEmotionExtClickListener;
import com.lqr.emoji.IEmotionSelectedListener;
import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.bean.ImageItem;
import com.lqr.imagepicker.ui.ImageGridActivity;
import com.lqr.imagepicker.ui.ImagePreviewActivity;
import com.lqr.recyclerview.LQRRecyclerView;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.common.AppConst;
import com.zhongke.weiduo.app.listener.SampleListener;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.contract.ISessionAtView;
import com.zhongke.weiduo.mvp.control.RecentChatListManager;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.model.entity.Session;
import com.zhongke.weiduo.mvp.presenter.SessionAtPresenter2;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zk.Json;
import com.zk.ZkNetEvent;
import com.zk.ZkRetCode;
import com.zk.android.sqlite.ZkLocalMessage;
import com.zk.net.ZkMessagePacket;
import com.zk.net.message.AckS2CMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import top.zibin.luban.Luban;


/**
 * Created by Karma on 2017/6/21.
 * 类描述：单聊、群聊、直播页面
 */

public class SessionActivity2 extends BaseMvpActivity implements ISessionAtView, IEmotionSelectedListener, BGARefreshLayout.BGARefreshLayoutDelegate {
    private static final String TAG = "SessionActivity2";
    public static final int REQUEST_IMAGE_PICKER = 1000;
    public final static int REQUEST_TAKE_PHOTO = 1001;
    public final static int REQUEST_MY_LOCATION = 1002;
    public final static int REQUEST_UPLOAD_DATA = 1003;

    /**
     * 单聊
     */
    public final static int SESSION_TYPE_PRIVATE = 1;
    /**
     * 群组群聊
     */
    public final static int SESSION_TYPE_GROUP = 2;
    /**
     * 家庭群聊
     */
    public final static int SESSION_TYPE_FAMILY = 4;
    /**
     * 视频直播
     */
    public final static int SESSION_TYPE_VIDEO = 3;


    /**
     * 聊天对象的id(好友、群组、直播室)
     */
    private String sessionId = "";
    /**
     * 聊天类型
     */
    private int sessionType = 1;
    /**
     * 聊天对象名称
     */
    private String sessionName;

    private SessionAtPresenter2 mPresenter;

    @Bind(R.id.llRoot)
    LinearLayout mLlRoot;
    @Bind(R.id.rvMsg)
    LQRRecyclerView mRvMsg;
    @Bind(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    @Bind(R.id.ivAudio)
    ImageView mIvAudio;
    @Bind(R.id.etContent)
    EditText mEtContent;
    @Bind(R.id.btnAudio)
    Button mBtnAudio;
    @Bind(R.id.ivEmo)
    ImageView mIvEmo;
    @Bind(R.id.ivMore)
    ImageView mIvMore;
    @Bind(R.id.btnSend)
    Button mBtnSend;
    @Bind(R.id.llContent)
    AutoLinearLayout mLlContent;
    @Bind(R.id.elEmotion)
    EmotionLayout mElEmotion;
    @Bind(R.id.ivAlbum)
    ImageView mIvAlbum;
    @Bind(R.id.rlAlbum)
    AutoRelativeLayout mRlAlbum;
    @Bind(R.id.ivShot)
    ImageView mIvShot;
    @Bind(R.id.rlTakePhoto)
    AutoRelativeLayout mRlTakePhoto;
    @Bind(R.id.ivLocation)
    ImageView mIvLocation;
    @Bind(R.id.rlLocation)
    AutoRelativeLayout mRlLocation;
    @Bind(R.id.ivRedPack)
    ImageView mIvRedPack;
    @Bind(R.id.rlRedPacket)
    AutoRelativeLayout mRlRedPacket;
    @Bind(R.id.flEmotionView)
    AutoFrameLayout mFlEmotionView;
    @Bind(R.id.llMore)
    LinearLayout mLlMore;
    @Bind(R.id.no_net_tips)
    LinearLayout noNetTips;
    @Bind(R.id.web_player)
    NormalGSYVideoPlayer webPlayer;


    private EmotionKeyboard mEmotionKeyboard;//表情键盘协调工具
    private boolean mIsFirst = false;
    private String imgUrl;
    private Transferee transferee;
    private OrientationUtils orientationUtils;

    private boolean isPlay;
    private boolean isPause;
    private boolean isSamll;

    private int mNoPermissionIndex = 0;
    private static final int REQUEST_CODE_PERMISSION_MULTI = 101;
    private static final int REQUEST_CODE_SETTING = 300;

    private final int PERMISSION_REQUEST_CODE = 1;
    private final String[] permissionManifest = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private final int[] noPermissionTip = {
            R.string.no_camera_permission,
            R.string.no_record_audio_permission,
            R.string.no_read_phone_state_permission,
            R.string.no_write_external_storage_permission,
            R.string.no_read_external_storage_permission
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCenterLay(R.layout.activity_session2);
        initView();
        initData();
        initListener();
        showCenterView();
    }

    @Override
    public int getSessionType() {
        return sessionType;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public void setRefreshState(boolean state) {
        this.isRefresh = state;
    }

    private int currentIndex = 1;
    private final int pageSize = 8;

    public void initView() {
        ButterKnife.bind(this);
        //沉浸式状态栏
//        StatusBarUtil.setColor(this, UIUtils.getColor(R.color.title_bg), 0);
        //应用运行时，保持屏幕高亮，不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        baseTitle.setRightImgRes(R.drawable.iv_right_more_2);
        mElEmotion.attachEditText(mEtContent);
        initEmotionKeyboard();
        initRefreshLayout();
    }

    public void initData() {
        // 申请多个权限。
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_PERMISSION_MULTI)
                .permission(Permission.MICROPHONE, Permission.STORAGE)
                .callback(this)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
                // 这样避免用户勾选不再提示，导致以后无法申请权限。
                // 你也可以不设置。
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
//                        AndPermission.rationaleDialog(AnnotationActivity.this, rationale).show();
                    }
                })
                .start();


        EventBus.getDefault().register(this);
        initAudioRecordManager();
        transferee = Transferee.getDefault(this);
        this.mPresenter.setTransferee(transferee);

        Intent intent = getIntent();
        sessionId = intent.getStringExtra("sessionId");
        sessionType = intent.getIntExtra("sessionType", SESSION_TYPE_PRIVATE);
        sessionName = intent.getStringExtra("sessionName");

        setTitleName(sessionName);

        switch (sessionType) {
            case SESSION_TYPE_PRIVATE:
                // 单聊
                webPlayer.setVisibility(View.GONE);

//                // 主动发起的聊天
//                RecentChatListManager.getInstance().initiativeChat(ChatListBean.CHAT_TYPE_SINGLE, sessionId);
                // 设置已读消息
                RecentChatListManager.getInstance().readMessage(ChatListBean.CHAT_TYPE_SINGLE, sessionId);
                break;
            case SESSION_TYPE_GROUP:
                webPlayer.setVisibility(View.GONE);
//                // 主动发起的聊天
//                RecentChatListManager.getInstance().initiativeChat(ChatListBean.CHAT_TYPE_MORE, sessionId);
                // 设置已读消息
                RecentChatListManager.getInstance().readMessage(ChatListBean.CHAT_TYPE_MORE, sessionId);
                baseTitle.setRightImgClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GroupDataActivity.startActivity(SessionActivity2.this, sessionId);
                    }
                });
                // 群聊
                break;
            case SESSION_TYPE_FAMILY:
                webPlayer.setVisibility(View.GONE);
//                // 主动发起的聊天
//                RecentChatListManager.getInstance().initiativeChat(ChatListBean.CHAT_TYPE_MORE, sessionId);
                // 设置已读消息
                RecentChatListManager.getInstance().readMessage(ChatListBean.CHAT_TYPE_MORE, sessionId);
                baseTitle.setRightImgClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FamilyDetailActivity.startActivity(SessionActivity2.this, Integer.valueOf(sessionId), true, true);
                    }
                });
                break;
            case SESSION_TYPE_VIDEO:
                // 直播聊天
                // 初始化播放器
                webPlayer.setVisibility(View.VISIBLE);
                initPlayer();
                break;
            default:
                break;
        }

        // 加载历史聊天记录
        mPresenter.loadMessage(sessionId, sessionType, currentIndex, pageSize);
    }

    /**
     * 初始化播放器
     */
    public void initPlayer() {
        String url = "http://baobab.wdjcdn.com/14564977406580.mp4";

        webPlayer.setUp(url, false, null, "测试视频");
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.wugeng);
        webPlayer.setThumbImageView(imageView);
        resolveNormalVideoUI();
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
                webPlayer.startWindowFullscreen(SessionActivity2.this, true, true);
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

    /**
     * 初始化监听器
     */
    public void initListener() {
        mElEmotion.setEmotionSelectedListener(this);
        mElEmotion.setEmotionAddVisiable(true);
        mElEmotion.setEmotionSettingVisiable(true);
        mElEmotion.setEmotionExtClickListener(new IEmotionExtClickListener() {
            @Override
            public void onEmotionAddClick(View view) {
                UIUtils.showToast("add");
            }

            @Override
            public void onEmotionSettingClick(View view) {
                UIUtils.showToast("setting");
            }
        });
        mLlContent.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    closeBottomAndKeyboard();
                    break;
            }
            return false;
        });
        mRvMsg.setOnTouchListener((v, event) -> {
            closeBottomAndKeyboard();
            return false;
        });
        mIvAudio.setOnClickListener(v -> {
            if (mBtnAudio.isShown()) {
                hideAudioButton();
                mEtContent.requestFocus();
                if (mEmotionKeyboard != null) {
                    mEmotionKeyboard.showSoftInput();
                }
            } else {
                mEtContent.clearFocus();
                showAudioButton();
                hideEmotionLayout();
                hideMoreLayout();
            }
            UIUtils.postTaskDelay(() -> mRvMsg.smoothMoveToPosition(mRvMsg.getAdapter().getItemCount() - 1), 50);
        });

        //内容变化监测框
        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                LogUtil.e(TAG, "---------》输入框内容" + mEtContent.getText().toString());
                if (mEtContent.getText().toString().trim().length() > 0) {
                    mBtnSend.setVisibility(View.VISIBLE);
                    mIvMore.setVisibility(View.GONE);
//                    RongIMClient.getInstance().sendTypingStatus(mConversationType, sessionId, TextMessage.class.getAnnotation(MessageTag.class).value());
                } else {
                    mBtnSend.setVisibility(View.GONE);
                    mIvMore.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEtContent.setOnFocusChangeListener((v, hasFocus) -> {//编辑内容
            if (hasFocus) {
                UIUtils.postTaskDelay(() -> mRvMsg.smoothMoveToPosition(mRvMsg.getAdapter().getItemCount() - 1), 50);
            }
        });
        mEtContent.setOnClickListener(v -> {
            UIUtils.postTaskDelay(() -> mRvMsg.smoothMoveToPosition(mRvMsg.getAdapter().getItemCount() - 1), 50);
        });

        mBtnSend.setOnClickListener(v -> {
            mPresenter.sendTextMsg(Session.TEXT);
        }); //发送信息
        mBtnAudio.setOnTouchListener((v, event) -> {//按住说话监听
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    AudioRecordManager.getInstance(SessionActivity2.this).startRecord();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (isCancelled(v, event)) {
                        AudioRecordManager.getInstance(SessionActivity2.this).willCancelRecord();
                    } else {
                        AudioRecordManager.getInstance(SessionActivity2.this).continueRecord();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    AudioRecordManager.getInstance(SessionActivity2.this).stopRecord();
                    AudioRecordManager.getInstance(SessionActivity2.this).destroyRecord();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    //如果当前正在处理的事件被上层 View 拦截，会收到一个 ACTION_CANCEL，后续事件不会再传递过来
                    AudioRecordManager.getInstance(SessionActivity2.this).stopRecord();
                    AudioRecordManager.getInstance(SessionActivity2.this).destroyRecord();
                    break;
            }
            return false;
        });

        mRlAlbum.setOnClickListener(v -> {//相片选择
            Intent intent = new Intent(SessionActivity2.this, ImageGridActivity.class);
            startActivityForResult(intent, REQUEST_IMAGE_PICKER);
        });
        mRlTakePhoto.setOnClickListener(v -> {//拍照和视频
            Intent intent = new Intent(SessionActivity2.this, TakePhotoActivity.class);
            startActivityForResult(intent, REQUEST_TAKE_PHOTO);
        });
        mRlLocation.setOnClickListener(v -> {//观众看直播

        });
        mRlRedPacket.setOnClickListener(v -> {
        });//主持人视频直播

        noNetTips.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
            startActivity(intent);
        });
    }

    @Override
    protected SessionAtPresenter2 createPresenter() {
        return mPresenter = new SessionAtPresenter2(this, this, transferee);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
        if (!mIsFirst) {
            mEtContent.clearFocus();
        } else {
            mIsFirst = false;
        }
        // mPresenter.resetDraft();
        if (!permissionCheck()) {
            if (Build.VERSION.SDK_INT >= 23) {
                ActivityCompat.requestPermissions(this, permissionManifest, PERMISSION_REQUEST_CODE);
            } else {
//                showNoPermissionTip(getString(noPermissionTip[mNoPermissionIndex]));
                finish();
            }
        }

    }

    /**
     * 权限检查（适配6.0以上手机）
     */
    private boolean permissionCheck() {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        String permission;
        for (int i = 0; i < permissionManifest.length; i++) {
            permission = permissionManifest[i];
            mNoPermissionIndex = i;
            if (PermissionChecker.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionCheck = PackageManager.PERMISSION_DENIED;
            }
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }


    @PermissionYes(REQUEST_CODE_PERMISSION_MULTI)
    private void getMultiYes(@NonNull List<String> grantedPermissions) {

    }

    @PermissionNo(REQUEST_CODE_PERMISSION_MULTI)
    private void getMultiNo(@NonNull List<String> deniedPermissions) {
        UIUtils.showToast("授权失败");

        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING)
                    .setTitle(R.string.title_dialog)
                    .setMessage(R.string.message_permission_failed)
                    .setPositiveButton(R.string.ok)
                    .setNegativeButton(R.string.no, null)
                    .show();

            // 更多自定dialog，请看上面。
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ZkNetEvent event) {
        switch (event.netEventType) {
            case MSG_CHAT_REQ_EVENT://聊天消息发送
                if (event.retCode == ZkRetCode.FAILED) {
                    LogUtil.e(TAG, "-------发送失败--------");
                    ZkLocalMessage localMessage = event.localMessage;
                    ZkMessagePacket packet = (ZkMessagePacket) event.extData;//这里可以重传
                    Session msession = new Session();
                    msession.setMsgId(localMessage.getMsgId());
                    msession.setSendTime(localMessage.getTime());
                    mPresenter.sendMsgFailed(msession);
                } else if (event.retCode == ZkRetCode.SECCUSS) {
                    AckS2CMessage chatResponseMessage = (AckS2CMessage) event.eventData;
                    Session session = new Session();
                    session.setMsgId(chatResponseMessage.getMsgId());
                    session.setSendTime(chatResponseMessage.getTime());
                    mPresenter.sendMsgSuccess(session);
                }
                break;
            case MSG_CHAT_RES_EVNT://聊天消息接收
                if (event.localMessage != null) {
                    if (sessionId.equals(event.localMessage.getSendUserId()) || sessionId.equals(event.localMessage.getGroupId())) {
                        String content = event.localMessage.chatContent;
                        Session session = Json.toBean(content, Session.class);
                        LogUtil.i("llj","接收时的头像---------->>>"+session.getMemberInfo().getIcon());
                        mPresenter.getResopnMessage(session);
                    }
                } else {
                    LogUtil.e(TAG, "-------->服务器返回聊天信息：null");
                }
                break;
            case CHANNEL_EXCEPTION://套接字通道异常
//                UIUtils.showToast("套接字通道异常！");
                LogUtil.e(TAG, "-------套接字通道异常-------");
//                IMClient.onReconnect();
                break;
            case CHANNEL_INTERRUPT://套接字通道被关闭中断
//                UIUtils.showToast("套接字通道被关闭中断！");
                break;
            case CHANNEL_CONNECT_SUCCESS://套接字通道连接成功
//                UIUtils.showToast("套接字通道连接成功！");
                break;
            case CHANNEL_KILL_OFF://套接字通道被踢下线
//                UIUtils.showToast("套接字通道被踢下线！");
                break;
            case CHANEEL_AUTH_SECCUSS://通道鉴权成功
//                UIUtils.showToast("通道鉴权成功！");
                noNetTips.setVisibility(View.GONE);
                LogUtil.e(TAG, "-------通道鉴权成功-------");
                break;
            case NETWORK_WIFI_STATE_CHANGE://WIFI状态变化
//                UIUtils.showToast("wifi网络连接异常");
                noNetTips.setVisibility(View.VISIBLE);
                break;
            case NETWORK_WIFT_CONNECT_ROUTER_CHANGE://WIFI连接路由状态变化
//                UIUtils.showToast("wifi网络连接路由");
//                IMClient.onReconnect();
                break;
            case NETWORK_INFO_CHANGE://网络信息变化包含移动和WIFI的变化
                if (event.eventData != null) {
                    NetworkInfo activeNetInfo = (NetworkInfo) event.eventData;
                    if (activeNetInfo.isAvailable() && activeNetInfo.isConnected()) {
                        return;
                    }
                }
//                UIUtils.showToast("网络连接异常");
                noNetTips.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        int toastTip = noPermissionTip[i];
                        mNoPermissionIndex = i;
                        if (toastTip != 0) {

                            finish();
                        }
                    }
                }
                break;
        }
    }


    /**
     * 初始化表情键盘
     */
    private void initEmotionKeyboard() {
        mEmotionKeyboard = EmotionKeyboard.with(this);
        mEmotionKeyboard.bindToEditText(mEtContent);
        mEmotionKeyboard.bindToContent(mLlContent);
        mEmotionKeyboard.setEmotionLayout(mFlEmotionView);
        mEmotionKeyboard.bindToEmotionButton(mIvEmo, mIvMore);
        mEmotionKeyboard.setOnEmotionButtonOnClickListener(view -> {
            switch (view.getId()) {
                case R.id.ivEmo:
                    UIUtils.postTaskDelay(() -> mRvMsg.smoothMoveToPosition(mRvMsg.getAdapter().getItemCount() - 1), 50);
                    mEtContent.clearFocus();
                    if (!mElEmotion.isShown()) {
                        if (mLlMore.isShown()) {
                            showEmotionLayout();
                            hideMoreLayout();
                            hideAudioButton();
                            return true;
                        }
                    } else if (mElEmotion.isShown() && !mLlMore.isShown()) {
                        mIvEmo.setImageResource(R.drawable.ic_cheat_emo);
                        return false;
                    }
                    showEmotionLayout();
                    hideMoreLayout();
                    hideAudioButton();
                    break;
                case R.id.ivMore:
                    UIUtils.postTaskDelay(() -> mRvMsg.smoothMoveToPosition(mRvMsg.getAdapter().getItemCount() - 1), 50);
                    mEtContent.clearFocus();
                    if (!mLlMore.isShown()) {
                        if (mElEmotion.isShown()) {
                            showMoreLayout();
                            hideEmotionLayout();
                            hideAudioButton();
                            return true;
                        }
                    }
                    showMoreLayout();
                    hideEmotionLayout();
                    hideAudioButton();
                    break;
            }
            return false;
        });

    }

    /**
     * 初始化录音管理器
     */
    private void initAudioRecordManager() {
        AudioRecordManager.getInstance(this).setMaxVoiceDuration(AppConst.DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND);
        File audioDir = new File(AppConst.AUDIO_SAVE_DIR);
        if (!audioDir.exists()) {
            audioDir.mkdirs();
        }
        AudioRecordManager.getInstance(this).setAudioSavePath(audioDir.getAbsolutePath());
        AudioRecordManager.getInstance(this).setAudioRecordListener(new IAudioRecordListener() {

            private TextView mTimerTV;
            private TextView mStateTV;
            private ImageView mStateIV;
            private PopupWindow mRecordWindow;

            @Override
            public void initTipView() {

                View view = View.inflate(SessionActivity2.this, R.layout.popup_audio_wi_vo, null);
                mStateIV = (ImageView) view.findViewById(R.id.rc_audio_state_image);
                mStateTV = (TextView) view.findViewById(R.id.rc_audio_state_text);
                mTimerTV = (TextView) view.findViewById(R.id.rc_audio_timer);
                mRecordWindow = new PopupWindow(view, -1, -1);
                mRecordWindow.showAtLocation(mLlRoot, 17, 0, 0);
                mRecordWindow.setFocusable(false);
                mRecordWindow.setOutsideTouchable(false);
                mRecordWindow.setTouchable(false);
            }

            @Override
            public void setTimeoutTipView(int counter) {
                if (this.mRecordWindow != null) {
                    this.mStateIV.setVisibility(View.GONE);
                    this.mStateTV.setVisibility(View.VISIBLE);
                    this.mStateTV.setText(R.string.voice_rec);
                    this.mStateTV.setBackgroundResource(R.drawable.bg_voice_popup);
                    this.mTimerTV.setText(String.format("%s", new Object[]{Integer.valueOf(counter)}));
                    this.mTimerTV.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void setRecordingTipView() {
                if (this.mRecordWindow != null) {
                    this.mStateIV.setVisibility(View.VISIBLE);
                    this.mStateIV.setImageResource(R.drawable.ic_volume_1);
                    this.mStateTV.setVisibility(View.VISIBLE);
                    this.mStateTV.setText(R.string.voice_rec);
                    this.mStateTV.setBackgroundResource(R.drawable.bg_voice_popup);
                    this.mTimerTV.setVisibility(View.GONE);
                }
            }

            @Override
            public void setAudioShortTipView() {
                if (this.mRecordWindow != null) {
                    mStateIV.setImageResource(R.drawable.ic_volume_wraning);
                    mStateTV.setText(R.string.voice_short);
                }
            }

            @Override
            public void setCancelTipView() {
                if (this.mRecordWindow != null) {
                    this.mTimerTV.setVisibility(View.GONE);
                    this.mStateIV.setVisibility(View.VISIBLE);
                    this.mStateIV.setImageResource(R.drawable.ic_volume_cancel);
                    this.mStateTV.setVisibility(View.VISIBLE);
                    this.mStateTV.setText(R.string.voice_cancel);
                    this.mStateTV.setBackgroundResource(R.drawable.corner_voice_style);
                }
            }

            @Override
            public void destroyTipView() {
                if (this.mRecordWindow != null) {
                    this.mRecordWindow.dismiss();
                    this.mRecordWindow = null;
                    this.mStateIV = null;
                    this.mStateTV = null;
                    this.mTimerTV = null;
                }
            }

            @Override
            public void onStartRecord() {
                //RongIMClient.getInstance().sendTypingStatus(mConversationType, sessionId, VoiceMessage.class.getAnnotation(MessageTag.class).value());
            }

            @Override
            public void onFinish(Uri audioPath, int duration) {
                //发送文件
                File file = new File(audioPath.getPath());
                if (file != null && file.exists()) {
                    mPresenter.uploadVoiceToOss(audioPath, duration, Session.VOICE);
                }
            }

            @Override
            public void onAudioDBChanged(int db) {
                switch (db / 5) {
                    case 0:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_1);
                        break;
                    case 1:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_2);
                        break;
                    case 2:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_3);
                        break;
                    case 3:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_4);
                        break;
                    case 4:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_5);
                        break;
                    case 5:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_6);
                        break;
                    case 6:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_7);
                        break;
                    default:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_8);
                }
            }
        });
    }

    /**
     * 刷新组件
     */
    private void initRefreshLayout() {
        mRefreshLayout.setDelegate(this);
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, false);
        refreshViewHolder.setRefreshingText("");
        refreshViewHolder.setPullDownRefreshText("");
        refreshViewHolder.setReleaseRefreshText("");
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    /**
     * 隐藏语音按钮
     */
    private void hideAudioButton() {
        mBtnAudio.setVisibility(View.GONE);
        mEtContent.setVisibility(View.VISIBLE);
        mIvAudio.setImageResource(R.drawable.ic_cheat_voice);
    }

    /**
     * 显示表情布局
     */
    private void showEmotionLayout() {
        mElEmotion.setVisibility(View.VISIBLE);
        mIvEmo.setImageResource(R.drawable.ic_cheat_keyboard);
    }

    /**
     * 隐藏表情布局
     */
    private void hideEmotionLayout() {
        mElEmotion.setVisibility(View.GONE);
        mIvEmo.setImageResource(R.drawable.ic_cheat_emo);
    }

    /**
     * 显示其他功能
     */
    private void showMoreLayout() {
        mLlMore.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏其他功能
     */
    private void hideMoreLayout() {
        mLlMore.setVisibility(View.GONE);
    }

    private boolean isCancelled(View view, MotionEvent event) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        if (event.getRawX() < location[0] || event.getRawX() > location[0] + view.getWidth()
                || event.getRawY() < location[1] - 40) {
            return true;
        }
        return false;
    }


    private void resolveNormalVideoUI() {
        //增加title
        webPlayer.getTitleTextView().setVisibility(View.GONE);
        webPlayer.getTitleTextView().setText("测试视频");
        webPlayer.getBackButton().setVisibility(View.GONE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause && !isSamll) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!webPlayer.isIfCurrentIsFullscreen()) {
                    webPlayer.startWindowFullscreen(SessionActivity2.this, true, true);
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

    private void showAudioButton() {
        mBtnAudio.setVisibility(View.VISIBLE);
        mEtContent.setVisibility(View.GONE);
        mIvAudio.setImageResource(R.drawable.ic_cheat_keyboard);

        if (mFlEmotionView.isShown()) {
            if (mEmotionKeyboard != null) {
                mEmotionKeyboard.interceptBackPress();
            }
        } else {
            if (mEmotionKeyboard != null) {
                mEmotionKeyboard.hideSoftInput();
            }
        }
    }

    private void closeBottomAndKeyboard() {
        mElEmotion.setVisibility(View.GONE);

        mEmotionKeyboard.hideSoftInput();
        mEtContent.clearFocus();

        mLlMore.setVisibility(View.GONE);
        if (mEmotionKeyboard != null) {
            mEmotionKeyboard.interceptBackPress();
            mIvEmo.setImageResource(R.drawable.ic_cheat_emo);
        }
    }

    private boolean isRefresh;

    /**
     * 向下拉，刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        handler.obtainMessage(110).sendToTarget();
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 110 && !isRefresh) {
                isRefresh = true;
                // 加载历史聊天记录
                mPresenter.loadMessage(sessionId, sessionType, currentIndex, pageSize);
            }
            return false;
        }
    });

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onEmojiSelected(String key) {

    }

    @Override
    public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {
        String fileName = "/" + categoryName + "/" + stickerName;
        mPresenter.sendSticker(fileName, stickerBitmapPath, Session.STICKER);
    }

    @Override
    public BGARefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }

    @Override
    public LQRRecyclerView getRvMsg() {
        return mRvMsg;
    }

    @Override
    public EditText getEtContent() {
        return mEtContent;
    }

    @Override
    public void setCurrentIndex(int addPageSize) {
        this.currentIndex += addPageSize;
    }

    @Override
    public void onBackPressed() {
        if (NormalGSYVideoPlayer.backFromWindowFull(SessionActivity2.this)) return;
        if (mElEmotion.isShown() || mLlMore.isShown()) {
            mEmotionKeyboard.interceptBackPress();
            mIvEmo.setImageResource(R.drawable.ic_cheat_emo);
        } else {
            super.onBackPressed();
        }
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
    }

    List<String> photoPath = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_IMAGE_PICKER:
                if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {//返回多张照片
                    if (data != null) {
                        //是否发送原图
                        boolean isOrig = data.getBooleanExtra(ImagePreviewActivity.ISORIGIN, false);
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        LogUtil.e(TAG, "-----选择图片个数------》" + images.size());
                        LogUtil.e(TAG, isOrig ? "发原图" : "不发原图");//若不发原图的话，需要在自己在项目中做好压缩图片算法
                        for (ImageItem imageItem : images) {
                            if (isOrig) {
                                File imageFileSource = new File(imageItem.path);
                                compressWithRx(imageFileSource, photoPath);
                            } else {
                                //压缩图片
                                File imageFileSource = new File(imageItem.path);
                                compressWithRx(imageFileSource, photoPath);
                            }
                        }
                    }
                }
                break;
            case REQUEST_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    String path = data.getStringExtra("path");
                    LogUtil.e(TAG, "<-------------->REQUEST_TAKE_PHOTO: " + data.getBooleanExtra("take_photo", true));
                    if (data.getBooleanExtra("take_photo", true)) {
                        List<String> photoList = new ArrayList<>();
                        photoList.add(path);
                        mPresenter.setPhotoList(path, Session.IMAGE, AppConst.IMAGE_TYPE);
                    } else {
//                        mPresenter.startCompress(path);
                        mPresenter.uploadVideoToOss(path);

                    }
                }
                break;
            case REQUEST_MY_LOCATION:
                if (resultCode == RESULT_OK) {
                    // LocationData locationData = (LocationData) data.getSerializableExtra("location");
                    // mPresenter.sendLocationMessage(locationData);
                }
                break;
            case REQUEST_UPLOAD_DATA:
                if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                    if (data != null) {
                        /*mLeChose.setVisibility(View.GONE);
                        mVideoView.setVisibility(View.GONE);
                        mPhotoView.setVisibility(View.VISIBLE);
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        ImageItem imageItem = images.get(0);
                        String path = imageItem.path;
                        imgUrl = path;
                        PhotoLoaderUtil.display(this, mPhotoView, path, UIUtils.getResource().getDrawable(R.drawable.wugeng));*/
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 压缩图片
     *
     * @param file
     * @param path
     */
    private void compressWithRx(File file, List<String> path) {
        //同步压缩
        Observable.just(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(file1 -> {
                    try {
                        Luban.with(SessionActivity2.this).load(file1).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .doOnCompleted(() -> {
                    path.add(file.getAbsolutePath());
                    mPresenter.setPhotoList(file.getAbsolutePath(), Session.IMAGE, AppConst.IMAGE_TYPE);
                })
                .subscribe();
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
        transferee.destroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
//        // 保存最近聊天列表的数据
//        RecentChatListManager.getInstance().saveRecentChatList();
        // 退出正在聊天
        RecentChatListManager.getInstance().exitChatting();


        if (orientationUtils != null)
            if (sessionType == SESSION_TYPE_VIDEO) {
                GSYVideoPlayer.releaseAllVideos();
            }
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }

    /**
     * 跳转至聊天界面
     *
     * @param context
     * @param type    聊天类型:
     *                单聊 = SESSION_TYPE_PRIVATE = 1,群聊 = SESSION_TYPE_GROUP = 2，视频直播聊天 = SESSION_TYPE_VIDEO = 3
     * @param id      如果是：单聊=好友id,群聊=群组id,视频直播聊天=直播室id
     * @param name    聊天对象名称
     */
    public static void startActivity(Context context, int type, String id, String name) {
        Intent intent = new Intent(context, SessionActivity2.class);
        intent.putExtra("sessionType", type);
        intent.putExtra("sessionId", id);
        intent.putExtra("sessionName", name);
        context.startActivity(intent);
    }

}
