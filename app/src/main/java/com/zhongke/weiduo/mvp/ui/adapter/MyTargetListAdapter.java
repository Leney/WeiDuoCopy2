package com.zhongke.weiduo.mvp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.MyTargetBean;
import com.zhongke.weiduo.mvp.ui.widget.view.SlidingButtonView;

import java.util.List;

/**
 * Created by ${tanlei} on 2017/9/28.
 * 目标，计划，规划列表适配器
 */

public class MyTargetListAdapter extends RecyclerView.Adapter<MyTargetListAdapter.AimsViewHolder> implements SlidingButtonView.IonSlidingButtonListener {
    private SlidingButtonView mMenu = null;
    private List<MyTargetBean.RecordsBean> list;
    private Activity context;
    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    public MyTargetListAdapter(List<MyTargetBean.RecordsBean> list, Context context) {
        this.list = list;
        this.context = (Activity) context;
        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
    }

    public void updateData(List<MyTargetBean.RecordsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AimsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_aims_plan, null);
        AimsViewHolder holder = new AimsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AimsViewHolder holder, int position) {
        MyTargetBean.RecordsBean bean = list.get(position);
        holder.Content.getLayoutParams().width = context.getWindowManager().getDefaultDisplay().getWidth();
        holder.tvName.setText(bean.getName());

        holder.progressBar.setProgress((int) (bean.getPercent()* 100));
        holder.progress_text.setText(String.valueOf((int) (bean.getPercent()*100))+"%");
//        holder.system_source.setText(bean.systemSource);

        holder.Content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onItemClick(v, n);
                }
            }
        });
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;
    }

    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }

    class AimsViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName,progress_text,system_source;
        private RelativeLayout tvDelete, Content;
        private ProgressBar progressBar;

        public AimsViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvDelete = (RelativeLayout) itemView.findViewById(R.id.tv_delete);
            Content = (RelativeLayout) itemView.findViewById(R.id.layout_content);
            progressBar = (ProgressBar) itemView.findViewById(R.id.aims_plan_progress);
            progress_text = (TextView) itemView.findViewById(R.id.aims_plan_progress_text);
//            system_source = (TextView) itemView.findViewById(R.id.tv_system_source);
            ((SlidingButtonView) itemView).setSlidingButtonListener(MyTargetListAdapter.this);
        }
    }

    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);

        void onDeleteBtnCilck(View view, int position);
    }
}
