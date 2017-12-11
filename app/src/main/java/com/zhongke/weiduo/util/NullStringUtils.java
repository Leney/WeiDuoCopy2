package com.zhongke.weiduo.util;

/**
 * Created by ${tanlei} on 2017/6/21.
 */

public class NullStringUtils {
    public static boolean isNullStringUtils(String str){
        return "".equals(str)|| null == str|| "null".equals(str);
    }
}
