package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.base.BaseRecyclerAdapter;
import com.zhongke.weiduo.mvp.model.entity.FindRecommendBean;
import com.zhongke.weiduo.mvp.ui.activity.ActivityDetailActivity;

/**
 * 发现Fragment Adapter
 * Created by llj on 2017/9/4.
 */

public class FindActAdapter extends BaseRecyclerAdapter<FindRecommendBean.RecordsBean> {

    private Context mContext;

    private Drawable likeDrawable;

    private Drawable unlikeDrawable;

    private LikeClickListener likeListener;

    public FindActAdapter(Context context) {
        this.mContext = context;
        likeDrawable = ContextCompat.getDrawable(context, R.drawable.ic_like);
        likeDrawable.setBounds(0, 0, likeDrawable.getMinimumWidth(), likeDrawable.getMinimumHeight());
        unlikeDrawable = ContextCompat.getDrawable(context, R.drawable.ic_like_normal);
        unlikeDrawable.setBounds(0, 0, unlikeDrawable.getMinimumWidth(), unlikeDrawable.getMinimumHeight());
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_act_adapter, parent, false);
        return new ItemViewHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, FindRecommendBean.RecordsBean data) {
        if (viewHolder instanceof ItemViewHolder) {
            ItemViewHolder holder = (ItemViewHolder) viewHolder;
            PhotoLoaderUtil.display(mContext, holder.icon, data.getCoverUrl(), R.drawable.default_img);
            holder.name.setText(data.getTitle());
            holder.date.setText(String.format(mContext.getString(R.string.format_data), data.getBeginTime().substring(0,10)));
            holder.address.setText(String.format(mContext.getString(R.string.format_address), data.getAddress()));
            holder.like.setText(String.valueOf(data.getThumbUpCount()));
            //holder.comment.setText(String.valueOf(data.commentNum));
            //holder.location.setText(data.location);
            holder.like.setCompoundDrawables(data.isLike()?likeDrawable : unlikeDrawable, null, null, null);
            holder.like.setTag(data);
            holder.like.setTag(R.id.find_adapter_like, RealPosition);
            holder.itemLay.setTag(R.id.find_adapter_icon, data);
        }
    }

    private class ItemViewHolder extends BaseRecyclerAdapter.Holder {
        RelativeLayout itemLay;
        ImageView icon;
        TextView name;
        TextView date;
        TextView address;
        TextView like, comment, location;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemLay = (RelativeLayout) itemView.findViewById(R.id.find_adapter_item_lay);
            itemLay.setOnClickListener(itemClickListener);
            icon = (ImageView) itemView.findViewById(R.id.find_adapter_icon);
            name = (TextView) itemView.findViewById(R.id.find_adapter_name);
            date = (TextView) itemView.findViewById(R.id.find_adapter_date);
            address = (TextView) itemView.findViewById(R.id.find_adapter_address);
            like = (TextView) itemView.findViewById(R.id.find_adapter_like);
            like.setOnClickListener(likeClickListener);
            comment = (TextView) itemView.findViewById(R.id.find_adapter_comment);
            location = (TextView) itemView.findViewById(R.id.find_adapter_location);
        }
    }


    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FindRecommendBean.RecordsBean bean = (FindRecommendBean.RecordsBean) v.getTag(R.id.find_adapter_icon);
            ActivityDetailActivity.startActivity(v.getContext(), bean.getId(), bean.getTitle());
        }
    };

    /**
     * 点赞点击事件
     */
    private View.OnClickListener likeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FindRecommendBean.RecordsBean bean = (FindRecommendBean.RecordsBean) v.getTag();
            int position = (int) v.getTag(R.id.find_adapter_like);
//            LogUtil.e("position---"+position);
            bean.setThumbUpCount(bean.getThumbUpCount()+1);
            bean.setLike(true);
            if (likeListener != null) {
                if(getHeaderView() != null) {
                    likeListener.likeClick(position - 1,true);
                } else {
                    likeListener.likeClick(position,false);
                }
            }
        }
    };

    public interface LikeClickListener{
         void likeClick(int position,boolean hasHead);
    }

    public void setLikeClickListener(LikeClickListener listener) {
        likeListener = listener;
    }

}
