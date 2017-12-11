package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AddAssessmentContract;
import com.zhongke.weiduo.mvp.presenter.AddAssessmentPresenter;

/**
 * Created by ${tanlei} on 2017/6/21.
 */

public class AddAssessmentActivity extends BaseMvpActivity implements View.OnClickListener, AddAssessmentContract {
    private EditText etAdd;
    private TextView tvAdd;
    private AddAssessmentPresenter addAssessmentPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_add_assessment);
        showCenterView();
        init();
    }

    @Override
    protected BasePresenter createPresenter() {
        addAssessmentPresenter = new AddAssessmentPresenter(mDataManager, this);
        return addAssessmentPresenter;
    }

    private void init() {
        etAdd = (EditText) findViewById(R.id.et_add);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        setTitleName(getResources().getString(R.string.add_assess_tips));
        setListener();
    }

    private void setListener() {
        tvAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //点击了确定按钮
        if (v.getId() == R.id.tv_add) {
            addAssessmentPresenter.AddAssessmentData();
        }
    }

    @Override
    public String getAddAssessment() {
        return etAdd.getText().toString();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
    public static void startActivity(Context context/*, int stepId, String stepName*/) {
        Intent intent = new Intent(context, AddAssessmentActivity.class);
//        intent.putExtra("stepId", stepId);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }
}
