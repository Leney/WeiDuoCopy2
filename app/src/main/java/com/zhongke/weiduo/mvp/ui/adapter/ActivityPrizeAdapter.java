package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.ui.widget.view.RewardView;
import com.zhongke.weiduo.util.BitmapUtils;

/**
 * Created by ${xingen} on 2017/6/23.
 */

public class ActivityPrizeAdapter extends BaseAdapter {
    private Context context;

    public ActivityPrizeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RewardView rewardView=new RewardView(context);
        switch (position) {
            case 1:
                Bitmap normalBitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.microrudder_add);
                rewardView.loadData(RewardView.STATE_1, BitmapUtils.drawnRoundBitmap(normalBitmap),"公仔");
                break;
            case 2:
                rewardView.loadData(RewardView.STATE_2,"80","积分");
                break;
            case 0:
            default:
                rewardView.loadData(RewardView.STATE_0,"100","金币");
                break;
        }
        return  rewardView;
    }
}
