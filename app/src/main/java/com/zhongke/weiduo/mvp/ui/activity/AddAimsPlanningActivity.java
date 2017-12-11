package com.zhongke.weiduo.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AddAimsPlanningContract;
import com.zhongke.weiduo.mvp.control.UserInfoManager;
import com.zhongke.weiduo.mvp.model.entity.ExecutePeopleBean;
import com.zhongke.weiduo.mvp.presenter.AddAimsPlanningPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.ExecuteAdapter;
import com.zhongke.weiduo.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加自定义目标或者规划或者计划
 * Created by ${tanlei} on 2017/9/27.
 */

public class AddAimsPlanningActivity extends BaseMvpActivity implements AddAimsPlanningContract, View.OnClickListener {
    public static final int ADD_TARGET_RESULT_CODE = 10008;
    public static final int ADD_SCHEME_RESULT_CODE = 10009;
    public static final int ADD_PLAN_RESULT_CODE = 100010;
    /**
     * 我的目标
     */
    public static final int MY_TARGET = 0;
    /**
     * 我的规划
     */
    public static final int MY_SCHEME = 1;
    /**
     * 我的计划
     */
    public static final int MY_PLAN = 2;
    private AddAimsPlanningPresenter presenter;
    private int current;

    private EditText nameInput, infoInput;
    private TextView infoTitle;
    private View selectView;
    private TextView peopleName;
    private ListView executeListView;
    private List<ExecutePeopleBean.ListBean> peopleList;
    private ExecuteAdapter executeAdapter;
    private int selectUserId = -1;
    private String selectName="";

    @Override
    protected BasePresenter createPresenter() {
        presenter = new AddAimsPlanningPresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_add_aims_planning);
        showCenterView();
        peopleList = new ArrayList<>();
        initData();
        presenter.getUserAndChild();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            current = intent.getIntExtra("type", 0);
        }

        nameInput = (EditText) findViewById(R.id.name_input);
        infoInput = (EditText) findViewById(R.id.infos_input);
        infoTitle = (TextView) findViewById(R.id.tv5);
        findViewById(R.id.commit_btn).setOnClickListener(this);
        selectView = View.inflate(this, R.layout.pop_select_execute,null);
        executeListView = (ListView) selectView.findViewById(R.id.select_execute_list_view);
        executeAdapter = new ExecuteAdapter(this,peopleList);

        peopleName = (TextView) findViewById(R.id.tv_execute_people_name);
        ImageView downArrow = (ImageView) findViewById(R.id.aims_down_arrow);
        downArrow.setOnClickListener(this);

        /**
         * 设置标题栏
         */
        if (current == MY_TARGET) {
            setTitleName("添加目标");
            infoTitle.setText("目标事项说明");
        } else if (current == MY_SCHEME) {
            setTitleName("添加规划");
            infoTitle.setText("规划事项说明");
        } else {
            setTitleName("添加计划");
            infoTitle.setText("计划事项说明");
        }
    }

    @Override
    public void addSelfTargetSuccess() {
        // 添加自定义目标成功
        Log.i("llj", "添加自定义目标成功");
        setResult(ADD_TARGET_RESULT_CODE);
        UserInfoManager.getInstance().setTargetNum(UserInfoManager.getInstance().getTargetNum() + 1);
        finish();
    }

    @Override
    public void addSelfTargetFailed(CommonException e) {
        Toast.makeText(this, "添加目标失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addSelfSchemeSuccess() {
        // 添加自定义规划成功
        Log.i("llj", "添加自定义规划成功");
        setResult(ADD_SCHEME_RESULT_CODE);
        UserInfoManager.getInstance().setSchemeNum(UserInfoManager.getInstance().getSchemeNum() + 1);
        finish();
    }

    @Override
    public void addSelfSchemeFailed(CommonException e) {
        Toast.makeText(this, "添加规划失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addSelfPlanSuccess() {
        // 添加自定义计划成功
        Log.i("llj", "添加自定义计划成功");
        setResult(ADD_PLAN_RESULT_CODE);
        UserInfoManager.getInstance().setPlanNum(UserInfoManager.getInstance().getPlanNum() + 1);
        finish();
    }

    @Override
    public void addSelfPlanFailed(CommonException e) {
        Toast.makeText(this, "添加计划失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserAndChildSuccess(ExecutePeopleBean bean) {
        if (bean.getList() != null) {
            if (!TextUtils.isEmpty(bean.getList().get(0).getNickName())) {
                peopleName.setText(bean.getList().get(0).getNickName());
            } else {
                peopleName.setText(bean.getList().get(0).getUserName());
            }
            selectUserId = bean.getList().get(0).getUserId();
        }

        peopleList.clear();
        LogUtil.e("bean.getList()--"+bean.getList());
        peopleList.addAll(bean.getList());
        //executeAdapter.notifyDataSetChanged();
    }

    @Override
    public void getUserAndChildFailed(CommonException e) {

    }


    public static void startActivityForResult(Activity activity, int type) {
        Intent intent = new Intent(activity, AddAimsPlanningActivity.class);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, 1008);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit_btn:
                // 提交按钮
                String name = nameInput.getText().toString();
                if (name.isEmpty()) {
                    UIUtils.showToast("请输入名称");
                    return;
                }
                if (selectUserId == -1) {
                    UIUtils.showToast("请选择执行人");
                    return;
                }
                String info = infoInput.getText().toString();
                if (info.isEmpty()) {
                    UIUtils.showToast("请输入事项说明");
                    return;
                }
                if (current == MY_TARGET) {
                    // 目标
                    presenter.addSelfTarget(name, info,selectUserId);
                } else if (current == MY_SCHEME) {
                    // 规划
                    presenter.addSelfScheme(name, info,selectUserId);
                } else {
                    // 计划
                    presenter.addSelfPlan(name, info,selectUserId);
                }
                break;
            case R.id.aims_down_arrow:
                //
                PopupWindow pw = new PopupWindow(selectView,peopleName.getWidth(), DisplayUtils.dip2px(AddAimsPlanningActivity.this,120),true);
                pw.setBackgroundDrawable(new BitmapDrawable());
                pw.setFocusable(true);
                pw.setOutsideTouchable(true);
                pw.showAsDropDown(peopleName,0,0);

                executeListView.setAdapter(executeAdapter);
                executeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ExecutePeopleBean.ListBean bean = peopleList.get(position);
                        selectUserId = bean.getUserId();
                        selectName = bean.getNickName();
                        peopleName.setText(selectName);
                        pw.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }


}
