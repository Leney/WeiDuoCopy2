package com.zhongke.weiduo.app.listener;

/**
 * Created by maimingliang on 16/6/22.
 */


import com.zhongke.weiduo.app.utils.ApiException;
import com.zhongke.weiduo.library.retrofit.HttpResult;

import rx.functions.Func1;

/**
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    private static final String TAG = "HttpResultFunc";

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getCode() != 0) {
            throw new ApiException(httpResult.getCode(), httpResult.getMsg() == null ? "" : httpResult.getMsg());
        }
        return httpResult.getResult();
    }
}

