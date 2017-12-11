package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.lisenter.ErrorLisenter;
import com.cjt2325.cameralibrary.lisenter.JCameraLisenter;
import com.cjt2325.cameralibrary.util.DeviceUtil;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.common.AppConst;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.mvp.base.BaseMainActivity;
import com.zhongke.weiduo.mvp.base.BaseMainPresenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Karma on 2017/6/27.
 * 类描述：拍照和小视频
 */

public class TakePhotoActivity extends BaseMainActivity {
    private static final String TAG = "TakePhotoActivity";

    private JCameraView jCameraView;

    @Override
    public void init() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void initView() {

        jCameraView = (JCameraView) findViewById(R.id.jcameraview);
        //设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);
    }

    @Override
    public void initListener() {
        jCameraView.setErrorLisenter(new ErrorLisenter() {
            @Override
            public void onError() {
                //错误监听
                Log.i("CJT", "camera error");
                Intent intent = new Intent();
                setResult(103, intent);
                finish();
            }

            @Override
            public void AudioPermissionError() {
                Toast.makeText(TakePhotoActivity.this, "给点录音权限可以?", Toast.LENGTH_SHORT).show();
            }
        });
        //JCameraView监听
        jCameraView.setJCameraLisenter(new JCameraLisenter() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                //获取图片bitmap
//                Log.i("JCameraView", "bitmap = " + bitmap.getWidth());
             /*   String path = FileUtil.saveBitmap("JCamera", bitmap);
                Intent intent = new Intent();
                intent.putExtra("path", path);
                setResult(101, intent);
                finish();*/
                //获取到拍照成功后返回的Bitmap
                String path = saveBitmap(bitmap, AppConst.PHOTO_SAVE_DIR);
                Intent data = new Intent();
                data.putExtra("take_photo", true);
                data.putExtra("path", path);
                setResult(RESULT_OK, data);
                finish();
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
              /*  //获取视频路径
                LogUtil.e(TAG, "url = " + url + ", Bitmap = " + firstFrame.getWidth());
                Intent intent = new Intent();
                intent.putExtra("path", url);
                setResult(102, intent);
                finish();*/
                //获取成功录像后的视频路径
                Intent data = new Intent();
                data.putExtra("take_photo", false);
                data.putExtra("path", url);
                setResult(RESULT_OK, data);
                finish();
            }

            @Override
            public void quit() {
                //退出按钮
                TakePhotoActivity.this.finish();
            }
        });

        LogUtil.e(TAG, DeviceUtil.getDeviceModel());
    }

    @Override
    protected BaseMainPresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_take_photo;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    protected void onResume() {
        LogUtil.e(TAG, "onResume");
        super.onResume();
        jCameraView.onResume();
    }

    @Override
    protected void onPause() {
        LogUtil.e(TAG, "onPause");
        super.onPause();
        jCameraView.onPause();
    }

    public String saveBitmap(Bitmap bm, String dir) {
        String path = "";
        File f = new File(dir, "CSDN_LQR_" + SystemClock.currentThreadTimeMillis() + ".png");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            path = f.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
