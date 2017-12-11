package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqr.audio.AudioPlayManager;
import com.lqr.audio.IAudioPlayUrlListener;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.DesireListBean2;

import java.util.List;

/**
 * Created by ${xingen} on 2017/9/21.
 * <p>
 * 具体使用介绍：http://www.jianshu.com/p/b343fcff51b0
 */

public class DesireListAdapter extends BaseQuickAdapter<DesireListBean2.ChildWishBean, BaseViewHolder> {

    /**
     * 是否是通过推荐活动进入的愿望
     */
    private boolean isRecommend = false;
    private Context context;

    private int[] levelImgs = {R.drawable.activity_desirte_level1, R.drawable.activity_desirte_level2, R.drawable.activity_desirte_level3};
    private int num = 0;
    private ImageView audio;

    public DesireListAdapter setRecommend(boolean recommend) {
        isRecommend = recommend;
        return this;
    }

    public DesireListAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<DesireListBean2.ChildWishBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DesireListBean2.ChildWishBean item) {
        View line = helper.getView(R.id.desire_line_1);
        CheckBox checkBox = helper.getView(R.id.desire_check_tv);
        TextView recommendText = helper.getView(R.id.desire_choose_good_tv);
        TextView info = helper.getView(R.id.desire_content_iv);
        TextView audioBg = helper.getView(R.id.desire_audio_bg);
        audio = helper.getView(R.id.desire_audio_icon);
        TextView createTime = helper.getView(R.id.desire_time_tv);
        if (isRecommend) {
            line.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.VISIBLE);
            recommendText.setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.desire_check_tv);
        } else {
            line.setVisibility(View.INVISIBLE);
            checkBox.setVisibility(View.GONE);
            recommendText.setVisibility(View.GONE);
        }
        //wishType 类型（1.文字 2.音频 3.礼物）
        if (item.getWishType() == 2 && !item.getAudioUrl().isEmpty()) {
            info.setVisibility(View.GONE);
            audioBg.setVisibility(View.VISIBLE);
            audio.setVisibility(View.VISIBLE);
            audioBg.setOnClickListener(view -> playAudio(audio, item));
        } else {
            audioBg.setOnClickListener(null);
        }
        ImageView imageView = helper.getView(R.id.desire_icon_iv);
        if (!TextUtils.isEmpty(item.getCoverUrl())) {
            PhotoLoaderUtil.display(imageView.getContext(), imageView, item.getCoverUrl(), R.mipmap.ic_launcher);
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
        info.setText(item.getWishInfo());
        createTime.setText(item.getCreateTime());
        helper.addOnClickListener(R.id.desire_check_tv).addOnClickListener(R.id.desire_choose_good_tv);
    }
    /**
     * 播放语音
     *
     * @param imageView
     * @param childWishBean
     */
    private void playAudio(final ImageView imageView, final DesireListBean2.ChildWishBean childWishBean) {
        AudioPlayManager audioPlayManager = AudioPlayManager.getInstance();
        if (audioPlayManager.isRecordPlay()) {
            audioPlayManager.stopPlay();
        }
        audioPlayManager.startPlay(ZkApplication.getInstance(), childWishBean.getAudioUrl(), new IAudioPlayUrlListener() {
                @Override
                public void onStart(String var1) {
                    startAnimation();
                }
                @Override
                public void onStop(String var1) {
                    stopAnimation();
                }
                @Override
                public void onComplete(String var1) {
                    startAnimation();
                }
                private AnimationDrawable getImageAnimation() {
                    if (imageView == null) {
                        return null;
                    }
                    return (AnimationDrawable) imageView.getBackground();
                }
                /**
                 *   开始动画
                 */
                public void startAnimation() {
                    AnimationDrawable animationDrawable = getImageAnimation();
                    if (animationDrawable == null) {
                        return;
                    }
                    animationDrawable.start();
                }
                /**
                 * 停止动画，切设置为第一种默认的
                 */
                public void stopAnimation() {
                    AnimationDrawable animationDrawable = getImageAnimation();
                    if (animationDrawable == null) {
                        return;
                    }
                    animationDrawable.stop();
                    animationDrawable.selectDrawable(0);
                }
            });


    }


}
