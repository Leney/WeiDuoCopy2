package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;

/**
 * Created by Karma on 2017/6/15.
 * 类描述：
 */

public interface TestBusContract<T> extends BaseView {
    void success(T result);
}
