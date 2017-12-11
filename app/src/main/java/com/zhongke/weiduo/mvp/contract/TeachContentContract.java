package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.TeachContentBean;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/20.
 */

public interface TeachContentContract extends BaseView{
    /**
     * 删除了某一行数据
     */
    void deleteData(List<TeachContentBean> teachContentBeanList);
    /**
     * 显示数据
     */
    void showData(List<TeachContentBean> teachContentBeanList);
    /**
     * 显示ProgressDialog
     */
    void showProgressDialog();
    /**
     * 影藏ProgressDialog
     */
    void hideProgressDialog();
}
