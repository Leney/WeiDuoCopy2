package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.view.View;

import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by ${xinGen} on 2017/9/21.
 * <p>
 * Module信息控件
 */

public class ModuleMessageView extends View {
    private Context context;
    private ModuleMessage moduleMessage;
    private DrawFilter pfdf;

    public ModuleMessageView(Context context) {
        super(context);
        this.context = context.getApplicationContext();
        initConfig();
    }

    private Paint paint;

    private void initConfig() {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.moduleCircleRadius = DisplayUtils.dip2px(context, Configuration.moduleCircleRadius);
        this.messageCircleRadius = DisplayUtils.dip2px(context, Configuration.messageCircleRadius);
        this.pfdf = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    private int moduleCircleRadius, messageCircleRadius;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (moduleMessage == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {//手动代码
            int width = moduleCircleRadius * 2 + (int) (messageCircleRadius * 2 * Configuration.paddingRight);
            int height = moduleCircleRadius * 2 + (int) (messageCircleRadius * 2 * Configuration.paddingTop);
            // int width = moduleMessage.messageSize == 0 ? moduleCircleRadius * 2 : moduleCircleRadius * 2 + (int) (messageCircleRadius * 2 * Configuration.paddingRight);
            //  int height = moduleMessage.messageSize == 0 ? moduleCircleRadius * 2 : moduleCircleRadius * 2 + (int) (messageCircleRadius * 2 * Configuration.paddingTop);
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(pfdf);
        if (moduleMessage != null) {
            drawModule(canvas);
            if (moduleMessage.messageSize > 0) {
                drawMessage(canvas);
            }
        }
    }

    /**
     * 添加ModuleMessage,且通知改变
     *
     * @param moduleMessage
     */
    public void addModuleNotify(ModuleMessage moduleMessage) {
        this.moduleMessage = moduleMessage;
        requestLayout();
        this.invalidate();
    }

    /**
     * 添加ModuleMessage
     *
     * @param moduleMessage
     */
    public void addModule(ModuleMessage moduleMessage) {
        this.moduleMessage = moduleMessage;
    }

    /**
     * 绘制Module
     *
     * @param canvas
     */
    private void drawModule(Canvas canvas) {

        float cx = moduleCircleRadius;
        float cy = getHeight() - moduleCircleRadius;
        paint.reset();
        paint.setAntiAlias(true);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx, cy, moduleCircleRadius - 1, paint);

        paint.setColor(Configuration.moduleCircleColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        canvas.drawCircle(cx, cy, moduleCircleRadius - 1, paint);

        Rect rect = new Rect();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setColor(Configuration.moduleTextColor);
        paint.setTextSize(DisplayUtils.sp2px(context, Configuration.moduleTextSize));
        paint.getTextBounds(moduleMessage.moduleName, 0, moduleMessage.moduleName.length(), rect);
        float left = cx - rect.width() / 2;
        float bottom = cy + rect.height() / 2;
        canvas.drawText(moduleMessage.moduleName, left, bottom, paint);
    }

    /**
     * 绘制信息
     *
     * @param canvas
     */
    private void drawMessage(Canvas canvas) {
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Configuration.messageCircleColor);
        paint.setStyle(Paint.Style.FILL);
        float cx = getWidth() - messageCircleRadius;
        float cy = messageCircleRadius;
        canvas.drawCircle(cx, cy, messageCircleRadius, paint);

        Rect rect = new Rect();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setColor(Configuration.messageTextColor);
        paint.setTextSize(DisplayUtils.sp2px(context, Configuration.messageTextSize));
        String messageSize = String.valueOf(moduleMessage.messageSize);
        paint.getTextBounds(messageSize, 0, messageSize.length(), rect);
        float left = cx - rect.width() / 2;
        float bottom = cy + rect.height() / 2;
        canvas.drawText(messageSize, left, bottom, paint);
    }

    /**
     * 一个配置类
     */
    private static final class Configuration {
        //大圆信息
        public static final int moduleTextColor = Color.parseColor("#8b8b8b");
        public static final int moduleTextSize = 12;
        public static final float moduleCircleRadius = 16.5f;
        public static final int moduleCircleColor = Color.parseColor("#cac8c8");
        //小圆信息
        public static final int messageTextColor = Color.parseColor("#ffffff");
        public static final int messageTextSize = 10;

        public static final int messageCircleColor = Color.parseColor("#ff4a4a");
        public static final float messageCircleRadius = 8f;
        //大圆和小圆的间距
        public static final float paddingTop = (3.7f / (messageCircleRadius * 2));
        public static final float paddingRight = (4.7f / (messageCircleRadius * 2));
    }

    /**
     * 一个存储数据的实体类
     */
    public static class ModuleMessage {
        public String moduleName;
        public int messageSize;
        //目标
        public static final String MODEL_TARGET = "目标";
        //计划
        public static final String MODEL_PLAN = "计划";
        //规划
        public static final String MODEL_PROGRAM = "规划";
        //活动
        public static final String MODEL_ACTIVITY = "活动";
    }
}
