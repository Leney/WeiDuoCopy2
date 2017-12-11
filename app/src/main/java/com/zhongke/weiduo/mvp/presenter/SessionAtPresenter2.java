package com.zhongke.weiduo.mvp.presenter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.hitomi.glideloader.GlideImageLoader;
import com.hitomi.tilibrary.style.progress.ProgressPieIndicator;
import com.hitomi.tilibrary.transfer.TransferConfig;
import com.hitomi.tilibrary.transfer.Transferee;
import com.lqr.adapter.LQRViewHolder;
import com.lqr.audio.AudioPlayManager;
import com.lqr.audio.IAudioPlayUrlListener;
import com.mabeijianxi.smallvideorecord2.LocalMediaCompress;
import com.mabeijianxi.smallvideorecord2.model.AutoVBRMode;
import com.mabeijianxi.smallvideorecord2.model.BaseMediaBitrateConfig;
import com.mabeijianxi.smallvideorecord2.model.LocalMediaConfig;
import com.mabeijianxi.smallvideorecord2.model.OnlyCompressOverBean;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.common.AppConst;
import com.zhongke.weiduo.app.listener.CallBackListener;
import com.zhongke.weiduo.app.oss.ImageOSSClient;
import com.zhongke.weiduo.app.utils.BitmapUtil;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.StringUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.app.utils.ValidateUtil;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ISessionAtView;
import com.zhongke.weiduo.mvp.db.local.DBManager;
import com.zhongke.weiduo.mvp.model.entity.PhotoOrVideo;
import com.zhongke.weiduo.mvp.model.entity.Session;
import com.zhongke.weiduo.mvp.ui.activity.SessionActivity2;
import com.zhongke.weiduo.mvp.ui.adapter.SessionAdapter2;
import com.zhongke.weiduo.video.MVideo;

import org.apache.commons.lang3.text.StrBuilder;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.zhongke.weiduo.app.utils.UIUtils.showToast;

/**
 * Created by Karma on 2017/6/21.
 * 单聊和群聊p层
 */
public class SessionAtPresenter2 extends BasePresenter<ISessionAtView> {
    private static final String TAG = "SessionAtPresenter";
    private List<Session> mData = new ArrayList<>();
    private SessionAdapter2 mAdapter;
    private List<byte[]> zipPicUrlList = new ArrayList<>();
    private List<String> imagePaths = new ArrayList<>();
    private boolean isUploadFail;
    private int progress = 0;
    private String imageFileThumbUri;
    private boolean isUpload = false;
    List<String> photoUrls = new ArrayList<String>();
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    protected Transferee transferee;
    protected TransferConfig config;

    private Context mContext;
    private ISessionAtView iSessionAtView;


    public SessionAtPresenter2(Context context, ISessionAtView iSessionAtView, Transferee transferee) {
        this.mContext = context;
        this.transferee = transferee;
        this.iSessionAtView = iSessionAtView;

        config = TransferConfig.build()
                .setSourceImageList(photoUrls)
                .setMissPlaceHolder(R.mipmap.ic_empty_photo)
                .setProgressIndicator(new ProgressPieIndicator())
                .setImageLoader(GlideImageLoader.with(context))
                .setOnLongClcikListener((imageView, pos) -> {

                })
                .create();
    }

    public void setTransferee(Transferee transferee) {
        this.transferee = transferee;
    }

    public void loadMessage(String objectId, int objectType, int pageIndex, int pageSize) {
        loadData(objectId, objectType, pageIndex, pageSize);
        setAdapter();
    }

    private void loadData(String objectId, int objectType, int pageIndex, int pageSize) {
        getLocalHistoryMessage(objectId, objectType, pageIndex, pageSize);
        setAdapter();
    }

    /**
     * 向下拉，加载更多旧数据
     */
    public void loadMore() {

        getLoadMore();
        mAdapter.notifyDataSetChangedWrapper();
    }

    public void receiveNewMessage(Session message) {
//        mData.add(message);
        setAdapter();
        //设置会话已读
        // RongIMClient.getInstance().clearMessagesUnreadStatus(mConversationType, mSessionId);
    }

    @NonNull
    protected List<ImageView> wrapOriginImageViewList(LQRViewHolder helper, int size) {
        List<ImageView> originImgList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ImageView thumImg = helper.getView(R.id.bivPic);
            originImgList.add(thumImg);
        }
        return originImgList;
    }

    public void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new SessionAdapter2(mContext, mData, this);
            mAdapter.setOnItemClickListener((helper, parent, itemView, position) -> {
                Session sessionMessage1 = mData.get(position);
                int status = sessionMessage1.getChatType();
                boolean isSend = sessionMessage1.getSendOrReceive() == AppConst.SESSION_RECEIVE ? true : false;
                if (status == Session.IMAGE) {
                    for (int i = 0; i < photoUrls.size(); i++) {
                        if (photoUrls.get(i).equals(AppConst.DOWND_PHOTO + sessionMessage1.getContent())) {
                            config.setNowThumbnailIndex(i);
                            config.setOriginImageList(wrapOriginImageViewList(helper, photoUrls.size()));
                            transferee.apply(config).show();
                        }
                    }

                } else if (status == Session.VIDEO) {
                    helper.getView(R.id.ivPlay).setOnClickListener(v -> {
                        if (isSend) {
                            MVideo.getInstance().start((Activity) mContext, helper.getView(R.id.bivPic), AppConst.DOWND_PHOTO + sessionMessage1.getContent());
                        } else {
                            MVideo.getInstance().start((Activity) mContext, helper.getView(R.id.bivPic), sessionMessage1.getLocalUrl() != null ? sessionMessage1.getLocalUrl() : AppConst.DOWND_PHOTO + sessionMessage1.getContent());
                        }
                    });
                } else if (status == Session.VOICE) {
                    final ImageView ivAudio = helper.getView(R.id.ivAudio);
                    AudioPlayManager.getInstance().startPlay(mContext, AppConst.DOWND_PHOTO + sessionMessage1.getContent(), new IAudioPlayUrlListener() {
                        @Override
                        public void onStart(String var1) {
                            AnimationDrawable animation = (AnimationDrawable) ivAudio.getBackground();
                            animation.start();
                        }

                        @Override
                        public void onStop(String var1) {
                            AnimationDrawable animation = (AnimationDrawable) ivAudio.getBackground();
                            animation.stop();
                            animation.selectDrawable(0);
                        }

                        @Override
                        public void onComplete(String var1) {
                            if (ivAudio != null && ivAudio.getBackground() instanceof AnimationDrawable) {
                                AnimationDrawable animation = (AnimationDrawable) ivAudio.getBackground();
                                animation.stop();
                                animation.selectDrawable(0);
                            }
                        }
                    });
                    if (AudioPlayManager.getInstance().isRecordPlay()) {

                    }

                }
            });
            getView().getRvMsg().setAdapter(mAdapter);
        } else

        {
            mAdapter.notifyDataSetChangedWrapper();
            if (getView() != null && getView().getRvMsg() != null)
                rvMoveToBottom();
        }

    }


    private void rvMoveToBottom() {
        getView().getRvMsg().smoothMoveToPosition(mData.size() - 1);
    }

    private void updateMessageStatus(Session session) {
        LogUtil.e(TAG, "------->加入数据更新" + session.getContent());
        for (int i = 0; i < mData.size(); i++) {
            Session msg = mData.get(i);
            if (session.getMsgId().equals(msg.getMsgId())) {
                mData.remove(i);
                mData.add(i, session);
                rvMoveToBottom();
                mAdapter.notifyDataSetChangedWrapper();
                break;
            }
        }

    }

    /**
     * 发送消息
     */
    public void sendTextMsg(int chatType) {
        sendTextMsg(getView().getEtContent().getText().toString(), 0, chatType);
        getView().getEtContent().setText("");
    }

    /**
     * 发送文本信息
     *
     * @param content
     * @param duration
     * @param chatType
     */
    public void sendTextMsg(String content, int duration, int chatType) {
        Session mSession = new Session();
        mSession.setChatType(chatType);
//        mSession.getMemberInfo().setIcon("http://img.zcool.cn/community/01f7d155abb6dc6ac7258178bf5e4d.png");
        if (chatType == Session.VOICE) {
            mSession.setDuraction(duration);
        }
        if (chatType == Session.IMAGE) {
            mSession.setLocalUrl(imageFileThumbUri);
        }
        mSession.setSendOrReceive(AppConst.SESSION_SEND);
        mSession.setContent(content);
        mSession.setSendStatus(AppConst.SENDING);
        addReceiverConfig(mSession);
        String msgId = IMClient.sendMessageByMsgId(mSession, mSession.getToUserList(), mSession.getGroupId());
        mSession.setMsgId(msgId);
        mAdapter.addLastItem(mSession);
        rvMoveToBottom();
        LogUtil.e(TAG, "----------->加入数据=" + mSession.getContent() + "---->数据个数" + mData.size());

//        Log.i("llj", "size--------->>>>" + mSession.getToUserList().size());
//        IMClient.sendExtMessage(IMConstance.ADD_FRIEND_REQUEST, mSession.getToUserList(), mSession.getGroupId());
    }

    /**
     * 发送贴图
     *
     * @param content
     * @param localUrl
     * @param chatType
     */
    public void sendSticker(String content, String localUrl, int chatType) {
        Session mSession = new Session();
        mSession.setChatType(chatType);
//        mSession.getMemberInfo().setIcon("http://img.zcool.cn/community/01f7d155abb6dc6ac7258178bf5e4d.png");
        mSession.setLocalUrl(localUrl);
        mSession.setSendOrReceive(AppConst.SESSION_SEND);
        mSession.setContent(content);
        mSession.setSendStatus(AppConst.SENDING);
        addReceiverConfig(mSession);
        String msgId = IMClient.sendMessageByMsgId(mSession, mSession.getToUserList(), mSession.getGroupId());
        mSession.setMsgId(msgId);
        mAdapter.addLastItem(mSession);
        rvMoveToBottom();
        LogUtil.e(TAG, "----------->加入数据=" + mSession.getContent() + "---->数据个数" + mData.size());
    }

    /**
     * 发送图片信息
     *
     * @param session
     */
    public void sendImageMsg(Session session) {
        addReceiverConfig(session);
        String msgId = IMClient.sendMessageByMsgId(session, session.getToUserList(), session.getGroupId());
        session.setMsgId(msgId);
        photoUrls.add(AppConst.DOWND_PHOTO + session.getContent());
        LogUtil.e(TAG, "----------->加入数据=" + session.getContent() + "---->数据个数" + mData.size());
    }

    /**
     * 添加接受者信息
     *
     * @param session
     */
    private void addReceiverConfig(Session session) {
        switch (iSessionAtView.getSessionType()) {
            case SessionActivity2.SESSION_TYPE_PRIVATE://单聊
                List<String> list = new ArrayList<>();
                list.add(iSessionAtView.getSessionId());
                session.setToUserList(list);
                break;
            case SessionActivity2.SESSION_TYPE_GROUP://群聊
                session.setGroupId(iSessionAtView.getSessionId());
                break;
        }
    }

    /**
     * 发送视频信息
     *
     * @param session
     */
    public void sendVideo(Session session) {
        addReceiverConfig(session);
        String msgId = IMClient.sendMessageByMsgId(session, session.getToUserList(), session.getGroupId());
        session.setMsgId(msgId);
        photoUrls.add(session.getLocalUrl() == null ? AppConst.DOWND_PHOTO + session.getContent() : session.getLocalUrl());
        LogUtil.e(TAG, "----------->加入数据=" + session.getContent() + "---->数据个数" + mData.size());
    }

    /**
     * 异步匹配，发送消息的状态
     *
     * @param type
     * @param session
     */
    private void sendMsgResult(final int type, Session session) {
        Subscription subscription = Observable.create(new Observable.OnSubscribe<Session>() {
            @Override
            public void call(Subscriber<? super Session> subscriber) {
                Session msg = null;
                for (int i = 0; i < mData.size(); i++) {
                    msg = mData.get(i);
                    if (session.getMsgId().equals(msg.getMsgId())) {
                        msg.setMsgId(session.getMsgId());
                        msg.setSendTime(session.getSendTime());
                        msg.setSendStatus(type == 1 ? AppConst.SENT : AppConst.FAILED);
                        break;
                    }
                }
                subscriber.onNext(msg);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Session>() {
            @Override
            public void call(Session msg) {
                rvMoveToBottom();
                mAdapter.notifyDataSetChangedWrapper();
                if (type == 1) {
                    boolean isSend = msg.getSendOrReceive() == AppConst.SESSION_RECEIVE ? true : false;
                    if (isSend) {
                        PhotoOrVideo photoOrVideo = new PhotoOrVideo();
                        photoOrVideo.setPhotoUrl(AppConst.DOWND_PHOTO + msg.getContent());
                    } else {
                        String url = msg.getLocalUrl() != null ? msg.getLocalUrl() : AppConst.DOWND_PHOTO + msg.getContent();
                        PhotoOrVideo photoOrVideo = new PhotoOrVideo();
                        photoOrVideo.setPhotoUrl(url);
                    }
                }
            }
        });
        this.mCompositeSubscription.add(subscription);
    }

    /**
     * 发送消息成功的回调
     *
     * @param session
     */
    public void sendMsgSuccess(Session session) {
        sendMsgResult(1, session);
    }

    /**
     * 发送信息失败
     *
     * @param session
     */
    public void sendMsgFailed(Session session) {
        sendMsgResult(0, session);
    }

    public void getResopnMessage(Session session) {
        session.setSendOrReceive(AppConst.SESSION_RECEIVE);
        mAdapter.addLastItem(session);
        if (session.getChatType() == Session.IMAGE) {
            photoUrls.add(AppConst.DOWND_PHOTO + session.getContent());
        }
        rvMoveToBottom();
        mAdapter.notifyDataSetChangedWrapper();
    }


    public void getLocalHistoryMessage(String objectId, int objectType, int pageIndex, int pageSize) {
        try {
            executeTask(DBManager.getInstance().querySession(objectId, objectType, pageIndex, pageSize));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行观察者任务
     *
     * @param observable
     */
    private void executeTask(Observable<List<Session>> observable) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Session>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        iSessionAtView.setRefreshState(false);
                        getLoadMore();
                    }

                    @Override
                    public void onNext(List<Session> sessions) {
                        Log.i(TAG, "新增的聊天记录 个数 " + sessions.size() + "  原本RecyclerView中个item个数 " + mAdapter.getItemCount());
                        if (mAdapter != null) {
                            mAdapter.addNewData(sessions);
                            if (iSessionAtView != null) {
                                iSessionAtView.setCurrentIndex(1);
                            }
                            getView().getRvMsg().moveToPosition(sessions.size() == 0 ? 0 : sessions.size() - 1);
                        }
                        for (Session session : sessions) {
                            if (session.getChatType() == Session.IMAGE) {
                                photoUrls.add(AppConst.DOWND_PHOTO + session.getContent());
                            }
                        }
                        getLoadMore();
                        iSessionAtView.setRefreshState(false);
                    }
                });
        this.mCompositeSubscription.add(subscription);
    }

    public void getLoadMore() {
        getView().getRefreshLayout().endRefreshing();
    }

    private void saveHistoryMsg(List<Session> messages) {
        //messages的时间顺序从新到旧排列，所以必须反过来加入到mData中
        if (messages != null && messages.size() > 0) {
            for (Session msg : messages) {
                mData.add(0, msg);
            }
            getView().getRvMsg().moveToPosition(messages.size() - 1);
        }
    }

    public void setPhotoList(String phontoList, int chatType, int sendType) {
        addPhotoList(chatType, phontoList, 0);
    }

    /**
     * 图片添加到列表
     *
     * @param chatType
     * @param imageFileThumbUri
     */
    private void addPhotoList(int chatType, String imageFileThumbUri, int i) {
        Session session = new Session();
        session.setSendStatus(AppConst.SENDING);
        session.setLocalUrl(imageFileThumbUri);
        session.setChatType(chatType);
//        session.getMemberInfo().setIcon("http://img.zcool.cn/community/01f7d155abb6dc6ac7258178bf5e4d.png");
        session.setSendOrReceive(AppConst.SESSION_SEND);
        mAdapter.addLastItem(session);
        rvMoveToBottom();
        upLoadPhotoPathToOss(imageFileThumbUri, i, session);
    }

    /**
     * 图片路径上传到oss
     *
     * @param path
     * @param page
     */
    private void upLoadPhotoPathToOss(String path, int page, Session session) {
        ImageOSSClient imageOSSClient = new ImageOSSClient(AppConst.TEST_ENDPOINT, AppConst.ACCES_KEYID, AppConst.ACCES_KEYSECRET);
        imageOSSClient.initialize(mContext);
        String uuid = StringUtil.getUUID();
        String objectKey = "session" + "/" + uuid + "/" + uuid.substring(0, 6) + ".jpg";
        imageOSSClient.uploadImage(AppConst.TEST_BUCKET, objectKey, path, page, new CallBackListener<String>() {
            @Override
            public void onSuccess(int requestCode, int responseCode, String result) {
                Observable.just(objectKey)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(String s) {
                                LogUtil.e(TAG, "-------上传图片成功-------》" + s);
                                session.setExtra("100");
                                session.setContent(s);
                                sendImageMsg(session);
                            }
                        });
            }

            @Override
            public void onFailure(int requestCode, int responseCode, String result) {
                UIUtils.showToastSafely("上传图片失败");
            }

            @Override
            public void setProgressCallback(long currentSize, long totalSize) {
                Observable.just(currentSize)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            progress = progress + 1;
                            if (progress >= 100 || currentSize == 0) {
                                session.setExtra(String.valueOf(100));
                            } else {
                                session.setExtra(String.valueOf(progress));
                            }
                            mAdapter.notifyDataSetChangedWrapper();

                        });
            }
        });
    }

    private void rxPhotoList(int chatType, int sendType) {
        List<String> imgUrlList = getUrlList();
        LogUtil.e(TAG, "---------->设置图片个数" + imgUrlList.size());
        zipPicUrlList.clear();
        Observable.from(imgUrlList)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> compress(s))
                .doOnCompleted(() -> {
                    uploadPhotoToOss(zipPicUrlList, chatType, sendType);
                })
                .subscribe();
    }

    private void uploadPhotoToOss(List<byte[]> resourseList, int chatType, int sendType) {
        if (ValidateUtil.isNull(resourseList)) {
            showToast("请选择图片上传");
            return;
        }
        ImageOSSClient imageOSSClient = new ImageOSSClient(AppConst.TEST_ENDPOINT, AppConst.ACCES_KEYID, AppConst.ACCES_KEYSECRET);
        imageOSSClient.initialize(mContext);
        isUploadFail = false;
        String uuid = StringUtil.getUUID();
        StrBuilder pictureUrl = new StrBuilder();
        for (int i = 0; i < resourseList.size(); i++) {
            String objectKey = null;
            if (sendType == AppConst.IMAGE_TYPE) {
                objectKey = "session" + "/" + uuid + "/" + (uuid.substring(0, 5) + i) + ".jpg";
            } else if (sendType == AppConst.GIF_TYPE) {
                objectKey = "session" + "/" + uuid + "/" + (uuid.substring(0, 5) + i) + ".gif";
            }
            pictureUrl.append(objectKey);
            if (i != resourseList.size() - 1) {
                pictureUrl.append(",");
            }
            LogUtil.e(TAG, "---->上传发布动态图片路径：" + resourseList.get(i));

            imageOSSClient.uploadImageBybyte(AppConst.TEST_BUCKET, objectKey, resourseList.get(i), i, new CallBackListener<String>() {
                @Override
                public void onSuccess(int requestCode, int responseCode, String result) {
                    progress = 0;
                    int index = Integer.valueOf(result);
                    LogUtil.e(TAG, "---->上传图片成功数：" + index);
                    if (index == resourseList.size() - 1) {//最后一张图片上传成功
                        String imgPath = pictureUrl.toString();
                        Observable.just(imgPath)
                                .subscribeOn(Schedulers.io())
                                .unsubscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<String>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(String s) {

                                    }
                                });
                    }
                }

                @Override
                public void onFailure(int requestCode, int responseCode, String result) {
                    isUploadFail = true;
                    UIUtils.showToastSafely("上传图片失败");
                }

                @Override
                public void setProgressCallback(long currentSize, long totalSize) {
                    Observable.just(currentSize)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aLong -> {

                            });

                }
            });
        }
    }

    /**
     * 压缩视频
     *
     * @param path
     */
    public void startCompress(String path) {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                final String newPath = compressVideo(path);
                subscriber.onNext(newPath);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "-----压缩视频失败-----" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        uploadVideoToOss(s);
                    }
                });
        mCompositeSubscription.add(subscribe);
    }

    /**
     * 压缩视频
     *
     * @param path
     * @return
     */
    private String compressVideo(String path) {
        //压缩配置
        BaseMediaBitrateConfig compressMode = new AutoVBRMode(35);
        compressMode.setVelocity("veryfast");
        // BaseMediaBitrateConfig compressMode =   new CBRMode(166,1280);
        compressMode.setVelocity(BaseMediaBitrateConfig.Velocity.VERYFAST);
        //视频频率
        int iRate = 15;
        //压缩比率：
        float fScale = 1.0f;
        LocalMediaConfig config = new LocalMediaConfig.Buidler()
                .setVideoPath(path)
                .captureThumbnailsTime(1)
                .doH264Compress(compressMode)
                .setFramerate(iRate)
                .setScale(fScale)
                .build();
        //开始压缩动作
        OnlyCompressOverBean onlyCompressOverBean = new LocalMediaCompress(config).startCompress();
        return onlyCompressOverBean.getVideoPath();
    }

    public void uploadVideoToOss(String videoPath) {
        LogUtil.e(TAG, "---------->上传视频");
        if (StringUtil.isNull(videoPath)) {
            showToast("请选择视频上传");
            return;
        }

        Session mSession = new Session();
        mSession.setSendStatus(AppConst.SENDING);
        mSession.setLocalUrl(videoPath);
        mSession.setChatType(Session.VIDEO);
//        mSession.getMemberInfo().setIcon("http://img.zcool.cn/community/01f7d155abb6dc6ac7258178bf5e4d.png");
        mSession.setSendOrReceive(AppConst.SESSION_SEND);
        mAdapter.addLastItem(mSession);
        rvMoveToBottom();


        ImageOSSClient imageOSSClient = new ImageOSSClient(AppConst.TEST_ENDPOINT, AppConst.ACCES_KEYID, AppConst.ACCES_KEYSECRET);
        imageOSSClient.initialize(mContext);
        String uuid = StringUtil.getUUID();
        String objectKey = "session" + "/" + uuid + "/" + (uuid.substring(0, 6)) + ".mp4";

        imageOSSClient.uploadVideo(AppConst.TEST_BUCKET, objectKey, videoPath, new CallBackListener<String>() {
            @Override
            public void onSuccess(int requestCode, int responseCode, String result) {
                LogUtil.e(TAG, "---->视频成功" + result);
                Observable.just(result)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String s) {
                                mSession.setExtra("100");
                                mSession.setContent(s);
                                sendVideo(mSession);
                            }
                        });

            }

            @Override
            public void onFailure(int requestCode, int responseCode, String result) {
                UIUtils.showToastSafely("上传视频失败");
            }

            @Override
            public void setProgressCallback(long currentSize, long totalSize) {
                Observable.just(currentSize)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            LogUtil.e(TAG, "------上传视频进度-----》" + progress);
                            progress = progress + 1;
                            if (progress >= 100 || currentSize == 0) {
                                mSession.setExtra(String.valueOf(100));
                            } else {
                                mSession.setExtra(String.valueOf(progress));
                            }
                            mAdapter.notifyDataSetChangedWrapper();
                        });
            }
        });
    }

    public void uploadVoiceToOss(Uri uri, int duration, int chatType) {
        LogUtil.e(TAG, "---------->上传语音");
        String voicePath = StringUtil.getRealFilePath(mContext, uri);
        if (StringUtil.isNull(voicePath)) {
            showToast("请选择语音上传");
            return;
        }
        ImageOSSClient imageOSSClient = new ImageOSSClient(AppConst.TEST_ENDPOINT, AppConst.ACCES_KEYID, AppConst.ACCES_KEYSECRET);
        imageOSSClient.initialize(mContext);
        String uuid = StringUtil.getUUID();
        String objectKey = "session" + "/" + uuid + "/" + (uuid.substring(0, 6)) + ".mp3";
        imageOSSClient.uploadVideo(AppConst.TEST_BUCKET, objectKey, voicePath, new CallBackListener<String>() {
            @Override
            public void onSuccess(int requestCode, int responseCode, String result) {
                Observable.just(result)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String s) {
                                sendTextMsg(s, duration, chatType);
                            }
                        });

            }

            @Override
            public void onFailure(int requestCode, int responseCode, String result) {
                UIUtils.showToastSafely("上传语音失败");
            }

            @Override
            public void setProgressCallback(long currentSize, long totalSize) {

            }
        });
    }

    /**
     * 图片转化成字节数组
     */
    private void compress(String s) {
        try {
            byte[] bytes = BitmapUtil.compressBitmapToBytes(s, 600, 0, 90, Bitmap.CompressFormat.JPEG);
            zipPicUrlList.add(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取图片地址
     *
     * @return
     */
    private List<String> getUrlList() {
        ArrayList<String> imgList = new ArrayList<>();
        for (String url : imagePaths) {
            imgList.add(url);
        }
        return imgList;
    }
}
