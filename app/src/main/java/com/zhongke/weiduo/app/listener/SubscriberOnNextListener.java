package com.zhongke.weiduo.app.listener;

/**
 * Created by karma on 16/3/10.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void onError(Throwable t);
}
