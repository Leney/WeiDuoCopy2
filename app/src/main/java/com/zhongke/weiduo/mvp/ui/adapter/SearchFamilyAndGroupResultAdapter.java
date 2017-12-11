package com.zhongke.weiduo.mvp.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.SearchFamilyAndGroupResultBean;

import java.util.List;

/**
 * 搜索好友结果列表Adapter
 * Created by ${llj} on 2017/9/4.
 */

public class SearchFamilyAndGroupResultAdapter extends RecyclerView.Adapter<SearchFamilyAndGroupResultAdapter.SearchResultViewHolder> {
    private Context context;
    private List<SearchFamilyAndGroupResultBean.ResultListBean> list;
    private OnResultItemClickListener mClickListener;

    public SearchFamilyAndGroupResultAdapter(Context context, List<SearchFamilyAndGroupResultBean.ResultListBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_search_family_and_group_result, parent, false);
//        view.setOnClickListener(this);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        SearchFamilyAndGroupResultBean.ResultListBean bean = list.get(position);
        holder.name.setText(bean.getGName());
        PhotoLoaderUtil.display(context, holder.icon, bean.getGIconUrl(), R.drawable.default_img);
        holder.itemLay.setTag(R.id.search_family_and_group_result_adapter_lay, bean);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class SearchResultViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemLay;
        ImageView icon;
        TextView name;

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            itemLay = (LinearLayout) itemView.findViewById(R.id.search_family_and_group_result_adapter_lay);
            icon = (ImageView) itemView.findViewById(R.id.search_family_and_group_result_adapter_icon);
            name = (TextView) itemView.findViewById(R.id.search_family_and_group_result_adapter_name);
            itemLay.setOnClickListener(itemClickListener);
        }
    }

    /**
     * 设置item点击事件
     *
     * @param listener
     */
    public void setOnResultItemClickListener(OnResultItemClickListener listener) {
        this.mClickListener = listener;
    }


    /**
     * item 点击事件
     */
    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mClickListener == null) return;
            mClickListener.onResultItemClick((SearchFamilyAndGroupResultBean.ResultListBean) v.getTag(R.id.search_family_and_group_result_adapter_lay));
        }
    };

    public interface OnResultItemClickListener {
        void onResultItemClick(SearchFamilyAndGroupResultBean.ResultListBean bean);
    }
}
