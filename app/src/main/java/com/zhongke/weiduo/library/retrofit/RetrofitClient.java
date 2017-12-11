package com.zhongke.weiduo.library.retrofit;

import android.content.Context;

import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.library.okhttp.OkHttpProvider;
import com.zhongke.weiduo.library.rxjava.SubscribeUtils;
import com.zhongke.weiduo.mvp.model.entity.ActionListBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityAwardBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityBehaviorBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityFlowBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityListBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityListDetailBean;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.CatalogueVideoBean;
import com.zhongke.weiduo.mvp.model.entity.ClickLikeBean;
import com.zhongke.weiduo.mvp.model.entity.ContactsBean;
import com.zhongke.weiduo.mvp.model.entity.DesireListBean2;
import com.zhongke.weiduo.mvp.model.entity.DeviceMsgBean;
import com.zhongke.weiduo.mvp.model.entity.EducationSystemList;
import com.zhongke.weiduo.mvp.model.entity.ExecutePeopleBean;
import com.zhongke.weiduo.mvp.model.entity.FindClassifyBean;
import com.zhongke.weiduo.mvp.model.entity.FindRecommendBean;
import com.zhongke.weiduo.mvp.model.entity.FriendInfoBean;
import com.zhongke.weiduo.mvp.model.entity.IsBindDeviceBean;
import com.zhongke.weiduo.mvp.model.entity.JoinMember;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;
import com.zhongke.weiduo.mvp.model.entity.MyPlanBean;
import com.zhongke.weiduo.mvp.model.entity.MySchemeBean;
import com.zhongke.weiduo.mvp.model.entity.MyTargetBean;
import com.zhongke.weiduo.mvp.model.entity.NewExpertBean;
import com.zhongke.weiduo.mvp.model.entity.NewExpertCourseBean;
import com.zhongke.weiduo.mvp.model.entity.NewExpertDetailBean;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyList;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyOrGroupDetail;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyTag;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyTitle;
import com.zhongke.weiduo.mvp.model.entity.NewGroupBean;
import com.zhongke.weiduo.mvp.model.entity.NewGroupMemberBean;
import com.zhongke.weiduo.mvp.model.entity.NewMechanismBean;
import com.zhongke.weiduo.mvp.model.entity.NewMyFamily;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyFamilyBean;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyGroup;
import com.zhongke.weiduo.mvp.model.entity.NewNearbyUser;
import com.zhongke.weiduo.mvp.model.entity.NewOrganizationCourseBean;
import com.zhongke.weiduo.mvp.model.entity.NewOrganizationDetailBean;
import com.zhongke.weiduo.mvp.model.entity.NewPersonalData;
import com.zhongke.weiduo.mvp.model.entity.NewRecommendUser;
import com.zhongke.weiduo.mvp.model.entity.Paper;
import com.zhongke.weiduo.mvp.model.entity.PlanBean2;
import com.zhongke.weiduo.mvp.model.entity.PlanDetailBean2;
import com.zhongke.weiduo.mvp.model.entity.RecommendActiveBean;
import com.zhongke.weiduo.mvp.model.entity.RecommendPlanBean;
import com.zhongke.weiduo.mvp.model.entity.RecommendSchemeBean;
import com.zhongke.weiduo.mvp.model.entity.RegisterResultBean;
import com.zhongke.weiduo.mvp.model.entity.RequestAddFriendBean;
import com.zhongke.weiduo.mvp.model.entity.SchemeBean2;
import com.zhongke.weiduo.mvp.model.entity.SchemeDetailBean2;
import com.zhongke.weiduo.mvp.model.entity.SearchFamilyAndGroupResultBean;
import com.zhongke.weiduo.mvp.model.entity.SearchFriendResultBean;
import com.zhongke.weiduo.mvp.model.entity.SystemListDetailBean;
import com.zhongke.weiduo.mvp.model.entity.TargetDetailBean2;
import com.zhongke.weiduo.mvp.model.entity.TaskListBean;
import com.zhongke.weiduo.mvp.model.entity.UserAcountSizeBean;
import com.zhongke.weiduo.mvp.model.entity.UserCollectListBean;
import com.zhongke.weiduo.mvp.model.entity.WeiDuoHomeBean;
import com.zhongke.weiduo.mvp.model.entity.eventschedule.EventScheduleResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.ActionDetailResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CollectionCourseEntity;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseActionEntity;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseActionResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseBannerResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseListResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.JoinCourseEntity;
import com.zhongke.weiduo.mvp.model.requestEntity.LoginEntity;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscription;

/**
 * Created by ${xinGen} on 2017/11/6.
 * <p>
 * 一个网络数据管理类：
 * <p>
 * 这里配置Retrofit
 */

public class RetrofitClient {
    private final Retrofit retrofit;
    private final ApiService apiService;
    private static RetrofitClient instance;
    private final OkHttpClient okHttpClient;
    private final String baseUrl;
    private Context appContext;

    /**
     * 配置Retrofit,OkHttp3
     */
    private RetrofitClient() {
        this.okHttpClient = OkHttpProvider.provideOkHttpClient();
        this.baseUrl = HttpConstance.BASE_URL;
        retrofit = new Retrofit.Builder()
                .client(this.okHttpClient)
                .baseUrl(this.baseUrl)
                .addConverterFactory(SpecifiedJsonConverter.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        this.apiService = createService(ApiService.class);
        this.appContext = ZkApplication.getInstance();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    /**
     * 指定创建对应的ServiceInterface.防止基本的Service接口不适合
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T createService(final Class<T> service) {
        return this.retrofit.create(service);
    }

    /**
     * 通用方法：将回调接口对象，传递到Subscriber
     *
     * @param subscriber
     * @param <T>
     * @return
     */
    private <T> BaseSubscriber<T> toSubscriber(ResponseResultListener<T> subscriber) {
        return new BaseSubscriber(this.appContext, subscriber);
    }

    /**
     * 登入接口
     *
     * @param entity
     * @param subscriber
     */
    public Subscription getLogin(LoginEntity entity, ResponseResultListener<LoginResult> subscriber) {
        return this.apiService.getLogin(BuilderMap.builderMap(entity)).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取规划列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getSchemeList(Map<String, Object> params, ResponseResultListener<SchemeBean2> subscriber) {
        return this.apiService.getSchemeList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取计划列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getPlanList(Map<String, Object> params, ResponseResultListener<PlanBean2> subscriber) {
        return this.apiService.getPlanList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取规划详情
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getSchemeDetail(Map<String, Object> params, ResponseResultListener<SchemeDetailBean2> subscriber) {
        return this.apiService.getSchemeDetail(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取计划详情
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getPlanDetail(Map<String, Object> params, ResponseResultListener<PlanDetailBean2> subscriber) {
        return this.apiService.getPlanDetail(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取规划详情
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getTargetDetail(Map<String, Object> params, ResponseResultListener<TargetDetailBean2> subscriber) {
        return this.apiService.getTargetDetail(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取规划详情推荐的计划列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getTargetDetailRecommendList(Map<String, Object> params, ResponseResultListener<RecommendSchemeBean> subscriber) {
        return this.apiService.getTargetDetailRecommendSchemeList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 点赞规划、计划
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription likeSchemeOrPlan(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.likeSchemeOrPlan(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取规划详情推荐的计划列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getSchemeDetailRecommendList(Map<String, Object> params, ResponseResultListener<RecommendPlanBean> subscriber) {
        return this.apiService.getSchemeDetailRecommendPlanList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取计划详情推荐的活动列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getPlanDetailRecommendList(Map<String, Object> params, ResponseResultListener<RecommendActiveBean> subscriber) {
        return this.apiService.getSchemeDetailRecommendActiveList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 添加已有规划或者计划到我的
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription addSchemeOrPlan(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.addSchemeOrPlan(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 添加已有目标到我的
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription addTarget(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.addTarget(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 添加自定义目标到我的
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription addSelfTarget(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.addSelfTarget(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 添加自定义规划到我的
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription addSelfScheme(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.addSelfScheme(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 添加自定义计划到我的
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription addSelfPlan(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.addSelfPlan(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取我的目标列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getMyTargetList(Map<String, Object> params, ResponseResultListener<MyTargetBean> subscriber) {
        return this.apiService.getMyTargetList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取我的规划列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getMySchemeList(Map<String, Object> params, ResponseResultListener<MySchemeBean> subscriber) {
        return this.apiService.getMySchemeList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取我的计划列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getMyPlanList(Map<String, Object> params, ResponseResultListener<MyPlanBean> subscriber) {
        return this.apiService.getMyPlanList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 删除我的目标
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription deleteMyTarget(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.deleteMyTarget(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 删除我的规划
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription deleteMyScheme(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.deleteMyScheme(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 删除我的计划
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription deleteMyPlan(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.deleteMyPlan(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取我的联系人列表
     *
     * @param subscriber
     * @return
     */
    public Subscription getContactsList(ResponseResultListener<ContactsBean> subscriber) {
        return this.apiService.getContactsList().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 搜索添加好友列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription searchAddFriendList(Map<String, Object> params, ResponseResultListener<SearchFriendResultBean> subscriber) {
        return this.apiService.searchAddFriendList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 搜索家庭或者群组列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription searchAddFamilyAndGroupList(Map<String, Object> params, ResponseResultListener<SearchFamilyAndGroupResultBean> subscriber) {
        return this.apiService.searchAddFamilyAndGroupList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }


    /**
     * 获取课程列表接口
     *
     * @param subscriber
     */
    public Subscription getCourseList(HashMap map, ResponseResultListener<CourseListResult> subscriber) {
        return this.apiService.getCourseList(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 活动列表
     *
     * @param subscriber
     * @return
     */
    public Subscription getActionList(String token, ResponseResultListener<ActionListBean> subscriber) {
        return this.apiService.getActionList(token).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }


    /**
     * 获取广告轮播图
     *
     * @param subscriber
     */
    public Subscription getBannerBitmap(HashMap map, ResponseResultListener<CarouselBitmapBean> subscriber) {
        return this.apiService.getBannerBitmap(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }


    /**
     * 获取课程轮播图的url
     */

    public Subscription getCourseBanner(Map<String, String> map, ResponseResultListener<CourseBannerResult> subscriber) {
        return this.apiService.getCourseBanner(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取课程里的课程列表
     *
     * @param subscriber
     * @return
     */
    public Subscription getCourseTable(Map<String, String> map, ResponseResultListener<CourseListResult> subscriber) {
        return this.apiService.getCourseTable(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取课程详情
     */
    public Subscription getCourseDetail(HashMap map, ResponseResultListener<CourseDetailResult> subscriber) {
        return this.apiService.getCourseDetail(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 加入课程
     *
     * @param entity
     * @param subscriber
     * @return
     */
    public Subscription joinCourse(JoinCourseEntity entity, ResponseResultListener<Object> subscriber) {
        return this.apiService.joinCourse(entity.getToken(), entity.getCourseId()).
                compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取课程的活动
     *
     * @param entity
     * @param subscriber
     * @return
     */
    public Subscription getCourseAction(CourseActionEntity entity, ResponseResultListener<CourseActionResult> subscriber) {
        return this.apiService.getCourseAction(entity.getToken(), entity.getCourseId()).
                compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 加入课程的活动
     *
     * @param subscriber
     * @return
     */
    public Subscription joinCourseAction(HashMap map, ResponseResultListener subscriber) {
        return this.apiService.joinCourseAction(map)
                .compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 收藏活动
     */
    public Subscription collectionAction(String token, String collectType, int collectObjectId, ResponseResultListener subscriber) {
        return this.apiService.collectionAction(token, collectType, collectObjectId).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获得课程的活动详情
     */
    public Subscription getCourseActionDetail(HashMap map, ResponseResultListener<ActionDetailResult> subscriber) {
        return this.apiService.getCourseActionDetail(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 收藏课程
     */
    public Subscription collectionCourse(CollectionCourseEntity entity, ResponseResultListener<Object> subscriber) {
        return this.apiService.collectionCourse(entity.getToken(), entity.getCollectType(), entity.getCollectObjectId()).
                compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取专家列表
     *
     * @param subscriber
     */
    public Subscription getExperList(HashMap map, ResponseResultListener<NewExpertBean> subscriber) {
        return this.apiService.getExperList(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取专家详情
     *
     * @param subscriber
     */
    public Subscription getExperDetail(HashMap map, ResponseResultListener<NewExpertDetailBean> subscriber) {
        return this.apiService.getExperDetail(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取机构列表
     *
     * @param subscriber
     */
    public Subscription getMechanismList(HashMap map, ResponseResultListener<NewMechanismBean> subscriber) {
        return this.apiService.getMechanismList(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取机构详情
     *
     * @param subscriber
     */
    public Subscription getMechanismDetail(HashMap map, ResponseResultListener<NewOrganizationDetailBean> subscriber) {
        return this.apiService.getMechanismDetail(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取机构课程列表
     *
     * @param subscriber
     */
    public Subscription getOrganizationCourse(HashMap map, ResponseResultListener<NewOrganizationCourseBean> subscriber) {
        return this.apiService.getOrganizationCourse(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 收藏专家，机构，课程，活动
     *
     * @param subscriber
     */
    public Subscription Collection(HashMap map, ResponseResultListener subscriber) {
        return this.apiService.Collection(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 取消收藏专家，机构，课程，活动
     *
     * @param subscriber
     */
    public Subscription cancelCollection(HashMap map, ResponseResultListener subscriber) {
        return this.apiService.cancelCollection(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取活动列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getActivityList(Map<String, Object> params, ResponseResultListener<ActivityListBean> subscriber) {
        return this.apiService.getActivityList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 微舵中底部体系界面中接口
     *
     * @param subscriber
     * @return
     */
    public Subscription getWeiDuoHome(ResponseResultListener<WeiDuoHomeBean> subscriber) {
        return this.apiService.getWeiDuoHome().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 体系列表
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getEducationSystemAList(Map<String, Object> params, ResponseResultListener<EducationSystemList> subscriber) {
        return this.apiService.getEducationSystemList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 活动详情
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getActivityListDetail(Map<String, Object> params, ResponseResultListener<ActivityListDetailBean> subscriber) {
        return this.apiService.getActivityListDetail(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取活动日程
     *
     * @param map
     * @param subscriber
     * @return
     */
    public Subscription getEventSchedule(HashMap map, ResponseResultListener<EventScheduleResult> subscriber) {
        return this.apiService.getEventSchedule(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 添加日程
     */
    public Subscription addSchedule(HashMap map, ResponseResultListener subscriber) {
        return this.apiService.addSchedule(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 体系详情
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getSystemListDetail(Map<String, Object> params, ResponseResultListener<SystemListDetailBean> subscriber) {
        return this.apiService.getSystemListDetail(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 个人资料
     *
     * @param subscriber
     * @return
     */
    public Subscription getPersonalData(ResponseResultListener<NewPersonalData> subscriber) {
        return this.apiService.getPersonalData().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取我的家庭资料
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getMyFamily(Map<String, Object> params, ResponseResultListener<NewMyFamily> subscriber) {
        return this.apiService.getMyFamily(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 1.9获取家庭头衔列表
     *
     * @param params
     * @return
     */
    public Subscription getFamilyTitle(Map<String, Object> params, ResponseResultListener<NewFamilyTitle> subscriber) {
        return this.apiService.getFamilyTitle(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 1.12修改当前登录用户信息
     *
     * @param params
     * @return
     */
    public Subscription modifyUserInfo(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.modifyUserInfo(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 注册
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getRegister(Map<String, Object> params, ResponseResultListener<RegisterResultBean> subscriber) {
        return this.apiService.getRegister(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取验证码
     *
     * @param params
     * @param subscriber
     * @return
     */
    public Subscription getPhoneCode(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.getPhoneCode(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 激活设备
     *
     * @param params
     * @param subscriber
     * @return
     */

    public Subscription activateDevice(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.activateDevice(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取专家课程列表
     *
     * @param params
     * @return
     */
    public Subscription getExpertCourse(Map<String, Object> params, ResponseResultListener<NewExpertCourseBean> subscriber) {
        return this.apiService.getExpertCourse(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.1获取家庭标签列表√
     *
     * @param params
     * @return
     */
    public Subscription getFamilyTag(Map<String, Object> params, ResponseResultListener<NewFamilyTag> subscriber) {
        return this.apiService.getFamilyTag(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.5创建群组（群聊）√
     *
     * @param params
     * @return
     */
    public Subscription createGroup(Map<String, Object> params, ResponseResultListener<NewGroupBean> subscriber) {
        return this.apiService.createGroup(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.6加入群组/家庭√
     *
     * @param params
     * @return
     */
    public Subscription joinGroup(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.joinGroup(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 添加好友请求
     */
    public Subscription applyFriendRequest(Map<String, String> map, ResponseResultListener subscriber) {
        return this.apiService.applyFriendRequest(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.2获取家庭列表根据标签√
     */
    public Subscription getFamilyListToTag(Map<String, Object> map, ResponseResultListener<NewFamilyList> subscriber) {
        return this.apiService.getFamilyListToTag(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.7从群组/家庭移除成员√
     *
     * @param params
     * @return
     */
    public Subscription dropOutGroup(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.dropOutGroup(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.8获取【家庭/群组】成员列表√
     *
     * @param params
     * @return
     */
    public Subscription getGroupMemberList(Map<String, Object> params, ResponseResultListener<NewGroupMemberBean> subscriber) {
        return this.apiService.getGroupMemberList(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.9添加家庭为好友家庭√
     *
     * @param params
     * @return
     */
    public Subscription addFamilyFriend(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.addFamilyFriend(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.13退出群组或家庭√
     *
     * @param params
     * @return
     */
    public Subscription quitGroup(Map<String, Object> params, ResponseResultListener subscriber) {
        return this.apiService.quitGroup(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.15获取附近的家庭列表√
     *
     * @param params
     * @return
     */
    public Subscription getNearbyFamily(Map<String, Object> params, ResponseResultListener<NewNearbyFamilyBean> subscriber) {
        return this.apiService.getNearbyFamily(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.16获取附近的群组列表√
     *
     * @return
     */
    public Subscription getNearbyGroup(ResponseResultListener<NewNearbyGroup> subscriber) {
        return this.apiService.getNearbyGroup().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.17获取附近的用户列表√
     *
     * @return
     */
    public Subscription getNearbyUser(ResponseResultListener<NewNearbyUser> subscriber) {
        return this.apiService.getNearbyUser().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.18获取推荐好友列表
     *
     * @return
     */
    public Subscription getRecommendUser(Map<String, Object> params, ResponseResultListener<NewRecommendUser> subscriber) {
        return this.apiService.getRecommendUser(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.14获取群组或家庭的资料√
     *
     * @return
     */
    public Subscription getFamilyOrGroupDetail(Map<String, Object> params, ResponseResultListener<NewFamilyOrGroupDetail> subscriber) {
        return this.apiService.getFamilyOrGroupDetail(params).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 6.6获取孩子的愿望列表
     */
    public Subscription getChildWish(Map<String, String> map, ResponseResultListener<DesireListBean2> subscriber) {
        return this.apiService.getChildWish(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    public Subscription getChildWishNoActionId(ResponseResultListener<DesireListBean2> subscriber) {
        return this.apiService.getChildWishNoActionId().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 6.7帮孩子实现愿望
     */
    public Subscription helpRealizeWish(Map<String, String> map, ResponseResultListener subscriber) {
        return this.apiService.helpRealizeWish(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 2.9获取好友资料
     */
    public Subscription getFriendInfo(Map<String, String> map, ResponseResultListener<FriendInfoBean> subscriber) {
        return this.apiService.getFriendInfo(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取请求好友的消息列表
     */
    public Subscription getRequestAddFriendList(ResponseResultListener<RequestAddFriendBean> subscriber) {
        return this.apiService.getRequestAddFriendList().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 2.5 同意与拒绝好友申请
     */
    public Subscription handleFriendRequest(Map<String, String> map, ResponseResultListener subscriber) {
        return this.apiService.handleFriendRequest(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 2.6删除联系人
     */
    public Subscription removeFriend(Map<String, String> map, ResponseResultListener subscriber) {
        return this.apiService.removeFriend(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取首页的孩子和父母的任务
     *
     * @param subscriber
     * @return
     */
    public Subscription getTaskList(ResponseResultListener<TaskListBean> subscriber) {
        return this.apiService.getTaskList().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获得愿望、目标、规划、计划、活动日程统计
     *
     * @param subscriber
     * @return
     */
    public Subscription getUserAcountSize(ResponseResultListener<UserAcountSizeBean> subscriber) {
        return this.apiService.getUserAcountSize().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 1.2获得活动类别表
     */
    public Subscription getFindActivityType(Map<String, String> map, ResponseResultListener<FindClassifyBean> subscriber) {
        return this.apiService.getFindActivityType(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 1.3获得推荐活动列表
     */
    public Subscription getFindRecommend(Map<String, String> map, ResponseResultListener<FindRecommendBean> subscriber) {
        return this.apiService.getFindRecommend(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 点赞活动
     */
    public Subscription clickLike(Map<String, String> map, ResponseResultListener<ClickLikeBean> subscriber) {
        return this.apiService.clickLike(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 根据活动类别获取活动列表
     */
    public Subscription getActionByActionKind(Map<String, String> map, ResponseResultListener<FindRecommendBean> subscriber) {
        return this.apiService.getActionByActionKind(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取用户收藏列表
     */
    public Subscription getUserCollectionList(ResponseResultListener<UserCollectListBean> subscriber) {
        return this.apiService.getUserCollectionList().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 更改用户秘密
     */
    public Subscription changePassword(Map<String, String> map, ResponseResultListener subscriber) {
        return this.apiService.changePassword(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 1.15忘记密码√
     */
    public Subscription resetPassword(Map<String, String> map, ResponseResultListener subscriber) {
        return this.apiService.resetPassword(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取用户和用户孩子列表
     */
    public Subscription getUserAndChild(ResponseResultListener<ExecutePeopleBean> subscriber) {
        return this.apiService.getUserAndChild().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 检查设备
     */
    public Subscription checkDeviceMSG(Map<String, String> map, ResponseResultListener<DeviceMsgBean> subscriber) {
        return this.apiService.checkDevice(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }


    /**
     * 获取活动流程数据列表
     */
    public Subscription getActivityFlowList(Map<String, String> map, ResponseResultListener<ActivityFlowBean> subscriber) {
        return this.apiService.getActivityFlowList(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取行为列表数据
     */
    public Subscription getActivityBehavior(Map<String, String> map, ResponseResultListener<ActivityBehaviorBean> subscriber) {
        return this.apiService.getActivityBehavior(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 评价活动行为
     */
    public Subscription CommentActivityBehavior(Map<String, String> map, ResponseResultListener subscriber) {
        return this.apiService.CommentActivityBehavior(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 检验用户是否绑定设备
     */
    public Subscription getDeviceCount(ResponseResultListener<IsBindDeviceBean> subscriber) {
        return this.apiService.getDeviceCount().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取视频地址
     */
    public Subscription getCatalogueVideoUrl(Map<String, String> map, ResponseResultListener<CatalogueVideoBean> subscriber) {
        return this.apiService.getCatalogueVideoUrl(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }


    public Subscription bindDevice(Map<String, Object> map, ResponseResultListener subscriber) {
        return this.apiService.bindDevice(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取离线消息
     */
    public Subscription getOfflineMessageList(ResponseResultListener subscriber) {
        return this.apiService.getOfflineMessageList().compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 改变消息接收状态
     *
     * @param ids 以逗号分隔的消息主键id
     */
    public Subscription changeMessageReceiveState(String ids, ResponseResultListener subscriber) {
        return this.apiService.changeMessageReceiveState(ids).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 3.21修改群名称√
     */
    public Subscription updateGroupName(Map<String, String> map, ResponseResultListener subscriber) {
        return this.apiService.updateGroupName(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /*public Subscription updateGroupName(Map<String, String> map, ResponseResultListener subscriber) {
        return this.apiService.getActionFlowHtml(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }*/

    /**
     * 获取活动未发放的奖品列表
     */
    public Subscription getAwardList(Map<String, String> map, ResponseResultListener<ActivityAwardBean> subscriber) {
        return this.apiService.getAwardList(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 发放活动奖励
     */
    public Subscription grantActivityAward(Map<String, String> map, ResponseResultListener subscriber) {
        return this.apiService.grantActivityAward(map).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取抢答活动的试卷内容
     */
    public Subscription getPaper(int actionId, ResponseResultListener<Paper> subscriber) {
        return this.apiService.getPaper(actionId).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }

    /**
     * 获取参与活动人员列表(包含观战人员)
     */
    public Subscription getJoinMemberList(int actionId, ResponseResultListener<JoinMember> subscriber) {
        return this.apiService.getJoinMemberList(actionId).compose(SubscribeUtils.createTransformer()).subscribe(toSubscriber(subscriber));
    }
}
