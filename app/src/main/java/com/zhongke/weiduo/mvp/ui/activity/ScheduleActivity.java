package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ScheduleContract;
import com.zhongke.weiduo.mvp.model.entity.ScheduleBean;
import com.zhongke.weiduo.mvp.model.entity.eventschedule.EventScheduleResult;
import com.zhongke.weiduo.mvp.model.event.AppendScheduleEvent;
import com.zhongke.weiduo.mvp.presenter.SchedulePresenter;
import com.zhongke.weiduo.mvp.ui.adapter.ScheduleAdapter;
import com.zhongke.weiduo.mvp.ui.adapter.YearDropDownAdapter;
import com.zhongke.weiduo.mvp.ui.widget.view.DateTableView;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Karma on 2017/9/18.
 */

public class ScheduleActivity extends BaseMvpActivity implements ScheduleContract,View.OnClickListener {

    private Context context;
    private SchedulePresenter presenter;
    private DateTableView dateTableView;

    public static final  String TAG = "ScheduleActivity";
    //现在的年月
    private String nowYearMonth;

    private ExpandableListView expandableListView;
    private View date_pick;
    private boolean hasAddHeader;
    private List<String> yearStr = new ArrayList<>();
    private List<String> monthStr = new ArrayList<>();
    private TextView year_select;
    private TextView month_select;
    private View showDrop;
    private ListView yearList;
    private YearDropDownAdapter yearAdapter;
    private YearDropDownAdapter monthAdapter;
    private LinearLayout year_and_arrow;
    private LinearLayout month_and_arrow;
    private String[] nowDateArr;
    private String dateToday;

    private ImageView addSchedule;
    private boolean firstRequest;

    private String tomorrow;
    //现在选择的请求日程的日期
    private String currentSelect;
    private List<ScheduleBean> scheduleList;
    private List<String> groupStr = new ArrayList<>();

    private boolean firstToday;
    private boolean firstTomorrow;
    private boolean btnDisplay = true;
    private ScheduleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_schedule);
        EventBus.getDefault().register(this);
        context = this;
        baseTitle.setTitleName("我的日程");
        baseTitle.setRightVisible(true);
        baseTitle.setRightImgRes(R.drawable.complete_date);
        baseTitle.setRightText("");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date date = Calendar.getInstance().getTime();
        nowYearMonth = sdf.format(date);
        //获取今天的日期
        dateToday = sdf2.format(date);
//        LogUtil.i(TAG+"---", nowYearMonth);
//        LogUtil.i(TAG+"---", dateToday);
        addYearMonthStr();
        firstRequest = true;

        initView();
        initListener();
        try {
            groupStr.clear();
            groupStr.addAll(getGroupShow(dateToday));
        } catch (ParseException e) {
            e.printStackTrace();
            LogUtil.e("日期转换字符失败");
        }
        ScheduleBean scheduleBean = new ScheduleBean();
        scheduleBean.setMonth(groupStr.get(0));
        scheduleBean.setDayNumber(groupStr.get(1));
        scheduleBean.setDayOfWeek(groupStr.get(2));
        scheduleList = new ArrayList<>();
        scheduleList.add(scheduleBean);
        //请求今天的数据
        presenter.getEventSchedule(dateToday);
        currentSelect = dateToday;
        //获取明天的日期
        Calendar calendar = Calendar.getInstance();
        int day = date.getDate();
//        LogUtil.e("day----"+day);
        calendar.set(Calendar.DAY_OF_MONTH,day+1);
        Date date2 = calendar.getTime();
        tomorrow = sdf2.format(date2);
//        LogUtil.e("tomorrow----"+ tomorrow);
        try {
            groupStr.clear();
            groupStr.addAll(getGroupShow(tomorrow));
        } catch (ParseException e) {
            e.printStackTrace();
            LogUtil.e("日期转换字符失败");
        }
        ScheduleBean scheduleBean2 = new ScheduleBean();
        scheduleBean2.setMonth(groupStr.get(0));
        scheduleBean2.setDayNumber(groupStr.get(1));
        scheduleBean2.setDayOfWeek(groupStr.get(2));
        scheduleList.add(scheduleBean2);
        //请求明天的数据
        presenter.getEventSchedule(tomorrow);

        dateTableView.addData(nowYearMonth);
        baseTitle.setRightImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasAddHeader) {//没有添加header
                    expandableListView.addHeaderView(date_pick);
                    hasAddHeader = true;
                } else if (hasAddHeader) { //有添加header
                    expandableListView.removeHeaderView(date_pick);
                    hasAddHeader = false;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.elv_detail);
        addSchedule = (ImageView) findViewById(R.id.activity_schedule_add);

        date_pick = View.inflate(context,R.layout.header_schedule_date_table,null);

        dateTableView = (DateTableView) date_pick.findViewById(R.id.date_table);

        year_and_arrow = (LinearLayout) date_pick.findViewById(R.id.year_and_arrow);
        year_select = (TextView) date_pick.findViewById(R.id.date_year_select);
        nowDateArr = nowYearMonth.split("-");
        year_select.setText(nowDateArr[0]);
        showDrop = View.inflate(context, R.layout.pop_year_dropdown,null);
        yearList = (ListView) showDrop.findViewById(R.id.year_listview);
        yearList.setDivider(null);
        yearAdapter = new YearDropDownAdapter(context,yearStr);

        month_and_arrow = (LinearLayout) date_pick.findViewById(R.id.month_and_arrow);
        month_select = (TextView) date_pick.findViewById(R.id.date_month_select);
        month_select.setText(nowDateArr[1]);
        monthAdapter = new YearDropDownAdapter(context, monthStr);
    }

    private void initListener() {
        addSchedule.setOnClickListener(this);
        //选择年的监听
        year_and_arrow.setOnClickListener(this);
        //选择月的监听
        month_and_arrow.setOnClickListener(this);

        dateTableView.setItemClickListener(DateTableItemClick);
    }

    private DateTableView.ItemClickListener DateTableItemClick = new DateTableView.ItemClickListener() {
        @Override
        public void clickDate(String clickDate) {
            try {
                groupStr.clear();
                groupStr.addAll(getGroupShow(clickDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ScheduleBean bean = new ScheduleBean();
            bean.setMonth(groupStr.get(0));
            bean.setDayNumber(groupStr.get(1));
            bean.setDayOfWeek(groupStr.get(2));

            scheduleList.clear();
            scheduleList.add(bean);

            firstRequest = false;
            presenter.getEventSchedule(clickDate);
            showLoadingView();
            currentSelect = clickDate;


        }
    };


    @Override
    protected BasePresenter createPresenter() {
        return presenter= new SchedulePresenter(context,this);
    }

    private void addYearMonthStr() {
        yearStr.add("2016");
        yearStr.add("2017");
        yearStr.add("2018");
        yearStr.add("2019");
        yearStr.add("2020");

        monthStr.add("01");
        monthStr.add("02");
        monthStr.add("03");
        monthStr.add("04");
        monthStr.add("05");
        monthStr.add("06");
        monthStr.add("07");
        monthStr.add("08");
        monthStr.add("09");
        monthStr.add("10");
        monthStr.add("11");
        monthStr.add("12");
    }

    @Override
    public void getEventScheduleSuccess(EventScheduleResult resultBean,String requestTime) {
        if (firstRequest) {//第一次进来页面,没有点击日期选择器选择
            if (requestTime.equals(dateToday)) {
                firstToday = true;
                scheduleList.get(0).getScheduleChildList().clear();
                scheduleList.get(0).getScheduleChildList().addAll(resultBean.getRecords());
                LogUtil.e("scheduleList1---"+scheduleList);
            } else if(requestTime.equals(tomorrow)) {
                firstTomorrow = true;
                scheduleList.get(1).getScheduleChildList().clear();
                scheduleList.get(1).getScheduleChildList().addAll(resultBean.getRecords());
                LogUtil.e("scheduleList2---"+scheduleList);
            }
        } else {//点击日期来选择
            scheduleList.get(0).getScheduleChildList().addAll(resultBean.getRecords());

        }

        LogUtil.e("scheduleList----4"+scheduleList.toString());
        if (adapter == null) {
            adapter = new ScheduleAdapter(context,scheduleList);
        }
        expandableListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //暂时不能点击跳转详情页面
        //expandableListView.setOnChildClickListener(activityDetailListener);
        int length = scheduleList.size();
        //默认展开
        for (int i =0;i < length;i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        showCenterView();
    }

    @Override
    public void getEventScheduleFailed(CommonException e) {
        showCenterView();
    }

    private List<String> getGroupShow(String dateStr) throws ParseException {
        List<String> stringList = new ArrayList<>();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date =sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int month = calendar.get(Calendar.MONTH) + 1;
//        LogUtil.e("month----"+month);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        LogUtil.e("day----"+day);
        int weeknum = calendar.get(Calendar.DAY_OF_WEEK);
        String weekDay = weekStr(weeknum);
        //几月
        stringList.add(String.valueOf(month));
        //几日
        stringList.add(String.valueOf(day));
        //星期几
        stringList.add(weekDay);
        return stringList;
    }

    private String weekStr(int day_of_week) {
        if (day_of_week == 1) {
            return "星期天";
        } else if (day_of_week == 2) {
            return "星期一";
        } else if (day_of_week == 3) {
            return "星期二";
        } else if (day_of_week == 4) {
            return "星期三";
        } else if (day_of_week == 5) {
            return "星期四";
        } else if (day_of_week == 6) {
            return "星期五";
        } else if (day_of_week == 7) {
            return "星期六";
        }
        return "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //选择年
            case R.id.year_and_arrow:
                PopupWindow pw = new PopupWindow(showDrop,year_select.getWidth(), DisplayUtils.dip2px(context,90),true);
                pw.setBackgroundDrawable(new BitmapDrawable());
                pw.setFocusable(true);
                pw.setOutsideTouchable(true);
                pw.showAsDropDown(year_select,0,-year_select.getHeight());

                yearList.setAdapter(yearAdapter);
                yearList.setSelection(yearStr.indexOf(nowDateArr[0]));
                yearList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pw.dismiss();
                        year_select.setText(yearStr.get(position));
                        dateTableView.addData(yearStr.get(position)+"-"+month_select.getText().toString());
                    }
                });
                break;
            //选择月
            case R.id.month_and_arrow:
                PopupWindow pw2 = new PopupWindow(showDrop,month_select.getWidth(),DisplayUtils.dip2px(context,90),true);
                pw2.setBackgroundDrawable(new BitmapDrawable());
                pw2.setFocusable(true);
                pw2.setOutsideTouchable(true);
                pw2.showAsDropDown(month_select,0,-month_select.getHeight());

                yearList.setAdapter(monthAdapter);
                yearList.setSelection(Integer.valueOf(nowDateArr[1]) - 1);
                yearList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pw2.dismiss();
                        month_select.setText(monthStr.get(position));
                        dateTableView.addData(year_select.getText().toString()+"-"+String.valueOf(position+1));
                    }
                });
                break;
            case R.id.activity_schedule_add:
                AppendScheduleActivity.startAppendSchedule(ScheduleActivity.this);
                break;
            default:
                break;
        }
    }

    private ExpandableListView.OnChildClickListener activityDetailListener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//            int actionId = scheduleList.get(groupPosition).getScheduleChildList().get(childPosition).getActionId();
//            LogUtil.e("--schedule actionId--"+actionId);
//            if (actionId != -1) {
//                ActivityDetailActivity.startActivity(ScheduleActivity.this,actionId,"活动详情");
//            } else {
//                ToastUtil.showShort(ScheduleActivity.this,"actionId 为空");
//            }
            return true;
        }
    };

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,ScheduleActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshEvent(AppendScheduleEvent event) {
        LogUtil.e("refreshEvent----"+"event.date---"+event.beginDate);
        if (event.beginDate.equals(currentSelect) && event.beginDate.equals(dateToday)) {
            presenter.getEventSchedule(dateToday);

        } else if (event.beginDate.equals(currentSelect) && event.beginDate.equals(tomorrow)) {
            presenter.getEventSchedule(tomorrow);

        } else if (event.beginDate.equals(currentSelect)) {
            presenter.getEventSchedule(currentSelect);
        }
    }
}
