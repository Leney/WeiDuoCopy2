package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhongke.weiduo.R;

/**
 * Created by ${tanlei} on 2017/9/21.
 * 开关控件，根据不同的状态显示不同的图片
 */

public class SwitchView extends RelativeLayout {
    private final ImageView imageViewOn;
    private final ImageView imageViewOff;

    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageViewOn = new ImageView(context);
        imageViewOn.setImageResource(R.drawable.hardware_on);
        imageViewOn.setLayoutParams(params);
        this.addView(imageViewOn);

        LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageViewOff = new ImageView(context);
        imageViewOff.setImageResource(R.drawable.hardware_off);
        imageViewOff.setLayoutParams(params2);
        this.addView(imageViewOff);
    }

    public void setVisibil(boolean bool) {
        if (bool) {
            imageViewOn.setVisibility(View.VISIBLE);
            imageViewOff.setVisibility(View.INVISIBLE);
        } else {
            imageViewOn.setVisibility(View.INVISIBLE);
            imageViewOff.setVisibility(View.VISIBLE);
        }
    }
}
