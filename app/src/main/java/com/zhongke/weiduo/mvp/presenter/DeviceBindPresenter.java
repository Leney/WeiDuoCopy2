package com.zhongke.weiduo.mvp.presenter;

import android.app.Dialog;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.DeviceBindContract;
import com.zhongke.weiduo.mvp.model.entity.Pickers;
import com.zhongke.weiduo.mvp.ui.widget.BottomDialog;
import com.zhongke.weiduo.mvp.ui.widget.PickerScrollView;
import com.zhongke.weiduo.util.ActionConstances;
import com.zhongke.weiduo.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * Created by ${xingen} on 2017/12/5.
 */

public class DeviceBindPresenter extends BasePresenter {
    private DeviceBindContract contract;
    private List<Pickers> roleList;

    public DeviceBindPresenter(DeviceBindContract contract) {
        this.contract = contract;
        loadData();
    }

    private void loadData() {
        roleList = new ArrayList<>();
        String[] roles =appContext. getResources().getStringArray(R.array.roles);
        for (int i = 0; i < roles.length; i++) {
            Pickers pickers = new Pickers();
            pickers.setId(i);
            pickers.setName(roles[i]);
            pickers.setValue(i + 1);
            roleList.add(pickers);
        }
    }
    private BottomDialog roleDialog;
    public Dialog selectRole(){
        if (roleDialog==null){
            roleDialog=new BottomDialog(contract.getActivityContext(),R.layout.dialog_one_scroll);
            final PickerScrollView scrollView = (PickerScrollView) roleDialog.findViewById(R.id.dialog_scroll_picker);
            // 设置数据，默认选择第一条
            scrollView.setData(roleList);
            scrollView.setSelected(0);
            TextView cancelBtn = (TextView) roleDialog.findViewById(R.id.dialog_scroll_cancel_btn);
            cancelBtn.setOnClickListener( view-> roleDialog.dismiss());
            TextView sureBtn = (TextView) roleDialog.findViewById(R.id.dialog_scroll_sure_btn);
            sureBtn.setOnClickListener( view-> {
                Pickers pickers = scrollView.getCurSelectedItem();
                if (pickers != null) {
                    contract.showSelectRole(pickers.getName(),pickers.getValue());
                }
                roleDialog.dismiss();

            });
        }
        roleDialog.show();
        return roleDialog;
    }
    public void bindDevice(Map<String,Object> params){
      Subscription subscription= retrofitClient.bindDevice(params, new ResponseResultListener() {
            @Override
            public void success(Object o) {
                ToastUtil.show(appContext,"绑定成功", Toast.LENGTH_SHORT);
                // 发送绑定设备成功的广播
                EventBus.getDefault().post(ActionConstances.ACTION_BIND_DEVICE_SUCCESS);
                contract.getActivityContext().finish();
            }
            @Override
            public void failure(CommonException e) {
                ToastUtil.show(appContext,"绑定失败", Toast.LENGTH_SHORT);
            }
        });
        this.compositeSubscription.add(subscription);
    }
}
