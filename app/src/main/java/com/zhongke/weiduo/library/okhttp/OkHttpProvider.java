package com.zhongke.weiduo.library.okhttp;

import com.zhongke.weiduo.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ${xingen} on 2017/11/6.
 * 配置OkHttp3
 */

public class OkHttpProvider {
    public static OkHttpClient provideOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true) //设置出现错误进行重新连接。
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(new HeaderInterceptor())   //拦截器
                // .addNetworkInterceptor(interceptor)//自定义网络拦截器
//                .addInterceptor(interceptor)
                // .sslSocketFactory(HttpsUtils.getSslSocketFactory(null, null, null))//创建一个证书工厂
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            //打印一次请求的全部信息
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        // .cache(providesCache())
        OkHttpClient okhttpClient = builder.build();
        return okhttpClient;
    }
}
