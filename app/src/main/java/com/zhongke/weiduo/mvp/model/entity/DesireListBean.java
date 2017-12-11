package com.zhongke.weiduo.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${xingen} on 2017/9/21.
 */

public class DesireListBean {
    public List<DesireBean> dataList;
    public static  class  DesireBean{
        public String url;
    }
    public  static  DesireListBean createInstance(){
        DesireListBean desireListBean=new DesireListBean();
        desireListBean.dataList=new ArrayList<>();
        for (int i=0;i<5;++i){
            DesireBean desireBean=new DesireBean();
            desireBean.url="http://img.ivsky.com/img/tupian/pre/201708/09/shangke-003.jpg";
            desireListBean.dataList.add(desireBean);
        }
        return  desireListBean;
    }

}
