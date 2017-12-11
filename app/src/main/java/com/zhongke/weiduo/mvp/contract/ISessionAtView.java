package com.zhongke.weiduo.mvp.contract;


import android.widget.EditText;

import com.lqr.recyclerview.LQRRecyclerView;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Karma on 2017/6/21.
 * 类描述：聊天view
 */

public interface ISessionAtView {

    BGARefreshLayout getRefreshLayout();

    LQRRecyclerView getRvMsg();

    EditText getEtContent();

    void setCurrentIndex(int addPageIndex);

    int getSessionType();

    String getSessionId();

    void setRefreshState(boolean state);
}
