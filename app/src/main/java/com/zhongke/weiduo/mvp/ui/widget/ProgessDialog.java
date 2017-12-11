package com.zhongke.weiduo.mvp.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.listener.ProgressCancelListener;
import com.zhongke.weiduo.app.utils.LogUtil;


/**
 * Created by karma on 2016/1/20 0020.
 */
public class ProgessDialog {


    private Dialog mProgessDialog;
    private Context mContext;
    private TextView textView;
    private ImageView imgLoad;

    private ProgressCancelListener progressCancelListener;
    public ProgessDialog(Context context) {
        this.mContext = context;
        createDialog(context);
    }
    public ProgessDialog(Context context, ProgressCancelListener progressCancelListener) {
        this.mContext = context;
        this.progressCancelListener = progressCancelListener;
        createDialog(context);
    }
    private void createDialog(Context context) {
        mProgessDialog = new Dialog(context, R.style.CustomProgressDialog);
        mProgessDialog.setCanceledOnTouchOutside(false);
        mProgessDialog.setContentView(setDialogView(context));
    }

    private View setDialogView(Context context) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.dialog_loading, null);

        textView = (TextView) view.findViewById(R.id.tv_msg);

        imgLoad = (ImageView) view.findViewById(R.id.img_load);

        RotateAnimation rotate = (RotateAnimation) AnimationUtils
                .loadAnimation(mContext, R.anim.refresh);
        rotate.setDuration(500);
        imgLoad.startAnimation(rotate);
        ImageView imgCancel = (ImageView) view
                .findViewById(R.id.img_cancel);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(progressCancelListener != null){
                    progressCancelListener.onCancelProgress();
                }
                mProgessDialog.dismiss();
            }
        });
        return view;
    }

    private void startAnim() {
        RotateAnimation rotate = (RotateAnimation) AnimationUtils
                .loadAnimation(mContext, R.anim.refresh);
        rotate.setDuration(500);
        if (imgLoad != null)
            imgLoad.startAnimation(rotate);
    }

    /**
     * [Summary]
     * setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public void setMessage(String strMessage) {
        if (textView != null) {
            textView.setText(strMessage);
        }
    }

    public void showLoading() {

        try {
            if (mProgessDialog.isShowing()) {
                return;
            }
            if (mProgessDialog == null) {
                createDialog(mContext);
            }
            mProgessDialog.show();
            startAnim();
        }catch (Exception e){
            LogUtil.e(e.getMessage());
        }

    }

    public void showLoading(String message) {

        try {
            if (mProgessDialog.isShowing()) {
                return;
            }
            if (mProgessDialog == null) {
                createDialog(mContext);
            }
            mProgessDialog.show();
            startAnim();
            setMessage(message);
        }catch (Exception e){
            LogUtil.e(e.getMessage());
        }

    }

    public void showLoading(String message, Boolean canCanel) {

        try {
            if (mProgessDialog == null) {
                createDialog(mContext);
            }
            if (mProgessDialog.isShowing()) {
                return;
            }
            startAnim();
            mProgessDialog.show();
            setMessage(message);
            mProgessDialog.show();
            mProgessDialog.setCancelable(canCanel);
        }catch (Exception e){
            LogUtil.e(e.getMessage());
        }

    }

    public void hiddenLoading() {

        try {
            if (mProgessDialog == null) {
                createDialog(mContext);
            }
            startAnim();
            if (mProgessDialog != null && mProgessDialog.isShowing()) {
                mProgessDialog.dismiss();
            }
        }catch (Exception e){
            LogUtil.e(e.getMessage());
        }

    }

    public boolean isShowing() {
        if (mProgessDialog.isShowing()) {
            return true;
        }
        return false;
    }




}
