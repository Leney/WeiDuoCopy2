package com.zhongke.weiduo.mvp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Karma on 2017/6/23.
 * 类描述：BaseFragmentPresenter
 */

public class BaseFragmentPresenter<V> {

    /*================== 以下是网络请求接口 ==================*/
    public BaseFragmentActivity mContext;

    public BaseFragmentPresenter(BaseFragmentActivity context) {
        mContext = context;
    }

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);//弱引用
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
        return mViewRef != null ? mViewRef.get() : null;
    }
}
