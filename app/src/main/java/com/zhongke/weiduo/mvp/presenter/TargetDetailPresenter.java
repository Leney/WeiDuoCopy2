package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.TargetDetailContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.RecommendSchemeBean;
import com.zhongke.weiduo.mvp.model.entity.TargetDetailBean2;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * 登录
 * Created by llj on 2017/6/20.
 */

public class TargetDetailPresenter extends BasePresenter {
    private static final String TAG = "TargetDetailPresenter";
    private Context mContext;
    private DataManager mDataManager;
    private TargetDetailContract mContract;

    public TargetDetailPresenter(Context context, DataManager dataManager, TargetDetailContract contract) {
        this.mContext = context;
        this.mDataManager = dataManager;
        this.mContract = contract;
    }

    /**
     * 获取相关数据
     */
    public void getDetailInfo(int id) {
//        TargetDetailBean detailBean = new TargetDetailBean();
//        detailBean.setId(10001);
//        detailBean.setBelongId(10010);
//        detailBean.setName("乐于学习养成好习惯");
//        detailBean.setSimpleDes("帮助学生在健康的现代家庭教育制度下逐步摆脱幼小衔接的不适应现象，顺利适应小学的学习、生活，乐于学习养成好习惯");
//        detailBean.setDetailDes("1.建立良性、健康的家庭与学校的沟通联系平台，提高家长素养，优化家庭环境\n\n2、成立家长委员会，创办“家长总动员”的家校共同参与平台，组织多种活动，形成集体的凝聚力\n\n3、充分利用现代化的通讯手段进行互通交流，促进亲子共读，培养良好习惯\n\n4、定期召开家长交流会，家访及家长进校园等面对面的交流探讨活动");
//        detailBean.setPrice(0);
//        detailBean.setVideoUrl("http://baobab.wdjcdn.com/14564977406580.mp4");
//        detailBean.setIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505900485083&di=3cd381196f45546b69099833d7232e05&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01136b575cb5700000012e7e7fbb81.jpg%40900w_1l_2o_100sh.jpg");
//        ExpertBean expertBean = new ExpertBean();
//        expertBean.setId(101);
//        expertBean.setName("王专家");
//        detailBean.setExpertBean(expertBean);
//
//        List<SchemeDetailBean> recommendList = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            SchemeDetailBean recommendBean = new SchemeDetailBean();
//            recommendBean.setId((10001 + i));
//            recommendBean.setBelongId(10010);
//
//            recommendBean.setSimpleDes("规划案简单描述规划案简单描述规划案简单描述规划案简单描述规划案简单描述规划案简单描述规划案简单描述规划案简单描述");
//            recommendBean.setDetailDes("规划案具体描述规划案具体描述规划案具体描述规划案具体描述规划案具体描述规划案具体描述规划案具体描述规划案具体描述");
//            recommendBean.setPrice(0);
//            recommendBean.setUseCount((566 + i));
//            detailBean.setVideoUrl("http://baobab.wdjcdn.com/14564977406580.mp4");
//            if (i == 0) {
//                recommendBean.setName("全面培养孩子3Q");
//                recommendBean.setIcon("http://img.zcool.cn/community/0175bd5935713da8012193a3471f8f.jpg@2o.jpg");
//            } else if (i == 1) {
//                recommendBean.setName("和孩子一起享受艺术");
//                recommendBean.setIcon("http://58pic.ooopic.com/58pic/21/58/64/00j58PICiyW.jpg");
//            } else if (i == 2) {
//                recommendBean.setName("放飞梦想、成就未来");
//                recommendBean.setIcon("http://www.dachengjiaoyu.com.cn/upimg/product/month_1501/201501051715278713.jpg");
//            } else if (i == 3) {
//                recommendBean.setName("学会感恩与快乐成长");
//                recommendBean.setIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509196454797&di=36ae129c1a4722bf0f4f1e5eda7a6881&imgtype=0&src=http%3A%2F%2Fmanage.360dkt.com%2FUpload%2Fusers%2F1%2FEditor%2F201205151739490621166.jpg");
//            }
//            ExpertBean recommendExpertBean = new ExpertBean();
//            recommendExpertBean.setId((101 + i));
//            recommendExpertBean.setName("王专家");
//            recommendBean.setExpertBean(recommendExpertBean);
//            recommendList.add(recommendBean);
//        }
//
//        mContract.getDetailSuccess(detailBean, recommendList);


        Map<String, Object> params = new HashMap<>();
        params.put("targetId", id);

        Subscription subscription = retrofitClient.getTargetDetail(params, new ResponseResultListener<TargetDetailBean2>() {
            @Override
            public void success(TargetDetailBean2 targetDetailBean2) {
                mContract.getDetailSuccess(targetDetailBean2);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("llj", "errorCode---->>>" + e.getCode() + "---errorMsg------>>>" + e.getErrorMsg());
                mContract.getDetailFailed(e);
            }
        });

        compositeSubscription.add(subscription);
    }


    /**
     * 获取推荐规划列表
     *
     * @param bookId
     */
    public void getRecommendList(int bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("targetId", bookId);

        Subscription subscription = retrofitClient.getTargetDetailRecommendList(params, new ResponseResultListener<RecommendSchemeBean>() {
            @Override
            public void success(RecommendSchemeBean recommendSchemeBean) {
                mContract.getRecommendListSuccess(recommendSchemeBean);
            }

            @Override
            public void failure(CommonException e) {
                mContract.getRecommendListFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

    /**
     * 添加目标
     *
     * @param targetId
     */
    public void addTarget(int targetId) {
        Map<String, Object> params = new HashMap<>();
        params.put("targetId", targetId);


        Subscription subscription = retrofitClient.addTarget(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                mContract.addTargetSuccess();
            }

            @Override
            public void failure(CommonException e) {
                mContract.addTargetFailed(e);
            }
        });

        compositeSubscription.add(subscription);
    }


}
