package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.CurriculumActivityBean;
import com.zhongke.weiduo.mvp.model.entity.CurriculumTemplateBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseActionResult;

import java.util.List;

/**
 *
 * Created by llj on 2017/9/14.
 */

public interface CourseMovementContract extends BaseView {

    void getActDataInfoSuccess(List<CurriculumActivityBean> actList, List<CurriculumTemplateBean> templateList);
    void getActDataInfoFailed(int errorCode,String msg);

    //获取活动的课程列表
    void getCourseActionSuccess(CourseActionResult bean);

    void getCourseActionFailed(CommonException e);

    //收藏活动成功
    void collectionActionSuccess();

    void collectionActionFailed();

    //取消收藏成功
    void cancelCollectionSuccess();

    void cancelCollectionFailed(CommonException e);

    //加入课程的活动成功
    void joinCourseActionSuccess();

    void joinCourseActionFailed(CommonException e);

}
