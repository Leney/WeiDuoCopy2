package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;

/**
 * Created by ${tanlei} on 2017/6/26.
 */

public interface AddTeachContentContract extends BaseView {
    /**
     * 获取用户输入的播放设备
     *
     * @return 返回用户输入的设备名称
     */
    String getAddPlayEquipment();

    /**
     * 获取用户输入的播放时间
     *
     * @return 返回用户输入的播放时间
     */
    String getAddPlayDate();

    /**
     * 获取用户输入的播放角度
     *
     * @return 返回用户输入的播放角度
     */
    String getAddPlayAngle();

    /**
     * 显示ProgressDialog
     */
    void showProgressDialog();

    /**
     * 影藏ProgressDialog
     */
    void hideProgressDialog();
}
