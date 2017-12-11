package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.TeachContentContract;
import com.zhongke.weiduo.mvp.model.entity.TeachContentBean;
import com.zhongke.weiduo.mvp.presenter.TeachContentPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.TeachContentAdapter;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/19.
 * 教学内容界面
 */

public class TeachContentActivity extends BaseMvpActivity implements TeachContentContract, View.OnClickListener {
    private TeachContentPresenter teachContentPresenter;
    /**
     * 展现数据的ExpandableListView
     */
    private ExpandableListView lvTeach;
    /**
     * ExpandableListView的适配器
     */
    private TeachContentAdapter teachContentAdapter;
    /**
     * 本地添加课程的按钮
     */
    private TextView tvAdd;

    @Override
    protected BasePresenter createPresenter() {
        teachContentPresenter = new TeachContentPresenter(this, mDataManager);
        return teachContentPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_teach_content);
        showCenterView();
        init();
        setListener();
    }

    private void setListener() {
        tvAdd.setOnClickListener(this);
    }

    public void init() {
        setTitleName(getString(R.string.teach_content));
        tvAdd = (TextView) findViewById(R.id.tv_add);
        lvTeach = (ExpandableListView) findViewById(R.id.lv_teach_content);
        teachContentPresenter.showTeachContentData();
    }

    @Override
    public void deleteData(List<TeachContentBean> teachContentBeanList) {
        teachContentAdapter.myNotifyDataSetChanged(teachContentBeanList);
    }

    @Override
    public void showData(List<TeachContentBean> teachContentBeanList) {
        teachContentAdapter = new TeachContentAdapter(teachContentBeanList, this);
        lvTeach.setAdapter(teachContentAdapter);
        for (int i = 0; i < teachContentBeanList.size(); i++) {
            lvTeach.expandGroup(i);
        }
        lvTeach.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_add) {
            AddTeachContentActivity.startActivity(this);
        }
    }

    public static void startActivity(Context context/*, int stepId, String stepName*/) {
        Intent intent = new Intent(context, TeachContentActivity.class);
//        intent.putExtra("stepId", stepId);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }
}
