package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.StepDetailContract;
import com.zhongke.weiduo.mvp.model.entity.StepDetailBean;
import com.zhongke.weiduo.mvp.presenter.StepDetailPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 步骤详情界面
 * Created by llj on 2017/6/26.
 */

public class StepDetailActivity extends BaseMvpActivity implements StepDetailContract, View.OnClickListener {
    private StepDetailPresenter mPresenter;

    /**
     * 教学内容(组)
     */
    @Bind(R.id.step_detail_teach_content_group)
    TextView teachContentGroup;
    /**
     * 注意事项(组)
     */
    @Bind(R.id.step_detail_notes_content_group)
    TextView notesGroup;
    /**
     * 步骤内容(组)
     */
    @Bind(R.id.step_detail_step_content_group)
    TextView stepContentGroup;
    /**
     * 提示与交互(组)
     */
    @Bind(R.id.step_detail_prompt_mutually_group)
    TextView promptMutuallyGroup;
    /**
     * 监控与记录(组)
     */
    @Bind(R.id.step_detail_monitoring_record_group)
    TextView monitoringRecordGroup;
    /**
     * 考核要素(组)
     */
    @Bind(R.id.step_detail_assess_tips_group)
    TextView assessTipsGroup;
    /**
     * 奖励与惩罚(组)
     */
    @Bind(R.id.step_detail_award_punishment_group)
    TextView awardPunishmentGroup;


    /**
     * 注意事项显示控件
     */
    @Bind(R.id.step_detail_notes_content)
    TextView notes;
    /**
     * 步骤内容显示控件
     */
    @Bind(R.id.step_detail_step_content)
    TextView stepContent;

    /**
     * 教学内容显示item的区域
     */
    @Bind(R.id.step_detail_teach_content_item_lay)
    LinearLayout teachItemLay;
    /**
     * 提示与交互显示item的区域
     */
    @Bind(R.id.step_detail_prompt_mutually_item_lay)
    LinearLayout promptMutuallyItemLay;
    /**
     * 监控与记录显示item的区域
     */
    @Bind(R.id.step_detail_monitoring_record_item_lay)
    LinearLayout monitoringRecordItemLay;
    /**
     * 考核要素显示item的区域
     */
    @Bind(R.id.step_detail_assess_tips_item_lay)
    LinearLayout assessTipsItemLay;
    /**
     * 奖励与惩罚显示item的区域
     */
    @Bind(R.id.step_detail_award_punishment_item_lay)
    LinearLayout awardPunishmentItemLay;


    /**
     * 活动id
     */
    private int actId;
    /**
     * 步骤id
     */
    private int stepId;

    /**
     * 步骤名称
     */
    private String stepName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actId = getIntent().getIntExtra("actId",-1);
        stepId = getIntent().getIntExtra("stepId", -1);
        stepName = getIntent().getStringExtra("stepName");

        if (actId <= -1 || stepId <= -1) {
            finish();
        }

//        Tools.setStatusColor(this, getResources().getColor(R.color.edit_lay_bg));
        setTitleName(stepName);
//        baseTitle.setTitleBackground(R.color.edit_lay_bg);
//        baseTitle.setLeftImgRes(R.drawable.back_img_black);
//        baseTitle.setTitleNameColor(R.color.black_text_color);
        setCenterLay(R.layout.activity_step_detail);

        ButterKnife.bind(this);
        showCenterView();

        init();

        // 网络获取步骤详情数据
        mPresenter.getStepDetails();
    }

    private void init() {
        teachContentGroup.setOnClickListener(this);
        promptMutuallyGroup.setOnClickListener(this);
        monitoringRecordGroup.setOnClickListener(this);
        assessTipsGroup.setOnClickListener(this);
    }

    @Override
    public void getStepDetailSuccess(StepDetailBean detailBean) {

        notes.setText(detailBean.getNotes());
        stepContent.setText(detailBean.getStepContent());


        // 教学内容
        int size = detailBean.getTeachList().size();
        for (int i = 0; i < size; i++) {
            StepDetailBean.TeachListBean teachListBean = detailBean.getTeachList().get(i);
            View view = View.inflate(StepDetailActivity.this, R.layout.step_item_lay, null);
            TextView name = (TextView) view.findViewById(R.id.step_item_left_name);
            TextView value = (TextView) view.findViewById(R.id.step_item_right_value);
            name.setText(teachListBean.getName());
            value.setText(teachListBean.getValue());
            teachItemLay.addView(view);
            if (i < size - 1) {
                View splineView = new View(StepDetailActivity.this);
                splineView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.split_line_height)));
                splineView.setBackgroundResource(R.color.spline_color);
                teachItemLay.addView(splineView);
            }
        }

        // 提示与交互
        int size2 = detailBean.getTipList().size();
        for (int i = 0; i < size2; i++) {
            StepDetailBean.TipListBean tipListBean = detailBean.getTipList().get(i);
            View view = View.inflate(StepDetailActivity.this, R.layout.step_item_lay2, null);
            TextView name = (TextView) view.findViewById(R.id.step_item2_name);
            name.setText(tipListBean.getName());
            promptMutuallyItemLay.addView(view);
            if (i < size2 - 1) {
                View splineView = new View(StepDetailActivity.this);
                splineView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.split_line_height)));
                splineView.setBackgroundResource(R.color.spline_color);
                promptMutuallyItemLay.addView(splineView);
            }
        }

        // 监控与记录
        int size3 = detailBean.getRecordList().size();
        for (int i = 0; i < size3; i++) {
            StepDetailBean.RecordListBean recordListBean = detailBean.getRecordList().get(i);
            View view = View.inflate(StepDetailActivity.this, R.layout.step_item_lay, null);
            TextView name = (TextView) view.findViewById(R.id.step_item_left_name);
            TextView value = (TextView) view.findViewById(R.id.step_item_right_value);
            name.setText(recordListBean.getName());
            value.setText(recordListBean.getValue() == 0 ? getResources().getString(R.string.opened) : getResources().getString(R.string.closed));
            monitoringRecordItemLay.addView(view);
            if (i < size3 - 1) {
                View splineView = new View(StepDetailActivity.this);
                splineView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.split_line_height)));
                splineView.setBackgroundResource(R.color.spline_color);
                monitoringRecordItemLay.addView(splineView);
            }
        }

        // 考核要素
        int size4 = detailBean.getAssessList().size();
        for (int i = 0; i < size4; i++) {
            StepDetailBean.AssessListBean assessListBean = detailBean.getAssessList().get(i);
            View view = View.inflate(StepDetailActivity.this, R.layout.step_item_lay, null);
            TextView name = (TextView) view.findViewById(R.id.step_item_left_name);
            TextView value = (TextView) view.findViewById(R.id.step_item_right_value);
            name.setText(assessListBean.getName());
            value.setText(assessListBean.getValue());
            assessTipsItemLay.addView(view);
            if (i < size4 - 1) {
                View splineView = new View(StepDetailActivity.this);
                splineView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.split_line_height)));
                splineView.setBackgroundResource(R.color.spline_color);
                assessTipsItemLay.addView(splineView);
            }
        }

        // 奖励与惩罚
        int size5 = detailBean.getResultList().size();
        for (int i = 0; i < size5; i++) {
            StepDetailBean.ResultListBean resultListBean = detailBean.getResultList().get(i);
            View view = View.inflate(StepDetailActivity.this, R.layout.step_item_lay, null);
            TextView name = (TextView) view.findViewById(R.id.step_item_left_name);
            TextView value = (TextView) view.findViewById(R.id.step_item_right_value);
            name.setText(resultListBean.getName());
            value.setText(resultListBean.getValue());
            awardPunishmentItemLay.addView(view);
            if (i < size5 - 1) {
                View splineView = new View(StepDetailActivity.this);
                splineView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.split_line_height)));
                splineView.setBackgroundResource(R.color.spline_color);
                awardPunishmentItemLay.addView(splineView);
            }
        }


    }

    @Override
    public void getStepDetailFailed(int errorCode, String msg) {

    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new StepDetailPresenter(this, mDataManager, this);
        return mPresenter;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.step_detail_teach_content_group:
                /** 教学内容(组)*/
                TeachContentActivity.startActivity(this);
                break;
            case R.id.step_detail_prompt_mutually_group:
                /** 提示与交互(组)*/
                break;
            case R.id.step_detail_monitoring_record_group:
                /** 监控与记录(组)*/
                MonitorEquipmentActivity.startActivity(this);
                break;
            case R.id.step_detail_assess_tips_group:
                /** 考核要素(组)*/
                AssessmentActivity.startActivity(this);
                break;
        }
    }


    public static void startActivity(Context context, int actId,int stepId, String stepName) {
        Intent intent = new Intent(context, StepDetailActivity.class);
        intent.putExtra("actId", actId);
        intent.putExtra("stepId", stepId);
        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }
}
