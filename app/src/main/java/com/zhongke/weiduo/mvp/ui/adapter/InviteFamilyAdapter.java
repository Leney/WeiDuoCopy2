package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.ColorUtils;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * Created by ${xingen} on 2017/6/22.
 */

public class InviteFamilyAdapter  extends BaseAdapter {
    private Context context;
    public InviteFamilyAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 3;
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
        convertView =View.inflate(context, R.layout.invitefamily_listview_item,null);
        TextView tip_tv=(TextView) convertView.findViewById(R.id.invitefamily_tip_tv);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.invitefamily_icon_iv);
        setImageView(imageView);
        switch (position){
            case 0:
                return convertView;
            case 1:
            case 2:
                tip_tv.setTextColor(ColorUtils.stringToColor("#1cbf61"));
                tip_tv.setText("提醒");
                tip_tv.setBackgroundResource(R.drawable.invitefamily_tip_tv_shape);
                return convertView;
            default:
                return null;
        }

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
