package com.zhongke.weiduo.util;

/**
 * Created by ${xingen} on 2017/7/12.
 *
 * Java中常用的计算：
 *
 * 1. 正弦
 * 2. 余弦
 * 3. 角度与弧度转换
 * 4.. 开根号
 */

public class MathUtils {

    /**
     *  将角度换成弧度
     *
     * 计算公式：弧度＝度×π/180
     *
     * π的值：π=Math.PI
     *
     * @param degree
     * @return
     */
     public static double degreeToRad(double degree) {
        return degree * Math.PI / 180;
    }
    /**
     * 计算出弧度对应的sin值
     * <p>
     * 也可以采用cos,tan函数进行转换。
     *
     * @param radians
     * @return
     */
    public static double sin(double radians) {
        //弧度对应的sin值
        double result = Math.sin(radians);
        return result;
    }

    /**
     * 计算出弧度对应的cos值
     *
     * @param radians
     * @return
     */
   public static double cos(double radians) {
        //弧度对应的cos值
        double result = Math.cos(radians);
        return result;
    }
    /**
     * 计算出sin值对应的弧度
     *
     *
     * @param sin
     * @return
     */
    public static double asin(double sin) {
        double result = Math.asin(sin);
        return result;
    }
    /**
     * 计算出弧度对应的角度
     * @param radians
     * @return
     */
    public static double toDegrees(double radians){
        double result=Math.toDegrees(radians);
        return  result;
    }
}
