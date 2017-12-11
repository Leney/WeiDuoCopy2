package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;

/**
 * Created by ${tanlei} on 2017/6/26.
 */

public class PrizeCustomLayout extends LinearLayout {
    private MyImageView myImageView;
    private TextView tvPrizeName,tvPrizePictor;
    public PrizeCustomLayout(Context context) {
        super(context);
        init(context);
    }

    public PrizeCustomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PrizeCustomLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.prize_bean_layout,this);
        myImageView = (MyImageView) view.findViewById(R.id.iv_prize_pictor);
        tvPrizeName = (TextView) view.findViewById(R.id.tv_prize_name);
        tvPrizePictor = (TextView) view.findViewById(R.id.tv_prize_pictor);
    }
    public void setPrizeName(String name){
        tvPrizeName.setText(name);
    }
    public void setPrizePictor(String pictor){
        tvPrizePictor.setText(pictor);
    }
    public  void setPrizeBitmap(Bitmap b){
        myImageView.setImageBitmap(b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
