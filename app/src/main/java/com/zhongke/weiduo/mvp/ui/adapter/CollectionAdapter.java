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
import com.zhongke.weiduo.mvp.model.entity.UserCollectItem;
import com.zhongke.weiduo.mvp.model.entity.UserCollectListBean;

import java.util.List;

/**
 * Created by Karma on 2017/9/19.
 */

public class CollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "CollectionAdapter";
    private Context context;
    private List<UserCollectItem> collectLis;
    private static int FIRST_TYPE = 1;
    private static int DEFAULT_TYPE=2;

    public CollectionAdapter(Context context, List<UserCollectItem> collectLis) {
        this.context = context;
        this.collectLis = collectLis;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == FIRST_TYPE) {
//            View v = LayoutInflater.from(context).inflate(R.layout.item_collection_first,parent,false);
//            TheFirstHolder firstHolder =new TheFirstHolder(v);
//            return firstHolder;
//        } else {
//            View v = LayoutInflater.from(context).inflate(R.layout.item_collection,parent,false);
//            CollectionHolder collectionHolder = new CollectionHolder(v);
//            return collectionHolder;
//        }
        View v = LayoutInflater.from(context).inflate(R.layout.item_collection,parent,false);
        CollectionHolder collectionHolder = new CollectionHolder(v);
        return collectionHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CollectionHolder collectionHolder = (CollectionHolder)holder;
        UserCollectItem bean = collectLis.get(position);
        if (bean.ImageUrl != null) {
            Glide.with(context).load(bean.ImageUrl).override(700,240).centerCrop().into(collectionHolder.content_img);
        }
        if (bean.title != null) {
            collectionHolder.title.setText(bean.title);
        }
        if (bean.time != null) {
            collectionHolder.issue_time.setText(bean.time);
        }
    }

    @Override
    public int getItemCount() {
        if (collectLis !=null && collectLis.size() > 0) {
            return collectLis.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
//        if (position == 0) {
//            return FIRST_TYPE;
//        } else {
//            return DEFAULT_TYPE;
//        }
        return DEFAULT_TYPE;
    }

    private static class TheFirstHolder extends RecyclerView.ViewHolder {
        ImageView firstImg;
        TextView firstTitle,firstAuthor,firstTime;

        public TheFirstHolder(View itemView) {
            super(itemView);
            firstImg = (ImageView) itemView.findViewById(R.id.first_img);
            firstTitle = (TextView) itemView.findViewById(R.id.first_title);
            firstAuthor = (TextView) itemView.findViewById(R.id.first_author);
            firstTime = (TextView) itemView.findViewById(R.id.first_time);
        }
    }

    private static class CollectionHolder extends RecyclerView.ViewHolder{
        ImageView content_img;
        TextView title,issue_time;
        public CollectionHolder(View itemView) {
            super(itemView);
            content_img = (ImageView) itemView.findViewById(R.id.content_img);
            title = (TextView) itemView.findViewById(R.id.collection_title);
            issue_time = (TextView) itemView.findViewById(R.id.issue_time);
        }
    }

}
