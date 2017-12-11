package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.NewExpertCourseBean;
import com.zhongke.weiduo.mvp.model.entity.NewExpertDetailBean;

/**
 * Created by ${tanlei} on 2017/9/6.
 */

public interface ExpertDetailsContract extends BaseView {
    /**
     * 展示专家详细信息
     *
     * @param newExpertDetailBean
     */
    void getExpertDetail(NewExpertDetailBean newExpertDetailBean);

    /**
     * 展示获取数据失败
     */
    void requestFailure();

    /**
     * 收藏专家成功
     */
    void collectionExpert();

    /**
     * 取消收藏专家成功
     */
    void cancelCollectionExpert();

    void showExpertCourse(NewExpertCourseBean newExpertCourseBean);
}
