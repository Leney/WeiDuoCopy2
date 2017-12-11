package com.zhongke.weiduo.di.component;

/**
 * Created by Karma on 2017/6/8.
 * 类描述：
 */

public class ComponentHolder {

    private static AppComponent sAppComponent;

    public static void setAppComponent(AppComponent appComponent) {
        sAppComponent = appComponent;
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
