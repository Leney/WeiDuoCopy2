package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.CourseDetailBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;

/**
 *
 * Created by llj on 2017/9/14.
 */

public interface CourseDetailContract extends BaseView {

    //加入课程成功
    void joinCourseSuccess();

    //加入课程失败
    void joinCourseFailed();

    void getCourseDetailSuccess(CourseDetailResult courseDetailResult);

    void getCourseDetailFailed(CommonException e);
}
