package com.zhongke.weiduo.library.okhttp;

import android.database.Cursor;
import android.text.TextUtils;

import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.library.retrofit.HttpConstance;
import com.zhongke.weiduo.library.sqlbrite.SQLBriteProvider;
import com.zhongke.weiduo.mvp.db.local.Data;
import com.zhongke.weiduo.mvp.db.local.SqlHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${xingen} on 2017/11/6.
 * 定义一个请求的拦截器：
 * <p>
 * 添加一些共同的header表头:
 * 例如:
 * 数据格式，token,cockie等
 */

public class HeaderInterceptor implements Interceptor {

    /**
     * 常见的表单form格式
     */
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
    private final String HEADER_TOKEN = "token";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder()
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("User-Agent", "android")
                .addHeader("appId", HttpConstance.APP_ID);
        String token = ZkApplication.getInstance().getToken();
        if(TextUtils.isEmpty(token)){
            token = queryToken();
            ZkApplication.getInstance().setToken(token);
        }
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader(HEADER_TOKEN, token);
        }
        return chain.proceed(builder.build());
    }

    private String queryToken() {
        String token = "";
        Cursor cursor = null;
        try {
            cursor = SQLBriteProvider.getInstance(ZkApplication.getInstance()).getBriteDatabase().query(SqlHelper.createQueryTokenSQL(), new String[]{});
            if (cursor != null && cursor.moveToFirst()) {
                token = cursor.getString(cursor.getColumnIndex(Data.COLUMNS_USER_TOKEN));
            } else {
                token = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return token;
    }
}
