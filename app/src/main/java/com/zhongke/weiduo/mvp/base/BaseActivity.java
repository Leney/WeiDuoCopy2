package com.zhongke.weiduo.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;
import com.zhongke.weiduo.mvp.ui.widget.BaseTitleView;
import com.zhongke.weiduo.util.ActivityUtils;
import com.zhongke.weiduo.util.Tools;

import javax.inject.Inject;


/**
 * Created by llj on 2017/6/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    protected RelativeLayout loadingLay;
    protected RelativeLayout noDataLay;
    protected RelativeLayout errorLay;
    protected RelativeLayout centerLay;
    protected BaseTitleView baseTitle;
    @Inject
    protected DataManager mDataManager;

    /** 是否设置状态栏颜色*/
    protected boolean isSetStatusColor = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ActivityUtils.addActivity(this);

        if(isSetStatusColor){
            Tools.setStatusColor(this, getResources().getColor(R.color.main_color));
        }

        loadingLay = (RelativeLayout) findViewById(R.id.base_loading_lay);
        noDataLay = (RelativeLayout) findViewById(R.id.base_no_data_lay);
        errorLay = (RelativeLayout) findViewById(R.id.base_error_lay);
        centerLay = (RelativeLayout) findViewById(R.id.base_content_lay);
        baseTitle = (BaseTitleView) findViewById(R.id.base_title_lay);

     //   ComponentHolder.getAppComponent().inject(this);
        showLoadingView();
        Log.e(TAG, "---------->onCreate" + mDataManager);

        errorLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryAgain();
            }
        });
    }

    /**
     * 添加中间正文视图
     *
     * @param resId 本地布局文件资源id
     */
    protected void setCenterLay(int resId) {
        centerLay.removeAllViews();
        centerLay.addView(View.inflate(this, resId, null), RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    /**
     * 添加中间正文视图
     *
     * @param view 子视图
     */
    protected void setCenterLay(View view) {
        centerLay.removeAllViews();
        centerLay.addView(view,RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }


    /**
     * 显示loading视图
     */
    protected void showLoadingView() {
        loadingLay.setVisibility(View.VISIBLE);
        noDataLay.setVisibility(View.GONE);
        errorLay.setVisibility(View.GONE);
        centerLay.setVisibility(View.GONE);
    }

    /**
     * 显示无数据视图
     */
    protected void showNoDataView() {
        loadingLay.setVisibility(View.GONE);
        noDataLay.setVisibility(View.VISIBLE);
        errorLay.setVisibility(View.GONE);
        centerLay.setVisibility(View.GONE);
    }

    /**
     * 显示错误视图
     */
    protected void showErrorView() {
        loadingLay.setVisibility(View.GONE);
        noDataLay.setVisibility(View.GONE);
        errorLay.setVisibility(View.VISIBLE);
        centerLay.setVisibility(View.GONE);
    }

    /**
     * 显示正文视图
     */
    protected void showCenterView() {
        loadingLay.setVisibility(View.GONE);
        noDataLay.setVisibility(View.GONE);
        errorLay.setVisibility(View.GONE);
        centerLay.setVisibility(View.VISIBLE);
    }

    /**
     * 设置界面标题名称
     *
     * @param title
     */
    protected void setTitleName(String title) {
        baseTitle.setTitleName(title);
    }

    /**
     * 设置右边文本
     * @param str
     */
    protected void setRightText(String str){
        baseTitle.setRightText(str);
    }
    /**
     * 设置右边控件可见
     * @param listener
     */
    protected void setRightOnClickListener(View.OnClickListener listener){
        baseTitle.setRightOnClickListener(listener);
    }
    /**
     * 设置右边控件监听
     * @param str
     */
    protected void setRightVisivle(boolean str){
        baseTitle.setRightVisible(str);
    }
    /**
     * 界面重新加载方法
     */
    protected void tryAgain(){
        showLoadingView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
    }
    //    /** 初始化视图*/
//    protected abstract void initView();
}
