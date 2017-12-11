package com.zhongke.weiduo.mvp.model.entity;

import android.net.Uri;

/**
 * Created by Karma on 2017/7/4.
 * 类描述：
 */

public class VoiceMessage extends MessageContent {
    private int mDuration;
    private Uri voiceUrl;

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public Uri getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(Uri voiceUrl) {
        this.voiceUrl = voiceUrl;
    }
}
