package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;

/**
 *
 * Created by llj on 2017/9/14.
 */

public interface CourseCatalogueContract extends BaseView {
//    void getCatalogueListSuccess(List<CatalogueBean> list);
//    void getCatalogueFailed(int errorCode, String msg);

    //获取课程目录
    void getCourseCatalogSuccess(CourseDetailResult courseDetailResult);
    //获取课程失败
    void getCourseCatalogFailed(CommonException e);
}
