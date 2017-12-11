package com.zhongke.weiduo.mvp.ui.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.wingsofts.dragphotoview.DragPhotoView;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.mvp.base.BaseMainActivity;
import com.zhongke.weiduo.mvp.base.BaseMainPresenter;
import com.zhongke.weiduo.mvp.model.entity.PhotoBrowseInfo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Karma on 2017/6/29.
 * 类描述：仿微信图片查看
 */

public class DragPhotoActivity extends BaseMainActivity {
    private static final String TAG = "DragPhotoActivity";


    private ViewPager mViewPager;
    private List<String> mList;
    private DragPhotoView[] mPhotoViews;
    private List<DragPhotoView> mDragPhotoViews;
    private String url;
    private PhotoBrowseInfo mPhotoBrowseInfo;

    int mOriginLeft;
    int mOriginTop;
    int mOriginHeight;
    int mOriginWidth;
    int mOriginCenterX;
    int mOriginCenterY;
    private float mTargetHeight;
    private float mTargetWidth;
    private float mScaleX;
    private float mScaleY;
    private float mTranslationX;
    private float mTranslationY;

    @Override
    public void initView() {
        super.initView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      /*  DBManager.getInstance().queryPhoto().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);*/

        mPhotoBrowseInfo = getIntent().getParcelableExtra("url");
        LogUtil.e(TAG, "-----图片地址-------" + mPhotoBrowseInfo.toString());
        int photoCount = mPhotoBrowseInfo.getPhotosCount();
        LogUtil.e(TAG, "------图片个数------" + photoCount);
        mPhotoViews = new DragPhotoView[photoCount];
        mDragPhotoViews = new LinkedList<>();
        for (int i = 0; i < photoCount; i++) {
            DragPhotoView dragPhotoView = (DragPhotoView) View.inflate(this, R.layout.item_viewpager, null);
            dragPhotoView.setOnTapListener(new DragPhotoView.OnTapListener() {
                @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onTap(DragPhotoView view) {
                    finishWithAnimation();
                }
            });
            dragPhotoView.setOnExitListener(new DragPhotoView.OnExitListener() {
                @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onExit(DragPhotoView view, float x, float y, float w, float h) {
                    performExitAnimation(view, x, y, w, h);
                }
            });
            mDragPhotoViews.add(dragPhotoView);
        }
        test();

    }

    /*Subscriber<List<PhotoOrVideo>> mSubscriber = new Subscriber<List<PhotoOrVideo>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(List<PhotoOrVideo> photoOrVideos) {
            mList.clear();
            for (PhotoOrVideo photoOrVideo : photoOrVideos) {
                mList.add(photoOrVideo.getPhotoUrl());
            }
            LogUtil.e(TAG, "------数据库长度-------" + photoOrVideos.size());
            mPhotoViews = new DragPhotoView[mList.size()];
            if (!mSubscriber.isUnsubscribed()) {
                mSubscriber.unsubscribe();
            }

        }
    };*/

    private void test() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        LogUtil.e(TAG, "--------点击位置----" + mPhotoBrowseInfo.getCurrentPhotoPosition());
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mPhotoBrowseInfo.getPhotosCount();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                DragPhotoView photoView = mDragPhotoViews.get(position);
                String url = mPhotoBrowseInfo.getPhotoUrls().get(position);
                LogUtil.e(TAG, "--------选择图片地址----[" + url + "]----当前=" + position);
                PhotoLoaderUtil.display(DragPhotoActivity.this, photoView, url, UIUtils.getResource().getDrawable(R.drawable.default_img_failed));
                container.addView(photoView);
                return photoView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mDragPhotoViews.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);
            }
        });


        mViewPager.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        mViewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        mOriginLeft = getIntent().getIntExtra("left", 0);
                        mOriginTop = getIntent().getIntExtra("top", 0);
                        mOriginHeight = getIntent().getIntExtra("height", 0);
                        mOriginWidth = getIntent().getIntExtra("width", 0);
                        mOriginCenterX = mOriginLeft + mOriginWidth / 2;
                        mOriginCenterY = mOriginTop + mOriginHeight / 2;

                        int[] location = new int[2];

                        final DragPhotoView photoView = mDragPhotoViews.get(mPhotoBrowseInfo.getCurrentPhotoPosition());
                        photoView.getLocationOnScreen(location);

                        mTargetHeight = (float) photoView.getHeight();
                        mTargetWidth = (float) photoView.getWidth();
                        mScaleX = (float) mOriginWidth / mTargetWidth;
                        mScaleY = (float) mOriginHeight / mTargetHeight;

                        float targetCenterX = location[0] + mTargetWidth / 2;
                        float targetCenterY = location[1] + mTargetHeight / 2;

                        mTranslationX = mOriginCenterX - targetCenterX;
                        mTranslationY = mOriginCenterY - targetCenterY;
                        photoView.setTranslationX(mTranslationX);
                        photoView.setTranslationY(mTranslationY);

                        photoView.setScaleX(mScaleX);
                        photoView.setScaleY(mScaleY);

                        performEnterAnimation();

                        for (int i = 0; i < mPhotoBrowseInfo.getPhotosCount(); i++) {
                            mDragPhotoViews.get(i).setMinScale(mScaleX);
                        }
                    }
                });
//        mViewPager.setCurrentItem(mPhotoBrowseInfo.getCurrentPhotoPosition());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected BaseMainPresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_drag_photo;
    }

    /**
     * ===================================================================================
     * <p>
     * 底下是低版本"共享元素"实现   不需要过分关心  如有需要 可作为参考.
     * <p>
     * Code  under is shared transitions in all android versions implementation
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void performExitAnimation(final DragPhotoView view, float x, float y, float w, float h) {
        view.finishAnimationCallBack();
        float viewX = mTargetWidth / 2 + x - mTargetWidth * mScaleX / 2;
        float viewY = mTargetHeight / 2 + y - mTargetHeight * mScaleY / 2;
        view.setX(viewX);
        view.setY(viewY);

        float centerX = view.getX() + mOriginWidth / 2;
        float centerY = view.getY() + mOriginHeight / 2;

        float translateX = mOriginCenterX - centerX;
        float translateY = mOriginCenterY - centerY;


        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(view.getX(), view.getX() + translateX);
        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setX((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateXAnimator.setDuration(300);
        translateXAnimator.start();
        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(view.getY(), view.getY() + translateY);
        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateYAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animator.removeAllListeners();
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        translateYAnimator.setDuration(300);
        translateYAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void finishWithAnimation() {

        final DragPhotoView photoView = mDragPhotoViews.get(0);
        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(0, mTranslationX);
        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setX((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateXAnimator.setDuration(300);
        translateXAnimator.start();

        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(0, mTranslationY);
        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateYAnimator.setDuration(300);
        translateYAnimator.start();

        ValueAnimator scaleYAnimator = ValueAnimator.ofFloat(1, mScaleY);
        scaleYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleY((Float) valueAnimator.getAnimatedValue());
            }
        });
        scaleYAnimator.setDuration(300);
        scaleYAnimator.start();

        ValueAnimator scaleXAnimator = ValueAnimator.ofFloat(1, mScaleX);
        scaleXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });

        scaleXAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animator.removeAllListeners();
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        scaleXAnimator.setDuration(300);
        scaleXAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void performEnterAnimation() {
        final DragPhotoView photoView = mDragPhotoViews.get(0);
        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(photoView.getX(), 0);
        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setX((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateXAnimator.setDuration(300);
        translateXAnimator.start();

        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(photoView.getY(), 0);
        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        translateYAnimator.setDuration(300);
        translateYAnimator.start();

        ValueAnimator scaleYAnimator = ValueAnimator.ofFloat(mScaleY, 1);
        scaleYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleY((Float) valueAnimator.getAnimatedValue());
            }
        });
        scaleYAnimator.setDuration(300);
        scaleYAnimator.start();

        ValueAnimator scaleXAnimator = ValueAnimator.ofFloat(mScaleX, 1);
        scaleXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                photoView.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });
        scaleXAnimator.setDuration(300);
        scaleXAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onBackPressed() {
        finishWithAnimation();
    }

}
