package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MoneyPrepareContract;
import com.zhongke.weiduo.mvp.model.entity.MoneyManageBean;
import com.zhongke.weiduo.mvp.model.entity.Pickers;
import com.zhongke.weiduo.mvp.presenter.MoneyPreparePresenter;
import com.zhongke.weiduo.mvp.ui.widget.BottomDialog;
import com.zhongke.weiduo.mvp.ui.widget.PickerScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 资金筹备界面
 * Created by llj on 2017/6/27.
 */

public class MoneyPrepareActivity extends BaseMvpActivity implements MoneyPrepareContract, View.OnClickListener {
    private MoneyPreparePresenter mPresenter;
    @Bind(R.id.money_prepare_duty_person)
    TextView dutyText;

    @Bind(R.id.money_prepare_money_detail)
    TextView detailText;

    @Bind(R.id.money_prepare_remind_btn)
    TextView remindBtn;

    private BottomDialog bottomDialog;

    private MoneyManageBean mMoneyManageBean;

    private List<Pickers> roleList;

    /**
     * 活动id
     */
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName(getResources().getString(R.string.money_prepare));

        id = getIntent().getIntExtra("id", -1);
        if (id <= -1) {
            finish();
        }

        setCenterLay(R.layout.activity_money_prepare);

        ButterKnife.bind(this);
        showCenterView();


        init();


        // 获取活动资金筹备信息
        mPresenter.getMoneyManageInfo();
    }

    private void init() {
        dutyText.setOnClickListener(this);
        remindBtn.setOnClickListener(this);
    }

    @Override
    public void getMoneyManageInfoSuccess(MoneyManageBean moneyManageBean) {
        this.mMoneyManageBean = moneyManageBean;
        dutyText.setText(moneyManageBean.getPrinName() + getResources().getString(R.string.set_duty_people));
        detailText.setText(moneyManageBean.getMoney() + getResources().getString(R.string.yuan) + "/" + moneyManageBean.getUnit());
        roleList = new ArrayList<>();
        for (int i = 0; i < moneyManageBean.getRoleList().size(); i++) {
            MoneyManageBean.RoleListBean roleListBean = moneyManageBean.getRoleList().get(i);
            Pickers pickers = new Pickers();
            pickers.setName(roleListBean.getName());
            pickers.setId(roleListBean.getId());
            roleList.add(pickers);
        }
    }

    @Override
    public void getMoneyManageInfoFailed(int errorCode, String msg) {
        showErrorView();
    }

    @Override
    public void modifyDutyChargeSuccess(MoneyManageBean.RoleListBean roleListBean) {
        dutyText.setText(roleListBean.getName() + getResources().getString(R.string.set_duty_people));
    }

    @Override
    public void modifyDutyChargeFailed(int errorCode,String msg) {
        Toast.makeText(MoneyPrepareActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new MoneyPreparePresenter(this, mDataManager, this);
        return mPresenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.money_prepare_duty_person:
                // 责任人
                if (mMoneyManageBean.getIsManager() != 0) {
                    // 不是活动管理人员 不准修改
                    return;
                }
                if (bottomDialog == null) {
                    bottomDialog = new BottomDialog(MoneyPrepareActivity.this, R.layout.dialog_one_scroll);
                    final PickerScrollView scrollView = (PickerScrollView) bottomDialog.findViewById(R.id.dialog_scroll_picker);
                    // 设置数据，默认选择第一条
                    scrollView.setData(roleList);
                    scrollView.setSelected(0);

                    TextView cancelBtn = (TextView) bottomDialog.findViewById(R.id.dialog_scroll_cancel_btn);
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bottomDialog.dismiss();
                        }
                    });

                    TextView sureBtn = (TextView) bottomDialog.findViewById(R.id.dialog_scroll_sure_btn);
                    sureBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Pickers pickers = scrollView.getCurSelectedItem();
                            if (pickers != null && pickers.getId() != mMoneyManageBean.getPrinId()) {
                                // 选择的跟当前责任人不一样才进行请求网络进行更改
                                mPresenter.modifyDutyCharge(id, pickers.getId(), pickers.getName());
                            }
                            bottomDialog.dismiss();
                        }
                    });
                }
                bottomDialog.show();
                break;
            case R.id.money_prepare_remind_btn:
                // 提醒按钮
                break;
        }
    }


    public static void startActivity(Context context, int id) {
        Intent intent = new Intent(context, MoneyPrepareActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}
