package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.ActivityDetailBean;
import com.zhongke.weiduo.util.DisplayUtils;

import java.util.List;

/**
 * Created by ${xingen} on 2017/9/25.
 */

public class ActivityDetailAdapter extends BaseQuickAdapter<ActivityDetailBean.DetailBean, BaseViewHolder> {
    private int grayColor, blackColor;
    public ActivityDetailAdapter(@LayoutRes int layoutResId, @Nullable List<ActivityDetailBean.DetailBean> data) {
        super(layoutResId, data);
        this.grayColor = Color.parseColor("#666666");
        this.blackColor = Color.parseColor("#333333");
    }

    @Override
    protected void convert(BaseViewHolder helper, ActivityDetailBean.DetailBean item) {
        Context context = helper.itemView.getContext();
        String name = item.name;
        helper.setText(R.id.item_activity_detail_title_tv, name);
        LinearLayout linearLayout = helper.getView(R.id.item_activity_detail_content_layout);
        linearLayout.removeAllViews();
        switch (name) {
            case "活动资料":
                linearLayout.addView(createContent(context,"活动时间","2017.9.10 上午10:00",blackColor,grayColor));
                linearLayout.addView(createContent(context,"活动地点","Hapilo支持区域",blackColor,grayColor));
                linearLayout.addView(createContent(context,"活动对象","6-12岁亲子家庭",blackColor,grayColor));
                linearLayout.addView(createContent(context,"活动人数","8-15人",blackColor,grayColor));
                linearLayout.addView(createContent(context,"硬件支持","Hapilo",blackColor,grayColor));
                break;
            case "活动流程":
                linearLayout.addView(createContent(context,"10:00-10:10","比赛时间",grayColor,blackColor));
                linearLayout.addView(createContent(context,"10:20-10:30","抢答时间",grayColor,blackColor));
                linearLayout.addView(createContent(context,"10:40-10:50","比赛颁奖",grayColor,blackColor));
                break;
            case "活动奖励":
                linearLayout.addView(createContent(context,"第一名","100积分   紫光勋章",blackColor,blackColor));
                linearLayout.addView(createContent(context,"第二名","90积分    国光勋章",blackColor,blackColor));
                linearLayout.addView(createContent(context,"第三名","80积分    蓝光勋章",blackColor,blackColor));
                break;
        }

    }


    private LinearLayout createContent(Context context, String text1, String text2, int color1, int color2) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = DisplayUtils.dip2px(context, 12);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        TextView textView1 = createContentChild(context, text1, color1);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView1.setLayoutParams(layoutParams1);

        TextView textView2 = createContentChild(context, text2, color2);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams2.leftMargin = DisplayUtils.dip2px(context, 10);
        textView2.setLayoutParams(layoutParams2);

        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        return linearLayout;
    }

    private TextView createContentChild(Context context, String text, int color) {
        TextView textView1 = new TextView(context);
        textView1.setTextSize(14);
        textView1.setText(text);
        textView1.setTextColor(color);
//        if (color == blackColor) {
//            textView1.getPaint().setFakeBoldText(true);
//        }
        return textView1;
    }
}
