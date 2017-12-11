package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AppendScheduleContract;
import com.zhongke.weiduo.mvp.control.UserInfoManager;
import com.zhongke.weiduo.mvp.model.entity.ExecutePeopleBean;
import com.zhongke.weiduo.mvp.model.event.AppendScheduleEvent;
import com.zhongke.weiduo.mvp.presenter.AppendSchedulePrensenter;
import com.zhongke.weiduo.mvp.ui.adapter.ExecuteAdapter;
import com.zhongke.weiduo.mvp.ui.widget.CustomDatePicker;
import com.zhongke.weiduo.mvp.ui.widget.dialog.ScheduleCycleDialog;
import com.zhongke.weiduo.mvp.ui.widget.wheelview.ScheduleCycleView;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Karma on 2017/9/18.
 *
 */

public class AppendScheduleActivity extends BaseMvpActivity implements AppendScheduleContract, View.OnClickListener {

    @Bind(R.id.append_schedule_start_time)
    RelativeLayout start_time;
    @Bind(R.id.tv_month_day)
    TextView month_day;
    @Bind(R.id.tv_hour_min)
    TextView hour_min;
    @Bind(R.id.append_schedule_end_time)
    RelativeLayout end_time;
    @Bind(R.id.tv_end_month_day)
    TextView end_month_day;
    @Bind(R.id.tv_end_hour_min)
    TextView end_hour_min;
    @Bind(R.id.tv_cycle)
    TextView cycle;
    @Bind(R.id.append_schedule_schedule_content)
    EditText schedule_content;
    @Bind(R.id.append_schedule_executor_ll)
    LinearLayout executorLL;
    @Bind(R.id.append_schedule_executor_right)
    RelativeLayout executorRight;
    @Bind(R.id.append_schedule_executor_people)
    TextView executorPeoPle;

    private String now;
    private CustomDatePicker startCustomDatePicker;
    private CustomDatePicker endCustomDatePicker;
    private ScheduleCycleDialog cycleDialog;
    private ScheduleCycleView cycleView;
    private AppendSchedulePrensenter prensenter;
    private SimpleDateFormat sdf;
    private String selectBegin;
    private String selectEnd;
    private String beginDate;
    private View popView;
    private ListView executorLv;
    private List<ExecutePeopleBean.ListBean> peopleList;
    private int selectUserId = 0;
    private ExecuteAdapter executeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_append_schedule);
        ButterKnife.bind(this);

        initDate();
        initDatePicker();
        peopleList = new ArrayList<>();
        initView();
        prensenter.getUserAndChild();

        baseTitle.setTitleName("添加日程");
        baseTitle.setRightVisible(true);
        baseTitle.setRightText("完成");
        baseTitle.setRightOnClickListener(completeListener);

    }

    private void initDate() {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(System.currentTimeMillis()).toString();
    }

    //初始化时间选择picker
    private void initDatePicker() {
        startCustomDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                LogUtil.e("time---" + time);
                String[] date = time.split(" ");
                String yearMonthStr = date[0].replace("-", ".");

                month_day.setText(yearMonthStr);
                hour_min.setText(date[1]);
                selectBegin = time;
            }
        }, now, "2020-12-20 12:30"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        startCustomDatePicker.showSpecificTime(true); // 显示时和分
        startCustomDatePicker.setIsLoop(true); // 允许循环滚动

        endCustomDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                String[] date = time.split(" ");
                String yearMonthStr = date[0].replace("-", ".");

                end_month_day.setText(yearMonthStr);
                end_hour_min.setText(date[1]);
                selectEnd = time;
            }
        }, now, "2020-12-20 12:30"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        endCustomDatePicker.showSpecificTime(true); // 显示时和分
        endCustomDatePicker.setIsLoop(true); // 允许循环滚动
    }

    private void initView() {
        String[] date = now.split(" ");
        String yearMonthStr = date[0].replace("-", ".");
        month_day.setText(yearMonthStr);
        hour_min.setText(date[1]);
        end_month_day.setText(yearMonthStr);
        end_hour_min.setText(date[1]);

        popView = View.inflate(this, R.layout.pop_append_schedule_executor,null);
        executorLv = (ListView) popView.findViewById(R.id.pop_append_schedule_executor_lv);
        executeAdapter = new ExecuteAdapter(this,peopleList);
        start_time.setOnClickListener(this);
        end_time.setOnClickListener(this);
        cycle.setOnClickListener(this);
        executorLL.setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return prensenter = new AppendSchedulePrensenter(this, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.append_schedule_start_time:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                now = sdf.format(System.currentTimeMillis()).toString();
                if (TextUtils.isEmpty(selectBegin)) {
                    startCustomDatePicker.show(now);
                } else {
                    startCustomDatePicker.show(selectBegin);
                }
                break;
            case R.id.append_schedule_end_time:
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                now = sdf2.format(System.currentTimeMillis()).toString();
                if (TextUtils.isEmpty(selectEnd)) {
                    endCustomDatePicker.show(now);
                } else {
                    endCustomDatePicker.show(selectEnd);
                }
                break;
            case R.id.tv_cycle:
                if (cycleDialog == null) {
                    cycleDialog = new ScheduleCycleDialog(AppendScheduleActivity.this, R.style.profile_picture_dialog_style);
                }
                cycleDialog.show();
                TextView cycleSure = (TextView) cycleDialog.findViewById(R.id.append_schedule_sure);
                cycleView = (ScheduleCycleView) cycleDialog.findViewById(R.id.schedule_cycle_view);
                cycleSure.setOnClickListener(this);
                break;
            case R.id.append_schedule_sure: //周期确认按钮
                if (cycleView.getCycleString() != null && !"".equals(cycleView.getCycleString())) {
                    cycle.setText(cycleView.getCycleString());
                }
                if (cycleDialog != null) {
                    cycleDialog.dismiss();
                }
                break;
            case R.id.append_schedule_executor_ll:
                PopupWindow pw = new PopupWindow(popView,executorRight.getWidth(), DisplayUtils.dip2px(AppendScheduleActivity.this,120),true);
                pw.setBackgroundDrawable(new BitmapDrawable());
                pw.setFocusable(true);
                pw.setOutsideTouchable(true);
                pw.showAsDropDown(executorRight,0,0);

                executorLv.setAdapter(executeAdapter);
                executorLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ExecutePeopleBean.ListBean bean = peopleList.get(position);
                        selectUserId = bean.getUserId();

                        if (!TextUtils.isEmpty(bean.getNickName())) {
                            executorPeoPle.setText(bean.getNickName());
                        } else if (!TextUtils.isEmpty(bean.getFullName())){
                            executorPeoPle.setText(bean.getFullName());
                        } else {
                            executorPeoPle.setText(bean.getUserName());
                        }
                        pw.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }

    //点击完成
    private View.OnClickListener completeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String contentStr = schedule_content.getText().toString().trim();
            //开始日期和开始时间,时间要到秒
            String beginTime = hour_min.getText().toString().trim() + ":00";
            LogUtil.e("beginTime--" + beginTime);
            beginDate = month_day.getText().toString().trim().replace(".", "-");
            //结束日期和结束时间
            String endTime = end_hour_min.getText().toString().trim() + ":00";
            String endDate = end_month_day.getText().toString().trim().replace(".", "-");
            //重复周期
            String stringCyle = string2Cycle(cycle.getText().toString().trim());
            LogUtil.e("endTime--" + endTime);
            if (TextUtils.isEmpty(contentStr)) {
                showToast("请填写内容");
            }
            //判断结束时间是否大于开始时间
            String confirmBeginTime = beginDate + " " + beginTime;
            String confirmEndTime = endDate + " " + endTime;
            long time = 0;
            long time2 = 0;
            try {
                time = sdf.parse(confirmBeginTime).getTime();
                time2 = sdf.parse(confirmEndTime).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
                LogUtil.e("时间转毫秒异常");
            }
//            LogUtil.e("time-----"+time);
//            LogUtil.e("time2-----"+time2);
            if (time >= time2) {
                ToastUtil.showShort(AppendScheduleActivity.this, "结束时间必须大于开始时间");
            }
            if (selectUserId == 0) {
                ToastUtil.showShort(AppendScheduleActivity.this,"请添加执行人");
            }
            LogUtil.e("selectUserId--"+selectUserId);
            if (!TextUtils.isEmpty(contentStr) && !stringCyle.equals("-1") && time < time2 && selectUserId != 0) {
                LogUtil.e("commit");
                prensenter.addSchedule(beginTime, endTime, beginDate, endDate, stringCyle, contentStr,selectUserId+"");
                showLoadingView();
            }

        }
    };

    private String string2Cycle(String cycle) {
        switch (cycle) {
            case "每天一次":
                return "1";
            case "三天一次":
                return "3";
            case "每周一次":
                return "7";
            case "每两周一次":
                return "14";
            case "每月一次":
                return "30";
            default:
                break;
        }
        return "-1";
    }

    public static void startAppendSchedule(Context context) {
        Intent intent = new Intent(context, AppendScheduleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void addScheduleSuccess() {
        showCenterView();
        EventBus.getDefault().post(new AppendScheduleEvent(beginDate));
        showToast("添加成功");
        UserInfoManager.getInstance().setActiveNum(UserInfoManager.getInstance().getActiveNum() + 1);
    }

    @Override
    public void addScheduleFailed(CommonException e) {
        showCenterView();
        showToast("添加失败");
    }

    @Override
    public void getUserAndChildSuccess(ExecutePeopleBean executePeopleBean) {
        showCenterView();
        peopleList.clear();
        peopleList.addAll(executePeopleBean.getList());
    }

    @Override
    public void getUserAndChildFailed(CommonException e) {

    }

    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
