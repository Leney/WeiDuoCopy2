package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.HttpConstance;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.ActivityFlowContract;
import com.zhongke.weiduo.mvp.presenter.ActivityFlowPresenter;

/**
 * Created by hyx on 2017/12/7.
 *
 */

public class ActivityFlowActivity extends BaseMvpActivity implements ActivityFlowContract {

    private int actionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName("活动流程");
        setCenterLay(R.layout.activity_activity_flow);
        showCenterView();
        receiverIntent();
        initView();
    }

    private void receiverIntent() {
        actionId = getIntent().getIntExtra("actionId",-1);
        LogUtil.e("actionId---"+actionId);
        if (actionId == -1) {
            finish();
            return;
        }
    }

    private void initView() {
        WebView webView = (WebView) findViewById(R.id.activity_activity_flow_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String token = ZkApplication.getInstance().getToken();
        String url = HttpConstance.BASE_URL+"/action/action_flow_view"+"?token="+token+"&actionId="+actionId;
        webView.loadUrl(url);
    }

    @Override
    protected BasePresenter createPresenter() {
        ActivityFlowPresenter presenter = new ActivityFlowPresenter(ActivityFlowActivity.this,this);
        return presenter;
    }

    public static void openActivity(Context context,int actionId) {
        Intent intent = new Intent(context,ActivityFlowActivity.class);
        intent.putExtra("actionId",actionId);
        context.startActivity(intent);
    }
}
