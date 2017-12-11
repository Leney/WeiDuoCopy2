package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.CurriculumActivityContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseBannerResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseListResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/6/27.
 */

public class CurriculumPresenter extends BasePresenter{
    private static final String TAG = "CurriculumPresenter";
    private CurriculumActivityContract curriculumActivityContract;
    private DataManager dataManager;

    public CurriculumPresenter(CurriculumActivityContract curriculumActivityContract, DataManager dataManager) {
        this.curriculumActivityContract = curriculumActivityContract;
        this.dataManager = dataManager;
    }

//    /** 获取课程数据*/
//    public void getCurriculumData(){
//        List<String> bannerUrls = new ArrayList<>();
//        bannerUrls.add("http://img.hb.aicdn.com/10d71b7b48fa693ba3c8d54c77c30f23a1ef8f6137cde-5iv274_fw658");
//        bannerUrls.add("http://image.tupian114.com/20160804/10303227.jpg");
//        bannerUrls.add("http://pic23.nipic.com/20120915/7612599_191200494310_2.jpg");
//
//        List<CurriculumBean> curriculumBeenList = new ArrayList<>();
//        curriculumBeenList.add(new CurriculumBean(1001, "好家长训练营初级班", "（第三期）", "2月20日开课", "999人已报名", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505962078&di=5bcda089c5e95fc2e460a53ec6ffcd83&imgtype=jpg&er=1&src=http%3A%2F%2Fsc.jb51.net%2Fuploads%2Fallimg%2F141018%2F11-14101Q124462A.jpg"));
//        curriculumBeenList.add(new CurriculumBean(1002, "好家长训练营初级班", "（第三期）", "2月20日开课", "999人已报名", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505367370283&di=81a01ffa84c0105d16809ee394973d77&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F10%2F57%2F50%2F93b1OOOPIC4d.jpg"));
//        curriculumBeenList.add(new CurriculumBean(1003, "好家长训练营初级班", "（第三期）", "2月20日开课", "999人已报名", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4047216380,2446001478&fm=27&gp=0.jpg"));
//        curriculumBeenList.add(new CurriculumBean(1004, "好家长训练营初级班", "（第三期）", "2月20日开课", "999人已报名", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505367399363&di=ea6c4002caa78d6162c318a156b4657a&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F10%2F58%2F55%2F96b1OOOPICac.jpg"));
//        curriculumBeenList.add(new CurriculumBean(1005, "好家长训练营初级班", "（第三期）", "2月20日开课", "999人已报名", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505367433566&di=d2137601c82480ff6fe43afe79f8ac81&imgtype=0&src=http%3A%2F%2Fimg.sc115.com%2Fuploads1%2Fsc%2Fpsd%2F150517%2F15051753.jpg"));
//        curriculumBeenList.add(new CurriculumBean(1006, "好家长训练营初级班", "（第三期）", "2月20日开课", "999人已报名", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505367443238&di=17c06273d08b0401368dc4d052a442f7&imgtype=0&src=http%3A%2F%2Fpic.35pic.com%2F10%2F60%2F48%2F43b1OOOPICb9.jpg"));
//        curriculumBeenList.add(new CurriculumBean(1007, "好家长训练营初级班", "（第三期）", "2月20日开课", "999人已报名", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4216498696,3723485679&fm=27&gp=0.jpg"));
//
//        curriculumActivityContract.getCurriculumDataSuccess(bannerUrls,curriculumBeenList);
//    }


    /**
     * 获取课程页面的Banner url
     */
    public void getCourseBanner() {
        Map<String,String> hmap = new HashMap<>();
//        hmap.put("token",token);
        hmap.put("bannerType","4");
        Subscription subscription =  retrofitClient.getCourseBanner(hmap , new ResponseResultListener<CourseBannerResult>(){
            @Override
            public void success(CourseBannerResult courseBannerResult) {
                List<CourseBannerResult.RecordsBean> records = courseBannerResult.getRecords();
                //LogUtil.e(TAG,"CourseBannerResult records----------"+records.toString());
                curriculumActivityContract.getBannerSuccess(records);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e(TAG,"CourseBannerResult fail----------"+"e.getCode()----------"+e.getCode()+e.getErrorMsg());
            }
        });
        compositeSubscription.add(subscription);
    }

    public void getCourseTable(int pageIndex,int refreshStatus) {

        Map<String,String> hashMap = new HashMap<>();
//        hashMap.put("token",token);
        hashMap.put("pageIndex",pageIndex+"");
        Subscription subscription = retrofitClient.getCourseTable(hashMap, new ResponseResultListener<CourseListResult>() {
            @Override
            public void success(CourseListResult courseListResult) {
                //LogUtil.e(TAG,"CourseListResult records----------"+courseListResult.getRecords().toString());
                curriculumActivityContract.getCourseTableSuccess(courseListResult,refreshStatus);
            }
            @Override
            public void failure(CommonException e) {
                LogUtil.e(TAG,"getCourseTableSuccess fail----------"+"e.getCode()----------"+e.getCode());
            }
        });
        compositeSubscription.add(subscription);
    }

}
