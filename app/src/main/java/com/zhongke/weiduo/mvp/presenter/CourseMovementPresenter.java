package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CourseMovementContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseActionEntity;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseActionResult;

import java.util.HashMap;

import rx.Subscription;

/**
 *
 * Created by llj on 2017/9/14.
 */
public class CourseMovementPresenter extends BasePresenter {
    private static final String TAG = "CourseMovementPresenter";
    private CourseMovementContract courseContract;
    private DataManager dataManager;

    public CourseMovementPresenter(CourseMovementContract courseContract, DataManager dataManager) {
        this.courseContract = courseContract;
        this.dataManager = dataManager;
    }

    public void getActDataInfo(CourseActionEntity entity){
//        entity.setToken(token);
//        List<CurriculumActivityBean> curriculumActivityBeanList = new ArrayList<>();
//        curriculumActivityBeanList.add(new CurriculumActivityBean(1, 0, "周末爬山户外运动，培养孩子保护环境的意识，和锻炼身体的习惯，周末爬山户外运动，培养孩子保护环境的意识", "活动地址：深圳市南山区科技园福安大厦5C", "asdas", false, false));
//        curriculumActivityBeanList.add(new CurriculumActivityBean(1, 0, "周末爬山户外运动，培养孩子保护环境的意识，和锻炼身体的习惯", "活动地址：深圳市南山区科技园福安大厦5C", "asdas", false, false));
//        curriculumActivityBeanList.add(new CurriculumActivityBean(1, 0, "周末爬山户外运动，培养孩子保护环境的意识，和锻炼身体的习惯", "活动地址：深圳市南山区科技园福安大厦5C", "asdas", false, false));
//        curriculumActivityBeanList.add(new CurriculumActivityBean(1, 0, "周末爬山户外运动，培养孩子保护环境的意识，和锻炼身体的习惯", "活动地址：深圳市南山区科技园福安大厦5C", "asdas", false, false));
//        curriculumActivityBeanList.add(new CurriculumActivityBean(1, 0, "周末爬山户外运动，培养孩子保护环境的意识，和锻炼身体的习惯", "活动地址：深圳市南山区科技园福安大厦5C", "asdas", false, true));
//        curriculumActivityBeanList.add(new CurriculumActivityBean(1, 0, "周末爬山户外运动，培养孩子保护环境的意识，和锻炼身体的习惯", "活动地址：深圳市南山区科技园福安大厦5C", "asdas", false, false));
//        curriculumActivityBeanList.add(new CurriculumActivityBean(1, 0, "周末爬山户外运动，培养孩子保护环境的意识", "活动地址：深圳市南山区科技园福安大厦5C", "asdas", false, false));
//        curriculumActivityBeanList.add(new CurriculumActivityBean(1, 0, "周末爬山户外运动，培养孩子保护环境的意识，和锻炼身体的习惯", "活动地址：深圳市南山区科技园福安大厦5C", "asdas", true, false));
//
//
//        List<CurriculumTemplateBean> curricelumList = new ArrayList<>();
//        curricelumList.add(new CurriculumTemplateBean("我是模板1", 0));
//        curricelumList.add(new CurriculumTemplateBean("我是模板2", 0));
//        curricelumList.add(new CurriculumTemplateBean("我是模板15", 0));
//        curricelumList.add(new CurriculumTemplateBean("我是模板17", 0));
//        curricelumList.add(new CurriculumTemplateBean("我是模板81", 0));

//        courseContract.getActDataInfoSuccess(curriculumActivityBeanList,curricelumList);
        Subscription courseAction = retrofitClient.getCourseAction(entity, new ResponseResultListener<CourseActionResult>() {
            @Override
            public void success(CourseActionResult courseActionResult) {

                courseContract.getCourseActionSuccess(courseActionResult);
                //LogUtil.e("getCourseAction 成功" + courseActionResult.getRecords());
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e(TAG, "e.getCode()----------" + e.getCode() + "e.getErrMsg---" + e.getErrorMsg());
                courseContract.getCourseActionFailed(e);
            }
        });
        compositeSubscription.add(courseAction);
    }

    /**
     * 收藏活动
     * @param collectType 类型（1.专家2.课程3.机构4.活动）
     * @param collectObjectId
     */

    public void  collection(String collectType,int collectObjectId) {
        HashMap<String,String> map = new HashMap<>();
//        map.put("token",token);
        map.put("collectType",collectType);
        map.put("collectObjectId",collectObjectId+"");
        Subscription subscription = retrofitClient.Collection(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                courseContract.collectionActionSuccess();
            }

            @Override
            public void failure(CommonException e) {
                courseContract.collectionActionFailed();
                LogUtil.e("failure--"+e.getCode()+"----"+e.toString());
            }
        });
        compositeSubscription.add(subscription);
    }

    public void  cancelCollection(String collectType,int collectObjectId) {
        HashMap<String,String> map = new HashMap<>();
//        map.put("token",token);
        map.put("collectType",collectType);
        map.put("collectObjectId",collectObjectId+"");
        Subscription subscription = retrofitClient.cancelCollection(map, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                courseContract.cancelCollectionSuccess();
            }

            @Override
            public void failure(CommonException e) {
                courseContract.cancelCollectionFailed(e);
                LogUtil.e("failure--"+e.getCode()+"----"+e.toString());

            }
        });
        compositeSubscription.add(subscription);
    }


    /**
     * 加入课程活动
     */
    public void joinCourseAction(String actionId) {
        HashMap<String,Object> hashMap = new HashMap<>();
//        hashMap.put("token",token);
        //LogUtil.e("actionIds------"+token);
        hashMap.put("actionIds",actionId);
        //LogUtil.e("actionIds------"+actionId);

        Subscription subscription = retrofitClient.joinCourseAction(hashMap, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                courseContract.joinCourseActionSuccess();
                LogUtil.e("加入课程活动success--");
            }

            @Override
            public void failure(CommonException e) {
                courseContract.joinCourseActionFailed(e);
                LogUtil.e("failure--"+e.getCode()+e.toString());
            }
        });
        compositeSubscription.add(subscription);
    }

}
