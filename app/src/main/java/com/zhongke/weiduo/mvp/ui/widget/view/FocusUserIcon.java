package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.PhotoLoaderUtil;
import com.zhongke.weiduo.mvp.model.entity.JoinMember;


/**
 * Created by llj on 2017/8/29.
 */

public class FocusUserIcon extends FrameLayout {
    private ImageView icon;
    private TextView like;
    private TextView integral;


    /**
     * 参加人员信息对象
     */
    private JoinMember.ActUserListBean mPartInBean;

//    /** 被赞的总次数*/
//    private int likeNum = 0;
//    /** 总积分数*/
//    private int integralNum = 0;
    /**
     * 是否赞过
     */
    private boolean isLiked;
    /**
     * 是否选中
     */
    private boolean isSelect = false;

    public FocusUserIcon(@NonNull Context context) {
        super(context);
        init(context);
    }

    public FocusUserIcon(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FocusUserIcon(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        addView(View.inflate(context, R.layout.answer_user_lay, null));
        setBackgroundResource(R.drawable.user_light_empty);
        icon = (ImageView) findViewById(R.id.user_item_icon);
        like = (TextView) findViewById(R.id.user_item_like);
        integral = (TextView) findViewById(R.id.user_item_integral);
        mPartInBean = new JoinMember.ActUserListBean();

        like.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                doLike();
            }
        });
    }


    /**
     * 设置参与人员的信息
     *
     * @param partInBean
     */
    public void setPartInBean(JoinMember.ActUserListBean partInBean) {
        this.mPartInBean = partInBean;
        PhotoLoaderUtil.displayRoundImage(getContext(), icon, mPartInBean.getHeadImageUrl(), ContextCompat.getDrawable(getContext(), R.drawable.ic_default_user_circle));
//        this.like.setText(String.valueOf(this.mPartInBean.likeNum));
//        this.integral.setText(String.valueOf(this.mPartInBean.integralNum));
    }

    /**
     * 获取参与人员信息
     *
     * @return
     */
    public JoinMember.ActUserListBean getPartInBean() {
        return mPartInBean;
    }

//    /**
//     * 设置头像
//     * @param url
//     */
//    public void setHeaderIcon(String url){
//        GlideLoader.loadNetWorkResource(getContext(),url,icon,true);
//    }

//    /**
//     * 设置点赞次数
//     *
//     * @param num
//     */
//    public void setLikeNum(int num) {
//        this.mPartInBean.likeNum = num;
//        this.like.setText(String.valueOf(this.mPartInBean.likeNum));
//    }
//
//    public void setIntegralNum(int num) {
//        this.mPartInBean.integralNum = num;
//        this.integral.setText(String.valueOf(this.mPartInBean.integralNum));
//    }
//
//
//    /**
//     * 添加积分数量
//     *
//     * @param num
//     */
//    public void addIntegralNum(int num) {
//        this.mPartInBean.integralNum += num;
//        this.integral.setText(String.valueOf(this.mPartInBean.integralNum));
//    }
//
//    /**
//     * 减去积分数量
//     *
//     * @param num
//     */
//    public void minusIntegralNum(int num) {
//        this.mPartInBean.integralNum -= num;
////        // 不能有负数
////        if(this.integralNum < 0) this.integralNum = 0;
//        this.integral.setText(String.valueOf(this.mPartInBean.integralNum));
//    }
//
//    /**
//     * 点赞
//     */
//    private void doLike() {
//        LogUtil.i("llj", "点赞！！");
//        if (!isLiked) {
//            // 未被赞过
//            Drawable likedDrawable = ContextCompat.getDrawable(getContext(), R.drawable.like);
//            likedDrawable.setBounds(0, 0, likedDrawable.getMinimumWidth(), likedDrawable.getMinimumHeight());
//            like.setCompoundDrawables(likedDrawable, null, null, null);
//            like.setTextColor(Color.RED);
//            like.setText(String.valueOf(++this.mPartInBean.likeNum));
//            integral.setBackgroundResource(R.drawable.user_icon_rect_bottom_select_bg);
//            integral.setText(String.valueOf(this.mPartInBean.integralNum));
//        }
//        isLiked = true;
//    }

    public void setSelect(boolean select) {
        if (select) {
            if (isSelect) return;
            setBackgroundResource(R.drawable.anim_answer_user);
            AnimationDrawable animationDrawable = (AnimationDrawable) getBackground();
            animationDrawable.start();
        } else {
            if (!isSelect) return;
            setBackgroundResource(R.drawable.user_light_empty);
        }
        isSelect = select;
    }

}
