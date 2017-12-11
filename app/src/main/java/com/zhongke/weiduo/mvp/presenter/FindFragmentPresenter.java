package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.FindFragmentContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.CarouselBitmapBean;
import com.zhongke.weiduo.mvp.model.entity.ClickLikeBean;
import com.zhongke.weiduo.mvp.model.entity.FindClassifyBean;
import com.zhongke.weiduo.mvp.model.entity.FindRecommendBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * 发现presenter
 * Created by ${llj} on 2017/6/27.
 */

public class FindFragmentPresenter extends BasePresenter{
    private FindFragmentContract findFragmentContract;
    private DataManager dataManager;

    public FindFragmentPresenter(FindFragmentContract findFragmentContract, DataManager dataManager) {
        this.findFragmentContract = findFragmentContract;
        this.dataManager = dataManager;
    }

    /**
     * 获取发现数据
     */
    public void getFindInfo(){
//        // 组banner数据
//        List<BannerInfo> bannerInfos = new ArrayList<>();
//        BannerInfo bannerInfo = new BannerInfo();
//        bannerInfo.id = 100;
//        bannerInfo.type = 0;
//        bannerInfo.icon = "http://img.taopic.com/uploads/allimg/100924/169-1009241401420.jpg";
//        bannerInfo.describle = "第一条banner";
//        bannerInfo.value = "http://www.dddf/sfsdf.html";
//
//        BannerInfo bannerInfo1 = new BannerInfo();
//        bannerInfo1.id = 101;
//        bannerInfo1.type = 1;
//        bannerInfo1.icon = "http://pic42.nipic.com/20140609/18702850_151330225000_2.jpg";
//        bannerInfo1.describle = "第二条banner";
//        bannerInfo1.value = "http://www.dddf/sfsdf.html";
//
//        BannerInfo bannerInfo2 = new BannerInfo();
//        bannerInfo2.id = 102;
//        bannerInfo2.type = 1;
//        bannerInfo2.icon = "http://pic102.nipic.com/file/20160628/23300679_201052027000_2.jpg";
//        bannerInfo2.describle = "第三条banner";
//        bannerInfo2.value = "http://www.dddf/sfsdf.html";
//
//        bannerInfos.add(bannerInfo);
//        bannerInfos.add(bannerInfo1);
//        bannerInfos.add(bannerInfo2);
//
//
//        // 组类别数据
//        List<ClassifyInfo> classifyInfos = new ArrayList<>();
//        ClassifyInfo classifyInfo = new ClassifyInfo();
//        classifyInfo.id = 1001;
//        classifyInfo.icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504534905884&di=3c84895c585f19f280158a5b5b0358ee&imgtype=0&src=http%3A%2F%2Fimg10.3lian.com%2Fshow2015%2F2%2F16%2F83.jpg";
//        classifyInfo.name = "类别1";
//
//        ClassifyInfo classifyInfo2 = new ClassifyInfo();
//        classifyInfo2.id = 1002;
//        classifyInfo2.icon = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1996274561,3454009669&fm=27&gp=0.jpg";
//        classifyInfo2.name = "类别2";
//
//        ClassifyInfo classifyInfo3 = new ClassifyInfo();
//        classifyInfo3.id = 1003;
//        classifyInfo3.icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504534850868&di=4cdd7d07470306c58b89df86b02ded56&imgtype=0&src=http%3A%2F%2Fimage.tupian114.com%2F20140522%2F09592009.png.thumb.jpg";
//        classifyInfo3.name = "类别3";
//
//        ClassifyInfo classifyInfo4 = new ClassifyInfo();
//        classifyInfo4.id = 1004;
//        classifyInfo4.icon = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3590298943,1108087221&fm=27&gp=0.jpg";
//        classifyInfo4.name = "类别4";
//
//        classifyInfos.add(classifyInfo);
//        classifyInfos.add(classifyInfo2);
//        classifyInfos.add(classifyInfo3);
//        classifyInfos.add(classifyInfo4);
//
//        // 组推荐活动数据
//        List<FindActBean> beanList = new ArrayList<>();
//        FindActBean bean = new FindActBean();
//        bean.id = 10001;
//        bean.icon = "http://img.aiimg.com/uploads/userup/0909/1G241352Q1.jpg";
//        bean.name = "活动名称(1)";
//        bean.date = "2017.09.01";
//        bean.address = "深圳市南山区科技园赋安大厦(1)";
//        bean.likeNum = 15251;
//        bean.commentNum = 2568;
//        bean.location = "369m";
//        bean.isLike = false;
//
//        FindActBean bean2 = new FindActBean();
//        bean2.id = 10002;
//        bean2.icon = "http://img0.imgtn.bdimg.com/it/u=2975314305,2681251099&fm=11&gp=0.jpg";
//        bean2.name = "活动名称(2)";
//        bean2.date = "2017.09.02";
//        bean2.address = "深圳市南山区科技园赋安大厦(2)";
//        bean2.likeNum = 15252;
//        bean2.commentNum = 2578;
//        bean2.location = "9km";
//        bean2.isLike = true;
//
//        FindActBean bean3 = new FindActBean();
//        bean3.id = 10003;
//        bean3.icon = "http://d.hiphotos.baidu.com/image/h%3D220/sign=23532216b63eb1355bc7b0b9961fa8cb/9f510fb30f2442a77a6af35bdb43ad4bd01302e3.jpg";
//        bean3.name = "活动名称(3)";
//        bean3.date = "2017.09.03";
//        bean3.address = "深圳市南山区科技园赋安大厦(3)";
//        bean3.likeNum = 152351;
//        bean3.commentNum = 252;
//        bean3.location = "39m";
//        bean3.isLike = true;
//
//        FindActBean bean4 = new FindActBean();
//        bean4.id = 10004;
//        bean4.icon = "http://pic51.nipic.com/file/20141023/2531170_115622554000_2.jpg";
//        bean4.name = "活动名称(4)";
//        bean4.date = "2017.09.04";
//        bean4.address = "深圳市南山区科技园赋安大厦(4)";
//        bean4.likeNum = 1524;
//        bean4.commentNum = 254;
//        bean4.location = "49km";
//        bean4.isLike = false;
//
//        FindActBean bean5 = new FindActBean();
//        bean5.id = 10005;
//        bean5.icon = "http://img.glzy8.com/upfiles/www/ppt/jpg/34063.jpg";
//        bean5.name = "活动名称(5)";
//        bean5.date = "2017.09.05";
//        bean5.address = "深圳市南山区科技园赋安大厦(5)";
//        bean5.likeNum = 15255;
//        bean5.commentNum = 255;
//        bean5.location = "3km";
//        bean5.isLike = false;
//
//        FindActBean bean6 = new FindActBean();
//        bean6.id = 10006;
//        bean6.icon = "http://www.86ps.com/imgWeb/psd/hf_et/ET_071.jpg";
//        bean6.name = "活动名称(6)";
//        bean6.date = "2017.09.06";
//        bean6.address = "深圳市南山区科技园赋安大厦(6)";
//        bean6.likeNum = 15652;
//        bean6.commentNum = 256;
//        bean6.location = "6km";
//        bean6.isLike = false;
//
//        FindActBean bean7 = new FindActBean();
//        bean7.id = 10007;
//        bean7.icon = "http://scimg.jb51.net/allimg/130523/2-1305231414312R.jpg";
//        bean7.name = "活动名称(7)";
//        bean7.date = "2017.09.07";
//        bean7.address = "深圳市南山区科技园赋安大厦(7)";
//        bean7.likeNum = 1527;
//        bean7.commentNum = 27;
//        bean7.location = "7m";
//        bean7.isLike = false;
//
//        FindActBean bean8 = new FindActBean();
//        bean8.id = 10008;
//        bean8.icon = "http://img2.imgtn.bdimg.com/it/u=443089188,1819036016&fm=200&gp=0.jpg";
//        bean8.name = "活动名称(8)";
//        bean8.date = "2017.09.08";
//        bean8.address = "深圳市南山区科技园赋安大厦(8)";
//        bean8.likeNum = 1528;
//        bean8.commentNum = 258;
//        bean8.location = "8km";
//        bean8.isLike = true;
//
//        beanList.add(bean);
//        beanList.add(bean2);
//        beanList.add(bean3);
//        beanList.add(bean4);
//        beanList.add(bean5);
//        beanList.add(bean6);
//        beanList.add(bean7);
//        beanList.add(bean8);
//
//        findFragmentContract.getFindInfoSuccess(bannerInfos,classifyInfos,beanList);
    }
    //获取发现的banner
    public void getBannerBitmap() {
        HashMap<String,String> map = new HashMap<>();
        //（1.微舵广告2.学堂广告3.专家广告4.课程广告5.机构 6.发现）
        map.put("bannerType","6");

        Subscription subscription = retrofitClient.getBannerBitmap(map, new ResponseResultListener<CarouselBitmapBean>() {
            @Override
            public void success(CarouselBitmapBean carouselBitmapBean) {
                LogUtil.e("发现banner success");
                findFragmentContract.getBannerBitmapSuccess(carouselBitmapBean);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("发现banner failure--"+e.getErrorMsg());

            }
        });
        compositeSubscription.add(subscription);
    }

    public void getActivityType() {
        Map<String,String> map = new HashMap<>();
        map.put("pageSize",100+"");
        Subscription subscription =  retrofitClient.getFindActivityType(map,new ResponseResultListener<FindClassifyBean>() {
            @Override
            public void success(FindClassifyBean findActivityTypeBean) {
                LogUtil.e("activity type success--");
                findFragmentContract.getActivityTypeSuccess(findActivityTypeBean);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("activity type failure--");
                findFragmentContract.getActivityTypeFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

    //获取推荐列表
    public void getRecommend(int pageIndex,int pageSize) {
        Map<String,String > map = new HashMap<>();
        map.put("pageIndex",pageIndex+"");
        map.put("pageSize",pageSize+"");
        Subscription subscription = retrofitClient.getFindRecommend(map,new ResponseResultListener<FindRecommendBean>() {
            @Override
            public void success(FindRecommendBean findRecommendBean) {
                LogUtil.e("获取推荐 success--");
//                LogUtil.e("findRecommendBean--"+findRecommendBean.getRecords().get(0).getThumbUpCount());
                findFragmentContract.getRecommendSuccess(findRecommendBean);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("获取推荐 failure--");
                findFragmentContract.getRecommendFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

    //点赞
    public void clickLike(String actionId) {
        Map<String,String> map = new HashMap<>();
        map.put("actionId",actionId+"");
        LogUtil.e("actionId---"+actionId);
        Subscription subscription = retrofitClient.clickLike(map, new ResponseResultListener<ClickLikeBean>() {
            @Override
            public void success(ClickLikeBean bean) {
                LogUtil.e("点赞 success--");
                LogUtil.e("bean---"+bean.getThumbUpCount());
                findFragmentContract.clickLikeSuccess();
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("点赞 failure--");
                findFragmentContract.clickLikeFailed();
            }
        });
        compositeSubscription.add(subscription);
    }

}
