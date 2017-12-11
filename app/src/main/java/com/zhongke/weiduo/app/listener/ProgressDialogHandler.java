package com.zhongke.weiduo.app.listener;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.zhongke.weiduo.mvp.ui.widget.ProgessDialog;


/**
 * Created by liukun on 16/3/10.
 */
public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgessDialog pd;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog(){
        if (pd == null) {
            pd = new ProgessDialog(context,mProgressCancelListener);


//            pd.setCancelable(cancelable);
//
//            if (cancelable) {
//
//                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialogInterface) {
//                        mProgressCancelListener.onCancelProgress();
//                    }
//                });
//            }
            pd.showLoading();
//            if (!pd.isShowing()) {
//                pd.show();
//            }
        }
    }

    private void dismissProgressDialog(){
        if (pd != null) {
            pd.hiddenLoading();

        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

}
