package com.zhongke.weiduo.app.listener;

/**
 * Created by Karma on 2017/6/8.
 * 类描述：图片上传回调接口
 */

public interface CallBackListener<T> {
    void onSuccess(int requestCode, int responseCode, T result);

    void onFailure(int requestCode, int responseCode, T result);

    void setProgressCallback(long currentSize,long totalSize);

}
