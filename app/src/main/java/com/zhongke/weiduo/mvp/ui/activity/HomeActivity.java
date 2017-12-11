package com.zhongke.weiduo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.library.rxjava.ObservableUtils;
import com.zhongke.weiduo.library.rxjava.SubscribeUtils;
import com.zhongke.weiduo.util.ActivityUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${xingen} on 2017/11/7.
 * 首页界面
 */

public class HomeActivity extends AppCompatActivity {
    private CompositeSubscription compositeSubscription;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // PhotoLoaderUtil.display(this,R.mipmap.home_bg,R.mipmap.home_bg,imageView);
        ActivityUtils.addActivity(this);
        this.compositeSubscription = new CompositeSubscription();
        executeTask();
        executeDelayTask();
    }

    /**
     * 执行一个定时任务
     */
    private void executeDelayTask() {
        Subscription subscription = Observable
                .timer(2, TimeUnit.SECONDS, Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber -> {
                    if (checkeLogin()) {
                        MainActivity.startActivity(HomeActivity.this);
                    } else {
                        LoginActivity.startActivity(HomeActivity.this);
                    }
                    HomeActivity.this.finish();
                });
        this.compositeSubscription.add(subscription);
    }


    /**
     * @return
     */
    private Action1<String> createAction() {
        return result -> {
            token = result;
            ZkApplication.getInstance().setToken(result);
        };
    }

    /**
     * 检查是否登入过：
     * <p>
     * 1. token为空，未登录。
     *
     * @return
     */
    private boolean checkeLogin() {
        return !TextUtils.isEmpty(token);
    }

    /**
     * 查询数据库
     */
    private void executeTask() {
        Subscription subscription = ObservableUtils.createQueryToken()
                .compose(SubscribeUtils.createTransformer())
                .subscribe(createAction());
        this.compositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
        this.compositeSubscription.clear();
    }
}
