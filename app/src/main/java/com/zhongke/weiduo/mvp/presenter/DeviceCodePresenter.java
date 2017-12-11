package com.zhongke.weiduo.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.library.retrofit.HttpConstance;
import com.zhongke.weiduo.library.zxing.ZXingUtils;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.DeviceCodeContract;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.ToastUtil;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${xingen} on 2017/12/5.
 */

public class DeviceCodePresenter extends BasePresenter {
    private DeviceCodeContract view;

    public DeviceCodePresenter(DeviceCodeContract view) {
        this.view = view;
    }

    public void loadData() {
        createScanCode();
    }

    /**
     * 生成二维码，且加载到对应的图片中
     */
    private void createScanCode() {
        String url = getScanContent();
        int width = DisplayUtils.dip2px(appContext, 300);
        int height = width;
        Subscription subscription = Observable.create(subscriber -> {
            Bitmap logoBitmap = BitmapFactory.decodeResource(appContext.getResources(), R.mipmap.app_icon, new BitmapFactory.Options());
            Bitmap bitmap = ZXingUtils.createQRImage(url, width, height, logoBitmap);
            subscriber.onNext(bitmap);
        }).subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        bitmap -> view.showCodeBitmap((Bitmap) bitmap),
                        error -> ToastUtil.show(appContext, "生成二维码图片出错", Toast.LENGTH_SHORT)
                );
        this.compositeSubscription.add(subscription);
    }

    /**
     * 生成二维码扫描的内容
     *
     * @return
     */
    public String getScanContent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HttpConstance.BASE_URL);
        stringBuilder.append("/user/get_user_info_by_devicecode?");
        stringBuilder.append("deviceCode=");
        stringBuilder.append(view.getDeviceCode());
        return stringBuilder.toString();
    }

}
