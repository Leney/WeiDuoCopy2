package com.zhongke.weiduo.library.retrofit;

import com.zhongke.weiduo.mvp.model.entity.ActionListBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityAwardBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityBehaviorBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityDetailBaseDataBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityFlowBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityFlowDetailBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityListBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityListDetailBean;
import com.zhongke.weiduo.mvp.model.entity.ActivityResultFinishBean;
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
import com.zhongke.weiduo.mvp.model.entity.LoginModelBean;
import com.zhongke.weiduo.mvp.model.entity.LoginResult;
import com.zhongke.weiduo.mvp.model.entity.MicroRudderBean;
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
import com.zhongke.weiduo.mvp.model.entity.OfflineMessage;
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
import com.zhongke.weiduo.mvp.model.entity.TestBus;
import com.zhongke.weiduo.mvp.model.entity.UserAcountSizeBean;
import com.zhongke.weiduo.mvp.model.entity.UserCollectListBean;
import com.zhongke.weiduo.mvp.model.entity.UserInfoBean;
import com.zhongke.weiduo.mvp.model.entity.WeiDuoHomeBean;
import com.zhongke.weiduo.mvp.model.entity.eventschedule.EventScheduleResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseActionResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseBannerResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseDetailResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseListResult;
import com.zhongke.weiduo.mvp.model.entity.school_course.JoinActionEntity;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Karma on 2017/6/8.
 * 类描述：网络请求接口
 */

public interface ApiService {

    //dtype=&city=%E6%B7%B1%E5%9C%B3&bus=m345&key=524454baff052e8f892797a420d4c808
    @GET("index")
    Observable<HttpResult<List<TestBus>>> getBus(@Query("dtype") String dtype, @Query("city") String city, @Query("bus") String bus, @Query("key") String key);


    @GET("{path}")
        //主界面维舵
    Observable<HttpResult<MicroRudderBean>> getMainActList(@Path("path") String url, @QueryMap Map<String, String> options);

    @GET("{path}")
        //获取活动详情基本数据
    Observable<HttpResult<ActivityDetailBaseDataBean>> getActivityDetailBaseData(@Path("path") String url, @QueryMap Map<String, String> options);

    @GET("{path}")
        //获取活动流程详情
    Observable<HttpResult<ActivityFlowDetailBean>> getActivityFlowDetailBaseData(@Path("path") String url, @QueryMap Map<String, String> options);

    @GET("{path}")
        //获取已结束活动成果评价详情
    Observable<HttpResult<ActivityResultFinishBean>> getActivityDetailBaseFinishData(@Path("path") String url, @QueryMap Map<String, String> options);

    @GET("/user/login")
        //登录
    Call<HttpResult<LoginModelBean>> getLoginBaseData(@Query("loginName") String loginName, @Query("userPass") String userPass);

    @GET("/user/get_user_info")
        //获取用户信息
    Call<HttpResult<UserInfoBean>> getUserInfoData(@Query("token") String token, @Query("userId") int userId);

    /**
     * 登入接口
     *
     * @param data
     * @return
     */
    @FormUrlEncoded
    @POST("/user/login")
    Observable<HttpResult<LoginResult>> getLogin(@FieldMap Map<String, String> data);

    /**
     * 获取规划列表
     *
     * @param params
     * @return
     */
    @GET("/b_plan/get_b_plan_list")
    Observable<HttpResult<SchemeBean2>> getSchemeList(@QueryMap Map<String, Object> params);

    /**
     * 获取计划列表
     *
     * @param params
     * @return
     */
    @GET("/s_plan/get_s_plan_list")
    Observable<HttpResult<PlanBean2>> getPlanList(@QueryMap Map<String, Object> params);

    /**
     * 获取活动列表
     */
    @GET("/book/get_action_list")
    Observable<HttpResult<ActionListBean>> getActionList(@Query("token") String token);

    /**
     * 获取规划详情
     *
     * @param params
     * @return
     */
    @GET("/b_plan/get_b_plan_detail")
    Observable<HttpResult<SchemeDetailBean2>> getSchemeDetail(@QueryMap Map<String, Object> params);

    /**
     * 获取目标详情
     *
     * @param params
     * @return
     */
    @GET("/ability_book/get_target_detail")
    Observable<HttpResult<TargetDetailBean2>> getTargetDetail(@QueryMap Map<String, Object> params);

    /**
     * 获取计划详情
     *
     * @param params
     * @return
     */
    @GET("/s_plan/get_s_plan_detail")
    Observable<HttpResult<PlanDetailBean2>> getPlanDetail(@QueryMap Map<String, Object> params);

    /**
     * 获取规划详情中推荐的计划列表
     *
     * @param params
     * @return
     */
    @GET("/b_plan/get_recommend_s_plan")
    Observable<HttpResult<RecommendPlanBean>> getSchemeDetailRecommendPlanList(@QueryMap Map<String, Object> params);

    /**
     * 获取计划详情中推荐的活动列表
     *
     * @param params
     * @return
     */
    @GET("/s_plan/get_recommend_action_list")
    Observable<HttpResult<RecommendActiveBean>> getSchemeDetailRecommendActiveList(@QueryMap Map<String, Object> params);

    /**
     * 获取目标详情中推荐的规划列表
     *
     * @param params
     * @return
     */
    @GET("/ability_book/get_recommend_b_plan")
    Observable<HttpResult<RecommendSchemeBean>> getTargetDetailRecommendSchemeList(@QueryMap Map<String, Object> params);

    /**
     * 点赞规划或者计划
     *
     * @param params
     * @return
     */
    @GET("/b_plan/thumb_up_book")
    Observable<HttpResult> likeSchemeOrPlan(@QueryMap Map<String, Object> params);

    /**
     * 添加已有规划或者计划
     *
     * @param params
     * @return
     */
    @GET("/b_plan/add_to_my_b_plan")
    Observable<HttpResult> addSchemeOrPlan(@QueryMap Map<String, Object> params);

    /**
     * 添加已有目标
     *
     * @param params
     * @return
     */
    @GET("/target/add_to_my_target")
    Observable<HttpResult> addTarget(@QueryMap Map<String, Object> params);

    /**
     * 添加自定义目标
     *
     * @param params
     * @return
     */
    @GET("/target/add_target")
    Observable<HttpResult> addSelfTarget(@QueryMap Map<String, Object> params);

    /**
     * 添加自定义规划
     *
     * @param params
     * @return
     */
    @GET("/b_plan/add_b_plan")
    Observable<HttpResult> addSelfScheme(@QueryMap Map<String, Object> params);

    /**
     * 添加自定义计划
     *
     * @param params
     * @return
     */
    @GET("/s_plan/add_s_plan")
    Observable<HttpResult> addSelfPlan(@QueryMap Map<String, Object> params);

    /**
     * 获取我的目标列表
     *
     * @param params
     * @return
     */
    @GET("/target/get_my_target_list")
    Observable<HttpResult<MyTargetBean>> getMyTargetList(@QueryMap Map<String, Object> params);

    /**
     * 获取我的规划列表
     *
     * @param params
     * @return
     */
    @GET("/b_plan/get_my_b_plan_list")
    Observable<HttpResult<MySchemeBean>> getMySchemeList(@QueryMap Map<String, Object> params);

    /**
     * 获取我的计划列表
     *
     * @param params
     * @return
     */
    @GET("/s_plan/get_my_s_plan_list")
    Observable<HttpResult<MyPlanBean>> getMyPlanList(@QueryMap Map<String, Object> params);

    /**
     * 删除我的目标
     *
     * @param params
     * @return
     */
    @GET("/target/delete_target_by_id")
    Observable<HttpResult> deleteMyTarget(@QueryMap Map<String, Object> params);

    /**
     * 删除我的规划
     *
     * @param params
     * @return
     */
    @GET("/b_plan/delete_b_plan_by_id")
    Observable<HttpResult> deleteMyScheme(@QueryMap Map<String, Object> params);


    /**
     * 删除我的计划
     *
     * @param params
     * @return
     */
    @GET("/s_plan/delete_s_plan_by_id")
    Observable<HttpResult> deleteMyPlan(@QueryMap Map<String, Object> params);

    /**
     * 获取我的联系人列表
     *
     * @return
     */
    @GET("/user/get_contacts_list")
//    Observable<HttpResult<ContactsBean>> getContactsList(@Query("token") String token);
    Observable<HttpResult<ContactsBean>> getContactsList();

    /**
     * 搜索添加好友列表
     *
     * @param params
     * @return
     */
    @GET("/friend/search_user")
    Observable<HttpResult<SearchFriendResultBean>> searchAddFriendList(@QueryMap Map<String, Object> params);

    /**
     * 搜索家庭或者群组列表
     *
     * @param params
     * @return
     */
    @GET("/friend/search_family_or_group")
    Observable<HttpResult<SearchFamilyAndGroupResultBean>> searchAddFamilyAndGroupList(@QueryMap Map<String, Object> params);


    /**
     * 课程列表接口
     *
     * @param data
     * @return
     */

    @GET("/course/get_recommend_course_list")
    Observable<HttpResult<CourseListResult>> getCourseList(@QueryMap Map<String, String> data);

    /**
     * 获取轮播图
     *
     * @param data
     * @return
     */
    @GET("/course/get_banner_list")
    Observable<HttpResult<CarouselBitmapBean>> getBannerBitmap(@QueryMap Map<String, String> data);

    /**
     * 获取专家列表
     *
     * @param data
     * @return
     */

    @GET("/expert/get_expert_list")
    Observable<HttpResult<NewExpertBean>> getExperList(@QueryMap Map<String, String> data);

    /**
     * 获取专家详情
     *
     * @param data
     * @return
     */
    @GET("/expert/get_expert_detail")
    Observable<HttpResult<NewExpertDetailBean>> getExperDetail(@QueryMap Map<String, String> data);

    /**
     * 获取机构列表
     *
     * @param data
     * @return
     */

    @GET("/org/get_org_list")
    Observable<HttpResult<NewMechanismBean>> getMechanismList(@QueryMap Map<String, String> data);

    /**
     * 获取机构详情
     *
     * @param data
     * @return
     */

    @GET("/org/get_org_detail")
    Observable<HttpResult<NewOrganizationDetailBean>> getMechanismDetail(@QueryMap Map<String, String> data);

    /**
     * 获取机构课程列表
     *
     * @param data
     * @return
     */

    @GET("/org/get_org_course_list")
    Observable<HttpResult<NewOrganizationCourseBean>> getOrganizationCourse(@QueryMap Map<String, String> data);

    /**
     * 收藏专家，机构，课程，活动
     *
     * @param data
     * @return
     */
    @GET("/course/user_collect")
    Observable<HttpResult> Collection(@QueryMap Map<String, String> data);

    /**
     * 活动列表
     *
     * @param data
     * @return
     */
    @GET("/action/get_action_list")
    Observable<HttpResult<ActivityListBean>> getActivityList(@QueryMap Map<String, Object> data);

    /**
     * 微舵的中间体系接口
     */
    @GET("/book/get_weiduo_index_list")
    Observable<HttpResult<WeiDuoHomeBean>> getWeiDuoHome();

    /**
     * 教育体系列表
     *
     * @param data
     * @return
     */
    @GET("/ability_book/get_ability_book_list")
    Observable<HttpResult<EducationSystemList>> getEducationSystemList(@QueryMap Map<String, Object> data);

    /**
     * 活动详情
     *
     * @param data
     * @return
     */
    @GET("/action/get_action_detail")
    Observable<HttpResult<ActivityListDetailBean>> getActivityListDetail(@QueryMap Map<String, Object> data);


    /**
     * 获取课程的banner url
     *
     * @return
     */
    @GET("/course/get_banner_list")
    Observable<HttpResult<CourseBannerResult>> getCourseBanner(@QueryMap Map<String, String> data);

    /**
     * 获取课程里的课程列表
     *
     * @return
     */
    @GET("/course/get_course_list")
    Observable<HttpResult<CourseListResult>> getCourseTable(@QueryMap Map<String, String> data);


    /**
     * 获取课程详情
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("/course/get_course_detail")
    Observable<HttpResult<CourseDetailResult>> getCourseDetail(@FieldMap Map<String, String> map);


    /**
     * 加入课程
     *
     * @param token
     * @param courseId
     * @return
     */
    @GET("/course/join_course")
    Observable<HttpResult<Object>> joinCourse(@Query("token") String token, @Query("courseId") String courseId);

    /**
     * 获取课程的活动列表
     *
     * @param token
     * @param courseId
     * @return
     */
    @GET("/course/get_course_action_list")
    Observable<HttpResult<CourseActionResult>> getCourseAction(@Query("token") String token, @Query("courseId") String courseId);

    /**
     * 加入课程的活动
     *
     * @param data
     * @return
     */
    @GET("/course/join_action")
    Observable<HttpResult> joinCourseAction(@QueryMap Map<String, Object> data);

    /**
     * 加入活动
     *
     * @param data
     * @return
     */
    @GET("/course/join_action")
    Observable<HttpResult<JoinActionEntity>> joinAction(@QueryMap Map<String, String> data);


    /**
     * 收藏活动
     */
    @GET("/course/user_collect")
    Observable<HttpResult> collectionAction(@Query("token") String token, @Query("collectType") String collectType, @Query("collectObjectId") int collectObjectId);

    /**
     * 获得课程的活动详情
     * <p>
     * 先不用课程的活动详情接口,用的活动的获得活动详情接口
     */

    @GET("/action/get_action_detail")
    Observable<HttpResult<Object>> getCourseActionDetail(@QueryMap Map<String, String> data);


    /**
     * 收藏课程
     */
    @GET("/course/get_action_detail")
    Observable<HttpResult<Object>> collectionCourse(@Query("token") String token, @Query("collectType") String collectType,
                                                    @Query("collectObjectId") String collectObjectId);

    /**
     * 获取互动日程列表
     */
    @GET("/schedule/get_my_schedule_list")
    Observable<HttpResult<EventScheduleResult>> getEventSchedule(@QueryMap Map<String, String> data);

    /**
     * 添加日程
     *
     * @param data
     * @return
     */
    @GET("/schedule/add_schedule")
    Observable<HttpResult> addSchedule(@QueryMap Map<String, String> data);


    /**
     * 体系详情
     *
     * @param data
     * @return
     */
    @GET("/ability_book/get_ability_book_detail")
    Observable<HttpResult<SystemListDetailBean>> getSystemListDetail(@QueryMap Map<String, Object> data);

    /**
     * 取消收藏（专家，机构，课程，活动）
     *
     * @param data
     * @return
     */
    @GET("/course/cancel_user_collect")
    Observable<HttpResult> cancelCollection(@QueryMap Map<String, Object> data);

    /**
     * 获取个人资料
     *
     * @return
     */
    @GET("/user/get_user_info")
    Observable<HttpResult<NewPersonalData>> getPersonalData();

    /**
     * 获取我的家庭资料
     *
     * @param data
     * @return
     */
    @GET("/user/get_my_family")
    Observable<HttpResult<NewMyFamily>> getMyFamily(@QueryMap Map<String, Object> data);

    /**
     * 1.9获取家庭头衔列表
     *
     * @param data
     * @return
     */
    @GET("/user/get_family_title_list")
    Observable<HttpResult<NewFamilyTitle>> getFamilyTitle(@QueryMap Map<String, Object> data);

    /**
     * 1.12修改当前登录用户信息
     *
     * @param data
     * @return
     */
    @GET("/user/update_current_user_info")
    Observable<HttpResult> modifyUserInfo(@QueryMap Map<String, Object> data);


    /**
     * 注册
     *
     * @param data
     * @return
     */
    @GET("/user/register_with_phone")
    Observable<HttpResult<RegisterResultBean>> getRegister(@QueryMap Map<String, Object> data);

    /**
     * 获取手机验证码
     *
     * @param data
     * @return
     */
    @GET("/user/get_phone_code")
    Observable<HttpResult> getPhoneCode(@QueryMap Map<String, Object> data);

    /**
     * 激活Happlior设备信息
     *
     * @param data
     * @return
     */
    @FormUrlEncoded
    @POST("/user/active_device")
    Observable<HttpResult> activateDevice(@FieldMap Map<String, Object> data);


    /**
     * 获取专家课程列表
     *
     * @param data
     * @return
     */
    @GET("/expert/expert_course")
    Observable<HttpResult<NewExpertCourseBean>> getExpertCourse(@QueryMap Map<String, Object> data);

    /**
     * 3.1获取家庭标签列表√
     *
     * @param data
     * @return
     */
    @GET("/group/get_my_family_tag_list")
    Observable<HttpResult<NewFamilyTag>> getFamilyTag(@QueryMap Map<String, Object> data);

    /**
     * 3.5创建群组（群聊）√
     *
     * @param data
     * @return
     */
    @GET("/group/create_group")
    Observable<HttpResult<NewGroupBean>> createGroup(@QueryMap Map<String, Object> data);

    /**
     * 3.6加入群组/家庭√
     *
     * @param data
     * @return
     */
    @GET("/group/join_family_or_group")
    Observable<HttpResult> joinGroup(@QueryMap Map<String, Object> data);

    /**
     * 添加好友请求
     */
    @GET("/friend/apply_friend")
    Observable<HttpResult> applyFriendRequest(@QueryMap Map<String, String> data);

    /**
     * 3.2获取家庭列表根据标签√
     *
     * @param data
     * @return
     */
    @GET("/group/get_family_list_by_tag")
    Observable<HttpResult<NewFamilyList>> getFamilyListToTag(@QueryMap Map<String, Object> data);

    /**
     * 3.7从群组/家庭移除成员√
     *
     * @param data
     * @return
     */
    @GET("/group/remove_group_member")
    Observable<HttpResult> dropOutGroup(@QueryMap Map<String, Object> data);

    /**
     * 3.8获取【家庭/群组】成员列表√
     *
     * @param data
     * @return
     */
    @GET("/group/get_group_member_list")
    Observable<HttpResult<NewGroupMemberBean>> getGroupMemberList(@QueryMap Map<String, Object> data);

    /**
     * 3.9添加家庭为好友家庭√
     *
     * @param data
     * @return
     */
    @GET("/group/add_family_friend")
    Observable<HttpResult> addFamilyFriend(@QueryMap Map<String, Object> data);

    /**
     * 3.13退出群组或家庭√
     *
     * @param data
     * @return
     */
    @GET("/group/quit_group")
    Observable<HttpResult> quitGroup(@QueryMap Map<String, Object> data);

    /**
     * 3.15获取附近的家庭列表√
     *
     * @param data
     * @return
     */
    @GET("/group/get_nearby_family")
    Observable<HttpResult<NewNearbyFamilyBean>> getNearbyFamily(@QueryMap Map<String, Object> data);

    /**
     * 6.6 获取孩子的愿望列表
     */
    @GET("/wish/get_child_wish_list")
    Observable<HttpResult<DesireListBean2>> getChildWish(@QueryMap Map<String, String> param);

    /**
     * 6.6 获取孩子的愿望列表,没有actionid 参数
     */
    @GET("/wish/get_child_wish_list")
    Observable<HttpResult<DesireListBean2>> getChildWishNoActionId();

    /**
     * 6.7帮孩子实现愿望
     */
    @GET("/wish/help_child_realize_wish")
    Observable<HttpResult> helpRealizeWish(@QueryMap Map<String, String> param);

    /**
     * 2.9获取好友资料
     */
    @GET("/friend/get_user_info")
    Observable<HttpResult<FriendInfoBean>> getFriendInfo(@QueryMap Map<String, String> param);

    /**
     * 获取请求添加好友的列表
     *
     * @return
     */
    @GET("/friend/get_user_friend_request_list")
    Observable<HttpResult<RequestAddFriendBean>> getRequestAddFriendList();

    /**
     * 2.5同意与拒绝好友申请
     *
     * @param param
     * @return
     */
    @GET("/friend/handle_friend_request")
    Observable<HttpResult> handleFriendRequest(@QueryMap Map<String, String> param);

    /**
     * 2.6删除联系人
     */
    @GET("/friend/remove_friend")
    Observable<HttpResult> removeFriend(@QueryMap Map<String, String> param);

    /**
     * 3.16获取附近的群组列表√
     *
     * @return
     */
    @GET("/group/get_nearby_group")
    Observable<HttpResult<NewNearbyGroup>> getNearbyGroup();

    /**
     * 3.17获取附近的用户列表√
     *
     * @return
     */
    @GET("/group/get_nearby_user")
    Observable<HttpResult<NewNearbyUser>> getNearbyUser();

    /**
     * 3.18获取推荐好友列表
     *
     * @return
     */
    @GET("/group/get_recommend_user")
    Observable<HttpResult<NewRecommendUser>> getRecommendUser(@QueryMap Map<String, Object> data);

    /**
     * 3.14获取群组或家庭的资料√
     *
     * @return
     */
    @GET("/group/get_family_or_group_detail")
    Observable<HttpResult<NewFamilyOrGroupDetail>> getFamilyOrGroupDetail(@QueryMap Map<String, Object> data);

    /**
     * 获取首页任务，自己和孩子的任务
     *
     * @return
     */
    @GET("/action/get_task_list")
    Observable<HttpResult<TaskListBean>> getTaskList();

    /**
     * 获得愿望、目标、规划、计划、活动日程统计
     *
     * @return
     */
    @GET("/user/count_user_item")
    Observable<HttpResult<UserAcountSizeBean>> getUserAcountSize();

    /**
     * 1.2 获取发现的活动类别
     */
    @GET("/action/get_action_kind")
    Observable<HttpResult<FindClassifyBean>> getFindActivityType(@QueryMap Map<String, String> param);

    /**
     * 1.3 获取推荐列表
     */
    @GET("/action/get_recommend_action_list")
    Observable<HttpResult<FindRecommendBean>> getFindRecommend(@QueryMap Map<String, String> param);

    /**
     * 1.4 点赞活动
     */
    @GET("/action/thumb_up_action")
    Observable<HttpResult<ClickLikeBean>> clickLike(@QueryMap Map<String, String> param);

    /**
     * 根据活动类别获取活动列表
     */
    @GET("/action/get_action_by_actionKind_list")
    Observable<HttpResult<FindRecommendBean>> getActionByActionKind(@QueryMap Map<String, String> param);

    /**
     * 用户的收藏列表
     */
    @GET("/user/get_user_collect_list")
    Observable<HttpResult<UserCollectListBean>> getUserCollectionList();

    /**
     * 更改用户密码
     */
    @GET("/user/update_password")
    Observable<HttpResult> changePassword(@QueryMap Map<String, String> param);

    /**
     * 1.15忘记密码√
     */
    @GET("/user/reset_password")
    Observable<HttpResult> resetPassword(@QueryMap Map<String, String> param);

    /**
     * 获取用户和用户孩子列表√
     */
    @GET("/user/get_user_and_user_chirld")
    Observable<HttpResult<ExecutePeopleBean>> getUserAndChild();


    /**
     * 检查Haplior设备
     *
     * @param param
     * @return
     */
    @GET("/user/check_device")
    Observable<HttpResult<DeviceMsgBean>> checkDevice(@QueryMap Map<String, String> param);


//    /**
//     * 获取活动流程数据列表
//     */
//    @GET("/action/get_action_flow_list"
//    Observable<HttpResult<ActivityFlowBean>>  getActivityFlowList(@QueryMap Map<String,String> param);
    /**
     * 获取活动流程数据列表2
     */
    @GET("/action/get_action_flow_node_data_list")
    Observable<HttpResult<ActivityFlowBean>> getActivityFlowList(@QueryMap Map<String, String> param);

    /**
     * 获取行为列表数据
     */
    @GET("action/get_user_flow_data")
    Observable<HttpResult<ActivityBehaviorBean>> getActivityBehavior(@QueryMap Map<String, String> param);

    /**
     * 评价活动行为
     */
    @GET("/action/evaluate_behavior")
    Observable<HttpResult> CommentActivityBehavior(@QueryMap Map<String, String> param);

    /**
     * 检验用户是否有绑定设备
     */
    @GET("/wish/get_device_count")
    Observable<HttpResult<IsBindDeviceBean>> getDeviceCount();


    /**
     * 绑定设备
     *
     * @param param
     * @return
     */
    @GET("/user/relate_device")
    Observable<HttpResult> bindDevice(@QueryMap Map<String, Object> param);

    /**
     * 预览视频(获取视频地址)
     */
    @GET("/course/preview_course")
    Observable<HttpResult<CatalogueVideoBean>> getCatalogueVideoUrl(@QueryMap Map<String, String> param);

    /**
     * 获取离线消息
     *
     * @return
     */
    @GET("/chat_msg/get_not_receive_msg")
    Observable<OfflineMessage> getOfflineMessageList();

    /**
     * 改变消息的接收状态
     *
     * @return
     */
    @GET("/chat_msgupdate_recvState")
    Observable<HttpResult> changeMessageReceiveState(@Query("ids") String ids);

    /**
     * 3.21修改群名称√
     */
    @GET("/group/update_group_name")
    Observable<HttpResult> updateGroupName(@QueryMap Map<String, String> param);

    /**
     * 获取活动流程图(html页面)
     */
    @GET("/action/action_flow_view")
    Observable<HttpResult> getActionFlowHtml(@QueryMap Map<String, String> param);

    /**
     * 获取活动未发放的奖品列表
     */
    @GET("/action/get_award_list")
    Observable<HttpResult<ActivityAwardBean>> getAwardList(@QueryMap Map<String,String> param);

    /**
     * 发放活动奖励
     */
    @GET("/action/grant_award")
    Observable<HttpResult> grantActivityAward(@QueryMap Map<String,String> param);

    /**
     * 获取抢答活动试卷内容(题库)
     */
    @GET("/contest/get_paper")
    Observable<HttpResult<Paper>> getPaper(@Query("actionId") int actionId);

    /**
     * 获取参加活动人员列表(包含观战人员)
     */
    @GET("/contest/get_room_user_list")
    Observable<HttpResult<JoinMember>> getJoinMemberList(@Query("actionId") int actionId);
}
