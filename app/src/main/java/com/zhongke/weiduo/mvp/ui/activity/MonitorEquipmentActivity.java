package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.MonitorEquipmentContract;
import com.zhongke.weiduo.mvp.model.entity.MonitorEquipmentBean;
import com.zhongke.weiduo.mvp.presenter.MonitorEquipmentPresenter;
import com.zhongke.weiduo.mvp.ui.adapter.MonitorEquipmentAdapter;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/20.
 */

public class MonitorEquipmentActivity extends BaseMvpActivity implements MonitorEquipmentContract {
    private ListView lvMonitorEquipment;
    private MonitorEquipmentAdapter monitorEquipmentAdapter;
    private MonitorEquipmentPresenter monitorEquipmentPresenter;
    @Override
    protected BasePresenter createPresenter() {
        monitorEquipmentPresenter = new MonitorEquipmentPresenter(this, mDataManager);
        return monitorEquipmentPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_assessment);
        showCenterView();
        init();
    }

    public void init() {
        setTitleName(getString(R.string.monitoring_record));
        lvMonitorEquipment = (ListView) findViewById(R.id.lv_assessment);
        monitorEquipmentPresenter.showMonitorEquipment();
        findViewById(R.id.tv_add).setVisibility(View.GONE);
    }

    @Override
    public void showMonitorEquipmentData(List<MonitorEquipmentBean> monitorEquipmentBeans) {
        monitorEquipmentAdapter = new MonitorEquipmentAdapter(monitorEquipmentBeans, this);
        lvMonitorEquipment.setAdapter(monitorEquipmentAdapter);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
    public static void startActivity(Context context/*, int stepId, String stepName*/) {
        Intent intent = new Intent(context, MonitorEquipmentActivity.class);
//        intent.putExtra("stepId", stepId);
//        intent.putExtra("stepName", stepName);
        context.startActivity(intent);
    }
}
