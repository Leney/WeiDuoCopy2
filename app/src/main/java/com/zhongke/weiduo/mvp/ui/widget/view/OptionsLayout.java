package com.zhongke.weiduo.mvp.ui.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.entity.Paper;
import com.zhongke.weiduo.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择题选项控件
 * Created by llj on 2017/8/26.
 */

public class OptionsLayout extends LinearLayout {

    /**
     * 具体选项的文本视图
     */
    private TextView[] optionTexts;

    private Context mContext;

//    /**
//     * 用户选择的position,注意：不是最终确定答案
//     */
//    private int selectPosition = -1;
    /**
     * 标识是否已经点击确定了答案
     */
    private boolean isSure = false;

    /**
     * 标识答案选项是否可以点击(还未抢答，只是给用户看答案)
     */
    private boolean isCanClick = false;

    private OnSelectResultListener mListener;

    /**
     * 答案选项集合
     */
    private List<Paper.QutionBean.AnswerBean> optionList;

    /**
     * 当前问题的对象
     */
    private Paper.QutionBean curQutionBean;

    public OptionsLayout(Context context) {
        super(context);
        init(context);
    }

    public OptionsLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OptionsLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        this.optionList = new ArrayList<>();
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
     * @param qutionBean 问题对象
     */
    public void setOptions(Paper.QutionBean qutionBean) {
        if (qutionBean == null) return;
        this.curQutionBean = qutionBean;
        List<Paper.QutionBean.AnswerBean> options = qutionBean.getAnswer();
        if (options == null) return;
        removeAllViews();
        this.isSure = false;
        this.isCanClick = false;
        this.optionList.clear();
        this.optionList.addAll(options);
        double length = options.size();

        // 创建局里的子布局,具体选项部分
        // 子选项的布局参数
        LayoutParams optionParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        optionParams.bottomMargin = DisplayUtils.dip2px(mContext, 10);
        int horPadding = DisplayUtils.dip2px(mContext, 15);
        int verPadding = DisplayUtils.dip2px(mContext, 14);

        optionTexts = new TextView[(int) length];

        // 循环设置每个选项的显示视图
        for (int i = 0; i < length; i++) {
            // 单个选项
            Paper.QutionBean.AnswerBean answerBean = optionList.get(i);
            TextView optionText = new TextView(mContext);
            optionText.setTextColor(Color.parseColor("#333333"));
            optionText.setTextSize(14);
            optionText.setText(answerBean.getTitle());
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

    public Paper.QutionBean getCurQutionBean(){
        return curQutionBean;
    }

//    /**
//     * 处理item点击
//     *
//     * @param position
//     */
//    private void doItemClick(int position) {
//        LogUtil.i("llj", "position------>>" + position + "\nselectPosition------>>" + selectPosition + "\nisCanClick------>>" + isCanClick + "\nisSure------>>" + isSure);
//        if (position == selectPosition || isSure || !isCanClick) return;
//        if (selectPosition >= 0) {
//            // 还原之前未选中的状态
//            optionTexts[selectPosition].setBackgroundResource(R.drawable.option_nothing_bg);
//        }
//        // 设置新的选择项的背景
//        optionTexts[position].setBackgroundResource(R.drawable.option_select_bg);
//        selectPosition = position;
//    }

//    /**
//     * 设置选择position
//     *
//     * @param position
//     */
//    public void setSelectPosition(int position) {
//        this.selectPosition = position;
//    }

//    /**
//     * 获取正确答案的选项position
//     * @return
//     */
//    public int getRightPosition(){
//        return this.rightPosition;
//    }


    /**
     * 确定答案
     */
    public void sure(int answerId) {
        if (isSure) return;
        // 设置正确选项的背景
        int length = optionList.size();
        for (int i = 0; i < length; i++) {
            Paper.QutionBean.AnswerBean bean = optionList.get(i);
            if (bean.getIsRight() == 1) {
                // 不管答对与否，都要设置正确答案的背景
                optionTexts[i].setBackgroundResource(R.drawable.option_right_bg);
            }
            if (answerId == bean.getId()) {
                if (bean.getIsRight() != 1) {
                    // 答案错误
                    optionTexts[i].setBackgroundResource(R.drawable.option_wrong_bg);
                }
                if (mListener != null) {
                    // 回调结果
                    mListener.onResult(bean.getIsRight() == 1);
                }
            }
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
