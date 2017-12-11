package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;

import static com.zhongke.weiduo.util.DisplayUtils.dip2px;

/**
 * Created by ${ xingen } on 2017/6/19.
 */

public class DotLineView extends View {

    /**
     * 线段的颜色
     */
    private int color;
    private int greenColor;
    private int redColor;
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 最大圆的颜色
     */
    private int largeCircleColor;
    private int largeCircRedleColor;

    private int smallCircleColor;

    private int width;
    //圆圈的y坐标值
    private int first_padding_top = 25, second_padding_top = 82, three_padding_top = 133;
    /**
     * 距离，这里用dp
     */
    //小圆圈
    private final int SMALL_CIRCLE_RADIUS_SIZE = 3;
    //大圆圈
    private final int CIRCLE_RADIUS_SIZE = 5;
    //最大外圆圈
    private final int LAGER_CIRCLE_RADIUS_SIZE = 10;
    //线段和圆圈到左边的边距
    private final int PADDING_LEFT_SIZE = 56;
    //文字的左边距
    private final int PADDING_LEFT_SIZE_TEXT=13;
    //文字大小
    private final int TEXT_SIZE=12;
    //文字颜色
    private  int TEXT_COLOR;

    private Context context;

    /**
     * 设置默认宽高
     */
    private final int VIEW_HEIGHT = 190;
    private final int VIEW_WIDTH = 71;

    private boolean needRedCircle;

    public DotLineView(Context context) {
        super(context);
        this.context = context;
        initConfig();
    }

    public DotLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initConfig();
    }

    /**
     * 初始化一些配置属性
     */
    private void initConfig() {
        //这里是rgb的色值，转成android用到的色值。
        smallCircleColor = color = ColorUtils.stringToColor("#dcdcdc");
        largeCircleColor = ColorUtils.stringToColor("#dff4dd");
        largeCircRedleColor=ColorUtils.stringToColor("#ff7c7c");
        TEXT_COLOR=ColorUtils.stringToColor("#999999");
        greenColor=ColorUtils.stringToColor("#61c653");
        redColor = ColorUtils.stringToColor("#ff0000");

        width = dip2px(context, PADDING_LEFT_SIZE);

        //创建画笔对象
        paint = new Paint();
        //设置画笔风格，为填充
        paint.setStyle(Paint.Style.FILL);
        //设置抗锯齿
        paint.setAntiAlias(true);
    }

    public void setData(boolean needLargeCircle,String showContent) {
        this.needLargeCircle = needLargeCircle;
        this.s=showContent;
    }

    public void setData(boolean needLargeCircle,String showContent,boolean needRedCircle) {
        this.needLargeCircle = needLargeCircle;
        this.s=showContent;
        this.needRedCircle = needRedCircle;
    }


private    String s ;//绘制文字
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘画线段
        drawLine(canvas);

        int circleColor;
        if (!needRedCircle) {
            circleColor=needLargeCircle?greenColor:color;
        } else {
            circleColor = needLargeCircle?redColor:color;
        }


        //画三个圆形
        drawCircle(canvas, width, dip2px(context, first_padding_top), circleColor);
        drawSmallCircle(canvas, width, dip2px(context, second_padding_top));
        drawSmallCircle(canvas, width, dip2px(context, three_padding_top));

       s =s==null?"12:30":s;
        paint.reset();
        paint.setAntiAlias(true);
        int textSize=DisplayUtils.sp2px(context,TEXT_SIZE);
        paint.setTextSize(textSize);
       // float textLength=paint.measureText(s);
        float textHeight=((-paint.ascent()) + paint.descent());
        float text_x=dip2px(context,PADDING_LEFT_SIZE_TEXT);
        float text_y=dip2px(context, 22f)+textHeight/2;
        drawText(TEXT_COLOR,s,text_x,text_y,textSize,canvas);
    }

    /**
     * 画text
     *
     * @param color
     * @param s
     * @param canvas x,y代表文字边框的左下角坐标
     */
    public void drawText(int color, String s, float x, float y, float textSize, Canvas canvas) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(textSize);
        canvas.drawText(s, x, y, paint);
    }
    private boolean needLargeCircle = true;


    /**
     * 画大圆形
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas, int x, int y, int color) {
        paint.setStyle(Paint.Style.FILL);
        if (needLargeCircle) {
            if (needRedCircle) {
                paint.setColor(largeCircRedleColor);
            } else {
                paint.setColor(largeCircleColor);
            }

            canvas.drawCircle(x, y, dip2px(context, LAGER_CIRCLE_RADIUS_SIZE), paint);
        }
        paint.setColor(color);
        /**
         * 参数1和参数2：中心点坐标，x和y
         * 参数3：圆的半径
         * 参数4：画笔
         */
        canvas.drawCircle(x, y, dip2px(context, CIRCLE_RADIUS_SIZE), paint);
    }

    /**
     * 画小圆点
     *
     * @param canvas
     */
    private void drawSmallCircle(Canvas canvas, int x, int y) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        /**
         * 参数1和参数2：中心点坐标，x和y
         * 参数3：圆的半径
         * 参数4：画笔
         */
        canvas.drawCircle(x, y, dip2px(context, SMALL_CIRCLE_RADIUS_SIZE), paint);
    }
    /**
     * 画线段
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        paint.setColor(color);
        int y = getHeight();
        canvas.drawLine(width, 0, width, y, paint);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(VIEW_WIDTH, widthMeasureSpec), measureSize(VIEW_HEIGHT, heightMeasureSpec));
    }
    /**
     * 重新计算高度值
     * @param defalut
     * @param measureSpec
     * @return
     */
    private int measureSize(int defalut, int measureSpec) {
        //设置一个默认值
        int result = dip2px(context, defalut);
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            //已经控件的大小
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            //遵循 AT_MOST,result不能大于specSize
            result = Math.min(result, specSize);
        }
        return result;
    }
}
