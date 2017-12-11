package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActMembersContract;
import com.zhongke.weiduo.mvp.model.entity.MemberInfo;
import com.zhongke.weiduo.mvp.presenter.ActMembersPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.ActMemberAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 活动当前已参与人员列表界面
 * Created by llj on 2017/6/27.
 */

public class ActMembersActivity extends BaseMvpActivity implements ActMembersContract {
    private ActMembersPresenter mPresenter;

    @Bind(R.id.act_members_total_num)
    TextView totalNum;

    @Bind(R.id.act_members_cur_join_num)
    TextView curJoinNun;

    @Bind(R.id.act_members_gridView)
    GridView memberGridView;

    private ActMemberAdapter adapter;

    /**
     * 活动id
     */
    private int id;

    /**
     * 预计参与人员总数量
     */
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName(getResources().getString(R.string.join_members));

        id = getIntent().getIntExtra("id", -1);
        if (id <= -1) {
            finish();
        }

        setCenterLay(R.layout.activity_jon_act_members);

        total = getIntent().getIntExtra("totalNum", 0);

        ButterKnife.bind(this);
        showCenterView();
        init();

        // 网络获取当前参与人员列表信息
        mPresenter.getCurMembers();
    }

    private void init() {
        totalNum.setText(total + getResources().getString(R.string.people));
    }

    @Override
    public void getMembersSuccess(List<MemberInfo> memberInfoList) {
        curJoinNun.setText(memberInfoList.size() + getResources().getString(R.string.people));
        // 设置最后一个是"邀请好友"按钮
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setId(-1);
        memberInfo.setName(getResources().getString(R.string.invite_friend));
        memberInfoList.add(memberInfo);

        adapter = new ActMemberAdapter(memberInfoList);
        memberGridView.setAdapter(adapter);
    }

    @Override
    public void getMembersFailed(int errorCode, String msg) {
        showErrorView();
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new ActMembersPresenter(this, mDataManager, this);
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public static void startActivity(Context context, int totalNum, int id) {
        Intent intent = new Intent(context, ActMembersActivity.class);
        intent.putExtra("totalNum", totalNum);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}
