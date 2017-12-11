package com.zhongke.weiduo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhongke.weiduo.R;
import com.zhongke.weiduo.app.utils.LogUtil;
import com.zhongke.weiduo.library.retrofit.CommonException;
import com.zhongke.weiduo.mvp.base.BaseMvpActivity;
import com.zhongke.weiduo.mvp.base.BasePresenter;
import com.zhongke.weiduo.mvp.contract.FriendVerificationContract;
import com.zhongke.weiduo.mvp.presenter.FriendVerificationPresenter;
import com.zhongke.weiduo.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hyx on 2017/11/15.
 * 好友验证信息界面
 */

public class FriendVerificationActivity extends BaseMvpActivity implements FriendVerificationContract {

    private FriendVerificationPresenter presenter;

    @Bind(R.id.verification_mine_name)
    EditText mineName;
    @Bind(R.id.verification_note_name)
    EditText noteName;
    private String friendId;

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new FriendVerificationPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterLay(R.layout.activity_friend_verification);
        ButterKnife.bind(this);
        baseTitle.setTitleName("验证信息");
        baseTitle.setRightVisible(true);
        baseTitle.setRightText("发送");
        baseTitle.setRightOnClickListener(new SendClickListener());
        showCenterView();
        getIntentData();
        init();
    }

    private void init() {

    }

    private void initListener() {

    }

    private void getIntentData() {
        friendId = getIntent().getStringExtra("friendId");
        if (friendId.isEmpty()) {
            LogUtil.e("friendId 为空");
        }
        LogUtil.e("friendId-----"+friendId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void applyFriendRequestSuccess() {
        ToastUtil.showShort(this,"发送成功");
        showCenterView();
        finish();
    }

    @Override
    public void applyFriendRequestFailed(CommonException e) {
        showCenterView();
        ToastUtil.showShort(this,e.getErrorMsg());
    }

    private class SendClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String applyStr = mineName.getText().toString().trim();
            String friendNickName = noteName.getText().toString().trim();
            LogUtil.e("friendNickName--"+friendNickName);
            if (applyStr.isEmpty()) {
                ToastUtil.showShort(FriendVerificationActivity.this,"输入的名字不能为空");
            } else {
                presenter.applyFriend(friendId,applyStr,friendNickName);
                showLoadingView();
            }
        }
    }

    /**
     * @param friendId 想要添加的人的id
     */
    public static void openActivity(Context context,String friendId) {
        Intent intent = new Intent(context,FriendVerificationActivity.class);
        intent.putExtra("friendId",friendId);
        context.startActivity(intent);
    }

}
