package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.ActivityListBean;
import com.zhongke.weiduo.mvp.ui.widget.recyclerview.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/26.
 */

public class MovableListAdapter extends RecyclerView.Adapter<MovableListAdapter.ViewHolder> {
    private List<ActivityListBean.RecordsBean> list;
    private Context context;
    private RecyclerViewItemClickListener itemClickListener;
    public MovableListAdapter( Context context,RecyclerViewItemClickListener itemClickListener) {
        this.list = new ArrayList<>();
        this.context = context;
        this.itemClickListener=itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView=LayoutInflater.from(parent.getContext()).inflate( R.layout.item_movable,parent,false);
        ViewHolder viewHolder=new ViewHolder(rootView);
        rootView.setOnClickListener(view->{
            if (itemClickListener!=null){
                itemClickListener.onClick(view,viewHolder.getLayoutPosition());
            }
        });
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActivityListBean.RecordsBean mb = list.get(position);
        holder.tvTitle.setText(mb.getTitle());
        holder.tvLocation.setText(mb.getAddress());
        holder.tvName.setText(mb.getTitle());
        holder.tvTime.setText(mb.getBeginTime());
        holder.tvNumber.setText(mb.getPlanCount() + "");
        Glide.with(context)
                .load(mb.getCoverUrl())
                .into(holder.ivPhoto);
        //"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507025672&di=04d6cfa937b5830043c95557bc484dc3&imgtype=jpg&er=1&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F74%2F37%2F60858PICFNa_1024.jpg"
    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public List<ActivityListBean.RecordsBean> getList() {
        return list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvName, tvLocation, tvNumber, tvTime, tvRecommend;
        ImageView ivPhoto;
        public ViewHolder(View convertView) {
            super(convertView);
            ivPhoto = (ImageView) convertView.findViewById(R.id.iv);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvName = (TextView) convertView.findViewById(R.id.tv5);
            tvLocation = (TextView) convertView.findViewById(R.id.tv2);
            tvNumber = (TextView) convertView.findViewById(R.id.tv4);
            tvTime = (TextView) convertView.findViewById(R.id.tv3);
            tvRecommend = (TextView) convertView.findViewById(R.id.tv_recommend);
        }
    }

    /**
     * 改变数据
     * @param beanList
     */
    public void changeData(List<ActivityListBean.RecordsBean>  beanList){
        this.list.clear();
        this.list.addAll(beanList);
        this.notifyDataSetChanged();
    }

    /**
     * 新增数据
     * @param beanList
     */
    public  void addData(List<ActivityListBean.RecordsBean>  beanList){
        int size=list.size();
        this.list.addAll(size,beanList);
        this.notifyItemInserted(size);
    }
}
