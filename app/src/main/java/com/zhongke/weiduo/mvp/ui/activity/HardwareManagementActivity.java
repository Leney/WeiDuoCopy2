package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.im.IMClient;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.HardwareManagementContract;
import com.zhongke.weiduo.mvp.model.entity.HardwareOrder;
import com.zhongke.weiduo.mvp.model.entity.SceneBean;
import com.zhongke.weiduo.mvp.presenter.HardwareManagementPresenter;
import com.zhongke.weiduo.mvp.ui.widget.dialog.HardwareDialog;
import com.zhongke.weiduo.mvp.ui.widget.view.SceneView;
import com.zhongke.weiduo.mvp.ui.widget.view.SeekBarTopText;
import com.zhongke.weiduo.mvp.ui.widget.view.SwitchView;
import com.zhongke.weiduo.util.SizeUtils;
import com.zk.Json;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ${tanlei} on 2017/9/20.
 * 硬件管理界面
 */

public class HardwareManagementActivity extends BaseMvpActivity implements HardwareManagementContract, View.OnClickListener {
    /**
     * presenter
     */
    private HardwareManagementPresenter presenter;
    /**
     * 情景灯颜色的线性布局
     */
    private LinearLayout llColour;
    /**
     * 情景灯主题的线性布局
     */
    private LinearLayout llScene;
    /**
     * 台灯开关控件
     */
    private SwitchView switch_table_lamp;
    /**
     * 情景灯开关控件
     */
    private SwitchView switch_scene_lights;
    /**
     * 触控投影仪开关控件
     */
    private SwitchView switch_projector;
    /**
     * 高拍仪开关控件
     */
    private SwitchView switch_shot_instrument;
    /**
     * 聊天摄像头开关控件
     */
    private SwitchView switch_chat_camre;
    /**
     * 主机开关控件
     */
    private SwitchView switch_hardware_host;
    /**
     * 音箱开关控件
     */
    private SwitchView switch_hardware_speaker;
    /**
     * 各个开关的状态值
     */
    private boolean tableLampSwitch, sceneLightsSwitch, projectorSwitch, shotInstrumentSwitch, chatCamreSwitch, hardwareHostSwitch, hardwareSpeakerSwitch;
    /**
     * 高拍仪按钮
     */
    private TextView tv_shot_instrument;
    /**
     * 聊天摄像头按钮
     */
    private TextView tv_chat_camre;
    /**
     * 弹出框
     */
    private HardwareDialog dialog;
    /**
     * 台灯(黄灯和白灯)
     */
    private SeekBar tableYellowBar, tableWhiteBar;
    /**
     * 音量调节
     */
    private SeekBarTopText volumeBar;
    /**
     * 设备id集合
     */
    private List<String> toUserList;
    /**
     * 情景灯颜色集合
     */
    private List<String> colors;

    @Override
    protected BasePresenter createPresenter() {
        presenter = new HardwareManagementPresenter(this, mDataManager);
        return presenter;
    }
    private String deviceCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int deviceId = getIntent().getIntExtra("deviceId", -1);
        deviceCode=getIntent().getStringExtra("deviceCode");
        Log.i("llj","deviceId--------->>>"+deviceId);
        if (deviceId < 0) {
            Toast.makeText(HardwareManagementActivity.this, "无效设备", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        toUserList = new ArrayList<>();
        toUserList.add(deviceId + "");

        colors = new ArrayList<>();
        colors.add("255,74,74");
        colors.add("255,134,74");
        colors.add("255,206,74");
        colors.add("124,204,66");
        colors.add("66,204,154");
        colors.add("99,130,245");
        colors.add("251,102,253");

        setCenterLay(R.layout.activity_hardware);
        showCenterView();
        setRightImage();
        init();
        setListeners();
    }

    private void setRightImage() {
        baseTitle.setRightImgRes(R.mipmap.scode_code_show);
        baseTitle.setRightImgClickListener(view->{
            DeviceCodeActivity.startActivity(HardwareManagementActivity.this,deviceCode);
        });
    }

    private void setListeners() {
        switch_table_lamp.setOnClickListener(this);
        switch_scene_lights.setOnClickListener(this);
        switch_projector.setOnClickListener(this);
        switch_shot_instrument.setOnClickListener(this);
        switch_chat_camre.setOnClickListener(this);
        switch_hardware_host.setOnClickListener(this);
        switch_hardware_speaker.setOnClickListener(this);
        tv_shot_instrument.setOnClickListener(this);
        tv_chat_camre.setOnClickListener(this);
    }

    private void init() {
        setTitleName("硬件管理");
        llColour = (LinearLayout) findViewById(R.id.ll1);
        llScene = (LinearLayout) findViewById(R.id.ll2);
        //设置tag
        switch_table_lamp = (SwitchView) findViewById(R.id.switch_table_lamp);
        switch_table_lamp.setTag(tableLampSwitch);
        switch_scene_lights = (SwitchView) findViewById(R.id.switch_scene_lights);
        switch_scene_lights.setTag(sceneLightsSwitch);
        switch_projector = (SwitchView) findViewById(R.id.switch_projector);
        switch_projector.setTag(projectorSwitch);
        switch_shot_instrument = (SwitchView) findViewById(R.id.switch_shot_instrument);
        switch_shot_instrument.setTag(shotInstrumentSwitch);
        switch_chat_camre = (SwitchView) findViewById(R.id.switch_chat_camre);
        switch_chat_camre.setTag(chatCamreSwitch);
        switch_hardware_host = (SwitchView) findViewById(R.id.switch_hardware_host);
        switch_hardware_host.setTag(hardwareHostSwitch);
        switch_hardware_speaker = (SwitchView) findViewById(R.id.switch_hardware_speaker);
        switch_hardware_speaker.setTag(hardwareSpeakerSwitch);
        tv_shot_instrument = (TextView) findViewById(R.id.tv_shot_instrument);
        tv_chat_camre = (TextView) findViewById(R.id.tv_chat_camre);
        dialog = new HardwareDialog(this);
        addColors(colors);

        addScene(SceneBean.getData2());


        tableYellowBar = (SeekBar) findViewById(R.id.sb);
        tableYellowBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                LogUtil.i("llj", "黄灯进度值------->>>" + seekBar.getProgress());
                HardwareOrder order = new HardwareOrder();
                order.type = HardwareOrder.ORDER_TYPE_TABLE_LAMP_YELLOW;
                order.value1 = seekBar.getProgress() + "";
                // 发送拓展消息
                IMClient.sendExtMessage(Json.toJson(order), toUserList, "");
            }
        });
        tableWhiteBar = (SeekBar) findViewById(R.id.sb_2);
        tableWhiteBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                LogUtil.i("llj", "白灯进度值------->>>" + seekBar.getProgress());
                HardwareOrder order = new HardwareOrder();
                order.type = HardwareOrder.ORDER_TYPE_TABLE_LAMP_WHITE;
                order.value1 = seekBar.getProgress() + "";
                // 发送拓展消息
                IMClient.sendExtMessage(Json.toJson(order), toUserList, "");
            }
        });
        volumeBar = (SeekBarTopText) findViewById(R.id.hardware_voice_control_seek_bar);
        volumeBar.setListener(new SeekBarTopText.OnStopTrackingTouchListener() {
            @Override
            public void onStopTouch(int progress) {
                LogUtil.i("llj", "音量进度值------->>>" + progress);
                HardwareOrder order = new HardwareOrder();
                order.type = HardwareOrder.ORDER_TYPE_VOLUME;
                order.value1 = progress + "";
                // 发送拓展消息
                IMClient.sendExtMessage(Json.toJson(order), toUserList, "");
            }
        });
    }

    /**
     * 添加颜色选择控件
     *
     * @param str
     */
    private void addColors(List<String> str) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(SizeUtils.dp2px(this, 40), SizeUtils.dp2px(this, 40));
        params.weight = 1;
        params.setMargins(SizeUtils.dp2px(this, 10), SizeUtils.dp2px(this, 10), SizeUtils.dp2px(this, 10), SizeUtils.dp2px(this, 10));

        for (int i = 0; i < str.size(); i++) {
            View view = new View(this);
//            Glide.with(this).load(str.get(i)).transform(new GlideCircleTransform(this)).into(i);
            String[] rgbs = str.get(i).split(",");
            view.setBackgroundColor(Color.rgb(Integer.valueOf(rgbs[0]), Integer.valueOf(rgbs[1]), Integer.valueOf(rgbs[2])));
            view.setLayoutParams(params);
            view.setTag(rgbs);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] rgb = (String[]) v.getTag();
                    HardwareOrder order = new HardwareOrder();
                    order.type = HardwareOrder.ORDER_TYPE_SCENE_LIGHT_COLOR;
                    order.value1 = rgb[0];
                    order.value2 = rgb[1];
                    order.value3 = rgb[2];
                    // 发送拓展消息
                    IMClient.sendExtMessage(Json.toJson(order), toUserList, "");
                }
            });
            llColour.addView(view);
        }
    }

    /**
     * 添加情景灯背景控件
     */
    private void addScene(List<SceneBean> list) {
        llScene.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(SizeUtils.dp2px(this, 60), SizeUtils.dp2px(this, 60));
        params.leftMargin = SizeUtils.dp2px(this, 20);
//        params.weight = 1;
        for (int i = 0; i < list.size(); i++) {
            SceneView sv = new SceneView(this);
            sv.setData(list.get(i).getName(), list.get(i).getResId());
            sv.setLayoutParams(params);
            sv.setGravity(Gravity.CENTER);
            sv.setTag(i);
            sv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
//                    String position = String.valueOf(v.getTag());
                    HardwareOrder order = new HardwareOrder();
                    order.type = HardwareOrder.ORDER_TYPE_HARDWARE_FACE;
                    // TODO 暂时这样写
                    switch (position){
                        case 0:
                            // 倒计时
                            order.value1 = "http://yanfayi.cn:8888/img/shu.gif";
                            order.value2 = "http://yanfayi.cn:8888/img/shu.wav";
                            break;
                        case 1:
                            // 早上好
                            order.value1 = "http://yanfayi.cn:8888/img/zao.gif";
                            order.value2 = "http://yanfayi.cn:8888/img/zao.wav";
                            break;
                        case 2:
                            // 真棒
                            order.value1 = "http://yanfayi.cn:8888/img/ok.gif";
                            order.value2 = "http://yanfayi.cn:8888/img/ok.wav";
                            break;
                        default:
                            break;
                    }
                    // 发送拓展消息
                    IMClient.sendExtMessage(Json.toJson(order), toUserList, "");
                }
            });
            llScene.addView(sv);
        }
    }

    // TODO: 2017/9/21 点击后还需要传递数据给后台，从而达到控制hapilo
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_table_lamp:
                setOnOff(switch_table_lamp);
                break;
            case R.id.switch_scene_lights:
                setOnOff(switch_scene_lights);
                break;
            case R.id.switch_projector:
                setOnOff(switch_projector);
                break;
            case R.id.switch_shot_instrument:
                setOnOff(switch_shot_instrument);
                break;
            case R.id.switch_chat_camre:
                setOnOff(switch_chat_camre);
                break;
            case R.id.switch_hardware_host:
                setOnOff(switch_hardware_host);
                break;
            case R.id.switch_hardware_speaker:
                setOnOff(switch_hardware_speaker);
                break;
            case R.id.tv_shot_instrument:
                dialog.show();
                break;
            case R.id.tv_chat_camre:
                dialog.show();
                break;
            default:
                break;
        }
    }

    /**
     * 设置开关状态改变
     *
     * @param v
     */
    private void setOnOff(SwitchView v) {
        boolean switchValues = (boolean) v.getTag();
        switchValues = !switchValues;
        v.setVisibil(switchValues);
        v.setTag(switchValues);
    }

    public static void startActivity(Context context, int deviceId,String deviceCode) {
        Intent intent = new Intent(context, HardwareManagementActivity.class);
        intent.putExtra("deviceId", deviceId);
        intent.putExtra("deviceCode",deviceCode);
        context.startActivity(intent);
    }
}
