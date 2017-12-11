package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.AddTeachContentContract;
import com.zhongke.weiduo.mvp.model.entity.Pickers;
import com.zhongke.weiduo.mvp.presenter.AddTeachContentPresenter;
import com.zhongke.weiduo.mvp.ui.widget.BottomDialog;
import com.zhongke.weiduo.mvp.ui.widget.CustomDatePicker;
import com.zhongke.weiduo.mvp.ui.widget.PickerScrollView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ${tanlei} on 2017/6/21.
 * 本地添加教学内容界面
 */

public class AddTeachContentActivity extends BaseMvpActivity implements View.OnClickListener, AddTeachContentContract {
    private TextView tvPlayEquipment;
    private TextView tvPlayDate, tvPlayAngle, tvDetermine;
    private AddTeachContentPresenter addTeachContentPresenter;
    private BottomDialog equipmentDialog;
    private List<Pickers> determineList = new ArrayList<>();
    private BottomDialog angleDialog;
    private List<Pickers> angleList = new ArrayList<>();
    private List<Pickers> angleList2 = new ArrayList<>();
    private CustomDatePicker customDatePicker2;
    private String now;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_add_teach_content);
        showCenterView();
        init();
        setListener();
    }

    @Override
    protected BasePresenter createPresenter() {
        addTeachContentPresenter = new AddTeachContentPresenter(this, mDataManager);
        return addTeachContentPresenter;
    }

    private void setListener() {
        tvPlayEquipment.setOnClickListener(this);
        tvPlayDate.setOnClickListener(this);
        tvPlayAngle.setOnClickListener(this);
        tvDetermine.setOnClickListener(this);
    }

    private void init() {
        setTitleName(getResources().getString(R.string.add_teach_content));
        tvDetermine = (TextView) findViewById(R.id.tv_play_equipment);
        tvPlayDate = (TextView) findViewById(R.id.tv_play_date);
        tvPlayAngle = (TextView) findViewById(R.id.tv_play_angle);
        tvPlayEquipment = (TextView) findViewById(R.id.tv_determine);
        determineList.add(new Pickers("主屏播放", 2));
        determineList.add(new Pickers("投影播放", 0));
        determineList.add(new Pickers("录像播放", 1));
        angleList.add(new Pickers("30", 1));
        angleList.add(new Pickers("40", 2));
        angleList.add(new Pickers("50", 3));
        angleList.add(new Pickers("60", 4));
        angleList.add(new Pickers("70", 5));
        angleList.add(new Pickers("80", 6));
        angleList.add(new Pickers("20", 0));
        angleList2.add(new Pickers("30", 1));
        angleList2.add(new Pickers("40", 2));
        angleList2.add(new Pickers("50", 3));
        angleList2.add(new Pickers("60", 4));
        angleList2.add(new Pickers("70", 5));
        angleList2.add(new Pickers("80", 6));
        angleList2.add(new Pickers("20", 0));
        initDatePicker();
    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(System.currentTimeMillis()).toString();
        customDatePicker2 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvPlayDate.setText(time);
                tvPlayDate.setTextColor(getResources().getColor(R.color.actionBarPreColor));
            }
        }, now, "2020-12-20 12:30"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(true); // 显示时和分
        customDatePicker2.setIsLoop(true); // 允许循环滚动
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //确定按钮
            case R.id.tv_determine:
                addTeachContentPresenter.AddTeachContentSuccess();
                break;
            //选择播放设备
            case R.id.tv_play_equipment:
                if (equipmentDialog == null) {
                    equipmentDialog = new BottomDialog(this, R.layout.dialog_one_scroll);
                    final PickerScrollView scrollView = (PickerScrollView) equipmentDialog.findViewById(R.id.dialog_scroll_picker);
                    // 设置数据，默认选择第一条
                    scrollView.setData(determineList);
                    scrollView.setSelected(0);
                    TextView cancelBtn = (TextView) equipmentDialog.findViewById(R.id.dialog_scroll_cancel_btn);
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            equipmentDialog.dismiss();
                        }
                    });

                    TextView sureBtn = (TextView) equipmentDialog.findViewById(R.id.dialog_scroll_sure_btn);
                    sureBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Pickers pickers = scrollView.getCurSelectedItem();
                            if (pickers != null) {
                                tvDetermine.setText(pickers.getName());
                                tvDetermine.setTextColor(getResources().getColor(R.color.actionBarPreColor));
                            }
                            equipmentDialog.dismiss();
                        }
                    });
                }
                equipmentDialog.show();
                break;
            //选择播放时间
            case R.id.tv_play_date:
                customDatePicker2.show(now);
                break;
            //选择播放角度
            case R.id.tv_play_angle:
                if (angleDialog == null) {
                    angleDialog = new BottomDialog(this, R.layout.dialog_angle_scroll);
                    final PickerScrollView scrollView = (PickerScrollView) angleDialog.findViewById(R.id.dialog_scroll_picker_level);
                    final PickerScrollView scrollView2 = (PickerScrollView) angleDialog.findViewById(R.id.dialog_scroll_picker_vertical);
                    // 设置数据，默认选择第一条
                    scrollView.setData(angleList);
                    scrollView.setSelected(0);
                    scrollView2.setData(angleList2);
                    scrollView2.setSelected(0);
                    TextView cancelBtn = (TextView) angleDialog.findViewById(R.id.dialog_scroll_cancel_btn);
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            angleDialog.dismiss();
                        }
                    });
                    TextView sureBtn = (TextView) angleDialog.findViewById(R.id.dialog_scroll_sure_btn);
                    sureBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Pickers pickers = scrollView.getCurSelectedItem();
                            Pickers pickers2 = scrollView2.getCurSelectedItem();
                            if (pickers != null) {
                                tvPlayAngle.setText("水平" + pickers.getName() + "°" + "，" + "垂直" + pickers2.getName() + "°");
                                tvPlayAngle.setTextColor(getResources().getColor(R.color.actionBarPreColor));
                            }
                            angleDialog.dismiss();
                        }
                    });
                }
                angleDialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public String getAddPlayEquipment() {
        return tvPlayEquipment.getText().toString();
    }

    @Override
    public String getAddPlayDate() {
        return tvPlayDate.getText().toString();
    }

    @Override
    public String getAddPlayAngle() {
        return tvPlayAngle.getText().toString();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    public static void startActivity(Context context/*, int stepId, String stepName*/) {
        Intent intent = new Intent(context, AddTeachContentActivity.class);
//        intent.putExtra("stepId", stepId);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }
}
