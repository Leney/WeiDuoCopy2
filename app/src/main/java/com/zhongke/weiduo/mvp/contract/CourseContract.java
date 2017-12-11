package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.CatalogueVideoBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;

/**
 * Created by ${tanlei} on 2017/6/28.
 */

public interface CourseContract extends BaseView{

    //获取课程详情
    void getCourseDetailSuccess(CourseDetailResult courseDetailResult);

    void getCourseDetailFailed();

    //获取目录视频的地址
    void getVideoUrlSuccess(CatalogueVideoBean bean);

    void getVideoUrlFailed(CommonException e);
}
