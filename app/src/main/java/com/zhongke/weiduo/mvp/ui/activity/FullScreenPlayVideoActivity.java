package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lqr.audio.AudioPlayManager;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.zhongke.weiduo.R;

import org.apache.commons.lang3.StringUtils;

/**
 * 全屏播放视频的界面
 * Created by llj on 2017/10/23.
 */

public class FullScreenPlayVideoActivity extends AppCompatActivity {
    private NormalGSYVideoPlayer webPlayer;

    //    private OrientationUtils orientationUtils;
//
//    private boolean isPlay;
//    private boolean isPause;
    private String url;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        if (StringUtils.isEmpty(url)) {
            finish();
            return;
        }
        title = getIntent().getStringExtra("title");
        setContentView(R.layout.activity_full_screen_video_play);
        webPlayer = (NormalGSYVideoPlayer) findViewById(R.id.video_player);
        initPlayer();
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        isPause = false;
//    }

    @Override
    protected void onPause() {
        super.onPause();
//        isPause = true;
        AudioPlayManager.getInstance().stopPlay();
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        //如果旋转了就全屏
//        if (isPlay && !isPause) {
//            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
//                if (!webPlayer.isIfCurrentIsFullscreen()) {
//                    webPlayer.startWindowFullscreen(FullScreenPlayVideoActivity.this, true, true);
//                }
//            } else {
//                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
//                if (webPlayer.isIfCurrentIsFullscreen()) {
//                    StandardGSYVideoPlayer.backFromWindowFull(this);
//                }
//                if (orientationUtils != null) {
//                    orientationUtils.setEnable(true);
//                }
//            }
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (orientationUtils != null)
        GSYVideoPlayer.releaseAllVideos();
//        if (orientationUtils != null) {
//            orientationUtils.releaseListener();
//        }
    }


    /**
     * 初始化播放器
     */
    public void initPlayer() {
//        String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
        webPlayer.setUp(url, false, null, title);
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.wugeng);
        // 自定义的播放进度条seekBar的样式
        webPlayer.setBottomShowProgressBarDrawable(
                ContextCompat.getDrawable(FullScreenPlayVideoActivity.this, R.drawable.video_play_seek_bar_progress)
                , ContextCompat.getDrawable(FullScreenPlayVideoActivity.this, R.drawable.video_play_seek_bar_thumb));
//        webPlayer.setBottomProgressBarDrawable(ContextCompat.getDrawable(FullScreenPlayVideoActivity.this, R.drawable.video_play_seek_bar_progress));
        webPlayer.setThumbImageView(imageView);
//        webPlayer.getTitleTextView().setVisibility(View.GONE);
        webPlayer.getTitleTextView().setText(title);
        webPlayer.getBackButton().setVisibility(View.GONE);
        //外部辅助的旋转，帮助全屏
//        orientationUtils = new OrientationUtils(this, webPlayer);
//        //初始化不打开外部的旋转
//        orientationUtils.setEnable(false);
        webPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        webPlayer.setRotateViewAuto(false);
        webPlayer.setLockLand(true);
        webPlayer.setShowFullAnimation(false);
        webPlayer.setNeedLockFull(true);
        webPlayer.getFullscreenButton().setVisibility(View.GONE);
//        webPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                LogUtil.e(TAG, "执行横屏操作");
//                //直接横屏
//                orientationUtils.resolveByClick();
//                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
//                webPlayer.startWindowFullscreen(FullScreenPlayVideoActivity.this, true, true);
//            }
//        });

//        webPlayer.setStandardVideoAllCallBack(new SampleListener() {
//            @Override
//            public void onPrepared(String url, Object... objects) {
//                super.onPrepared(url, objects);
//                //开始播放了才能旋转和全屏
//                orientationUtils.setEnable(true);
//                isPlay = true;
//            }
//
//            @Override
//            public void onAutoComplete(String url, Object... objects) {
//                super.onAutoComplete(url, objects);
//            }
//
//            @Override
//            public void onClickStartError(String url, Object... objects) {
//                super.onClickStartError(url, objects);
//            }
//
//            @Override
//            public void onQuitFullscreen(String url, Object... objects) {
//                super.onQuitFullscreen(url, objects);
//                if (orientationUtils != null) {
//                    orientationUtils.backToProtVideo();
//                }
//            }
//        });
//
//        webPlayer.setLockClickListener(new LockClickListener() {
//            @Override
//            public void onClick(View view, boolean lock) {
//                if (orientationUtils != null) {
//                    //配合下方的onConfigurationChanged
//                    orientationUtils.setEnable(!lock);
//                }
//            }
//        });
    }

    public static void startActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, FullScreenPlayVideoActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

}
