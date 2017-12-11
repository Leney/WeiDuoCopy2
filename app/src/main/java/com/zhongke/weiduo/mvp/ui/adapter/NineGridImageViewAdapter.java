package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by ${tanlei} on 2017/8/25.
 */

public abstract class NineGridImageViewAdapter<T> {
    public abstract void onDisplayImage(Context context, ImageView imageView, T t);

    public ImageView generateImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    //这里可以添加你所需要的事件之类的方法
}
