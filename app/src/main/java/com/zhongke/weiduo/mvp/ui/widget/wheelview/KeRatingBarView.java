package com.zhongke.weiduo.mvp.ui.widget.wheelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.StringUtils;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by hyx on 2017/11/7.
 */

public class KeRatingBarView extends LinearLayout {
    public interface OnRatingListener {
        void onRating(Object bindObject, int RatingScore);
    }

    private boolean mClickable = true;
    private OnRatingListener onRatingListener;
    private Object bindObject;
    private float starImageSize;
    private int starCount;
    private Drawable starEmptyDrawable;
    private Drawable starFillDrawable;
    private int mStarCount;
    private int imagePaddingr;

    public void setClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    public KeRatingBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.KeRatingBarView);
        starImageSize = ta.getDimension(R.styleable.KeRatingBarView_starImageSize, 20);
        starCount = ta.getInteger(R.styleable.KeRatingBarView_starCount, 5);
        starEmptyDrawable = ta.getDrawable(R.styleable.KeRatingBarView_starEmpty);
        starFillDrawable = ta.getDrawable(R.styleable.KeRatingBarView_starFill);
        ta.recycle();

        imagePaddingr = DisplayUtils.dip2px(context,15);

        for (int i = 0; i < starCount; ++i) {
            ImageView imageView = getStarImageView(context, attrs);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickable) {
                        mStarCount = indexOfChild(v) + 1;
                        setStar(mStarCount, true);
                        if (onRatingListener != null) {
                            onRatingListener.onRating(bindObject, mStarCount);
                        }
                    }
                }
            });
            addView(imageView);
        }
    }

    private ImageView getStarImageView(Context context, AttributeSet attrs) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams para = new ViewGroup.LayoutParams(Math.round(starImageSize), Math.round(starImageSize));
        imageView.setLayoutParams(para);
        // TODO:you can change gap between two stars use the padding
//        imageView.setPadding(0, 0, 40, 0);

        imageView.setPadding(0, 0, imagePaddingr, 0);
        imageView.setImageDrawable(starEmptyDrawable);
        imageView.setMaxWidth(20);
        imageView.setMaxHeight(20);
        return imageView;
    }

    public void setStar(int starCount, boolean animation) {
        starCount = starCount > this.starCount ? this.starCount : starCount;
        starCount = starCount < 0 ? 0 : starCount;
        for (int i = 0; i < starCount; ++i) {
            ((ImageView) getChildAt(i)).setImageDrawable(starFillDrawable);
            if (animation) {
                ScaleAnimation sa = new ScaleAnimation(0, 0, 1, 1);
                getChildAt(i).startAnimation(sa);
            }
        }
        for (int i = this.starCount - 1; i >= starCount; --i) {
            ((ImageView) getChildAt(i)).setImageDrawable(starEmptyDrawable);
        }
    }

    public int getStarCount() {
        return mStarCount;
    }

    public void setStarFillDrawable(Drawable starFillDrawable) {
        this.starFillDrawable = starFillDrawable;
    }

    public void setStarEmptyDrawable(Drawable starEmptyDrawable) {
        this.starEmptyDrawable = starEmptyDrawable;
    }

    public void setStarCount(int startCount) {
        this.starCount = startCount;
    }

    public void setStarImageSize(float starImageSize) {
        this.starImageSize = starImageSize;
    }

    public void setBindObject(Object bindObject) {
        this.bindObject = bindObject;
    }

    /**
     *这个回调，可以获取到用户评价给出的星星等级
     */
    public void setOnRatingListener(OnRatingListener onRatingListener) {
        this.onRatingListener = onRatingListener;
    }
}
