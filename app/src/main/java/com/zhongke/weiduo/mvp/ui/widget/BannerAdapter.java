package com.zhongke.weiduo.mvp.ui.widget;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/6/27.
 */

public class BannerAdapter extends PagerAdapter {
    private List<ImageView> mList;

    public BannerAdapter(List<ImageView> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position % mList.size()));
        return mList.get(position % mList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position % mList.size()));
    }
}
