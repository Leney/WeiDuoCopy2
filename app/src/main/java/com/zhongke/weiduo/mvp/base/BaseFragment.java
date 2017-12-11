package com.zhongke.weiduo.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.mvp.model.api.service.DataManager;

/**
 * Created by llj on 2017/6/16.
 */

public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    protected RelativeLayout loadingLay;
    protected RelativeLayout noDataLay;
    protected RelativeLayout errorLay;
    protected RelativeLayout centerLay;


    protected DataManager mDataManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = DataManager.getInstance();
//        ComponentHolder.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_base, null, false);
        loadingLay = (RelativeLayout) baseView.findViewById(R.id.base_loading_lay);
        noDataLay = (RelativeLayout) baseView.findViewById(R.id.base_no_data_lay);
        errorLay = (RelativeLayout) baseView.findViewById(R.id.base_error_lay);
        centerLay = (RelativeLayout) baseView.findViewById(R.id.base_content_lay);


        showLoadingView();

        initView(baseView);

        errorLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryAgain();
            }
        });

        return baseView;
    }

    /**
     * 初始化视图
     *
     * @param baseView
     */
    protected void initView(View baseView) {

    }

    /**
     * 添加中间正文视图
     *
     * @param resId 本地布局文件资源id
     */
    protected void setCenterLay(int resId) {
        centerLay.removeAllViews();
        centerLay.addView(View.inflate(getActivity(), resId, null), RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }


    /**
     * 添加中间正文视图
     *
     * @param view 子视图
     */
    protected void setCenterLay(View view) {
        centerLay.removeAllViews();
        centerLay.addView(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
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
     * 界面重新加载方法
     */
    protected void tryAgain(){
        showLoadingView();
    }
}
