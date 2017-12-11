package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by ${xingen} on 2017/6/22.
 */

public class ActivityEvaluationAdapter  extends BaseAdapter {
    private Context context;
    public ActivityEvaluationAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 2;
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
        convertView=View.inflate(context, R.layout.activityevaluation_listview_item,null);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.activityevaluation_icon_iv);
        setImageView(imageView);
        if (position==1){
            convertView.findViewById(R.id.activityevaluation_line).setVisibility(View.GONE);
        }
        return convertView;
    }
    /**
     *  设置指定圆角度的图片
     * @param imageView
     */
    public void setImageView(final ImageView imageView){
        Glide.with(context).load(R.mipmap.ic_launcher).asBitmap().error(R.mipmap.ic_launcher).into(new BitmapImageViewTarget(imageView){
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable bitmapDrawable= RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                //指定圆角度数
                bitmapDrawable.setCornerRadius(DisplayUtils.dip2px(context,5));
                imageView.setImageDrawable(bitmapDrawable);
            }
        });

    }
}
