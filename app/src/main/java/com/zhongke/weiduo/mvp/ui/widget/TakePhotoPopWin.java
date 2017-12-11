package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;

/**
 * Created by ${tanlei} on 2017/6/22.
 */

public class TakePhotoPopWin extends PopupWindow implements View.OnClickListener {
    private OnClickItemListeners onClickItemListeners;
    private TextView tvAlbum, tvPhotograph, tvCancel;

    /**
     * @param mContext
     * @param view     需要弹出的布局
     */
    public TakePhotoPopWin(Context mContext, View view) {
        // 设置外部可点击,点击外部后弹出框影藏
        this.setOutsideTouchable(true);
        // 设置视图
        this.setContentView(view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);
        tvAlbum = (TextView) view.findViewById(R.id.album);
        tvPhotograph = (TextView) view.findViewById(R.id.photograph);
        tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
        tvAlbum.setOnClickListener(this);
        tvPhotograph.setOnClickListener(this);
    }

    public void setOnClickItemListeners(OnClickItemListeners onClickItemListeners) {
        this.onClickItemListeners = onClickItemListeners;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.album:
                onClickItemListeners.clickItem(1);
                break;
            case R.id.photograph:
                onClickItemListeners.clickItem(2);
                break;
            case R.id.tv_cancel:
                onClickItemListeners.clickItem(3);
                break;
            default:
                break;
        }
    }

    public interface OnClickItemListeners {
        void clickItem(int position);
    }
}
