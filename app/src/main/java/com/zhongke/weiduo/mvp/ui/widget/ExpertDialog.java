package com.zhongke.weiduo.mvp.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;

/**
 * Created by ${tanlei} on 2017/9/5.
 */

public class ExpertDialog extends Dialog implements View.OnClickListener {
    private View view;
    private ImageView ivBack;
    private TextView tv1, tv2, tv3, tv4;

    public ExpertDialog(@NonNull Context context) {
        this(context, 0);
        init(context);
    }

    public ExpertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.DialogTheme_no_title);
        init(context);
    }

    private void init(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_service_description, null);
        setContentView(view);
        ivBack = (ImageView) view.findViewById(R.id.iv);
        ivBack.setOnClickListener(this);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        tv4 = (TextView) view.findViewById(R.id.tv4);
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }

    /**
     * 设置内容
     *
     * @param str1 第一个标题
     * @param str2 第一个标题的内容
     * @param str3 第二个标题
     * @param str4 第二个标题内容
     */
    public void setContent(String str1, String str2, String str3, String str4) {
        tv1.setText(str1);
        tv2.setText(str2);
        tv3.setText(str3);
        tv4.setText(str4);
    }
}
