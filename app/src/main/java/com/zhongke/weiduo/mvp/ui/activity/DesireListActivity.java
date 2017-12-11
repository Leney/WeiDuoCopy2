package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lqr.audio.AudioPlayManager;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.DesireListContract;
import com.zhongke.weiduo.mvp.model.entity.DesireListBean2;
import com.zhongke.weiduo.mvp.presenter.DesireListPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.DesireListAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ${xingen} on 2017/9/21.
 * <p>
 * 愿望列表界面
 */

public class DesireListActivity extends BaseMvpActivity implements DesireListContract, View.OnClickListener {
    private DesireListPresenter desireListPresenter;

    private TextView sureBtn;
    private RecyclerView recyclerView;
    private DesireListAdapter desireListAdapter;
    /**
     * 要推荐的活动id
     */
    private int actId;

    private int checkedCount;
    private List<DesireListBean2.ChildWishBean> desireList;
    private List<DesireListBean2.ChildWishBean> checkList;
    private StringBuilder userIdBuild;
    private StringBuilder wishIdBuild;
    private String actionName;
    private String beginTime;
    private String endTime;

    @Override
    protected BasePresenter createPresenter() {
        this.desireListPresenter = new DesireListPresenter(this);
        return desireListPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.actvitiy_desire_list);
        getIntentData();
        desireList = new ArrayList<>();
        checkList = new ArrayList<>();
        userIdBuild = new StringBuilder();
        wishIdBuild = new StringBuilder();

        if (actId!=-1) {
            desireListPresenter.getChildWish(actId);
        } else {
            desireListPresenter.getChildWishNoActionId();
        }
        initView();
    }

    private void getIntentData() {
        actId = getIntent().getIntExtra("actId", -1);
        actionName = getIntent().getStringExtra("actionName");
        beginTime = getIntent().getStringExtra("beginTime");
        endTime = getIntent().getStringExtra("endTime");
    }

    private void initView() {

        this.sureBtn = (TextView) findViewById(R.id.desire_sure_btn);
        this.sureBtn.setOnClickListener(this);

        if (actId >= 0) {
            this.sureBtn.setVisibility(View.VISIBLE);
            this.baseTitle.setTitleName("孩子的愿望");
//            this.baseTitle.setLeftImgVisible(false);
        } else {
            this.sureBtn.setVisibility(View.GONE);
            this.baseTitle.setTitleName("愿望");
        }


        this.recyclerView = (RecyclerView) findViewById(R.id.desire_list_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.desireListAdapter = new DesireListAdapter(this,R.layout.item_desire_view, desireList);
        this.desireListAdapter.setRecommend(actId >= 0);
        this.recyclerView.setAdapter(this.desireListAdapter);
        this.desireListAdapter.isFirstOnly(false);
        this.desireListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        this.desireListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.desire_choose_good_tv:
                    Toast.makeText(DesireListActivity.this.getApplicationContext(), "点击推举商品", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.desire_check_tv:
                    CheckBox checkBox = (CheckBox) view;
                    if (checkBox.isChecked()) {
                        checkedCount++;
                        checkList.add(desireList.get(position));
                    } else {
                        checkedCount--;
                        checkList.remove(desireList.get(position));
                    }
                    break;
                default:

                    break;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.desire_sure_btn:
                if (actId >= 0) {
//                    if(checkedCount > 0){
                    //TODO 获取到勾选的愿望清单，推荐活动

                    userIdBuild.reverse();
                    wishIdBuild.reverse();
                    if (checkList.size() > 0) {
                        for (int i =0,size = checkList.size();i<size;i++) {
                            DesireListBean2.ChildWishBean bean = checkList.get(i);
                            if (i != size-1) {
                                userIdBuild.append(bean.getUserId()).append(",");
                                wishIdBuild.append(bean.getId()).append(",");
                            } else {
                                userIdBuild.append(bean.getUserId());
                                wishIdBuild.append(bean.getId());
                            }
                        }
                        desireListPresenter.helpRealizeWish(actId+"",userIdBuild.toString(),wishIdBuild.toString(),actionName,beginTime,endTime);
                    } else {
                        UIUtils.showToast("请选择一个愿望");
                    }
//                    }else {
//                        Toast.makeText(this, "请勾选孩子的愿望！", Toast.LENGTH_SHORT).show();
//                    }
                } else {
//                    // 跳转到活动列表
//                    MovableListActivity.startActivityForResult(DesireListActivity.this);
//                    finish();
                }
                break;

            case R.id.base_title_right:
                desireListPresenter.directlyRecommended(actId+"",actionName,beginTime,endTime);
                break;
            default:

                break;
        }
    }


    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DesireListActivity.class);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param actId   需要推荐的活动id，-1 = 不推荐任何活动id，是直接进入到愿望列表的
     */
    public static void startActivity(Context context, int actId,String actionName,String beginTime,String endTime) {
        Intent intent = new Intent(context, DesireListActivity.class);
        // 是否是通过推荐活动跳转到此界面的
        intent.putExtra("actId", actId);
        intent.putExtra("actionName",actionName);
        intent.putExtra("beginTime",beginTime);
        intent.putExtra("endTime",endTime);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       if (AudioPlayManager.getInstance().isRecordPlay()){
           AudioPlayManager.getInstance().stopPlay();
       }
    }

    //获取孩子的愿望
    @Override
    public void getChildWishSuccess(DesireListBean2 bean) {
        desireList.clear();
//        LogUtil.e("bean---" + bean.getChildWish().toString());
        desireList.addAll(bean.getChildWish());
        desireListAdapter.notifyDataSetChanged();

        if (!bean.getChildWish().isEmpty()) {
            this.showCenterView();
            Toast.makeText(DesireListActivity.this, "为孩子实现一个愿望吧", Toast.LENGTH_SHORT).show();
        } else {
            this.showNoDataView();
            if (actId != -1) {
                baseTitle.setRightVisible(true);
                baseTitle.setRightText("跳过");
                baseTitle.setRightOnClickListener(this);
            }
        }
    }

    @Override
    public void getChildWishFailed(CommonException e) {
        showErrorView();
        UIUtils.showToast(e.getErrorMsg());
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        if (actId!=-1) {
            desireListPresenter.getChildWish(actId);
        } else {
            desireListPresenter.getChildWishNoActionId();
        }
    }

    //帮助孩子实现愿望成功
    @Override
    public void helpRealizeWishSuccess() {
        Toast.makeText(this, "推荐成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void helpRealizeWishFailed(CommonException e) {
        UIUtils.showToast(e.getErrorMsg());
    }

    @Override
    public void directlyRecommendSuccess() {
        UIUtils.showToast("推荐成功");
        finish();
    }

    @Override
    public void directlyRecommendFailed(CommonException e) {
        UIUtils.showToast(e.getErrorMsg());
    }

}
