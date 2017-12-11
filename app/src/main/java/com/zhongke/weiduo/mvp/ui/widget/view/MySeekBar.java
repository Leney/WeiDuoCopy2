package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zhongke.weiduo.R;

/**
 * Created by ${tanlei} on 2017/9/20.
 * 带TextView的SeekBar
 */

public class MySeekBar extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
    private final View v;
    private String topText = "水平调节";
    //一个刻度代表的宽度
    private float fDensity;
    private SeekBar sb;
    private TextView tv;
    private int numbers;
    private int tvWidth;
    //进度条的宽度
    private float widthPixels;
    //起始值
    private float start = -90;

    public MySeekBar(@NonNull Context context) {
        this(context, null);
    }

    public MySeekBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySeekBar);
        topText = typedArray.getString(R.styleable.MySeekBar_top_text);
        v = View.inflate(context, R.layout.my_seekbar, this);
        sb = (SeekBar) v.findViewById(R.id.sb);
        tv = (TextView) v.findViewById(R.id.tv);
        sb.setOnSeekBarChangeListener(this);
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        tvWidth = tv.getWidth() / 2;
        widthPixels = sb.getWidth() - tvWidth;
        fDensity = widthPixels / 180;
        initSeekBarProgress();
    }

    //TextView跟随SeekBar移动
    private void initSeekBarProgress() {
        sb.setProgress(numbers);
        LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsStrength.leftMargin = (int) (numbers * fDensity + tvWidth);
        tv.setLayoutParams(paramsStrength);
        tv.setText(topText + (int) (numbers + start));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        numbers = progress;
        LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsStrength.leftMargin = (int) (progress * fDensity) + tvWidth;
        tv.setLayoutParams(paramsStrength);
        tv.setText(topText + (int) (numbers + start));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
