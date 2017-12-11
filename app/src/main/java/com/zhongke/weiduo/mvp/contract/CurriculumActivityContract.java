package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.CurriculumBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.ActionDetailResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseBannerResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseListResult;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/27.
 */

public interface CurriculumActivityContract extends BaseView{

    /** 获取课程banner url*/
    void getBannerSuccess(List<CourseBannerResult.RecordsBean> recordsBeanList);


    /** 获取课程列表 */
    void getCourseTableSuccess(CourseListResult courseListResult,int refreshStatus);

    /** 获取课程列表失败 */
    void getCourseTableFailed(CommonException e);


}
