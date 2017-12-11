package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolder;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.adapter.OnItemClickListener;
import com.lqr.recyclerview.LQRRecyclerView;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.model.entity.NewOrganizationCourseBean;
import com.zhongke.weiduo.mvp.model.entity.NewOrganizationDetailBean;
import com.zhongke.weiduo.mvp.presenter.OrganizationDataAtPresent;
import com.zhongke.weiduo.mvp.ui.widget.AdvisoryDialog;
import com.zhongke.weiduo.util.StringUtils;

import java.util.List;

/**
 * Created by Karma on 2017/9/4.
 * 类描述：机构资料
 */

public class OrganizationDataActivity extends BaseMvpActivity implements View.OnClickListener, AdvisoryDialog.OnSubmitClickListener {
    private LQRAdapterForRecyclerView<NewOrganizationCourseBean.RecordsBean> adapter;
    //    private LQRAdapterForRecyclerView<IOrganizationDataComment> commentAdapter;
    private OrganizationDataAtPresent mPresenter;
    //    private List<IOrganizationDataComment> mCommentData = new ArrayList<>();
    private ImageView mOrDataImg;
    private TextView mOrDataName, tvCollection;
    private LQRRecyclerView mOrDataRvmsg;
    //    private LQRRecyclerView mOrCommentRvmsg;
    private RelativeLayout subscribe;
    private AdvisoryDialog advisoryDialog;
    private LinearLayout llCollection;
    private TextView mechanismInfo;
//    private NewOrganizationDetailBean newOrganizationDetailBean;
    /**
     * 改机构的收藏状态
     */
    private int collectionStatus;
    /**
     * 机构Id
     */
    private int orgId;
    /**
     * 机构名
     */
    private String name;
    private LinearLayout consultationLL;
    private TextView mechanismWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_organizationdata);
        showLoadingView();
        initData();
        initListener();
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new OrganizationDataAtPresent(this);
        return mPresenter;
    }

    public void initData() {
        orgId = getIntent().getIntExtra("orgId", 0);
        name = getIntent().getStringExtra("name");

        subscribe = (RelativeLayout) findViewById(R.id.organizationData_subscribe_layout);
//        mOrCommentRvmsg = (LQRRecyclerView) findViewById(R.id.or_comment_rvmsg);
        mOrDataRvmsg = (LQRRecyclerView) findViewById(R.id.or_data_rvmsg);
        mOrDataName = (TextView) findViewById(R.id.or_data_name);
        mOrDataImg = (ImageView) findViewById(R.id.or_data_img);
        consultationLL = (LinearLayout) findViewById(R.id.activity_organizationdata_ll_consultation);
        llCollection = (LinearLayout) findViewById(R.id.ll_collection);
        tvCollection = (TextView) llCollection.findViewById(R.id.tv_collection);
        mechanismInfo = (TextView) findViewById(R.id.mechanism_info);
        mechanismWebsite = (TextView) findViewById(R.id.mechanism_website);
//        IOrganizationDataComment iOrganizationDataComment = new IOrganizationDataComment();
//        mCommentData.add(iOrganizationDataComment);
//        mCommentData.add(iOrganizationDataComment);
//        mCommentData.add(iOrganizationDataComment);
//        mCommentData.add(iOrganizationDataComment);
        setTitleName(name);
        mPresenter.showOrganizationDetail(orgId);
        mPresenter.showOrganizationCourse(orgId);
    }

    public static void startActivity(Context context, int orgId, String name) {
        Intent intent = new Intent(context, OrganizationDataActivity.class);
        intent.putExtra("orgId", orgId);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    public void initListener() {
        subscribe.setOnClickListener(this);
        llCollection.setOnClickListener(this);
        consultationLL.setOnClickListener(this);
    }

    public void setData(NewOrganizationDetailBean newOrganizationDetailBean) {
//        this.newOrganizationDetailBean = newOrganizationDetailBean;
        mOrDataName.setText(newOrganizationDetailBean.getOrg().getFullName());
        collectionStatus = newOrganizationDetailBean.getOrg().getCollectId();
        if (newOrganizationDetailBean.getOrg().getInfo() != null) {
            mechanismInfo.setText(newOrganizationDetailBean.getOrg().getInfo() + "");
        }
        if (!StringUtils.isEmpty(newOrganizationDetailBean.getOrg().getUrl())) {
            mechanismWebsite.setText("机构网址：" + newOrganizationDetailBean.getOrg().getUrl());
        }
        setCollection();
        Glide.with(this).load(newOrganizationDetailBean.getOrg().getLogo()).into(mOrDataImg);
//        setCommentAdaoter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.organizationData_subscribe_layout:
                if (advisoryDialog == null) {
                    advisoryDialog = new AdvisoryDialog(this);
                }
                advisoryDialog.setOnSubmitClickListener(this);
                advisoryDialog.show();
                break;
            case R.id.ll_collection:
                if (collectionStatus == 0) {
                    mPresenter.collectionOrganization(orgId);
                } else {
                    mPresenter.cancelCollectionOrganization(orgId);
                }
                break;
            case R.id.activity_organizationdata_ll_consultation:
                SessionActivity2.startActivity(this, 1, orgId + "", name);
                break;
            default:
                break;
        }
    }

    /**
     * dialog的回调方法
     */
    @Override
    public void onSubmitClick(String phone, String text) {
        Toast.makeText(this, "提交成功" + phone + text, Toast.LENGTH_SHORT).show();
    }

    public void showCourse(List<NewOrganizationCourseBean.RecordsBean> mData) {
        showCenterView();
        if (adapter == null) {
            adapter = new LQRAdapterForRecyclerView<NewOrganizationCourseBean.RecordsBean>(this, mData, R.layout.item_or_class) {
                @Override
                public void convert(LQRViewHolderForRecyclerView helper, NewOrganizationCourseBean.RecordsBean item, int position) {
                    helper.setText(R.id.tv1, item.getCourseName());
                    Glide.with(OrganizationDataActivity.this).load(item.getCoverUrl()).into((ImageView) helper.getView(R.id.iv));
                }
            };
        }
        mOrDataRvmsg.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position) {
                CourseActivity.startActivity(OrganizationDataActivity.this, mData.get(position).getId());
            }
        });
    }

//    public void setCommentAdaoter() {
//        if (commentAdapter == null) {
//            commentAdapter = new LQRAdapterForRecyclerView<IOrganizationDataComment>(this, mCommentData, R.layout.item_or_comment) {
//                @Override
//                public void convert(LQRViewHolderForRecyclerView helper, IOrganizationDataComment item, int position) {
//
//                }
//            };
//        }
//        mOrCommentRvmsg.setAdapter(commentAdapter);
//    }

    public void setCollection() {
        if (collectionStatus == 0) {
            tvCollection.setText("收藏");
            collectionStatus = 1;
        } else {
            tvCollection.setText("取消收藏");
            collectionStatus = 0;
        }
    }
}
