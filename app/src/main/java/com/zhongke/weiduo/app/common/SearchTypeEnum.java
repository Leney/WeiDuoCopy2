package com.zhongke.weiduo.app.common;

/**
 * Created by  karma on 2017/06/19.
 * 类描述：查询status的枚举
 */
public enum SearchTypeEnum {

    OLD("0", "加载更多"),
    NEW("1", "下拉刷新"),
    NEW_OLD("2", "加载更多和下拉刷新");

    private String code;
    private String message;

    private SearchTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
