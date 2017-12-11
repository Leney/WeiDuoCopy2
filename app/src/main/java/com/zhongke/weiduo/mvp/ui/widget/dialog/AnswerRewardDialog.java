package com.zhongke.weiduo.mvp.ui.widget.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.zhongke.weiduo.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by ${xingen} on 2017/8/26.
 * <p>
 * 抢答奖励
 */

public class AnswerRewardDialog extends BaseDialog implements Dialog.OnDismissListener {

    public AnswerRewardDialog(@NonNull Context context) {
        super(context);
        this.setOnDismissListener(this);
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
    }

    @Override
    protected View getRootView() {
        return View.inflate(context, R.layout.dialog_answerreward, null);
    }

    private ImageView round_iv;
    private ImageView score_tv, score_iv, score_true_iv;
    private ImageView star_iv, star_iv2, star_iv3, star_iv4, star_iv5, star_iv6;
    private ImageView bg1_iv, bg2_iv, bg3_iv;

    public void reStartShow(){
        if (this.isShowing()) return;
        hideAllView();
        setRoundAnimator();
        this.show();
    }
    @Override
    protected void initView(View rootView) {

        bg1_iv =(ImageView) rootView.findViewById(R.id.answer_reward_rorate_iv);
        bg2_iv = (ImageView)rootView.findViewById(R.id.answer_reward_bg2_iv);
        bg3_iv = (ImageView)rootView.findViewById(R.id.answer_reward_bg3_iv);
        round_iv = (ImageView)rootView.findViewById(R.id.answer_reward_round_iv);
        score_iv =(ImageView) rootView.findViewById(R.id.answer_reward_score_iv);
        score_true_iv =(ImageView) rootView.findViewById(R.id.answer_reward_true_tv);
        score_tv =(ImageView) rootView.findViewById(R.id.answer_reward_score_tv);
        star_iv = (ImageView)rootView.findViewById(R.id.answer_reward_star_iv);
        star_iv2 =(ImageView) rootView.findViewById(R.id.answer_reward_star_iv_2);
        star_iv3 = (ImageView)rootView.findViewById(R.id.answer_reward_star_iv_3);
        star_iv4 = (ImageView)rootView.findViewById(R.id.answer_reward_star_iv_4);
        star_iv5 = (ImageView)rootView.findViewById(R.id.answer_reward_star_iv_5);
        star_iv6 =(ImageView) rootView.findViewById(R.id.answer_reward_star_iv_6);
        hideAllView();
        setRoundAnimator();
    }

    /**
     * 先隐藏View
     */
    private void hideAllView() {
        scaleView(bg1_iv);
        scaleView(bg2_iv);
        scaleView(bg3_iv);
        scaleView(round_iv);
        scaleView(score_tv);
        scaleView(score_iv);
        scaleView(score_true_iv);
        scaleView(star_iv);
        scaleView(star_iv2);
        scaleView(star_iv3);
        scaleView(star_iv4);
        scaleView(star_iv5);
        scaleView(star_iv6);

    }

    /**
     * 圆形图片的动画
     */
    private void setRoundAnimator() {
        AnimatorSet round_AnimatorSet = enlargeScaleAnimatorSet();
        round_AnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                startOtherViewAnimator();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        round_AnimatorSet.start();
    }

    private AnimatorSet rotationAnimatorSet;

    /**
     * 开启其他控件的指定动画
     */
    private void startOtherViewAnimator() {
        rotationAnimatorSet = new AnimatorSet();
        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> list = new ArrayList<>();
        rotationViews(list, star_iv);
        rotationViews(list, star_iv2);
        rotationViews(list, star_iv3);
        rotationViews(list, star_iv4);
        rotationViews(list, star_iv5);
        rotationViews(list, star_iv6);
        animatorSet.playTogether(list);
        animatorSet.setDuration(500);

        ObjectAnimator animator = ObjectAnimator.ofFloat(bg1_iv, "rotation", 0f, 359f);
        //设置动画次数：无限重复
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(1500);

        ObjectAnimator scale_x = ObjectAnimator.ofFloat(bg3_iv, "scaleX", 1.0f, 0.5f);
        ObjectAnimator scale_y = ObjectAnimator.ofFloat(bg3_iv, "scaleY", 1.0f, 0.5f);
        scale_x.setRepeatCount(ValueAnimator.INFINITE);
        scale_y.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet thirdAnimator = new AnimatorSet();
        //设置动画次数：无限重复
        thirdAnimator.play(scale_x).with(scale_y);
        thirdAnimator.setDuration(800);

        rotationAnimatorSet.playTogether(animatorSet, animator, thirdAnimator);
        rotationAnimatorSet.setInterpolator(new LinearInterpolator());
        rotationAnimatorSet.start();
    }

    private void rotationViews(List<Animator> list, View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 359f);
        //设置动画次数：无限重复
        animator.setRepeatCount(ValueAnimator.INFINITE);
        //animator.setRepeatMode(ValueAnimator.RESTART);
        list.add(animator);
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

    /**
     * 从0到1的放大动画
     * <p>
     * animatorSet.play(<animator1>).before(<animator2>)来表示让Animator1在Animator2之前执行
     *
     * @param
     * @return
     */
    private AnimatorSet enlargeScaleAnimatorSet() {
        ObjectAnimator scale_x = ObjectAnimator.ofFloat(round_iv, "scaleX", 0, 1.2f);
        ObjectAnimator scale_y = ObjectAnimator.ofFloat(round_iv, "scaleY", 0, 1.2f);
        AnimatorSet firstSet = new AnimatorSet();
        firstSet.play(scale_x).with(scale_y);
        firstSet.setDuration(300);

        firstSet.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator scale_x_after = ObjectAnimator.ofFloat(round_iv, "scaleX", 1.2f, 1.0f);
        ObjectAnimator scale_y_after = ObjectAnimator.ofFloat(round_iv, "scaleY", 1.2f, 1.0f);
        AnimatorSet secondSet = new AnimatorSet();
        secondSet.play(scale_x_after).with(scale_y_after);
        secondSet.setDuration(150);


        AnimatorSet thirdSet = new AnimatorSet();
        List<Animator> list = new ArrayList<>();
        scaleViews(list, bg1_iv);
        scaleViews(list, bg2_iv);
        scaleViews(list, bg3_iv);

        scaleViewsDelay(list, score_tv);
        scaleViewsDelay(list, score_iv);

        scaleViews(list, score_true_iv);
        scaleViews(list, star_iv);
        scaleViews(list, star_iv2);
        scaleViews(list, star_iv3);
        scaleViews(list, star_iv4);
        scaleViews(list, star_iv5);
        scaleViews(list, star_iv6);
        thirdSet.playTogether(list);
        thirdSet.setDuration(400);
        thirdSet.setInterpolator(new AccelerateInterpolator());
        //按着顺序播放
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(firstSet, secondSet, thirdSet);
        return animatorSet;
    }

    private void scaleViews(List<Animator> list, View view) {
        ObjectAnimator scale_x = ObjectAnimator.ofFloat(view, "scaleX", 0, 1f);
        ObjectAnimator scale_y = ObjectAnimator.ofFloat(view, "scaleY", 0, 1f);
        list.add(scale_x);
        list.add(scale_y);
    }

    private void scaleViewsDelay(List<Animator> list, View view) {
        ObjectAnimator scale_x = ObjectAnimator.ofFloat(view, "scaleX", 0, 1f);
        ObjectAnimator scale_y = ObjectAnimator.ofFloat(view, "scaleY", 0, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scale_x).with(scale_y);
        animatorSet.setStartDelay(100);
        list.add(animatorSet);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        //取消动画
        if (rotationAnimatorSet != null) {
            rotationAnimatorSet.cancel();
        }
    }
}
