package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.DeviceCodeContract;
import com.zhongke.weiduo.mvp.presenter.DeviceCodePresenter;
import com.zhongke.weiduo.util.ToastUtil;

/**
 * Created by ${xingen} on 2017/12/5.
 * 设备二维码界面
 */

public class DeviceCodeActivity extends BaseMvpActivity implements DeviceCodeContract {
    @Override
    public String getDeviceCode() {
        return deviceCode;
    }

    private DeviceCodePresenter presenter;

    @Override
    protected BasePresenter createPresenter() {
        this.presenter = new DeviceCodePresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiverIntent();
        setCenterLay(R.layout.activity_device_code);
        this.presenter.loadData();
        initView();
    }

    private ImageView code_iv;

    private void initView() {
        showCenterView();
        setTitleName("设备二维码");
        this.code_iv = (ImageView) findViewById(R.id.device_code_show_iv);
        baseTitle.setLeftImgOnClickListener(view-> DeviceCodeActivity.this.finish());
    }

    private String deviceCode;

    private void receiverIntent() {
        this.deviceCode = getIntent().getStringExtra("deviceCode");
    }

    public static void startActivity(Context context, String deviceCode) {
        Intent intent = new Intent(context, DeviceCodeActivity.class);
        intent.putExtra("deviceCode", deviceCode);
        context.startActivity(intent);
    }

    @Override
    public void showCodeBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            ToastUtil.show(getApplicationContext(), "生成二维码出错", Toast.LENGTH_SHORT);
            return;
        }
        this.code_iv.setImageBitmap(bitmap);
    }
}
