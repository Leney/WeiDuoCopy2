package com.zhongke.weiduo.app.utils;


import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Karma on 2017/6/26.
 * 类描述：
 */

public class IOUtils {
    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                LogUtil.e(e.getMessage());
            }
        }
        return true;
    }
}
