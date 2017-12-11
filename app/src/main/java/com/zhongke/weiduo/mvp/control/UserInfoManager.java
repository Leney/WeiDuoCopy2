package com.zhongke.weiduo.mvp.control;

import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.library.retrofit.RetrofitClient;
import com.zhongke.weiduo.mvp.model.entity.UserAcountSizeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息相关数据管理类
 * Created by llj on 2017/11/20.
 */

public class UserInfoManager {
    /**
     * 愿望类型
     */
    public static final int TYPE_WISH = 0;
    /**
     * 目标类型
     */
    public static final int TYPE_TARGET = 1;
    /**
     * 规划类型
     */
    public static final int TYPE_SCHEME = 2;
    /**
     * 计划类型
     */
    public static final int TYPE_PLAN = 3;
    /**
     * 日程类型
     */
    public static final int TYPE_ACTIVE = 4;
    /**
     * 愿望、目标、规划、计划、活动日程数量
     */
    private int wishNum, targetNum, schemeNum, planNum, activeNum;

    private static UserInfoManager instance;

    private List<OnUserInfoChangeListener> listeners;

    private UserInfoManager() {
        listeners = new ArrayList<>();
    }

    public static UserInfoManager getInstance() {
        if (instance == null) {
            synchronized (UserInfoManager.class) {
                if (instance == null) {
                    instance = new UserInfoManager();
                }
            }
        }
        return instance;
    }

    /**
     * 从服务器获取用户信息
     */
    public void getUserInfoFromNetwork() {
//        String token = ZkApplication.getInstance().getToken();
//        if (token.isEmpty()) {
//            return;
//        }
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        retrofitClient.getUserAcountSize(new ResponseResultListener<UserAcountSizeBean>() {
            @Override
            public void success(UserAcountSizeBean userAcountSizeBean) {
                UserAcountSizeBean.CountBean countBean = userAcountSizeBean.getCount();
                if (countBean != null) {
                    // 获取到用户信息相关数据
                    wishNum = countBean.getWishCount();
                    targetNum = countBean.getTargetCount();
                    schemeNum = countBean.getBPlanCount();
                    planNum = countBean.getSPlanCount();
                    activeNum = countBean.getScheduleCount();
                    callBackListeners();
                }
            }

            @Override
            public void failure(CommonException e) {
                UIUtils.showToast(e.getErrorMsg());
            }
        });
    }

    private void callBackListeners() {
        int length = listeners.size();
        for (int i = 0; i < length; i++) {
            listeners.get(i).onUserInfoChange();
        }
    }

    /**
     * 注册监听用户信息改变的监听
     *
     * @param listener
     */
    public void registerOnUserInfoChangeListener(OnUserInfoChangeListener listener) {
        if (listener == null) return;
        listeners.add(listener);
    }

    /**
     * 取消注册监听信息改变的监听
     *
     * @param listener
     */
    public void unregisterOnUserInfoChangeListener(OnUserInfoChangeListener listener) {
        if (listener == null) return;
        listeners.remove(listener);
    }

    public int getWishNum() {
        return wishNum;
    }

    public void setWishNum(int wishNum) {
        this.wishNum = wishNum;
        callBackListeners();
    }

    public int getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(int targetNum) {
        this.targetNum = targetNum;
        callBackListeners();
    }

    public int getSchemeNum() {
        return schemeNum;
    }

    public void setSchemeNum(int schemeNum) {
        this.schemeNum = schemeNum;
        callBackListeners();
    }

    public int getPlanNum() {
        return planNum;
    }

    public void setPlanNum(int planNum) {
        this.planNum = planNum;
        callBackListeners();
    }

    public int getActiveNum() {
        return activeNum;
    }

    public void setActiveNum(int activeNum) {
        this.activeNum = activeNum;
        callBackListeners();
    }

    public void clear(){
        instance = null;
    }

    /**
     * 增加数量
     *
     * @param num  需要增加的数量
     * @param type 需要增加数量的类型(给谁增加)
     */
    public void plusNum(int num, int type) {
        switch (type) {
            case TYPE_WISH:
                // 愿望
                wishNum += num;
                break;
            case TYPE_TARGET:
                // 目标
                targetNum += num;
                break;
            case TYPE_SCHEME:
                // 规划
                schemeNum += num;
                break;
            case TYPE_PLAN:
                // 计划
                planNum += num;
                break;
            case TYPE_ACTIVE:
                // 日程
                activeNum += num;
                break;
        }
        callBackListeners();
    }

    /**
     * 增加数量
     *
     * @param num  需要增加的数量
     * @param type 需要增加数量的类型(给谁增加)
     */
    public void minusNum(int num, int type) {
        switch (type) {
            case TYPE_WISH:
                // 愿望
                wishNum -= num;
                if(wishNum < 0) wishNum = 0;
                break;
            case TYPE_TARGET:
                // 目标
                targetNum -= num;
                if(targetNum < 0) targetNum = 0;
                break;
            case TYPE_SCHEME:
                // 规划
                schemeNum -= num;
                if(schemeNum < 0) schemeNum = 0;
                break;
            case TYPE_PLAN:
                // 计划
                planNum -= num;
                if(planNum < 0) planNum = 0;
                break;
            case TYPE_ACTIVE:
                // 日程
                activeNum -= num;
                if(activeNum < 0) activeNum = 0;
                break;
        }
        callBackListeners();
    }

    /**
     * 用户相关信息发生改变的监听器
     */
    public interface OnUserInfoChangeListener {
        void onUserInfoChange();
    }
}
