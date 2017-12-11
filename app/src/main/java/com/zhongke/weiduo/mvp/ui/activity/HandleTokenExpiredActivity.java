package com.zhongke.weiduo.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.library.rxjava.ObservableUtils;
import com.zhongke.weiduo.library.rxjava.SubscribeUtils;
import com.zhongke.weiduo.mvp.ui.widget.dialog.HandelTokenExpireDialog;
import com.zhongke.weiduo.util.ActivityUtils;

/**
 * Created by ${xingen} on 2017/11/8.
 *
 * 一个全局的Dialog用于，处理token过期
 */

public class HandleTokenExpiredActivity  extends Activity{
    private HandelTokenExpireDialog handelTokenExpireDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        showDialog();
        handleTokenData();
    }

    /**
     * 清空token
     */
    private  void handleTokenData(){
        ZkApplication.getInstance().setToken("");
        ObservableUtils.createDeletePersonMSG().compose(SubscribeUtils.createTransformer()).subscribe();
    }
    private void showDialog() {
        this.handelTokenExpireDialog=new HandelTokenExpireDialog(this,view->{
            handelTokenExpireDialog.dismiss();
            ActivityUtils.finishAllActivity();
            LoginActivity.startActivity(HandleTokenExpiredActivity.this);
        });
        if (!this.handelTokenExpireDialog.isShowing()){
            this.handelTokenExpireDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handelTokenExpireDialog!=null&&handelTokenExpireDialog.isShowing()){
            handelTokenExpireDialog.dismiss();
        }
        ActivityUtils.removeActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
