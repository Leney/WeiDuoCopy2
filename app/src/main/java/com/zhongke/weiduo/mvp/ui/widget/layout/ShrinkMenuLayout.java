package com.zhongke.weiduo.mvp.ui.widget.layout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.ViewIdUtils;

import static com.zhongke.weiduo.util.DisplayUtils.dip2px;

/**
 * Created by ${xingen} on 2017/7/10.
 * <p>
 * 收缩菜单布局
 * <p>
 * 用于首页的维舵页面
 */

public class ShrinkMenuLayout extends ViewGroup implements View.OnClickListener {
    private final String TAG = ShrinkMenuLayout.class.getSimpleName();
    /**
     * 状态，展开1，收缩2
     */
    private final int unfolded_state = 1;
    private final int shrink_state = 2;
    private int currentState = shrink_state;
    private Paint paint;
    /**
     * 展开与搜索的点击图标
     */
    private Bitmap unfolded_bitmap, shrink_bitmap;
    /**
     * 菜单上的子菜单
     */
    private MenuItem message_item, gift_item, ling_item;
    /**
     *  存储动态分配给子菜单的ID集合
     */
    private int[] viewIds = new int[3];
    /**
     * 位移动画中的点位置
     */
    private Point message_point, gift_point, ling_point, center_point;
    /**
     *  收缩与展开图标的区域范围
     */
    private Region globalRegion;
    private Region clickRegion;
    /**
     * 默认ViewGroup的宽高，单位dp(来源美工给定图片)
     */
    private final int default_with = 74, default_height = 76;
    public ShrinkMenuLayout(Context context) {
        super(context);
        init();
        addChildView();
    }
    public ShrinkMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        addChildView();
    }

    /**
     * 添加子View ，动态分配ID ,添加点击监听事件。
     */
    private void addChildView() {
        this.message_item = new MenuItem(getContext(), R.drawable.main_menu_message);
        this.message_item.setId(viewIds[0]);
        this.message_item.setOnClickListener(this);

        this.gift_item = new MenuItem(getContext(), R.drawable.main_menu_gif);
        this.gift_item.setId(viewIds[1]);
        this.gift_item.setOnClickListener(this);

        this.ling_item = new MenuItem(getContext(), R.drawable.main_menu_ling);
        this.ling_item.setId(viewIds[2]);
        this.ling_item.setOnClickListener(this);

        this.addView(message_item);
        this.addView(gift_item);
        this.addView(ling_item);
    }
    /**
     * 初始化配置
     */
    public void init() {
        //创建ViewGroup绘制展开与收缩的图片需要用到的画笔
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        //为三个子菜单动态分配ID
        for (int i = 0; i < viewIds.length; ++i) {
            this.viewIds[i] = ViewIdUtils.getViewId();
        }
        /**
         *   强制onDraw被调用的方式:
         *   1. 去除去掉其WILL_NOT_DRAW flag
         *   2. 采用设置background方式调用。
         */
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, " ViewGroup onDraw");
        int width = getWidth();
        int height = getHeight();
        //绘制中心的icon
        Bitmap bitmap = getIndexBitmap(currentState);
        float left = width - bitmap.getWidth();
        float top = height - bitmap.getHeight();
        canvas.drawBitmap(bitmap, left, top, paint);
    }

    /**
     * 获取指定的Bitmap
     *
     * @param state
     * @return
     */
    private Bitmap getIndexBitmap(int state) {
        shrink_bitmap = (shrink_bitmap == null ? loadLocalBitmap(R.drawable.main_menu_btn_shrink) : shrink_bitmap);
        unfolded_bitmap = (unfolded_bitmap == null ? loadLocalBitmap(R.drawable.main_menu_btn_unfolded) : unfolded_bitmap);
        return state == shrink_state ? shrink_bitmap : unfolded_bitmap;
    }
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //重写了onSizeChanged()，这里不做处理。
    }

    /**
     *  重写，摆放子View的位置。
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int ww = w - getPaddingLeft() - getPaddingRight();
        int hh = h - getPaddingTop() - getPaddingBottom();

        Log.i(TAG, " ViewGroup onSizeChanged ");
        /**
         *   计算收缩和展开的点位置
         */
        int radius = DisplayUtils.dip2px(getContext(), MenuItem.CIRCLE_SIZE);
        //中心点位置，即展开与收缩图片的中心点。
        int center_x = ww - radius;
        int center_y = hh - radius;
        /**
         *  主菜单的中心点到子菜单中心点的距离
         *  该距离为位移动画的距离
         */
        int hypotenuse = center_x - radius;
        this.gift_point = new Point(center_x - radius, 0);
        double anlge = Math.toRadians(45);
        /**
         *     根据一条斜边和已知角度，利用正弦和余弦函数，求出两条直角边。
         */
        this.ling_point = new Point((int) (Math.sin(anlge) * hypotenuse), (int) (Math.cos(anlge) * hypotenuse));
        this.message_point = new Point(0, center_x - radius);
        this.center_point = new Point(0, 0);

        Log.i(TAG, " ViewGroup onSizeChanged " + " w " + w + " h " + h + " hypotenuse " + hypotenuse + " radius " + radius + " gif_point " + gift_point.x + " " + gift_point.y + " ling " + ling_point.x + " " + ling_point.y + " message " + message_point.x + " " + message_point.y);
        /**
         *  点击区域
         */
        this.globalRegion = new Region(0, 0, w, h);
        this.clickRegion = new Region();
        Path path = new Path();
        path.addCircle(center_x, center_y, radius, Path.Direction.CW);
        this.clickRegion.setPath(path, globalRegion);

        /**
         * 将三个Item摆放好位置，且设置不可见
         */

        int left = center_x - radius;
        int top = center_y - radius;
        this.message_item.layout(left, top, w, h);
        this.ling_item.layout(left, top, w, h);
        this.gift_item.layout(left, top, w, h);
        hideMenuItem(this.gift_item);
        hideMenuItem(this.ling_item);
        hideMenuItem(this.message_item);
    }

    /**
     * 将三个item压缩至不可见。
     * @param menuItem
     */
    public void hideMenuItem(MenuItem menuItem) {
        menuItem.setScaleX(0.0f);
        menuItem.setScaleY(0.0f);
    }
    /**
     * 重写,ViewGroup实现自适应
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(default_with, widthMeasureSpec), measureSize(default_height, heightMeasureSpec));
    }
    /**
     * 重新计算高度值
     *
     * @param defaultSize
     * @param measureSpec
     * @return
     */
    private int measureSize(int defaultSize, int measureSpec) {
        //设置一个默认值
        int result = dip2px(getContext(), defaultSize);
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
    /**
     *  传递ViewGroup的事件
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            /**
             * 展开与收缩按钮的点击范围
             */
            if (this.clickRegion.contains(x, y)) {
                Log.i(TAG, " ViewGroup  menu dispatchTouchEvent  true");
                onClickChange();
                return true;
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     *  重写，进行拦截某些动作
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.i(TAG, " ViewGroup  onInterceptTouchEvent ");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (!this.clickRegion.contains(x, y)) {
                /**
                 *      当收缩状态下，对item的的点击事件进行拦截。
                 */
                if (currentState==shrink_state){
                      return  true;
                }
            }
        }
        return super.onInterceptTouchEvent(event);
    }

    /**
     * 点击相应展开与收缩变化
     */
    public void onClickChange() {
        AnimatorSet messageAnimator, lingAnimator, giftAnimator;
        if (currentState == shrink_state) {//收缩状态
            messageAnimator = unfoldedAnimator(message_item, message_point);
            lingAnimator = unfoldedAnimator(ling_item, ling_point);
            giftAnimator = unfoldedAnimator(gift_item, gift_point);
            currentState = unfolded_state;

        } else {//展开状态
            messageAnimator = shrinkAnimator(message_item, message_point);
            lingAnimator = shrinkAnimator(ling_item, ling_point);
            giftAnimator = shrinkAnimator(gift_item, gift_point);
            currentState = shrink_state;
        }
        messageAnimator.start();
        lingAnimator.start();
        giftAnimator.start();
        this.invalidate();
    }
    /**
     * 收缩动画
     *
     * @param menuItem
     * @param targetPoint
     * @return
     */
    private AnimatorSet shrinkAnimator(MenuItem menuItem, Point targetPoint) {
        ObjectAnimator moveIn_x = ObjectAnimator.ofFloat(menuItem, "translationX", -targetPoint.x, center_point.y);
        ObjectAnimator moveIn_y = ObjectAnimator.ofFloat(menuItem, "translationY", -targetPoint.y, center_point.y);
        ObjectAnimator scale_x = ObjectAnimator.ofFloat(menuItem, "scaleX", 1f, 0);
        ObjectAnimator scale_y = ObjectAnimator.ofFloat(menuItem, "scaleY", 1f, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(moveIn_x).with(moveIn_y).with(scale_x).with(scale_y);
        animatorSet.setDuration(ANIMATOR_TIME);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        return animatorSet;
    }
    /**
     * 动画时间
     */
    private final int ANIMATOR_TIME = 500;
    /**
     * 展开动画
     *
     * @param menuItem
     * @param targetPoint
     * @return
     */
    private AnimatorSet unfoldedAnimator(MenuItem menuItem, Point targetPoint) {
        ObjectAnimator moveIn_x = ObjectAnimator.ofFloat(menuItem, "translationX", center_point.x, -targetPoint.x);
        ObjectAnimator moveIn_y = ObjectAnimator.ofFloat(menuItem, "translationY", center_point.y, -targetPoint.y);
        ObjectAnimator scale_x = ObjectAnimator.ofFloat(menuItem, "scaleX", 0, 1f);
        ObjectAnimator scale_y = ObjectAnimator.ofFloat(menuItem, "scaleY", 0, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(moveIn_x).with(moveIn_y).with(scale_x).with(scale_y);
        animatorSet.setDuration(ANIMATOR_TIME);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        return animatorSet;
    }
    @Override
    public void onClick(View view) {
        String content = " ";
        if (view.getId() == viewIds[0]) {//消息
            content = "消息";
        } else if (view.getId() == viewIds[1]) {//礼物
            content = "礼物";
        } else if (view.getId() == viewIds[2]) {//铃声
            content = "铃声";
        }
        Toast.makeText(this.getContext(), content, Toast.LENGTH_SHORT).show();
    }
    /**
     * 带有灰色圆形背景的icon
     */
    private class MenuItem extends View {
        private Paint paint;
        private Context context;
        //灰色圆形的半径
        public static final int CIRCLE_SIZE = 15;
        private int imageId;
        //绘制一个Bitmap
        Bitmap bitmap;
        public MenuItem(Context context, int imageId) {
            super(context);
            this.context = context;
            this.imageId = imageId;
            initConfig();
        }
        private void initConfig() {
            this.paint = new Paint();
            this.paint.setAntiAlias(true);
            //绘制一个Bitmap
            bitmap = loadLocalBitmap(imageId);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            float left = 0;
            float top = 0;
            canvas.drawBitmap(bitmap, left, top, paint);
        }
        /**
         * 重写,设置MenuItem的高度
         * @param widthMeasureSpec
         * @param heightMeasureSpec
         */
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            if (bitmap != null) {//当图片不为空，设置控件的宽高与图片一致。
                setMeasuredDimension(bitmap.getWidth(), bitmap.getHeight());
            } else {//设置，美工给定图片上的默认值。
                int width = dip2px(context, CIRCLE_SIZE);
                int height = width;
                setMeasuredDimension(width, height);
            }
        }
    }
    /**
     * 加载本地资源
     *
     * @param imageId
     * @return
     */
    private Bitmap loadLocalBitmap(int imageId) {
        return BitmapFactory.decodeResource(getContext().getResources(), imageId, new BitmapFactory.Options());
    }

}
