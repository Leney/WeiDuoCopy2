package com.zhongke.weiduo.util;

import android.graphics.Color;

/**
 * Created by ${xingen} on 2017/6/19.
 */

public class ColorUtils {

    /**
     * rgb转成颜色
     * @param red
     * @param green
     * @param blue
     * @return
     */
    public static int rgbToColor(int red, int green, int blue){
        return Color.rgb(red,green,blue);
    }

    /**
     * 将色值的字符串转成颜色
     * @param colorString
     * @return
     */
    public static int stringToColor(String colorString){
        return Color.parseColor(colorString);
    }
}
