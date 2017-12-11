package com.zhongke.weiduo.mvp.ui.widget.swiperefreshlayout;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ${xinGen} on 2017/6/19.
 */
public class ScrollChildSwipeRefreshLayout extends SwipeRefreshLayout {
    private View scrollUpChild;

    public ScrollChildSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置在哪个view中触发刷新。
     *
     * @param view
     */
    public void setScrollUpChild(View view) {
        this.scrollUpChild = view;
    }

    /**
     * ViewCompat..canScrollVertically（）：用于检查view是否可以在某个方向上垂直滑动
     *
     * @return
     */
    @Override
    public boolean canChildScrollUp() {

        if (scrollUpChild != null) {
            return ViewCompat.canScrollVertically(scrollUpChild, -1);
        }
        return super.canChildScrollUp();
    }

    /**
     * 控制SwipeRefreshLayout的显示与隐藏
     *
     * @param active
     */
    public void setLoadingIndicator(final boolean active) {

        /**
         *     通过swipeRefreshLayout.post来调用swipeRefreshLayout.setRefreshing（）来实现，一进入页面就自动下拉提示窗。
         */
        this.post(() ->
                //确保布局加载完成后，调用
                this.setRefreshing(active)
        );
    }

    /**
     * 设置默认颜色
     */
    public void setRefreshColor(){
         this.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
    }

}