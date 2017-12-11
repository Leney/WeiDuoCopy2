package com.zhongke.weiduo.mvp.ui.widget.wheelview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhongke.weiduo.app.utils.LogUtil;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by hyx on 2017/9/27.
 */

public class CustomDateView extends LinearLayout {

    private Calendar calendar = Calendar.getInstance();
    private WheelView yearView,monthView,dayView;
    /*private ArrayList<DateObject> yearList,monthList,dayList;*/
    private ArrayList<String> yearList,monthList,dayList;
    private final int MARGIN_RIGHT = 15;
    private OnChangeListener onChangeListener;
    private int year;
    private int month;
    private int day;
    private StringWheelAdapter2 dayAdapter;
    private int maxDayOfMonth;

    public CustomDateView(Context context) {
        super(context);
        init(context);
    }

    public CustomDateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH )+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        yearList = new ArrayList<>();
        monthList = new ArrayList<>();
        dayList = new ArrayList<>();

        for(int i = year;i>1867;i--) {
            yearList.add(String.valueOf(i));
        }

        for (int i=1;i<=12;i++) {
            monthList.add(String.valueOf(i));
        }
        maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i<= maxDayOfMonth; i++) {
            dayList.add(String.valueOf(i));
        }

        yearView = new WheelView(context);
        LayoutParams lparams_year = new LayoutParams(200,LayoutParams.WRAP_CONTENT);
        lparams_year.setMargins(0,0,MARGIN_RIGHT,0);
        yearView.setLayoutParams(lparams_year);
        yearView.setAdapter(new StringWheelAdapter2(yearList,yearList.size()));
        yearView.setVisibleItems(5);
        yearView.setCyclic(true);
        yearView.addChangingListener(onYearsChangerListener);
        addView(yearView);

        monthView = new WheelView(context);
        LayoutParams lparams_month = new LayoutParams(120,LayoutParams.WRAP_CONTENT);
        lparams_month.setMargins(0,0,MARGIN_RIGHT,0);
        monthView.setLayoutParams(lparams_month);
        monthView.setAdapter(new StringWheelAdapter2(monthList,monthList.size()));
        monthView.setVisibleItems(5);
        monthView.setCurrentItem(month - 1);
        monthView.setCyclic(true);
        monthView.addChangingListener(onMonthsChangerListener);
        addView(monthView);

        dayView = new WheelView(context);
        LayoutParams lparmas_day = new LayoutParams(120, ViewGroup.LayoutParams.WRAP_CONTENT);
        lparmas_day.setMargins(0,0,MARGIN_RIGHT,0);
        dayView.setLayoutParams(lparmas_day);
        dayAdapter = new StringWheelAdapter2(dayList,dayList.size());
        dayView.setAdapter(dayAdapter);
        dayView.setVisibleItems(5);
        dayView.setCurrentItem(day - 1);
        dayView.setCyclic(true);
        dayView.addChangingListener(onDayChangerListener);
        addView(dayView);

    }

    //listeners
    private OnWheelChangedListener onYearsChangerListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView years, int oldValue, int newValue) {
            year = Integer.valueOf(yearList.get(newValue));
            calendar.set(Calendar.YEAR,year);
        }
    };

    private OnWheelChangedListener onMonthsChangerListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView years, int oldValue, int newValue) {    //newValue 是选中的item的索引值
            month = Integer.valueOf(monthList.get(newValue));
            calendar.set(Calendar.MONTH,month-1);
            maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            dayList.clear();
            for(int i = 1; i<= maxDayOfMonth; i++) {
                dayList.add(String.valueOf(i));
            }
            dayAdapter.setList(dayList);
            dayView.setAdapter(dayAdapter);
        }
    };

    private OnWheelChangedListener onDayChangerListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            day = Integer.valueOf(dayList.get(newValue));
            calendar.set(Calendar.DAY_OF_MONTH,day);
        }
    };

    public interface OnChangeListener {
        void onChange(int year,int month,int day);
    }

    public void setOnChangerListener(OnChangeListener onChangerListener) {
        this.onChangeListener = onChangerListener;
    }

    public void change() {
        if (onChangeListener != null) {

        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setCurrentItem(String date) {
        String yearStr = date.substring(0,4);
        int yearInt = Integer.valueOf(yearStr);
        int offset1 = year-yearInt;
        yearView.setCurrentItem(offset1);
        String monthStr = date.substring(5,7);
        int month = Integer.valueOf(monthStr);
        monthView.setCurrentItem(month -1);
        String dayStr = date.substring(8,10);
        LogUtil.e("dayStr--"+dayStr);
        int day = Integer.valueOf(dayStr);
        dayView.setCurrentItem(day - 1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
