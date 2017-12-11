package com.zhongke.weiduo.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.signature.StringSignature;

import java.io.File;

/***
 * Created by Karma on 2017/6/8.
 * 类描述：图片加载工具（Glide）
 */
public class PhotoLoaderUtil {

    /**
     * 加载uri格式的图片
     *
     * @param context
     * @param def
     * @param uri
     * @param imageView
     */
    public static void display(Context context, int def, Uri uri, ImageView imageView) {
        if (!CommonUtil.isOnMainThread() || context == null) {
            return;
        }
        Glide.with(context)
                .load(uri)
                .placeholder(def)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//default
                .into(imageView);

    }

    /**
     * 加载string格式的图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param def
     */
    public static void display(Context context, ImageView imageView, String url, int def) {

        if (CommonUtil.isOnMainThread() && context != null) {
            Glide.with(context.getApplicationContext())
                    .load(url)
                    .placeholder(def)
                    .error(def)
                    .crossFade()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//default
                    .into(imageView);
        }

    }

    /**
     * 加载string格式的图片
     *
     * @param context
     * @param src
     * @param url
     * @param imageView
     */
    public static void display(Context context, ImageView imageView, String url, Drawable src) {

        if (CommonUtil.isOnMainThread() && context != null) {
            Glide.with(context.getApplicationContext())
                    .load(url)
                    .placeholder(src)
                    .error(src)
                    .crossFade()
                    .centerCrop()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)//default
                    .into(imageView);
        }

    }

    /**
     * 加载string格式的图片 跳过内存
     *
     * @param context
     * @param src
     * @param url
     * @param imageView
     */
    public static void displaySkipMemory(Context context, ImageView imageView, String url, Drawable src, String status) {

        if (!CommonUtil.isOnMainThread() || context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .placeholder(src)
                .signature(new StringSignature(status))//标识符
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)//跳过缓存
                .error(src)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)//default
                .into(imageView);
    }

    public static void display(ImageView view, String url, Drawable src) {

        display(view.getContext(), view, url, src);
    }

    /**
     * 加载res图片
     *
     * @param context
     * @param def
     * @param resourceId
     * @param imageView
     */
    public static void display(Context context, int def, int resourceId, ImageView imageView) {
        if (!CommonUtil.isOnMainThread() || context == null) {
            return;
        }
        Glide.with(context)
                .load(resourceId)
                .placeholder(def)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//default
                .into(imageView);
    }

    /**
     * 加载本地file
     *
     * @param imageView
     * @param file
     */
    public static void display(ImageView imageView, File file, Drawable src) {
        Glide.with(imageView.getContext())
                .load(file)
                .placeholder(src)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//default
                .into(imageView);
    }

    /**
     * 加载图片并显示成圆形
     *
     * @param context
     * @param src
     * @param url
     * @param imageView
     */
    public static void displayRoundImage(final Context context, final ImageView imageView, String url, Drawable src) {

        if (!CommonUtil.isOnMainThread() || context == null) {
            return;
        }
        Glide.with(context.getApplicationContext())
                .load(url)
                .asBitmap()
                .placeholder(src)
                .centerCrop()
                .error(src)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        roundedBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(roundedBitmapDrawable);
                    }
                });
    }

    /**
     * 跳过内存缓存和磁盘缓存
     *
     * @param context
     * @param imageView
     * @param url
     * @param src
     */
    public static void displayNoMenoryRoundImage(final Context context, final ImageView imageView, String url, Drawable src, String status) {

        if (!CommonUtil.isOnMainThread() || context == null) {
            return;
        }

        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(src)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .signature(new StringSignature(status))//标识符
                .error(src)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        roundedBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(roundedBitmapDrawable);
                    }
                });
    }

    public static void displayRoundImage(final Context context, final ImageView imageView, int resId) {

        if (!CommonUtil.isOnMainThread() || context == null) {
            return;
        }
        Glide.with(context)
                .load(resId)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        roundedBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(roundedBitmapDrawable);
                    }
                });
    }

    public static void displayRoundImage(ImageView view, String roundUrl, Drawable src) {
        displayRoundImage(view.getContext(), view, roundUrl, src);
    }

    /**
     * 加载缩略图
     *
     * @param context
     * @param def
     * @param url
     * @param imageView
     */
    public static void displayThumbImage(Context context, int def, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .thumbnail(0.2f)
                .crossFade()
                .placeholder(def)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }


}
