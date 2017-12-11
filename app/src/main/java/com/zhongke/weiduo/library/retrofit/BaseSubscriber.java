package com.zhongke.weiduo.library.retrofit;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.zhongke.weiduo.mvp.broadcastreceiver.HandleTokenExpiredBroadcastReceiver;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * Created by ${xingen} on 2017/11/6.
 * <p>
 * 定义一个基本操作的Subscriber
 */

public class BaseSubscriber<T> extends Subscriber<HttpResult<T>> {
    private Context context;
    private  ResponseResultListener<T> resultListener;
    public  final Handler mainHandler = new Handler(Looper.getMainLooper());

    public BaseSubscriber(Context context, ResponseResultListener<T> resultListener) {
        this.context = context;
        this.resultListener=resultListener;
    }

    private boolean isCancelListener() {
        return getResultListener() == null ? true : false;
    }

    private ResponseResultListener<T> getResultListener() {
        return resultListener;
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        mainHandler.post(() -> {
                    if (!isCancelListener()) {
                       getResultListener().failure(handleError(e));
                    }
                }
        );
    }

    /**
     * @param runnable
     */
    private void postRunnable(Runnable runnable) {
        mainHandler.post(runnable);
    }

    @Override
    public void onNext(HttpResult<T> result) {
        switch (result.getCode()) {
            //请求成功
            case HttpConstance.RESULT_CODE_SUCCESS:
                mainHandler.post(() -> {
                    if (!isCancelListener()) {
                        T t = result.getResult();
                        getResultListener().success(t);
                    }
                });
                break;
            //请求失败
            case HttpConstance.RESULT_CODE_ERROR:
                mainHandler.post(() -> {
                    if (!isCancelListener()) {
                        getResultListener().failure(CommonException.newInstance(CommonException.RESULT_FAIRL, result.getMsg()));
                    }
                });
                break;
            //token过期
            case HttpConstance.RESULT_CODE_EXPIRED:
                mainHandler.post(() -> {
                    if (!isCancelListener()) {
                        getResultListener().failure(CommonException.newInstance(CommonException.RESULT_EXPIRED, "token过期"));
                    }
                });
                //这里统一处理token操作
                context.sendBroadcast(HandleTokenExpiredBroadcastReceiver.builderIntent());
                break;
            //默认的状态，处理特殊情况
            default:
                mainHandler.post(() -> {
                    if (!isCancelListener()) {
                       getResultListener().failure(CommonException.newInstance(result.getCode(), result.getMsg()));
                    }
                });
        }
    }



    /**
     * 处理常见的异常
     *
     * @param e
     * @return
     */
    protected CommonException handleError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            //Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
            return CommonException.newInstance(CommonException.RESULT_NET_ERROR, "网络异常");
        } else if (e instanceof ConnectException) {
            //Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
            return CommonException.newInstance(CommonException.RESULT_NET_ERROR, "网络异常");
        } else if (e instanceof UnknownHostException) {
            //Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
            return CommonException.newInstance(CommonException.RESULT_NET_ERROR, "网络异常");
        } else if (e instanceof SocketException) {
            //Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return CommonException.newInstance(CommonException.RESULT_NET_ERROR, "网络异常");
        } else if (e instanceof JsonSyntaxException) {
            return CommonException.newInstance(CommonException.RESULT_PARSE_ERROR, " Json解析: " + e.getMessage());
        } else {
            return CommonException.newInstance(CommonException.RESULT_UNKNOWN_ERROR, e.getMessage());
        }
    }
}
