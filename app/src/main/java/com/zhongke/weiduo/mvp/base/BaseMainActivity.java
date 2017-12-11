package com.zhongke.weiduo.mvp.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.app.utils.UIUtils;
import com.zhongke.weiduo.mvp.ui.widget.CustomCircleDialog;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Karma on 2017/6/27.
 * 类描述：BaseMainActivity
 */

public abstract class BaseMainActivity<V, T extends BaseMainPresenter<V>> extends AppCompatActivity {
    private static final String TAG = "BaseMainActivity";

    @Bind(R.id.re_left)
    protected RelativeLayout mRelativeLayout;
    @Bind(R.id.ivToolbarNavigation)
    protected ImageView mIvToolbarNavigation;
    @Bind(R.id.vToolbarDivision)
    protected View mVToolbarDivision;
    @Bind(R.id.tvToolbarTitle)
    protected TextView mTvToolbarTitle;
    @Bind(R.id.tvToolbarSubTitle)
    protected TextView mTvToolbarSubTitle;
    @Bind(R.id.llToolbarTitle)
    protected AutoLinearLayout mLlToolbarTitle;
    @Bind(R.id.title)
    protected TextView mTitle;
    @Bind(R.id.ibAddMenu)
    protected ImageButton mIbAddMenu;
    @Bind(R.id.tvToolbarAddFriend)
    protected TextView mTvToolbarAddFriend;
    @Bind(R.id.llToolbarAddFriend)
    protected AutoLinearLayout mLlToolbarAddFriend;
    @Bind(R.id.etSearchContent)
    protected EditText mEtSearchContent;
    @Bind(R.id.llToolbarSearch)
    protected AutoLinearLayout mLlToolbarSearch;
    @Bind(R.id.btnToolbarSend)
    protected Button mBtnToolbarSend;
    @Bind(R.id.ibToolbarMore)
    protected ImageButton mIbToolbarMore;
    @Bind(R.id.flToolbar)
    protected AutoFrameLayout mFlToolbar;
    @Bind(R.id.appBar)
    protected AppBarLayout mAppBar;

    protected T mPresenter;
    private CustomCircleDialog mDialogWaiting;
    protected ZkApplication hpApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }

        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);

        setupAppBarAndToolbar();

        //沉浸式状态栏
        StatusBarUtil.setColor(this, UIUtils.getColor(R.color.title_bg), 30);
        hpApplication = (ZkApplication) getApplication();
        hpApplication.addActivity(this);

        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    //在setContentView()调用之前调用，可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
    public void init() {
    }

    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    /**
     * 是否让Toolbar有返回按钮(默认可以，一般一个应用中除了主界面，其他界面都是可以有返回按钮的)
     */
    protected boolean isToolbarCanBack() {
        return true;
    }

    /**
     * 设置AppBar和Toolbar
     */
    private void setupAppBarAndToolbar() {
        //如果该应用运行在android 5.0以上设备，设置标题栏的z轴高度
        if (mAppBar != null && Build.VERSION.SDK_INT > 21) {
            mAppBar.setElevation(10.6f);
        }

        //如果界面中有使用toolbar，则使用toolbar替代actionbar
        //默认不是使用NoActionBar主题，所以如果需要使用Toolbar，需要自定义NoActionBar主题后，在AndroidManifest.xml中对指定Activity设置theme
//        if (mToolbar != null) {
//            setSupportActionBar(mToolbar);
//            if (isToolbarCanBack()) {
//                ActionBar actionBar = getSupportActionBar();
//                if (actionBar != null) {
//                    actionBar.setDisplayHomeAsUpEnabled(true);
//                }
//            }
//        }

        mVToolbarDivision.setVisibility(isToolbarCanBack() ? View.VISIBLE : View.GONE);
        mVToolbarDivision.setVisibility(isToolbarCanBack() ? View.GONE : View.GONE);
        mRelativeLayout.setOnClickListener(v -> onBackPressed());
        mLlToolbarTitle.setPadding(isToolbarCanBack() ? 0 : 40, 0, 0, 0);
    }

    /**
     * 显示等待提示框
     */
    public Dialog showWaitingDialog(String tip) {
        hideWaitingDialog();
        View view = View.inflate(this, R.layout.dialog_waiting, null);
        if (!TextUtils.isEmpty(tip))
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        mDialogWaiting = new CustomCircleDialog(this, view, R.style.MyDialog);
        mDialogWaiting.show();
        mDialogWaiting.setCancelable(false);
        return mDialogWaiting;
    }

    /**
     * 隐藏等待提示框
     */
    public void hideWaitingDialog() {
        if (mDialogWaiting != null) {
            mDialogWaiting.dismiss();
            mDialogWaiting = null;
        }
    }

    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void jumpToWebViewActivity(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", url);
        jumpToActivity(intent);
    }


    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /*------------------ toolbar的一些视图操作 ------------------*/
    public void setToolbarTitle(String title) {
        mTitle.setText(title);
    }

    public void setToolbarSubTitle(String subTitle) {
        mTvToolbarTitle.setText(subTitle);
        mTvToolbarTitle.setVisibility(subTitle.length() > 0 ? View.VISIBLE : View.GONE);
    }
}
