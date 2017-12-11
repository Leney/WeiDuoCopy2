package com.zhongke.weiduo.mvp.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.StringUtils;

/**
 * Created by ${tanlei} on 2017/9/5.
 */

public class AdvisoryDialog extends Dialog implements View.OnClickListener {
    private View view;
    private EditText etPhone, etText;
    private ImageView ivBack;
    private TextView tvSubmit;
    private OnSubmitClickListener onSubmitClickListener;
    private Context context;

    public void setOnSubmitClickListener(OnSubmitClickListener onSubmitClickListener) {
        this.onSubmitClickListener = onSubmitClickListener;
    }

    public AdvisoryDialog(@NonNull Context context) {
        this(context, 0);
        init(context);
    }

    public AdvisoryDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.DialogTheme_no_title);
        init(context);
    }

    private void init(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_advisory, null);
        setContentView(view);
        this.context = context;
        ivBack = (ImageView) view.findViewById(R.id.iv);
        tvSubmit = (TextView) view.findViewById(R.id.tv_submit);
        etPhone = (EditText) view.findViewById(R.id.et_phone);
        etText = (EditText) view.findViewById(R.id.et_text);
        ivBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv) {
            this.dismiss();
        } else if (R.id.tv_submit == v.getId()) {
            String phone = etPhone.getText().toString();
            if (!StringUtils.isPhone(phone)) {
                Toast.makeText(context, context.getResources().getString(R.string.register_tips_2), Toast.LENGTH_SHORT).show();
                return;
            }
            String text = etText.getText().toString();
            if (phone.equals("") || text.equals(""))
                return;
            if (onSubmitClickListener != null) {
                onSubmitClickListener.onSubmitClick(phone, text);
            }
            dismiss();
        }
    }

    /**
     * 回调接口
     */
    public interface OnSubmitClickListener {
        void onSubmitClick(String phone, String text);
    }
}
