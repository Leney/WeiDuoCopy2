package com.zhongke.weiduo.util;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ${xingen} on 2017/6/20.
 */

public class TextViewUtils {
    /**
     * 随机分配一个新的Id
     *
     * @return
     */
    public static int getViewId() {
        int viewId;
        if (Build.VERSION.SDK_INT < 17) {
            //采用View中源码
            viewId = generateViewId();
        } else {
            //采用View中generateViewId()静态方法
            viewId = View.generateViewId();
        }
        return viewId;
    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Generate a value suitable for use in {@link #setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    private static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
    /**
     * 实现多颜色字体
     *
     * spannableStringBuilder.setSpan( ):
     * 参数1：对应的各种Span
     * 参数2：开始应用指定Span的位置，索引从0开始
     * 参数3：结束应用指定Span的位置，特效并不包括这个位置
     * 参数4：指定前后插入的字符是否采用本次样式
     * <p/>
     * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE:前后插入的字符不采用本次样式
     * @param colors
     * @param s
     * @return
     */
    public static SpannableStringBuilder setColorPan(int[] colors, String[] s){
        SpannableStringBuilder  spannableStringBuilder = new SpannableStringBuilder();
        int length=0;
        for (int i=0;i<s.length;++i){
             spannableStringBuilder.append(s[i]);
              spannableStringBuilder.setSpan(new ForegroundColorSpan(colors[i]),length,spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
              length= spannableStringBuilder.length();
        }
        return spannableStringBuilder;
    }

    /**
     * 设置不同大小的字体,这里使用px
     * @param size
     * @param s
     * @return
     */
    public static SpannableStringBuilder setSizePan( int[]size,String[] s){
        SpannableStringBuilder  spannableStringBuilder = new SpannableStringBuilder();
        int length=0;
        for (int i=0;i<s.length;++i){
            spannableStringBuilder.append(s[i]);
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(size[i]),length,spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            length= spannableStringBuilder.length();
        }
        return spannableStringBuilder;
    }

    /**
     * 为TextView设置四周的图片
     * @param textView
     * @param leftPaddingResouces
     * @param topPaddingResouces
     * @param rightPaddingResouces
     * @param bottomPaddingResouces
     */
    public static void setCompoundDrawables(TextView textView, int leftPaddingResouces, int topPaddingResouces, int rightPaddingResouces, int bottomPaddingResouces){
        Context context=textView.getContext();
        textView.setCompoundDrawables(setDrawable(context,leftPaddingResouces),setDrawable(context,topPaddingResouces),setDrawable(context,rightPaddingResouces),setDrawable(context,bottomPaddingResouces));
    }
    private static  Drawable setDrawable(Context context,int drawableId){
        if(drawableId==0){
            return null;
        }
        Drawable  drawable = context.getResources().getDrawable(drawableId);
        drawable.setBounds(0,0,drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    /**
     * 计算字体的高度
     *
     * @param
     * @return
     */
  public static float calculationDigitalHeight(Paint paint) {
        return ((-paint.ascent()) + paint.descent());
    }

    /**
     * 计算字体的长度
     *
     * @param
     * @return
     */
    public static float calculationDigitalWith(Paint paint, String text) {
        return paint.measureText(text);
    }
}
