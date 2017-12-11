package com.zhongke.weiduo.mvp.ui.widget.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;

import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.MathUtils;
import com.zhongke.weiduo.util.TextViewUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ${xingen} on 2017/6/21.
 * <p>
 * 创建具备透明背景的TextView
 */

public class TransparentBGTextView extends FrameLayout {
    private Context context;
    private TextView textView;
    private CircleView circleView;
    private final String TAG = TransparentBGTextView.class.getSimpleName();

    public TransparentBGTextView(Context context) {
        super(context);
        iniConfig(context);
    }

    public TransparentBGTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        iniConfig(context);
    }

    private void iniConfig(Context context) {
        this.context = context;
    }

    /**
     * 创建制定的TextView
     *
     * @return
     */
    private TextView crateView() {
        TextView textView = new TextView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(ColorUtils.stringToColor("#ffffff"));
        textView.setPadding(DisplayUtils.dip2px(context, 10.3f), DisplayUtils.dip2px(context, 8f), DisplayUtils.dip2px(context, 10.3f), DisplayUtils.dip2px(context, 8f));
        return textView;
    }

    /**
     * 创建制定的TextView
     *
     * @return
     */
    private TextView createBGView(String content) {
        TextView textView = new TextView(context);
        Paint paint = textView.getPaint();
        paint.setTextSize(DisplayUtils.sp2px(context, 10));
        float with = DisplayUtils.dip2px(context, 10) * 3.5f + TextViewUtils.calculationDigitalWith(paint, content);
        float height = DisplayUtils.dip2px(context, 8) * 2 + TextViewUtils.calculationDigitalHeight(paint);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) with, (int) height);
        layoutParams.gravity = Gravity.LEFT;
        textView.setLayoutParams(layoutParams);
        //ColorUtils.stringToColor("#3eb245")
        textView.setBackgroundResource(R.drawable.event_detials_tv_bg_shape);
        return textView;
    }

    /**
     * 对外提供，添加数据的方法
     *
     * @param message
     */
    public void addData(Message message) {

        int width = DisplayUtils.dip2px(context, 36);

        FrameLayout frameLayout = new FrameLayout(context);
        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams1.gravity = Gravity.CENTER_VERTICAL;
        layoutParams1.leftMargin = DisplayUtils.dip2px(context, 16);
        frameLayout.setLayoutParams(layoutParams1);

        frameLayout.addView(createBGView(message.content));
        this.textView = crateView();
        this.textView.setTextSize(10);
        this.textView.setText(message.content);
        frameLayout.addView(textView);

        this.addView(frameLayout);

        //添加圆形
        CircleView circleView = new CircleView(this.context, message.percentage);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(width, width);
        layoutParams2.gravity = Gravity.LEFT;
        circleView.setLayoutParams(layoutParams2);
        this.addView(circleView);
    }

    private class CircleView extends View {
        private Paint paint;
        private Context context;
        private int text_size = 10;
        private int whiteColor;
        private String percentage;
        private int bg_color;
        private int curve_color;
        private  double alpha=0.3;
        private int large_curve_length=6;
        private  Path sinPath, reverseSinPath;

        public CircleView(Context context, String percentage) {
            super(context);
            this.context = context;
            this.paint = new Paint();
            this.paint.setAntiAlias(true);
            this.percentage = percentage;
            this.whiteColor = ColorUtils.stringToColor("#ffffff");
            this.bg_color=ColorUtils.stringToColor("#26a53b");
            this.curve_color=ColorUtils.stringToColor("#8cdc81");
            this.sinPath=new Path();
            this.reverseSinPath =new Path();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int width = getWidth();
            float cx = (float) width / 2;
            float cy = cx;
            float radius = cx;
            //绘制浅色的内圆
            paint.reset();
            paint.setAntiAlias(true);
            paint.setColor(this.bg_color);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(cx, cy, radius - 2, paint);

            //绘制白色外圆形边框
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(whiteColor);
            //paint.setStrokeWidth(DisplayUtils.dip2px(context,2f));
            canvas.drawCircle(cx, cy, radius, paint);

            //绘制百分比文字
            paint.reset();
            paint.setAntiAlias(true);
            paint.setColor(whiteColor);
            paint.setTextSize(DisplayUtils.sp2px(context, text_size));
            float text_x = (width - TextViewUtils.calculationDigitalWith(paint, this.percentage)) / 2;
            float text_y = cx + TextViewUtils.calculationDigitalHeight(paint) / 2 - DisplayUtils.dip2px(context, 1);
            canvas.drawText(this.percentage, text_x, text_y, paint);


            paint.setColor(this.curve_color);
            paint.setAlpha((int )(this.alpha*255));
             paint.setStyle(Paint.Style.FILL);
            caculationPath(percentage);
            //绘制波浪线区域：sin区域
             canvas.drawPath(sinPath, paint);
            //绘制波浪线区域：反向sin区域
            canvas.drawPath(reverseSinPath,paint);

           // canvas.drawPath(caculationCosPath(percentage), paint);

        }

        /**
         * 计算sin函数线段和对称的sin函数线段
         * @param percentage
         * @return
         */
        public void  caculationPath(String percentage) {

            double height ;
            float radius = (float) getWidth() / 2;
            float percentage_f = getPercentage(percentage);

            //按比率计算出，点位置
            float x1=0;
            float percentage_longtime = 0;

            if (percentage_f == 0.5) {
                x1 = 0;
            } else if (percentage_f==0|| percentage_f ==1) {
                  return ;
            }else {
                percentage_longtime = getWidth() * percentage_f;
                height = percentage_f > 0.5 ? percentage_longtime - radius : radius - percentage_longtime;
                //勾股定理：一条斜边和一个直角边
                x1 = (getWidth() / 2) - (float) Math.sqrt(radius * radius - height * height);
            }
            float y1 = getHeight() - getHeight() * percentage_f;
            float y3 = y1;
            float x3 = getWidth() - x1;

            //   正弦曲线的表达式为y=Asin(ωx+φ)+k,通过一些列的点来构建Sin函数
            int length=(int) (x3-x1);
            float singleDegree = (float) (360 ) / length;
            Log.i(TAG," length "+length+" singleDegree "+singleDegree);
            int curve_length=large_curve_length;
            for (int i = 0; i <= length; i++) {
                if (i == 0) {
                    sinPath.moveTo(x1, y1);
                    reverseSinPath.moveTo(x1, y1);
                } else {
                   float x=x1+i;
                    int y=(int) (y1 - sin(i * singleDegree) * curve_length);
                   sinPath.lineTo(x,y );
                   reverseSinPath.lineTo  (x,y1+(y1-y));
                }
                if (i==length){//添加一个圆弧曲线
                    RectF rectF=new RectF(0,0,getWidth(),getHeight());
                    double sin=(x3-x1)/2/radius;
                    int degress=(int) MathUtils.toDegrees(MathUtils.asin(sin));
                    float startAngle;
                    float sweepAngle;
                    if (percentage_f==0.5){
                        startAngle=0;
                        sweepAngle=180;
                    }else if (percentage_f<0.5){
                        startAngle=90.0f-degress;
                        sweepAngle=degress*2;
                    }else {
                        startAngle=360.0f-(90.0f-degress);
                        sweepAngle=180+(90-degress)*2;
                    }
                    Log.i(TAG,"  添加最后的 半圆形  "+percentage_f+" 计算的角度"+degress+" 开始的角度"+startAngle+" 扫过的角度 "+sweepAngle );
                    sinPath.arcTo(rectF,startAngle,sweepAngle);
                    reverseSinPath.arcTo(rectF,startAngle,sweepAngle);
                }
            }
            sinPath.close();
            reverseSinPath.close();
        }
        /**
         * 计算cos函数线段
         * @param percentage
         * @return
         */
        public Path caculationCosPath(String percentage) {
            Path path = new Path();
            double height ;
            float radius = (float) getWidth() / 2;
            float percentage_f = getPercentage(percentage);

            //按比率计算出，点位置
            float x1;
            float percentage_longtime = 0;
            if (percentage_f == 0.5) {
                x1 = 0;
            } else if (percentage_f == 1 || percentage_f == 0) {
                return path;
            } else {
                percentage_longtime = getWidth() * percentage_f;
                height = percentage_f > 0.5 ? percentage_longtime - radius : radius - percentage_longtime;
                //勾股定理：一条斜边和一个直角边
                x1 = (getWidth() / 2) - (float) Math.sqrt(radius * radius - height * height);
            }
            float y1 = getHeight() - getHeight() * percentage_f;
            float y3 = y1;
            float x3 = getWidth() - x1;

           float singleDegree = (360.0f / (int) (x3 - x1));
            int curve_length=DisplayUtils.dip2px(context,large_curve_length);
            //余玄函数的点
            List<Point> points = new ArrayList<>();
            for (int i = 0; i < x3 - x1; i++) {
                Point point = new Point();
                point.x = (int) x1 + i;
                point.y = (int) (y1 + cos(i * singleDegree) *curve_length);
                points.add(point);
            }
            for (int i = points.size() - 1; i >= 0; i--) {
                if (i==points.size()-1){
                    path.moveTo(points.get(i).x, points.get(i).y);
                }else{
                    path.lineTo(points.get(i).x, points.get(i).y);
                }
                if (i==0){//添加一个圆弧曲线
                    RectF rectF=new RectF(0,0,getWidth(),getHeight());
                    double sin=(x3-x1)/2/radius;
                    int degress=(int) MathUtils.toDegrees(MathUtils.asin(sin));
                    float startAngle;
                    float sweepAngle;
                    if (percentage_f==0.5){
                        startAngle=0;
                        sweepAngle=180;
                    }else if (percentage_f<0.5){
                        startAngle=90.0f-degress;
                        sweepAngle=degress*2;
                    }else {
                        startAngle=360.0f-(90.0f-degress);
                        sweepAngle=180+(90-degress)*2;
                    }
                    Log.i(TAG,"  添加最后的 半圆形  "+percentage_f+" 计算的角度"+degress+" 开始的角度"+startAngle+" 扫过的角度 "+sweepAngle );
                    path.arcTo(rectF,startAngle,sweepAngle);
                }
            }
            path.close();
            return path;
        }


        /**
         * 计算出角度对应的sin值
         * <p>
         * 也可以采用cos,tan函数进行转换。
         *
         * @param i
         * @return
         */
        private double sin(float i) {
            //弧度对应的sin值
            double result = Math.sin(degreeToRad(i));
            return result;
        }

        /**
         * 计算出角度对应的cos值
         *
         * @param i
         * @return
         */
        private double cos(float i) {
            //弧度对应的cos值
            double result = Math.cos(degreeToRad(i));
            return result;
        }

        /**
         * 将角度换成弧度（ 计算公式：弧度＝度×π/180 ）
         *
         * @param degree
         * @return
         */
        private double degreeToRad(double degree) {
            return degree * Math.PI / 180;
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            Log.i(TAG, "onLayout " + left + " " + top + " " + right + " " + bottom + " " + getWidth() + " " + getHeight());
            super.onLayout(changed, left, top, right, bottom);
        }

        /**
         * 获取进度
         *
         * @param s
         * @return
         */
        public float getPercentage(String s) {
            String[] content = s.split("%");
            return Float.valueOf(content[0]) / 100;
        }
    }

    /**
     * 一个用于存储进度和信息内容的实体类
     */
    public static class Message {
        public String percentage;
        public String content;
    }

}
