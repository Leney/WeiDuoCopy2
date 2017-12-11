package com.zhongke.weiduo.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${xingen} on 2017/9/25.
 */

public class ActivityDetailBean   {

    public List<DetailBean>  dataList;
    public static class  DetailBean{
        public String name;

        public List<DetailChildbean> childList;
    }

    public static ActivityDetailBean createInstance(){
        ActivityDetailBean bean=new ActivityDetailBean();
        bean.dataList = new ArrayList<>();
        for (int i=0;i<7;++i){
            DetailBean detailBean=new DetailBean();
            detailBean.childList = new ArrayList<>();
            switch (i){
                case 0:
                    detailBean.name="关键行为";
                    detailBean.childList.add(new DetailChildbean.Behaviour());
                    break;
                case 1:
                    detailBean.name="活动资料";
                    detailBean.childList.add(new DetailChildbean.Data());
                    break;
                case 2:
                    detailBean.name="活动流程";
                    detailBean.childList.add(new DetailChildbean.Process());
                    break;
                case 3:
                    detailBean.name="活动奖励";
                    detailBean.childList.add(new DetailChildbean.Reward());
                    break;
                case 4:
                    detailBean.name="活动道具";
                    detailBean.childList.add(new DetailChildbean.Props());
                    break;
                case 5:
                    detailBean.name="人员邀请";
                    detailBean.childList.add(new DetailChildbean.Invite());
                    break;
                case 6:
                    detailBean.name="活动评论";
                    for (int j = 0;j < 2;j++) {
                        detailBean.childList.add(new DetailChildbean.Comment());
                    }
                    break;
                default:
                    break;
            }
            bean.dataList.add(detailBean);
        }
        return bean;
    }
}
