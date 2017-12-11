package com.zhongke.weiduo.mvp.ui.activity;

import com.zhongke.weiduo.mvp.base.BaseMainActivity;
import com.zhongke.weiduo.mvp.contract.IUploadResultAtView;
import com.zhongke.weiduo.mvp.presenter.UploadResultAtPresentr;

/**
 * Created by Karma on 2017/6/30.
 * 类描述：上传成果
 */

public class UploadResultActivity extends BaseMainActivity<IUploadResultAtView, UploadResultAtPresentr> implements IUploadResultAtView {
    private static final String TAG = "UploadResultActivity";

    @Override
    protected UploadResultAtPresentr createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return 0;
    }
}
