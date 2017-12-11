package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.util.DisplayUtils;

/**
 * 选择题选项控件
 * Created by llj on 2017/8/26.
 */

public class OptionsLayout2 extends LinearLayout {

    /**
     * 具体选项的文本视图
     */
    private TextView[] optionTexts;

    private Context mContext;

    /**
     * 正确答案的position
     */
    private int rightPosition = -1;

    /**
     * 用户选择的position,注意：不是最终确定答案
     */
    private int selectPosition = -1;

    /**
     * 标识是否已经点击确定了答案
     */
    private boolean isSure = false;

    /**
     * 标识答案选项是否可以点击(还未抢答，只是给用户看答案)
     */
    private boolean isCanClick = false;

    private OnSelectResultListener mListener;

    public OptionsLayout2(Context context) {
        super(context);
        init(context);
    }

    public OptionsLayout2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OptionsLayout2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setOrientation(VERTICAL);
    }

    /**
     * 设置选择结果回调监听
     *
     * @param listener
     */
    public void setOnSelectResultListener(OnSelectResultListener listener) {
        this.mListener = listener;
    }

    /**
     * 设置是否可点击选择
     *
     * @param isCanClick
     */
    public void setCanClick(boolean isCanClick) {
        this.isCanClick = isCanClick;
    }

    /**
     * 设置选项数据
     *
     * @param options       选项数组值
     * @param rightPosition 正确的选答案选项
     */
    public void setOptions(String[] options, int rightPosition) {
        if (options == null || rightPosition < 0 || rightPosition >= options.length) return;
        removeAllViews();
        this.rightPosition = rightPosition;
        this.selectPosition = -1;
        this.isSure = false;
        this.isCanClick = false;
        double length = options.length;

        // 创建局里的子布局,具体选项部分
        // 子选项的布局参数
        LayoutParams optionParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        optionParams.bottomMargin = DisplayUtils.dip2px(mContext, 10);
        int horPadding = DisplayUtils.dip2px(mContext, 15);
        int verPadding = DisplayUtils.dip2px(mContext, 14);

        optionTexts = new TextView[(int) length];

        for (int i = 0; i < length; i++) {
            TextView optionText = new TextView(mContext);
            optionText.setTextColor(Color.parseColor("#333333"));
            optionText.setTextSize(14);
            optionText.setText(options[i]);
            optionText.setGravity(Gravity.LEFT);
            optionText.setPadding(horPadding, verPadding, horPadding, verPadding);
            optionText.setBackgroundResource(R.drawable.option_nothing_bg);
            optionTexts[i] = optionText;
            addView(optionTexts[i], optionParams);

            optionText.setTag(i);
//            optionText.setOnClickListener(view ->
//                    doItemClick((Integer) view.getTag())
//            );

        }
    }

    /**
     * 处理item点击
     *
     * @param position
     */
    private void doItemClick(int position) {
        LogUtil.i("llj", "position------>>" + position + "\nselectPosition------>>" + selectPosition + "\nisCanClick------>>" + isCanClick + "\nisSure------>>" + isSure);
        if (position == selectPosition || isSure || !isCanClick) return;
        if (selectPosition >= 0) {
            // 还原之前未选中的状态
            optionTexts[selectPosition].setBackgroundResource(R.drawable.option_nothing_bg);
        }
        // 设置新的选择项的背景
        optionTexts[position].setBackgroundResource(R.drawable.option_select_bg);
        selectPosition = position;
    }

    /**
     * 设置选择position
     * @param position
     */
    public void setSelectPosition(int position){
        this.selectPosition = position;
    }

    /**
     * 获取正确答案的选项position
     * @return
     */
    public int getRightPosition(){
        return this.rightPosition;
    }


    /**
     * 确定答案
     */
    public void sure() {
        LogUtil.i("llj", "rightPosition------->>>" + rightPosition);
        LogUtil.i("llj", "selectPosition------->>>" + selectPosition);
        if (rightPosition < 0 || isSure) return;
        if (selectPosition < 0) {
            return;
        }
        if (rightPosition < 0 || selectPosition < 0) return;
        // 设置正确选项的背景
        optionTexts[rightPosition].setBackgroundResource(R.drawable.option_right_bg);
        if (rightPosition == selectPosition) {
            // 回答正确
            if (mListener == null) return;
            mListener.onResult(true);
        } else {
            // 回答错误
            if (mListener == null) return;
            optionTexts[selectPosition].setBackgroundResource(R.drawable.option_wrong_bg);
            mListener.onResult(false);
        }
        isSure = true;
    }

    public interface OnSelectResultListener {
        /**
         * 选择结果回调
         *
         * @param isRight 是否正确 true=正确，false=错误
         */
        void onResult(boolean isRight);
    }

}
