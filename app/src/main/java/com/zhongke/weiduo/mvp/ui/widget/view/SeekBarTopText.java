package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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

public class SeekBarTopText extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
    private final View v;
    //一个刻度代表的宽度
    private float fDensity;
    private SeekBar sb;
    private TextView tv;
    private int numbers;
    private int tvWidth;
    //进度条的宽度
    private float widthPixels;

    private OnStopTrackingTouchListener mListener;

    /** 进度值的显示LayoutParams对象*/
    private LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    public SeekBarTopText setListener(OnStopTrackingTouchListener listener) {
        mListener = listener;
        return this;
    }

    public SeekBarTopText(@NonNull Context context) {
        this(context, null);
    }

    public SeekBarTopText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        v = View.inflate(context, R.layout.seekbar_top_textview, null);
        sb = (SeekBar) v.findViewById(R.id.sb);
        tv = (TextView) v.findViewById(R.id.tv);
        sb.setOnSeekBarChangeListener(this);
        initSeekBarProgress();
        addView(v);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        tvWidth = tv.getWidth() / 2;
//        widthPixels = sb.getWidth() - tvWidth;
//        fDensity = widthPixels / 100;
//        initSeekBarProgress();
    }

    //TextView跟随SeekBar移动
    private void initSeekBarProgress() {
        Log.i("llj","initSeekBarProgress()");
        sb.setProgress(numbers);
//        LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsStrength = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsStrength.leftMargin = (int) (numbers * fDensity + tvWidth);
        tv.setLayoutParams(paramsStrength);
        tv.setText("音量调节" + numbers);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.i("llj","onProgressChanged！！@@！！！");
        if(tvWidth <= 0){
            tvWidth = tv.getWidth() / 2;
            widthPixels = sb.getWidth() - tvWidth;
            fDensity = widthPixels / 100;
        }
        numbers = progress;
//        LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
        if (100 == progress) {
            paramsStrength.leftMargin = (int) (progress * fDensity) + tvWidth;
        } else {
            paramsStrength.leftMargin = (int) (progress * fDensity) + tvWidth;
        }
        tv.setLayoutParams(paramsStrength);
        tv.setText("音量调节" + numbers);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mListener == null) return;
        mListener.onStopTouch(seekBar.getProgress());
    }

    public interface OnStopTrackingTouchListener {
        void onStopTouch(int progress);
    }
}
