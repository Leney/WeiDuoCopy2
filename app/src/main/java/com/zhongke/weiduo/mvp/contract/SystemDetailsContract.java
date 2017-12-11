package com.zhongke.weiduo.mvp.contract;

import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseView;
import com.zhongke.weiduo.mvp.model.entity.SystemListDetailBean;
import com.zhongke.weiduo.mvp.ui.widget.tree.treeview.TreeNode;

/**
 * Created by ${tanlei} on 2017/9/19.
 */

public interface SystemDetailsContract extends BaseView{
        String getBookId();
        void  showData(SystemListDetailBean systemListDetailBean);
        void showError(CommonException e);
        void createTreeView(TreeNode root);
}
