package com.zhongke.weiduo.library.retrofit;

import com.zhongke.weiduo.mvp.model.requestEntity.LoginEntity;
import com.zhongke.weiduo.mvp.model.requestEntity.ModifyUserBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${xingen} on 2017/11/7.
 * <p>
 * 将实体类转成对应的Map
 */

public class BuilderMap {

    public static Map<String, String> builderMap(LoginEntity entity) {
        Map<String, String> map = new HashMap<>();
        map.put("loginName", entity.getLoginName());
        map.put("userPass", entity.getUserPass());
        map.put("appId", entity.getAppId());
        return map;
    }

    public static Map<String, Object> buildMapEducationSystemDefault(int currentPage, int pageSize) {
        Map<String, Object> map = new HashMap<>();
//        map.put("token", token);
        map.put("pageIndex", currentPage);
        map.put("pageSize", pageSize);
        return map;
    }
    public static Map<String, Object> buildMap(int currentPage, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageIndex", currentPage);
        map.put("pageSize", pageSize);
        return map;
    }
    public static Map<String, Object> buildMapEducationSystemSearch(String bookName, int currentPage, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("bookName", bookName);
        map.put("pageIndex", currentPage);
        map.put("pageSize", pageSize);
        return map;
    }
    public static Map<String, Object> buildMap(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("actionId", id);
        return map;
    }


    public static Map<String, String> builderSchoolBannerMap(String bannerType, String pageIndex) {
        Map<String, String> map = new HashMap<>();
        map.put("bannerType", bannerType);
        map.put("pageIndex", pageIndex);
        return map;
    }

    /**
     * 体系详情
     *
     * @param id
     * @return
     */
    public static Map<String, Object> buildMapWithSystemDetail(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("bookId", id);
        return map;
    }

    /**
     * 注册
     *
     * @param phone
     * @param pwd
     * @param msgCode
     * @return
     */
    public static Map<String, Object> buildMapRegistrer(String phone, String pwd, String msgCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("userPhone", phone);
        map.put("userPass", pwd);
        map.put("phoneCode", msgCode);
        return map;
    }

    /**
     * 短信验证码
     *
     * @param phone
     * @return
     */
    public static Map<String, Object> buildMapPhoneCode(String phone) {
        Map<String, Object> map = new HashMap<>();
        map.put("userPhone", phone);
        return map;
    }

    /**
     * 修改革新信息
     *
     * @param bean
     * @return
     */
    public static Map<String, Object> buildMapPersona(ModifyUserBean bean) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", bean.getToken());
        if (bean.getNickName() != null)
            map.put("nickName", bean.getNickName());
        if (bean.getHeadImageUrl() != null)
            map.put("headImageUrl", bean.getHeadImageUrl());
        if (bean.getUserPhone() != null)
            map.put("userPhone", bean.getUserPhone());
        if (bean.getSex() != null)
            map.put("sex", bean.getSex());
        if (bean.getBirthday() != null)
            map.put("birthday", bean.getBirthday());
        if (bean.getFullName() != null)
            map.put("fullName", bean.getFullName());
        if (bean.getFamilyAddress() != null)
            map.put("familyAddress", bean.getFamilyAddress());
        return map;
    }
}
