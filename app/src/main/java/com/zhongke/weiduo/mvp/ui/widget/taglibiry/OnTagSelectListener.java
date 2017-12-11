package com.zhongke.weiduo.mvp.ui.widget.taglibiry;

import java.util.List;

/**
 * Created by HanHailong on 15/10/20.
 */
public interface OnTagSelectListener {
    void onItemSelect(FlowTagLayout parent, List<Integer> selectedList);

    void onItemNoSelect(FlowTagLayout parent, List<Integer> selectedNoList);
}
