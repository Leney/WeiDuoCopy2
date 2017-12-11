package com.zhongke.weiduo.mvp.ui.widget.wheelview;

import java.util.Calendar;
import java.util.Objects;

/**
 * Created by hyx on 2017/9/27.
 */

public class DateObject extends Object {

    private int year;
    private int month;
    private int day;
    private int number;
    private String listItem;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getListItem() {
        return listItem;
    }

    public void setListItem(String listItem) {
        this.listItem = listItem;
    }

    /**
     * 设置日期
     */
    public DateObject(int year2,int month2,int day2) {
        super();
        this.year = year2;
        int maxDayofMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

        if (day > maxDayofMonth) {
            this.month = month2 + 1;
            this.day = day2 % maxDayofMonth;
        } else {
            this.month = month2;
            this.day = day2;
        }
    }

    /**
     * 设置单项数据
     * @param number2
     */
    public DateObject(int number2,boolean isYear,boolean isMonth,boolean isDay) {
        super();
        if(isYear) {
            this.year = number2;
        } else if (isMonth) {
            this.month = number2;
        } else if (isDay) {
            this.day = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        }
    }

    @Override
    public String toString() {
        return "DateObject{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", listItem='" + listItem + '\'' +
                '}';
    }
}
