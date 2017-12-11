package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActToolsContract;
import com.zhongke.weiduo.mvp.model.entity.Pickers;
import com.zhongke.weiduo.mvp.model.entity.ToolDetailBean;
import com.zhongke.weiduo.mvp.presenter.ActToolsPresenter;
import com.zhongke.weiduo.mvp.ui.widget.BottomDialog;
import com.zhongke.weiduo.mvp.ui.widget.PickerScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.id;

/**
 * 用具清单界面
 * Created by llj on 2017/6/28.
 */

public class ActToolsActivity extends BaseMvpActivity implements ActToolsContract, View.OnClickListener {
    private ActToolsPresenter mPresenter;

    @Bind(R.id.tool_list_duty_person)
    TextView dutyText;

    @Bind(R.id.tool_list_item_lay)
    LinearLayout toolsLay;

    @Bind(R.id.tool_list_remind_btn)
    TextView remindBtn;

    private ToolDetailBean mToolDetailBean;

    /**
     * 活动id
     */
    private int actId;

    private BottomDialog bottomDialog;

    private List<Pickers> roleList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actId = getIntent().getIntExtra("actId", -1);
        if (actId <= -1) {
            finish();
        }
        setTitleName(getResources().getString(R.string.tool_list));
        setCenterLay(R.layout.activity_tool_list);
        ButterKnife.bind(this);
        showCenterView();
        init();

        mPresenter.getToolListDetail();
    }

    private void init() {
        dutyText.setOnClickListener(this);
        remindBtn.setOnClickListener(this);
    }

    @Override
    public void getToolListSuccess(ToolDetailBean toolDetailBean) {
        this.mToolDetailBean = toolDetailBean;
        dutyText.setText(toolDetailBean.getPrinName() + getResources().getString(R.string.set_duty_people));

        int length = toolDetailBean.getToolList().size();
        for (int i = 0; i < length; i++) {
            ToolDetailBean.ToolListBean toolListBean = toolDetailBean.getToolList().get(i);
            View view = View.inflate(ActToolsActivity.this, R.layout.tools_item_lay, null);
            TextView name = (TextView) view.findViewById(R.id.tool_name);
            ImageView status = (ImageView) view.findViewById(R.id.tool_status);
            name.setText(toolListBean.getName());
            status.setImageResource(toolListBean.getIsOk() == 0 ? R.drawable.ic_checked : R.drawable.ic_unchecked);
            toolsLay.addView(view);
            if (i < length - 1) {
                View splitView = new View(ActToolsActivity.this);
                splitView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.split_line_height)));
                splitView.setBackgroundResource(R.color.spline_color);
                toolsLay.addView(splitView);
            }
        }

        roleList = new ArrayList<>();
        int roleLength = toolDetailBean.getRoleList().size();
        for (int i = 0; i < roleLength; i++) {
            ToolDetailBean.RoleListBean roleListBean = toolDetailBean.getRoleList().get(i);
            Pickers pickers = new Pickers();
            pickers.setName(roleListBean.getName());
            pickers.setId(roleListBean.getId());
            roleList.add(pickers);
        }

    }

    @Override
    public void getToolListFailed(int errorCode, String msg) {
        showErrorView();
    }

    @Override
    public void modifyDutyChargeSuccess(ToolDetailBean.RoleListBean roleListBean) {
        dutyText.setText(roleListBean.getName() + getResources().getString(R.string.set_duty_people));
    }

    @Override
    public void modifyDutyChargeFailed(int errorCode, String msg) {
        Toast.makeText(ActToolsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new ActToolsPresenter(this, mDataManager, this);
        return mPresenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tool_list_duty_person:
                // 责任人
                if (mToolDetailBean.getIsManager() != 0) {
                    // 不是活动管理人员 不准修改
                    return;
                }
                if (bottomDialog == null) {
                    bottomDialog = new BottomDialog(ActToolsActivity.this, R.layout.dialog_one_scroll);
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
                            if (pickers != null && pickers.getId() != mToolDetailBean.getPrinId()) {
                                // 选择的跟当前责任人不一样才进行请求网络进行更改
                                mPresenter.modifyDutyCharge(id, pickers.getId(), pickers.getName());
                            }
                            bottomDialog.dismiss();
                        }
                    });
                }
                bottomDialog.show();
                break;
            case R.id.tool_list_remind_btn:
                // 提醒
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public static void startActivity(Context context, int actId) {
        Intent intent = new Intent(context, ActToolsActivity.class);
        intent.putExtra("actId", actId);
        context.startActivity(intent);
    }
}
