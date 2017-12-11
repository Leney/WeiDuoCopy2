package com.zhongke.weiduo.mvp.ui.widget.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.SizeUtils;

/**
 * Created by ${tanlei} on 2017/9/18.
 * 联系人弹出框
 */

public class ContactPopWin extends PopupWindow implements PopupWindow.OnDismissListener, View.OnClickListener {
    private View view;
    private Activity context;
    private TextView tvAddEquipment, tvAddFriends, tvCreateGroup;
    private OnPopWinClickListeners listeners;
    /**
     * 点击了3个item的常量标识
     */
    public static final int ADD_FRIENDS = 1;
    public static final int CREATE_GROUP = 2;
    public static final int ADD_EQUIPMENT = 3;

    public ContactPopWin(Activity context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.contact_pop, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        tvAddFriends = (TextView) view.findViewById(R.id.add_friends);
        tvAddFriends.setOnClickListener(this);
        tvCreateGroup = (TextView) view.findViewById(R.id.create_group);
        tvCreateGroup.setOnClickListener(this);
        tvAddEquipment = (TextView) view.findViewById(R.id.add_equipment);
        tvAddEquipment.setOnClickListener(this);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(SizeUtils.dp2px(context, 140));
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        setOnDismissListener(this);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);
    }

    public OnPopWinClickListeners getListeners() {
        return listeners;
    }

    public void setListeners(OnPopWinClickListeners listeners) {
        this.listeners = listeners;
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 0);
            setBackgroundAlpha(0.80f);
        } else {
            this.dismiss();
        }
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().setAttributes(lp);
    }

    @Override
    public void onDismiss() {
        setBackgroundAlpha(1.0f);
    }

    @Override
    public void onClick(View v) {
        if (getListeners() == null) {
            return;
        }
        if (isShowing()) {
            dismiss();
        }
        switch (v.getId()) {
            case R.id.add_friends:
                listeners.clickPopItem(ADD_FRIENDS);
                break;
            case R.id.add_equipment:
                listeners.clickPopItem(ADD_EQUIPMENT);
                break;
            case R.id.create_group:
                listeners.clickPopItem(CREATE_GROUP);
                break;
            default:
                break;
        }
    }

    //回调方法
    public interface OnPopWinClickListeners {
        void clickPopItem(int i);
    }
}
