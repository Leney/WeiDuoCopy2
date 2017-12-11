package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CourseCatalogueContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;

import java.util.HashMap;

/**
 * Created by llj on 2017/9/14.
 */

public class CourseCataloguePresenter extends BasePresenter {
    private static final String TAG = "CourseCataloguePresenter";
    private CourseCatalogueContract contract;
    private DataManager dataManager;

    public CourseCataloguePresenter(CourseCatalogueContract contract, DataManager dataManager) {
        this.contract = contract;
        this.dataManager = dataManager;
    }

    /**
     * 获取课程目录
     */
    public void getCourseCatalog(String courseId) {
        HashMap<String,String> hashMap = new HashMap<>();
//        hashMap.put("token",token);
        hashMap.put("courseId",courseId);
        retrofitClient.getCourseDetail(hashMap, new ResponseResultListener<CourseDetailResult>() {
            @Override
            public void success(CourseDetailResult courseDetailResult) {

                LogUtil.e("成功");
                contract.getCourseCatalogSuccess(courseDetailResult);
            }

            @Override
            public void failure(CommonException e) {
                contract.getCourseCatalogFailed(e);
                LogUtil.e("失败"+e.getCode()+e.toString());
            }
        });
    }
}
