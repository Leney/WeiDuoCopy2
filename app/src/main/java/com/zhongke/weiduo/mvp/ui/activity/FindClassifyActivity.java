package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.FindClassifyContract;
import com.zhongke.weiduo.mvp.model.entity.ClassifyInfo;
import com.zhongke.weiduo.mvp.model.entity.FindRecommendBean;
import com.zhongke.weiduo.mvp.presenter.FindClassifyPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.FindActAdapter;
import com.zhongke.weiduo.mvp.ui.widget.view.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * 发现类别活动列表界面
 * Created by llj on 2017/9/5.
 */

public class FindClassifyActivity extends BaseMvpActivity implements FindClassifyContract, FindActAdapter.LikeClickListener {
    private FindClassifyPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private FindActAdapter mAdapter;

    private List<FindRecommendBean.RecordsBean> beanList;

    private ClassifyInfo classifyInfo;
    private String actionKindId;
    private String actionKindName;
    private int clickLikePosition;
    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new FindClassifyPresenter(FindClassifyActivity.this,mDataManager,this);
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionKindId = getIntent().getStringExtra("actionKindId");
        actionKindName = getIntent().getStringExtra("actionKindName");
        if(actionKindId == null||"".equals(actionKindId)){
            finish();
            return;
        }
        setTitleName(actionKindName);
        initView();
        // 获取类别下的活动列表数据
        LogUtil.e("actionKindId--"+actionKindId);
        mPresenter.getInfo(actionKindId);
    }

    private void initView(){
        beanList = new ArrayList<>();

        mRecyclerView = new RecyclerView(FindClassifyActivity.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FindClassifyActivity.this,LinearLayoutManager.VERTICAL,false));
        // 设置自定义的分割线
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(FindClassifyActivity.this,1));
        mAdapter = new FindActAdapter(FindClassifyActivity.this);
        mAdapter.setLikeClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        setCenterLay(mRecyclerView);
    }

    @Override
    public void getActListSuccess(FindRecommendBean bean) {
        beanList.addAll(bean.getRecords());
        mAdapter.addDatas(beanList);
        showCenterView();
    }

    @Override
    public void getActListFailed(CommonException e) {
        UIUtils.showToast(e.getErrorMsg());
    }

    public static void startActivity(Context context,String actionKindId,String actionKindName){
        Intent intent = new Intent(context,FindClassifyActivity.class);
        intent.putExtra("actionKindId",actionKindId);
        intent.putExtra("actionKindName",actionKindName);
        context.startActivity(intent);
    }

    @Override
    public void likeClick(int position, boolean hasHead) {
        if (hasHead) {
            clickLikePosition = position + 1;
        } else {
            clickLikePosition = position;
        }
        int actionId = beanList.get(position).getId();
        mPresenter.clickLike(actionId+"");
    }

    @Override
    public void clickLikeSuccess() {
        mAdapter.notifyItemChanged(clickLikePosition,"");
    }

    @Override
    public void clickLikeFailed() {

    }
}
