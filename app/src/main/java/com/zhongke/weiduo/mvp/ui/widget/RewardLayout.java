package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.mvp.ui.adapter.ActivityPrizeAdapter;
import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by ${xingen} on 2017/6/27.
 * 奖励的布局
 */

public class RewardLayout extends LinearLayout {
    private TextView title_tv;
    private GridView gridView;
    private Context context;

    public RewardLayout(Context context) {
        super(context);
        this.context = context;
        this.setWillNotDraw(false);
    }

    public RewardLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOrientation(VERTICAL);

    }

    private TextView createTV(String name) {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundColor(ColorUtils.stringToColor("#f5f5f5"));
        textView.setTextColor(ColorUtils.stringToColor("#999999"));
        textView.setPadding(DisplayUtils.dip2px(context, 10), DisplayUtils.dip2px(context, 8), 0, DisplayUtils.dip2px(context, 8));
        textView.setTextSize(14);
        textView.setText(name);
        return textView;
    }

    private GridView createGridView() {
        GridView gridView = new WapGridView(context);
        gridView.setNumColumns(4);
      /*  gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setHorizontalSpacing(DisplayUtils.dip2px(context, 30));
        gridView.setVerticalSpacing(DisplayUtils.dip2px(context, 20));*/

        LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        int margin = DisplayUtils.dip2px(context, 20);
      //  layoutParams.setMargins(margin, margin, margin, margin);
        gridView.setLayoutParams(layoutParams);
        return gridView;
    }

    public void addData(String name) {
        this.addView(createTV(name));
        GridView gridView = createGridView();
        gridView.setAdapter(new ActivityPrizeAdapter(context));
        this.addView(gridView);
    }

}
