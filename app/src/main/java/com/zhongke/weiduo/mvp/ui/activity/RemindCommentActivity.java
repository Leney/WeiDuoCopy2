package com.zhongke.weiduo.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.zhongke.weiduo.mvp.model.entity.TaskListBean;
import com.zhongke.weiduo.mvp.ui.widget.dialog.RemindCommentDialog;
import com.zhongke.weiduo.util.ActivityUtils;

/**
 * Created by ${xingen} on 2017/11/8.
 *
 * 一个全局的Dialog用于，处理token过期
 */

public class RemindCommentActivity extends Activity{
    private RemindCommentDialog remindCommentDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        showDialog();
    }

    private void showDialog() {
        this.remindCommentDialog=new RemindCommentDialog(this,view->{
            remindCommentDialog.dismiss();

            TaskListBean.MyTaskBean bean = new TaskListBean.MyTaskBean();
            //ActivityCommentActivity2.startActivity(RemindCommentActivity.this,1);
        });
        if (!this.remindCommentDialog.isShowing()){
            this.remindCommentDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (remindCommentDialog!=null&&remindCommentDialog.isShowing()){
            remindCommentDialog.dismiss();
        }
        ActivityUtils.removeActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
