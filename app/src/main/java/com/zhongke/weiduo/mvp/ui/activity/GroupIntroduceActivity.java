package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.GroupIntroduceContract;
import com.zhongke.weiduo.mvp.model.entity.NewFamilyOrGroupDetail;
import com.zhongke.weiduo.mvp.presenter.GroupIntroducePresenter;
import com.zhongke.weiduo.mvp.ui.adapter.GroupAvatarAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.GroupLabelAdapter;
import com.zhongke.weiduo.mvp.ui.widget.WapGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hyx on 2017/10/10.
 */

public class GroupIntroduceActivity extends BaseMvpActivity implements View.OnClickListener, GroupIntroduceContract {

    @Bind(R.id.label_wapgridview)
    WapGridView labelGroup;
    @Bind(R.id.avatar_wapgridview)
    WapGridView avatarGroup;
    @Bind(R.id.group_arrow)
    ImageView backArrow;
    private ImageView groupPartening;
    private TextView tvJoinGroup, location, introduction;
    private GroupIntroducePresenter presenter;
    /**
     * 群Id
     */
    private int groupId;
    private List<String> labelStr = new ArrayList<>();
    private List<String> avatarArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //是否设置状态栏颜色
        isSetStatusColor = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_introduce);
        ButterKnife.bind(this);
        showLoadingView();
        initData();
        initView();
    }

    /**
     * 获取从上个界面传递过来的参数数据
     */
    private void initData() {
        groupId = getIntent().getIntExtra("groupId", 0);
    }

    private void initView() {
        location = (TextView) findViewById(R.id.text_location);
        introduction = (TextView) findViewById(R.id.introduction);
        tvJoinGroup = (TextView) findViewById(R.id.btn_join_apply);
        groupPartening = (ImageView) findViewById(R.id.group_partening);
        backArrow.setOnClickListener(this);
        tvJoinGroup.setOnClickListener(this);
        presenter.getGroupDetail(groupId);
    }

    @Override
    protected BasePresenter createPresenter() {
        presenter = new GroupIntroducePresenter(this);
        return presenter;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.group_arrow:
                finish();
                break;
            case R.id.btn_join_apply:
                presenter.requestJoinGroup(groupId);
                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context, int groupId) {
        Intent intent = new Intent(context, GroupIntroduceActivity.class);
        intent.putExtra("groupId", groupId);
        context.startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 加入群组成功
     */
    @Override
    public void joinGroupSuccess() {
        finish();
    }

    /**
     * 显示群组信息
     *
     * @param newFamilyOrGroupDetail
     */
    @Override
    public void showGroupDetail(NewFamilyOrGroupDetail newFamilyOrGroupDetail) {
        if (null != newFamilyOrGroupDetail) {
            showCenterView();
            location.setText(newFamilyOrGroupDetail.getFamilyGroup().getAddress());
            introduction.setText(newFamilyOrGroupDetail.getFamilyGroup().getGInfo());
            PhotoLoaderUtil.display(GroupIntroduceActivity.this, groupPartening, newFamilyOrGroupDetail.getFamilyGroup().getGCoverUrl(), R.drawable.ic_default_banner_large);
            String str = newFamilyOrGroupDetail.getFamilyGroup().getTagList();
            if (str != null) {
                labelStr = Arrays.asList(str.split(","));
            }
            GroupLabelAdapter labelAdapter = new GroupLabelAdapter(this, labelStr);
            labelGroup.setAdapter(labelAdapter);

            List<NewFamilyOrGroupDetail.MemberListBean> memberList = newFamilyOrGroupDetail.getMemberList();
            for (NewFamilyOrGroupDetail.MemberListBean memberListBean : memberList) {
                avatarArr.add(memberListBean.getHeadImageUrl());
            }
            GroupAvatarAdapter avatarAdapter = new GroupAvatarAdapter(this, avatarArr);
            avatarGroup.setAdapter(avatarAdapter);
        } else {
            showNoDataView();
        }
    }

    @Override
    public void showErrorViews() {
        showErrorView();
    }
}
