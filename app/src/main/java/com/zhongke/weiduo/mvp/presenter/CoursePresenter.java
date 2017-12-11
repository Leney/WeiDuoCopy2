package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CourseContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.CatalogueVideoBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/6/28.
 */

public class CoursePresenter extends BasePresenter {
    private static final String TAG = "CoursePresenter";
    private CourseContract courseContract;
    private DataManager dataManager;

    public CoursePresenter(CourseContract courseContract, DataManager dataManager) {
        this.courseContract = courseContract;
        this.dataManager = dataManager;
    }

    public void getCourseDetail(String courseId) {
        HashMap<String,String> hashMap = new HashMap<>();
//        hashMap.put("token",token);
        hashMap.put("courseId",courseId);
        Subscription subscription = retrofitClient.getCourseDetail(hashMap, new ResponseResultListener<CourseDetailResult>() {
            @Override
            public void success(CourseDetailResult courseDetailResult) {

                LogUtil.e(TAG,"success-----");
                courseContract.getCourseDetailSuccess(courseDetailResult);
            }

            @Override
            public void failure(CommonException e) {

                LogUtil.e(TAG,"e.getcode"+e.getCode()+"e.msg"+e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }
    //获取视频地址
    public void getVideoUrl(String resId,String type) {
        Map<String,String> map = new HashMap<>();
        map.put("resId",resId);
        map.put("type",type);
        Subscription subscription = retrofitClient.getCatalogueVideoUrl(map, new ResponseResultListener<CatalogueVideoBean>() {
            @Override
            public void success(CatalogueVideoBean bean) {
                courseContract.getVideoUrlSuccess(bean);
            }

            @Override
            public void failure(CommonException e) {
                courseContract.getVideoUrlFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }
}
