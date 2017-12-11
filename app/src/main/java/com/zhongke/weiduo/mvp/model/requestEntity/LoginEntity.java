package com.zhongke.weiduo.mvp.model.requestEntity;

import com.zhongke.weiduo.library.retrofit.HttpConstance;

/**
 * Created by ${xingen} on 2017/11/6.
 *
 * 登入接口的实体类
 */

public class LoginEntity {
    private String loginName;
    private String userPass;
    private String appId;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 静态工厂方式
     * @param loginName
     * @param userPass
     * @return
     */
    public static LoginEntity newInstance( String loginName,String userPass){
        LoginEntity entity=new LoginEntity();
        entity.appId= HttpConstance.APP_ID;
        entity.loginName=loginName;
        entity.userPass=userPass;
       return entity;
    }
}
