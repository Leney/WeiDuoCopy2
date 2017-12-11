package com.zhongke.weiduo.mvp.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.control.UserInfoManager;
import com.zhongke.weiduo.mvp.ui.activity.MyPlanListActivity;
import com.zhongke.weiduo.mvp.ui.activity.MySchemeListActivity;
import com.zhongke.weiduo.mvp.ui.activity.MyTargetListActivity;
import com.zhongke.weiduo.mvp.ui.activity.ScheduleActivity;
import com.zhongke.weiduo.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${xinGen} on 2017/9/22.
 * <p>
 * 通用Module的布局控件，活动，规划，目标，计划等等。
 */

public class ModuleMessageLayout extends LinearLayout implements UserInfoManager.OnUserInfoChangeListener {
    private List<ModuleMessageView> moduleMessageViewList;
    private Context context;

    public ModuleMessageLayout(Context context) {
        super(context);
        initConfig(context);

        addChildView();
    }

    public ModuleMessageLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initConfig(context);
        addChildView();
    }

    private void initConfig(Context context) {
        this.context = context;
        this.setOrientation(LinearLayout.VERTICAL);
    }

    private void addChildView() {
        this.moduleMessageViewList = new ArrayList<>();
        int margin = DisplayUtils.dip2px(context, 11);
        for (int i = 0; i < 4; ++i) {
            ModuleMessageView view = new ModuleMessageView(this.context);
            ModuleMessageView.ModuleMessage moduleMessage = new ModuleMessageView.ModuleMessage();
            LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.START;
            switch (i) {
                case 0:
                    moduleMessage.moduleName = ModuleMessageView.ModuleMessage.MODEL_TARGET;
                    // 设置当前相关是数字值
                    moduleMessage.messageSize = UserInfoManager.getInstance().getTargetNum();
                    break;
                case 1:
                    layoutParams.topMargin = margin;
                    moduleMessage.moduleName = ModuleMessageView.ModuleMessage.MODEL_PROGRAM;
                    // 设置当前相关是数字值
                    moduleMessage.messageSize = UserInfoManager.getInstance().getSchemeNum();
                    break;
                case 2:
                    layoutParams.topMargin = margin;
                    moduleMessage.moduleName = ModuleMessageView.ModuleMessage.MODEL_PLAN;
                    // 设置当前相关是数字值
                    moduleMessage.messageSize = UserInfoManager.getInstance().getPlanNum();
                    break;
                case 3:
                    layoutParams.topMargin = margin;
                    moduleMessage.moduleName = ModuleMessageView.ModuleMessage.MODEL_ACTIVITY;
                    // 设置当前相关是数字值
                    moduleMessage.messageSize = UserInfoManager.getInstance().getActiveNum();
                    break;
            }
            view.addModule(moduleMessage);
            view.setTag(moduleMessage);
            this.addView(view, layoutParams);
            this.moduleMessageViewList.add(view);
            view.setOnClickListener(itemOnClickListener);
        }
    }

    /**
     * 对外提供API,结合ParabolaImageView 动画使用
     *
     * @param module      在ModuleMessage 的各种module
     * @param messageSize 信息数量
     */
    public void updateMessageWithAnimator(View startView, String module, int messageSize) {
        for (ModuleMessageView view : moduleMessageViewList) {
            ModuleMessageView.ModuleMessage message = (ModuleMessageView.ModuleMessage) view.getTag();
            if (message.moduleName.equals(module)) {
                message.messageSize = messageSize;
                ParabolaImageView parabolaImageView = new ParabolaImageView(this.context);
                parabolaImageView.setImageResource(R.mipmap.ic_launcher);
                parabolaImageView.loadParabolaAnimator(startView, view, (() -> view.addModuleNotify(message)));
                break;
            }
        }
    }

    /**
     * 对外提供API
     *
     * @param module      在ModuleMessage 的各种module
     * @param messageSize 信息数量
     */
    public void updateMessage(String module, int messageSize) {
        for (ModuleMessageView view : moduleMessageViewList) {
            ModuleMessageView.ModuleMessage message = (ModuleMessageView.ModuleMessage) view.getTag();
            if (message.moduleName.equals(module)) {
                message.messageSize = messageSize;
                view.addModuleNotify(message);
                break;
            }
        }
    }

    /**
     * item的点击事件
     */
    private OnClickListener itemOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            ModuleMessageView.ModuleMessage message = (ModuleMessageView.ModuleMessage) v.getTag();
            switch (message.moduleName) {
                case ModuleMessageView.ModuleMessage.MODEL_TARGET:
                    // 目标
                    MyTargetListActivity.startActivity(v.getContext());
                    break;
                case ModuleMessageView.ModuleMessage.MODEL_PROGRAM:
                    // 规划
                    MySchemeListActivity.startActivity(v.getContext());
                    break;
                case ModuleMessageView.ModuleMessage.MODEL_PLAN:
                    // 计划
                    MyPlanListActivity.startActivity(v.getContext());
                    break;
                case ModuleMessageView.ModuleMessage.MODEL_ACTIVITY:
                    // 活动
                    ScheduleActivity.startActivity(v.getContext());
                    break;
            }
        }
    };

    @Override
    public void onUserInfoChange() {
        // 用户信息发生改变
        for (ModuleMessageView view : moduleMessageViewList) {
            ModuleMessageView.ModuleMessage message = (ModuleMessageView.ModuleMessage) view.getTag();
            switch (message.moduleName) {
                case ModuleMessageView.ModuleMessage.MODEL_TARGET:
                    // 目标
                    message.messageSize = UserInfoManager.getInstance().getTargetNum();
                    break;
                case ModuleMessageView.ModuleMessage.MODEL_PROGRAM:
                    // 规划
                    message.messageSize = UserInfoManager.getInstance().getSchemeNum();
                    break;
                case ModuleMessageView.ModuleMessage.MODEL_PLAN:
                    // 计划
                    message.messageSize = UserInfoManager.getInstance().getPlanNum();
                    break;
                case ModuleMessageView.ModuleMessage.MODEL_ACTIVITY:
                    // 活动
                    message.messageSize = UserInfoManager.getInstance().getActiveNum();
                    break;
            }
        }
        this.invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i("llj", "注册监听用户信息改变的监听！！！");
        UserInfoManager.getInstance().registerOnUserInfoChangeListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i("llj", "取消注册监听用户信息改变的监听！！！");
        UserInfoManager.getInstance().unregisterOnUserInfoChangeListener(this);
    }
}
