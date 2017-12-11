package com.zhongke.weiduo.mvp.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zhongke.weiduo.mvp.ui.activity.HandleTokenExpiredActivity;
import com.zhongke.weiduo.util.ActivityUtils;

/**
 * Created by hyx on 2017/12/8.
 */

public class RemindCommentBroadcastReceiver extends BroadcastReceiver {

    public static Intent builderIntent() {
        Intent intent = new Intent();
        intent.setAction("com.zhongke.weiduo.mvp.broadcastreceiver.RemindCommentBroadcastReceiver");
        return intent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        handleRemindComment(context);
    }

    private void handleRemindComment(Context context) {
        ActivityUtils.openActivity(context,HandleTokenExpiredActivity.class);
    }


}
