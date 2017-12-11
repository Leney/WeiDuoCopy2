package com.zhongke.weiduo.mvp.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.DeviceBindContract;
import com.zhongke.weiduo.mvp.presenter.DeviceBindPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${xingen} on 2017/12/5.
 * <p>
 * 绑定设备
 */

public class DeviceBindActivity extends BaseMvpActivity implements DeviceBindContract {
    private DeviceBindPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiverIntent();
        setCenterLay(R.layout.activity_device_bind);
        initView();
    }
    private ImageView icon;
    private TextView name_tv;
    private TextView character_tv;
    private Dialog dialog;
    private void initView() {
        showCenterView();
        setTitleName("关联设备");
        this.icon=(ImageView) findViewById(R.id.device_code_icon_iv);
        this.name_tv=(TextView)findViewById(R.id.device_code_title_tv);
        this.character_tv=(TextView) findViewById(R.id.device_bind_character_tv);
        this.name_tv.setText(name);
        PhotoLoaderUtil.display(this,icon,url,getResources().getDrawable(R.drawable.default_img));
        findViewById(R.id.device_code_bg_2).setOnClickListener(v -> dialog=presenter.selectRole());
        findViewById(R.id.device_bind_btn).setOnClickListener(v -> {
            if (TextUtils.isEmpty(character_tv.getText())){
                Toast.makeText(getApplicationContext(),"请先选择角色，后绑定",Toast.LENGTH_SHORT).show();
                return;
            }
            presenter.bindDevice(createParams());
        });
    }
    private Map<String,Object> createParams(){
        Map<String,Object> params=new HashMap<>();
        params.put("deviceCode",device_code);
        params.put("familyTitle",character_tv.getTag());
        return params;
    }

    private String device_code, name, url;

    @Override
    public Activity getActivityContext() {
        return this;
    }

    @Override
    public void showSelectRole(String name, int values) {
             character_tv.setText(name);
             character_tv.setTag(values);
    }

    private void receiverIntent() {
        this.device_code = getIntent().getStringExtra("deviceCode");
        this.name = getIntent().getStringExtra("name");
        this.url = getIntent().getStringExtra("url");
    }

    @Override
    protected BasePresenter createPresenter() {
        this.presenter = new DeviceBindPresenter(this);
        return this.presenter;
    }

    public static void startActivity(Context context, String deviceCode, String name, String url) {
        Intent intent = new Intent(context, DeviceBindActivity.class);
        intent.putExtra("deviceCode", deviceCode);
        intent.putExtra("url", url);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null){
            dialog.dismiss();
        }
    }
}
