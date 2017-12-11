package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by ${xingen} on 2017/6/22.
 * <p>
 * 一个根据不同状态的显示不同奖励的控件。
 */

public class RewardView extends View {
    private final  String TAG=RewardView.class.getSimpleName();
    private Paint paint;
    private Context context;
    /**
     * 圆圈的直径
     */
    private final int CICRLE_SIZE = 44;
    /**
     * 字体大小
     */
    private final int DIGITAL_SIZE_LARGE = 16;
    private final int DIGITAL_SIZE_SMALL = 12;
    /**
     * 文字到圆形的距离
     */
    private final int PADDING_TOP_SIZE = 5;
    /**
     * 字体颜色
     */
    private int digital_color_gray, digital_color_yellow, digital_color_red;
    /**
     * 圆圈颜色
     */
    private int circler_color_gray;
    /**
     * 商品的标志
     * 0 金币 1 积分 2 商城礼物 3 实物
     */
    public static final int STATE_0 = 0, STATE_1 = 1, STATE_2 = 2, STATE_3 = 3;
    private int currentRewardState = -1;
    private String currentRewardName;
    private String currentDigital;
    private Bitmap bitmap;

    public RewardView(Context context) {
        super(context);
        initConfig(context);
    }

    public RewardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initConfig(context);
    }

    /**
     * 初始化配置
     */
    private void initConfig(Context context) {
        this.context = context;
        //初始画笔
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        //初始色值
        this.digital_color_gray = ColorUtils.stringToColor("#999999");
        this.digital_color_red = ColorUtils.stringToColor("#ef5e52");
        this.digital_color_yellow = ColorUtils.stringToColor("#f5c607");
        this.circler_color_gray = ColorUtils.stringToColor("#cecece");
    }

    /**
     * 重写onDraw，绘制图形
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (currentRewardState == -1) return;
        Log.i(TAG,"onDraw "+currentRewardState);
        switch (currentRewardState) {
            case STATE_0:
                drawGoldReward(canvas);
                break;
            case STATE_1:
                drawMallReward(canvas);
                break;
            case STATE_2:
                drawIntegralReward(canvas);
                break;
            case STATE_3:
                drawEntityReward(canvas);
                break;
        }
    }

    /**
     * 重置画笔
     */
    private void restPaint() {
        paint.reset();
        paint.setAntiAlias(true);
    }

    /**
     * 绘制金币奖励
     *
     * @param canvas
     */
  private void drawGoldReward(Canvas canvas) {
        drawGrayCircle(canvas);
        drawLargeDigital(canvas, digital_color_yellow);
        drawBottomText(canvas);
    }

    /**
     * 绘制商城奖励
     *
     * @param canvas
     */
   private void drawMallReward(Canvas canvas) {
        drawLargeCircle(canvas);
        drawBottomText(canvas);
    }

    /**
     * 绘制积分奖励
     *
     * @param canvas
     */
   private void drawIntegralReward(Canvas canvas) {
        drawGrayCircle(canvas);
        drawLargeDigital(canvas, digital_color_red);
        drawBottomText(canvas);
    }

    /**
     * 绘制实体奖励
     *
     * @param canvas
     */
    private void drawEntityReward(Canvas canvas) {
        drawLargeCircle(canvas);
        drawBottomText(canvas);
    }

    /**
     * 绘制圆形bitmap
     * @param canvas
     */

    private void drawLargeCircle(Canvas canvas) {
        restPaint();
        float left = (float) getMeasuredWidth() / 2 - bitmap.getWidth() / 2;
        float top = 0;
        Log.i(TAG,"bitmap" +bitmap+"  top "+top +" "+left );

        canvas.drawBitmap(bitmap, left, top, paint);
    }

    /**
     * 绘制圆形中粗体数字
     * @param canvas
     * @param color
     */
    private void drawLargeDigital(Canvas canvas, int color) {
        restPaint();
        paint.setColor(color);
        paint.setFakeBoldText(true);
        int y1 = DisplayUtils.dip2px(context, CICRLE_SIZE)-( DisplayUtils.dip2px(context, CICRLE_SIZE) -  (int) calculationDigitalHeight(DIGITAL_SIZE_LARGE))/2-DisplayUtils.dip2px(context,2f);
        float x1 =  getMeasuredWidth() / 2 - calculationDigitalWith(DIGITAL_SIZE_LARGE, currentDigital) / 2;
        canvas.drawText(currentDigital, x1, y1, paint);
    }

    /**
     * 绘制灰色的圆形
     * @param canvas
     */
    private void drawGrayCircle(Canvas canvas) {
        restPaint();
        paint.setColor(circler_color_gray);
        //设置画笔风格，为填充
        paint.setStyle(Paint.Style.STROKE);
        float cx =  getMeasuredWidth() / 2;
        float cy =  DisplayUtils.dip2px(context, CICRLE_SIZE) / 2;
        float radius = cy;
        canvas.drawCircle(cx, cy, radius, paint);
    }

    /**
     * 绘制底部文字
     * @param canvas
     */
    private void drawBottomText(Canvas canvas) {
        restPaint();
        paint.setColor(digital_color_gray);
        float y2 = DisplayUtils.dip2px(context, (CICRLE_SIZE + PADDING_TOP_SIZE)) + calculationDigitalHeight(DIGITAL_SIZE_SMALL)/2;

        float x2 = (float) getMeasuredWidth() / 2 - calculationDigitalWith(DIGITAL_SIZE_SMALL, currentRewardName) / 2;
        canvas.drawText(currentRewardName, x2, y2, paint);
    }

    /**
     * 实现自适应，重写onMeasure()
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSize = calcuationViewHeight();
        int withSize = calcuationViewWith();
        setMeasuredDimension(measureSize(withSize, widthMeasureSpec), measureSize(heightSize, heightMeasureSpec));
        Log.i(TAG," onMeasure "+heightSize+" "+withSize);
    }

    /**
     * 计算控件的长度
     *
     * @return
     */
    private int calcuationViewWith() {
        int circleWith = DisplayUtils.dip2px(context, CICRLE_SIZE);
        String defaultName = "金币";
        int textWith = (int) calculationDigitalWith(DIGITAL_SIZE_SMALL, currentRewardName == null ? defaultName : currentRewardName);
        return circleWith > textWith ? circleWith : textWith;
    }

    /**
     * 计算控件的高度
     *
     * @return
     */
    private int calcuationViewHeight() {
        return DisplayUtils.dip2px(context, (CICRLE_SIZE + PADDING_TOP_SIZE)) + (int) calculationDigitalHeight(DIGITAL_SIZE_SMALL);
    }

    /**
     * 计算字体的高度
     *
     * @param textSize
     * @return
     */
    private float calculationDigitalHeight(int textSize) {
        paint.setTextSize(DisplayUtils.sp2px(context, textSize));
        return ((-paint.ascent()) + paint.descent());
    }

    /**
     * 计算字体的长度
     *
     * @param textSize
     * @return
     */
    private float calculationDigitalWith(int textSize, String text) {
        paint.setTextSize(DisplayUtils.sp2px(context, textSize));
        return paint.measureText(text);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int withSize = calcuationViewWith();
       w=(w>withSize?withSize:(w==0?withSize:w));
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG,"onSizeChanged "+w+" "+h+" "+oldw+" "+oldh+" "+withSize);
    }

    /**
     * 重新计算宽高度值
     *
     * @param defalut
     * @param measureSpec
     * @return
     */
    private int measureSize(int defalut, int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {           //已知控件的大小
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            //遵循 AT_MOST,result不能大于specSize
            result = Math.min(result, specSize);
        } else {        //设置一个默认值
            result = defalut;
        }
        return result;
    }

    /**
     *  加载绘制
     * @param state
     * @param digital
     * @param name
     */
    public void loadData(int state,String digital,String name){
        this.currentRewardState=state;
        this.currentDigital=digital;
        this.currentRewardName=name;
        this.invalidate();
        Log.i(TAG," loadData "+state);
    }
    /**
     *  加载绘制
     * @param state
     * @param bitmap
     * @param name
     */
    public void loadData(int state,Bitmap bitmap,String name){
        this.currentRewardState=state;
        this.bitmap=bitmap;
        this.currentRewardName=name;
        this.invalidate();
        Log.i(TAG," loadData "+state);
    }
}
