package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.SizeUtils;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by ${tanlei} on 2017/9/21.
 * 情景主题控件，上边是imageview下面是textview
 */

public class SceneView extends LinearLayout {
    private final ImageView imageView;
    private TextView tv;
    private Context context;

    public SceneView(Context context) {
        this(context, null);
    }

    public SceneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOrientation(VERTICAL);
        imageView = new ImageView(context);
        LayoutParams params = new AutoLinearLayout.LayoutParams(SizeUtils.dp2px(context, 36), SizeUtils.dp2px(context, 36));
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.addView(imageView);

        tv = new TextView(context);
        LayoutParams params2 = new AutoLinearLayout.LayoutParams(SizeUtils.dp2px(context, 36), LayoutParams.WRAP_CONTENT);
        params2.setMargins(0, SizeUtils.dp2px(context, 3), 0, 0);
        tv.setLayoutParams(params2);
        tv.setTextSize(12);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(getResources().getColor(R.color.color4));
        this.addView(tv);
    }

    /**
     * 给view设置数据
     *
     * @param text
     * @param url
     */
    public void setData(String text, String url) {
        tv.setText(text);
        Glide.with(context).load(url).into(imageView);
    }

    /**
     * 给view设置数据
     *
     * @param text
     * @param resId
     */
    public void setData(String text, int resId) {
        tv.setText(text);
        imageView.setImageResource(resId);
    }
}
