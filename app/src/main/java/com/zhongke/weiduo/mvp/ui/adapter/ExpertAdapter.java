package com.zhongke.weiduo.mvp.ui.adapter;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.NewExpertBean;
import com.zhongke.weiduo.mvp.presenter.ExpertPresenter;
import com.zhongke.weiduo.util.GlideCircleTransform;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/4.
 */

public class ExpertAdapter extends RecyclerView.Adapter<ExpertAdapter.ExpertViewHolder> implements View.OnClickListener {
    //    private List<ExpertBean> expertBeanList;
    private Context context;
    private OnItemClickListeners onItemClickListeners;
    private RecyclerView recyclerView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private List<NewExpertBean.RecordsBean> list;
    //头布局
    private View mHeaderView;

    public ExpertAdapter(List<NewExpertBean.RecordsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ExpertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new ExpertViewHolder(mHeaderView);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_expert, parent, false);
        view.setOnClickListener(this);
        return new ExpertViewHolder(view);
    }

    public void myNotifyDataSetChanged(List<NewExpertBean.RecordsBean> list, int stadius) {
        if (stadius == ExpertPresenter.REFRESH_STATUS) {
            this.list.clear();
            this.list.addAll(list);
        } else {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ExpertViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER)
            return;
        final int pos = getRealPosition(holder);
        NewExpertBean.RecordsBean expertBean = list.get(pos);
        holder.tvName.setText(expertBean.getNickName());
        holder.tvIntroduce.setText(expertBean.getInfo());
//        holder.tvFans.setText(expertBean.getFans() + "粉丝");
//        holder.tvCurriculum.setText("共" + expertBean.getCurriculum() + "节课");
        Glide.with(context).load(expertBean.getCoverUrl()).transform(new GlideCircleTransform(context)).into(holder.ivPhoto);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public void setHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? list.size() : list.size() + 1;
    }

    @Override
    public void onClick(View v) {
        if (recyclerView != null && onItemClickListeners != null) {
            int position = recyclerView.getChildAdapterPosition(v);
            onItemClickListeners.clickItem(recyclerView, v, position - 1);
        }
    }

    static class ExpertViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvIntroduce, tvFans, tvCurriculum;
        ImageView ivPhoto;

        public ExpertViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_expert_name);
            tvIntroduce = (TextView) itemView.findViewById(R.id.tv_expert_introduce);
            tvFans = (TextView) itemView.findViewById(R.id.tv_fans);
            tvCurriculum = (TextView) itemView.findViewById(R.id.tv1);
            ivPhoto = (ImageView) itemView.findViewById(R.id.expert_photo);
        }
    }

    //点击事件监听器
    public interface OnItemClickListeners {
        void clickItem(RecyclerView Parent, View view, int position);

    }

    public void setOnItemClickListeners(OnItemClickListeners onItemClickListeners) {
        this.onItemClickListeners = onItemClickListeners;
    }
}
