package com.zhongke.weiduo.mvp.ui.adapter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ${tanlei} on 2017/7/3.
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    private int left;
    private int right;
    private int top;

    public RecyclerItemDecoration(int left, int right, int top) {
        this.left = left;
        this.right = right;
        this.top = top;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int childPosition = parent.getChildAdapterPosition(view);
        if (childPosition == 0) {
            return;
        }
        if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {//如果是竖直方向
            if (childPosition % layoutManager.getSpanCount() == 0) {//判断是左边还是右边
                outRect.left = right / 2;
                outRect.right = left;
            } else {
                outRect.left = left;
                outRect.right = right / 2;
            }
            if (childPosition <= layoutManager.getSpanCount()) {//判断是否为第一排
                outRect.bottom = top;
                outRect.top = top;
            } else {
                outRect.bottom = top;
            }

        }
    }
}
