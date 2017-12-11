package com.zhongke.weiduo.mvp.ui.widget.wheelview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.util.DisplayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hyx on 2017/11/1.
 *
 * 日程添加周期
 */

public class ScheduleCycleView extends LinearLayout {

    private ArrayList<String> cycleList;
    private WheelView scheduleCyleView;
    private String cycleString;

    public ScheduleCycleView(Context context) {
        super(context);
        init(context);
    }

    public ScheduleCycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        cycleList = new ArrayList<>();
        cycleList.add("每天一次");
        cycleList.add("三天一次");
        cycleList.add("每周一次");
        cycleList.add("每两周一次");
        cycleList.add("每月一次");

        scheduleCyleView = new WheelView(context);
        LayoutParams layoutParams = new LayoutParams(200,LayoutParams.WRAP_CONTENT);
        scheduleCyleView.setLayoutParams(layoutParams);
        scheduleCyleView.setAdapter(new StringWheelAdapter2(cycleList,cycleList.size()));
        scheduleCyleView.addChangingListener(onCycleChangedListener);
        scheduleCyleView.setVisibleItems(5);
        scheduleCyleView.setCyclic(true);
        addView(scheduleCyleView);
    }

    private OnWheelChangedListener onCycleChangedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            cycleString = cycleList.get(newValue);
//            LogUtil.e("onCycleChangedListener"+cycleString);
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public String getCycleString() {
        //LogUtil.e("getCycleString"+cycleString);
        if ("".equals(cycleString) || cycleString == null) {
            return cycleList.get(0);
        }
        return cycleString;
    }
}
