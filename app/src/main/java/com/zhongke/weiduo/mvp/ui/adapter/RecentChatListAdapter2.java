package com.zhongke.weiduo.mvp.ui.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.app.utils.TimeUtils;
import com.zhongke.weiduo.mvp.base.BaseRecyclerAdapter;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.ui.activity.SessionActivity2;
import com.zhongke.weiduo.mvp.ui.widget.slideitem.ItemSlideHelper;
import com.zhongke.weiduo.mvp.ui.widget.view.SlidingButtonView;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * 最近聊天列表Adapter
 * Created by llj on 2017/10/16.
 */

public class RecentChatListAdapter2 extends BaseRecyclerAdapter<ChatListBean> implements ItemSlideHelper.Callback {

    private RecyclerView mRecyclerView;
    private static final int ADD_FRIEND_VIEW_TYPE = 3;
    private static final int CHAT_MSG_VIEW_TYPE = 4;
    private Context mContext;

    private SlidingButtonView mMenu = null;

    private AgreeListener agreeListener;
    private RejectListener rejectListener;

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        if (viewType == ADD_FRIEND_VIEW_TYPE) {
            return new ViewHolderAddFriend(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recent_add_friend_item, parent, false));
        } else {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recent_list_item, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = super.getItemViewType(position);
        if (type == TYPE_NORMAL) {
            ChatListBean listBean = mDatas.get(position);
            if (listBean.type == ChatListBean.CHAT_TYPE_ADD_FRIEND) {
                return ADD_FRIEND_VIEW_TYPE;
            }
            return CHAT_MSG_VIEW_TYPE;
        }
        Log.i("llj", "viewType---------->>>" + type);
        return type;
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, ChatListBean data) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
            PhotoLoaderUtil.display(mContext, holder.icon, data.icon, null);
            holder.num.setText(String.valueOf(data.unreadMessageNum));
            holder.num.setVisibility(data.unreadMessageNum > 0 ? View.VISIBLE : View.GONE);
            holder.name.setText(data.name);
            holder.delete.setOnClickListener(new DeleteBtnListener() {
                @Override
                public void onClick(View v) {
                    int n = holder.getLayoutPosition();
                    mDatas.remove(n);
                    notifyItemRemoved(n);
                }
            });

            if (data.newestMessageIsReceiveType) {
                holder.msg.setText("对方：" + data.newestMsg);
            } else {
                holder.msg.setText("我：" + data.newestMsg);
            }
            if (data.newestMessageTime <= 0) {
                holder.time.setVisibility(View.GONE);
            } else {
                holder.time.setText(TimeUtils.getMsgFormatTime(data.newestMessageTime));
                holder.time.setVisibility(View.VISIBLE);
            }
            holder.itemLay.setTag(data);
        } else if (viewHolder instanceof ViewHolderAddFriend) {
            ViewHolderAddFriend holderAddFriend = (ViewHolderAddFriend) viewHolder;
            PhotoLoaderUtil.display(mContext, holderAddFriend.icon, data.icon, null);
            holderAddFriend.name.setText(data.name);
            holderAddFriend.msg.setText(data.newestMsg);

            if (data.addState == ChatListBean.NEW_ADD_FRIEND_STATE_PASSED) {
                // 已添加
                holderAddFriend.addBtn.setClickable(false);
                holderAddFriend.addBtn.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                holderAddFriend.addBtn.setTextColor(ContextCompat.getColor(mContext, R.color.text_666666));
                holderAddFriend.addBtn.setText("已添加");
                holderAddFriend.addBtn.setVisibility(View.VISIBLE);
                holderAddFriend.refuseBtn.setVisibility(View.GONE);
            } else if (data.addState == ChatListBean.NEW_ADD_FRIEND_STATE_ALREADY_SEND) {
                // 对方未处理
//                holderAddFriend.addBtn.setBackgroundColor(ContextCompat.getColor(mContext, R.color.main_color));
//                holderAddFriend.addBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//                holderAddFriend.addBtn.setText("同意");
//                holderAddFriend.addBtn.setVisibility(View.VISIBLE);
//                holderAddFriend.refuseBtn.setVisibility(View.VISIBLE);

                holderAddFriend.addBtn.setClickable(false);
                holderAddFriend.addBtn.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                holderAddFriend.addBtn.setTextColor(ContextCompat.getColor(mContext, R.color.text_666666));
                holderAddFriend.addBtn.setText("已申请");
                holderAddFriend.addBtn.setVisibility(View.VISIBLE);
                holderAddFriend.refuseBtn.setVisibility(View.GONE);

            } else if (data.addState == ChatListBean.NEW_ADD_FRIEND_STATE_UN_DO){
                // 自己未处理
                holderAddFriend.addBtn.setBackgroundColor(ContextCompat.getColor(mContext, R.color.main_color));
                holderAddFriend.addBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                holderAddFriend.addBtn.setText("同意");
                holderAddFriend.addBtn.setVisibility(View.VISIBLE);
                holderAddFriend.refuseBtn.setVisibility(View.VISIBLE);
            }
            else if (data.addState == ChatListBean.NEW_ADD_FRIEND_STATE_NO_PASSED) {
                // 已拒绝
                holderAddFriend.addBtn.setClickable(false);
                holderAddFriend.addBtn.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                holderAddFriend.addBtn.setTextColor(ContextCompat.getColor(mContext, R.color.text_666666));
                holderAddFriend.addBtn.setText("已拒绝");
                holderAddFriend.addBtn.setVisibility(View.VISIBLE);
                holderAddFriend.refuseBtn.setVisibility(View.GONE);
            }
            holderAddFriend.addBtn.setTag(data);
            holderAddFriend.refuseBtn.setTag(data);
            holderAddFriend.addBtn.setOnClickListener(addBtnClickListener);
            holderAddFriend.refuseBtn.setOnClickListener(refuseBtnClickListener);
        }
    }

    public class ViewHolder extends Holder {
        ImageView icon;
        TextView num, name, msg, time;
        ConstraintLayout contentLayout,itemLay, delete;

        public ViewHolder(View itemView) {
            super(itemView);
            //contentLayout = (ConstraintLayout) itemView.findViewById(R.id.recent_list_content_layout);
            itemLay = (ConstraintLayout) itemView.findViewById(R.id.recent_list_item_lay);
            itemLay.setOnClickListener(itemClickListener);
            delete = (ConstraintLayout) itemView.findViewById(R.id.recent_delete_Layout);
            icon = (ImageView) itemView.findViewById(R.id.recent_list_item_icon);
            num = (TextView) itemView.findViewById(R.id.recent_list_item_num);
            name = (TextView) itemView.findViewById(R.id.recent_list_item_name);
            msg = (TextView) itemView.findViewById(R.id.recent_list_item_msg);
            time = (TextView) itemView.findViewById(R.id.recent_list_item_time);
        }
    }

    private class ViewHolderAddFriend extends Holder {
        ImageView icon;
        TextView name, msg, addBtn, refuseBtn;

        public ViewHolderAddFriend(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.recent_list_item_icon);
            name = (TextView) itemView.findViewById(R.id.recent_list_item_name);
            msg = (TextView) itemView.findViewById(R.id.recent_list_item_msg);
            addBtn = (TextView) itemView.findViewById(R.id.recent_list_add_btn);
            refuseBtn = (TextView) itemView.findViewById(R.id.recent_list_refuse_btn);

        }
    }

    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ChatListBean bean = (ChatListBean) v.getTag();
            switch (bean.type) {
                case ChatListBean.CHAT_TYPE_SINGLE:
                    // 单聊
                    SessionActivity2.startActivity(v.getContext(), SessionActivity2.SESSION_TYPE_PRIVATE, bean.id, bean.name);
                    break;
                case ChatListBean.CHAT_TYPE_MORE:
                    // 群聊
                    SessionActivity2.startActivity(v.getContext(), SessionActivity2.SESSION_TYPE_GROUP, bean.id, bean.name);
                    break;
            }
        }
    };

    private View.OnClickListener addBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ChatListBean bean = (ChatListBean) v.getTag();
//            if (bean.type == ChatListBean.CHAT_TYPE_ADD_FRIEND && !bean.isAdded) {
//            Toast.makeText(mContext, "添加好友(" + bean.name + ")", Toast.LENGTH_SHORT).show();
            LogUtil.e("addBtnClickListener ----");

//            }
            if (agreeListener != null) {
                agreeListener.agreeAdd(bean);
            }

        }
    };

    private View.OnClickListener refuseBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ChatListBean bean = (ChatListBean) v.getTag();
//            if (bean.type == ChatListBean.CHAT_TYPE_ADD_FRIEND && !bean.isAdded) {
            //UIUtils.showToast("拒绝添加好友(" + bean.name + ")");
            LogUtil.e("refuseBtnClickListener ----");
//            }
            if (rejectListener != null) {
                rejectListener.rejectAdd(bean);
            }
        }
    };

    private class RefuseBtnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }

    private class DeleteBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
        //mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));
    }

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {

        if (holder instanceof RecentChatListAdapter2.ViewHolder) {
            //ViewGroup viewGroup = (ViewGroup) holder.itemView;
            return DisplayUtils.dip2px(mContext, 70);
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return mRecyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return mRecyclerView.findChildViewUnder(x, y);
    }

    public interface AgreeListener{
        void agreeAdd(ChatListBean chatListBean);
    }
    public void  setAgreeListener(AgreeListener listener) {
        this.agreeListener = listener;
    }

    public interface RejectListener {
        void rejectAdd(ChatListBean bean);
    }

    public void setRejectListener(RejectListener listener) {
        this.rejectListener = listener;
    }


}
