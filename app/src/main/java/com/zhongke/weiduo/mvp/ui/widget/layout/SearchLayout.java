package com.zhongke.weiduo.mvp.ui.widget.layout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.util.DisplayUtils;
import com.zhongke.weiduo.util.ToastUtil;
import com.zhongke.weiduo.util.ViewIdUtils;

/**
 * Created by ${xinGen} on 2017/11/17.
 * <p>
 * 定义一个搜索框的布局
 */

public class SearchLayout extends RelativeLayout {
    private SearchResponseListener responseListener;
    private final String TAG = SearchLayout.class.getSimpleName();
    private EditText search_edit;
    private int phoneWidth;
    private InputMethodManager manager;
    /**
     * 当前输入的内容
     */
    private String currentContent;
    private int padding;
    private TextView back_tv;
    private ImageView delete_iv;

    public SearchLayout(Context context) {
        super(context);
        initConfig();
        addChildView();
    }

    public SearchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initConfig();
        addChildView();
    }

    /**
     * 初始化配置
     */
    private void initConfig() {
        this.phoneWidth = getResources().getDisplayMetrics().widthPixels;
        this.manager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        this.padding = DisplayUtils.dip2px(getContext().getApplicationContext(), 10);
        this.setBackgroundResource(R.color.main_color);
    }

    /**
     * 添加子类View
     */
    private void addChildView() {
        back_tv = createBackTextView();
        this.addView(back_tv);
        RelativeLayout childLayout = createChildLayout();
        this.addView(childLayout);
        this.delete_iv = createImageView();
        childLayout.addView(this.delete_iv);
        this.search_edit = createEditText();
        childLayout.addView(this.search_edit);
    }

    /**
     * 创建子类Layout
     *
     * @return
     */
    private RelativeLayout createChildLayout() {
        RelativeLayout childLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams childLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        childLayoutParams.addRule(RelativeLayout.LEFT_OF, back_id);
        childLayout.setFocusableInTouchMode(true);
        childLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        childLayoutParams.rightMargin=padding;
        childLayout.setLayoutParams(childLayoutParams);
        childLayout.setBackgroundResource(R.drawable.shape_search_content_editext);
        childLayout.setPadding(padding-5, 0, padding, 0);
        return childLayout;
    }
    private int back_id;
    /**
     * 设置返回控件
     *
     * @return
     */
    private TextView createBackTextView() {
        TextView textView = new TextView(getContext());
        textView.setText("返回");
        textView.setTextSize(14);
        RelativeLayout.LayoutParams back_LayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        back_LayoutParams.rightMargin = padding;
        back_LayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        back_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        textView.setLayoutParams(back_LayoutParams);
        this.back_id = ViewIdUtils.getViewId();
        textView.setId(this.back_id);
        textView.setOnClickListener(view -> {
            closeIfInput();
            if (responseListener != null) {
                responseListener.back();
            }
        });
        return textView;
    }

    /**
     * 删除的ImageView
     *
     * @return
     */
    private ImageView createImageView() {
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.delete_2);
        imageView.setId(ViewIdUtils.getViewId());
        imageView.setOnClickListener(view ->
                search_edit.setText("")
        );
        return imageView;
    }

    /**
     * 创建搜索框
     * 1. 设置单行显示
     * 2. 设置
     *
     * @return
     */
    private EditText createEditText() {
        EditText editText = new EditText(getContext());
        editText.setHint("请输入");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.LEFT_OF, delete_iv.getId());
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        editText.setTextSize(12);
        editText.setLayoutParams(layoutParams);
        editText.setSingleLine(true);
        editText.setBackgroundDrawable(null);
        editText.setGravity(Gravity.CENTER_VERTICAL);
        //设置搜索键
        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        //设置软键盘的动作监听
        editText.setOnEditorActionListener((textView, actionId, event) -> {
            //搜索键的动作触发
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String inputContent = editText.getText().toString().trim();
                setCurrentContent(inputContent);
                triggerSearch(inputContent);
                return true;
            }
            return false;
        });
        return editText;
    }

    /**
     * 触发搜索
     */
    private void triggerSearch(String content) {
        if (TextUtils.isEmpty(content)) {
            ToastUtil.show(getContext().getApplicationContext(), "搜索内容不能为空", Toast.LENGTH_SHORT);
        } else {
            closeIfInput();
            if (responseListener != null) {
                responseListener.searchContent(content);
            }
        }
    }

    /**
     * InputMethodManager类用于控制输入法
     * <p>
     * isAcceptingText():当前view在使用输入法编辑内容，返回true
     * <p>
     * 其toggleSoftInput（）：用于切换输入法窗口显示。若是已经显示输入法窗口，将会被隐藏。反之，则显示输入法窗口。
     * 参数1：showFlags提供其他操作标志。可以为0或者SHOW_IMPLICIT，SHOW_FORCED
     * <p>
     * 参数2：hideFlags提供其他操作标志。可以为0或者HIDE_IMPLICIT_ONLY，HIDE_NOT_ALWAYS。
     * InputMethodManager.toggleSoftInput( InputMethodManager.HIDE_IMPLICIT_ONLY,0);
     * <p>
     * hideSoftInputFromWindow():请求隐藏输入法窗口从正在输入的window环境。
     * <p>
     * 参数1：发送请求的window 令牌。从View#getWindowToken() 获取
     * <p>
     * 参数2：提供其他操作。可以为0或者HIDE_IMPLICIT_ONLY。
     */
    public void closeIfInput() {
        if (manager != null) {
            if (manager.isAcceptingText()) {
                manager.hideSoftInputFromWindow(getRootView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 获取到的移动距离
     *
     * @return
     */
    private int getMoveDistance() {
        int distance = phoneWidth - getLeft();
        Log.i(TAG, "移动的距离是 " + distance);
        return distance;
    }

    /**
     * 一开始的时候，隐藏本身。
     */
    public void hideSelf() {
        this.setTranslationX(getMoveDistance());
    }

    /**
     * 出现动画
     */
    public void startAnimator() {
        Animator animator = createAnimator(this.getTranslationX(), 0);
        animator.start();
    }

    /**
     * 返回动画
     */
    public void backAnimator() {
        Animator animator = createAnimator(this.getTranslationX(), getMoveDistance());
        animator.start();
    }

    public void setResponseListener(SearchResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public String getCurrentContent() {
        return currentContent;
    }

    private void setCurrentContent(String currentContent) {
        this.currentContent = currentContent;
    }

    /**
     * 创建位移动画
     *
     * @param startY
     * @param endY
     * @return
     */
    private Animator createAnimator(float startY, float endY) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "translationX", startY, endY);
        objectAnimator.setDuration(800);
        return objectAnimator;
    }

    /**
     * 搜索响应接口
     */
    public interface SearchResponseListener {
        /**
         * 点击了软键盘的搜索键
         */
        void searchContent(String content);

        /**
         * 点击了return ，返回
         */
        void back();
    }
}
