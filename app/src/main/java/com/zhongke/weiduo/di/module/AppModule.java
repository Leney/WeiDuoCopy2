package com.zhongke.weiduo.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhongke.weiduo.app.utils.FileUtils;
import com.zhongke.weiduo.library.okhttp.HeaderInterceptor;
import com.zhongke.weiduo.library.retrofit.HttpConstance;
import com.zhongke.weiduo.library.retrofit.ApiService;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Karma on 2017/6/8.
 * 类描述：dagger2 module
 */
@Module
public class AppModule {
    private static final String TAG = "AppModule";
    private final Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    Interceptor providesIntercepter() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url();
                Log.d("intercept", "----->request url = " + url.toString());
                Log.d("intercept", "----->request headers = " + request.headers().toString());

                //更改请求头
//                if (!NetworkUtil.isConnected(mContext)){
//                    //如果没有网络，那么就强制使用缓存数据
//                    request = request.newBuilder()
//                            .cacheControl(CacheControl.FORCE_CACHE)
//                            .build();
//                }
                //获得返回头，如果有网络，就缓存一分钟,没有网络缓存四周
                Response originalResponse = chain.proceed(request);
                Log.d("response headers ", "-----response headers " + originalResponse.headers().toString());
//                LogUtil.d(originalResponse.body().toString());
//                ResponseBody body = originalResponse.body();  //response.body.string 不能调用多次。
//                if(body != null){
//
////                  LogUtil.e("resp ","-----> contentLength =" + body.contentLength());
//                    LogUtil.d("resp ","-----> contentType =" + body.contentType().toString());
//
//                    Reader in = body.charStream();
////                    LogUtil.e("reader ","-----> read res = " + reader.read());
//
//                    int s;
//                    String sb = "";
//                    while((s=in.read())!=-1)
//                    {
//                        sb+=(char)s;
//                    }
//                    in.close();
//
//                    LogUtil.d(sb);
//
//                }
                //判断是否有token
              /*  if (Your.sToken == null || alreadyHasAuthorizationHeader(originalRequest)) {
                    return chain.proceed(originalResponse);
                }
                Request authorised = originalResponse.newBuilder()
                        .header("Authorization", Your.sToken)
                        .build();
                return chain.proceed(authorised);//重新请求*/

                return originalResponse;
                //更改响应头
//                if (NetworkUtil.isConnected(mContext)){
//                    String cacheControl = request.cacheControl().toString();
//                    return originalResponse.newBuilder()
//                            .header("Cache-Control", cacheControl)
//                            .header("Content-Encoding", "gzip")
//                            .header("Vary","Accept-Encoding")
//                            .removeHeader("Pragma")
//                            .build();
//                }else {
//                    return originalResponse.newBuilder()
//                            .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
//                            .header("Content-Encoding", "gzip")
//                            .removeHeader("Pragma")
//                                   .build();
//                }
            }
        };
    }

    @Provides
    Cache providesCache() {
        File httpCacheFile = FileUtils.getDiskCacheDir("response");
        return new Cache(httpCacheFile, 1024 * 100 * 1024);
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true) //设置出现错误进行重新连接。
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(new HeaderInterceptor())   //拦截器
                .addNetworkInterceptor(interceptor)//自定义网络拦截器
//                .addInterceptor(interceptor)
                // .sslSocketFactory(HttpsUtils.getSslSocketFactory(null, null, null))//创建一个证书工厂
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .cache(providesCache())
                .build();
        return okhttpClient;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okhttpClient) {

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl(HttpConstance.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public ApiService provideUserApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    public DataManager provideUserManager(SharedPreferences preferences, ApiService service) {
      //  return new DataManager(preferences, service);
        return null;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

}
