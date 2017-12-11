package com.zhongke.weiduo.util;

import java.util.HashMap;

/**
 * Created by dgg1 on 2017/11/9.
 * 拼接请求参数
 */

public class RequestParameterUtils {
    /**
     * @param array 请求的参数按照键值键值的形式丢进去（key,value,key,value,key,value...）
     * @return
     */
    public static HashMap setRequestPar(String... array) {
        HashMap map = new HashMap();
        for (int i = 0; i < array.length; i += 2) {
            map.put(array[i], array[i + 1]);
        }
        return map;
    }
}
