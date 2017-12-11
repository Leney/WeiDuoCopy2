package com.zhongke.weiduo.library.retrofit;

/**
 * 返回的数据结构可能是实体类，数组，等等。
 * 因此，定义泛型
 * @param <T>
 */
public class HttpResult<T> {
    /**
     * 返回结果状态 1=成功， 0=失败
     2登录token信息过期，需重新登录

     */

    private int code;//返回码

    /**
     * 当为失败状态时所返回的失败信息，如果成功则返回空字符串
     */

    private String msg;
    /**json对应的类型，list或者实体类
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        this.data = result;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + data +
                '}';
    }
}
