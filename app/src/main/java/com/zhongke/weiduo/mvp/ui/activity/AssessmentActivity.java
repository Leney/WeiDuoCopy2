package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AssessmentContart;
import com.zhongke.weiduo.mvp.model.entity.AssessmentBean;
import com.zhongke.weiduo.mvp.model.entity.Pickers;
import com.zhongke.weiduo.mvp.presenter.AssessmentPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.AssessmentAdapter;
import com.zhongke.weiduo.mvp.ui.widget.BottomDialog;
import com.zhongke.weiduo.mvp.ui.widget.PickerScrollView;

import java.util.ArrayList;
import java.util.List;

import static com.zhongke.weiduo.mvp.ui.adapter.AssessmentAdapter.SCORING_SYSTEM;

/**
 * Created by ${tanlei} on 2017/6/20.
 */

public class AssessmentActivity extends BaseMvpActivity implements View.OnClickListener, AssessmentContart, AssessmentAdapter.OnMyItemClickListeners {
    private AssessmentPresenter assessmentPresenter;
    /**
     * 展示数据的ListView
     */
    private ListView lvAssessment;
    /**
     * 适配器
     */
    private AssessmentAdapter assessmentAdapter;
    /**
     * 添加按钮
     */
    private TextView tvAdd;
    private List<Pickers> scoringList = new ArrayList<>();
    private BottomDialog scoringDialog;

    @Override
    protected BasePresenter createPresenter() {
        assessmentPresenter = new AssessmentPresenter(this, mDataManager);
        return assessmentPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_assessment);
        showCenterView();
        init();
        setListener();
    }

    public void setListener() {
        tvAdd.setOnClickListener(this);
    }

    public void init() {
        setTitleName(getResources().getString(R.string.assess_tips));
        lvAssessment = (ListView) findViewById(R.id.lv_assessment);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        assessmentPresenter.showAssessmentData();
        scoringList.add(new Pickers("五分制", 0));
        scoringList.add(new Pickers("A/B等级制", 1));
        scoringList.add(new Pickers("五星制", 2));
        scoringList.add(new Pickers("100分制", 3));
        scoringList.add(new Pickers("10分制", 4));
        scoringList.add(new Pickers("5分制", 5));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_add) {
            AddAssessmentActivity.startActivity(this);
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showData(List<AssessmentBean> assessmentBeans) {
        assessmentAdapter = new AssessmentAdapter(this, assessmentBeans);
        assessmentAdapter.setOnMyItemClickListeners(this);
        lvAssessment.setAdapter(assessmentAdapter);
    }

    @Override
    public void deleteData(List<AssessmentBean> assessmentBeans) {

    }

    public static void startActivity(Context context/*, int stepId, String stepName*/) {
        Intent intent = new Intent(context, AssessmentActivity.class);
//        intent.putExtra("stepId", stepId);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }

    @Override
    public void clickItem(String str, final View v) {
        if (SCORING_SYSTEM.equals(str)) {
            if (scoringDialog == null) {
                scoringDialog = new BottomDialog(this, R.layout.dialog_one_scroll);
                final PickerScrollView scrollView = (PickerScrollView) scoringDialog.findViewById(R.id.dialog_scroll_picker);
                // 设置数据，默认选择第一条
                scrollView.setData(scoringList);
                scrollView.setSelected(0);
                TextView cancelBtn = (TextView) scoringDialog.findViewById(R.id.dialog_scroll_cancel_btn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        scoringDialog.dismiss();
                    }
                });

                TextView sureBtn = (TextView) scoringDialog.findViewById(R.id.dialog_scroll_sure_btn);
                sureBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Pickers pickers = scrollView.getCurSelectedItem();
                        if (pickers != null) {
                            TextView tv = (TextView) v.findViewById(R.id.tv_assessment_value);
                            tv.setText(pickers.getName());
                            tv.setTextColor(getResources().getColor(R.color.actionBarPreColor));
                        }
                        scoringDialog.dismiss();
                    }
                });
            }
            scoringDialog.show();
        } else {

        }
    }
}
