package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.activity.PersonalActivity;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by hyx on 2017/9/25.
 */

public class ChooseProfileDialog extends Dialog implements View.OnClickListener {
    private File mImageFile;
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_ALBUM = 2;

    private Context context;
    public ChooseProfileDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }


    public ChooseProfileDialog(Context context,int style) {
        super(context,style);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_choose_profile_picture,null);
        initView(view);
        setContentView(view);
    }

    public void initView(View rootView) {

        LinearLayout cancel = (LinearLayout) rootView.findViewById(R.id.cancel);

        cancel.setOnClickListener(this);

        Window win = getWindow();
        if (win != null) {
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = StringUtils.getPhoneMetrics(context).widthPixels;
            win.setAttributes(lp);
            win.setGravity(Gravity.BOTTOM);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

}
