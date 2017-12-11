package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MyCollectionContract;
import com.zhongke.weiduo.mvp.model.entity.UserCollectItem;
import com.zhongke.weiduo.mvp.model.entity.UserCollectListBean;
import com.zhongke.weiduo.mvp.presenter.MyCollectionPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.CollectionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Karma on 2017/9/19.
 * 我的收藏界面
 */
public class MyCollectionActivity extends BaseMvpActivity implements MyCollectionContract {

    @Bind(R.id.collection_recyclerview)
    RecyclerView collectionRlv;
    private MyCollectionPresenter presenter;
    private List<UserCollectItem> collectList;
    private CollectionAdapter collectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_my_collection);
        ButterKnife.bind(this);

        collectList = new ArrayList<>();
        initView();
        baseTitle.setTitleName("收藏");
        presenter.getUserCollection();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        collectionRlv.setLayoutManager(layoutManager);
        collectionAdapter = new CollectionAdapter(this,collectList);
        collectionRlv.setAdapter(collectionAdapter);
    }

    @Override
    protected BasePresenter createPresenter() {
        presenter = new MyCollectionPresenter(MyCollectionActivity.this,this);
        return presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,MyCollectionActivity.class));
    }

    @Override
    public void getUserCollectionSuccess(UserCollectListBean bean, List<UserCollectItem> collects) {
        collectList.clear();
        collectList.addAll(collects);
        collectionAdapter.notifyDataSetChanged();
        showCenterView();
    }

    @Override
    public void getUserCollectionFailed(CommonException e) {
        showErrorView();
        UIUtils.showToast(e.getErrorMsg());
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        presenter.getUserCollection();
    }
}
