package com.zhongke.weiduo.mvp.base;

import com.zhongke.weiduo.app.utils.LogUtil;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Karma on 2017/6/27.
 * 类描述：
 */

public class BaseMainPresenter<V> {
      /*================== 以下是网络请求接口 ==================*/

    public BaseMainActivity mContext;

    public BaseMainPresenter(BaseMainActivity context) {
        mContext = context;
    }

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public V getView() {
        LogUtil.e("kevin", "------------" + mViewRef);
        return mViewRef != null ? mViewRef.get() : null;
    }
}
