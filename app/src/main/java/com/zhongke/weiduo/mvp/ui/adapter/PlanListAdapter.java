package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.PlanBean2;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/18.
 * 学习计划适配器
 */

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.LearnPlanViewHolder> implements View.OnClickListener {
    private List<PlanBean2.RecordsBean> list;
    private Context context;
    private OnItemClickListeners onItemClickListeners;

    private Drawable likeDrawable;

    private Drawable unlikeDrawable;

    private OnLikeListener likeListener;

    public void setLikeListener(OnLikeListener likeListener) {
        this.likeListener = likeListener;
    }

    public OnItemClickListeners getOnItemClickListeners() {
        return onItemClickListeners;
    }

    public void setOnItemClickListeners(OnItemClickListeners onItemClickListeners) {
        this.onItemClickListeners = onItemClickListeners;
    }

    public PlanListAdapter(List<PlanBean2.RecordsBean> list, Context context) {
        this.list = list;
        this.context = context;
        likeDrawable = ContextCompat.getDrawable(context, R.drawable.ic_like);
        likeDrawable.setBounds(0, 0, likeDrawable.getMinimumWidth(), likeDrawable.getMinimumHeight());
        unlikeDrawable = ContextCompat.getDrawable(context, R.drawable.ic_like_normal);
        unlikeDrawable.setBounds(0, 0, unlikeDrawable.getMinimumWidth(), unlikeDrawable.getMinimumHeight());
    }

    @Override
    public LearnPlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_plan_item, parent, false);
        LearnPlanViewHolder holder = new LearnPlanViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(LearnPlanViewHolder holder, int position) {
        PlanBean2.RecordsBean bean = list.get(position);
        Glide.with(context).load(bean.getCoverUrl()).into(holder.ivPlanPhoto);
        holder.tvPlanName.setText(bean.getBName());
        holder.tvPlanText.setText(bean.getBInfo());
        holder.tvPlanThumbs.setText(bean.getThumbUpCount() + "人点赞");
        holder.tvPlanThumbs.setCompoundDrawables(bean.isLike() ? likeDrawable : unlikeDrawable, null, null, null);
//        if (list.get(position).getPrice() == 0) {
//            holder.tvPlanPrice.setText("免费");
//        } else {
//            holder.tvPlanPrice.setText("￥" + bean.getPrice());
//        }
        holder.tvPlanUse.setText(bean.getUserCount() + "人使用");
        holder.itemView.setTag(position);
        holder.tvPlanThumbs.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (getOnItemClickListeners() != null) {
            onItemClickListeners.onItemClick(v, (int) v.getTag());
        }
    }

    class LearnPlanViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPlanPhoto;
        TextView tvPlanName, tvPlanText, tvPlanPrice, tvPlanThumbs, tvPlanUse;

        public LearnPlanViewHolder(View itemView) {
            super(itemView);
            ivPlanPhoto = (ImageView) itemView.findViewById(R.id.iv_plan_photo);
            tvPlanName = (TextView) itemView.findViewById(R.id.tv_plan_name);
            tvPlanText = (TextView) itemView.findViewById(R.id.tv_plan_text);
            tvPlanPrice = (TextView) itemView.findViewById(R.id.tv_plan_price);
            tvPlanThumbs = (TextView) itemView.findViewById(R.id.tv_plan_thumbs);
            tvPlanThumbs.setOnClickListener(likeClickListener);
            tvPlanUse = (TextView) itemView.findViewById(R.id.tv_plan_use);
        }
    }

    /**
     * 回调接口
     */
    public interface OnItemClickListeners {
        void onItemClick(View v, int position);
    }

    /**
     * 点赞点击事件
     */
    private View.OnClickListener likeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            PlanBean2.RecordsBean bean = list.get(position);
            if (bean.isLike()) return;
            bean.setThumbUpCount(bean.getThumbUpCount() + 1);
            bean.setLike(!bean.isLike());
            // 后面那个参数是为了保证刷新item时不闪烁
            notifyItemChanged(position, "");
            if (likeListener != null) {
                likeListener.clickLike(bean);
            }
        }
    };

    public interface OnLikeListener {
        void clickLike(PlanBean2.RecordsBean bean);
    }
}
