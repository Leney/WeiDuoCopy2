package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.JoinMember;
import com.zhongke.weiduo.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 参与抢答人员布局
 * Created by llj on 2017/8/29.
 */

public class PartInLayout extends LinearLayout {

    /**
     * 参与用户的视图数组
     */
    private List<FocusUserIcon> itemList = new ArrayList<>();
    /**
     * 当前参与抢答的人员position
     */
    private int curSelect = -1;

    /**
     * 每个子item的布局参数
     */
    private LayoutParams itemParams;

    /**
     * 左边子item的显示区域
     */
    private LinearLayout leftItemLay;
    /**
     * 右边点击收缩按钮
     */
    private View rightBtn;

    public PartInLayout(Context context) {
        super(context);
        init(context);
    }

    public PartInLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PartInLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);

        itemParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);

        leftItemLay = new LinearLayout(context);
        leftItemLay.setOrientation(HORIZONTAL);
        leftItemLay.setBackgroundResource(R.drawable.part_in_bg);
        Animation animation_out = AnimationUtils.loadAnimation(context, R.anim.anim_part_in_out);
        Animation animation_in = AnimationUtils.loadAnimation(context, R.anim.anim_part_in_in);
//        leftItemLay.setAnimation(animation);
        addView(leftItemLay, itemParams);

        rightBtn = new View(context);
        rightBtn.setBackgroundResource(R.drawable.selector_pary_in_right_bg);
        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                    leftItemLay.startAnimation(animation_in);
                    leftItemLay.setVisibility(VISIBLE);
                } else {
                    leftItemLay.startAnimation(animation_out);
                    leftItemLay.setVisibility(INVISIBLE);
                }
                rightBtn.setSelected(!view.isSelected());
            }
        });
        addView(rightBtn, new LayoutParams(DisplayUtils.dip2px(context, 37), LayoutParams.MATCH_PARENT));
    }

    /**
     * 添加一个视图
     *
     * @param bean
     */
    public void addItem(JoinMember.ActUserListBean bean) {
        FocusUserIcon item = new FocusUserIcon(getContext());
        item.setPartInBean(bean);
        itemList.add(item);
        leftItemLay.addView(item, itemParams);
    }

    /**
     * 一次性添加视图所有视图
     *
     * @param beanList
     */
    public void addViews(List<JoinMember.ActUserListBean> beanList) {
        // 移除之前所有视图
        removeAllViews();
        int length = beanList.size();
        for (int i = 0; i < length; i++) {
            addItem(beanList.get(i));
        }
    }

    /**
     * 获取当前添加的子item的数量
     *
     * @return
     */
    public int getCurItemSize() {
        return itemList.size();
    }


    /**
     * 设置当前正在回答的用户
     *
     * @param position
     */
    public void setAnswerPosition(int position) {
        if (position >= itemList.size()) return;
        // 将上次正在答题的item设置为未选答题状态
        if (curSelect >= 0) itemList.get(curSelect).setSelect(false);
        curSelect = position;
        // 小于0就只是将上一次选择的答题状态改变成正常的就好了
        if (position < 0) return;
        // 设置此次的为答题状态
        itemList.get(position).setSelect(true);
    }


    /**
     * 通过userId 设置选中的用户
     * @param userId
     */
    public void setAnswerUserId(int userId) {
        int position = -1;
        int length = itemList.size();
        for (int i = 0; i < length; i++) {
            FocusUserIcon focusUserIcon = itemList.get(i);
            if (focusUserIcon.getPartInBean().getUserId() == userId) {
                position = i;
                break;
            }
        }
        setAnswerPosition(position);
    }

}
