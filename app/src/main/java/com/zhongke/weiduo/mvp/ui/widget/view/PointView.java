package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;

/**
 * 指示控件(我的任务子item控件)
 * Created by llj on 2017/9/20.
 */

public class PointView extends LinearLayout {

    private String text;
    private int src;

    private ImageView icon;
    private TextView name,num;

    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PointView);
        text = a.getString(R.styleable.PointView_text);
        src = a.getResourceId(R.styleable.PointView_src,-1);
        a.recycle();

        init(context);
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){
        View view = View.inflate(context,R.layout.item_point_lay,null);
        icon = (ImageView) view.findViewById(R.id.item_point_icon);
        name = (TextView) view.findViewById(R.id.item_point_name);
        num = (TextView) view.findViewById(R.id.item_point_num);

        if(src != -1) icon.setImageResource(src);
        name.setText(text);

        addView(view);
    }

    /**
     * 设置圆圈中的提示数字
     * @param num
     */
    public void setNum(int num){
        this.num.setVisibility(num <= 0 ? GONE : VISIBLE);
        this.num.setText(num > 99 ? "...": String.valueOf(num));
    }
}
