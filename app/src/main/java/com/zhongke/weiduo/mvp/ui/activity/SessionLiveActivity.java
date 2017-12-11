package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.common.AppConst;
import com.zhongke.weiduo.app.event.ChatEvent;
import com.zhongke.weiduo.app.utils.ImageUtils;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.mvp.base.BaseFragmentActivity;
import com.zhongke.weiduo.mvp.contract.ISessionAtView;
import com.zhongke.weiduo.mvp.model.entity.Session;
import com.zhongke.weiduo.mvp.presenter.SessionAtPresenter;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * Created by Karma on 2017/6/21.
 * 类描述：单聊、群聊、直播页面
 */

public class SessionLiveActivity extends BaseFragmentActivity<ISessionAtView, SessionAtPresenter> implements ISessionAtView, IEmotionSelectedListener, BGARefreshLayout.BGARefreshLayoutDelegate {
    private static final String TAG = "SessionActivity";
    public static final int REQUEST_IMAGE_PICKER = 1000;
    public final static int REQUEST_TAKE_PHOTO = 1001;
    public final static int REQUEST_MY_LOCATION = 1002;
    public final static int REQUEST_UPLOAD_DATA = 1003;

    public final static int SESSION_TYPE_PRIVATE = 1;//单聊
    public final static int SESSION_TYPE_GROUP = 2;//群聊
    public final static int SESSION_TYPE_VIDEO = 3;//视频直播

    @Override
    public void setCurrentIndex(int addPageIndex) {

    }

    @Override
    public int getSessionType() {
        return 0;
    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public void setRefreshState(boolean state) {

    }

    private String mSessionId = "";
    private int mConversationType = 1;

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


    private EmotionKeyboard mEmotionKeyboard;//表情键盘协调工具
    private boolean mIsFirst = false;
    private String imgUrl;



    @Override
    protected void onResume() {
        super.onResume();
        if (!mIsFirst) {
            mEtContent.clearFocus();
        } else {
            mIsFirst = false;
        }
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        mSessionId = intent.getStringExtra("sessionId");
        int sessionType = intent.getIntExtra("sessionType", SESSION_TYPE_PRIVATE);
        switch (sessionType) {
            case SESSION_TYPE_PRIVATE:
                break;
            case SESSION_TYPE_GROUP:
                break;
            case SESSION_TYPE_VIDEO:
                break;
            default:
                break;
        }

        initAudioRecordManager();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void parseIntent() {
        super.parseIntent();
        /*boolean isVideo = getIntent().getBooleanExtra(EXTRA_MODE, true);
        liveType = isVideo ? LiveType.VIDEO_TYPE : LiveType.AUDIO_TYPE;//直播类型*/
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void callChatEvent(ChatEvent chatEvent) {
        LogUtil.e(TAG, "-------------->eventBus消息：" + chatEvent.toString());
    }

    @Override
    public void initView() {

        mIbToolbarMore.setImageResource(R.drawable.iv_right_more);
        mIbToolbarMore.setVisibility(View.VISIBLE);//更多按钮
        mElEmotion.attachEditText(mEtContent);
        initEmotionKeyboard();
        initRefreshLayout();
        setTitle();

//        registerLiveObservers(true);
    }

    /**
     * 设置标题
     */
    private void setTitle() {
        setToolbarTitle("众可智能管理有限公司培训");

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
        AudioRecordManager.getInstance(UIUtils.getContext()).setMaxVoiceDuration(AppConst.DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND);
        File audioDir = new File(AppConst.AUDIO_SAVE_DIR);
        if (!audioDir.exists()) {
            audioDir.mkdirs();
        }
        AudioRecordManager.getInstance(UIUtils.getContext()).setAudioSavePath(audioDir.getAbsolutePath());
        AudioRecordManager.getInstance(UIUtils.getContext()).setAudioRecordListener(new IAudioRecordListener() {

            private TextView mTimerTV;
            private TextView mStateTV;
            private ImageView mStateIV;
            private PopupWindow mRecordWindow;

            @Override
            public void initTipView() {
                View view = View.inflate(SessionLiveActivity.this, R.layout.popup_audio_wi_vo, null);
                mStateIV = (ImageView) view.findViewById(R.id.rc_audio_state_image);
                mStateTV = (TextView) view.findViewById(R.id.rc_audio_state_text);
                mTimerTV = (TextView) view.findViewById(R.id.rc_audio_timer);
                mRecordWindow = new PopupWindow(view, -1, -1);
                mRecordWindow.showAtLocation(mLlRoot, 17, 0, 0);
                mRecordWindow.setFocusable(true);
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
                //RongIMClient.getInstance().sendTypingStatus(mConversationType, mSessionId, VoiceMessage.class.getAnnotation(MessageTag.class).value());
            }

            @Override
            public void onFinish(Uri audioPath, int duration) {
                //发送文件
                File file = new File(audioPath.getPath());
                if (file.exists()) {
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

    /**
     * 初始化监听器
     */
    @Override
    public void initListener() {

        mIbToolbarMore.setOnClickListener(v -> {

        });
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
                LogUtil.e(TAG, "---------》输入框内容" + mEtContent.getText().toString());
                if (mEtContent.getText().toString().trim().length() > 0) {
                    mBtnSend.setVisibility(View.VISIBLE);
                    mIvMore.setVisibility(View.GONE);
//                    RongIMClient.getInstance().sendTypingStatus(mConversationType, mSessionId, TextMessage.class.getAnnotation(MessageTag.class).value());
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
            LogUtil.e(TAG, "------------>hasFocus=" + hasFocus);
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
                    AudioRecordManager.getInstance(SessionLiveActivity.this).startRecord();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (isCancelled(v, event)) {
                        AudioRecordManager.getInstance(SessionLiveActivity.this).willCancelRecord();
                    } else {
                        AudioRecordManager.getInstance(SessionLiveActivity.this).continueRecord();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    AudioRecordManager.getInstance(SessionLiveActivity.this).stopRecord();
                    AudioRecordManager.getInstance(SessionLiveActivity.this).destroyRecord();
                    break;
            }
            return false;
        });

        mRlAlbum.setOnClickListener(v -> {//相片选择
            Intent intent = new Intent(SessionLiveActivity.this, ImageGridActivity.class);
            startActivityForResult(intent, REQUEST_IMAGE_PICKER);
        });
        mRlTakePhoto.setOnClickListener(v -> {//拍照和视频
            Intent intent = new Intent(SessionLiveActivity.this, TakePhotoActivity.class);
            startActivityForResult(intent, REQUEST_TAKE_PHOTO);
        });
        mRlLocation.setOnClickListener(v -> {//观众看直播

        });
        mRlRedPacket.setOnClickListener(v -> {

        });//主持人视频直播


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

    /**
     * 初始化消息列表
     */
    @Override
    public void initData() {
        mPresenter.loadMessage();
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

    @Override
    protected SessionAtPresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_live_session;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        LogUtil.e(TAG, "-----------》刷新更多");
        mPresenter.loadMore();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onEmojiSelected(String key) {

    }

    @Override
    public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {

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
    public void onBackPressed() {
        if (mElEmotion.isShown() || mLlMore.isShown()) {
            mEmotionKeyboard.interceptBackPress();
            mIvEmo.setImageResource(R.drawable.ic_cheat_emo);

        } else {
            super.onBackPressed();
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_IMAGE_PICKER:
                if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {//返回多张照片
                    if (data != null) {

                        List<String> photoPath = new ArrayList<>();

                        //是否发送原图
                        boolean isOrig = data.getBooleanExtra(ImagePreviewActivity.ISORIGIN, false);
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        Log.e("CSDN_LQR", isOrig ? "发原图" : "不发原图");//若不发原图的话，需要在自己在项目中做好压缩图片算法
                        for (ImageItem imageItem : images) {
                            photoPath.add(imageItem.path);
                            File imageFileThumb;
                            File imageFileSource;
                            if (isOrig) {
                                imageFileSource = new File(imageItem.path);
                                imageFileThumb = ImageUtils.genThumbImgFile(imageItem.path);
                            } else {
                                //压缩图片
                                imageFileSource = ImageUtils.genThumbImgFile(imageItem.path);
                                imageFileThumb = ImageUtils.genThumbImgFile(imageFileSource.getAbsolutePath());
                            }
                            if (imageFileSource != null && imageFileThumb != null) {
                                // mPresenter.sendImgMsg(imageFileThumb, imageFileSource);
//                                mPresenter.sendTextMsg(imageItem.path, AppConst.IMAGE);
                            }
                        }
                        LogUtil.e(TAG, "----------->>>>>>" + photoPath.toString());
//                        mPresenter.setPhotoList(photoPath);
                    }
                }
                break;
            case REQUEST_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    String path = data.getStringExtra("path");
                    LogUtil.e(TAG, "<-------------->REQUEST_TAKE_PHOTO: " + path);
                    if (data.getBooleanExtra("take_photo", true)) {
                        //照片
                        // mPresenter.sendImgMsg(ImageUtils.genThumbImgFile(path), new File(path));
                        List<String> photoList = new ArrayList<>();
                        photoList.add(path);
//                        mPresenter.setPhotoList(photoList);
                    } else {
                        //小视频
//                         mPresenter.sendFileMsg(new File(path));
                        mPresenter.uploadVideoToOss(path);
                        // mPresenter.sendTextMsg("http://img.zcool.cn/community/01f7d155abb6dc6ac7258178bf5e4d.png", AppConst.VIDEO);
                    }
                }
                break;
            case REQUEST_MY_LOCATION:
                if (resultCode == RESULT_OK) {

                }
                break;
            case REQUEST_UPLOAD_DATA:
                if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                    if (data != null) {

                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // mPresenter.saveDraft();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unRegisterBR();
        LogUtil.e(TAG, "------------->销毁聊天activity");
        EventBus.getDefault().unregister(this);
    }


}
