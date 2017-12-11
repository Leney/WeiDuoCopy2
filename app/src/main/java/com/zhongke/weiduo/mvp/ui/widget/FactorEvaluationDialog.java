package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.mvp.ui.widget.dialog.BaseDialog;
import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by ${xingen} on 2017/6/28.
 * 核心要素评价
 */

public class FactorEvaluationDialog extends BaseDialog {


    public FactorEvaluationDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context, onClickListener);
        setDialogWidth(0.8f);
    }

    @Override
    protected View getRootView() {
        LinearLayout linearLayout = createLinearLayout();
        linearLayout.addView(createTitle());
        linearLayout.addView(createLine());
        String[] title=getTitle();
        String[] content=getContent();
        for (int i = 0; i < title.length; ++i) {
            TextView textView = createLargeText();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(DisplayUtils.dip2px(context, 20), DisplayUtils.dip2px(context, 20), 0, 0);
            textView.setLayoutParams(layoutParams);
            textView.setText(title[i]);
            linearLayout.addView(textView);
            TextView contentTv=createSmallText(content[i]);
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams1.setMargins(DisplayUtils.dip2px(context, 20), DisplayUtils.dip2px(context, 7), DisplayUtils.dip2px(context, 20), i==title.length-1?DisplayUtils.dip2px(context,30):0);
            contentTv.setLayoutParams(layoutParams1);
            linearLayout.addView(contentTv);
        }
        return linearLayout;
    }

    @Override
    protected void initView(View rootView) {


    }
    private String[]  getTitle(){
        return new String[]{"观察能力", "注意力", "身体机能", "思维逻辑"};
    }
    private String[] getContent(){
        return new String[]{"学会观察影响十五发展的周边环境，及其变化，逻辑性强，科学应用 双击分析能力。",
                "学会观察影响十五发展的周边环境，及其变化，逻辑性强，科学应用 双击分析能力。",
                "学会观察影响十五发展的周边环境，及其变化，逻辑性强，科学应用 双击分析能力。",
                "学会观察影响十五发展的周边环境，及其变化，逻辑性强，科学应用 双击分析能力。"};
    }
    private LinearLayout createLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundColor(ColorUtils.stringToColor("#ffffff"));
        return linearLayout;
    }

    private RelativeLayout createTitle() {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setPadding(DisplayUtils.dip2px(context, 20), DisplayUtils.dip2px(context, 16), DisplayUtils.dip2px(context, 16), DisplayUtils.dip2px(context, 20));
        TextView textView = createLargeText();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        textView.setLayoutParams(layoutParams);
        textView.setText("核心培养要素评价");
        relativeLayout.addView(textView);
        return relativeLayout;
    }

    private TextView createLine() {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtils.dip2px(context, 1)));
        textView.setBackgroundColor(ColorUtils.stringToColor("#eeeeee"));
        return textView;
    }

    /**
     * 创建大的子体
     *
     * @return
     */
    private TextView createLargeText() {
        TextView textView = new TextView(context);
        textView.setTextSize(16);
        textView.setTextColor(ColorUtils.stringToColor("#333333"));
        return textView;
    }

    /**
     * 创建大的子体
     *
     * @return
     */
    private TextView createSmallText(String text) {
        TextView textView = new TextView(context);
        textView.setTextSize(14);
        textView.setTextColor(ColorUtils.stringToColor("#999999"));
        textView.setText(text);
        return textView;
    }
}
