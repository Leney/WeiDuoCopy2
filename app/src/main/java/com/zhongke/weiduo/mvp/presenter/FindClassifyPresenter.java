package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.FindClassifyContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.ClickLikeBean;
import com.zhongke.weiduo.mvp.model.entity.FindRecommendBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * 登录
 * Created by llj on 2017/6/20.
 */

public class FindClassifyPresenter extends BasePresenter {
    private static final String TAG = "FindClassifyPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private FindClassifyContract mContract;

    public FindClassifyPresenter(Context context, DataManager dataManager, FindClassifyContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

//    /**
//     * 获取相关数据
//     * @param id 类别id
//     */
//    public void getInfo(int id){
//        List<FindActBean> beanList = new ArrayList<>();
//
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
//        mContract.getActListSuccess(beanList);
//    }
    /**
     * 获取相关数据
     * @param id 类别id
     */
    public void getInfo(String id) {
        Map<String,String> map = new HashMap<>();
        map.put("actionKindId",id);
        Subscription subscription = retrofitClient.getActionByActionKind(map, new ResponseResultListener<FindRecommendBean>() {
            @Override
            public void success(FindRecommendBean findRecommendBean) {
                LogUtil.e("通过类别获取活动列表 success---"+findRecommendBean.getRecords().toString());
                mContract.getActListSuccess(findRecommendBean);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("通过类别获取活动列表 failure---");
                mContract.getActListFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

    public void clickLike(String actionId) {
        Map<String,String> map = new HashMap<>();
        map.put("actionId",actionId);

        Subscription subscription = retrofitClient.clickLike(map, new ResponseResultListener<ClickLikeBean>() {
            @Override
            public void success(ClickLikeBean clickLikeBean) {
                LogUtil.e("success---");
                mContract.clickLikeSuccess();
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("failure---"+e.getErrorMsg());
                mContract.clickLikeFailed();
            }
        });
        compositeSubscription.add(subscription);
    }
}
