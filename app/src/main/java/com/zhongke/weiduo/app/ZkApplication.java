package com.zhongke.weiduo.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lqr.emoji.LQREmotionKit;
import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.loader.ImageLoader;
import com.lqr.imagepicker.view.CropImageView;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhongke.weiduo.app.utils.CommonUtil;
import com.zhongke.weiduo.app.utils.SPUtils;
import com.zhongke.weiduo.app.utils.StringUtil;
import com.zhongke.weiduo.zxing.camera.CameraManager;
import com.zxy.tiny.Tiny;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.Stack;


/**
 * Created by Karma on 2017/6/8.
 * 类描述：程序入口
 */

public class ZkApplication extends Application {
    private static final String TAG = "ZkApplication";
    private static ZkApplication instance;
    private static Stack<Activity> activityStack;
    private String IMEI;



    /**
     *

     */
    private String token;

    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Context mContext;//上下文
    private static Thread mMainThread;//主线程
    private static long mMainThreadId;//主线程id
    private static Looper mMainLooper;//循环队列
    private static Handler mHandler;//主线程Handler

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        activityStack = new Stack<>();
        //对全局属性赋值
        mContext = getApplicationContext();
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
        mHandler = new Handler();
        initQQBugConfig();

//        OkHttpClient httpClient = new OkHttpClient.Builder().
//                connectTimeout(10000L, TimeUnit.MILLISECONDS).
//                build();
//        OkHttpUtils.initClient(httpClient);


   /*     //dagger2
        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        ComponentHolder.setAppComponent(appComponent);*/


        // 时间格式工具初始化
        JodaTimeAndroid.init(getApplicationContext());
        //压缩图片
        Tiny.getInstance().init(this);
//        //初始化IM
//        IMClient.getInstance().initSdk();
        //初始化仿微信控件ImagePicker
        initImagePicker();
        //初始化表情控件
        LQREmotionKit.init(this, (context, path, imageView) -> Glide.with(context).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView));
        initCameraManager();

    }

    /**
     * 腾讯Bugly初始化
     */
    private void initQQBugConfig() {
        final String appId="afb7afea6d";
        CrashReport.initCrashReport(getApplicationContext(), appId, false);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 突破65535方法数限制
        MultiDex.install(base);
    }

    /**
     * 初始化二维码中相机
     */
    private void initCameraManager() {
        CameraManager.init(this);
    }

    /**
     * 初始化仿微信控件ImagePicker
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
                Glide.with(getContext()).load(Uri.parse("file://" + path).toString()).centerCrop().into(imageView);
            }

            @Override
            public void clearMemoryCache() {

            }
        });   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    /**
     * 单例模式
     *
     * @return
     */
    public synchronized static ZkApplication getInstance() {
        return instance;
    }


    private String getIMEI() {
        IMEI = (String) SPUtils.get(getApplicationContext(), "IMEI", "");
        if (StringUtil.isNull(IMEI)) {
            IMEI = CommonUtil.getIMEI(this);
            SPUtils.put(this, "IMEI", IMEI);
        }
        return IMEI;
    }


    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        ZkApplication.mContext = mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        ZkApplication.mMainThread = mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(long mMainThreadId) {
        ZkApplication.mMainThreadId = mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static void setMainThreadLooper(Looper mMainLooper) {
        ZkApplication.mMainLooper = mMainLooper;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void setMainHandler(Handler mHandler) {
        ZkApplication.mHandler = mHandler;
    }

    /**
     * 加入一个新的Activity堆栈中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        activityStack.push(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        activityStack.remove(activity);
        activity.finish();
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
