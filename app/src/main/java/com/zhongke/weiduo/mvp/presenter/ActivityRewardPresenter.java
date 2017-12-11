package com.zhongke.weiduo.mvp.presenter;

import android.util.Log;

import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActivityRewardContract;
import com.zhongke.weiduo.mvp.model.entity.ActivityRewardBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${xingen} on 2017/7/1.
 */

public class ActivityRewardPresenter extends BasePresenter {
   private ActivityRewardContract view;
   public ActivityRewardPresenter(ActivityRewardContract view){
       this.view=view;
   }
   public void loadData(){
       List<ActivityRewardBean.AwardListBean>  list=new ArrayList<>();
       for (int i=0;i<3;++i){
           ActivityRewardBean.AwardListBean awardListBean=new ActivityRewardBean.AwardListBean();

           switch (i){
               case 0:
                   awardListBean.setName("五星奖励");
                   break;
               case 1:
                   awardListBean.setName("四星奖励");
                   break;
               case 2:
                   awardListBean.setName("三星奖励");
                   break;
           }
           List<ActivityRewardBean.AwardListBean.ListBean> list1=new ArrayList<>();
           list1.add(new ActivityRewardBean.AwardListBean.ListBean());
           list1.add(new ActivityRewardBean.AwardListBean.ListBean());

           awardListBean.setList(list1);

           list.add(awardListBean);
       }
       Log.i(ActivityRewardPresenter.class.getSimpleName(),"个数 "+list.size());
       this.view.loadRewardList(list);
   }
}
