package com.zhongke.weiduo.library.Luban;

import android.content.Context;

import com.zhongke.weiduo.library.rxjava.SubscribeUtils;

import java.io.File;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import top.zibin.luban.Luban;

/**
 * Created by ${xinGen} on 2017/11/14.
 * <p>
 * Luban（鲁班） —— Android图片压缩工具，仿微信朋友圈压缩策略。
 */

public class LubanUtils {
    /**
     * 采用RxJava+Luban压缩图片
     * @param context
     * @param filePath
     * @param subscriber
     * @return
     */
    public static Subscription scalePictureWithRxJava(Context context, String filePath, Subscriber<File> subscriber) {
        return Observable.just(new File(filePath))
                .flatMap(file -> {
                    File scaleFile = null;
                    try {
                        scaleFile = Luban.with(context).load(file).get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //若是鲁班压缩失败，返回原图片。
                   scaleFile=( scaleFile==null?scaleFile:file);
                    return Observable.just(scaleFile);
                })
                .compose(SubscribeUtils.createTransformer())
                .subscribe(subscriber);
    }
}
