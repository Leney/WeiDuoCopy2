package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by ${xingen} on 2017/6/21.
 * 显示活动的TextView
 */

public class ActivityIntroductionTextView extends LinearLayout {
    private Context context;
    private TextView content_tv;
    public ActivityIntroductionTextView(Context context) {
        super(context);
        initConfig(context);
    }

    public ActivityIntroductionTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initConfig(context);
    }

    private void initConfig(Context context) {
        this.context = context;
        setStyle();
        this.addView(createTitle());
        this.content_tv = createContent();
        this.addView(content_tv);
    }

    /**
     * 设置特征
     */
    private void setStyle() {
        this.setOrientation(VERTICAL);
        this.setPadding(DisplayUtils.dip2px(context, 10), DisplayUtils.dip2px(context, 17), DisplayUtils.dip2px(context, 14), DisplayUtils.dip2px(context, 22));
        this.setBackgroundColor(ColorUtils.stringToColor("#ffffff"));
    }

    /**
     * 创建标题的textview
     *
     * @return
     */
    private TextView createTitle() {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        textView.setTextColor(ColorUtils.stringToColor("#333333"));
        textView.setTextSize(16);
        textView.getPaint().setFakeBoldText(true);
        textView.setText("活动介绍");
        return textView;
    }

    private TextView createContent() {
        TextView textView = new TextView(context);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = DisplayUtils.dip2px(context, 16);
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(ColorUtils.stringToColor("#666666"));
      //  textView.setTextSize(DisplayUtils.sp2px(context, 14));
        textView.setTextSize(14);
        textView.setText("健身登山可以方便地结合野外生存历练、人体潜能扩展和环保意识的强化，使参与者在强身、养心、智慧等多方面收到成效。");
        return textView;
    }

    /**
     * 设置活动内容
     *
     * @param content
     */
    public void setText(String content) {
        if (!TextUtils.isEmpty(content)) {
            this.content_tv.setText(content);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(this.getLayoutParams() instanceof RelativeLayout.LayoutParams  ){
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.getLayoutParams();
            layoutParams.topMargin = DisplayUtils.dip2px(context, 10);
        }else if(this.getLayoutParams() instanceof LinearLayout.LayoutParams  ){
            LinearLayout.LayoutParams layoutParams = ( LinearLayout.LayoutParams)this.getLayoutParams();
            layoutParams.topMargin = DisplayUtils.dip2px(context, 10);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
