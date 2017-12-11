package com.zhongke.weiduo.mvp.contract;


import android.widget.EditText;

import com.lqr.recyclerview.LQRRecyclerView;
import com.zhongke.weiduo.mvp.base.BaseView;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Karma on 2017/6/21.
 * 类描述：聊天view
 */

public interface ISessionAtView2 extends BaseView {

    BGARefreshLayout getRefreshLayout();

    LQRRecyclerView getRvMsg();

    EditText getEtContent();
}
