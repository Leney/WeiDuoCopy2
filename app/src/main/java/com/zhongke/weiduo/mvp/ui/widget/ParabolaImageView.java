package com.zhongke.weiduo.mvp.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Created by ${xinGen} on 2017/9/19.
 * <p>
 * 一个抛物线动画的ImageView
 */
public class ParabolaImageView extends AppCompatImageView {
    private ViewGroup rootView;

    public ParabolaImageView(Context context) {
        super(context);
        initConfig();
    }

    /**
     * 初始化配置
     */
    private void initConfig() {
        setScaleType(ScaleType.MATRIX);
    }

    /**
     * 二次贝兹曲线：起点，控制点，终点。
     */
    private Point startPoint, middlePoint, endPoint;
    /**
     * 属性动画，依靠TypeEvaluator来实现动画效果，其中位移，缩放，渐变，旋转都是可以直接使用
     */
    private ValueAnimator valueAnimator;

    public void loadParabolaAnimator(View startView, View endView) {
        loadParabolaAnimator(startView, endView, null);
    }

    /**
     * 执行一个抛物线动画
     *
     * @param startView
     * @param endView
     */
    public void loadParabolaAnimator(View startView, View endView, ParabolaAnimatorListener animatorListener) {
        this.rootView = (ViewGroup) startView.getRootView();
        addParabolaView();
        startPoint = Point.createPoint(getLocationInWindow(startView));
        endPoint = Point.createPoint(getLocationInWindow(endView));
        //控制点，这里是定在起点和终点的一半，且上移动200Px;
        int pointX = (startPoint.x + endPoint.x) / 2;
        int pointY;
        if (endPoint.y > startPoint.y) {
            pointY = startPoint.y - (endPoint.y - startPoint.y) / 4;
        } else {
            pointY = endPoint.y - (startPoint.y - endPoint.y) / 4;
        }

        middlePoint = new Point(pointX, pointY);
        startAnimator(startPoint, middlePoint, endPoint, animatorListener);
    }

    /**
     * 开启抛物线动画
     *
     * @param startPoint
     * @param controllPoint
     * @param endPoint
     */
    private void startAnimator(Point startPoint, Point controllPoint, Point endPoint, ParabolaAnimatorListener animatorListener) {
        valueAnimator = ValueAnimator.ofObject(new ParabolaEvaluator(controllPoint), startPoint, endPoint);
        valueAnimator.addUpdateListener(valueAnimator ->
                updateParabolaView((Point) valueAnimator.getAnimatedValue())
        );
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeParabolaView();
                if (animatorListener != null) {
                    animatorListener.onAnimationEnd();
                }
            }
        });
        valueAnimator.start();
    }

    public interface ParabolaAnimatorListener {
        void onAnimationEnd();
    }


    private void addParabolaView() {
        if (rootView != null) {
            rootView.addView(this);
        }
    }

    private void updateParabolaView(Point point) {
        if (rootView != null) {
            this.setX(point.x);
            this.setY(point.y);
        }
    }

    private void removeParabolaView() {
        if (rootView != null) {
            rootView.removeView(this);
        }
        rootView = null;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recycleValues();
    }

    /**
     * 释放资源
     */
    private void recycleValues() {
        startPoint = middlePoint = endPoint = null;
        if (valueAnimator != null) {
            if (valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
            valueAnimator = null;
        }
    }

    /**
     * Created by ${xingen} on 2017/9/12.
     * <p>
     * 二次方公式：
     * <p>
     * 二次方贝兹曲线的路径由给定点P0、P1、P2的函数B（t）追踪：
     * <p>
     * TrueType字型就运用了以贝兹样条组成的二次贝兹曲线。
     */

    private static class ParabolaEvaluator implements TypeEvaluator<Point> {
        /**
         * 控制点的坐标
         */
        private Point controllPoint;

        public ParabolaEvaluator(Point controllPoint) {
            this.controllPoint = controllPoint;
        }

        @Override
        public Point evaluate(float t, Point startValue, Point endValue) {
            int x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * controllPoint.x + t * t * endValue.x);
            int y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * controllPoint.y + t * t * endValue.y);
            return new Point(x, y);

        }
    }

    /**
     * 获取view控件到Window的坐标
     *
     * @param view
     * @return
     */
    private int[] getLocationInWindow(View view) {
        int[] position = new int[2];
        view.getLocationInWindow(position);
        return position;
    }

    /**
     * 数据存储类
     */
    private static class Point {
        public int x;
        public int y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point src) {
            this.x = src.x;
            this.y = src.y;
        }

        /**
         * 设置点的x,y坐标
         */
        public void set(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * 构建Point对象
         *
         * @param position
         * @return
         */
        public static Point createPoint(int[] position) {
            Point point = new Point();
            point.set(position[0], position[1]);
            return point;
        }
    }

    /**
     * 反射获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        int x = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("status_bar_height");
            x = (Integer) field.get(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
}
