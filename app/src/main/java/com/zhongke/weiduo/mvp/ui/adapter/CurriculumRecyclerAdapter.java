package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.school_course.CourseListResult;

import java.util.List;

import static com.zhongke.weiduo.mvp.presenter.SchoolPresenter.REFRESH_STATUS;

/**
 * Created by ${tanlei} on 2017/7/1.
 */

public class CurriculumRecyclerAdapter extends RecyclerView.Adapter<CurriculumRecyclerAdapter.CurriculumViewHolder> implements View.OnClickListener {
    private Context context;
    private List<CourseListResult.RecordsBean> records;
    private OnItemClickListeners onItemClickListeners;
    private RecyclerView recyclerView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    //头布局
    private View mHeaderView;

    public CurriculumRecyclerAdapter(Context context, List<CourseListResult.RecordsBean> records) {
        this.context = context;
        this.records = records;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);
    }

    @Override
    public CurriculumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new CurriculumViewHolder(mHeaderView);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.curriculum_item, parent, false);
        view.setOnClickListener(this);
        CurriculumViewHolder curriculumViewHolder = new CurriculumViewHolder(view);
        return curriculumViewHolder;
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

    public void myNotifyDataSetChanged(List<CourseListResult.RecordsBean> records, int stadius) {
        if (stadius == REFRESH_STATUS) {
            this.records.clear();
            this.records.addAll(records);
        } else {
            this.records.addAll(records);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    @Override
    public void onBindViewHolder(CurriculumViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER)
            return;
        final int pos = getRealPosition(holder);
        CourseListResult.RecordsBean curriculumBean = records.get(pos);
        holder.tvNameCurriculum.setText(curriculumBean.getCourseName());
//        holder.tvCurriculumBaoming.setText(curriculumBean.getSignUp());
//        holder.tvCurriculumDate.setText(curriculumBean.getDate());
//        holder.tvStage.setText(curriculumBean.getStage());
        PhotoLoaderUtil.display(context, holder.ivPictureCurriculum, curriculumBean.getCoverUrl(), null);
        int courseKind = curriculumBean.getCourseKind();
        if (courseKind == 1) {
            holder.courseKind.setText("直播");
        } else if (courseKind == 2) {
            holder.courseKind.setText("点播");
        } else if (courseKind == 3) {
            holder.courseKind.setText("线下");
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? records.size() : records.size() + 1;
    }

    @Override
    public void onClick(View v) {
        if (recyclerView != null && onItemClickListeners != null) {
            int position = recyclerView.getChildAdapterPosition(v);
            onItemClickListeners.clickItem(recyclerView, v, position - 1);
        }
    }

    static class CurriculumViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameCurriculum, tvStage, tvCurriculumDate, tvCurriculumBaoming,courseKind;
        ImageView ivPictureCurriculum;

        public CurriculumViewHolder(View itemView) {
            super(itemView);
            tvNameCurriculum = (TextView) itemView.findViewById(R.id.tv_name_curriculum);
            tvStage = (TextView) itemView.findViewById(R.id.tv_stage);
            tvCurriculumDate = (TextView) itemView.findViewById(R.id.tv_curriculum_date);
            tvCurriculumBaoming = (TextView) itemView.findViewById(R.id.tv_curriculum_baoming);
            ivPictureCurriculum = (ImageView) itemView.findViewById(R.id.iv_picture_curriculum);
            courseKind = (TextView) itemView.findViewById(R.id.curriculum_course_kind);
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
