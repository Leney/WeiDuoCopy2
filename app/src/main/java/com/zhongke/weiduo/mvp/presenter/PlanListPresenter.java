package com.zhongke.weiduo.mvp.presenter;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.PlanListContract;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.model.entity.PlanBean2;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${tanlei} on 2017/9/18.
 */

public class PlanListPresenter extends BasePresenter {
    private PlanListContract mContract;
    private DataManager dataManager;

    public PlanListPresenter(PlanListContract mContract, DataManager dataManager) {
        this.mContract = mContract;
        this.dataManager = dataManager;
    }

    public void getList(int pageIndex, int pageSize){
//        List<PlanBean> list = new ArrayList<>();
//        list.add(new PlanBean(1, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505827611244&di=773ae380d6c9f44e15869bd027cb6671&imgtype=0&src=http%3A%2F%2Fwww.istorcam.com%2Fupfile%2F201612511142757058189.png", "计划名称1", 0, "(1)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(2, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506422342&di=7ef817fee8dfdd9bdea2eab33c88344e&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F018ddf58d31e8fa801219c773572fc.jpg", "计划名称2", 100, "(2)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(3, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505827649105&di=3789f53bc038374e729b42d56e206a0b&imgtype=0&src=http%3A%2F%2Fpic65.nipic.com%2Ffile%2F20150504%2F7434096_085820141000_2.jpg", "计划名称3", 0, "(3)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(4, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505827661913&di=003e06a07e142f7f71a67dfa131254dd&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F014a2456df713e6ac72531cb593189.jpg%40900w_1l_2o_100sh.jpg", "计划名称4", 30, "(4)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(5, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505827674554&di=16398217bf7771152e94c1cce3725321&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01cdbc56f9cebf32f875a9449e6dc3.png%40900w_1l_2o_100sh.jpg", "计划名称5", 250, "(5)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(6, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505827686435&di=c89ede4967bc25d89fa871cc34deb7bc&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F43%2Fae1b637b71183d8e199c7c06046f3257.jpg", "计划名称6", 0, "(6)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(7, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505827703252&di=87520c75554ff0cfe0b4dcb602842b63&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F16%2F77%2F62%2F86M58PICqde_1024.jpg", "计划名称7", 0, "(7)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(8, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1506422465&di=f77a95ad3cb396f129e6432192994176&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01318a5544371a0000019ae9c664eb.jpg", "计划名称8", 0, "(8)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(9, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505827753183&di=8f2084cb55e1643d3cdb606ea3abc0a3&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f48b576504770000012e7eabf1cb.jpg", "计划名称9", 0, "(9)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(10, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505827760496&di=51606462a6902d6bea8b19956d2c3b9b&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c2895542c7790000019ae9df5d62.jpg", "计划名称10", 0, "(10)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(11, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505827772610&di=9e827e6a0f148253cfec513035c592d2&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0182425544614b0000019ae94a494b.jpg%40900w_1l_2o_100sh.jpg", "计划名称11", 0, "(11)小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        list.add(new PlanBean(12, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2732494967,2252780584&fm=27&gp=0.jpg", "小学一年级最重要", 0, "小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要小学一年级最重要", 93, 193));
//        mContract.getListSuccess(list);

        Map<String, Object> params = new HashMap<>();
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);

        Subscription subscription = retrofitClient.getPlanList(params, new ResponseResultListener<PlanBean2>() {
            @Override
            public void success(PlanBean2 planBeen) {
                mContract.getListSuccess(planBeen);
            }

            @Override
            public void failure(CommonException e) {
                mContract.getListFailed(e);
            }
        });
        compositeSubscription.add(subscription);
    }

    public void clickLike(int bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("bookType", 3);//2=规划，3=计划

        Subscription subscription = retrofitClient.likeSchemeOrPlan(params, null);
        compositeSubscription.add(subscription);
    }
}
