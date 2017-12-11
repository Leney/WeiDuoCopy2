package com.zhongke.weiduo.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.library.retrofit.HttpConstance;
import com.zhongke.weiduo.library.retrofit.ResponseResultListener;
import com.zhongke.weiduo.library.retrofit.RetrofitClient;
import com.zhongke.weiduo.mvp.model.entity.DeviceMsgBean;
import com.zhongke.weiduo.util.ToastUtil;
import com.zhongke.weiduo.zxing.camera.CameraManager;
import com.zhongke.weiduo.zxing.decoding.CaptureActivityHandler;
import com.zhongke.weiduo.zxing.decoding.InactivityTimer;
import com.zhongke.weiduo.zxing.view.ViewfinderView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static com.zhongke.weiduo.app.utils.UIUtils.showToast;


/**
 * Created by ${xingen} on 2017/7/10.
 * 二维码扫描结果界面
 */

public class MipcaActivityCapture extends AppCompatActivity implements SurfaceHolder.Callback{

    /**
     * 二维码扫描结果返回码
     */
    public static final int QD_SCAN_RESULT_CODE = 100011;
    /**
     * 扫码后跳转激活设备界面的标识
     */
    public static final int QD_SCAN_ACTIIVY_DEVICE_CODE=110;
    // private CameraManager cameramanager;
    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_capture);

        initView();

        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
    }
    private void initView() {
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
    }

    /**
     * 开启扫描界面
     * @param activity
     */
    public static void openActivity(Activity activity){
        Intent intent=new Intent(activity,MipcaActivityCapture.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivityForResult(intent,10011);
    }

    /**
     *  处理扫描结果
      */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String s=result.getText().toString().trim();
        if (s.contains(HttpConstance.BASE_URL)){
            String deviceCode=s.substring(s.lastIndexOf("=")+1,s.length());
             checkDeviceMSG(deviceCode);
        }else{
            Intent intent = new Intent();
            intent.putExtra("result",s);
            setResult(QD_SCAN_RESULT_CODE,intent);
            finish();
        }

    }
    private void checkDeviceMSG(String deviceCode) {
        Map<String,String> params=new HashMap<>();
        params.put("deviceCode",deviceCode);
        RetrofitClient.getInstance().checkDeviceMSG(params, new ResponseResultListener<DeviceMsgBean>() {
            @Override
            public void success(DeviceMsgBean deviceMsgBean) {
                Log.i("激活设备",deviceCode+" "+deviceMsgBean.getResCode()+" "+ ZkApplication.getInstance().getToken());
                switch (deviceMsgBean.getResCode()){
                    //已经关联
                    case 1:
                        ToastUtil.show(getApplicationContext(),"该设备已经关联", Toast.LENGTH_SHORT);
                        break;
                    //设备已经激活，未关联
                    case 2:
                        DeviceBindActivity.startActivity(MipcaActivityCapture.this,deviceCode,deviceMsgBean.getUser().getNickName(),deviceMsgBean.getUser().getHeadImageUrl());
                        break;
                    //未激活
                    case 3:
                        ActivateDeviceActivity.startActivity(MipcaActivityCapture.this, TextUtils.isEmpty(deviceCode)?"A1234C": deviceCode);
                        break;
                    default:
                        break;
                }
                MipcaActivityCapture.this.finish();
            }
            @Override
            public void failure(CommonException e) {
                ToastUtil.show(getApplicationContext(),"查询设备失败，请重新操作", Toast.LENGTH_SHORT);
                MipcaActivityCapture.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (Exception e) {
            showToast("获取相机权限失败！");
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }
   @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
}
