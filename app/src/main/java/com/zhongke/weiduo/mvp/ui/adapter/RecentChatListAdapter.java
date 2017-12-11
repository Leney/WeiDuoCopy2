package com.zhongke.weiduo.mvp.ui.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.zhongke.weiduo.app.utils.TimeUtils;
import com.zhongke.weiduo.mvp.base.BaseRecyclerAdapter;
import com.zhongke.weiduo.mvp.model.entity.ChatListBean;
import com.zhongke.weiduo.mvp.ui.activity.PersonalDetailsActivity;
import com.zhongke.weiduo.mvp.ui.activity.SessionActivity2;
import com.zhongke.weiduo.mvp.ui.widget.view.SlidingButtonView;

/**
 * 最近聊天列表Adapter
 * Created by llj on 2017/10/16.
 */

public class RecentChatListAdapter extends BaseRecyclerAdapter<ChatListBean> implements SlidingButtonView.IonSlidingButtonListener {

    private static final int ADD_FRIEND_VIEW_TYPE = 3;
    private static final int CHAT_MSG_VIEW_TYPE = 4;
    private Context mContext;

    private AgreeListener agreeListener;
    private RejectListener rejectListener;

    private SlidingButtonView mMenu = null;
    private IonSlidingViewClickListener mIDeleteBtnClickListener;
    private Activity context;
    private ViewHolder holder2;

    public RecentChatListAdapter(Context context) {
        this.context = (Activity) context;
        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        if (viewType == ADD_FRIEND_VIEW_TYPE) {
            return new ViewHolderAddFriend(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recent_add_friend_item, parent, false));
        } else {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recent_list_item2, parent, false));
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
        return type;
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, ChatListBean data) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.itemLay.getLayoutParams().width = context.getWindowManager().getDefaultDisplay().getWidth();
            PhotoLoaderUtil.display(mContext, holder.icon, data.icon, R.mipmap.default_user_icon);
            holder.num.setText(String.valueOf(data.unreadMessageNum));
            holder.num.setVisibility(data.unreadMessageNum > 0 ? View.VISIBLE : View.GONE);
            holder.name.setText(data.name);
            holder.delete.setOnClickListener(new DeleteBtnListener() {
                @Override
                public void onClick(View v) {
                    mIDeleteBtnClickListener.onDeleteBtnCilck(v, RealPosition);
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
            holder2 = holder;
        } else if (viewHolder instanceof ViewHolderAddFriend) {
            ViewHolderAddFriend holderAddFriend = (ViewHolderAddFriend) viewHolder;
            //holderAddFriend.icon.setTag(data);
            PhotoLoaderUtil.display(mContext, holderAddFriend.icon, data.icon, R.mipmap.default_user_icon);

            holderAddFriend.name.setText(data.name);
            holderAddFriend.msg.setText(data.newestMsg);
            if (data.addState == ChatListBean.NEW_ADD_FRIEND_STATE_PASSED) {
                // 已添加
                holderAddFriend.handleBtn.setVisibility(View.VISIBLE);
                holderAddFriend.handleBtn.setText("已添加");
                holderAddFriend.addBtn.setVisibility(View.GONE);
                holderAddFriend.refuseBtn.setVisibility(View.GONE);
            } else if (data.addState == ChatListBean.NEW_ADD_FRIEND_STATE_ALREADY_SEND) {
                // 对方未处理
                holderAddFriend.handleBtn.setVisibility(View.VISIBLE);
                holderAddFriend.handleBtn.setText("已申请");
                holderAddFriend.addBtn.setVisibility(View.GONE);
                holderAddFriend.refuseBtn.setVisibility(View.GONE);
            } else if (data.addState == ChatListBean.NEW_ADD_FRIEND_STATE_UN_DO) {
                // 自己未处理
                holderAddFriend.addBtn.setBackgroundColor(ContextCompat.getColor(mContext, R.color.main_color));
                holderAddFriend.addBtn.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                holderAddFriend.addBtn.setText("同意");
                holderAddFriend.addBtn.setVisibility(View.VISIBLE);
                holderAddFriend.refuseBtn.setVisibility(View.VISIBLE);
                holderAddFriend.handleBtn.setVisibility(View.GONE);
            } else if (data.addState == ChatListBean.NEW_ADD_FRIEND_STATE_NO_PASSED) {
                // 已拒绝
                holderAddFriend.handleBtn.setVisibility(View.VISIBLE);
                holderAddFriend.handleBtn.setText("已拒绝");
                holderAddFriend.addBtn.setVisibility(View.GONE);
                holderAddFriend.refuseBtn.setVisibility(View.GONE);
            }
            holderAddFriend.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonalDetailsActivity.startActivity(context, data.id, data.name, false, true);
                }
            });
            holderAddFriend.addBtn.setTag(data);
            holderAddFriend.refuseBtn.setTag(data);
            holderAddFriend.addBtn.setOnClickListener(addBtnClickListener);
            holderAddFriend.refuseBtn.setOnClickListener(refuseBtnClickListener);
        }
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

    public class ViewHolder extends BaseRecyclerAdapter.Holder {
        ImageView icon;
        TextView num, name, msg, time;
        RelativeLayout itemLay, delete;

        public ViewHolder(View itemView) {
            super(itemView);

            itemLay = (RelativeLayout) itemView.findViewById(R.id.recent_list_item_lay);
            itemLay.setOnClickListener(itemClickListener);
            delete = (RelativeLayout) itemView.findViewById(R.id.tv_delete);
            icon = (ImageView) itemView.findViewById(R.id.recent_list_item_icon);
            num = (TextView) itemView.findViewById(R.id.recent_list_item_num);
            name = (TextView) itemView.findViewById(R.id.recent_list_item_name);
            msg = (TextView) itemView.findViewById(R.id.recent_list_item_msg);
            time = (TextView) itemView.findViewById(R.id.recent_list_item_time);
            ((SlidingButtonView) itemView).setSlidingButtonListener(RecentChatListAdapter.this);
        }
    }

    private class ViewHolderAddFriend extends BaseRecyclerAdapter.Holder {
        ImageView icon;
        TextView name, msg, addBtn, refuseBtn, handleBtn;

        public ViewHolderAddFriend(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.recent_list_item_icon);
            name = (TextView) itemView.findViewById(R.id.recent_list_item_name);
            msg = (TextView) itemView.findViewById(R.id.recent_list_item_msg);
            addBtn = (TextView) itemView.findViewById(R.id.recent_list_add_btn);
            refuseBtn = (TextView) itemView.findViewById(R.id.recent_list_refuse_btn);
            handleBtn = (TextView) itemView.findViewById(R.id.recent_list_handle_btn);

        }
    }

    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //判断是否有删除菜单打开
            if (menuIsOpen()) {
                closeMenu();//关闭菜单
            } else {
                int n = holder2.getLayoutPosition();
                mIDeleteBtnClickListener.onItemClick(v, n);

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
        }
    };

    private View.OnClickListener addBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ChatListBean bean = (ChatListBean) v.getTag();

            if (agreeListener != null) {
                agreeListener.agreeAdd(bean);
            }

        }
    };

    private View.OnClickListener refuseBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ChatListBean bean = (ChatListBean) v.getTag();
            LogUtil.e("refuseBtnClickListener ----");
            if (rejectListener != null) {
                rejectListener.rejectAdd(bean);
            }
        }
    };

    private class DeleteBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }

    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);

        void onDeleteBtnCilck(View view, int position);
    }

    public interface AgreeListener {
        void agreeAdd(ChatListBean chatListBean);
    }

    public void setAgreeListener(AgreeListener listener) {
        this.agreeListener = listener;
    }

    public interface RejectListener {
        void rejectAdd(ChatListBean bean);
    }

    public void setRejectListener(RejectListener listener) {
        this.rejectListener = listener;
    }
}
