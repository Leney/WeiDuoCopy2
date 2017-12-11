package com.zhongke.weiduo.mvp.presenter;

import android.content.Context;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MyCollectionContract;
import com.zhongke.weiduo.mvp.model.entity.UserCollectItem;
import com.zhongke.weiduo.mvp.model.entity.UserCollectListBean;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by Karma on 2017/9/19.
 */

public class MyCollectionPresenter extends BasePresenter {

    private Context context;
    private MyCollectionContract contract;

    public MyCollectionPresenter(Context context, MyCollectionContract contract) {
        this.context = context;
        this.contract = contract;
    }

    public void getUserCollection() {
        Subscription subscription = retrofitClient.getUserCollectionList(new ResponseResultListener<UserCollectListBean>() {
            @Override
            public void success(UserCollectListBean userCollectBean) {
                LogUtil.e("获取用户收藏 success--");
                List<UserCollectItem> collects = dataTransFrom(userCollectBean.getRecords());
                contract.getUserCollectionSuccess(userCollectBean,collects);
            }

            @Override
            public void failure(CommonException e) {
                LogUtil.e("获取用户收藏 failure--");
                contract.getUserCollectionFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

    public List<UserCollectItem> dataTransFrom(List<UserCollectListBean.RecordsBean> list) {
        List<UserCollectItem> collects = new ArrayList<>();
        for (UserCollectListBean.RecordsBean recordsBean : list) {
            //collectType 1专家 2课程 3机构 4活动
            UserCollectItem collectItem = new UserCollectItem();
            if (recordsBean.getCollectType() == 1) {
                collectItem.id = recordsBean.getUserId();
                collectItem.ImageUrl = recordsBean.getHeadImageUrl();
                collectItem.title = recordsBean.getNickName();
            } else if(recordsBean.getCollectType() == 2) {
                collectItem.id = recordsBean.getCourseId();
                collectItem.ImageUrl = recordsBean.getCourseCoverUrl();
                collectItem.title = recordsBean.getCourseName();
            } else if(recordsBean.getCollectType() == 3) {
                collectItem.id = recordsBean.getOrgId();
                collectItem.ImageUrl = recordsBean.getLogo();
                collectItem.title = recordsBean.getOrgName();
            } else if(recordsBean.getCollectType() == 4) {
                collectItem.id = recordsBean.getActionId();
                collectItem.ImageUrl = recordsBean.getActionCoverUrl();
                collectItem.title = recordsBean.getTitle();
            }
            collectItem.time = recordsBean.getCreateTime();
            collects.add(collectItem);
        }
       return collects;
    }
}
