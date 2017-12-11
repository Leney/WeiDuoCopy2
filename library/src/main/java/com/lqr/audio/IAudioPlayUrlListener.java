package com.lqr.audio;

/**
 * Created by Karma on 2017/7/7.
 * 类描述：
 */

public interface IAudioPlayUrlListener {
    void onStart(String var1);

    void onStop(String var1);

    void onComplete(String var1);
}
