package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.StringUtils;



/**
 * Created by ${xingen} on 2017/8/28.
 * <p>
 * 问题答错，减少10分的弹窗。
 */

public class AnswerFailureDialog extends BaseDialog {
    private final String TAG=AnswerFailureDialog.class.getSimpleName();
    private DisplayMetrics displayMetrics;
    public AnswerFailureDialog(@NonNull Context context) {
        super(context);
        initConfig();
    }

    /**
     *  初始配置
     *
     */
    private void initConfig() {
        WindowManager.LayoutParams layoutParams= this.getWindow().getAttributes();
        layoutParams.width=this.displayMetrics.widthPixels;
        layoutParams.height=this.displayMetrics.heightPixels;
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
    }

    @Override
    protected View getRootView() {
        return View.inflate(context, R.layout.dialog_answerfailure, null);
    }
    private ImageView bg1_iv, bg2_iv;
    private ImageView text_tip_iv, reduce_10_iv;
    @Override
    protected void initView(View rootView) {
        this.displayMetrics= StringUtils.getPhoneMetrics(context);
        this.bg1_iv = (ImageView) rootView.findViewById(R.id.answer_failure_bg1_iv);
        this.bg2_iv =(ImageView) rootView.findViewById(R.id.answer_failure_bg2_iv);
        this.text_tip_iv =(ImageView) rootView.findViewById(R.id.answer_failure_text_tip_iv);
        this.reduce_10_iv = (ImageView) rootView.findViewById(R.id.answer_failure_reduce_10_iv);
        hideAllView();
        animatorChildStart();
    }

    /**
     * 重新动画的方法
     */
    public void reStartShow(){
        if (this.isShowing()) return;
        hideAllView();
        animatorChildStart();
        this.show();
    }

    /**
     * 配置控件
     */
    private void hideAllView() {
        scaleView(this.bg1_iv);
        scaleView(this.bg2_iv);
        scaleView(this.text_tip_iv);
        moveView(this.reduce_10_iv);
    }
    private float translationY;
    private float  view_original_y;
    private void moveView(View view) {
        view_original_y=0;
        translationY=-(getImageViewLocation()+getImageViewBitmap());
        Log.i(TAG," 弹窗中ImageView的偏移量 "+translationY);
       view.setTranslationY(translationY);
    }
    /**
     *  控件在屏幕中的位置
     *
     * @return
     */
    private int  getImageViewLocation(){
       int height_phone= displayMetrics.heightPixels;
        return height_phone/2;
    }
    /**
     * 获取到Bitmap的高度
     * @return
     */
    private int getImageViewBitmap() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.reduce_10_iv.getDrawable();
        if (bitmapDrawable != null) {
            return bitmapDrawable.getBitmap().getHeight();
        } else {
            return 0;
        }
    }
    /**
     * 压缩View,一开始不见
     *
     * @param view
     */
    private void scaleView(View view) {
        view.setScaleX(0.0f);
        view.setScaleY(0.0f);
    }
    private void animatorChildStart() {
        AnimatorSet bg2_animatorSet=scaleChildViewAnimatorSet(bg2_iv,800);

        AnimatorSet bg1_animatorSet=scaleChildViewAnimatorSet(bg1_iv,600);
        bg1_animatorSet.setStartDelay(700);

        AnimatorSet text_tip_animatorSet=scaleChildViewAnimatorSet(text_tip_iv,500);
        text_tip_animatorSet.setStartDelay(900);

        AnimatorSet animatorTotalSet=new AnimatorSet();
        animatorTotalSet.playTogether(bg2_animatorSet,bg1_animatorSet,text_tip_animatorSet);
        animatorTotalSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }
            @Override
            public void onAnimationEnd(Animator animator) {
                 fallingAnimation();
            }
            @Override
            public void onAnimationCancel(Animator animator) {
            }
            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorTotalSet.start();
    }

    /**
     * 设置位置动画
     */
    private void fallingAnimation(){
        ObjectAnimator translationY = ObjectAnimator.ofFloat(this.reduce_10_iv, "TranslationY",this.translationY, this.view_original_y);
        translationY.setDuration(600);
        translationY.start();
    }

    /**
     * 设置缩放动画
     * @param view
     * @param duration
     * @return
     */
    private AnimatorSet scaleChildViewAnimatorSet( View view,long duration ) {
        ObjectAnimator scale_x = ObjectAnimator.ofFloat(view, "scaleX", 0, 1f);
        ObjectAnimator scale_y = ObjectAnimator.ofFloat(view, "scaleY", 0, 1f);
        AnimatorSet animatorset=new AnimatorSet();
        animatorset.playTogether(scale_x,scale_y);
        animatorset.setDuration(duration);
        return  animatorset;
    }
}
