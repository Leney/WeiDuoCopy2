package com.zhongke.weiduo.mvp.ui.widget.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * 作者：新根
 * <p>
 * 博客链接：http://blog.csdn.net/hexingen
 * 自定义：自动加载的RecyclerView，结合SwipeRefreshLayout上拉刷新使用
 */
public class LoadMoreRecyclerView extends RecyclerView {

    public LoadMoreRecyclerView(Context context) {
        super(context);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 滑动停止后，最后一个可见item的位置
     */
    private int lastItemPosition;
    /**
     * 是否正在自动加载的标识
     * 1.防止多次触发
     * 2.可控制是否需要加载更多
     */
    private boolean isCanLoadMore = true;

    /**
     * 添加滚动监听事件
     */
    public void addScroller() {
        this.addOnScrollListener(new OnScrollListener() {
            /**
             * 当RecyclerView滚动时候，被调用。
             * @param recyclerView
             * @param newState
             * State的状态：
             *   1. SCROLL_STATE_IDLE ：空闲，停止滚动
             *   2. SCROLL_STATE_DRAGGING ：滚动。用户手机在触碰和手指在屏幕上
             *   3. SCROLL_STATE_SETTLING ：惯性滑动，定制的状态
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //当滑动停止状态
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //剩余少于一个item时，进行处理操作
                    if (lastItemPosition + 1 >= getLayoutManager().getItemCount()) {
                        if (isCanLoadMore) {
                            //设置正在加载的标识
                            setCanLoadMore(false);
                            //开启加载更多的回调事件
                            getLoadmoreListener().onLoadMore();
                        }
                    }
                }
            }

            /**
             *在RecyclerView滚动完成后被调用。
             * 当item范围发生改变通过一个布局计算时候，也会调用，同时dx,dy为0。
             * @param recyclerView
             * @param dx
             * @param dy
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LayoutManager layoutManager = getLayoutManager();
                //用于滑动后，最后一个可见Item的position
                if (layoutManager instanceof LinearLayoutManager) {
                    lastItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof GridLayoutManager) {
                    lastItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    int[] positions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
                    //先判断下这个数组的最大值,根据StaggeredGridLayoutManager设置的列数来定
                    // lastItemPosition=Math.max(positions[0],positions[1]);
                }
            }
        });
    }

    private boolean isCanLoadMore() {
        return isCanLoadMore;
    }

    /**
     * 当自动加载的回调事件响应后，需手动调用。
     *
     * @param canLoadMore
     */
    public void setCanLoadMore(boolean canLoadMore) {
        isCanLoadMore = canLoadMore;
    }

    private LoadMoreListener loadmoreListener;

    private LoadMoreListener getLoadmoreListener() {
        return loadmoreListener;
    }

    public void setLoadmoreListener(LoadMoreListener loadmoreListener) {
        this.loadmoreListener = loadmoreListener;
        this.addScroller();
    }

    /**
     * 加载更多的回调接口
     */
    public interface LoadMoreListener {
        void onLoadMore();
    }
}
