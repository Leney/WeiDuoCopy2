package com.zhongke.weiduo.di.component;

import android.content.Context;

import com.zhongke.weiduo.di.module.AppModule;
import com.zhongke.weiduo.mvp.base.BaseActivity;
import com.zhongke.weiduo.mvp.base.BaseFragment;
import com.zhongke.weiduo.mvp.ui.activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Karma on 2017/6/8.
 * 类描述：
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    Context context();  // 提供Applicaiton的Context

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    void inject(LoginActivity loginActivity);

}
