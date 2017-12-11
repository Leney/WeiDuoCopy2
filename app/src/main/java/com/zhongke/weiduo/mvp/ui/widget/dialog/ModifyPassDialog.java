package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.StringUtil;

/**
 * Created by ${tanlei} on 2017/9/26.
 * 修改登录密码
 */

public class ModifyPassDialog extends Dialog implements View.OnClickListener {
    private Activity activity;
    /**
     * 三个输入框
     */
    private EditText etOriginalPas, etNewPas, etRepeatPas;
    private TextView tvCancel, tvDetermine;
    private DetermineListener determineListener;

    public ModifyPassDialog(@NonNull Context context) {
        this(context, 0);
    }

    public ModifyPassDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.DialogTheme_no_title);
        this.activity = (Activity) context;
        View rootView = View.inflate(context, R.layout.dialog_modify_pass, null);
        setContentView(rootView);
        initView(rootView);
        setDialogWidth();
    }

    private void initView(View rootView) {
        etOriginalPas = (EditText) rootView.findViewById(R.id.infos_input);
        etNewPas = (EditText) rootView.findViewById(R.id.et2);
        etRepeatPas = (EditText) rootView.findViewById(R.id.et3);
        tvCancel = (TextView) rootView.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
        tvDetermine = (TextView) rootView.findViewById(R.id.tv_determine);
        tvDetermine.setOnClickListener(this);
    }


    /**
     * 设置屏幕宽度
     */
    private void setDialogWidth() {
        Window window = getWindow();
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.65); // 宽度设置为屏幕的0.65
        window.setAttributes(p);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel) {
            dismiss();
        } else if (v.getId() == R.id.tv_determine) {


            String originalPas = etOriginalPas.getText().toString().trim();
            String newPas = etNewPas.getText().toString().trim();
            String repeatPas = etRepeatPas.getText().toString().trim();
            if (determineListener !=null) {
                determineListener.determine(originalPas,newPas,repeatPas);
            }
            /*if (newPas.equals(repeatPas) && !StringUtil.isNull(newPas)) {
                if (originalPas.equals("")) {
                    // TODO: 2017/9/26 这里需要判断和后台的密码是否一致
                    Toast.makeText(activity, "修改成功", Toast.LENGTH_SHORT).show();
                    dismiss();
                } else {
                    // TODO: 2017/9/26 还需要提供密码规则验证
                    Toast.makeText(activity, "原始密码不正确请重新输入", Toast.LENGTH_SHORT).show();
                    etRepeatPas.setText("");
                    etNewPas.setText("");
                    etOriginalPas.setText("");
                }
            } else {
                Toast.makeText(activity, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                etRepeatPas.setText("");
                etNewPas.setText("");
                etOriginalPas.setText("");
            }*/
        }
    }


    public interface DetermineListener {
        void determine(String originalPas,String newPas,String repeatPas);
    }

    public void setDetermineListener(DetermineListener listener) {
        this.determineListener = listener;
    }

}
