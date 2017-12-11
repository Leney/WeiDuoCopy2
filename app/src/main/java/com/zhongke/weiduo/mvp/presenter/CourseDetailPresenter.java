package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CourseDetailContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.CourseDetailBean;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.JoinCourseEntity;

import java.util.HashMap;

import rx.Subscription;

/**
 *
 * Created by llj on 2017/9/14.
 */

public class CourseDetailPresenter extends BasePresenter {
    private static final String TAG = "CourseDetailPresenter";
    private CourseDetailContract contract;
    private DataManager dataManager;

    public CourseDetailPresenter(CourseDetailContract contract, DataManager dataManager) {
        this.contract = contract;
        this.dataManager = dataManager;
    }

    /**
     * 获取详情信息
     * @param id
     */
    public void getDetail(int id){
        CourseDetailBean bean = new CourseDetailBean();
        bean.setId(id);
        bean.setName("基础入门教程:怎么制作相册视频");
        bean.setGoodPercent("99%");
        bean.setSignUpNum(666);
        bean.setPlayNum(888);
        bean.setInfo("并不是真正的长镜头,算是\"伪长镜头\"\n" +
                "剪辑方法也是挺简单的，很多电视台拍摄宣传片用的也是类似的手法。\n" +
                "前后场景之间尽量多拍一点,拍摄最好在一天完成;如果表现不同季节的话,最好在相似的光照和天气条件下完成.\n" +
                "转场过渡时注意最好用渐隐,衔接处注意模糊效果.\n" +
                "机位保持一个角度[一个机位就够了]\n" +
                "对了 一个跟进镜头需要滑轨");
        bean.getInfoImgs().add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505388067084&di=3f40bae028527451a44a8efa81a5f07a&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f92157e389e80000018c1b6232c0.jpg%40900w_1l_2o_100sh.jpg");
        bean.getInfoImgs().add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505388105198&di=ff376ea692737d72c86aacb8a6b8143f&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F014a2456df713e6ac72531cb593189.jpg%40900w_1l_2o_100sh.jpg");
        bean.getInfoImgs().add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505388121731&di=71dd55658e7c21663c9306de21223bf1&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F43%2Fae1b637b71183d8e199c7c06046f3257.jpg");
    }

    public void joinCourse(JoinCourseEntity entity) {
        Subscription subscription = retrofitClient.joinCourse(entity, new ResponseResultListener<Object>() {
            @Override
            public void success(Object o) {
                //提示加入成功
                contract.joinCourseSuccess();
                LogUtil.e("joinCourse 成功");
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e(TAG, "e.getCode()----------" + e.getCode() + "e.getErrMsg---" + e.getErrorMsg());
                contract.joinCourseFailed();
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 获取课程详情
     * @param courseId
     */
    public void getCourseDetail(String courseId) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("courseId",courseId);

        Subscription subscription = retrofitClient.getCourseDetail(hashMap, new ResponseResultListener<CourseDetailResult>() {
            @Override
            public void success(CourseDetailResult courseDetailResult) {

                LogUtil.e(TAG,"获取课程详情success");
                contract.getCourseDetailSuccess(courseDetailResult);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e(TAG,"获取课程详情failed"+"code--"+e.getCode()+"stirng"+e.toString());
                contract.getCourseDetailFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }
}
