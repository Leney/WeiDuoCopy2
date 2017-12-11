package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.emoji.MoonUtils;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.common.AppConst;
import com.zhongke.weiduo.app.utils.BitmapUtil;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.app.utils.StringUtil;
import com.zhongke.weiduo.app.utils.TimeUtils;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.mvp.model.entity.Session;
import com.zhongke.weiduo.mvp.presenter.SessionAtPresenter2;
import com.zhongke.weiduo.mvp.ui.widget.BubbleImageView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.zhongke.weiduo.R.id.bivPic;


/**
 * Created by Karma on 2017/6/27.
 * 类描述：会话列表adapter
 */

public class SessionAdapter2 extends LQRAdapterForRecyclerView<Session> {
    private static final String TAG = "SessionAdapter";

    private Context mContext;
    private SessionAtPresenter2 mPresenter;
    private List<Session> mData;

    private static final int SEND_TEXT = R.layout.item_text_send;//文字发送方
    private static final int RECEIVE_TEXT = R.layout.item_text_receive;//文字接收方

    private static final int SEND_IMAGE = R.layout.item_image_send;//图片发送方
    private static final int RECEIVE_IMAGE = R.layout.item_image_receive;//图片接收方

    private static final int SEND_STICKER = R.layout.item_sticker_send;//表情发送方
    private static final int RECEIVE_STICKER = R.layout.item_sticker_receive;

    private static final int SEND_VIDEO = R.layout.item_video_send;//视频发送方
    private static final int RECEIVE_VIDEO = R.layout.item_video_receive;

    private static final int RECEIVE_VOICE = R.layout.item_audio_receive;//语音接收方
    private static final int SEND_VOICE = R.layout.item_audio_send;

    private static final int UNDEFINE_MSG = R.layout.item_no_support_msg_type;//不支持的消息类型

    public static final int TEXT = 1;
    public static final int VOICE = 2;
    public static final int IMAGE = 3;
    public static final int VIDEO = 4;
    public static final int STICKER = 5;

    public SessionAdapter2(Context context, List<Session> data, SessionAtPresenter2 presenter) {
        super(context, data);
        this.mContext = context;
        this.mData = data;
        this.mPresenter = presenter;
    }

    /**
     * 转换
     *
     * @param helper
     * @param item
     * @param position
     */
    @Override
    public void convert(LQRViewHolderForRecyclerView helper, Session item, int position) {
        setTime(helper, item, position);
        setView(helper, item, position);

        setAvatar(helper, item, position);//设置头像
        setName(helper, item, position);//设置名字
        setStatus(helper, item, position);//设置发送状态
        setOnClick(helper, item, position);//设置点击
    }

    @Override
    public int getItemViewType(int position) {

        Session msg = mData.get(position);
//        LogUtil.e(TAG, "------>类型=" + msg.getSendOrReceive());
        boolean isSend = msg.getSendOrReceive() == AppConst.SESSION_RECEIVE ? true : false;
        int status = msg.getChatType();
        if (status == TEXT) {
            return isSend ? RECEIVE_TEXT : SEND_TEXT;
        } else if (status == VOICE) {
            return isSend ? RECEIVE_VOICE : SEND_VOICE;
        } else if (status == IMAGE) {
            return isSend ? RECEIVE_IMAGE : SEND_IMAGE;
        } else if (status == VIDEO) {
            return isSend ? RECEIVE_VIDEO : SEND_VIDEO;
        } else if (status == STICKER) {
            return isSend ? RECEIVE_STICKER : SEND_STICKER;
        }
        return UNDEFINE_MSG;

    }

    private void setTime(LQRViewHolderForRecyclerView helper, Session item, int position) {
        boolean isSend = item.getSendOrReceive() == AppConst.SESSION_RECEIVE ? true : false;
        long msgTime = isSend ? item.getReceiveTime() : item.getSendTime();
        if (position > 0) {
            Session preMsg = mData.get(position - 1);
            boolean isSendForPreMsg = preMsg.getSendOrReceive() == AppConst.SESSION_RECEIVE ? true : false;
            long preMsgTime = isSendForPreMsg ? preMsg.getReceiveTime() : preMsg.getSendTime();
            if (msgTime - preMsgTime > (5 * 60 * 1000)) {
                helper.setViewVisibility(R.id.tvTime, View.VISIBLE).setText(R.id.tvTime, TimeUtils.getMsgFormatTime(msgTime));
            } else {
                helper.setViewVisibility(R.id.tvTime, View.GONE);
            }
        } else {
            helper.setViewVisibility(R.id.tvTime, View.VISIBLE).setText(R.id.tvTime, TimeUtils.getMsgFormatTime(msgTime));
        }
    }

    private void setView(LQRViewHolderForRecyclerView helper, Session item, int position) {
        LogUtil.e(TAG, "--------------adapter数据" + item.getContent() + " 位置=" + item.getLocalUrl());
        boolean isSend = item.getSendOrReceive() == AppConst.SESSION_RECEIVE ? true : false;
        String photoUrl;
        if (item.getChatType() == Session.TEXT) {
            MoonUtils.identifyFaceExpression(mContext, helper.getView(R.id.tvText), item.getContent(), ImageSpan.ALIGN_BOTTOM);
        } else if (item.getChatType() == Session.IMAGE) {
            BubbleImageView imageView = helper.getView(bivPic);
            photoUrl = AppConst.DOWND_PHOTO + item.getContent();
            if (isSend) {
                Glide.with(mContext).load(photoUrl).error(R.drawable.default_img_failed).override(UIUtils.dip2Px(100), UIUtils.dip2Px(150)).fitCenter().into(imageView);
            } else {
//                Glide.with(mContext).load(item.getLocalUrl() != null ? item.getLocalUrl() : photoUrl).error(R.drawable.default_img_failed).override(UIUtils.dip2Px(80), UIUtils.dip2Px(150)).centerCrop().into(imageView);
                Glide.with(mContext).load(item.getLocalUrl() != null ? item.getLocalUrl() : photoUrl).error(R.drawable.default_img_failed).override(UIUtils.dip2Px(100), UIUtils.dip2Px(150)).fitCenter().into(imageView);

            }
        } else if (item.getChatType() == Session.VIDEO) {
            BubbleImageView imageView = helper.getView(bivPic);
            String videoPath = AppConst.DOWND_PHOTO + item.getContent();
            if (isSend) {
                Bitmap bitmap = BitmapUtil.createVideoThumbnail(videoPath, 1);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] bytes = baos.toByteArray();
                Glide.with(mContext).load(bytes).error(R.drawable.default_img_failed).override(UIUtils.dip2Px(100), UIUtils.dip2Px(150)).fitCenter().into(imageView);
//                VideoThumbLoader.getInstance().showThumb(videoPath,imageView,100,200);
            } else {
                Bitmap bitmap = BitmapUtil.createVideoThumbnail(item.getLocalUrl() != null ? item.getLocalUrl() : videoPath, 1);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] bytes = baos.toByteArray();
                Glide.with(mContext).load(bytes).error(R.drawable.default_img_failed).override(UIUtils.dip2Px(100), UIUtils.dip2Px(150)).fitCenter().into(imageView);
//                VideoThumbLoader.getInstance().showThumb(item.getLocalUrl() != null ? item.getLocalUrl() : videoPath,imageView,100,200);
            }

        } else if (item.getChatType() == Session.VOICE) {
//            int increment = (int) (UIUtils.getDisplayWidth() / 2 / AppConst.DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND * item.getDuraction());
            int increment = ((int)(UIUtils.getDisplayWidth() * 0.6f)) / 2 / AppConst.DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND * item.getDuraction();
            RelativeLayout rlAudio = helper.setText(R.id.tvDuration, item.getDuraction() + "''").getView(R.id.rlAudio);
            ViewGroup.LayoutParams params = rlAudio.getLayoutParams();
            params.width = UIUtils.dip2Px(65) + UIUtils.dip2Px(increment);
            rlAudio.setLayoutParams(params);
        } else if (item.getChatType() == Session.STICKER) {
            ImageView ivPic = helper.getView(R.id.ivSticker);
            String url = item.getContent();
            String suffix = url.substring(url.lastIndexOf(".") + 1);
//            String secondPath = StringUtil.subString4Byte(url, 3);
            if (suffix.equals("png")) {
                Glide.with(mContext).load(item.getLocalUrl() == null ? AppConst.STICKER_PATH + item.getContent() :
                        item.getLocalUrl()).placeholder(R.drawable.default_img).error(R.drawable.default_img_failed).centerCrop().into(ivPic);
            } else if (suffix.equals("gif")) {
                Glide.with(mContext).load(item.getLocalUrl() == null ? AppConst.STICKER_PATH + item.getContent() :
                        item.getLocalUrl()).asGif().placeholder(R.drawable.default_img).error(R.drawable.default_img_failed).diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(ivPic);
            }
        }

    }

    /**
     * 设置头像
     *
     * @param helper
     * @param item
     * @param position
     */
    private void setAvatar(LQRViewHolderForRecyclerView helper, Session item, int position) {
        ImageView ivAvator = helper.getView(R.id.ivAvatar);
        LogUtil.i("llj","聊天头像地址-------->>>"+item.getMemberInfo().getIcon());
        if (!StringUtil.isNull(item.getMemberInfo().getIcon())) {
            PhotoLoaderUtil.display(mContext, ivAvator, item.getMemberInfo().getIcon(), UIUtils.getResource().getDrawable(R.drawable.default_header));
        } else {
            PhotoLoaderUtil.display(mContext, ivAvator, "https://ws1.sinaimg.cn/large/610dc034ly1fiednrydq8j20u011itfz.jpg", UIUtils.getResource().getDrawable(R.drawable.default_header));
        }
    }

    /**
     * 设置昵称
     *
     * @param helper
     * @param item
     * @param position
     */
    private void setName(LQRViewHolderForRecyclerView helper, Session item, int position) {
        helper.setViewVisibility(R.id.tvName, View.GONE);
    }

    /**
     * 设置内容发送状态
     *
     * @param helper
     * @param item
     * @param position
     */
    private void setStatus(LQRViewHolderForRecyclerView helper, Session item, int position) {
        boolean isSend = item.getSendOrReceive() == AppConst.SESSION_RECEIVE ? true : false;
        int chatStatus = item.getChatType();
        int sendStatus = item.getSendStatus();
        LogUtil.e(TAG, "------------>发送状态=" + sendStatus);
        if (chatStatus == Session.IMAGE) {
            BubbleImageView bivPic = helper.getView(R.id.bivPic);
            if (!isSend) {
                if (sendStatus == AppConst.SENDING) {
                    bivPic.setProgressVisible(true);
                    if (!TextUtils.isEmpty(item.getExtra()))
                        bivPic.setPercent(Integer.valueOf(item.getExtra()));
                    bivPic.showShadow(true);
                    helper.setViewVisibility(R.id.llError, View.GONE);
                } else if (sendStatus == AppConst.FAILED) {
                    bivPic.setProgressVisible(false);
                    bivPic.showShadow(false);
                    helper.setViewVisibility(R.id.llError, View.VISIBLE);
                } else if (sendStatus == AppConst.SENT) {
                    bivPic.setProgressVisible(false);
                    bivPic.showShadow(false);
                    helper.setViewVisibility(R.id.llError, View.GONE);
                }
            } else {
                bivPic.setProgressVisible(false);
                bivPic.showShadow(false);
                helper.setViewVisibility(R.id.llError, View.GONE);
            }
        } else if (chatStatus == Session.VIDEO) {
            BubbleImageView bivPic = helper.getView(R.id.bivPic);
            if (!isSend) {
                if (sendStatus == AppConst.SENDING) {
                    bivPic.setProgressVisible(true);
                    if (!TextUtils.isEmpty(item.getExtra()))
                        bivPic.setPercent(Integer.valueOf(item.getExtra()));
                    bivPic.showShadow(true);
                    helper.setViewVisibility(R.id.llError, View.GONE);
                    helper.setViewVisibility(R.id.ivPlay, View.GONE);
                } else if (sendStatus == AppConst.FAILED) {
                    bivPic.setProgressVisible(false);
                    bivPic.showShadow(false);
                    helper.setViewVisibility(R.id.llError, View.VISIBLE);
                    helper.setViewVisibility(R.id.ivPlay, View.VISIBLE);
                } else if (sendStatus == AppConst.SENT) {
                    bivPic.setProgressVisible(false);
                    bivPic.showShadow(false);
                    helper.setViewVisibility(R.id.llError, View.GONE);
                    helper.setViewVisibility(R.id.ivPlay, View.VISIBLE);
                }
            } else {
                bivPic.setProgressVisible(false);
                bivPic.showShadow(false);
                helper.setViewVisibility(R.id.llError, View.GONE);
            }
        } else if (chatStatus == Session.TEXT) {
            if (!isSend) {
                if (sendStatus == AppConst.SENDING) {
                    helper.setViewVisibility(R.id.pbSending, View.VISIBLE).setViewVisibility(R.id.llError, View.GONE);
                } else if (sendStatus == AppConst.FAILED) {
                    helper.setViewVisibility(R.id.pbSending, View.GONE).setViewVisibility(R.id.llError, View.VISIBLE);
                } else if (sendStatus == AppConst.SENT) {
                    helper.setViewVisibility(R.id.pbSending, View.GONE).setViewVisibility(R.id.llError, View.GONE);
                }
            }

        } else if (chatStatus == Session.VOICE) {
            if (!isSend) {
                if (sendStatus == AppConst.SENDING) {
                    helper.setViewVisibility(R.id.pbSending, View.VISIBLE).setViewVisibility(R.id.llError, View.GONE);
                } else if (sendStatus == AppConst.FAILED) {
                    helper.setViewVisibility(R.id.pbSending, View.GONE).setViewVisibility(R.id.llError, View.VISIBLE);
                } else if (sendStatus == AppConst.SENT) {
                    helper.setViewVisibility(R.id.pbSending, View.GONE).setViewVisibility(R.id.llError, View.GONE);
                }
            }
        }

    }

    private void setOnClick(LQRViewHolderForRecyclerView helper, Session item, int position) {//重发信息
        helper.getView(R.id.llError).setOnClickListener(v -> {
            mData.remove(position);
            mPresenter.setAdapter();
            int chatStatus = item.getChatType();
            String content = item.getContent();
            if (chatStatus == Session.TEXT) {
                mPresenter.sendTextMsg(content, item.getDuraction(), item.getChatType());
            } else if (chatStatus == Session.IMAGE) {
                mPresenter.sendTextMsg(content, item.getDuraction(), item.getChatType());
            } else if (chatStatus == Session.VIDEO) {
                mPresenter.sendTextMsg(content, item.getDuraction(), item.getChatType());
            } else if (chatStatus == Session.VOICE) {
                mPresenter.sendTextMsg(content, item.getDuraction(), item.getChatType());
            }

        });
    }
}
