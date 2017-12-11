package com.zhongke.weiduo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ${xingen} on 2017/11/8.
 * <p>
 * 时间工具
 */

public class TimeUtils {

    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 计算两个时间相差多少
     * <p>
     * long nd = 1000*24*60*60;//一天的毫秒数
     * long nh = 1000*60*60;//一小时的毫秒数
     * long nm = 1000*60;//一分钟的毫秒数
     * long ns = 1000;//一秒钟的毫秒数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String reduceTime(String startTime, String endTime) {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat(TIME_FORMAT);
        StringBuilder str = new StringBuilder();
        try {
            long nh = 1000 * 60 * 60;//一小时的毫秒数
            long nm = 1000 * 60;//一分钟的毫秒数
            //相差的秒数
            long diff = simpleFormatter.parse(endTime).getTime() - simpleFormatter.parse(startTime).getTime();
            //计算差多少小时
            long hour = diff / nh;
            //计算差多少分钟
            long min = diff / nm;
            if (hour > 0) {
                str.append(hour);
                str.append("h");
            } else {
                str.append(min);
                str.append("分钟");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    /**
     * 比较两个时间
     * <p>
     * 返回true, date1时间在data2之前
     *
     * @return
     */
    public static boolean compareTime(String date1, String date2) {
        DateFormat df = new SimpleDateFormat(TIME_FORMAT);
        boolean result = false;
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            result = dt1.getTime() > dt2.getTime() ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据指定格式，获取时间
     *
     * @param format
     * @return
     */
    public static String getTodayTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        String date = null;
        try {
            date = df.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
     *
     * @param time
     * @return
     */
    public static long getLongTime(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        Date date;
//        String times = null;
        try {
            date = sdr.parse(time);
            return date.getTime();
//            String stf = String.valueOf(l);
//            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
