package com.zhongke.weiduo.app.utils;

/**
 * * Created by Karma on 2017/6/8.
 * 类描述：网络请求错误码
 */
public class ApiException extends RuntimeException {

    public static final int KEY_NULL = 4001;
    public static final int SERVER_ERROR = 4000;
    public static final int API_ERROR = 401;
    public static final int NGINX_ERROR = 410;
    public static final int NGINX_CONNNECT_FAIL = 502;

    public ApiException(int resultCode, String errorMsg) {
        this(getApiExceptionMessage(resultCode, errorMsg));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code, String errMsg) {
        String message = "";
        switch (code) {
            case KEY_NULL:
                message = "字段值为空或值错误";
                break;
            case SERVER_ERROR:
                message = "服务器错误";
                break;
            case API_ERROR:
                message = "应用服务API反馈的错误";
                break;
            case NGINX_ERROR:
                message = "Nginx反馈的错误";
                break;
            case NGINX_CONNNECT_FAIL:
                message = "Nginx连接失败反馈的错误";
                break;
            default:
                message = "未知错误";

        }

        LogUtil.e("tag", "----------> errMsg " + errMsg);
        return errMsg;
    }
}

